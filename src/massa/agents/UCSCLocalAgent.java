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

public class UCSCLocalAgent extends DBagent {

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
	public String md = "hg19";
	// Set User Name
	public String user = "ucsc";
	// Set User Password
	public String key = "ucschg19";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	MySQLcon myconnection;

	public UCSCLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("ucsc");
		this.setInformation("ucsc");

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
					addBehaviour(new UCSCAction(snpdata.getSnpIdList(), sender, snpdata.getSearchid()));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class UCSCAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public UCSCAction(int[] sl, AID pa, int asi) {
			partneragent = pa;
			snpidlist = sl;
			setAnnsearchid(asi);

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessUCSC(snpidlist), STATE_A);
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

	class accessUCSC extends ParallelBehaviour {
		private int[] snpidlist; // Declare Array of SNPs

		public accessUCSC(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;
		}

		public void onStart() {

			// Initialize a behaviour for each snp
			for (int i = 0; i < snpidlist.length; i++) {
				if (snpidlist[i] > 0) {
					this.addSubBehaviour(new mysqlLocalUCSCquery(snpidlist[i]));
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
	class mysqlLocalUCSCquery extends OneShotBehaviour {

		private int snpid;
		private boolean errflag;

		public mysqlLocalUCSCquery(int l) {
			snpid = l;
			this.errflag = false;

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");

			QueryUCSC1(snpid);
		}

	}

	public void QueryUCSC1(int s) {

		String snpid = "rs" + Integer.toString(s);

		String strand = "null";
		String refUCSC = "null";
		String obsGen = "null";
		String ucscClass = "null";
		String ucscFunc = "null";

		// String A = "SELECT snp135.strand, snp135.refUCSC, snp135.observed,
		// snp135.class, snp135.func, snp135CodingDbSnp.codons,
		// snp135CodingDbSnp.peptides, phastCons46wayOneStep.phastScore FROM
		// snp135 LEFT JOIN snp135CodingDbSnp ON snp135.name =
		// snp135CodingDbSnp.name LEFT JOIN phastCons46wayOneStep ON
		// snp135.chrom = phastCons46wayOneStep.chr AND snp135.chromStart =
		// phastCons46wayOneStep.position WHERE snp135.name = 'rs71646557'";

		String strsql = "SELECT snp135.strand, " + "snp135.refUCSC, " + "snp135.observed, " + "snp135.class, "
				+ "snp135.func, " + "snp135CodingDbSnp.codons, " + "snp135CodingDbSnp.peptides " + "FROM snp135 "
				+ "LEFT JOIN snp135CodingDbSnp ON snp135.name = snp135CodingDbSnp.name " + "WHERE "
				+ "snp135.name = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				strand = rs.getString("strand");
				// System.out.println(snpid+" strand ="+strand);
				refUCSC = rs.getString("refUCSC");
				// System.out.println(snpid+" refUCSC ="+refUCSC);
				obsGen = rs.getString("observed");
				// System.out.println(snpid+" obsGen ="+obsGen);
				ucscClass = rs.getString("class");
				// System.out.println(snpid+" ucscClass ="+ucscClass);
				ucscFunc = rs.getString("func");
				// System.out.println(snpid+" ucscFunc ="+ucscFunc);

			} // End of rs.next while m

			rs.close();
			pstmt.close();

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			String sql = "insert ignore into ucsc (polID,strand,refUCSC,observed,class,func,fk_searchid) values ('"
					+ snpid + "','" + strand + "','" + refUCSC + "','" + obsGen + "','" + ucscClass + "','" + ucscFunc
					+ "'," + this.getAnnsearchid() + ")";

			Statement stm = annconnection.createStatement();

			stm.executeUpdate(sql);

			stm.close();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of QueryUCSC1

	public void QueryUCSCPathway(String g) {

		String cgapID = "";
		String path = "";

		String strsql = "SELECT cgapAlias.cgapID, " + "cgapBiocDesc.description " + "FROM cgapAlias "
				+ "LEFT JOIN cgapBiocPathway ON cgapAlias.cgapID = cgapBiocPathway.cgapID "
				+ "LEFT JOIN cgapBiocDesc ON cgapBiocPathway.mapID = cgapBiocDesc.mapID " + "WHERE "
				+ "cgapAlias.alias = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, g);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				// cgapID = rs.getString(1); // mapID
				path = rs.getString(2); // description

				// GET refSeq ID
				if (cgapID == "") {
					cgapID = rs.getString(1);
				} else {
					cgapID = cgapID + ";" + rs.getString(1);
				}
				// Get Substitution
				if (path == "") {
					path = rs.getString(2);
				} else {
					path = path + ";" + rs.getString(2);
				}

			} // End of rs.next while m

			rs.close();
			pstmt.close();

			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			// insert result into annotationdb
			String sql = "insert ignore into ucscGene (fk_searchid,cgapID,cgapPath) values (?,?,?)";

			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setInt(1, this.getAnnsearchid());
			pstmt2.setString(2, cgapID);
			pstmt2.setString(3, path);

			Statement stm = annconnection.createStatement();

			stm.executeUpdate(sql);

			stm.close();
			// connection.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of QueryUCSCPathway

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

} // End of UCSCLocalAgent
