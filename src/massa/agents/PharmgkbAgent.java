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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import jade.core.AID;
//import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
//import jade.domain.DFService;
//import jade.domain.FIPAException;
//import jade.domain.FIPAAgentManagement.DFAgentDescription;
//import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioData;
import massa.biodata.PharmGKB;
import massa.util.Info;

public class PharmgkbAgent extends DBagent {
	/* Atributes */
	String path;
	String userdir;
	String service;
	Hashtable<Info, Info> hash;
	BioData pharmdata;

	/* Contructor */
	public PharmgkbAgent() {
		super();

		// compatibility attributes
		// Initializing attributes
		slash = System.getProperty("file.separator");
		os = System.getProperty("os.name");
		System.out.println("OS is " + os);

		this.setDBname("dbpharmgkb");
		this.setInformation("pharmgkb");
		this.userdir = System.getProperty("user.dir");
		if (os.equals("Linux")) {
			this.path = this.userdir + slash + "divergenomenrich" + slash + "scripts-PharmGKB" + slash;
		} else {
			this.path = this.userdir + slash + "scripts-pharmgkb" + slash;
		}
		this.service = "search.pl";
		this.hash = new Hashtable<Info, Info>();
		this.pharmdata = new BioData("pharmgkb");

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
					addBehaviour(new PharmgkbAction(sender, contentdata));
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
				msg.setContentObject(pharmdata);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	class PharmgkbAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public PharmgkbAction(AID pa, BioData bd) {

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessPharmgkb(bd), STATE_A);
			this.registerLastState(new SendReply(pa), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}

	}

	class accessPharmgkb extends ParallelBehaviour {
		BioData contentdata;

		public accessPharmgkb(BioData bd) {
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
				this.addSubBehaviour(new remoteAccessPharmgkb(snp, gene));
			}

		}

		public int onEnd() {
			// printHash(hash);
			System.out.println("Agent " + getLocalName() + " finished.");
			return super.onEnd();
		}

	}

	// DB ACCESS IS IMPLEMENTED HERE!!!
	class remoteAccessPharmgkb extends OneShotBehaviour {
		private Process proc;
		private BufferedReader buffer;
		private PharmGKB ph;
		private String snpid;
		private String genesymbol;
		private String key;
		private String type;
		private String value;
		private boolean errflag;

		/* Constructor */
		public remoteAccessPharmgkb(String s, String g) {
			this.snpid = s;
			this.genesymbol = g;
			errflag = false;
			ph = pharmdata.createPharmGKBInstance();

		}

		public Process setProc(String path, String service, String input) {
			try {
				// System.out.println("perl " + path + service + " " + input);
				return Runtime.getRuntime().exec("perl " + path + service + " " + input);
			} catch (IOException e) {
				System.out.println("Processo nulo");
				e.printStackTrace();
				return null;
			}
		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " executing request for " + genesymbol + "...");

			this.proc = setProc(path, service, genesymbol);
			this.buffer = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			// this.hash = hash;

			try {

				String line = null;
				String[] split = null;
				int count = 0; // contador de linhas (para testes)
				Info info_key = null;
				Info info_value = null;
				do {
					line = buffer.readLine();

					// System.out.println("Linha:"+line);
					// continua se linha não for vazia e não tiver
					if (line != null) {
						if (!line.equals("") && !line.equals("No response from server")) {
							split = line.split(", ");
							type = split[1];
							// filtrar os tipos que irão ser guardados no hash
							if (!type.equals("Literature")) {
								count++;
								if (type.equals("Gene")) {
									key = split[0];

									// pegar o atributo depois de /gene/xxxx
									int aux = 0;
									while (!split[aux].contains("/gene/")) {
										aux++;
									}
									value = split[aux + 1];
								} else if (type.equals("Golden Path Position")) {
									key = split[3].split("/")[2]; // tirar o
																	// /rsid/
									value = split[0];
								} else {
									key = split[0];
									value = split[2];
								}
								// System.out.println(type + "\t\t\t" + key +
								// "\t\t"
								// + value);
								info_key = new Info(genesymbol, key);
								info_value = new Info(type, value);

								synchronized (hash) {
									hash.put(info_key, info_value);
								}

								// System.out.println("type=="+type+"; Value is:
								// "+value);
								if (type.equals("Drug")) {
									ph.setDrugItem(value);

									// System.out.println("Set!");
								}
								if (type.equals("Disease")) {
									ph.setDiseaseItem(value);
									// System.out.println("Set!");
								}
								if (type.equals("Gene")) {
									ph.setGenexItem(value);
									// System.out.println("Set!");
								}
								if (type.equals("Pathway")) {
									ph.setPathwayItem(value);
									// System.out.println("Set!");
								}
							}

						} else if (line.equals("No response from server")) {
							System.out.println("No response from PGKB server");
							errflag = true;
						}
					}

				} while (buffer.ready());
				// l_printHash();
				// System.out.println("...");

				ph.setGenesymbol(genesymbol);
				ph.setPolymorphismCode(snpid);

				synchronized (pharmdata) {
					pharmdata.setPharmgkbList(ph);
				}

				// System.out.println("Printing result for pharmgkb object:");
				// Iterator<String> itp = ph.getPathwayList().iterator();
				// while(itp.hasNext()) {
				// String pw = (String)itp.next();
				// System.out.println("pathway:"+pw);
				// }
				// System.out.println("...");

				// Doesnt need to add genetosearch (or p) because the changes
				// are made in the original object.
				// if(!errflag){
				// synchronized(genedata){
				// genedata.setPolymorphismList(p);
				// }
				// }

			} catch (IOException e) {
				// waitfor
				System.out.println("Exception 1");
				e.printStackTrace();
			}

		}// end ACTION

	} // end BEHAVIOUR

	// imprimir para teste
	public static void printHash(Hashtable<Info, Info> hash) {
		Enumeration<Info> en = hash.keys();
		while (en.hasMoreElements()) {
			// nextElement is used to get key of Hashtable
			Info key = (Info) en.nextElement();
			// get is used to get value of the key in Hashtable
			Info value = (Info) hash.get(key);

			// System.out.println("key :" + key + "\t\tvalue :" + value);
		}
	}
} // end pharmgkb agent class
