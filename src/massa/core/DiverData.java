package massa.core;

public class DiverData implements java.io.Serializable {

	private String[] snprslist;
	private int[] snpidlist;
	private String[] genenamelist;
	private String[] geneidlist;
	private String genename;
	public Protein protein;
	public Polymorphism polymorphism;
	public Genotype genotype;
	public Gene gene;
	public Haplotype haplotype;
	public ReferenceSequence referenceSequence;
	public Sample sample;
	public Person person;
	public Population population;
	public Phenotype phenotype;

	public DiverData() {

		protein = new Protein();
		polymorphism = new Polymorphism();
		genotype = new Genotype();
		gene = new Gene();
		haplotype = new Haplotype();
		referenceSequence = new ReferenceSequence();
		sample = new Sample();
		person = new Person();
		population = new Population();
		phenotype = new Phenotype();

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

	public class Protein implements java.io.Serializable {

		private String sequence;

		// Constructor
		public Protein() {

		}

		// Getters and Setters
		public String getSequence() {
			return sequence;
		}

		public void setSequence(String sequence) {
			this.sequence = sequence;
		}

	} // End of Class Protein

	public class Polymorphism implements java.io.Serializable {

		private String polymorphismCode;
		private String kind;
		private String subKind;
		private String referenceValue;
		private long coordRelGene;
		private String chromosome;
		private long coordRefSeq;
		private String assm_build_version;
		private String assm_coord_start;
		private String assm_coord_end;

		// Constructor
		public Polymorphism() {

		}

		// Getters and Setters

		public String getPolymorphismCode() {
			return polymorphismCode;
		}

		public void setPolymorphismCode(String polymorphismCode) {
			this.polymorphismCode = polymorphismCode;
		}

		public String getKind() {
			return kind;
		}

		public void setKind(String kind) {
			this.kind = kind;
		}

		public String getSubKind() {
			return subKind;
		}

		public void setSubKind(String subKind) {
			this.subKind = subKind;
		}

		public String getReferenceValue() {
			return referenceValue;
		}

		public void setReferenceValue(String referenceValue) {
			this.referenceValue = referenceValue;
		}

		public long getCoordRelGene() {
			return coordRelGene;
		}

		public void setCoordRelGene(long coordRelGene) {
			this.coordRelGene = coordRelGene;
		}

		public String getChromosome() {
			return chromosome;
		}

		public void setChromosome(String chromosome) {
			this.chromosome = chromosome;
		}

		public long getCoordRefSeq() {
			return coordRefSeq;
		}

		public void setCoordRefSeq(long coordRefSeq) {
			this.coordRefSeq = coordRefSeq;
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

	} // End of Class Polymorphism

	// Genotype Class
	public class Genotype implements java.io.Serializable {

		private String genotypeValue;

		// Constructor
		public Genotype() {

		}

		// Getters and Setters
		public String getGenotypeValue() {
			return genotypeValue;
		}

		public void setGenotypeValue(String genotypeValue) {
			this.genotypeValue = genotypeValue;
		}

	} // End of Class Genotype

	public class Haplotype implements java.io.Serializable {

		private String haplotypeID;
		private String haplotypeAlleleNum;
		private String haplotypeValue;
		private String softwareMethod;

		// Constructor
		public Haplotype() {

		}

		// Getters and Setters
		public String getHaplotypeID() {
			return haplotypeID;
		}

		public void setHaplotypeID(String haplotypeID) {
			this.haplotypeID = haplotypeID;
		}

		public String getHaplotypeAlleleNum() {
			return haplotypeAlleleNum;
		}

		public void setHaplotypeAlleleNum(String haplotypeAlleleNum) {
			this.haplotypeAlleleNum = haplotypeAlleleNum;
		}

		public String getHaplotypeValue() {
			return haplotypeValue;
		}

		public void setHaplotypeValue(String haplotypeValue) {
			this.haplotypeValue = haplotypeValue;
		}

		public String getSoftwareMethod() {
			return softwareMethod;
		}

		public void setSoftwareMethod(String softwareMethod) {
			this.softwareMethod = softwareMethod;
		}

	} // End of Class Haplotype

	public class Gene implements java.io.Serializable {

		private String geneName;
		private String geneSymbol;
		private String geneAltSymbol;
		private String geneSequence;
		private String assm_build_version;
		private String assm_coord_start;
		private String assm_coord_end;

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

	} // End of Class Gene

	public class ReferenceSequence implements java.io.Serializable {

		private String referenceSequenceID;
		private String referenceSequenceName;
		private String chormossome;
		private String cytogeneticBand;
		private String contig;
		private String assm_build_version;
		private String assm_coord_start;
		private String assm_coord_end;

		// Constructor
		public ReferenceSequence() {

		}

		// Getters and Setters
		public String getReferenceSequenceID() {
			return referenceSequenceID;
		}

		public void setReferenceSequenceID(String referenceSequenceID) {
			this.referenceSequenceID = referenceSequenceID;
		}

		public String getReferenceSequenceName() {
			return referenceSequenceName;
		}

		public void setReferenceSequenceName(String referenceSequenceName) {
			this.referenceSequenceName = referenceSequenceName;
		}

		public String getChormossome() {
			return chormossome;
		}

		public void setChormossome(String chormossome) {
			this.chormossome = chormossome;
		}

		public String getCytogeneticBand() {
			return cytogeneticBand;
		}

		public void setCytogeneticBand(String cytogeneticBand) {
			this.cytogeneticBand = cytogeneticBand;
		}

		public String getContig() {
			return contig;
		}

		public void setContig(String contig) {
			this.contig = contig;
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

		// Getters and Setters

	} // End of Class Reference Sequence

	public class Sample implements java.io.Serializable {

		private String sampleID;
		private String sampleCode;
		private String collectionDate;
		private String source;
		private String sampleStrategy;

		// Constructor
		public Sample() {

		}

		// Getters and Setters
		public String getSampleID() {
			return sampleID;
		}

		public void setSampleID(String sampleID) {
			this.sampleID = sampleID;
		}

		public String getSampleCode() {
			return sampleCode;
		}

		public void setSampleCode(String sampleCode) {
			this.sampleCode = sampleCode;
		}

		public String getCollectionDate() {
			return collectionDate;
		}

		public void setCollectionDate(String collectionDate) {
			this.collectionDate = collectionDate;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getSampleStrategy() {
			return sampleStrategy;
		}

		public void setSampleStrategy(String sampleStrategy) {
			this.sampleStrategy = sampleStrategy;
		}

	} // End of Class Sample

	public class Person implements java.io.Serializable {

		private String personID;
		private String personCode;
		private String familyID;
		private String fatherID;
		private String motherID;
		private boolean sex;
		private boolean liveSatuts;

		// Constructor
		public Person() {

		}

		// Getters and Setters
		public String getPersonID() {
			return personID;
		}

		public void setPersonID(String personID) {
			this.personID = personID;
		}

		public String getPersonCode() {
			return personCode;
		}

		public void setPersonCode(String personCode) {
			this.personCode = personCode;
		}

		public String getFamilyID() {
			return familyID;
		}

		public void setFamilyID(String familyID) {
			this.familyID = familyID;
		}

		public String getFatherID() {
			return fatherID;
		}

		public void setFatherID(String fatherID) {
			this.fatherID = fatherID;
		}

		public String getMotherID() {
			return motherID;
		}

		public void setMotherID(String motherID) {
			this.motherID = motherID;
		}

		public boolean isSex() {
			return sex;
		}

		public void setSex(boolean sex) {
			this.sex = sex;
		}

		public boolean isLiveSatuts() {
			return liveSatuts;
		}

		public void setLiveSatuts(boolean liveSatuts) {
			this.liveSatuts = liveSatuts;
		}

	} // End of Class Person

	public class Population implements java.io.Serializable {

		private String populationID;
		private String populationName;
		private String geographicOrigin;
		private String country;
		private String coordinatesPopuation;

		// Constructor
		public Population() {

		}

		// Getters and setters
		public String getPopulationID() {
			return populationID;
		}

		public void setPopulationID(String populationID) {
			this.populationID = populationID;
		}

		public String getPopulationName() {
			return populationName;
		}

		public void setPopulationName(String populationName) {
			this.populationName = populationName;
		}

		public String getGeographicOrigin() {
			return geographicOrigin;
		}

		public void setGeographicOrigin(String geographicOrigin) {
			this.geographicOrigin = geographicOrigin;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getCoordinatesPopuation() {
			return coordinatesPopuation;
		}

		public void setCoordinatesPopuation(String coordinatesPopuation) {
			this.coordinatesPopuation = coordinatesPopuation;
		}

	} // End of Class Population

	public class Phenotype implements java.io.Serializable {

		private String phenotype; // Corresponds to qual_var_name and
									// quan_var_name
		private boolean affStatus;
		private float quantVariable;
		private String qualVariable;

		// Constructor
		public Phenotype() {

		}

		// Getters and Setters

		public String getPhenotype() {
			return phenotype;
		}

		public void setPhenotype(String phenotype) {
			this.phenotype = phenotype;
		}

		public boolean isAffStatus() {
			return affStatus;
		}

		public void setAffStatus(boolean affStatus) {
			this.affStatus = affStatus;
		}

		public float getQuantVariable() {
			return quantVariable;
		}

		public void setQuantVariable(float quantVariable) {
			this.quantVariable = quantVariable;
		}

		public String getQualVariable() {
			return qualVariable;
		}

		public void setQualVariable(String qualVariable) {
			this.qualVariable = qualVariable;
		}

	} // End of Class Phenotype

	// Methods

	public String toString() { // Create a method to print variables of the
								// class

		return "Gene Name = " + gene.geneName + "\nGene Alt Symbol = " + gene.geneAltSymbol + "\nChromosome = "
				+ polymorphism.chromosome + "\nPolymorphism Code = " + polymorphism.polymorphismCode + "\nKind = "
				+ polymorphism.kind + "\nSub-Kind = " + polymorphism.subKind + "\nHaplotype ID = "
				+ haplotype.haplotypeID + "\nHaplotype Value = " + haplotype.haplotypeValue + "\nGenotype Value = "
				+ genotype.genotypeValue + "\nReference Sequence = " + referenceSequence.referenceSequenceID
				+ "\nSample Code = " + sample.sampleCode + "\nFamily Id = " + person.familyID + "\nPerson Code = "
				+ person.personCode + "\nPopulation Name = " + population.populationName + "\nPhenotype = "
				+ phenotype.phenotype + "\nAffected Status = " + phenotype.affStatus;
	}

} // End of BioData Class
