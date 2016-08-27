package massa.biodata;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BioDataLite implements java.io.Serializable {

	private String[] snprslist;
	private int[] snpidlist;
	private String[] genenamelist;
	private String[] geneidlist;
	private String genename;
	private int searchid;
	private List<Integer> searchidlist;
	private int analysistype;
	public Hashtable<String, String> provenance_attrib;
	public Hashtable<String, String> provenance_version;

	public BioDataLite() {
		searchidlist = new ArrayList<Integer>();
		provenance_attrib = new Hashtable<String, String>();
		provenance_version = new Hashtable<String, String>();
	}

	public void setBasicProvenance() {

		/*
		 * LIST OF ATTRIBUTES 1. Polymorphism Id 2. Polymorphism Type 3. Gene
		 * Symbol 4. Gene ID 5. Transcript Region 6. Nucleotide Numbering coding
		 * DNA 7. Chromosome 8. Chromosome Position 9. Ancestral Allele 10.
		 * Orientation 11. Assembly Build Version 12. Assembly Coord Start 13.
		 * Assembly Coord End 14. mRNA accession 15. mRNA version 16. Alleles
		 * 17. Frequency
		 */

		provenance_attrib.put("Polymorphism Id", "dbsnp");
		provenance_attrib.put("Polymorphism Type", "dbsnp");
		provenance_attrib.put("Gene Symbol", "dbsnp");
		provenance_attrib.put("Gene ID", "dbsnp");
		provenance_attrib.put("Transcript Region", "dbsnp");
		provenance_attrib.put("Nucleotide Numbering coding DNA", "dbsnp");
		provenance_attrib.put("Chromosome", "dbsnp");
		provenance_attrib.put("Chromosome Position", "dbsnp");
		provenance_attrib.put("Ancestral Allele", "dbsnp");
		provenance_attrib.put("Orientation", "dbsnp");
		provenance_attrib.put("Assembly Build Version", "dbsnp");
		provenance_attrib.put("Assembly Coord Start", "dbsnp");
		provenance_attrib.put("Assembly Coord End", "dbsnp");
		provenance_attrib.put("mRNA accession", "dbsnp");
		provenance_attrib.put("mRNA version", "dbsnp");
		provenance_attrib.put("Alleles", "dbsnp");
		provenance_attrib.put("Frequency", "dbsnp");

		provenance_version.put("dbsnp", "137");

	}

	public void setCompleteProvenance() {

		/*
		 * LIST OF ATTRIBUTES 1. Polymorphism Id 2. Polymorphism Type 3. Gene
		 * Symbol 4. Gene ID 5. Transcript Region 6. Nucleotide Numbering coding
		 * DNA 7. Chromosome 8. Chromosome Position 9. Ancestral Allele 10.
		 * Orientation 11. Assembly Build Version 12. Assembly Coord Start 13.
		 * Assembly Coord End 14. mRNA accession 15. mRNA version 16. Alleles
		 * 17. Frequency 18. Pathway list 19. Drug list 20. Disease list 21.
		 * Genex list
		 */

		provenance_attrib.put("Polymorphism Id", "dbsnp");
		provenance_attrib.put("Polymorphism Type", "dbsnp");
		provenance_attrib.put("Gene Symbol", "dbsnp");
		provenance_attrib.put("Gene ID", "dbsnp");
		provenance_attrib.put("Transcript Region", "dbsnp");
		provenance_attrib.put("Nucleotide Numbering coding DNA", "dbsnp");
		provenance_attrib.put("Chromosome", "dbsnp");
		provenance_attrib.put("Chromosome Position", "dbsnp");
		provenance_attrib.put("Ancestral Allele", "dbsnp");
		provenance_attrib.put("Orientation", "dbsnp");
		provenance_attrib.put("Assembly Build Version", "dbsnp");
		provenance_attrib.put("Assembly Coord Start", "dbsnp");
		provenance_attrib.put("Assembly Coord End", "dbsnp");
		provenance_attrib.put("mRNA accession", "dbsnp");
		provenance_attrib.put("mRNA version", "dbsnp");
		provenance_attrib.put("Alleles", "dbsnp");
		provenance_attrib.put("Frequency", "dbsnp");
		provenance_version.put("dbsnp", "137");

		provenance_attrib.put("Strand", "ucsc");
		provenance_attrib.put("UCSC Reference", "ucsc");
		provenance_attrib.put("UCSC Observed", "ucsc");
		provenance_attrib.put("UCSC Polymorphism Class", "ucsc");
		provenance_attrib.put("UCSC Functional Class", "ucsc");
		provenance_version.put("ucsc", "x");

		provenance_attrib.put("Pathway", "pharmgkb");
		provenance_attrib.put("Disease", "pharmgkb");
		provenance_attrib.put("Drug", "pharmgkb");
		provenance_attrib.put("Related Genes", "pharmgkb");
		provenance_version.put("pharmgkb", "x");

		provenance_attrib.put("Biological Process", "gene ontology");
		provenance_attrib.put("Molecular Function", "gene ontology");
		provenance_attrib.put("Celular Component", "gene ontology");
		provenance_version.put("gene ontology", "x");

		provenance_attrib.put("Disorder", "omim");
		provenance_attrib.put("Comments", "omim");
		provenance_attrib.put("CytoLoc", "omim");
		provenance_version.put("omim", "x");

		// OMIM
		provenance_attrib.put("Cytoloc", "omim");
		provenance_attrib.put("Gene Status", "omim");
		provenance_attrib.put("Gene Map Methods", "omim");
		provenance_attrib.put("Disorders", "omim");
		provenance_attrib.put("MIM ids", "omim");
		provenance_attrib.put("Inheritance", "omim");
		provenance_attrib.put("Pheno Map Methods", "omim");
		provenance_attrib.put("Comments", "omim");
		// HGNC
		// provenance_attrib.put("HgncId","hgnc");
		// provenance_attrib.put("GeneName","hgnc");
		// provenance_attrib.put("GeneSynonyms","hgnc");
		// provenance_attrib.put("LocusType","hgnc");
		// provenance_attrib.put("LocusGroup","hgnc");
		// provenance_attrib.put("GeneFamilyTag","hgnc");
		// provenance_attrib.put("GeneFamily","hgnc");
		// provenance_attrib.put("SpecialistDBLinks","hgnc");
		// provenance_attrib.put("LocusSpecDB","hgnc");
		// provenance_attrib.put("EnzymeId","hgnc");
		// provenance_attrib.put("EntrezId","hgnc");
		// provenance_attrib.put("EnsemblId","hgnc");
		// provenance_attrib.put("PubMedIds","hgnc");
		// provenance_attrib.put("RefSeqIds","hgnc");
		// provenance_attrib.put("CCDSIds","hgnc");
		// provenance_attrib.put("VegaIds","hgnc");
		// provenance_attrib.put("UniProtId","hgnc");
		// provenance_attrib.put("MouseGdbId","hgnc");
		// provenance_attrib.put("RatGdbId","hgnc");
		provenance_attrib.put("HgncId", "hgnc");
		provenance_attrib.put("GeneName", "hgnc");
		provenance_attrib.put("GeneSynonyms", "hgnc");
		provenance_attrib.put("LocusType", "hgnc");
		provenance_attrib.put("LocusGroup", "hgnc");
		provenance_attrib.put("GeneFamilyTag", "hgnc");
		provenance_attrib.put("GeneFamily", "hgnc");
		// GWAScatalog
		provenance_attrib.put("pubmedid", "gwas_catalog");
		provenance_attrib.put("reportedgenes", "gwas_catalog");
		provenance_attrib.put("riskallele", "gwas_catalog");
		provenance_attrib.put("context", "gwas_catalog");
		provenance_attrib.put("pvalue", "gwas_catalog");
		// PolyPhen
		provenance_attrib.put("polyphenScore", "polyphen");
		// SIFT
		provenance_attrib.put("siftScore", "sift");
		// PROVEAN
		provenance_attrib.put("proveanScore", "provean");
		// REACTOME
		provenance_attrib.put("reactomePathway", "reactome");

		provenance_version.put("dbsnp", "137_04-10-2013");
		provenance_version.put("pharmgkb", "05-16-2013");
		provenance_version.put("ucsc", "hg19_03-15-2013");
		provenance_version.put("gene ontology", "03-13-2013");
		provenance_version.put("omim", "03-25-2013");
		provenance_version.put("hgnc", "04-09-2013");
		provenance_version.put("gwas_catalog", "08-10-2013");
		provenance_version.put("provean", "12-16-2013");
		provenance_version.put("sift", "12-16-2013");
		provenance_version.put("polyphen", "12-16-2013");
		provenance_version.put("reactome", "12-27-2013");

		// provenance_version.put("dbsnp","online");
		// provenance_version.put("pharmgkb","online");
		// provenance_version.put("ucsc","online");
		// provenance_version.put("gene ontology","online");
		// provenance_version.put("omim","online");
		// provenance_version.put("hgnc","online");

	}

	public void setSnpRsList(String[] l) {
		this.snprslist = new String[l.length];
		this.snprslist = l;
	}

	public void setSnpIdList(int[] l) {
		this.snpidlist = new int[l.length];
		this.snpidlist = l;
	}

	public void setGeneNameList(String[] l) {
		this.genenamelist = new String[l.length];
		this.genenamelist = l;
	}

	public void setGeneIdList(String[] l) {
		this.geneidlist = new String[l.length];
		this.geneidlist = l;
	}

	public String[] getSnpRsList() {
		return snprslist;
	}

	public int[] getSnpIdList() {
		return snpidlist;
	}

	public String[] getGeneNameList() {
		return genenamelist;
	}

	public String[] getGeneIdList() {
		return geneidlist;
	}

	public String getGeneName() {
		return genename;
	}

	public void setGeneName(String genename) {
		this.genename = genename;
	}

	public int getSearchid() {
		return searchid;
	}

	public void setSearchid(int searchid) {
		this.searchid = searchid;
	}

	public List<Integer> getSearchidlist() {
		return searchidlist;
	}

	public void setSearchidlist(List<Integer> searchidlist) {
		this.searchidlist = searchidlist;
	}

	public void setSearchidItem(int item) {
		this.searchidlist.add(item);
	}

	public int getAnalysistype() {
		return analysistype;
	}

	public void setAnalysistype(int analysistype) {
		this.analysistype = analysistype;
	}

} // End of BioData Class
