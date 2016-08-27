/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# DBsnpAgent.java
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

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;
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

//import diverenrich.BioData.Gene;
//import diverenrich.BioData.GeneOntology;
//import diverenrich.DBsnpLocalAgent.remoteAccessDBsnpLocal;
//import diverenrich.GOAgent.GOAction;
//import diverenrich.GOAgent.mysqlRemoteGOqueryTwo;
//import diverenrich.PharmgkbAgent.PharmgkbAction;

public class GOLocalAgent extends DBagent {

	public String sn = "localhost";
	public String md = "GO";
	// public String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	public String gouser = "go";
	public String gokey = "amigo";

	BioDataLite godata;

	public GOLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */
		this.setDBname("go");
		this.setInformation("gene ontology");

		this.godata = new BioDataLite();

	} // End of GOAgent constructor

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		/* Here DBsnpAgent must connect to annotation db */
		// this.annConnect();

		/* Here DBagent must connect with its local database */
		this.dbConnect(sn, md, gouser, gokey);

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
					godata.setSearchid(getAnnsearchid());
					addBehaviour(new GOAction(sender, contentdata));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class GOAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public GOAction(AID pa, BioDataLite bd) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessGO(bd), STATE_A);
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

	class accessGO extends ParallelBehaviour {
		BioDataLite contentdata;

		public accessGO(BioDataLite bd) {
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
				// Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					this.addSubBehaviour(new mysqlLocalGOquery(rs.getString("gene_symbol")));

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
	class mysqlLocalGOquery extends OneShotBehaviour {

		private String genesymbol;
		private boolean errflag;

		public mysqlLocalGOquery(String g) {

			this.genesymbol = g;
			this.errflag = false;
		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			QueryGOLocal(genesymbol);
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
				msg.setContentObject(godata);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void QueryGOLocal(String gs) {

		String bp = "";
		String cc = "";
		String mf = "";

		List<String> bpl;
		List<String> ccl;
		List<String> mfl;

		bpl = new ArrayList<String>();
		ccl = new ArrayList<String>();
		mfl = new ArrayList<String>();

		String strsql = "SELECT  gene_product.symbol, " +
		// "gene_product.full_name, " +
				"term.term_type, " + "term.name " +
				// "term.acc, " +
				// "evidence.code, " +
				// "dbxref.xref_dbname, " +
				// "dbxref.xref_key " +
				"FROM gene_product " +
				// "INNER JOIN dbxref ON (gene_product.dbxref_id=dbxref.id) " +
				"INNER JOIN species ON (gene_product.species_id=species.id) "
				+ "INNER JOIN association ON (gene_product.id=association.gene_product_id) " +
				// "INNER JOIN evidence ON
				// (association.id=evidence.association_id) " +
				"INNER JOIN term ON (association.term_id=term.id) " + "WHERE gene_product.symbol = ? "
				+ "AND gene_product.species_id = '596693';";

		try {

			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, gs);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// String geneSymbol = rs.getString("symbol");
				// String geneName = rs.getString("full_name");
				String termType = rs.getString("term_type");
				String termName = rs.getString("name");
				// String termAcc = rs.getString("acc");
				// String termCode = rs.getString("code");
				// String dbName = rs.getString("xref_dbname");
				// String dbkey = rs.getString("xref_key");

				// System.out.println("GO Agent term type is "+termType+" term
				// name is "+termName);
				if (termType.equals("biological_process")) {
					// goobject.setBioProcessItem(termName);
					// String[] values = termName.split(",");
					// for (int i = 0; i < values.length; ++i) {
					// if(!values[i].equals("")){
					if (!termName.equals("")) {
						// if(!bpl.contains(values[i])){
						if (!bpl.contains(termName)) {
							// bp = bp+";"+values[i];
							// bpl.add(values[i]);
							bp = bp + ";" + termName;
							bpl.add(termName);
						}
					}
					// }
				}
				if (termType.equals("cellular_component")) {
					// goobject.setCelComponentItem(termName);
					// String[] values = termName.split(",");
					// for (int i = 0; i < values.length; ++i) {
					// if(!values[i].equals("")){
					if (!termName.equals("")) {
						// if(!ccl.contains(values[i])){
						if (!ccl.contains(termName)) {
							// cc = cc+";"+values[i];
							// ccl.add(values[i]);
							cc = cc + ";" + termName;
							ccl.add(termName);
						}
					}
					// }
				}
				if (termType.equals("molecular_function")) {
					// goobject.setMolFunctionItem(termName);
					// String[] values = termName.split(",");
					// for (int i = 0; i < values.length; ++i) {
					// if(!values[i].equals("")){
					if (!termName.equals("")) {
						// if(!mfl.contains(values[i])){
						if (!mfl.contains(termName)) {
							// mf = mf+";"+values[i];
							// mfl.add(values[i]);
							mf = mf + ";" + termName;
							mfl.add(termName);
						}
					}
					// }
				}

			} // End of rs.next while

			rs.close();
			pstmt.close();

			if (bp.length() > 0) {
				bp = bp.substring(1);
			} else {
				bp = "null";
			}
			if (cc.length() > 0) {
				cc = cc.substring(1);
			} else {
				cc = "null";
			}
			if (mf.length() > 0) {
				mf = mf.substring(1);
			} else {
				mf = "null";
			}

			// test for special characters
			// String bpn = bp.replaceAll("'","'");
			// String ccn = cc.replaceAll("'","'");
			// String mfn = mf.replaceAll("'","'");

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			// String sql = "insert ignore into geneOntology
			// (gp_symbol,molFunction,celComp,bioProcess,fk_searchid) values
			// ('"+gs+"','"+mfn+"','"+ccn+"','"+bpn+"',"+this.getAnnsearchid()
			// +")";
			String upsql = "insert ignore into geneOntology (gp_symbol,molFunction,celComp,bioProcess,fk_searchid) values (?,?,?,?,?)";

			// Statement stm = annconnection.createStatement();
			PreparedStatement pstm = annconnection.prepareStatement(upsql);
			pstm.setString(1, gs);
			pstm.setString(2, mf);
			pstm.setString(3, cc);
			pstm.setString(4, bp);
			pstm.setInt(5, this.getAnnsearchid());

			pstm.executeUpdate();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of QueryGOLocal

} // End of GOAgent
