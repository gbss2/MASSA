/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2013, by LDGH and Contributors.
#
# /
#/ -----------------
#  GWAScatalogLocalAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues
# Contributor(s):
# Updated by (and date): 09/10/2013
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioDataLite;
import massa.database.MySQLcon;

public class GWAScatalogLocalAgent extends DBagent {

	/* Atributes */
	BioDataLite snpdata;;
	int input_length;
	int[] input;

	// Set host address (localhost)
	public String sn = "localhost";
	// Set database used
	public String md = "gwascatalog";
	// Set User Name
	public String user = "gwas";
	// Set User Password
	public String key = "gwas";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	MySQLcon myconnection;

	public GWAScatalogLocalAgent() {

		this.setDBname("gwas");
		this.setInformation("gwas");

	} // End of constructor

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
				System.out.println("Agent " + getLocalName() + " received a REQUEST message from agent "
						+ msg.getSender().getName());
				try {
					sender = msg.getSender();
					snpdata = (BioDataLite) msg.getContentObject();
					/* Here agent gets the annotation db connection */
					// annconnection = snpdata.getConnection();
					addBehaviour(new GWASAction(snpdata.getSnpIdList(), sender, snpdata.getSearchid()));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class GWASAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public GWASAction(int[] sl, AID pa, int asi) {
			partneragent = pa;
			snpidlist = sl;
			setAnnsearchid(asi);

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessGWAS(snpidlist), STATE_A);
			this.registerLastState(new SendReply(partneragent), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}
	}

	class accessGWAS extends ParallelBehaviour {
		private int[] snpidlist; // Declare Array of SNPs

		public accessGWAS(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;
		}

		public void onStart() {

			// Initialize a behaviour for each snp
			for (int i = 0; i < snpidlist.length; i++) {
				if (snpidlist[i] > 0) {
					this.addSubBehaviour(new mysqlLocalGWASquery(snpidlist[i]));
				}
			} // End of FOR -> onStart Class

		} // End of onStart

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			// mysqlDisconnect(conn);
			return super.onEnd();
		} // Ends ucsclAgent
	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlLocalGWASquery extends OneShotBehaviour {

		private int snpid;
		private boolean errflag;

		public mysqlLocalGWASquery(int l) {
			snpid = l;
			this.errflag = false;

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+snpid+"...");

			QueryGWAS(snpid);
		}

	}

	public void QueryGWAS(int s) {

		String snpid = "rs" + Integer.toString(s);

		List<String> pubmedid = new ArrayList<String>();
		List<String> reportedgenes = new ArrayList<String>();
		List<String> riskallele = new ArrayList<String>();
		List<String> context = new ArrayList<String>();
		List<String> pvalue = new ArrayList<String>();
		List<String> disease_trait = new ArrayList<String>();
		List<String> initial_sample_size = new ArrayList<String>();
		String pid = "null";
		String rg = "null";
		String ra = "null";
		String ct = "null";
		String pv = "null";
		String dt = "null";
		String ss = "null";

		String strsql = "SELECT pubmedid, " + "reported_genes, " + "disease_trait, " + "initial_sample_size, "
				+ "strongest_snp_risk_allele, " + "context, " + "pvalue " + "FROM gwas " + "WHERE " + "snps = ? ;";

		// System.out.println(strsql);

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				if (!pubmedid.contains(rs.getString("pubmedid"))) {
					pubmedid.add(rs.getString("pubmedid"));
				}
				if (!reportedgenes.contains(rs.getString("reported_genes"))) {
					reportedgenes.add(rs.getString("reported_genes"));
				}
				if (!riskallele.contains(rs.getString("strongest_snp_risk_allele"))) {
					riskallele.add(rs.getString("strongest_snp_risk_allele"));
				}
				if (!context.contains(rs.getString("context"))) {
					context.add(rs.getString("context"));
				}
				if (!pvalue.contains(rs.getString("pvalue"))) {
					pvalue.add(rs.getString("pvalue"));
				}
				if (!disease_trait.contains(rs.getString("disease_trait"))) {
					disease_trait.add(rs.getString("disease_trait"));
				}
				if (!initial_sample_size.contains(rs.getString("initial_sample_size"))) {
					initial_sample_size.add(rs.getString("initial_sample_size"));
				}

			} // End of rs.next while
			rs.close();
			pstmt.close();

			// create strings with unique values
			Iterator<String> it;
			it = pubmedid.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!pid.equals("null")) {
					pid = pid + item + ";";
				} else {
					pid = item + ";";
				}
			}
			it = reportedgenes.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!rg.equals("null")) {
					rg = rg + item + ";";
				} else {
					rg = item + ";";
				}

			}
			it = riskallele.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!ra.equals("null")) {
					ra = ra + item + ";";
				} else {
					ra = item + ";";
				}

			}
			it = context.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!ct.equals("null")) {
					ct = ct + item + ";";
				} else {
					ct = item + ";";
				}

			}
			it = pvalue.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!pv.equals("null")) {
					pv = pv + item + ";";
				} else {
					pv = item + ";";
				}

			}
			it = disease_trait.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!dt.equals("null")) {
					dt = dt + item + ";";
				} else {
					dt = item + ";";
				}

			}
			it = initial_sample_size.iterator();
			while (it.hasNext()) {
				String item = (String) it.next();
				if (!ss.equals("null")) {
					ss = ss + item + ";";
				} else {
					ss = item + ";";
				}

			}
			/*
			 * System.out.println("--->"+pid); System.out.println("--->"+rg);
			 * System.out.println("--->"+ra); System.out.println("--->"+ct);
			 * System.out.println("--->"+pv); System.out.println("--->"+dt);
			 * System.out.println("--->"+ss);
			 */
			// insert result into annotationdb
			String sql = "insert ignore into gwascatalog (snps,pubmedid,disease_trait,initial_sample_size,reported_genes,strongest_snp_risk_allele,context,pvalue,fk_searchid) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setString(1, snpid);
			pstmt2.setString(2, pid);
			pstmt2.setString(3, dt);
			pstmt2.setString(4, ss);
			pstmt2.setString(5, rg);
			pstmt2.setString(6, ra);
			pstmt2.setString(7, ct);
			pstmt2.setString(8, pv);
			pstmt2.setInt(9, this.getAnnsearchid());

			pstmt2.executeUpdate();
			pstmt2.close();

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

} // End of UCSCLocalAgent
