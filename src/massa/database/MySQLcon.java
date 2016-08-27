/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  MySQLconAgent.java
#  -----------------
#
# Original Author: Giordano Bruno Soares Souza
# Contributor(s):
# Updated by (and date):
#
# Command line:  
#
# Dependencies: JADE jar files (jade.jar,commons-codec-1.3.jar)
#
# Description:  Generic class for MySQL Connections
#
# Parameters:
#
#/
 */

package massa.database;

//import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
//import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import massa.biodata.BioData;
import massa.biodata.GeneOntology;
import massa.biodata.HUGO;
import massa.biodata.OMIM;
import massa.biodata.PharmGKB;
import massa.biodata.UCSC;

public class MySQLcon {

	static String driverName = "com.mysql.jdbc.Driver";
	private String serverName; // = "150.164.28.6:3306";
	private String mydatabase; // ="giordano";
	// private String url; // = "jdbc:mysql://" + serverName + "/" + mydatabase;
	private String user; // = "giordano1";
	private String key; // = "giordano";
	public ResultSet rs; // = new ArrayList<String>();
	// String Statement = "";
	public String sql = null;
	public Statement stm;
	public Connection conn = null;
	public int q; // Choose the query to be executed
	public String[] polTable;
	// public String mRNAaccId;

	// BioData objects
	BioData bioobjects;
	GeneOntology goobject;
	UCSC ucscobject;
	OMIM omimobject;
	PharmGKB pharmobject;
	HUGO hugoobject;

	/* Constructor */
	public MySQLcon(String sname, String database, String useR, String passKey) {

		serverName = sname;
		mydatabase = database;
		user = useR; // = "giordano1";
		key = passKey; // = "giordano";

		polTable = new String[21]; // passar o tamanho como parametro

	} // END constructor

	public String getServerName() {
		return serverName;
	}

	public String getMyDatabase() {
		return mydatabase;
	}

	public String getUser() {
		return user;
	}

	public String getKey() {
		return key;
	}

	public String[] getPolTable() {
		return polTable;
	}

	public void clearPolTable() {

		for (int x = 0; x < polTable.length; x++) {
			polTable[x] = null;
		}

	}

