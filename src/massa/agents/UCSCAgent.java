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

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioData;
import massa.biodata.UCSC;

public class UCSCAgent extends DBagent {

	public String sn = "genome-mysql.cse.ucsc.edu";
	public String md = "hg19";
	// public String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	public String ucscUser = "genome";
	public String ucscKey = "";
	BioData ucscdata;

	public UCSCAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("ucsc");
		this.setInformation("ucsc");
		this.dbConnect(sn, md, ucscUser, ucscKey);
		this.ucscdata = new BioData("ucsc");

	} // End of ucscAgent constructor

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

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
				sender = msg.getSender();
				System.out.println("Agent " + getLocalName() + " received a REQUEST message from agent "
						+ msg.getSender().getName());
				try {
					BioData contentdata = (BioData) msg.getContentObject();
					addBehaviour(new UCSCAction(sender, contentdata.getSnpRsList()));
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

		public UCSCAction(AID pa, String[] rslist) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessUCSC(rslist), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}
	}

	class accessUCSC extends ParallelBehaviour {
		private String[] snprslist;

		public accessUCSC(String[] rsl) {
			super(WHEN_ALL);
			snprslist = rsl;
		}

		public void onStart() {

			// start connection
			// myconnection.mysqlConnect();

			for (int i = 0; i < snprslist.length; i++) {
				this.addSubBehaviour(new mysqlRemoteUCSCqueryThree(snprslist[i]));
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			dbDisconnect();
			return super.onEnd();
		}
	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlRemoteUCSCqueryThree extends OneShotBehaviour {

		private String snpid;
		private boolean errflag;
		private UCSC ucscobject;

		public mysqlRemoteUCSCqueryThree(String s) {
			snpid = s;
			this.errflag = false;
		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+snpid+"...");

			ucscobject = QueryUCSC1(snpid);
			ucscobject.setPolymorphismCode(snpid.substring(2));

			synchronized (ucscdata) {
				ucscdata.setUCSCList(ucscobject);
			}

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
				msg.setContentObject(ucscdata);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // UCSC Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public UCSC QueryUCSC1(String snpid) {

		// create instance of BioData and UCSC objects
		BioData bioobjects = new BioData("ucsc");
		UCSC ucscobject = bioobjects.createUCSCInstance();

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

				String strand = rs.getString("strand");
				// System.out.println(snpid+" strand ="+strand);
				ucscobject.setStrand(strand);
				String refUCSC = rs.getString("refUCSC");
				// System.out.println(snpid+" refUCSC ="+refUCSC);
				ucscobject.setRefUCSC(refUCSC);
				String obsGen = rs.getString("observed");
				// System.out.println(snpid+" obsGen ="+obsGen);
				ucscobject.setObsGen(obsGen);
				String ucscClass = rs.getString("class");
				// System.out.println(snpid+" ucscClass ="+ucscClass);
				ucscobject.setUcscClass(ucscClass);
				String ucscFunc = rs.getString("func");
				// System.out.println(snpid+" ucscFunc ="+ucscFunc);
				ucscobject.setUcscFunc(ucscFunc);

			} // End of rs.next while

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion

		return ucscobject;
	} // End of QueryUCSC1

} // End of UCSCAgent
