package massa.agents;

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
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioDataLite;

@SuppressWarnings("serial")
public class DBsnpLocalAgent extends DBagent {

	/* Atributes */
	BioDataLite snpdata;
	int input_length; // * o tamanho sempre sera igual ao numero de
						// testes. sera passado futuramente, junto com a
						// lista de entradas.

	int[] input; // ** futuramente a lista sera passado como
					// mensagem recebida do agente coordenador

	// Set host address (localhost)
	public String sn = "localhost";
	// Set database used
	public String md = "dbsnp_137";
	// Set User Name
	public String user = "dbsnp";
	// Set User Password
	public String key = "dbsnp137";

	// Create an object myconnection of class MySQLcon to create connection with
	// database
	// MySQLcon myconnection;

	/* Constructor */
	public DBsnpLocalAgent() {
		super();
		this.setDBname("dbsnp");
		this.setInformation("snp");

	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBsnpAgent must register */
		this.register();

		/* Here DBsnpAgent must connect to annotation db */
		// this.annConnect();

		/* Here DBagent must connect with its local database */
		this.dbConnect(sn, md, user, key);

		// Behaviour execution
		// addBehaviour(new accessDBsnpEutils(input));
		addBehaviour(new waitRequest());
	}

	protected void takeDown() {
		System.out.println("Agent" + getLocalName() + " shutdown.");
		mysqlDisconnect(conn);
		// annDisconnect();
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
					/* Here DBsnpAgent gets the annotation db connection */
					// setAnnConnection(snpdata.getConnection());
					addBehaviour(new dbSNPAction(snpdata.getSnpIdList(), sender, snpdata.getSearchid()));

				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class dbSNPAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public dbSNPAction(int[] sl, AID pa, int asi) {
			partneragent = pa;
			snpidlist = sl;
			setAnnsearchid(asi);

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessDBsnpLocal(snpidlist), STATE_A);
			this.registerLastState(new SendReply(partneragent), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			// mysqlDisconnect(conn);
			// annDisconnect();
			return super.onEnd();
		}

	}

	// Class accessDBsnpLocal implements a parallel behaviour to simultaneous
	// queries on database
	class accessDBsnpLocal extends ParallelBehaviour {

		private int[] snpidlist; // Declare Array of SNPs

		// Constructor of accessDBsnpLocal
		public accessDBsnpLocal(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;

		} // End of accessDBsnpLocal Constructor

		// Starts DBsnpLocalAgent
		public void onStart() {

			// System.out.println("Threads
			// ativas:"+Thread.getAllStackTraces().size());

			// Initialize a behaviour for each snp
			for (int i = 0; i < snpidlist.length; i++) {
				if (snpidlist[i] > 0) {
					this.addSubBehaviour(new remoteAccessDBsnpLocal(snpidlist[i]));
				}
			} // End of FOR -> onStart Class
		} // End of onStart

