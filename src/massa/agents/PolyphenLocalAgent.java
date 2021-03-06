/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# UCSCLocalAgent.java
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

public class PolyphenLocalAgent extends DBagent {

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
	public String md = "polyphen";
	// Set User Name
	public String user = "polyphen";
	// Set User Password
	public String key = "polyphenhg19";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	MySQLcon myconnection;

	public PolyphenLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("polyphen");
		this.setInformation("polyphen");

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
					addBehaviour(new PolyphenAction(snpdata.getSnpIdList(), sender, snpdata.getSearchid()));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class PolyphenAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public PolyphenAction(int[] sl, AID pa, int asi) {
			partneragent = pa;
			snpidlist = sl;
			setAnnsearchid(asi);

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessPolyphen(snpidlist), STATE_A);
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

	class accessPolyphen extends ParallelBehaviour {
		private int[] snpidlist; // Declare Array of SNPs

		public accessPolyphen(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;
		}

		public void onStart() {

			// Initialize a behaviour for each snp
			for (int i = 0; i < snpidlist.length; i++) {
				if (snpidlist[i] > 0) {
					this.addSubBehaviour(new mysqlLocalPolyphenquery(snpidlist[i]));
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
	class mysqlLocalPolyphenquery extends OneShotBehaviour {

		private int snpid;
		private boolean errflag;

		public mysqlLocalPolyphenquery(int l) {
			snpid = l;
			this.errflag = false;

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			QueryPolyphen(snpid);
		}

	}

	public void QueryPolyphen(int s) {

		String snpid = "rs" + Integer.toString(s);

		String uniProtId = "";
		String subst = "";
		String posProt = "";
		String predict2 = "";
		String prob2 = "";
		String fdr = "";
		String predict1 = "";
		String sep = "\\";

		// String A = "SELECT snp135.strand, snp135.refUCSC, snp135.observed,
		// snp135.class, snp135.func, snp135CodingDbSnp.codons,
		// snp135CodingDbSnp.peptides, phastCons46wayOneStep.phastScore FROM
		// snp135 LEFT JOIN snp135CodingDbSnp ON snp135.name =
		// snp135CodingDbSnp.name LEFT JOIN phastCons46wayOneStep ON
		// snp135.chrom = phastCons46wayOneStep.chr AND snp135.chromStart =
		// phastCons46wayOneStep.position WHERE snp135.name = 'rs71646557'";

		String strsql = "SELECT humvar.acc, " + "CONCAT(nt1,?,nt2) as subs, " + "humvar.pos, " + "humvar.prediction, "
				+ "humvar.pph2_prob, " + "humvar.pph2_FDR, " + "humvar.effect " +
				// "humvar.avHet " +
				"FROM humvar " + "WHERE " + "humvar.rsid = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, sep);
			pstmt.setString(2, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// GET uniProtID ID
				if (uniProtId == "") {
					uniProtId = rs.getString(1);
				} else {
					uniProtId = uniProtId + "," + rs.getString(1);
				}
				// Get Substitution
				if (subst == "") {
					subst = rs.getString(2);
				} else {
					subst = subst + "," + rs.getString(2);
				}
				// Get Pos
				if (posProt == "") {
					posProt = rs.getString(3);
				} else {
					posProt = posProt + "," + rs.getString(3);
				}

				// Get Prediction
				if (predict2 == "") {
					predict2 = rs.getString(4);
				} else {
					predict2 = predict2 + "," + rs.getString(4);
				}
				// Get Score
				if (prob2 == "") {
					prob2 = rs.getString(5);
				} else {
					prob2 = prob2 + "," + rs.getString(5);
				}
				// Get FDR rate
				if (fdr == "") {
					fdr = rs.getString(6);
				} else {
					fdr = fdr + "," + rs.getString(6);
				}
				// Get Prediction Polyphen1
				if (predict1 == "") {
					predict1 = rs.getString(7);
				} else {
					predict1 = predict1 + "," + rs.getString(7);
				}

				// System.out.println(snpid+" P1 refSeq ="+rs.getString(1));
				// System.out.println(snpid+" P2 refSeq ="+uniProtId);

			} // End of rs.next while m

			rs.close();
			pstmt.close();

			if (uniProtId.length() > 0) {
				// refSeqID = refSeqID.substring(1);
			} else {
				uniProtId = "null";
			}
			if (subst.length() > 1) {
				// subst = subst.substring(1);
			} else {
				subst = "null";
			}
			if (posProt.length() > 0) {
				// posProt = posProt.substring(1);
			} else {
				posProt = "null";
			}
			if (predict2.length() > 0) {
				// predict = predict.substring(1);
			} else {
				predict2 = "null";
			}
			if (prob2.length() > 0) {
				// score = score.substring(1);
			} else {
				prob2 = "null";
			}
			if (fdr.length() > 0) {
				// siftPredict = siftPredict.substring(1);
			} else {
				fdr = "null";
			}
			if (predict1.length() > 0) {
				// siftScore = siftScore.substring(1);
			} else {
				predict1 = "null";
			}

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			// String sql = "insert ignore into polyphen
			// (rsid,proteinID,subs,pred,score,median,fk_searchid) values
			// ('"+snpid+"','"+refSeqID+"','"+subst+"','"+predict+"','"+score+"','"+median+"',"+this.getAnnsearchid()
			// +")";
			String sql = "insert ignore into polyphen (rsid,proteinID,subs,posProt,pred,score,median,pph1Pred,fk_searchid) values (?,?,?,?,?,?,?,?,?)";

			// Statement stm = annconnection.createStatement();
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setString(1, snpid);
			pstmt2.setString(2, uniProtId);
			pstmt2.setString(3, posProt);
			pstmt2.setString(4, subst);
			pstmt2.setString(5, predict2);
			pstmt2.setString(6, prob2);
			pstmt2.setString(7, fdr);
			pstmt2.setString(8, predict1);
			pstmt2.setInt(9, this.getAnnsearchid());

			pstmt2.executeUpdate();

			pstmt2.close();
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

} // End of PolyphenLocalAgent
