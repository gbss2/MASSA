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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
//import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import massa.biodata.OMIM;

@SuppressWarnings("serial")

public class omimAgent extends DBagent {

	BioData omimdata;

	/* Constructor */
	public omimAgent() {
		super();
		this.setDBname("omim");
		this.setInformation("omim");
		this.omimdata = new BioData("omim");
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
					addBehaviour(new omimAction(sender, msgcontent));

				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	} // End of class wait request

	class omimAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public omimAction(AID pa, BioData bd) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessOmimAPI(bd), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}

	} // End of class omimAction

	class accessOmimAPI extends ParallelBehaviour {
		private BioData contentdata;

		public accessOmimAPI(BioData bd) {
			super(WHEN_ALL);
			this.contentdata = bd;

		}

		public void onStart() {

			// read hash{snp,gene} and pass on to remoteAccess
			Set<String> keyset;
			keyset = contentdata.snp_gene.keySet();
			Iterator<String> itr = keyset.iterator();

			while (itr.hasNext()) {
				String snp = itr.next();
				String gene = contentdata.snp_gene.get(snp);
				// System.out.println("key:"+snp+"value:"+gene);
				this.addSubBehaviour(new remoteAccessOmimAPI(snp, gene));
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished.");
			// annmyconnection.mysqlDisconnect();
			return super.onEnd();
		}

	} // End of class access omim API

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class remoteAccessOmimAPI extends OneShotBehaviour {

		private String snpid;
		private String genesymbol;
		private boolean errflag;
		private OMIM omimobject;
		UrlFilter urlfilter;

		public remoteAccessOmimAPI(String s, String g) {

			this.snpid = s;
			this.genesymbol = g;
			this.urlfilter = new UrlFilter(this.genesymbol);
			this.omimobject = omimdata.createOMIMInstance();
		}

		public void action() {

			omimobject = urlfilter.readXML();
			omimobject.setGenesymbol(genesymbol);
			omimobject.setPolymorphismCode(snpid);

			synchronized (omimdata) {
				omimdata.setOMIMList(omimobject);
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

			System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
			msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(msgreceiver);
			msg.setLanguage("English");
			try {
				msg.setContentObject(omimdata);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myAgent.send(msg);

		}
	}// END SENDMSGTOPROVIDER CLASS

	class UrlFilter {

		/* Atributos */
		String geneName;
		BioData bioobjects;
		OMIM omimobject;

		/* Constructor */
		public UrlFilter(String g) {

			// create instance of BioData and OMIM objects
			this.geneName = g;
			bioobjects = new BioData("omim");
			omimobject = bioobjects.createOMIMInstance();

		}

		// public int getPolTableSize(){
		// return polTable.length;
		// }

		/**
		 * ler arquivo XML, armazenando os dados:
		 */
		public OMIM readXML() {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder docBuilder;
			try {
				docBuilder = dbf.newDocumentBuilder();
				URL url = new URL(makeURL(geneName, "XML"));
				// System.out.println(url);
				Document doc = docBuilder.parse(url.openStream());
				doc.getDocumentElement().normalize();

				// System.out.println("Root element :" +
				// doc.getDocumentElement().getNodeName());

				NodeList searchList = doc.getElementsByTagName("searchResponse");

				for (int temp = 0; temp < searchList.getLength(); temp++) {

					Node searchNode = searchList.item(temp);

					// System.out.println("\nCurrent Element :" +
					// searchNode.getNodeName());

					if (searchNode.getNodeType() == Node.ELEMENT_NODE) {

						Element searchElement = (Element) searchNode;

						// Get Gene Symbols
						try {
							// System.out.println("OMIM
							// Gene:"+searchElement.getElementsByTagName("geneSymbols").item(0).getTextContent());
							omimobject.setGenesymbol(
									searchElement.getElementsByTagName("geneSymbols").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setGenesymbol("null");
						}

						// Get Cytogenetic Location
						try {
							// System.out.println("OMIM
							// cytoLoc:"+searchElement.getElementsByTagName("cytoLocation").item(0).getTextContent());
							omimobject.setCytoloc(
									searchElement.getElementsByTagName("cytoLocation").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setCytoloc("null");
						}

						// Get Gene Status - C,P,L,I
						try {
							// System.out.println("OMIM Gene
							// Status:"+searchElement.getElementsByTagName("confidence").item(0).getTextContent());
							omimobject.setGenestatus(
									searchElement.getElementsByTagName("confidence").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setGenestatus("null");
						}

						// Get Method for Mapping Genes
						try {
							// System.out.println("OMIM
							// Mapping:"+searchElement.getElementsByTagName("mappingMethods").item(0).getTextContent());
							omimobject.setGenemapmethods(
									searchElement.getElementsByTagName("mappingMethods").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setGenemapmethods("null");
						}

						// Get Disorders
						try {
							// System.out.println("OMIM
							// Disorder:"+searchElement.getElementsByTagName("phenotype").item(0).getTextContent());
							omimobject.setDisorderItem(
									searchElement.getElementsByTagName("phenotype").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setDisorderItem("null");
						}

						// Get OMIM ID
						try {
							// System.out.println("OMIM
							// ID:"+searchElement.getElementsByTagName("mimNumber").item(0).getTextContent());
							omimobject.setMimIDItem(
									searchElement.getElementsByTagName("mimNumber").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setMimIDItem("null");
						}

						// Get Comments
						try {
							// System.out.println("OMIM
							// Comments:"+searchElement.getElementsByTagName("comments").item(0).getTextContent());
							omimobject.setCommentsItem(
									searchElement.getElementsByTagName("comments").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setCommentsItem("null");
						}

						// Get Inheritance
						try {
							// System.out.println("OMIM
							// Inheritance:"+searchElement.getElementsByTagName("geneInheritance").item(0).getTextContent());
							omimobject.setInheritanceItem(
									searchElement.getElementsByTagName("geneInheritance").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setInheritanceItem("null");
						}

						// Get Phenotype Mapping Method
						try {
							// System.out.println("OMIM Map
							// Methods:"+searchElement.getElementsByTagName("phenotypeMappingKey").item(0).getTextContent());
							omimobject.setPhenoMapMethodsItem(
									searchElement.getElementsByTagName("phenotypeMappingKey").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setPhenoMapMethodsItem("null");
						}

						// Get References
						try {
							// System.out.println("OMIM
							// References:"+searchElement.getElementsByTagName("references").item(0).getTextContent());
							omimobject.setReferencesItem(
									searchElement.getElementsByTagName("references").item(0).getTextContent());
						} catch (Exception e) {
							omimobject.setReferencesItem("null");
						}

					} // End of IF
				} // End of For

			} catch (ParserConfigurationException e) {
				System.err.println("ERROR 3: ParserConfigurationException - readXML()");
				e.printStackTrace();
			} catch (MalformedURLException e) {
				System.err.println("ERROR 4: ERROR READING XML URL: MalformedURLException - readXML()");
				e.printStackTrace();
			} catch (SAXException e) {
				/*
				 * Entrar nessa excecao se acontecer algum erro ao abrir a
				 * pagina XML. Caso isso ocorra, polTable[4] = "<ERROR>"
				 */
				System.err.println("ERROR 5: SAXException - readXML()");
				System.err.println("polTable[4] setting to <ERROR> ...");
				omimobject.setGenemapmethods("<ERROR>");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("ERROR 6: IOException - readXML()");
				e.printStackTrace();
			}

			return omimobject;
		} // end readXML()

		/**
		 * Retorna a URL de acordo com os dados inseridos
		 * 
		 * @param id
		 * @param report
		 * @return
		 */
		public String makeURL(String geneName, String report) {
			return "http://api.omim.org/api/geneMap/search?search=" + geneName
					+ "&filter=&fields=&retrieve=&start=0&limit=10&sort=&operator=&include=all&format=xml&apiKey=0807D10B6344A56E6C07AC19A6AF5A1957C6B92C";
		}

	} // end class UrlFilter

} // End of class OMIM Agent
