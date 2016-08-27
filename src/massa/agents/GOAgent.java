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
import java.util.Iterator;
//import java.util.List;
import java.util.Set;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioData;
import massa.biodata.GeneOntology;

public class GOAgent extends DBagent {

	public String sn = "mysql.ebi.ac.uk:4085";
	public String md = "go_latest";
	// public String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	public String gouser = "go_select";
	public String gokey = "amigo";
	BioData godata;

	public GOAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */
		this.setDBname("go");
		this.setInformation("gene ontology");

		this.godata = new BioData("gene ontology");

	} // End of GOAgent constructor

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		// set a connection
		this.dbConnect(sn, md, gouser, gokey);

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

		public GOAction(AID pa, BioData bd) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessGO(bd), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}

	}

	class accessGO extends ParallelBehaviour {
		BioData contentdata;

		public accessGO(BioData bd) {
			super(WHEN_ALL);
			contentdata = bd;

		}

		public void onStart() {

			// start connection
			// myconnection.mysqlConnect();

			// read hash{snp,gene} and pass on to remoteAccess
			Set<String> keyset;
			keyset = contentdata.snp_gene.keySet();
			Iterator<String> itr = keyset.iterator();

			while (itr.hasNext()) {
				String snp = itr.next();
				String gene = contentdata.snp_gene.get(snp);
				// System.out.println("key:"+snp+"value:"+gene);
				this.addSubBehaviour(new mysqlRemoteGOqueryTwo(snp, gene));
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			dbDisconnect();
			return super.onEnd();
		}

	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class mysqlRemoteGOqueryTwo extends OneShotBehaviour {

		private String snpid;
		private String genesymbol;
		private boolean errflag;
		private GeneOntology goobject;

		public mysqlRemoteGOqueryTwo(String s, String g) {
			this.snpid = s;
			this.genesymbol = g;
			this.errflag = false;
		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " executing request for " + genesymbol + "...");

			goobject = QueryGOremote(genesymbol);

			goobject.setGenesymbol(genesymbol);
			goobject.setPolymorphismCode(snpid);

			synchronized (godata) {
				godata.setGOList(goobject);
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
				msg.setContentObject(godata);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // Gene Ontology Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public GeneOntology QueryGOremote(String gs) {

		// create instance of BioData and GeneOntology objects
		BioData bioobjects = new BioData("gene ontology");
		GeneOntology goobject = bioobjects.createGOInstance();

		String strsql = "Select distinct term_id," + " term_name," + " term_acc, " + "term_type, " + "gp_symbol, "
				+ "pub_dbname, " + "species.ncbi_taxa_id, " + "species.id "
				+ "FROM species, term_J_association_J_evidence_J_gene_product " + "WHERE gp_symbol = ? "
				+ "AND gp_species_id = species.id AND species.ncbi_taxa_id = 9606;";

		try {

			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, gs);
			System.out.println("SQL:" + strsql);

			// Statement stm = (Statement) conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			// conn.ResultSet executeQuery(sql);
			// System.out.println("Successful Query");

			while (rs.next()) {
				Integer termId = rs.getInt("term_id");
				String termName = rs.getString("term_name");
				String termAcc = rs.getString("term_acc");
				String termType = rs.getString("term_type");
				String geneSymbol = rs.getString("gp_symbol");
				String dbName = rs.getString("pub_dbname");

				System.out.println("GO Agent term type is " + termType + " term name is " + termName);
				if (termType.equals("biological_process")) {
					goobject.setBioProcessItem(termName);
				}
				if (termType.equals("cellular_component")) {
					goobject.setCelComponentItem(termName);
				}
				if (termType.equals("molecular_function")) {
					goobject.setMolFunctionItem(termName);
				}

			} // End of rs.next while

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion

		return goobject;

	} // End of QueryGORemote

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

} // End of GOAgent
