/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# omimLocalAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues, Giordano Bruno Soares-Souza
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

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.io.IOException;
//import java.net.URL;
//import java.net.MalformedURLException;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;

//import diverenrich.BioData.Gene;
//import diverenrich.BioData.OMIM;
//import diverenrich.DBsnpLocalAgent.remoteAccessDBsnpLocal;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioDataLite;

public class omimLocalAgent extends DBagent {

	/* Atributes */
	BioDataLite omimdata;
	int input_length; // * o tamanho sempre sera igual ao numero de
						// testes. sera passado futuramente, junto com a
						// lista de entradas.

	int[] input; // ** futuramente a lista sera passado como
					// mensagem recebida do agente coordenador

	// Set host address (localhost)
	public String sn = "localhost";
	// Set database used
	public String md = "OMIM";
	// Set User Name
	public String user = "omim";
	// Set User Password
	public String key = "omimdb";

	// PreparedStatement pstmt2 = null;

	public omimLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("omim");
		this.setInformation("omim");
		this.omimdata = new BioDataLite();

	} // End of ucscAgent constructor

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		/* Here DBsnpAgent must connect to annotation db */
		// this.annConnect();

		/* Here DBagent must connect with its local database */
		this.dbConnect(sn, md, user, key);

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
					omimdata.setSearchid(getAnnsearchid());
					/* Here agent gets the annotation db connection */
					// annconnection = omimdata.getConnection();
					addBehaviour(new omimAction(sender, contentdata));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class omimAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public omimAction(AID pa, BioDataLite bdlite) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessOMIM(bdlite), STATE_A);
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

	class accessOMIM extends ParallelBehaviour {
		BioDataLite contentdata;

		private int[] snpidlist; // Declare Array of SNPs

		public accessOMIM(BioDataLite bdl) {
			super(WHEN_ALL);
			contentdata = bdl;
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
					this.addSubBehaviour(new mysqlRemoteOMIMquery(rs.getString("gene_symbol")));

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
		} // Ends ucsclAgent
	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlRemoteOMIMquery extends OneShotBehaviour {

		private String genesymbol;
		private boolean errflag;

		public mysqlRemoteOMIMquery(String gs) {

			genesymbol = gs;
			this.errflag = false;
		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			// QueryOMIM(genesymbol);
			QueryOMIMmorbidmap(genesymbol);
		}

	}

	class SendReply extends OneShotBehaviour {

		private AID msgreceiver;
		private String msgperformative;
		private BioDataLite msgcontent;
		private ACLMessage msg;

		public SendReply(AID p) {
			msgreceiver = p;
			msgcontent = new BioDataLite();
			msgperformative = "INFORM";
			msgcontent.setSearchid(getAnnsearchid());

		} // End of SendReply

		public void action() {

			try {
				System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
				msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(msgreceiver);
				msg.setLanguage("English");
				msg.setContentObject(msgcontent);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // OMIM Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public void QueryOMIM(String genesymbol) {

		String cl = "";
		String ds = "";
		String cm = "";
		String rf = "";
		String gs = "";
		String mt = "";

		List<String> dsl;
		List<String> cml;
		List<String> cll;
		List<String> gsl;
		List<String> mtl;

		dsl = new ArrayList<String>();
		cml = new ArrayList<String>();
		cll = new ArrayList<String>();
		gsl = new ArrayList<String>();
		mtl = new ArrayList<String>();

		String strsql = "select genemap.geneSymbol," + "genemap.cytoLoc, " + "genemap.geneStatus, " + "genemap.method, "
				+ "genemap.disorder1, " + "genemap.disorder2, " + "genemap.disorder3, " + "genemap.mimId, "
				+ "genemap.comments1, " + "genemap.comments2, " + "genemap.reference " + "FROM genemap " + "WHERE "
				+ "genemap.geneSymbol = ? ;";

		// System.out.println("SQL:"+strsql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String geneSymbol = rs.getString("geneSymbol");
				String cytoLoc = rs.getString("cytoLoc");
				String geneStatus = rs.getString("geneStatus");
				String method = rs.getString("method");
				String disorder1 = rs.getString("disorder1");
				String disorder2 = rs.getString("disorder2");
				String disorder3 = rs.getString("disorder3");
				String omimId = rs.getString("mimId");
				String comments1 = rs.getString("comments1");
				String comments2 = rs.getString("comments2");
				String reference = rs.getString("reference");

				if (!cll.contains(cytoLoc)) {
					cl = cl + ";" + cytoLoc;
					cll.add(cytoLoc);
				}

				// gs = gs+";"+geneStatus;

				if (!gsl.contains(geneStatus) && geneStatus != "null") {
					gs = gs + ";" + geneStatus;
					gsl.add(geneStatus);
				}

				// mt = mt+";"+method;

				if (!mtl.contains(method) && method != "null") {
					mt = mt + ";" + method;
					mtl.add(method);
				}

				if (!dsl.contains(disorder1) && disorder1 != "null") {
					ds = ds + ";" + disorder1;
					dsl.add(disorder1);
				}
				if (!dsl.contains(disorder2) && disorder2 != "null") {
					ds = ds + ";" + disorder2;
					dsl.add(disorder2);
				}
				if (!dsl.contains(disorder3) && disorder3 != "null") {
					ds = ds + ";" + disorder3;
					dsl.add(disorder3);
				}

				if (!cml.contains(comments1) && comments1 != "null") {
					cm = cm + ";" + comments1;
					cml.add(comments1);
				}
				if (!cml.contains(comments2) && comments2 != "null") {
					cm = cm + ";" + comments2;
					cml.add(comments2);
				}

				rf = rf + ";" + reference;

			} // End of rs.next while

			rs.close();
			pstmt.close();

			if (ds.length() > 0) {
				ds = ds.substring(1);
			} else {
				ds = "null";
			}
			if (cm.length() > 0) {
				cm = cm.substring(1);
			} else {
				cm = "null";
			}
			if (cl.length() > 0) {
				cl = cl.substring(1);
			} else {
				cl = "null";
			}
			if (gs.length() > 0) {
				gs = gs.substring(1);
			} else {
				gs = "null";
			}
			if (mt.length() > 0) {
				mt = mt.substring(1);
			} else {
				mt = "null";
			}
			if (rf.length() > 0) {
				rf = rf.substring(1);
			} else {
				rf = "null";
			}

			// test for special characters
			// String dsn = ds.replaceAll("'","L");
			// String cmn = cm.replaceAll("'","L");
			// String cln = cl.replaceAll("'","L");
			// String rfn = rf.replaceAll("'","L");
			// String mtn = mt.replaceAll("'","L");

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");
			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation"
			// +
			// "user=annotation&password=1masannotation1");

			// insert result into annotationdb
			// String sql = "insert ignore into omim
			// (fk_searchid,cytoLoc,geneStatus,methods,disorder,comments,reference,gene_symbol)
			// values
			// ("+this.getAnnsearchid()+",'"+cln+"','"+gs+"','"+mtn+"','"+dsn+"','"+cmn+"','"+rfn+"','"+genesymbol+"')";
			String sql = "insert ignore into omim (fk_searchid,cytoLoc,geneStatus,methods,disorder,comments,reference,gene_symbol) values (?,?,?,?,?,?,?,?)";
			// System.out.println("SQL:"+sql);
			// Statement stm = annconnection.createStatement();
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setInt(1, this.getAnnsearchid());
			pstmt2.setString(2, cl);
			pstmt2.setString(3, gs);
			pstmt2.setString(4, mt);
			pstmt2.setString(5, ds);
			pstmt2.setString(6, cm);
			pstmt2.setString(7, rf);
			pstmt2.setString(8, genesymbol);

			pstmt2.executeUpdate();
			pstmt2.close();
			// connection.close();

			// stm.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query OMIM Local 1

	public void QueryOMIMmorbidmap(String genesymbol) {

		String cl = "";
		String ds = "";
		String pm = "";
		String mimId = "";

		List<String> dsl;
		List<String> pml;
		List<String> cll;
		List<String> mid;

		dsl = new ArrayList<String>();
		pml = new ArrayList<String>();
		cll = new ArrayList<String>();
		mid = new ArrayList<String>();

		String strsql = "select morbidmap.gene," + "morbidmap.cytoLoc, " + "morbidmap.phenomap, "
				+ "morbidmap.disorder, " + "morbidmap.omimDisorderId " + "FROM morbidmap " + "WHERE "
				+ "morbidmap.gene = ? ;";

		// System.out.println("SQL:"+strsql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String geneSymbol = rs.getString("gene");
				String cytoLoc = rs.getString("cytoLoc");
				String disorder = rs.getString("disorder");
				String omimId = rs.getString("omimDisorderId");
				String phenoMap = rs.getString("phenoMap");

				if (!cll.contains(cytoLoc)) {
					cl = cl + ";" + cytoLoc;
					cll.add(cytoLoc);
				}

				if (!dsl.contains(disorder) && disorder != "null") {
					ds = ds + ";" + disorder;
					dsl.add(disorder);

					pm = pm + ";" + phenoMap;
					pml.add(phenoMap);

					mimId = mimId + ";" + omimId;
					mid.add(omimId);

				}

			} // End of rs.next while

			rs.close();
			pstmt.close();

			if (ds.length() > 0) {
				ds = ds.substring(1);
			} else {
				ds = "null";
			}
			if (pm.length() > 0) {
				pm = pm.substring(1);
			} else {
				pm = "null";
			}
			if (cl.length() > 0) {
				cl = cl.substring(1);
			} else {
				cl = "null";
			}
			if (mimId.length() > 0) {
				mimId = mimId.substring(1);
			} else {
				mimId = "null";
			}

			// insert result into annotationdb
			// String sql = "insert ignore into omim
			// (fk_searchid,cytoLoc,geneStatus,methods,disorder,comments,reference,gene_symbol)
			// values
			// ("+this.getAnnsearchid()+",'"+cln+"','"+gs+"','"+mtn+"','"+dsn+"','"+cmn+"','"+rfn+"','"+genesymbol+"')";
			String sql = "insert ignore into omim (fk_searchid,cytoLoc,phenMap,disorder,mimId,gene_symbol) values (?,?,?,?,?,?)";
			// System.out.println("SQL:"+sql);
			// Statement stm = annconnection.createStatement();
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setInt(1, this.getAnnsearchid());
			pstmt2.setString(2, cl);
			pstmt2.setString(3, pm);
			pstmt2.setString(4, ds);
			pstmt2.setString(5, mimId);
			pstmt2.setString(6, genesymbol);

			pstmt2.executeUpdate();
			pstmt2.close();
			// connection.close();

			// stm.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query OMIM Local 1

} // End of omimLocalAgent
