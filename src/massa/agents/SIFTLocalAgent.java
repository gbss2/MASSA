/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# SIFTLocalAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues e Giordano Bruno Soares Souza
# Contributor(s):
# Updated by (and date): 17/12/2013
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
import massa.database.MySQLcon;

public class SIFTLocalAgent extends DBagent {

	/* Atributes */
	BioDataLite snpdata;;
	int input_length; // * o tamanho sempre sera igual ao numero de
						// testes. sera passado futuramente, junto com a
						// lista de entradas.

	int[] input; // ** futuramente a lista sera passado como
					// mensagem recebida do agente coordenador

	// Set host address (localhost)
	public String sn = "localhost";
	// Set database used
	public String md = "sift";
	// Set User Name
	public String user = "sift";
	// Set User Password
	public String key = "sift132";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	MySQLcon myconnection;

	public SIFTLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("SIFT");
		this.setInformation("SIFT");

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
	class waitRequest extends CyclicBehaviour {

		private MessageTemplate simplerequest_template;
		private AID sender;

		public waitRequest() {
			simplerequest_template.MatchPerformative(ACLMessage.REQUEST);
		}

		public void action() {
			ACLMessage msg = myAgent.receive(simplerequest_template);
			if (msg != null) {
				System.out.println("Agent " + getLocalName() + " received a REQUEST message from agent "
						+ msg.getSender().getName());
				try {
					sender = msg.getSender();
					snpdata = (BioDataLite) msg.getContentObject();
					addBehaviour(new SIFTAction(snpdata.getSnpIdList(), sender, snpdata.getSearchid()));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class SIFTAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public SIFTAction(int[] sl, AID pa, int asi) {
			partneragent = pa;
			snpidlist = sl;
			setAnnsearchid(asi);

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessSIFT(snpidlist), STATE_A);
			this.registerLastState(new SendReply(partneragent), STATE_B);
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

	class accessSIFT extends ParallelBehaviour {
		private int[] snpidlist; // Declare Array of SNPs

		public accessSIFT(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;
		}

		public void onStart() {

			// Initialize a behaviour for each snp
			for (int i = 0; i < snpidlist.length; i++) {
				if (snpidlist[i] > 0) {
					this.addSubBehaviour(new mysqlLocalSIFTquery(snpidlist[i]));
				}
			} // End of FOR -> onStart Class

		} // End of onStart

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			// mysqlDisconnect(conn);
			// annDisconnect();
			// mysqlDisconnect(conn);
			return super.onEnd();
		} // Ends ucsclAgent
	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlLocalSIFTquery extends OneShotBehaviour {

		private int snpid;
		private boolean errflag;

		public mysqlLocalSIFTquery(int l) {
			snpid = l;
			this.errflag = false;

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			QuerySIFT(snpid);
		}

	}

	public void QuerySIFT(int s) {

		String snpid = "rs" + Integer.toString(s);

		String refSeqID = "";
		String subst = "";
		String predict = "";
		String score = "";
		String median = "";

		String strsql = "SELECT snp132.RefSeqId, " + "snp132.Substitution, " + "snp132.Prediction, " + "snp132.Score, "
				+ "snp132.Median " + "FROM snp132 " + "WHERE " + "snp132.rsid = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// GET refSeq ID
				if (refSeqID == "") {
					refSeqID = rs.getString(1);
				} else {
					refSeqID = refSeqID + "," + rs.getString(1);
				}
				// Get Substitution
				if (subst == "") {
					subst = rs.getString(2);
				} else {
					subst = subst + "," + rs.getString(2);
				}
				// Get Prediction
				if (predict == "") {
					predict = rs.getString(3);
				} else {
					predict = predict + "," + rs.getString(3);
				}
				// Get Score
				if (score == "") {
					score = rs.getString(4);
				} else {
					score = score + "," + rs.getString(4);
				}
				// Get Median
				if (median == "") {
					median = rs.getString(5);
				} else {
					median = median + "," + rs.getString(5);
				}

			} // End of rs.next while m

			rs.close();
			pstmt.close();

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			String sql = "insert ignore into sift (rsid,proteinID,subs,pred,score,median,fk_searchid) values ('" + snpid
					+ "','" + refSeqID + "','" + subst + "','" + predict + "','" + score + "','" + median + "',"
					+ this.getAnnsearchid() + ")";

			Statement stm = annconnection.createStatement();

			stm.executeUpdate(sql);

			stm.close();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of QueryUCSC1

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

} // End of SIFTLocalAgent
