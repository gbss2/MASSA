/*#==========================================

# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# HGNCLocalAgent.java
#  -----------------
#
# Original Author: 
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
import java.sql.Statement;
//import java.util.List;
//
//import diverenrich.BioData.Gene;
//import diverenrich.BioData.OMIM;
//import diverenrich.omimLocalAgent.SendReply;
//import diverenrich.omimLocalAgent.accessOMIM;
//import diverenrich.omimLocalAgent.mysqlRemoteOMIMquery;
//import diverenrich.omimLocalAgent.omimAction;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioDataLite;

public class HGNCLocalAgent extends DBagent {

	/* Atributes */
	BioDataLite hugodata;
	int input_length; // * o tamanho sempre sera igual ao numero de
						// testes. sera passado futuramente, junto com a
						// lista de entradas.

	int[] input; // ** futuramente a lista sera passado como
					// mensagem recebida do agente coordenador

	// Set host address (localhost)
	public String sn = "localhost";
	// Set database used
	public String md = "HGNC";
	// Set User Name
	public String user = "hugo";
	// Set User Password
	public String key = "hugodb";
	// Create an object myconnection of class MySQLcon to create connection with
	// database
	// MySQLcon myconnection;
	// PreparedStatement pstmt2 = null;
	// Connection connection = null;

	public HGNCLocalAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("hugo");
		this.setInformation("hugo");
		this.hugodata = new BioDataLite();

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
					hugodata.setSearchid(getAnnsearchid());
					/* Here agent gets the annotation db connection */
					// annconnection = hugodata.getConnection();
					addBehaviour(new HUGOAction(sender, contentdata));
					// snpdata = (BioDataLite)msg.getContentObject();
					// genedata = (BioDataLite)msg.getContentObject();
					// addBehaviour(new
					// HUGOAction(snpdata.getSnpIdList(),sender,snpdata.getSearchid()));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class HUGOAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public HUGOAction(AID pa, BioDataLite bdlite) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessHUGO(bdlite), STATE_A);
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

	class accessHUGO extends ParallelBehaviour {
		BioDataLite contentdata;

		private int[] snpidlist; // Declare Array of SNPs

		public accessHUGO(BioDataLite bdl) {
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
				// Statement st = annconnection.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					this.addSubBehaviour(new mysqlLocalHUGOquery(rs.getString("gene_symbol")));

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
	class mysqlLocalHUGOquery extends OneShotBehaviour {

		// private Gene genetosearch;
		private String genesymbol;
		private boolean errflag;
		// private Gene snpid; // Declare an object to receive snp ID (rs#)

		public mysqlLocalHUGOquery(String gs) {

			// public mysqlRemoteUCSCquery(Gene x) {
			// genetosearch = x;
			genesymbol = gs;
			// snpid = g;
		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" executing request
			// for "+genesymbol+"...");
			QueryHGNCLocal(genesymbol);
			// myconnection.QueryHGNCLocal(snpid);
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
	 * /////////////////////////////// // // HGNC Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public void QueryHGNCLocal(String genesymbol) {

		// create instance of BioData and OMIM objects
		// bioobjects = new BioData("hugo");
		// hugoobject = bioobjects.createHUGOInstance();
		String hugoId = null;
		String geneSymbol = null;
		String geneName = null;
		// String prevGeneNames = null;
		String altGeneSymbols = null;
		String geneSyns = null;

		String geneTag = null;
		String geneFamily = null;
		String geneType = null;
		String geneGroup = null;

		// String link = null;
		//
		// String accNumbers = null;
		// String enzymeId = null;
		// String ensemblId = null;
		// String refSeqId = null;
		// String ccdsId = null;
		// String vegaId = null;
		// String entrezGeneId = null;
		// String omimId = null;
		// String uniProtGeneId = null;
		// String ucscGeneId = null;
		//
		// String mouseGdbId = null;
		// String ratGdbId = null;
		//
		String pmid = null;

		String strsql = "select hugoDB.hgncId, " + "hugoDB.approvedSymbol, " + "hugoDB.approvedName, "
				+ "hugoDB.locusType, " + "hugoDB.locusGroup, " +
				// "hugoDB.previousSymbol, " +
				"hugoDB.synonyms, " + "hugoDB.nameSynomys, " + "hugoDB.geneFamilyTag, " + "hugoDB.geneFamilyDesc, " +

				// "hugoDB.specialistDBLink, " +
				// "hugoDB.accessionNumbers, " +
				// "hugoDB.enzymeId, " +
				// "hugoDB.entrezId, " +
				// "hugoDB.ensemblId, " +
				// "hugoDB.pubmedIds, " +
				// "hugoDB.refSeqIds, " +
				// "hugoDB.CCDSIds, " +
				// "hugoDB.VEGAIds, " +
				// "hugoDB.entrezGeneId, " +
				// "hugoDB.omimId, " +
				// "hugoDB.refSeq, " +
				// "hugoDB.uniProtId, " +
				// "hugoDB.ucscId, " +
				// "hugoDB.mouseGdbId, " +
				// "hugoDB.ratGdbId, " +
				"hugoDB.pubmedIds " +

				"FROM hugoDB " + "WHERE approvedSymbol = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);
			// System.out.println("SQL: "+pstmt);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				hugoId = rs.getString("hgncId");
				geneSymbol = rs.getString("approvedSymbol");
				geneName = rs.getString("approvedName");
				// prevGeneNames = rs.getString("previousSymbol");
				altGeneSymbols = rs.getString("synonyms");
				geneSyns = rs.getString("nameSynomys");

				geneTag = rs.getString("geneFamilyTag");
				geneFamily = rs.getString("geneFamilyDesc");
				geneType = rs.getString("locusType");
				geneGroup = rs.getString("locusGroup");

				// link = rs.getString("specialistDBLink");
				//
				// accNumbers = rs.getString("accessionNumbers");
				// enzymeId = rs.getString("enzymeId");
				// ensemblId = rs.getString("ensemblId");
				// refSeqId = rs.getString("refSeqIds");
				// ccdsId = rs.getString("CCDSIds");
				// vegaId = rs.getString("VEGAIds");
				// entrezGeneId = rs.getString("entrezGeneId");
				// omimId = rs.getString("omimId");
				// uniProtGeneId = rs.getString("uniProtId");
				// ucscGeneId = rs.getString("ucscId");
				//
				// mouseGdbId = rs.getString("mouseGdbId");
				// ratGdbId = rs.getString("ratGdbId");

				pmid = rs.getString("pubmedIds");

			} // End of rs.next while

			// conn.close();

			// insert result into annotationdb
			// String sql = "insert ignore into hugoDB
			// (fk_searchid,hgncId,approvedSymbol,approvedName,previousSymbol,synonyms,nameSynomys,"
			// +
			// "geneFamilyTag,geneFamilyDesc,locusType,locusGroup,specialistDBLink,accessionNumbers,enzymeId,ensemblId,refSeqIds,"
			// +
			// "CCDSIds,VEGAIds,entrezGeneId,omimId,uniProtId,ucscId,mouseGdbId,ratGdbId,pubmedIds)
			// " +
			// "values
			// ('"+this.getAnnsearchid()+"','"+hugoId+"','"+geneSymbol+"','"+geneName+"','"+prevGeneNames+"','"+altGeneSymbols+"','"+geneSyns+"',"
			// +
			// "'"+geneTag+"','"+geneFamily+"','"+geneType+"','"+geneGroup+"','"+link+"','"+accNumbers+"','"+enzymeId+"','"+ensemblId+"',"
			// +
			// "'"+refSeqId+"','"+ccdsId+"','"+vegaId+"','"+entrezGeneId+"','"+omimId+"','"+uniProtGeneId+"','"+ucscGeneId+"','"+mouseGdbId+"',"
			// +
			// "'"+ratGdbId+"','"+pmid+"')";

			// String sql = "insert ignore into hugoDB
			// (fk_searchid,hgncId,approvedSymbol,approvedName,previousSymbol,synonyms,nameSynomys,"
			// +
			// "geneFamilyTag,geneFamilyDesc,locusType,locusGroup,specialistDBLink,accessionNumbers,enzymeId,ensemblId,refSeqIds,"
			// +
			// "CCDSIds,VEGAIds,entrezGeneId,omimId,uniProtId,ucscId,mouseGdbId,ratGdbId,pubmedIds)
			// " +
			// "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			// PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			// pstmt2.setInt(1,this.getAnnsearchid());
			// pstmt2.setString(1,hugoId);
			// pstmt2.setString(2,geneSymbol);
			// pstmt2.setString(3,geneName);
			// pstmt2.setString(4,prevGeneNames);
			// pstmt2.setString(5,altGeneSymbols);
			// pstmt2.setString(6,geneSyns);
			// pstmt2.setString(7,geneTag);
			// pstmt2.setString(8,geneFamily);
			// pstmt2.setString(9,geneType);
			// pstmt2.setString(10,geneGroup);
			// pstmt2.setString(11,link);
			// pstmt2.setString(12,accNumbers);
			// pstmt2.setString(13,enzymeId);
			// pstmt2.setString(14,ensemblId);
			// pstmt2.setString(15,refSeqId);
			// pstmt2.setString(16,ccdsId);
			// pstmt2.setString(17,vegaId);
			// pstmt2.setString(18,entrezGeneId);
			// pstmt2.setString(19,omimId);
			// pstmt2.setString(20,uniProtGeneId);
			// pstmt2.setString(21,ucscGeneId);
			// pstmt2.setString(22,mouseGdbId);
			// pstmt2.setString(23,ratGdbId);
			// pstmt2.setString(24,pmid);
			//
			// pstmt2.executeQuery();

			// Connection connection = PooledMysqlConn.getConnection();
			// Connection connection =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");

			String sql = "insert ignore into hugoDB (fk_searchid,hgncId,approvedSymbol,approvedName,synonyms,nameSynomys,"
					+ "geneFamilyTag,geneFamilyDesc,locusType,locusGroup) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt2 = annconnection.prepareStatement(sql);
			// pstmt2 = annconnection.prepareStatement(sql);
			pstmt2.setInt(1, this.getAnnsearchid());
			pstmt2.setString(2, hugoId);
			pstmt2.setString(3, geneSymbol);
			pstmt2.setString(4, geneName);
			// pstmt2.setString(5,prevGeneNames);
			pstmt2.setString(5, altGeneSymbols);
			pstmt2.setString(6, geneSyns);
			pstmt2.setString(7, geneTag);
			pstmt2.setString(8, geneFamily);
			pstmt2.setString(9, geneType);
			pstmt2.setString(10, geneGroup);
			// pstmt2.setString(11,link);
			// pstmt2.setString(12,accNumbers);
			// pstmt2.setString(13,enzymeId);
			// pstmt2.setString(14,ensemblId);
			// pstmt2.setString(15,refSeqId);
			// pstmt2.setString(16,ccdsId);
			// pstmt2.setString(17,vegaId);
			// pstmt2.setString(18,entrezGeneId);
			// pstmt2.setString(19,omimId);
			// pstmt2.setString(20,uniProtGeneId);
			// pstmt2.setString(21,ucscGeneId);
			// pstmt2.setString(22,mouseGdbId);
			// pstmt2.setString(23,ratGdbId);
			// pstmt2.setString(24,pmid);

			pstmt2.executeUpdate();
			pstmt2.close();
			// connection.close();

			// String sql = "insert ignore into hugoDB
			// (fk_searchid,approvedSymbol," +
			// "geneFamilyTag,locusType,locusGroup) " +
			// "values ('"+this.getAnnsearchid()+"','"+geneSymbol+"', "+
			// "'"+geneTag+"','"+geneType+"','"+geneGroup+"')";
			// System.out.println("SQL:"+sql);
			// Statement stm = annconnection.createStatement();
			// stm.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query HGNC Local 1

} // END OF HGNC LOCAL AGENT
