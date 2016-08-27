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
# Contributor(s): Guilherme Kingma	
# Updated by (and date): 30/11/12
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioData;
import massa.biodata.Polymorphism;

@SuppressWarnings("serial")
public class DBsnpAgent extends DBagent {

	/* Atributes */
	// BioDataLite snpdata;
	BioData snpdata;
	int input_length; // * o tamanho sempre sera igual ao numero de
	// testes. sera passado futuramente, junto com a
	// lista de entradas.

	int[] input; // ** futuramente a lista sera passado como
	// mensagem recebida do agente coordenador

	/* Constructor */
	public DBsnpAgent() {
		super();
		this.setDBname("dbsnp");
		this.setInformation("snp");
		this.snpdata = new BioData("snp");
	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBsnpAgent must register */
		this.register();

		// Behaviour execution
		addBehaviour(new waitRequest());
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
					BioData msgcontent = (BioData) msg.getContentObject();
					System.out.println("Agent " + getLocalName() + " executing request...");
					addBehaviour(new dbSNPAction(msgcontent.getSnpIdList(), sender));

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

		public dbSNPAction(int[] sl, AID pa) {
			partneragent = pa;
			snpidlist = sl;

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessDBsnpEutils(snpidlist), STATE_A);
			this.registerLastState(new SendReply(partneragent), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}

	}

	class accessDBsnpEutils extends ParallelBehaviour {
		private int[] snpidlist;

		public accessDBsnpEutils(int[] sl) {
			super(WHEN_ALL);
			this.snpidlist = sl;

		}

