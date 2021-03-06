package massa.biodata;

public class Gene implements java.io.Serializable {

	private String geneName;
	private String geneSymbol;
	private String geneId;
	private String geneAltSymbol;
	private String geneSequence;
	private String assm_build_version;
	private String assm_coord_start;
	private String assm_coord_end;
	private String snpid;

	// Constructor
	public Gene() {

	}

	// Getters and Setters
	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getGeneSymbol() {
		return geneSymbol;
	}

	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}

	public String getGeneid() {
		return geneId;
	}

	public void setGeneid(String geneId) {
		this.geneId = geneId;
	}

	public String getGeneAltSymbol() {
		return geneAltSymbol;
	}

	public void setGeneAltSymbol(String geneAltSymbol) {
		this.geneAltSymbol = geneAltSymbol;
	}

	public String getGeneSequence() {
		return geneSequence;
	}

	public void setGeneSequence(String geneSequence) {
		this.geneSequence = geneSequence;
	}

	public String getAssm_build_version() {
		return assm_build_version;
	}

	public void setAssm_build_version(String assm_build_version) {
		this.assm_build_version = assm_build_version;
	}

	public String getAssm_coord_start() {
		return assm_coord_start;
	}

	public void setAssm_coord_start(String assm_coord_start) {
		this.assm_coord_start = assm_coord_start;
	}

	public String getAssm_coord_end() {
		return assm_coord_end;
	}

	public void setAssm_coord_end(String assm_coord_end) {
		this.assm_coord_end = assm_coord_end;
	}

	public String getSnpid() {
		return snpid;
	}

	public void setSnpid(String snpid) {
		this.snpid = snpid;
	}
}