	public Connection mysqlConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Class strife. " + e);
		}

		try {

			conn = DriverManager.getConnection("jdbc:mysql://" + getServerName() + "/" + getMyDatabase(), getUser(),
					getKey());
			// Do something with the Connection
			System.out.println("Successful Connection ");

			return conn;
		} catch (SQLException e) {
			// handle any errors
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());

			return null;
		}
	}

	public void mysqlDisconnect() {

		try {
			conn.close();
			// System.out.println("Disconnected");
		} catch (SQLException e) {
			System.out.println("Error while Disconnecting");
			e.printStackTrace();
		}

	}

	public static class PooledMysqlConn {

		public static final BasicDataSource dbSource = new BasicDataSource();

		/* Constructor */
		public PooledMysqlConn() {

			dbSource.setDriverClassName(driverName);
			dbSource.setUrl("jdbc:mysql://localhost:3306/annotation");
			dbSource.setUsername("annotation");
			dbSource.setPassword("1masannotation1");
			dbSource.setPoolPreparedStatements(true);
			dbSource.setMaxOpenPreparedStatements(100);

		} // END constructor

		public Connection getConnection() throws SQLException {
			return dbSource.getConnection();
		}

	}

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // Temporary/Test Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public void QueryOne() {

		String sql = "Select * from example_autoincrement";
		System.out.println("SQL:" + sql);
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			// conn.ResultSet executeQuery(sql);
			System.out.println("Successful Query");

			while (rs.next()) {
				System.out.println("Successful Query");
				Integer id_gp = rs.getInt("id");
				String nome_gp = rs.getString("data");
				System.out.println("ID: " + id_gp + " Data: " + nome_gp);
			} // End of rs.next while
		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion
	} // End of QueryOne

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void QueryThree() {

		String sql = "Select * from snp135Seq limit 0, 30";
		// System.out.println("SQL:"+sql);
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			// conn.ResultSet executeQuery(sql);
			System.out.println("Successful Query");

			while (rs.next()) {
				System.out.println("Successful Query");
				String rs_id = rs.getString("acc");
				String fileOffset = rs.getString("file_offset");
				System.out.println("rs: " + rs_id + " file_offset: " + fileOffset);
			} // End of rs.next while
		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion
	} // End of QueryOne

	public void QueryDBsnpLocaltest() {

		String sql = "Select * from SNP limit 0, 15";
		System.out.println("SQL:" + sql);
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			// conn.ResultSet executeQuery(sql);
			System.out.println("Successful Query");

			while (rs.next()) {
				System.out.println("Successful Query");
				int rsId = rs.getInt("snp_id");
				float avgHet = rs.getFloat("avg_heterozygosity");
				float hetSe = rs.getFloat("het_se");
				String createDate = rs.getString("create_time");
				String updateDate = rs.getString("last_update_time");
				int cpgCode = rs.getInt(6);
				int taxId = rs.getInt(7);
				int validationStatus = rs.getInt(8);
				int exSubsnpId = rs.getInt(9);
				int univarId = rs.getInt(10);
				int cntSubsnp = rs.getInt(11);
				int mapProperty = rs.getInt(12);
				System.out.println("rs: " + rsId + " avgHet: " + avgHet + " HetSe: " + hetSe + " Create Date: "
						+ createDate + " Last Update: " + updateDate + " CPG code: " + cpgCode + " tax ID: " + taxId
						+ " Validation Status: " + validationStatus + " Exemplar SubSNP: " + exSubsnpId + " Univar ID: "
						+ univarId + " CNT Subsnp: " + cntSubsnp + " Map Property " + mapProperty);
			} // End of rs.next while
		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception
	} // End of QueryDBsnpLocaltest

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void QueryDBsnpLocal1(int snpid) {

		String sql = "SELECT SNP.snp_id," + "b137_MapLinkHGVS.snp_type," + "b137_SNPContigLocusId.fxn_class,"
				+ "SubPop.Observerd," + "b137_SNPChrPosOnRef.chr," + "b137_SNPChrPosOnRef.pos,"
				+ "b137_SNPContigLocusId.build_id," + "b137_SNPContigLocusId.asn_from,"
				+ "b137_SNPContigLocusId.asn_to," + "b137_MapLinkHGVS.orientation," + "b137_SNPContigLocusId.locus_id,"
				+ "b137_SNPContigLocusId.locus_symbol," + "Allele.allele AS AncestralAllele,"
				+ "b137_SNPContigLocusId.mrna_acc," + "b137_SNPContigLocusId.mrna_ver," + "SNPAlleleFreq.freq,"
				+ "Allele.allele AS FreqAllele" + "FROM SNP"
				+ "LEFT JOIN b137_MapLinkHGVS ON SNP.snp_id = b137_MapLinkHGVS.snp_id"
				+ "LEFT JOIN SNPContigLocusId ON SNP.snp_id = SNPContigLocusId.snp_id" + "LEFT JOIN SubPop"
				+ "LEFT JOIN b137_SNPChrPosOnRef ON SNP.snp_id = b137_SNPChrPosOnRef.snp_id"
				+ "LEFT JOIN Ancestrall_Alllele ON SNP.allele" + "LEFT JOIN FreqAllele ON SNP.allele" + "WHERE"
				+ "SNP.snp_id = \"" + snpid + "\" ";

		System.out.println("SQL:" + sql);
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			// conn.ResultSet executeQuery(sql);
			System.out.println("Successful Query");

			while (rs.next()) {
				System.out.println(1);
				int polCode = rs.getInt("snp_id");
				String polType = rs.getString("snp_type");
				String transcReg = rs.getString("fxn_class");
				String genotypeVal = rs.getString("genotVal");
				String coordRelGene = rs.getString("chr");
				int chr = rs.getInt("pos");
				String coordRefSeq = rs.getString("build_id");
				String assmBuildVer = rs.getString("asn_from");
				int assmCoordStart = rs.getInt("asn_to");
				int assmCoordEnd = rs.getInt("orientation");
				int geneId = rs.getInt("locus_id");
				String geneName = rs.getString("locus_symbol");
				String ancAllele = rs.getString("AncestralAllele");
				String orient = rs.getString("orientation");
				String mRnaAcc = rs.getString("mrna_acc");
				String mRnaVer = rs.getString("mrna_ver");
				String refAllele = rs.getString("FreqAllele");
				float freq = rs.getFloat("freq");

				System.out.println("rs: " + polCode + " Pol Type: " + polType + " Transcript Region: " + transcReg
						+ " Genotype Value: " + genotypeVal + " Ancestral Allele: " + ancAllele + " Coord Rel Gene: "
						+ coordRelGene + " Chr: " + chr + " Coord Ref Seq: " + coordRefSeq + " Assembly: "
						+ assmBuildVer + " Assembly Coord Start: " + assmCoordStart + " Assembly Coord End: "
						+ assmCoordEnd + " Gene ID: " + geneId + " Gene: " + geneName + " Orientation: " + orient
						+ " Mrna Acc: " + mRnaAcc + " Mrna Ver: " + mRnaVer + " Ref Allele: " + refAllele + " Freq "
						+ freq);
			} // End of rs.next while
		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception
	} // End of Query DB SNP Local 1

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // Gene Ontology Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public GeneOntology QueryGOremote(String gs) {

		// create instance of BioData and GeneOntology objects
		bioobjects = new BioData("gene ontology");
		goobject = bioobjects.createGOInstance();

		String strsql = "Select distinct term_id," + " term_name," + " term_acc, " + "term_type, " + "gp_symbol, "
				+ "pub_dbname " + "FROM term_J_association_J_evidence_J_gene_product " + "WHERE gp_symbol = ? "
				+ "AND gp_species_id = '596252';";
		// System.out.println("SQL:"+sql);
		try {

			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, gs);
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

	public GeneOntology QueryGOLocal(String gs) {

		// create instance of BioData and GeneOntology objects
		bioobjects = new BioData("gene ontology");
		goobject = bioobjects.createGOInstance();

		String strsql = "SELECT  gene_product.symbol, " + "gene_product.full_name, " + "term.term_type, "
				+ "term.name, " + "term.acc, " + "evidence.code, " + "dbxref.xref_dbname, " + "dbxref.xref_key "
				+ "FROM gene_product " + "INNER JOIN dbxref ON (gene_product.dbxref_id=dbxref.id) "
				+ "INNER JOIN species ON (gene_product.species_id=species.id) "
				+ "INNER JOIN association ON (gene_product.id=association.gene_product_id) "
				+ "INNER JOIN evidence ON (association.id=evidence.association_id) "
				+ "INNER JOIN term ON (association.term_id=term.id) " + "WHERE gene_product.symbol = ? "
				+ "AND gene_product.species_id = '596693';";

		try {

			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, gs);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String geneSymbol = rs.getString("gp_symbol");
				String geneName = rs.getString("full_name");
				String termType = rs.getString("term_type");
				String termName = rs.getString("name");
				String termAcc = rs.getString("acc");
				Integer termCode = rs.getInt("code");
				String dbName = rs.getString("xref_dbname");
				String dbkey = rs.getString("xref_key");

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

	} // End of QueryGOLocal

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
					polTable[6] = coordRefSeq + 1;
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

	// Querying assembly information

	public void QueryDBsnpYhaplo(int snpid) {

		String strsql = "SELECT Yhaplogroups.polymorphism " + "Yhaplogroups.positionGrch17 "
				+ "Yhaplogroups.haplogroup " + "FROM Yhaplogroups " + " WHERE " + "Yhaplogroups.dbSNPID = ? ;";

		// System.out.println("SQL:"+sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, Integer.toString(snpid));

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String polyId = rs.getString("polymorphism");
				String chrPos = rs.getString("positionGrch17");
				String haploG = rs.getString("haploGroup");

			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

	} // End of Query Y haplogroups

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // UCSC Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public UCSC QueryUCSC1(String snpid) {

		// create instance of BioData and UCSC objects
		bioobjects = new BioData("ucsc");
		ucscobject = bioobjects.createUCSCInstance();

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
				System.out.println(snpid + " strand =" + strand);
				ucscobject.setStrand(strand);
				String refUCSC = rs.getString("refUCSC");
				System.out.println(snpid + " refUCSC =" + refUCSC);
				ucscobject.setRefUCSC(refUCSC);
				String obsGen = rs.getString("observed");
				System.out.println(snpid + " obsGen =" + obsGen);
				ucscobject.setObsGen(obsGen);
				String ucscClass = rs.getString("class");
				System.out.println(snpid + " ucscClass =" + ucscClass);
				ucscobject.setUcscClass(ucscClass);
				String ucscFunc = rs.getString("func");
				System.out.println(snpid + " ucscFunc =" + ucscFunc);
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

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// UCSC Local Conservation Query
	public UCSC QueryUCSCLocalCons(String snpid) {

		// create instance of BioData and UCSC objects
		bioobjects = new BioData("ucsc");
		ucscobject = bioobjects.createUCSCInstance();

		String strsql = "SELECT snp135.name, " +

				"phastCons46wayPrimates.sumData as phastConsP, " + "phastCons46wayPlacental.sumData as phastConsM, "
				+ "phastCons46way.sumData as phastConsV, " +

				"phastCons46wayOneStepPrimates.phastScore as phastCons1P, "
				+ "phastCons46wayOneStepPlacental.phastScore as phastCons1M, "
				+ "phastCons46wayOneStep.phastScore as phastCons1V, " +

				"phastConsElements46wayPrimates.score as phastEConsP, "
				+ "phastConsElements46wayPlacental.score as phastEConsM, "
				+ "phastConsElements46way.score as phastEConsV, " +

				"phyloP46wayPrimates.sumData as phyloPP, " + "phyloP46wayPlacental.sumData as phyloPM, "
				+ "phyloP46wayAll.sumData as phyloPV, " +

				"phyloP46wayOneStepPrimates.phyloScore as phyloP1P, "
				+ "phyloP46wayOneStepPlacental.phyloScore as phyloP1M, "
				+ "phyloP46wayOneStepAll.phyloScore as phyloP1V, " +

				"FROM snp135 LEFT JOIN snp135CodingDbSnp ON snp135.name = snp135CodingDbSnp.name " +

				"LEFT JOIN phastCons46way ON snp135.chrom = phastCons46way.chr AND snp135.chromStart = phastCons46way.position "
				+ "LEFT JOIN phastCons46wayPlacental ON snp135.chrom = phastCons46wayPlacental.chr AND snp135.chromStart = phastCons46wayPlacental.position "
				+ "LEFT JOIN phastCons46wayPrimates ON snp135.chrom = phastCons46wayPrimates.chr AND snp135.chromStart = phastCons46wayPrimates.position "
				+

				"LEFT JOIN phastCons46wayOneStep ON snp135.chrom = phastCons46wayOneStep.chr AND snp135.chromStart = phastCons46wayOneStep.position "
				+ "LEFT JOIN phastCons46wayOneStepPlacental ON snp135.chrom = phastCons46wayOneStepPlacental.chr AND snp135.chromStart = phastCons46wayOneStepPlacental.position "
				+ "LEFT JOIN phastCons46wayOneStepPrimates ON snp135.chrom = phastCons46wayOneStepPrimates.chr AND snp135.chromStart = phastCons46wayOneStepPrimates.position "
				+

				"LEFT JOIN phastConsElements46way ON snp135.chrom = phastConsElements46way.chr AND snp135.chromStart = phastConsElements46way.position "
				+ "LEFT JOIN phastConsElements46wayPlacental ON snp135.chrom = phastConsElements46wayPlacental.chr AND snp135.chromStart = phastConsElements46wayPlacental.position "
				+ "LEFT JOIN phastConsElements46wayPrimates ON snp135.chrom = phastConsElements46wayPrimates.chr AND snp135.chromStart = phastConsElements46wayPrimates.position "
				+

				"LEFT JOIN phyloP46wayAll ON snp135.chrom = phyloP46wayAll.chr AND snp135.chromStart = phyloP46wayAll.position "
				+ "LEFT JOIN phyloP46wayPlacental ON snp135.chrom = phyloP46wayPlacental.chr AND snp135.chromStart = phyloP46wayPlacental.position "
				+ "LEFT JOIN phyloP46wayPrimates ON snp135.chrom = phyloP46wayPrimates.chr AND snp135.chromStart = phyloP46wayPrimates.position "
				+

				"LEFT JOIN phyloP46wayOneStepAll ON snp135.chrom = phyloP46wayOneStepAll.chr AND snp135.chromStart = phyloP46wayOneStepAll.position "
				+ "LEFT JOIN phyloP46wayOneStepPlacental ON snp135.chrom = phyloP46wayOneStepPlacental.chr AND snp135.chromStart = phyloP46wayOneStepPlacental.position "
				+ "LEFT JOIN phyloP46wayOneStepPrimates ON snp135.chrom = phyloP46wayOneStepPrimates.chr AND snp135.chromStart = phyloP46wayOneStepPrimates.position "
				+

				"WHERE " +

				"snp135.name = ?';";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String phastConsP = rs.getString("phastConsP");
				String phastConsM = rs.getString("phastConsM");
				String phastConsV = rs.getString("phastConsV");

				ucscobject.setPhastConsP(phastConsP);
				ucscobject.setPhastConsM(phastConsM);
				ucscobject.setPhastConsV(phastConsV);

				String phastCons1P = rs.getString("phastCons1P");
				String phastCons1M = rs.getString("phastCons1M");
				String phastCons1V = rs.getString("phastCons1V");

				ucscobject.setPhastConsP(phastCons1P);
				ucscobject.setPhastConsM(phastCons1M);
				ucscobject.setPhastConsV(phastCons1V);

				String phastEConsP = rs.getString("phastEConsP");
				String phastEConsM = rs.getString("phastEConsM");
				String phastEConsV = rs.getString("phastEConsV");

				ucscobject.setPhastEConsP(phastEConsP);
				ucscobject.setPhastEConsM(phastEConsM);
				ucscobject.setPhastEConsV(phastEConsV);

				String phyloPP = rs.getString("phyloPP");
				String phyloPM = rs.getString("phyloPM");
				String phyloPV = rs.getString("phyloPV");

				ucscobject.setPhyloPP(phyloPP);
				ucscobject.setPhyloPP(phyloPM);
				ucscobject.setPhyloPP(phyloPV);

				String phyloP1P = rs.getString("phyloP1P");
				String phyloP1M = rs.getString("phyloP1M");
				String phyloP1V = rs.getString("phyloP1V");

				ucscobject.setPhyloPP(phyloP1P);
				ucscobject.setPhyloPP(phyloP1M);
				ucscobject.setPhyloPP(phyloP1V);

			} // End of rs.next while

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion

		return ucscobject;

	} // End of QueryUCSC1

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	// UCSC Remote Conservation Query
	public UCSC QueryUCSCRemoteCons(String snpid) {

		// create instance of BioData and UCSC objects
		bioobjects = new BioData("ucsc");
		ucscobject = bioobjects.createUCSCInstance();

		String strsql = "SELECT snp135.name, " +

				"phastCons46wayPrimates.sumData as phastConsP, " + "phastCons46wayPlacental.sumData as phastConsM, "
				+ "phastCons46way.sumData as phastConsV, " +

				"phastConsElements46wayPrimates.score as  as phastEConsP, "
				+ "phastConsElements46wayPlacental.score as phastEConsM, "
				+ "phastConsElements46way.score as phastEConsV, " +

				"phyloP46wayPrimates.sumData as phyloPP, " + "phyloP46wayPlacental.sumData as phyloPM, "
				+ "phyloP46wayAll.sumData as phyloPV, " +

				"FROM snp135 LEFT JOIN snp135CodingDbSnp ON snp135.name = snp135CodingDbSnp.name " +

				"LEFT JOIN phastCons46way ON snp135.chrom = phastCons46way.chr AND snp135.chromStart = phastCons46way.position "
				+ "LEFT JOIN phastCons46wayPlacental ON snp135.chrom = phastCons46wayPlacental.chr AND snp135.chromStart = phastCons46wayPlacental.position "
				+ "LEFT JOIN phastCons46wayPrimates ON snp135.chrom = phastCons46wayPrimates.chr AND snp135.chromStart = phastCons46wayPrimates.position "
				+

				"LEFT JOIN phastConsElements46way ON snp135.chrom = phastConsElements46way.chr AND snp135.chromStart = phastConsElements46way.position "
				+ "LEFT JOIN phastConsElements46wayPlacental ON snp135.chrom = phastConsElements46wayPlacental.chr AND snp135.chromStart = phastConsElements46wayPlacental.position "
				+ "LEFT JOIN phastConsElements46wayPrimates ON snp135.chrom = phastConsElements46wayPrimates.chr AND snp135.chromStart = phastConsElements46wayPrimates.position "
				+

				"LEFT JOIN phyloP46wayAll ON snp135.chrom = phyloP46wayAll.chr AND snp135.chromStart = phyloP46wayAll.position "
				+ "LEFT JOIN phyloP46wayPlacental ON snp135.chrom = phyloP46wayPlacental.chr AND snp135.chromStart = phyloP46wayPlacental.position "
				+ "LEFT JOIN phyloP46wayPrimates ON snp135.chrom = phyloP46wayPrimates.chr AND snp135.chromStart = phyloP46wayPrimates.position "
				+ "WHERE " + "snp135.name = ?';";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, snpid);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String phastConsP = rs.getString("phastConsP");
				String phastConsM = rs.getString("phastConsM");
				String phastConsV = rs.getString("phastConsV");

				ucscobject.setPhastConsP(phastConsP);
				ucscobject.setPhastConsM(phastConsM);
				ucscobject.setPhastConsV(phastConsV);

				String phastEConsP = rs.getString("phastEConsP");
				String phastEConsM = rs.getString("phastEConsM");
				String phastEConsV = rs.getString("phastEConsV");

				ucscobject.setPhastEConsP(phastEConsP);
				ucscobject.setPhastEConsM(phastEConsM);
				ucscobject.setPhastEConsV(phastEConsV);

				String phyloPP = rs.getString("phyloPP");
				String phyloPM = rs.getString("phyloPM");
				String phyloPV = rs.getString("phyloPV");

				ucscobject.setPhyloPP(phyloPP);
				ucscobject.setPhyloPP(phyloPM);
				ucscobject.setPhyloPP(phyloPV);

			} // End of rs.next while

			rs.close();
			pstmt.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Excepetion

		return ucscobject;
	} // End of QueryUCSC1

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // OMIM Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public OMIM QueryOMIM(String genesymbol) {

		// create instance of BioData and OMIM objects
		bioobjects = new BioData("omim");
		omimobject = bioobjects.createOMIMInstance();

		String strsql = "select genemap.geneSymbol," + "genemap.cytoLoc, " + "genemap.geneStatus, " + "genemap.method, "
				+ "genemap.disorder1, " + "genemap.disorder2, " + "genemap.disorder3, " + "genemap.mimId, "
				+ "genemap.comments1, " + "genemap.comments2, " + "genemap.reference " + "FROM genemap" + "WHERE"
				+ "genemap.geneSymbol = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String geneSymbol = rs.getString("geneSymbol");
				String cytoLoc = rs.getString("cytoLoc");
				String geneStatus = rs.getString("geneStatus");
				String method = rs.getString("method");
				String disorder1 = rs.getString("disorder1");
				String disorder2 = rs.getString("disorder2");
				String disorder3 = rs.getString("disorder3");
				String omimId = rs.getString("mimId");
				String comments1 = rs.getString("comments1");
				String comments2 = rs.getString("comments2");
				String comments3 = rs.getString("comments3");
				String reference = rs.getString("reference");

				omimobject.setDisorderItem(disorder1);
				omimobject.setDisorderItem(disorder2);
				omimobject.setDisorderItem(disorder3);

				omimobject.setCommentsItem(comments1);
				omimobject.setCommentsItem(comments2);
				omimobject.setCommentsItem(comments3);

				omimobject.setReferencesItem(reference);

			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

		return omimobject;
	} // End of Query OMIM Local 1

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // PharmGKB Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public PharmGKB QueryPGKBLocal(String genesymbol) {

		// create instance of BioData and PharmGKB objects
		bioobjects = new BioData("PharmGKB");
		pharmobject = bioobjects.createPharmGKBInstance();

		String strsql = "select gene.pgkbkey \"geneSymbol\" ," + "gene.pgkbId \"genepgkbId\", " + "gene.relatedGenes, "
				+ "goldenPath.name \"polyLoc\", " + "goldenPath.pgkbKey \"polyId\", " + "haplotype.name \"haplotype\", "
				+ "drug.name \"drug\", " + "disease.name \"disease\", " + "pathway.name \"pathName\", "
				+ "pathway.type \"pathType\", " + "literature.pgkbkey \"literature\"" + "FROM gene "
				+ "LEFT JOIN goldenPath ON gene.fk = goldenPath.fk " + "LEFT JOIN haplotype ON gene.fk = haplotype.fk "
				+ "LEFT JOIN drug ON gene.fk = drug.fk " + "LEFT JOIN disease ON gene.fk = disease.fk "
				+ "LEFT JOIN pathway ON gene.fk = pathway.fk " + "LEFT JOIN literature ON gene.fk = literature.fk "
				+ "WHERE " + "gene.pgkbkey = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			ResultSet rs = pstmt.executeQuery();

			System.out.println("Result request:");
			while (rs.next()) {

				String geneSymbol = rs.getString("geneSymbol");
				String genepgkbId = rs.getString("genepgkbId");
				String relGenes = rs.getString("relatedGenes");
				String polyLoc = rs.getString("polyLoc");
				String polyId = rs.getString("polyId");
				String haplotype = rs.getString("haplotype");
				String drug = rs.getString("drug");
				String disease = rs.getString("disease");
				String pathName = rs.getString("pathName");
				String pathType = rs.getString("pathType");
				String literature = rs.getString("literature");

				System.out.println(
						"relgenes:" + relGenes + " pathway:" + pathName + " drug:" + drug + " disease:" + disease);

				pharmobject.setDrugItem(drug);
				pharmobject.setDiseaseItem(disease);
				pharmobject.setPolymorphismIdsItem(polyId);
				pharmobject.setPolymorphismLocItem(polyLoc);
				pharmobject.setHaplotypeItem(haplotype);
				pharmobject.setPathwayItem(pathName);
				pharmobject.setPathwayTypeItem(pathType);
				pharmobject.setLiteratureItem(literature);

				if (rs.last()) {
					pharmobject.setGenesymbol(geneSymbol);
					pharmobject.setGenePgkbId(genepgkbId);
					pharmobject.setGenex(Arrays.asList(relGenes.split(",")));
				}

			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

		return pharmobject;
	} // End of Query pharmGKB

	/*
	 * /////////////////////////////////////////////////////////////////////////
	 * /////////////////////////////// // // HGNC Queries //
	 * /////////////////////////////////////////////////////////////////////////
	 * ///////////////////////////////
	 */

	public OMIM QueryHGNCLocal(String genesymbol) {

		// create instance of BioData and OMIM objects
		bioobjects = new BioData("hugo");
		hugoobject = bioobjects.createHUGOInstance();

		String strsql = "select hugoDB.hgncId," + "hugoDB.approvedSymbol, " + "hugoDB.approvedName, "
				+ "hugoDB.locusType, " + "hugoDB.locusGroup, " + "hugoDB.previousSymbol, " + "hugoDB.synonyms, "
				+ "hugoDB.nameSynomys, " + "hugoDB.geneFamilyTag, " + "hugoDB.geneFamilyDesc, " +

				"hugoDB.specialistDBLink, " + "hugoDB.accessionNumbers, " + "hugoDB.enzymeId, " + "hugoDB.entrezId, "
				+ "hugoDB.ensemblId, " + "hugoDB.pubmedIds, " + "hugoDB.refSeqIds, " + "hugoDB.CCDSIds, "
				+ "hugoDB.VEGAIds, " + "hugoDB.entrezGeneId, " + "hugoDB.omimId, " + "hugoDB.refSeq, "
				+ "hugoDB.uniProtId, " + "hugoDB.ucscId, " + "hugoDB.mouseGdbId, " + "hugoDB.ratGdbId, " +

				"FROM hugoDB" + "WHERE" + "hugoDB.approvedSymbol = ? ;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(strsql);
			pstmt.setString(1, genesymbol);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String hugoId = rs.getString("hugoId");
				String geneSymbol = rs.getString("approvedSymbol");
				String geneName = rs.getString("approvedName");
				String prevGeneNames = rs.getString("previousSymbols");
				String altGeneSymbols = rs.getString("synonyms");
				String geneSyns = rs.getString("nameSynonyms");

				hugoobject.setHgncId(hugoId);
				hugoobject.setGeneSymbol(geneSymbol);
				hugoobject.setHgGeneName(prevGeneNames);
				hugoobject.setGeneSynonyms(altGeneSymbols);

				String geneTag = rs.getString("geneFamilyTag");
				String geneFamily = rs.getString("geneFamilyDesc");
				String geneType = rs.getString("locusType");
				String geneGroup = rs.getString("locusGroup");

				hugoobject.setGeneFamilyTag(geneTag);
				hugoobject.setGeneFamily(geneFamily);
				hugoobject.setLocusType(geneType);
				hugoobject.setLocusGroup(geneGroup);

				String link = rs.getString("specialistDBLink");
				hugoobject.setLocusSpecDB(link);

				String accNumbers = rs.getString("accessionNumbers");
				String enzymeId = rs.getString("enzymeId");
				String ensemblId = rs.getString("ensemblId");
				String refSeqId = rs.getString("refSeqIds");
				String ccdsId = rs.getString("CCDSIds");
				String vegaId = rs.getString("VEGAIds");
				String entrezGeneId = rs.getString("entrezGeneId");
				String omimId = rs.getString("omimId");
				String uniProtGeneId = rs.getString("uniProtId");
				String ucscGeneId = rs.getString("ucscId");

				hugoobject.setEnzymeId(enzymeId);
				hugoobject.setEnsemblId(ensemblId);
				hugoobject.setRefSeqIds(refSeqId);
				hugoobject.setCCDSIds(ccdsId);
				hugoobject.setVegaIds(vegaId);
				hugoobject.setEntrezId(entrezGeneId);
				hugoobject.setOmimId(omimId);
				hugoobject.setUniProtId(uniProtGeneId);
				hugoobject.setUcscId(ucscGeneId);

				String mouseGdbId = rs.getString("mouseGdbId");
				String ratGdbId = rs.getString("ratGdbId");

				hugoobject.setMouseGdbId(mouseGdbId);
				hugoobject.setRatGdbId(ratGdbId);

				String pmid = rs.getString("pubmedIds");
				hugoobject.setPubMedIds(pmid);

			} // End of rs.next while

			rs.close();
			pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		} // End of Catch Exception

		return omimobject;
	} // End of Query OMIM Local 1

} // End Class
