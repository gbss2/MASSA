package massa.biodata;

public class UCSC implements java.io.Serializable {

	private String polymorphismCode;
	private String strand;
	private String refUCSC;
	private String obsGen;
	private String ucscClass;
	private String ucscFunc;
	// Gene
	private String genesymbol;
	// Conservation Scores
	private String phastConsP; // PhastCons Primates
	private String phastConsM; // PhastCons Placental
	private String phastConsV; // PhastCons Vertebrate
	private String phastEConsP; // PhastCons Elements Primates
	private String phastEConsM; // PhastCons Elements Placental
	private String phastEConsV; // PhastCons Elements Vertebrate
	private String phyloPP; // PhyloP Primates
	private String phyloPM; // PhyloP Placental
	private String phyloPV; // PhyloP Vertebrate

	public UCSC() {

	}

	public String getPolymorphismCode() {
		return polymorphismCode;
	}

	public void setPolymorphismCode(String polymorphismCode) {
		this.polymorphismCode = polymorphismCode;
	}

	public String getGenesymbol() {
		return genesymbol;
	}

	public void setGenesymbol(String genesymbol) {
		this.genesymbol = genesymbol;
	}

	public String getStrand() {
		return strand;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}

	public String getRefUCSC() {
		return refUCSC;
	}

	public void setRefUCSC(String refUCSC) {
		this.refUCSC = refUCSC;
	}

	public String getObsGen() {
		return obsGen;
	}

	public void setObsGen(String obsGen) {
		this.obsGen = obsGen;
	}

	public String getUcscClass() {
		return ucscClass;
	}

	public void setUcscClass(String ucscClass) {
		this.ucscClass = ucscClass;
	}

	public String getUcscFunc() {
		return ucscFunc;
	}

	public void setUcscFunc(String ucscFunc) {
		this.ucscFunc = ucscFunc;
	}

	public String getPhastConsP() {
		return phastConsP;
	}

	public void setPhastConsP(String phastConsP) {
		this.phastConsP = phastConsP;
	}

	public String getPhastConsM() {
		return phastConsM;
	}

	public void setPhastConsM(String phastConsM) {
		this.phastConsM = phastConsM;
	}

	public String getPhastConsV() {
		return phastConsV;
	}

	public void setPhastConsV(String phastConsV) {
		this.phastConsV = phastConsV;
	}

	public String getPhyloPP() {
		return phyloPP;
	}

	public void setPhyloPP(String phyloPP) {
		this.phyloPP = phyloPP;
	}

	public String getPhyloPM() {
		return phyloPM;
	}

	public void setPhyloPM(String phyloPM) {
		this.phyloPM = phyloPM;
	}

	public String getPhyloPV() {
		return phyloPV;
	}

	public void setPhyloPV(String phyloPV) {
		this.phyloPV = phyloPV;
	}

	public String getPhastEConsP() {
		return phastEConsP;
	}

	public void setPhastEConsP(String phastEConsP) {
		this.phastEConsP = phastEConsP;
	}

	public String getPhastEConsM() {
		return phastEConsM;
	}

	public void setPhastEConsM(String phastEConsM) {
		this.phastEConsM = phastEConsM;
	}

	public String getPhastEConsV() {
		return phastEConsV;
	}

	public void setPhastEConsV(String phastEConsV) {
		this.phastEConsV = phastEConsV;
	}

}