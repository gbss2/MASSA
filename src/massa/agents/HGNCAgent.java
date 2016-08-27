/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  PharmgkbAgent.java
#  -----------------
#
# Original Author: Guilherme Kingman
# Contributor(s): Maira Ribeiro Rodrigues
# Updated by (and date):
#
# Command line:  
#
# Dependencies:  JADE jar files (jade.jar,commons-codec-1.3.jar)
#				 Perl Modules: SOAP-Lite, Class-Inspector
#							   (they can be found at http://search.cpan.org)
#	
# Description: Database agent that searches online database PharmGKB.
#
#/
*/
package massa.agents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Enumeration;
import java.util.Iterator;
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
import massa.biodata.HUGO;

public class HGNCAgent extends DBagent {
	/* Atributes */
	String path;
	String userdir;
	String service;
	// Hashtable<Info, Info> hash;
	BioData hugodata;

	/* Contructor */
	public HGNCAgent() {
		super();

		// compatibility attributes
		// Initializing attributes
		slash = System.getProperty("file.separator");
		os = System.getProperty("os.name");
		System.out.println("OS is " + os);

		this.setDBname("hugo");
		this.setInformation("hugo");
		this.userdir = System.getProperty("user.dir");
		if (os.equals("Linux")) {
			this.path = this.userdir + slash + "divergenomenrich" + slash + "scripts-PharmGKB" + slash;
		} else {
			this.path = this.userdir + slash + "scripts-pharmgkb" + slash;
		}
		this.service = "hugoPerl.pl";
		// this.hash = new Hashtable<Info, Info>();
		this.hugodata = new BioData("hugo");

	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		/* Add a behaviour */
		addBehaviour(new waitRequest());
	}

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
					addBehaviour(new hugoAction(sender, contentdata));
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
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
				msg.setContentObject(hugodata);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	class hugoAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public hugoAction(AID pa, BioData bd) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessHUGO(bd), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}

	}

	class accessHUGO extends ParallelBehaviour {
		BioData contentdata;

		public accessHUGO(BioData bd) {
			super(WHEN_ALL);
			contentdata = bd;
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
				this.addSubBehaviour(new remoteAccessHUGO(snp, gene));
			}

		}

		public int onEnd() {
			// printHash(hash);
			System.out.println("Agent " + getLocalName() + " finished.");
			return super.onEnd();
		}

	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class remoteAccessHUGO extends OneShotBehaviour {

		private Process proc;
		private BufferedReader buffer;

		private HUGO hugo;

		private String snpid;

		private String hgncId;
		private String geneSymbol;

		private boolean errflag;

		/* Constructor */
		public remoteAccessHUGO(String s, String g) {
			this.snpid = s;
			this.geneSymbol = g;
			errflag = false;
			hugo = hugodata.createHUGOInstance();

		}

		public Process setProc(String path, String service, String input) {
			try {
				// System.out.println("perl " + path + service + " " + input);
				return Runtime.getRuntime().exec("perl " + path + service + " " + input);
			} catch (IOException e) {
				System.out.println("Null process");
				e.printStackTrace();
				return null;
			}
		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " executing request for " + geneSymbol + "...");

			this.proc = setProc(path, service, geneSymbol);
			this.buffer = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			// this.hash = hash;

			try {

				String line = null;
				String[] split = null;
				// int count = 0; // contador de linhas (para testes)
				// Info info_key = null;
				// Info info_value = null;
				do {
					line = buffer.readLine();

					// System.out.println("HUGO Response:"+line);

					// continua se linha não for vazia e não tiver
					if (line != null && !line.equals("")) {

						// if (!line.equals("")) {
						// System.out.println("Pre split Line:"+line);
						split = line.split("\\t");
						// System.out.println("tamanho linha:"+split.length);
						hugo.setHgncId(split[0]);
						hugo.setGeneSymbol(split[1]);
						hugo.setHgGeneName(split[2]);
						hugo.setGeneSynonyms(split[8]);
						hugo.setLocusType(split[4]);
						hugo.setLocusGroup(split[5]);
						if (split[24].isEmpty()) {
							hugo.setGeneFamilyTag(null);
						} else {
							hugo.setGeneFamilyTag(split[24]);
						}
						;
						hugo.setGeneFamily(split[25]);

						// Gene Databases
						hugo.setSpecialistDBLinks(split[20]);
						hugo.setLocusSpecDB(split[31]);

						// Correspondence IDs
						try {
							hugo.setEnzymeId(split[16]);
						} catch (Exception e) {
							hugo.setEnzymeId("null");
						}

						try {
							hugo.setEntrezId(split[17]);
						} catch (Exception e) {
							hugo.setEntrezId("null");
						}
						try {
							hugo.setEnsemblId(split[18]);
						} catch (Exception e) {
							hugo.setEnsemblId("null");
						}

						try {
							hugo.setPubMedIds(split[22]);
						} catch (Exception e) {
							hugo.setPubMedIds("null");
						}

						try {
							hugo.setRefSeqIds(split[23]);
						} catch (Exception e) {
							hugo.setRefSeqIds("null");
						}

						try {
							hugo.setCCDSIds(split[29]);
						} catch (Exception e) {
							hugo.setCCDSIds("null");
						}

						try {
							hugo.setVegaIds(split[30]);
						} catch (Exception e) {
							hugo.setVegaIds("null");
						}

						try {
							hugo.setOmimId(split[33]);
						} catch (Exception e) {
							hugo.setOmimId("null");
						}

						try {
							hugo.setUniProtId(split[35]);
						} catch (Exception e) {
							hugo.setUniProtId("null");
						}

						try {
							hugo.setUcscId(split[37]);
						} catch (Exception e) {
							hugo.setUcscId("null");
						}

						try {
							hugo.setMouseGdbId(split[38]);
						} catch (Exception e) {
							hugo.setMouseGdbId("null");
						}

						try {
							hugo.setRatGdbId(split[39]);
						} catch (Exception e) {
							hugo.setRatGdbId("null");
							// System.out.println("Set!");
						}

						hugo.setGeneSymbol(geneSymbol);
						hugo.setPolymorphismCode(snpid);

						synchronized (hugodata) {
							hugodata.setHUGOList(hugo);
							// System.out.println("Sync!");
						}

						// } // End of Inner IF

					} else {
						System.out.println("No response from HGNC server");
						errflag = true;
					} // End of ELSE IF

				} while (buffer.ready());
				// l_printHash();
				// System.out.println("...");

				// System.out.println("Printing result for HUGO object:");
				// System.out.println("HGNC = Symbol: "+hugo.getGeneSymbol()+"
				// Name: "+hugo.getHgGeneName()+" Locus Type:
				// "+hugo.getLocusType()+" Group: "+hugo.getLocusGroup()+"
				// Synonyms: "+hugo.getGeneSynonyms()+" Tag:
				// "+hugo.getGeneFamilyTag()+" Family: "+hugo.getGeneFamily()+"
				// DBs: "+hugo.getSpecialistDBLinks());

			} catch (IOException e) {
				// waitfor
				System.out.println("Exception 1");
				e.printStackTrace();
			} // Catch End

		}// end ACTION

	} // end BEHAVIOUR

} // End of Class HGNC