		// Ends DBsnpLocalAgent
		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			// mysqlDisconnect(conn);
			// annDisconnect();
			return super.onEnd();

		} // End of onEnd

	} // End of accessDBsnpLocal class

	// Class remoteAccessDBsnpLocal implements local database search using MySQL
	// queries
	class remoteAccessDBsnpLocal extends OneShotBehaviour {

		private int snpid; // Declare an object to receive snp ID (rs#)
		// private Polymorphism snpresult; // Declare an object of type
		// Polymorphism
		// private Gene snptogeneresult; // Declare an object of type Gene

		public remoteAccessDBsnpLocal(int l) {

			snpid = l;

			// this.snpresult = snpdata.createPolymorphismInstance();
			// this.snptogeneresult = snpdata.createGeneInstance();
		}

		public void action() {

			// System.out.println("Agent" + getLocalName() + " executing request
			// for ..." );
			// System.out.println("Threads
			// ativas:"+Thread.getAllStackTraces().size());

			clearPolTable();
			String mrnaaccid = QueryDBsnpLocal11(snpid);
			QueryDBsnpLocal12(snpid);
			QueryDBsnpLocal13(snpid);
			QueryDBsnpLocal14(snpid);
			QueryDBsnpLocal15(snpid, mrnaaccid);

			/* add search to annotation database */
			// String sql = "insert into snp
			// (snppk,snpid,kind,subkind,referenceValue,
			// referenceAllele,coordRelGene,chromosome,coordRefSeq,assm_build_version,assm_coord_start,assm_coord_end,value,ancestral_allele,orientation,mrnaAcc,mrnaVer,freq,fk_genepk,fk_searchid)
			// values (1null,";

			// System.out.println("RS ID:"+snpid);

			int key = 1;
			String sql = "insert into snp " +
			// "(snppk," + // 1
					"(snpid," + // 2
					"kind," + // 3
					"subkind," + // 4
					"referenceValue," + // 5
					"referenceAllele," + // 6
					"coordRelGene," + // 7
					"chromosome," + // 8
					"coordRefSeq," + // 9
					"assm_build_version," + // 10
					"assm_coord_start," + // 11
					"assm_coord_end," + // 12
					"value," + // 13
					"ancestral_allele," + // 14
					"orientation," + // 15
					"mrnaAcc," + // 16
					"mrnaVer," + // 17
					"freq," + // 18
					"fk_genepk," + // 19
					"fk_searchid) " + // 20
					"values " + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// 0 = null
			// 1 sql = sql+"'"+polTable[0]+"',";
			// 2 sql = sql+"'"+polTable[1]+"',";
			// 3 sql = sql+"'"+polTable[2]+"',";
			// 4 sql = sql+"'"+polTable[3]+"',";
			// 5 sql = sql+"'"+polTable[20]+"',";
			// 6 sql = sql+"'"+polTable[4]+"',";
			// 7 sql = sql+"'"+polTable[5]+"',";
			// 8 sql = sql+"'"+polTable[6]+"',";
			// 9 sql = sql+"'"+polTable[7]+"',";
			// 10 sql = sql+"'"+polTable[8]+"',";
			// 11 sql = sql+"'"+polTable[9]+"',";
			// 12 sql = sql+"'"+polTable[14]+"',";
			// 13 sql = sql+"'"+polTable[15]+"',";
			// 14 sql = sql+"'"+polTable[16]+"',";
			// 15 sql = sql+"'"+polTable[17]+"',";
			// 16 sql = sql+"'"+polTable[18]+"',";
			// 17 sql = sql+"'"+polTable[19]+"',";

			if (polTable[12] != null) {
				if (!polTable[12].equals("NA") || !polTable[12].equals("null")) {
					// String genesql = "insert into gene
					// (gene_symbol,geneid,fk_searchid) values
					// ('"+polTable[13]+"','"+polTable[12]+"','"+getAnnsearchid()+"')";
					String genesql = "insert ignore into gene (gene_symbol,geneid,fk_searchid) values (?,?,?)";
					try {
						// Statement st = annconnection.createStatement();
						// st.executeUpdate(genesql,
						// Statement.RETURN_GENERATED_KEYS);

						// Connection connection =
						// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");
						// PreparedStatement st =
						// connection.prepareStatement(genesql,Statement.RETURN_GENERATED_KEYS);
						PreparedStatement st = annconnection.prepareStatement(genesql, Statement.RETURN_GENERATED_KEYS);
						st.setString(1, polTable[13]);
						st.setString(2, polTable[12]);
						st.setInt(3, getAnnsearchid());
						st.executeUpdate();

						/* Recover auto-generated search id */
						ResultSet rs = st.getGeneratedKeys();
						if (rs.next()) {
							// 18 sql = sql+ rs.getInt(1) +",";
							// stm.setInt(18,rs.getInt(1));
							key = rs.getInt(1);
						}

						st.close();
						rs.close();
						// connection.close();
					} catch (SQLException e) {
						System.out.println("SQL Exception... Error inserting value in annotation table.");
						e.printStackTrace();
					} // End of Catch Exception
				}
			} else {
				// 18 sql = sql+"1,";
				// stm.setInt(18,1);
				key = 1;
			}
			// 19 sql = sql+getAnnsearchid()+")";

			// System.out.println("SQL insert:"+sql);

			try {

				// Statement stm = annconnection.createStatement();
				// Connection connection =
				// DriverManager.getConnection("jdbc:mysql://localhost:3306/annotation","annotation","1masannotation1");
				// PreparedStatement stm = connection.prepareStatement(sql);
				// System.out.println("Before : " + stm.toString());
				PreparedStatement stm = annconnection.prepareStatement(sql);
				// stm.setNull(1,Types.INTEGER);
				stm.setString(1, Integer.toString(snpid));

				stm.setString(2, polTable[1]);
				stm.setString(3, polTable[2]);
				stm.setString(4, polTable[3]);
				stm.setString(5, polTable[20]);
				stm.setString(6, polTable[4]);
				stm.setString(7, polTable[5]);
				stm.setString(8, polTable[6]);
				stm.setString(9, polTable[7]);
				stm.setString(10, polTable[8]);
				stm.setString(11, polTable[9]);
				stm.setString(12, polTable[14]);
				stm.setString(13, polTable[15]);
				stm.setString(14, polTable[16]);
				stm.setString(15, polTable[17]);
				stm.setString(16, polTable[18]);
				stm.setString(17, polTable[19]);
				stm.setInt(18, key);
				stm.setInt(19, getAnnsearchid());

				// System.out.println("After : " + stm.toString());
				stm.executeUpdate();
				stm.close();
				// connection.close();
			} catch (SQLException e) {
				System.out.println("SQL Exception... Error inserting value in annotation table.");
				e.printStackTrace();
			} // End of Catch Exception

		} // End of action

	} // End of remoteAccessDBsnpLocal

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

		} // END of action
	}// END SENDMSGTOPROVIDER CLASS

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // dbSNP Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	// Querying type, chromosome position, assembly and contig information,
	// orientation, gene information
	public String QueryDBsnpLocal11(int snpid) {

		String mRnaAcc = "";

		String strsql = "SELECT SNP.snp_id, " + "b137_MapLinkHGVS.snp_type, " + "b137_SNPChrPosOnRef.chr, "
				+ "b137_SNPChrPosOnRef.pos, " + "b137_SNPContigLocusId.build_id, " + "b137_SNPContigLocusId.asn_from, "
				+ "b137_SNPContigLocusId.asn_to, " + "b137_MapLinkHGVS.orientation, "
				+ "b137_SNPContigLocusId.locus_id, " + "b137_SNPContigLocusId.locus_symbol, "
				+ "b137_SNPContigLocusId.mrna_acc, " + "b137_SNPContigLocusId.mrna_ver " + "FROM SNP "
				+ "LEFT JOIN b137_MapLinkHGVS ON SNP.snp_id = b137_MapLinkHGVS.snp_id "
				+ "LEFT JOIN b137_SNPContigLocusId ON SNP.snp_id = b137_SNPContigLocusId.snp_id "
				+ "LEFT JOIN b137_SNPChrPosOnRef ON SNP.snp_id = b137_SNPChrPosOnRef.snp_id " + "WHERE "
				+ "SNP.snp_id = ? ;";

		// System.out.println("SQL:"+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, Integer.toString(snpid));
			// Statement stm = (Statement) conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			// conn.ResultSet executeQuery(sql);
			// System.out.println("Successful Query");

			while (rs.next()) {
				// System.out.println(1);
				/* int */ String polCode = rs.getString("snp_id");
				String polType = rs.getString("snp_type");
				// String transcReg = rs.getString("fxn_class");
				// String coordRelGene = rs.getString("pos");
				/* int */ String chr = rs.getString("chr");
				String coordRefSeq = rs.getString("pos");
				// int coordRefseqint = Integer.parseInt(coordRefSeq)+1;
				String assmBuildVer = rs.getString("build_id");
				/* int */ String assmCoordStart = rs.getString("asn_from");
				/* int */ String assmCoordEnd = rs.getString("asn_to");
				/* int */ String geneId = rs.getString("locus_id");
				String geneName = rs.getString("locus_symbol");
				String orient = rs.getString("orientation");
				mRnaAcc = rs.getString("mrna_acc");
				String mRnaVer = rs.getString("mrna_ver");

				polTable[0] = polCode;
				polTable[1] = polType;
				// polTable[2]= transcReg;
				// polTable[4]= coordRelGene;
				polTable[5] = chr;
				if (coordRefSeq == null) {
					polTable[6] = coordRefSeq;
				} else {
					polTable[6] = Integer.toString((Integer.parseInt(coordRefSeq) + 1));
				}
				polTable[7] = assmBuildVer;
				if (assmCoordStart == null) {
					polTable[8] = assmCoordStart;
				} else {
					polTable[8] = assmCoordStart + 1;
				}
				if (assmCoordEnd == null) {
					polTable[9] = assmCoordEnd;
				} else {
					polTable[9] = assmCoordEnd + 1;
				}
				polTable[12] = geneId;
				polTable[13] = geneName;
				polTable[16] = orient;
				// mRNAaccId = "";
				polTable[17] = mRnaAcc;
				polTable[18] = mRnaVer;

				/*
				 * System.out.println("rs: " + polCode + " Pol Type: " +polType+
				 * // " Transcript Region: " + transcReg + " Coord Rel Gene: "+
				 * coordRelGene + " Chr: "+ chr + " Coord Ref Seq: "+
				 * coordRefSeq + " Assembly: "+ assmBuildVer +
				 * " Assembly Coord Start: "+ assmCoordStart +
				 * " Assembly Coord End: "+ assmCoordEnd + " Gene ID: "+ geneId
				 * + " Gene: " + geneName + " Orientation: " + orient +
				 * " Mrna Acc: " + mRnaAcc + " Mrna Ver: " + mRnaVer);
				 */
			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

		return mRnaAcc;

	} // End of Query DB SNP Local 1.1

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Querying Ancestral Alleles
	public void QueryDBsnpLocal12(int snpid) {

		String strsql = "SELECT SNPAncestralAllele.snp_id, " + "Allele.allele " + "FROM Allele "
				+ "LEFT JOIN SNPAncestralAllele ON SNPAncestralAllele.ancestral_allele_id = Allele.allele_id "
				+ "WHERE " + "SNPAncestralAllele.snp_id = ? ;";

		// System.out.println("SQL:"+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, Integer.toString(snpid));
			// Statement stm = (Statement) conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			// conn.ResultSet executeQuery(sql);
			// System.out.println("Successful Query");

			polTable[15] = "";
			while (rs.next()) {
				// System.out.println(1);
				int polCode = rs.getInt("snp_id");
				String ancAllele = rs.getString("allele");

				if (polTable[15] == "") {
					polTable[15] = ancAllele;
				} else {
					polTable[15] = polTable[15] + "," + ancAllele;
				}

				/*
				 * System.out.println("rs: " + polCode + " Ancestral Allele: " +
				 * ancAllele);
				 */
			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion

	} // End of Query DB SNP Local 1.2

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Querying Frequencies
	public void QueryDBsnpLocal13(int snpid) {

		String strsql = "SELECT SNPAlleleFreq.snp_id, " + "SNPAlleleFreq.freq, " + "Allele.allele " + "FROM Allele "
				+ "LEFT JOIN SNPAlleleFreq ON SNPAlleleFreq.allele_id = Allele.allele_id " + "WHERE "
				+ "SNPAlleleFreq.snp_id = ? ;";

		// System.out.println("SQL:"+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, Integer.toString(snpid));
			// Statement stm = (Statement) conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			// conn.ResultSet executeQuery(sql);
			// System.out.println("Successful Query");

			polTable[19] = "";
			polTable[20] = "";
			while (rs.next()) {
				// System.out.println(1);
				int polCode = rs.getInt("snp_id");
				String refAllele = rs.getString("allele");
				/* float */ String freq = rs.getString("freq");

				if (polTable[20] == "") {
					polTable[20] = refAllele;
				} else {
					polTable[20] = polTable[20] + "," + refAllele;
				}

				if (polTable[19] == "") {
					polTable[19] = freq;
				} else {
					polTable[19] = polTable[19] + "," + freq;
				}

				polTable[3] = polTable[20];

				/*
				 * System.out.println("rs: " + polCode + " Reference Allele: " +
				 * refAllele + " Freq: " + freq);
				 */
			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query DB SNP Local 1.3

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Querying transcript region
	public void QueryDBsnpLocal14(int snpid) {

		String strsql = "SELECT SnpFunctionCode.abbrev " + "FROM b137_SNPContigLocusId "
				+ "LEFT JOIN SnpFunctionCode ON b137_SNPContigLocusId.fxn_class = SnpFunctionCode.code" + " WHERE "
				+ "b137_SNPContigLocusId.snp_id = ? ;";

		// System.out.println("SQL:"+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, Integer.toString(snpid));
			// Statement stm = (Statement) conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			// conn.ResultSet executeQuery(sql);
			// System.out.println("Successful Query");

			while (rs.next()) {
				// System.out.println(1);
				// int polCode = rs.getInt("snp_id");
				String transcReg = rs.getString("abbrev");

				polTable[2] = transcReg;

				/*
				 * System.out.println("rs: " + polCode + " Ancestral Allele: " +
				 * ancAllele);
				 */
			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query DB SNP Local 1.4

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Querying assembly information
	public void QueryDBsnpLocal15(int snpid, String mRNAaccId) {

		if (mRNAaccId == null) {
			mRNAaccId = "";
		}

		String strsql = "SELECT SNP_HGVS.hgvs_name, " + "SNP_HGVS.source " + "FROM SNP_HGVS " + " WHERE "
				+ "SNP_HGVS.snp_id = ? " + " AND SNP_HGVS.hgvs_name like ? ;";

		// System.out.println("SQL:"+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, Integer.toString(snpid));
			pstmt.setString(2, mRNAaccId + "%");
			// Statement stm = (Statement) conn.createStatement();
			ResultSet rs = pstmt.executeQuery();
			// conn.ResultSet executeQuery(sql);
			// System.out.println("Successful Query");

			while (rs.next()) {
				// System.out.println(1);
				// int polCode = rs.getInt("snp_id");
				String coordRelGene = rs.getString("hgvs_name");
				String source = rs.getString("source");

				if (coordRelGene != null) {
					String aux = coordRelGene.split(":")[1];
					polTable[4] = aux.substring(0, aux.length() - 3);
					/*
					 * System.out.println("hgvs: " + aux + " Coord: " +
					 * polTable[4]);
					 */
					if (mRNAaccId == "") {
						polTable[7] = source;
						polTable[17] = coordRelGene.split("\\.")[0];
						String aux2 = coordRelGene.split("\\.")[1];
						polTable[18] = aux2.substring(0, aux2.length() - 2);
					} // End of IF mRNAaccID is null
				} // End of IF coordRelGene is null

			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception
	} // End of Query DB SNP Local 1.5

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

} // END OF DB SNP LOCAL AGENT
