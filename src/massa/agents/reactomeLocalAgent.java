/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# reactomeLocalAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues e Giordano Bruno Soares Souza
# Contributor(s):
# Updated by (and date): 27/12/2013
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

//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
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

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
//import diverenrich.BioData.Gene;
//import diverenrich.BioData.UCSC;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioDataLite;

public class reactomeLocalAgent extends DBagent {

	/* Atributes */
	BioDataLite reactomedata;
	int input_length; // * o tamanho sempre sera igual ao numero de
						// testes. sera passado futuramente, junto com a
						// lista de entradas.

	int[] input; // ** futuramente a lista sera passado como
					// mensagem recebida do agente coordenador

	// Set host address (localhost)
	public String sn = "localhost";
	// Set database used
	public String md = "reactome";
	// Set User Name
	public String user = "reactome_user";
	// Set User Password
	public String key = "reactome_pass";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	// MySQLcon myconnection;

	public reactomeLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("reactome");
		this.setInformation("reactome");
		this.reactomedata = new BioDataLite();

	} // End of ucscAgent constructor

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		/* Here DBAgent must connect to annotation db */
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
					reactomedata.setSearchid(getAnnsearchid());
					/* Here agent gets the annotation db connection */
					// annconnection = omimdata.getConnection();
					addBehaviour(new reactomeAction(sender, contentdata));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class reactomeAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		// public AID partneragent;
		// public int[] snpidlist;

		public reactomeAction(AID pa, BioDataLite bdlite) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessreactome(bdlite), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			// myconnection.mysqlDisconnect();
			// mysqlDisconnect(conn);
			// annDisconnect();

			return super.onEnd();
		}
	}

	class accessreactome extends ParallelBehaviour {
		BioDataLite contentdata;
		private int[] snpidlist; // Declare Array of SNPs

		public accessreactome(BioDataLite bdl) {
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
					this.addSubBehaviour(new mysqlLocalreactomequery(rs.getString("gene_symbol")));

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
			// mysqlDisconnect(conn);
			return super.onEnd();
		} // Ends ucsclAgent
	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlLocalreactomequery extends OneShotBehaviour {

		private String genesymbol;
		private boolean errflag;

		public mysqlLocalreactomequery(String gs) {
			genesymbol = gs;
			this.errflag = false;

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			Queryreactome(genesymbol);
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

	public void Queryreactome(String genesymbol) {

		String pathway = "";

		String strsql = "SELECT reactome.pathway, " + "reactome.gene " + "FROM reactome " + "WHERE "
				+ "reactome.gene = ? ;";

		// System.out.println("SQL:"+strsql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);
			ResultSet rs = pstmt.executeQuery();

			// System.out.println("SQL:"+pstmt);

			while (rs.next()) {

				// GET pathway
				if (pathway == "") {
					pathway = rs.getString(1);
				} else {
					pathway = pathway + ";" + rs.getString(1);
				}

				// System.out.println("Pathway = "+pathway);
			} // End of rs.next while m

			rs.close();
			pstmt.close();

			if (pathway.length() > 0) {
				//
			} else {
				pathway = "null";
			}

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			// String sql = "insert ignore into reactome
			// (genesymbol,pathway,fk_searchid) values
			// ('"+genesymbol+"','"+pathway+"',"+this.getAnnsearchid() +")";
			String sql = "insert ignore into reactome (genesymbol,pathway,fk_searchid) values (?,?,?)";

			// Statement stm = annconnection.createStatement();
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);

			pstmt2.setString(1, genesymbol);
			pstmt2.setString(2, pathway);
			pstmt2.setInt(3, this.getAnnsearchid());

			pstmt2.executeUpdate();
			pstmt2.close();

			// stm.executeUpdate(sql);

			// stm.close();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of QueryReactome

} // End of reactomeLocalAgent