		public void onStart() {

			// annconnection = annmyconnection.mysqlConnect();
			System.out.println("Threads ativas:" + Thread.getAllStackTraces().size());

			for (int i = 0; i < snpidlist.length; i++) {
				this.addSubBehaviour(new remoteAccessDBsnpEutils(snpidlist[i]));
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			// annmyconnection.mysqlDisconnect();
			return super.onEnd();
		}

	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class remoteAccessDBsnpEutils extends OneShotBehaviour {
		/*
		 * correspondence UrlFilter x BioData 0 id* (String polymorphismCode)
		 * 
		 * 1 kind (String kind)
		 * 
		 * 2 sub_kind (String subKind)
		 * 
		 * 3 reference_value (String referenceValue)
		 * 
		 * 4 coord_relative_gene (String coordRelGene)
		 * 
		 * 5 chromosome (String chromosome)
		 * 
		 * 6 contig_pos (String coordRefSeq)
		 * 
		 * 7 assm_build_version (String assm_build_version)
		 * 
		 * 8 assm_load_start (String assm_coord_start)
		 * 
		 * 9 assm_load_end (String assm_coord_end)
		 * 
		 * 10 description (String description)
		 * 
		 * 11 out_link (String outlink)
		 * 
		 * 12 geneID (String geneid)
		 * 
		 * 13 gene name (String genename)
		 * 
		 * 14 value (String value)
		 * 
		 * 15 ancestralAllele (String ancestralAllele)
		 * 
		 * 16 orientation (String orientation)
		 * 
		 * 17 mrnaAcc (String mrnaAcc)
		 * 
		 * 18 mrnaVer (String mrnaVer)
		 * 
		 * 19 freq (String freq)
		 * 
		 * 20 referenceAllele (String referenceAllele)
		 */

		private int snpid;
		UrlFilter urlfilter;
		private Polymorphism snpresult;

		public remoteAccessDBsnpEutils(int l) {

			snpid = l;
			this.urlfilter = new UrlFilter(snpid);
			this.snpresult = snpdata.createPolymorphismInstance();
		}

		public void action() {

			urlfilter.readFLT();
			urlfilter.readXML();
			// System.out.println("Imprimindo ID = "+snpid);
			for (int x = 0; x < urlfilter.getPolTableSize(); x++) {

				if (urlfilter.polTable[x] == null) {
					urlfilter.polTable[x] = "null";
				}
			}

			// Set results on the BioData object Polymorphism for further
			// incorporation to BioData object snpdata
			snpresult.setPolymorphismCode(Integer.toString(snpid));
			snpresult.setKind(urlfilter.polTable[1]);
			snpresult.setSubKind(urlfilter.polTable[2]);
			snpresult.setReferenceValue(urlfilter.polTable[3]);
			snpresult.setCoordRelGene(urlfilter.polTable[4]);
			snpresult.setChromosome(urlfilter.polTable[5]);
			snpresult.setCoordRefSeq(urlfilter.polTable[6]);
			snpresult.setAssm_build_version(urlfilter.polTable[7]);
			snpresult.setAssm_coord_start(urlfilter.polTable[8]);
			snpresult.setAssm_coord_end(urlfilter.polTable[9]);
			snpresult.setGenesymbol(urlfilter.polTable[13]);
			snpresult.setValue(urlfilter.polTable[14]);
			snpresult.setAncestralAllele(urlfilter.polTable[15]);
			snpresult.setOrientation(urlfilter.polTable[16]);
			snpresult.setMrnaAcc(urlfilter.polTable[17]);
			snpresult.setMrnaVer(urlfilter.polTable[18]);
			snpresult.setFreq(urlfilter.polTable[19]);
			snpresult.setReferenceAllele(urlfilter.polTable[20]);

			// Set results on the BioData object Gene for further incorporation
			// to BioData object snpdata
			if (urlfilter.polTable[12] != "NA") {
				snpresult.setGenesymbol(urlfilter.polTable[13]);
				snpresult.setGeneid(urlfilter.polTable[12]);
			} else {
				snpresult.setGenesymbol("null");
				snpresult.setGeneid("null");
			}

			synchronized (snpdata) {
				snpdata.setPolymorphismList(snpresult);
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
			snpdata.setSearchid(getAnnsearchid());
		}

		public void action() {

			System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
			msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(msgreceiver);
			msg.setLanguage("English");
			try {
				msg.setContentObject(snpdata);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myAgent.send(msg);

		}
	}// END SENDMSGTOPROVIDER CLASS

}

class UrlFilter {

	/*
	 * 0 id*
	 * 
	 * 1 kind [SNP, STR, CNV, indel] {XML TAG "Rs" atributo "snpClass"}
	 * 
	 * 2 sub_kind [intronic/genic/exonic/inter_genic] {FLT linha "LOC" atributo
	 * "fxn-class"}
	 * 
	 * 3 reference_value {FLT linha "SNP" atributo "alleles"}
	 * 
	 * 4 position_reference_sequence {XML TAG "hgvs"*} *primeira linha
	 * 
	 * 5 chromosome {FLT linha "CTG" atributo "chr"}
	 * 
	 * 6 position_reference_sequence
	 * 
	 * 7 assm_build_version {FLT linha "CTG" atributo "assembly"}
	 * 
	 * 8 assm_load_start {FLT linha "CTG" atributo "chr-pos"}
	 * 
	 * 9 assm_load_end {FLT linha "CTG" atributo "chr-pos"}
	 * 
	 * 10 description
	 * 
	 * 11 out_link
	 * 
	 * 12 geneID {FLT linha "LOC" atributo "locus_id"} *primeira linha CTG
	 * 
	 * 13 gene name {FLT linha "LOC" segundo atributo} *primeira linha
	 * 
	 * 14 value {XML TAG "Het" atributo "value"}
	 * 
	 * 15 ancestralAllele {XML TAG "Sequence" atributo "ancestralAllele"}
	 * 
	 * 16 orientation {XML TAG "Component"* atributo "orientation"} *dentro de
	 * "Assembly"
	 * 
	 * 17 mrnaAcc {XML TAG "FxnSet"* atributo "mrnaAcc"}
	 * *Assembly>Component>MapLoc>FxnSet
	 * 
	 * 18 mrnaVer {XML TAG "FxnSet"* atributo "mrnaVer"}
	 * 
	 * 19 freq {XML TAG "Frequency atributo "freq"}
	 * 
	 * 20 reference_allele {XML TAG "Frequency atributo "allele"}
	 */

	/* Atributos */
	// ID referente ao DBSNP:
	int id;
	// Vetor a ser mandado para atualizar o Banco de Dados:
	String[] polTable;
	// First Result Flag:
	int firstRes = 0;

	/* Constructor */
	public UrlFilter(int id) {
		polTable = new String[21];
		setId(id);
	}

	public int getPolTableSize() {
		return polTable.length;
	}

	/**
	 * ler arquivo FLT, armazenando os dados:
	 */
	public void readFLT() {
		try {
			URL url = new URL(makeURL(id, "FLT"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			do {
				line = reader.readLine();

				// polTable[3] - reference_value
				if (line.startsWith("SNP")) {
					String alleles = null;
					// String value = null; est\E1 atualizando no arquivo XML
					int inicio = 0;
					int fim = 0;

					// pegar a posicao inicial do atributo "alleles" em line
					inicio = line.indexOf("alleles=") + 8;
					alleles = line.substring(inicio);
					// pegar a posicao final do atributo "alleles" em line
					fim = alleles.indexOf(" | ");
					alleles = alleles.substring(0, fim);

					// armazenar
					polTable[3] = alleles; // est\E1 no readXML()
				}
				// polTable[7] - assm_build_version
				// polTable[5] - chromosome
				// polTable[8] - assm_load_start
				// polTable[9] - assm_load_end
				else if (line.startsWith("CTG")) {

					if (firstRes == 0) {
						String[] aux = new String[17];
						aux = line.split(" | "); // dividir line

						String assembly = aux[2];
						String chr = aux[4];
						String chr_pos = aux[6];
						String ctg_start = aux[10];

						int pos; // posicao

						// [parsers]
						pos = assembly.indexOf('=');
						assembly = assembly.substring(pos + 1);
						// armazenar
						polTable[7] = assembly;

						pos = chr.indexOf('=');
						chr = chr.substring(pos + 1);
						// armazenar
						polTable[5] = chr;

						pos = chr_pos.indexOf('=');
						chr_pos = chr_pos.substring(pos + 1);

						pos = ctg_start.indexOf('=');
						ctg_start = ctg_start.substring(pos + 1);

						// armazenar
						polTable[6] = chr_pos;
						polTable[8] = ctg_start;
						polTable[9] = ctg_start;
						firstRes = 1;
						// [/parsers]
					} // IF firstRes
				}

				// polTable[12] - geneID
				// polTable[2] - sub_kind
				else if (line.startsWith("LOC")) {
					String[] aux = new String[6];
					aux = line.split(" | "); // dividir line

					String gene_name = aux[2];
					String locus_id = aux[4];
					String fxn_class = aux[6];

					int pos; // posicao

					// [parsers]
					pos = locus_id.indexOf('=');
					locus_id = locus_id.substring(pos + 1);
					// armazenar
					polTable[12] = locus_id;

					pos = fxn_class.indexOf('=');
					fxn_class = fxn_class.substring(pos + 1);
					// armazenar
					polTable[2] = fxn_class;

					// armazenar
					polTable[13] = gene_name;
					// [/parsers]
				}
			} while (reader.ready());
			reader.close();

		} catch (MalformedURLException e) {
			System.err.println("ERROR 1: ERROR READING FLT URL: MalformedURLException - readFLT()");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR 2: IOException - readFLT()");
			e.printStackTrace();
		}
	} // end readFLT()

	/**
	 * ler arquivo XML, armazenando os dados:
	 */
	public void readXML() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(false);
		DocumentBuilder docBuilder;
		Element AssemblyTag;
		Element ComponentTag;
		Element MapLocTag;
		Element FxnSetTag;

		try {
			docBuilder = dbf.newDocumentBuilder();
			URL url = new URL(makeURL(id, "XML"));
			Document doc = docBuilder.parse(url.openStream());
			doc.getDocumentElement().normalize();

			// pegar a raiz
			Element rootTag = doc.getDocumentElement();

			Element rsTag = (Element) rootTag.getElementsByTagName("Rs").item(0);
			// coord_relative_gene //EDITAR CONFORME VISTO
			int i = 0;
			Element hgvsTag;
			String coord_relative_gene;
			do {
				try {
					hgvsTag = (Element) rsTag.getElementsByTagName("hgvs").item(i);
					coord_relative_gene = hgvsTag.getTextContent();
				} catch (Exception e) {
					coord_relative_gene = null;
				}
				i++;
			} while (coord_relative_gene != null && !coord_relative_gene.startsWith("NM_"));
			coord_relative_gene = process_coord_relative_gene(coord_relative_gene);

			try {
				polTable[1] = rsTag.getAttribute("snpClass");
			} catch (Exception e) {
				polTable[1] = null;
			}

			polTable[4] = coord_relative_gene;

			try {
				// value
				Element hetTag = (Element) rsTag.getElementsByTagName("Het").item(0);
				polTable[14] = hetTag.getAttribute("value");
			} catch (Exception e) {
				polTable[14] = null;
			}

			try {
				// ancestralAllele
				Element SequenceTag = (Element) rsTag.getElementsByTagName("Sequence").item(0);
				polTable[15] = SequenceTag.getAttribute("ancestralAllele");
				if (polTable[15].equals("")) {
					polTable[15] = null;
				}
			} catch (Exception e) {
				polTable[15] = null;
			}

			try {
				// orientation
				AssemblyTag = (Element) rsTag.getElementsByTagName("Assembly").item(0);
				ComponentTag = (Element) AssemblyTag.getElementsByTagName("Component").item(0);

				polTable[16] = ComponentTag.getAttribute("orientation");
			} catch (Exception e) {
				polTable[16] = null;
			}

			try {
				// symbol - mrnaAcc - mrnaVer *symbol no FLT
				AssemblyTag = (Element) rsTag.getElementsByTagName("Assembly").item(0);
				ComponentTag = (Element) AssemblyTag.getElementsByTagName("Component").item(0);
				MapLocTag = (Element) ComponentTag.getElementsByTagName("MapLoc").item(0);
				FxnSetTag = (Element) MapLocTag.getElementsByTagName("FxnSet").item(0);

				polTable[17] = FxnSetTag.getAttribute("mrnaAcc");
			} catch (Exception e) {
				polTable[17] = null;
			}

			try {
				AssemblyTag = (Element) rsTag.getElementsByTagName("Assembly").item(0);
				ComponentTag = (Element) AssemblyTag.getElementsByTagName("Component").item(0);
				MapLocTag = (Element) ComponentTag.getElementsByTagName("MapLoc").item(0);
				FxnSetTag = (Element) MapLocTag.getElementsByTagName("FxnSet").item(0);

				polTable[18] = FxnSetTag.getAttribute("mrnaVer");
			} catch (Exception e) {
				polTable[18] = null;
			}

			try {
				// freq - allele (reference_value)
				Element FrequencyTag = (Element) rsTag.getElementsByTagName("Frequency").item(0);
				polTable[19] = FrequencyTag.getAttribute("freq");
			} catch (Exception e) {
				polTable[19] = null;
			}

			try {
				// freq - allele (reference_value)
				Element FrequencyTag = (Element) rsTag.getElementsByTagName("Frequency").item(0);
				polTable[20] = FrequencyTag.getAttribute("allele");
			} catch (Exception e) {
				polTable[20] = null;
			}

		} catch (ParserConfigurationException e) {
			System.err.println("ERROR 3: ParserConfigurationException - readXML()");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.err.println("ERROR 4: ERROR READING XML URL: MalformedURLException - readXML()");
			e.printStackTrace();
		} catch (SAXException e) {
			/*
			 * Entrar nessa excecao se acontecer algum erro ao abrir a p\E1gina
			 * XML. Caso isso ocorra, polTable[4] = "<ERROR>"
			 */
			System.err.println("ERROR 5: SAXException - readXML()");
			System.err.println("polTable[4] setting to <ERROR> ...");
			polTable[4] = "<ERROR>";
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR 6: IOException - readXML()");
			e.printStackTrace();
		}
	} // end readXML()

	// ---

	/**
	 * processa o atributo coord_relative_gene para pegar so a ultima parte
	 * 
	 * @param coord_relative_gene
	 */
	public String process_coord_relative_gene(String coord_relative_gene) {
		if (coord_relative_gene != null) {
			String aux = coord_relative_gene.split(":")[1];
			aux = aux.substring(0, aux.length() - 3);
			return aux;
		}
		return null;
	}

	// ---
	/**
	 * Atualizar a posicao 0 da tabela com o ID
	 * 
	 * @param id
	 */
	public void setId(int id) {
		// polTable[0] - id
		this.id = id;
		polTable[0] = "" + this.id;
	}

	/**
	 * Retorna a URL de acordo com os dados inseridos
	 * 
	 * @param id
	 * @param report
	 * @return
	 */
	public String makeURL(int id, String report) {
		return "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=snp&id=" + id + "&report=" + report;
	}

} // end class UrlFilter
