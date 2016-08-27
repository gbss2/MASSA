/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# PharmgkbLocalAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues
# Contributor(s):
# Updated by (and date):
#
# Command line:
#
# Dependencies: JADE jar files (jade.jar,commons-codec-1.3.jar)
#
# Description:  Class for DBAgents
#
# Parameters:
#
#/
 */

package massa.agents;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.Set;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioDataLite;

public class PharmgkbLocalAgent extends DBagent {

	public String sn = "localhost";
	public String md = "pgkb";
	// public String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	public String pharmuser = "pharm";
	public String pharmkey = "pharmdb";
	BioDataLite pharmdata;
	// MySQLcon myconnection;

	public PharmgkbLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */
		this.setDBname("dbpharmgkb");
		this.setInformation("pharmgkb");

		this.pharmdata = new BioDataLite();

	} // End of GOAgent constructor

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();
		/* Here DBAgent must connect to annotation db */
		// this.annConnect();

		/* Here DBagent must connect with its local database */
		this.dbConnect(sn, md, pharmuser, pharmkey);

		/* Add a behaviour */
		addBehaviour(new waitRequest());

	} // End of Agent Setup

	// LIST OF BEHAVIOURS
	protected void takeDown() {
		System.out.println("Agent" + getLocalName() + " shutdown.");
		mysqlDisconnect(conn);
	}

	class waitRequest extends CyclicBehaviour {

		private MessageTemplate simplerequest_template;
		private AID sender;

		public waitRequest() {
			simplerequest_template.MatchPerformative(ACLMessage.REQUEST);
		}

		public void action() {
			ACLMessage msg = myAgent.receive(simplerequest_template);
			if (msg != null) {
				sender = msg.getSender();
				System.out.println("Agent " + getLocalName() + " received a REQUEST message from agent "
						+ msg.getSender().getName());
				try {
					BioDataLite contentdata = (BioDataLite) msg.getContentObject();
					setAnnsearchid(contentdata.getSearchid());
					pharmdata.setSearchid(getAnnsearchid());
					/* Here agent gets the annotation db connection */
					// annconnection = pharmdata.getConnection();
					addBehaviour(new PharmAction(sender, contentdata));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class PharmAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public PharmAction(AID pa, BioDataLite bd) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessPharm(bd), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			// mysqlDisconnect(conn);
			// annDisconnect();

			return super.onEnd();
		}

	}

	class accessPharm extends ParallelBehaviour {
		BioDataLite contentdata;

		public accessPharm(BioDataLite bd) {
			super(WHEN_ALL);
			contentdata = bd;

		}

		public void onStart() {

			// get gene symbol list for searchid
			String query = "SELECT * FROM gene WHERE fk_searchid=" + getAnnsearchid();
			try {
				// Connection connection =
				// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");
				Statement st = annconnection.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					this.addSubBehaviour(new mysqlLocalPharmquery(rs.getString("gene_symbol")));
				}

				st.close();
				rs.close();
				// connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			// mysqlDisconnect(conn);
			// annDisconnect();

			return super.onEnd();
		}

	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlLocalPharmquery extends OneShotBehaviour {

		private String genesymbol;
		private boolean errflag;

		public mysqlLocalPharmquery(String g) {
			this.genesymbol = g;
			this.errflag = false;
		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			// QueryPGKBLocalSimplified(genesymbol);
			QueryPGKBLocal(genesymbol);
		}

	}

	class SendReply extends OneShotBehaviour {

		private AID msgreceiver;
		private String msgperformative;
		private ACLMessage msg;

		public SendReply(AID p) {
			msgreceiver = p;
			msgperformative = "INFORM";
		}

		public void action() {

			try {
				System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
				msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(msgreceiver);
				msg.setLanguage("English");
				msg.setContentObject(pharmdata);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // PharmGKB Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public void QueryPGKBLocalSimplified(String genesymbol) {

		PreparedStatement pstmt;
		PreparedStatement pstmt2;
		String strsql;
		ResultSet rs;

		String pw = "";
		String ds = "";
		String dg = "";
		// String rg = null;
		// String lt = "";

		/*
		 * String strsql = "select gene.pgkbkey \"geneSymbol\" ," +
		 * "gene.pgkbId \"genepgkbId\", " + "gene.relatedGenes, " +
		 * "goldenPath.name \"polyLoc\", " + "goldenPath.pgkbKey \"polyId\", " +
		 * "haplotype.name \"haplotype\", " + "drug.name \"drug\", " +
		 * "disease.name \"disease\", " + "pathway.name \"pathName\", " +
		 * "pathway.type \"pathType\", " + "literature.pgkbkey \"literature\"" +
		 * "FROM gene " + "LEFT JOIN goldenPath ON gene.fk = goldenPath.fk " +
		 * "LEFT JOIN haplotype ON gene.fk = haplotype.fk " +
		 * "LEFT JOIN drug ON gene.fk = drug.fk " +
		 * "LEFT JOIN disease ON gene.fk = disease.fk " +
		 * "LEFT JOIN pathway ON gene.fk = pathway.fk " +
		 * "LEFT JOIN literature ON gene.fk = literature.fk " + "WHERE " +
		 * "gene.pgkbkey = ? ;";
		 */

		strsql = "select distinct gene.pgkbkey \"geneSymbol\" ," +
		// "gene.pgkbId \"genepgkbId\", " +
		// "gene.relatedGenes, " +
				"goldenPath.name \"polyLoc\", " + "goldenPath.pgkbKey \"polyId\", " +
				// "haplotype.name \"haplotype\", " +
				"drug.name \"drug\", " + "disease.name \"disease\", " +
				// "literature.pgkbkey \"literature\", " +
				// "pathway.name \"pathType\", " +
				"pathway.type \"pathName\" " + "FROM gene " + "LEFT JOIN goldenPath ON gene.fk = goldenPath.fk " +
				// "LEFT JOIN haplotype ON gene.fk = haplotype.fk " +
				"LEFT JOIN drug ON gene.fk = drug.fk " + "LEFT JOIN disease ON gene.fk = disease.fk "
				+ "LEFT JOIN pathway ON gene.fk = pathway.fk " +
				// "LEFT JOIN literature ON gene.fk = literature.fk " +
				"WHERE " + "gene.pgkbkey = ? " + "GROUP BY polyLoc, polyId, drug, disease, pathName "
				+ "HAVING COUNT(*) = 1;";

		try {
			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			rs = pstmt.executeQuery();

			// System.out.println("Result request:");
			while (rs.next()) {

				// String polyLoc = rs.getString("polyLoc");
				// String polyId = rs.getString("polyId");
				// String haplotype = rs.getString("haplotype");
				dg = dg + ";" + rs.getString("drug");
				ds = ds + ";" + rs.getString("disease");
				pw = pw + ";" + rs.getString("pathName");
				// lt = lt + ";"+ rs.getString("literature");

			} // End of rs.next while

			rs.close();
			pstmt.close();

			// insert result into annotationdb
			if (dg.length() > 0) {
				dg = dg.substring(1);
			} else {
				dg = "null";
			}
			if (pw.length() > 0) {
				pw = pw.substring(1);
			} else {
				pw = "null";
			}
			// if(rg.length()>0){
			// rg = rg.substring(1);
			// }else{
			// rg = "null";
			// }
			if (ds.length() > 0) {
				ds = ds.substring(1);
			} else {
				ds = "null";
			}

			// test for special characters
			// String dsn = ds.replaceAll("'","L");
			// String dgn = dg.replaceAll("'","L");
			// String rgn = rg.replaceAll("'","L");
			// String pwn = pw.replaceAll("'","L");

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// String sql = "insert ignore into pharmGKB
			// (geneSymbol,drugs,pathway,disease,fk_searchid) values
			// ('"+genesymbol+"','"+dgn+"','"+pwn+"','"+rgn+"','"+dsn+"',"+this.getAnnsearchid()
			// +")";
			String sql = "insert ignore into pharmGKB (geneSymbol,drugs,pathway,disease,fk_searchid) values (?,?,?,?,?)";
			// System.out.println("SQL:"+sql);
			PreparedStatement pstm2 = annconnection.prepareStatement(sql);
			pstm2.setString(1, genesymbol);
			pstm2.setString(2, dg);
			pstm2.setString(3, pw);
			pstm2.setString(4, ds);
			pstm2.setInt(5, this.getAnnsearchid());

			pstm2.executeUpdate();
			pstm2.close();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query pharmGKB

	public void QueryPGKBLocal(String genesymbol) {

		String strsql;
		ResultSet rs;

		String pw = "";
		String ds = "";
		String dg = "";
		// String rg = "";
		// String lt = "";

		try {

			// gene and golden path
			// strsql = "select distinct gene.pgkbkey \"geneSymbol\"
			// ,gene.pgkbId \"genepgkbId\", goldenPath.name \"polyLoc\",
			// goldenPath.pgkbKey \"polyId\" FROM gene LEFT JOIN goldenPath ON
			// gene.fk = goldenPath.fk WHERE gene.pgkbkey = ? ;";

			// pstmt = conn.prepareStatement(strsql);
			// pstmt.setString(1,genesymbol);
			//
			// rs = pstmt.executeQuery();
			//
			//// System.out.println("Result request:");
			// while (rs.next()) {
			//
			// String polyLoc = rs.getString("polyLoc");
			// String polyId = rs.getString("polyId");
			//// String[] values = rs.getString("relatedGenes").split(",");
			//// for (int i = 0; i < values.length; ++i) {
			//// if(!values[i].equals("")){
			//// rg = rg +";"+ values[i];
			//// }
			//// }
			//
			// System.out.println("polyLoc:"+polyLoc);
			//
			// } // End of rs.next while
			// rs.close();
			// pstmt.close();
			// System.out.println("related genes:"+rg);

			// haplotype
			// strsql = "select distinct gene.pgkbkey \"geneSymbol\",
			// haplotype.name \"haplotype\" FROM gene LEFT JOIN haplotype ON
			// gene.fk = haplotype.fk WHERE gene.pgkbkey = ? ;";
			//
			// pstmt = conn.prepareStatement(strsql);
			// pstmt.setString(1,genesymbol);
			//
			// rs = pstmt.executeQuery();
			//
			//
			// while (rs.next()) {
			//
			// String haplotype = rs.getString("haplotype");
			//
			// System.out.println("haplytype:"+haplotype);
			// } // End of rs.next while
			// rs.close();
			// pstmt.close();

			// drug
			strsql = "select distinct drug.name \"drug\" FROM gene LEFT JOIN drug ON gene.fk = drug.fk WHERE gene.pgkbkey = ? ;";

			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				dg = dg + ";" + rs.getString("drug");
			} // End of rs.next while
				// rs.close();
				// pstmt.close();
				// System.out.println("drug:"+dg);

			// disease
			strsql = "select distinct disease.name \"disease\" FROM gene LEFT JOIN disease ON gene.fk = disease.fk WHERE gene.pgkbkey = ? ;";

			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ds = ds + ";" + rs.getString("disease");
			} // End of rs.next while
				// rs.close();
				// pstmt.close();
				// System.out.println("disease:"+ds);

			// pathway
			strsql = "select distinct pathway.name \"pathName\", pathway.type \"pathType\" FROM gene LEFT JOIN pathway ON gene.fk = pathway.fk WHERE gene.pgkbkey = ? ;";

			pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				pw = pw + ";" + rs.getString("pathName");

			} // End of rs.next while
				// rs.close();
				// pstmt.close();
				// System.out.println(" pathway:"+pw);

			// literature
			// strsql = "select distinct gene.pgkbkey \"geneSymbol\",
			// literature.pgkbkey \"literature\" FROM gene LEFT JOIN literature
			// ON gene.fk = literature.fk WHERE gene.pgkbkey = ? ;";
			//
			// pstmt = conn.prepareStatement(strsql);
			// pstmt.setString(1,genesymbol);
			//
			// rs = pstmt.executeQuery();
			//
			//
			// while (rs.next()) {
			// lt = lt + ";"+ rs.getString("literature");
			//
			// } // End of rs.next while
			// System.out.println("literature:"+lt);

			rs.close();
			pstmt.close();

			// insert result into annotationdb
			if (dg.length() > 0) {
				dg = dg.substring(1);
			} else {
				dg = "null";
			}
			if (pw.length() > 0) {
				pw = pw.substring(1);
			} else {
				pw = "null";
			}
			// if(rg.length()>0){
			// rg = rg.substring(1);
			// }else{
			// rg = "null";
			// }
			if (ds.length() > 0) {
				ds = ds.substring(1);
			} else {
				ds = "null";
			}

			// test for special characters
			// String dsn = ds.replaceAll("'","L");
			// String dgn = dg.replaceAll("'","L");
			// String rgn = rg.replaceAll("'","L");
			// String pwn = pw.replaceAll("'","L");

			// String sql = "insert ignore into pharmGKB
			// (geneSymbol,drugs,pathway,geneCross,disease,fk_searchid) values
			// ('"+genesymbol+"','"+dgn+"','"+pwn+"','"+rgn+"','"+dsn+"',"+this.getAnnsearchid()
			// +")";
			String sql = "insert ignore into pharmGKB (geneSymbol,drugs,pathway,disease,fk_searchid) values (?,?,?,?,?)";
			// System.out.println("SQL:"+sql);
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setString(1, genesymbol);
			pstmt2.setString(2, dg);
			pstmt2.setString(3, pw);
			// pstmt2.setString(4,rgn);
			pstmt2.setString(4, ds);
			pstmt2.setInt(5, this.getAnnsearchid());

			pstmt2.executeUpdate();
			pstmt2.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query pharmGKB

} // End of Agent Class PharmGKB Local
