/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# ProveanLocalAgent.java
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

public class ProveanLocalAgent extends DBagent {

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
	public String md = "provean";
	// Set User Name
	public String user = "provean";
	// Set User Password
	public String key = "provean137";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	MySQLcon myconnection;

	public ProveanLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("Provean");
		this.setInformation("Provean");

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
					addBehaviour(new ProveanAction(snpdata.getSnpIdList(), sender, snpdata.getSearchid()));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class ProveanAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public ProveanAction(int[] sl, AID pa, int asi) {
			partneragent = pa;
			snpidlist = sl;
			setAnnsearchid(asi);

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessProvean(snpidlist), STATE_A);
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

	class accessProvean extends ParallelBehaviour {
		private int[] snpidlist; // Declare Array of SNPs

		public accessProvean(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;
		}

		public void onStart() {

			// Initialize a behaviour for each snp
			for (int i = 0; i < snpidlist.length; i++) {
				if (snpidlist[i] > 0) {
					this.addSubBehaviour(new mysqlLocalProveanquery(snpidlist[i]));
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
	class mysqlLocalProveanquery extends OneShotBehaviour {

		private int snpid;
		private boolean errflag;

		public mysqlLocalProveanquery(int l) {
			snpid = l;
			this.errflag = false;

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			QueryProvean(snpid);
		}

	}

	public void QueryProvean(int s) {

		String snpid = Integer.toString(s);

		String refSeqID = "";
		String subst = "";
		String predict = "";
		String score = "";
		String posProt = "";
		String siftPredict = "";
		String siftScore = "";

		String strsql = "SELECT snp137.proteinID, " + "snp137.CodonChange, " + "snp137.posProt, "
				+ "snp137.proveanScore, " + "snp137.proveanPredict, " + "snp137.siftScore, " + "snp137.siftPredict "
				+ "FROM snp137 " + "WHERE " + "snp137.rsid = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// GET refSeq ID
				if (refSeqID == "") {
					refSeqID = rs.getString(1);
				} else {
					refSeqID = refSeqID + ";" + rs.getString(1);
				}
				// Get Substitution
				if (subst == "") {
					subst = rs.getString(2);
				} else {
					subst = subst + ";" + rs.getString(2);
				}
				// Get Pos Prot
				if (posProt == "") {
					posProt = rs.getString(3);
				} else {
					posProt = posProt + ";" + rs.getString(3);
				}
				// Get Prediction
				if (predict == "") {
					predict = rs.getString(5);
				} else {
					predict = predict + ";" + rs.getString(5);
				}
				// Get Score
				if (score == "") {
					score = rs.getString(4);
				} else {
					score = score + ";" + rs.getString(4);
				}
				// Get SIFTPrediction
				if (siftPredict == "") {
					siftPredict = rs.getString(7);
				} else {
					siftPredict = siftPredict + ";" + rs.getString(7);
				}
				// Get SIFTScore
				if (siftScore == "") {
					siftScore = rs.getString(6);
				} else {
					siftScore = siftScore + ";" + rs.getString(6);
				}

				// System.out.println(snpid+" 1 refSeq ="+rs.getString(1));
				// System.out.println(snpid+" 2 refSeq ="+refSeqID);

			} // End of rs.next while m

			snpid = "rs" + Integer.toString(s);
			if (refSeqID.length() > 0) {
				// refSeqID = refSeqID.substring(1);
			} else {
				refSeqID = "null";
			}
			if (subst.length() > 0) {
				// subst = subst.substring(1);
			} else {
				subst = "null";
			}
			if (posProt.length() > 0) {
				// posProt = posProt.substring(1);
			} else {
				posProt = "null";
			}
			if (predict.length() > 0) {
				// predict = predict.substring(1);
			} else {
				predict = "null";
			}
			if (score.length() > 0) {
				// score = score.substring(1);
			} else {
				score = "null";
			}
			if (siftPredict.length() > 0) {
				// siftPredict = siftPredict.substring(1);
			} else {
				siftPredict = "null";
			}
			if (siftScore.length() > 0) {
				// siftScore = siftScore.substring(1);
			} else {
				siftScore = "null";
			}

			rs.close();
			pstmt.close();

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			String sql = "insert ignore into provean (rsid,proteinID,subs,median,pred,score,siftScore,siftPredict,fk_searchid) values ('"
					+ snpid + "','" + refSeqID + "','" + subst + "','" + posProt + "','" + predict + "','" + score
					+ "','" + siftScore + "','" + siftPredict + "'," + this.getAnnsearchid() + ")";

			Statement stm = annconnection.createStatement();

			stm.executeUpdate(sql);

			stm.close();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of QueryPROVEAN

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

} // End of ProveanLocalAgent
