package massa.agents;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jade.content.ContentException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.ContainerController;
import massa.agents.InterfaceAgent.writeSummary.MutableInt;
import massa.biodata.BioData;
import massa.biodata.BioDataLite;
import massa.biodata.Polymorphism;
import massa.database.MySQLcon;
import massa.enrichment.BioEnrichment;
import massa.enrichment.BioEnrichment2;
import massa.enrichment.ContingencyTable2x2;
import massa.enrichment.FishersExactTest;

public class InterfaceAgent extends Agent {

	/* Atributes */
	private String servicetype;
	private String infotype;
	long start;
	long end;
	long exectime;
	public String slash; // for Operating system control
	public String os; // for Operating system control (Linux,Windows)
	public String datapath;
	public String configpath;
	public String userdir;
	public String configfile;
	public String logfile;
	public String sumfile;
	public String sumfile2;
	private String datafilename;
	private String annotateddatafilename;
	private String enrichfile;
	private int analysistype;
	private int maxsizeforremoteannot;
	private int pquery;
	public List<String> rslist;
	// public BioData inputdata;
	// public BioDataLite inputdata;
	public List<Integer> searchidlist;
	public List<BioData> biodatalist;
	BufferedWriter efileWriter;

	// private int partnercount;
	private int requestcount;
	public int rscount;
	public int annrscount;
	public boolean inputerror;
	public boolean sizeerror;
	public ContainerController cc;

	/* Annotation DB information */
	public String sn = "localhost";
	public String dbname = "annotation";
	public String dbUser = "annotation";
	public String dbKey = "1masannotation1";
	MySQLcon myconnection;
	public Connection connection;

	/* Contructor */
	public InterfaceAgent() {
		// Start graphical interface
		// mainscreen = new Interface();

		Object[] args = getArguments();
		if (args != null) {
			cc = (ContainerController) args[0];
		}

		// inputdata = new BioDataLite();
		rslist = new ArrayList<String>();
		searchidlist = new ArrayList<Integer>();
		biodatalist = new ArrayList<BioData>();
		requestcount = 0;
		rscount = 0;
		annrscount = 0;
		maxsizeforremoteannot = 100;

		setServicetype("Interface");
		setInfoType("Interface");

		// compatibility attributes
		// Initializing attributes
		inputerror = false;
		sizeerror = false;
		slash = System.getProperty("file.separator");
		os = System.getProperty("os.name");
		this.userdir = System.getProperty("user.dir");
		this.datapath = userdir + slash + "data" + slash;
		this.configpath = userdir + slash + "config" + slash;

		this.configfile = this.configpath + "masconfig.txt";
		// this.logfile = this.datapath+"annotationlog.log";
	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		start = System.currentTimeMillis(); // Initialize Start Time
		System.out.println("Hello! MAS DiverEnrich is starting at " + start + " .........."); // Print
																								// Welcome
																								// Message
																								// and
																								// time

		/* Here Interface agent must register */
		this.register();

		/* Here Agent must connect to annotation db */
		myconnection = new MySQLcon(sn, dbname, dbUser, dbKey);

		/* Add a behaviour */
		addBehaviour(new GetInputAction());
		addBehaviour(new waitMsg());
	}

	protected void takeDown() {
		System.out.println("Agent" + getLocalName() + " shutdown.");
	}

	class waitMsg extends CyclicBehaviour {

		private AID sender;
		private String msgperformative;

		public waitMsg() {

		}

		public void action() {
			ACLMessage msg = myAgent.receive();

			if (msg != null) {
				sender = msg.getSender();
				msgperformative = msg.getPerformative(msg.getPerformative());

				if (msgperformative == "INFORM" && !sender.getLocalName().equals("ams")) {
					System.out.println("Agent " + getLocalName() + " received a " + msgperformative + " from agent "
							+ sender.getName());
					// COORDINATOR AGENT IS THE SENDER

					try {
						System.out.println("Sender is " + sender.getLocalName());
						if (msg.getContentObject() instanceof BioData) {
							BioData contentdata = (BioData) msg.getContentObject();
							biodatalist.add(contentdata);
							System.out.println("Agent " + getLocalName() + " biodatalist size is " + biodatalist.size()
									+ " | request count is " + getRequestCount());
							// generate output when all replies from coordinator
							// arrive
							if (biodatalist.size() == getRequestCount()) {
								addBehaviour(new GenerateAnnotationReport());

							}

						}

						if (msg.getContentObject() instanceof BioDataLite) {
							rmRequest();
							BioDataLite contentdata = (BioDataLite) msg.getContentObject();
							if (contentdata.getSearchidlist().size() > 0) {
								Iterator itr = contentdata.getSearchidlist().iterator();
								while (itr.hasNext()) {
									Integer sid = (Integer) itr.next();
									searchidlist.add(sid);
									// System.out.println("Agent " +
									// getLocalName() +" biodatalist size is
									// "+searchidlist.size()+" | searchid =
									// "+sid);
								}
							} else {
								searchidlist.add(contentdata.getSearchid());
								// System.out.println("Agent " + getLocalName()
								// +" biodatalist size is
								// "+searchidlist.size()+" | no ITR ");
							}
							// System.out.println("Agent " + getLocalName() +"
							// biodatalist size is "+searchidlist.size()+" |
							// request count is "+getRequestCount());
							// generate output when all replies from coordinator
							// arrive
							// if(getRequestCount() == 0){
							addBehaviour(new GenerateAnnotationReport());
							// }

						}

					} catch (UnreadableException e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("Agent " + getLocalName() + " received a msg from " + sender.getName());
				}
			} else {
				block();
			}
		}

	} // end waitReply

	private void addRequestCount() {
		this.requestcount++;
	}

	private void rmRequest() {
		this.requestcount--;
	}

	private int getRequestCount() {
		return this.requestcount;
	}

	class ShutDownPlatform extends OneShotBehaviour {

		public void action() {

			Action actExpr = new Action();
			actExpr.setActor(getAMS());
			actExpr.setAction(new ShutdownPlatform());

			System.out.println("Agent " + getLocalName() + " shutting down the agent platform.");

			SLCodec codec = new SLCodec();
			getContentManager().registerOntology(JADEManagementOntology.getInstance());
			getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL0);

			ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
			request.addReceiver(getAMS());
			request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
			request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
			request.setOntology(JADEManagementOntology.NAME);

			try {
				getContentManager().fillContent(request, actExpr);
				send(request);
			} catch (ContentException ce) {
				// Should never happen
				ce.printStackTrace();
			}
		} // End of public void shutdownPlatform/action

	} // End of class ShutDownPlatform

	class GenerateAnnotationReport extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";
		private static final String STATE_C = "C";
		private static final String STATE_D = "D";
		private static final String STATE_E = "E";

		public GenerateAnnotationReport() {

			System.out.println("Agent " + getLocalName() + " generating annotation report ...");

			if (getAnalysistype() == 1 || getAnalysistype() == 3) { /* Local */
				this.registerFirstState(new writeAnnotatedDB(), STATE_A);
				this.registerState(new writeSummary(), STATE_B);
				this.registerState(new cleanDB(), STATE_C);
				this.registerState(new writeLogFile(), STATE_D);
				this.registerLastState(new ShutDownPlatform(), STATE_E);

				this.registerDefaultTransition(STATE_A, STATE_B);
				this.registerDefaultTransition(STATE_B, STATE_C);
				this.registerDefaultTransition(STATE_C, STATE_D);
				this.registerDefaultTransition(STATE_B, STATE_D);
				this.registerDefaultTransition(STATE_D, STATE_E);

				// this.registerDefaultTransition(STATE_A, STATE_C);
				// this.registerDefaultTransition(STATE_C, STATE_D);
				// this.registerDefaultTransition(STATE_D, STATE_E);

			}
			if (getAnalysistype() == 2 || getAnalysistype() == 4) { /* Remote */
				this.registerFirstState(new writeAnnotatedDataFile(), STATE_A);
				this.registerState(new clearBDObjects(), STATE_B);
				this.registerState(new writeLogFile(), STATE_C);
				this.registerState(new writeSummary(), STATE_D);
				this.registerLastState(new ShutDownPlatform(), STATE_E);

				this.registerDefaultTransition(STATE_A, STATE_B);
				this.registerDefaultTransition(STATE_B, STATE_C);
				this.registerDefaultTransition(STATE_C, STATE_D);
				this.registerDefaultTransition(STATE_D, STATE_E);
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished task.");
			return super.onEnd();
		}
	}

	class GetInputAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";
		private static final String STATE_C = "C";
		private static final String STATE_D = "D";
		private static final String STATE_E = "E";

		public GetInputAction() {

			System.out.println("Agent " + getLocalName() + " executing request...");
			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new readConfigFile(), STATE_A);
			this.registerState(new readDataFile(), STATE_B);
			this.registerState(new preclearDB(), STATE_C);
			this.registerState(new sendAnnotationRequest(), STATE_D);
			this.registerLastState(new waitMsg(), STATE_E);

			this.registerDefaultTransition(STATE_A, STATE_B);
			this.registerDefaultTransition(STATE_B, STATE_C);
			this.registerDefaultTransition(STATE_C, STATE_D);
			// this.registerDefaultTransition(STATE_B,STATE_D);
			this.registerDefaultTransition(STATE_D, STATE_E);
		}

		public int onEnd() {
			return super.onEnd();
		}
	}

	class sendAnnotationRequest extends OneShotBehaviour {

		private int rssizeperrequest;
		private int requestsum;

		public sendAnnotationRequest() {
			requestsum = 0;
		}

		public void action() {

			if (inputerror || sizeerror) {

				if (inputerror) {
					System.out.println("Agent " + getLocalName()
							+ " is shutting down the platform due to WRONG SNP LIST INPUT FORMAT (EXISTING NON RS# SNPS)");
					ShutDownPlatform();
				}
				if (sizeerror) {
					System.out.println("Agent " + getLocalName()
							+ " is shutting down the platform due to TOO MANY SNPS FOR REMOTE ANNOTATION (>"
							+ maxsizeforremoteannot + "). TRY ANNOTATION TYPE 1 OR 3");
					ShutDownPlatform();
				}

			} else {
				rscount = rslist.size();
				System.out.println("Agent " + getLocalName() + " sending " + rscount + " SNPs in " + getPquery()
						+ " requests to Coordinator Agent.");

				// rssizeperrequest = Math.ceil(rscount/getPquery());
				// if(rssizeperrequest < 2){
				double bouble = Math.ceil((double) rscount / getPquery());
				rssizeperrequest = (int) bouble;
				// }

				// List<List<String>> lists = new ArrayList<List<String>>();

				for (int i = 0; i < rscount; i += rssizeperrequest) {

					List<String> rssublist = new ArrayList<String>();
					rssublist = rslist.subList(i, Math.min(i + rssizeperrequest, rscount));

					String[] rsarray = new String[rssublist.size()];

					if (getAnalysistype() == 1 || getAnalysistype() == 3) {
						BioDataLite inputdata = new BioDataLite();
						inputdata.setSnpRsList(rssublist.toArray(rsarray));
						inputdata.setAnalysistype(getAnalysistype());
						System.out.println("Agent " + getLocalName() + " sending " + rssublist.size()
								+ " SNPs to Coordinator Agent.");
						addBehaviour(new FindAndSend("coordinator", "REQUEST", inputdata));
					}

					if (getAnalysistype() == 2 || getAnalysistype() == 4) {
						BioData inputdata = new BioData();
						inputdata.setSnpRsList(rssublist.toArray(rsarray));
						inputdata.setAnalysistype(getAnalysistype());
						System.out.println("Agent " + getLocalName() + " sending " + rssublist.size()
								+ " SNPs to Coordinator Agent.");
						addBehaviour(new FindAndSend("coordinator", "REQUEST", inputdata));
					}

				}

				rslist.clear();
			}

		}

		public void ShutDownPlatform() {

			Action actExpr = new Action();
			actExpr.setActor(getAMS());
			actExpr.setAction(new ShutdownPlatform());

			System.out.println("Agent " + getLocalName() + " shutting down the agent platform.");

			SLCodec codec = new SLCodec();
			getContentManager().registerOntology(JADEManagementOntology.getInstance());
			getContentManager().registerLanguage(codec, FIPANames.ContentLanguage.FIPA_SL0);

			ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
			request.addReceiver(getAMS());
			request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
			request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
			request.setOntology(JADEManagementOntology.NAME);

			try {
				getContentManager().fillContent(request, actExpr);
				send(request);
			} catch (ContentException ce) {
				// Should never happen
				ce.printStackTrace();
			}

		} // End of class ShutDownPlatform

	}

	class writeLogFile extends OneShotBehaviour {

		BufferedWriter fileWriter;

		public writeLogFile() {

		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " writing log file ...");

			// Create timestamp for annotation run
			Calendar ts = Calendar.getInstance();
			String timestamp = "" + ts.get(ts.DAY_OF_MONTH) + "/" + (ts.get(ts.MONTH) + 1) + "/" + ts.get(ts.YEAR)
					+ " at " + ts.get(ts.HOUR) + ":" + ts.get(ts.MINUTE) + ":" + ts.get(ts.MILLISECOND);

			try {
				fileWriter = new BufferedWriter(new FileWriter(logfile));

				fileWriter.write("LOG FILE FOR MAS ANNOTATION SYSTEM");
				fileWriter.newLine();
				fileWriter.write("----------------------------------");
				fileWriter.newLine();
				fileWriter.write("Date and time: " + timestamp);
				fileWriter.newLine();
				fileWriter.write("Data file: " + datafilename);
				fileWriter.newLine();
				fileWriter.write("SNPs in file:" + rscount);
				fileWriter.newLine();
				fileWriter.write("SNPs annotated:" + annrscount);
				fileWriter.newLine();
				fileWriter.write("Execution time: " + exectime + " seconds");
				fileWriter.newLine();
				fileWriter.newLine();
				fileWriter.write("DATA PROVENANCE");
				fileWriter.newLine();
				fileWriter.write("---------------");
				fileWriter.newLine();
				fileWriter.write("Attribute \t Source \t Version");
				fileWriter.newLine();
				if (getAnalysistype() == 1) { /* local basic */
					Set<String> set;
					BioDataLite bd = new BioDataLite();
					bd.setBasicProvenance();
					set = bd.provenance_attrib.keySet();
					Iterator<String> itr = set.iterator();
					while (itr.hasNext()) {
						String attribute = itr.next();
						fileWriter.write(attribute + "\t" + bd.provenance_attrib.get(attribute) + "\t"
								+ bd.provenance_version.get(bd.provenance_attrib.get(attribute)));
						fileWriter.newLine();
					}
				}
				if (getAnalysistype() == 3) { /* local complete */
					Set<String> set;
					BioDataLite bd = new BioDataLite();
					bd.setCompleteProvenance();
					set = bd.provenance_attrib.keySet();
					Iterator<String> itr = set.iterator();
					while (itr.hasNext()) {
						String attribute = itr.next();
						fileWriter.write(attribute + "\t" + bd.provenance_attrib.get(attribute) + "\t"
								+ bd.provenance_version.get(bd.provenance_attrib.get(attribute)));
						fileWriter.newLine();
					}
				}

				if (getAnalysistype() == 2) { /* remote basic */
					Set<String> set;
					BioData bd = new BioData();
					bd.setBasicProvenance();
					set = bd.provenance_attrib.keySet();
					Iterator<String> itr = set.iterator();
					while (itr.hasNext()) {
						String attribute = itr.next();
						fileWriter.write(attribute + "\t" + bd.provenance_attrib.get(attribute) + "\t"
								+ bd.provenance_version.get(bd.provenance_attrib.get(attribute)));
						fileWriter.newLine();
					}
				}
				if (getAnalysistype() == 4) { /* local complete */
					Set<String> set;
					BioData bd = new BioData();
					bd.setCompleteProvenance();
					set = bd.provenance_attrib.keySet();
					Iterator<String> itr = set.iterator();
					while (itr.hasNext()) {
						String attribute = itr.next();
						fileWriter.write(attribute + "\t" + bd.provenance_attrib.get(attribute) + "\t"
								+ bd.provenance_version.get(bd.provenance_attrib.get(attribute)));
						fileWriter.newLine();
					}
				}

				fileWriter.close();

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}
		}

	}

	class readConfigFile extends OneShotBehaviour {

		BufferedReader inputStream;
		String[] values;

		public readConfigFile() {

		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " reading " + configfile + "...");

			try {
				inputStream = new BufferedReader(new FileReader(configfile));
				String l;
				while ((l = inputStream.readLine()) != null) {
					values = l.split("=");
					if (values[0].equals("analysis")) {
						setAnalysistype(Integer.parseInt(values[1]));
					}
					if (values[0].equals("datafile")) {
						setDatafilename(values[1]);
						// String[] names = values[1].split("\\.");
						setAnnotateddatafilename(values[1] + ".annotated");
						setLogfileName(values[1] + ".log");
						setSummaryfileName(values[1] + ".sum");
						setSummaryfileDBName(values[1] + ".DB.sum");
						setEnrichfileName(values[1] + ".enrich");
					}
					if (values[0].equals("pquery")) {
						setPquery(Integer.parseInt(values[1]));
					}

				}

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}
		}

	}

	class writeSummary extends OneShotBehaviour {
		BufferedReader inputStream;
		BufferedWriter fileWriter;
		String[] values;
		String[] values2;
		int gfTagSize = 0;
		int transRegSize = 0;
		int functionSize = 0;
		int pgkbPathSize = 0;
		int pgkbDiseaseSize = 0;
		int pgkbDrugSize = 0;
		int omimDisorderSize = 0;
		int omimCytoLocSize = 0;
		int goBioPSize = 0;
		int goCelCSize = 0;
		int goMolFSize = 0;
		int reactomePathSize = 0;

		// class MutableGene{
		//
		// }

		class MutableInt {

			// Surname Count
			int value = 1;

			public void increment() {
				++value;
			}

			public int get() {
				return value;
			}

			// Name List and Count
			List<String> gene = new ArrayList<String>();
			int geneCount = 0;

			public void incrementGene() {
				++geneCount;
			}

			public int getGeneCount() {
				return geneCount;
			}

			public void addGene(String name) {
				if (name != null && !gene.contains(name)) {
					gene.add(name);
					incrementGene();
				}
			}

			public String getGene() {
				return Arrays.toString(gene.toArray());
				// return gene.toString();
			}

			public String toString() {
				return (this.get() + "\t" + this.getGeneCount() + "\t" + this.getGene());
			}
		}

		Map<String, MutableInt> geneNameCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> fxnCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> chrCount = new HashMap<String, MutableInt>();

		Map<String, MutableInt> functionCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> pathwayCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> drugCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> diseaseCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> goMolFunCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> goCelCompCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> goBioProCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> cytoLocCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> disorderCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> locTypeCount = new HashMap<String, MutableInt>();
		// Map<String, MutableInt> locGroupCount = new HashMap<String,
		// MutableInt>();
		Map<String, MutableInt> gfTagCount = new HashMap<String, MutableInt>();
		Map<String, MutableInt> reactomePathCount = new HashMap<String, MutableInt>();

		public writeSummary() {

		}

		public void action() {
			System.out.println("Agent " + getLocalName() + " writing annotation summary at " + sumfile + "...");

			// open annotation file for reading

			int curLine = 0;
			try {
				inputStream = new BufferedReader(new FileReader(datapath + annotateddatafilename));
				String l;
				while ((l = inputStream.readLine()) != null) {

					if (curLine++ < 1) {
						continue;
					}

					values = l.split("\t");
					String gn = values[2];
					String tr = values[4];
					String ch = values[6];

					// Count Gene Names
					MutableInt count1 = geneNameCount.get(gn);
					if (count1 == null) {
						geneNameCount.put(gn, new MutableInt());
					} else {
						count1.increment();
					}
					// Count Transcript Region
					MutableInt count2 = fxnCount.get(tr);
					if (count2 == null) {
						fxnCount.put(tr, new MutableInt());

					} else {
						count2.increment();
					}

					// Count Chromosomes
					MutableInt count3 = chrCount.get(ch);
					if (count3 == null) {
						chrCount.put(ch, new MutableInt());
					} else {
						count3.increment();
					}

					if (getAnalysistype() == 4
							|| getAnalysistype() == 3) { /* remote complete */

						String fun = values[21];
						String rpath = values[22];
						String path = values[23];
						String drug = values[24];
						String dise = values[25];
						String gomf = values[27];
						String gocc = values[28];
						String gobp = values[29];
						String cyto = values[30];
						String diso = values[33];
						String locT = values[42];
						// String locG = values[42];
						String gfTag = values[44];

						// Count UCSC function
						MutableInt count4 = functionCount.get(fun);
						if (count4 == null) {
							functionCount.put(fun, new MutableInt());
						} else {
							count4.increment();
						}
						// Count PGKB pathway
						values2 = path.split(";");
						for (String value : values2) {

							MutableInt count5 = pathwayCount.get(value);
							if (count5 == null) {
								pathwayCount.put(value, new MutableInt());
								pathwayCount.get(value).addGene(gn);
							} else {
								count5.increment();
								pathwayCount.get(value).addGene(gn);
							}
						}
						// Count PGKB drugs
						values2 = drug.split(";");
						for (String value : values2) {
							MutableInt count6 = drugCount.get(value);
							if (count6 == null) {
								drugCount.put(value, new MutableInt());
								drugCount.get(value).addGene(gn);
							} else {
								count6.increment();
								drugCount.get(value).addGene(gn);
							}
						}
						// Count PGKB disease
						values2 = dise.split(";");
						for (String value : values2) {
							MutableInt count7 = diseaseCount.get(value);
							if (count7 == null) {
								diseaseCount.put(value, new MutableInt());
								diseaseCount.get(value).addGene(gn);
							} else {
								count7.increment();
								diseaseCount.get(value).addGene(gn);
							}
						}
						// Count GO Mol Function
						values2 = gomf.split(";");
						for (String value : values2) {
							MutableInt count8 = goMolFunCount.get(value);
							if (count8 == null) {
								goMolFunCount.put(value, new MutableInt());
								goMolFunCount.get(value).addGene(gn);
							} else {
								count8.increment();
								goMolFunCount.get(value).addGene(gn);
							}
						}
						// Count GO Cel Comp
						values2 = gocc.split(";");
						for (String value : values2) {
							MutableInt count9 = goCelCompCount.get(value);
							if (count9 == null) {
								goCelCompCount.put(value, new MutableInt());
								goCelCompCount.get(value).addGene(gn);
							} else {
								count9.increment();
								goCelCompCount.get(value).addGene(gn);
							}
						}
						// Count GO Bio Proc
						values2 = gobp.split(";");
						for (String value : values2) {
							MutableInt count10 = goBioProCount.get(value);
							if (count10 == null) {
								goBioProCount.put(value, new MutableInt());
								goBioProCount.get(value).addGene(gn);
							} else {
								count10.increment();
								goBioProCount.get(value).addGene(gn);
							}
						}
						// Count OMIM CytoLoc
						MutableInt count11 = cytoLocCount.get(cyto);
						if (count11 == null) {
							cytoLocCount.put(cyto, new MutableInt());
							cytoLocCount.get(cyto).addGene(gn);
						} else {
							count11.increment();
							cytoLocCount.get(cyto).addGene(gn);
						}

						// Count HGNC Locus Type
						MutableInt count13 = locTypeCount.get(locT);
						if (count13 == null) {
							locTypeCount.put(locT, new MutableInt());
							locTypeCount.get(locT).addGene(gn);

						} else {
							count13.increment();
							locTypeCount.get(locT).addGene(gn);
						}
						// Count HGNC Locus Group
						// MutableInt count14 = locGroupCount.get(locG);
						// if (count14 == null) {
						// locGroupCount.put(locG, new MutableInt());
						// }
						// else {
						// count14.increment();
						// }

						// Count HGNC FamilyTag
						MutableInt count15 = gfTagCount.get(gfTag);
						if (count15 == null) {
							gfTagCount.put(gfTag, new MutableInt());
							gfTagCount.get(gfTag).addGene(gn);
						} else {
							count15.increment();
							gfTagCount.get(gfTag).addGene(gn);
						}

						// Count OMIM Disorder
						values2 = diso.split(";");
						for (String value : values2) {
							MutableInt count12 = disorderCount.get(value);
							if (count12 == null) {
								disorderCount.put(value, new MutableInt());
								disorderCount.get(value).addGene(gn);
							} else {
								count12.increment();
								disorderCount.get(value).addGene(gn);
							}
						}

						// Count Reactome Pathway
						values2 = rpath.split(";");
						for (String value : values2) {
							MutableInt count16 = reactomePathCount.get(value);
							if (count16 == null) {
								reactomePathCount.put(value, new MutableInt());
								reactomePathCount.get(value).addGene(gn);
							} else {
								count16.increment();
								reactomePathCount.get(value).addGene(gn);
							}
						}

					} // End of IF analysis type = 3

				} // While END

				inputStream.close();

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}

			// open summary file for writing
			try {

				fileWriter = new BufferedWriter(new FileWriter(sumfile));

				// fileWriter.write("Gene Symbol Summary\n");
				// Iterator itr1 = geneNameCount.entrySet().iterator();
				Iterator<Entry<String, MutableInt>> itr0 = geneNameCount.entrySet().iterator();

				while (itr0.hasNext()) {

					Map.Entry<String, MutableInt> entry = itr0.next();
					fileWriter
							.write("Gene Symbol dbSNP\t" + entry.getKey() + "\t" + entry.getValue().get() + "\tnull\n");

				}

				// fileWriter.write("\nTranscript Region Summary\n");
				// Iterator itr2 = transRegCount.entrySet().iterator();
				Iterator<Entry<String, MutableInt>> itr2 = fxnCount.entrySet().iterator();

				while (itr2.hasNext()) {

					Map.Entry<String, MutableInt> entry = itr2.next();
					fileWriter.write(
							"Transcript Region dbSNP\t" + entry.getKey() + "\t" + entry.getValue().get() + "\tnull\n");
					transRegSize = transRegSize + Integer.valueOf(entry.getValue().get());

				}

				// fileWriter.write("\nChromosome Summary\n");
				// Iterator itr3 = chrCount.entrySet().iterator();
				Iterator<Entry<String, MutableInt>> itr3 = chrCount.entrySet().iterator();

				while (itr3.hasNext()) {

					Map.Entry<String, MutableInt> entry = itr3.next();
					fileWriter
							.write("Chromosome dbSNP\t" + entry.getKey() + "\t" + entry.getValue().get() + "\tnull\n");

				}

				if (getAnalysistype() == 4
						|| getAnalysistype() == 3) { /* remote complete */

					// fileWriter.write("\nUCSC Function Summary\n");
					Iterator<Entry<String, MutableInt>> itr4 = functionCount.entrySet().iterator();

					while (itr4.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr4.next();
						fileWriter.write("Transcript region UCSC\t" + entry.getKey() + "\t" + entry.getValue().get()
								+ "\tnull\n");

					}

					Iterator<Entry<String, MutableInt>> itr16 = reactomePathCount.entrySet().iterator();

					while (itr16.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr16.next();
						fileWriter.write("Reactome Pathway\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						reactomePathSize = reactomePathSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nPGKB Pathway Summary\n");
					Iterator<Entry<String, MutableInt>> itr5 = pathwayCount.entrySet().iterator();

					while (itr5.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr5.next();
						fileWriter.write("Pathway PharmGKB\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						pgkbPathSize = pgkbPathSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nPGKB Drugs Summary\n");
					Iterator<Entry<String, MutableInt>> itr6 = drugCount.entrySet().iterator();

					while (itr6.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr6.next();
						fileWriter.write("Drugs PharmGKB\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						pgkbDrugSize = pgkbDrugSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nPGKB Disease Summary\n");
					Iterator<Entry<String, MutableInt>> itr7 = diseaseCount.entrySet().iterator();

					while (itr7.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr7.next();
						fileWriter.write("Disease PharmGKB\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						pgkbDiseaseSize = pgkbDiseaseSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nGO Mollecular Function Summary\n");
					Iterator<Entry<String, MutableInt>> itr8 = goMolFunCount.entrySet().iterator();

					while (itr8.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr8.next();
						fileWriter.write("Molecular Function GO\t" + entry.getKey() + "\t" + entry.getValue().get()
								+ "\t" + entry.getValue().getGeneCount() + "\n");
						goMolFSize = goMolFSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nGO Cellular Component Summary\n");
					Iterator<Entry<String, MutableInt>> itr9 = goCelCompCount.entrySet().iterator();

					while (itr9.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr9.next();
						fileWriter.write("Cellular Component GO\t" + entry.getKey() + "\t" + entry.getValue().get()
								+ "\t" + entry.getValue().getGeneCount() + "\n");
						goCelCSize = goCelCSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nGO Biological Process Summary\n");
					Iterator<Entry<String, MutableInt>> itr10 = goBioProCount.entrySet().iterator();

					while (itr10.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr10.next();
						fileWriter.write("Biological Process PharmGKB\t" + entry.getKey() + "\t"
								+ entry.getValue().get() + "\t" + entry.getValue().getGeneCount() + "\n");
						goBioPSize = goBioPSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nOMIM CytoLoc Summary\n");
					Iterator<Entry<String, MutableInt>> itr11 = cytoLocCount.entrySet().iterator();

					while (itr11.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr11.next();
						fileWriter.write("CytoLocation OMIM\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						omimCytoLocSize = omimCytoLocSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nOMIM Disorders Summary\n");
					Iterator<Entry<String, MutableInt>> itr12 = disorderCount.entrySet().iterator();

					while (itr12.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr12.next();
						fileWriter.write("Disorders OMIM\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						omimDisorderSize = omimDisorderSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					// fileWriter.write("\nHGNC Locus Type Summary\n");
					Iterator<Entry<String, MutableInt>> itr13 = locTypeCount.entrySet().iterator();

					while (itr13.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr13.next();
						fileWriter.write("Locus Type HGNC\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");

					}

					// fileWriter.write("\nHGNC Locus Group Summary\n");
					// Iterator<Entry<String, MutableInt>> itr14 =
					// locGroupCount.entrySet().iterator();
					//
					// while(itr14.hasNext()){
					//
					// Map.Entry<String, MutableInt> entry = itr14.next();
					// fileWriter.write("Locus Group
					// HGNC\t"+entry.getKey()+"\t"+entry.getValue().get()+"\n");
					//
					// }

					// fileWriter.write("\nHGNC Gene Family Tag Summary\n");
					Iterator<Entry<String, MutableInt>> itr15 = gfTagCount.entrySet().iterator();

					while (itr15.hasNext()) {

						Map.Entry<String, MutableInt> entry = itr15.next();
						fileWriter.write("Gene Family HGNC\t" + entry.getKey() + "\t" + entry.getValue().get() + "\t"
								+ entry.getValue().getGeneCount() + "\n");
						gfTagSize = gfTagSize + Integer.valueOf(entry.getValue().getGeneCount());

					}

					System.out.println("ENRICHMENT TEST REMOTE");

					efileWriter = new BufferedWriter(new FileWriter(enrichfile));
					efileWriter.write(
							"Database\tTerm\tp-Value\tGene Population Count\tSNP Count\tGene Sample Count\tGene List\n");

					BioEnrichment hgncCount = new BioEnrichment();
					hgncCount.setHgncGeneFamilyCount();
					// Set<String> intersect = new
					// HashSet<String>(gfTagCount.keySet());
					// intersect.retainAll(hgncCount.getHgncGeneFamilyCount().keySet());

					System.out.println("Enrichment Test GeneFamily - Sample Matrix Size = " + gfTagCount.size());
					// efileWriter.write("HGNC Gene Family Enrichment Test\n");
					//
					EnrichmentTestRemote("HGNC Gene Family", gfTagCount, hgncCount.getHgncGeneFamilyCount(), gfTagSize);

					// HGNC Gene Family

					// BioEnrichment hgncCount = new BioEnrichment();
					// hgncCount.setHgncGeneFamilyCount();
					// Set<String> intersect = new
					// HashSet<String>(gfTagCount.keySet());
					// intersect.retainAll(hgncCount.getHgncGeneFamilyCount().keySet());
					//
					// System.out.println("Enrichment Test GeneFamily - Sample
					// Matrix Size = "+ gfTagCount.size()+" Population Size = "+
					// intersect.size());
					// efileWriter.write("HGNC Gene Family Enrichment Test\n");
					//
					// EnrichmentTestRemote(gfTagCount,hgncCount.getHgncGeneFamilyCount(),gfTagSize);

					// OMIM Disorder

					// BioEnrichment edisorderCount = new BioEnrichment();
					// edisorderCount.setomimDisorderCount();
					// intersect = new HashSet<String>(disorderCount.keySet());
					// intersect.retainAll(edisorderCount.getomimDisorderCount().keySet());

					// System.out.println("Enrichment Test OmimDisorder - Sample
					// Matrix Size = "+ disorderCount.size());
					// efileWriter.write("OMIM Disorder Enrichment Test\n");

					// EnrichmentTestRemote("OMIM
					// Disorder",disorderCount,edisorderCount.getomimDisorderCount(),omimDisorderSize);

					// OMIM MorbidMap

					BioEnrichment edisorderCount = new BioEnrichment();

					edisorderCount.setomimMorbidMapCount1();
					edisorderCount.setomimMorbidMapCount2();

					System.out.println("Enrichment Test OmimDisorder - Sample Matrix Size = " + disorderCount.size());
					// efileWriter.write("OMIM Disorder MorbidMap Enrichment
					// Test\n");

					EnrichmentTestRemote("OMIM MorbidMap Disorder", disorderCount,
							edisorderCount.getomimMorbidMapCount(), omimDisorderSize);

					// OMIM CytoLoc

					BioEnrichment ecytoLocCount = new BioEnrichment();
					ecytoLocCount.setomimCytoLocCount();
					// intersect = new HashSet<String>(cytoLocCount.keySet());
					// intersect.retainAll(ecytoLocCount.getomimCytoLocCount().keySet());

					System.out.println("Enrichment Test CytoLoc - Sample Matrix Size = " + disorderCount.size());
					// efileWriter.write("OMIM CytoLoc Enrichment Test\n");

					EnrichmentTestRemote("OMIM CytoLoc", cytoLocCount, ecytoLocCount.getomimCytoLocCount(),
							omimCytoLocSize);

					// dbSNP Transcript Region

					BioEnrichment etransRegCount = new BioEnrichment();
					etransRegCount.setdbsnpTransRegCount();
					// intersect = new HashSet<String>(fxnCount.keySet());
					// intersect.retainAll(etransRegCount.getdbsnpTransRegCount().keySet());

					// System.out.println("Enrichment Test TranscRegion - Sample
					// Matrix Size = "+ fxnCount.size());
					// efileWriter.write("dbSNP Transcript Region Enrichment
					// Test\n");

					// EnrichmentTestRemote("dbSNP Transcript
					// Region",fxnCount,etransRegCount.getdbsnpTransRegCount(),transRegSize);

					// Gene Ontology Molecular Function

					BioEnrichment egoMolFunCount = new BioEnrichment();
					egoMolFunCount.setgoMolFunctionCount();
					// intersect = new HashSet<String>(goMolFunCount.keySet());
					// intersect.retainAll(egoMolFunCount.getgoMolFunctionCount().keySet());

					System.out.println("Enrichment Test GOMolFunction - Sample Matrix Size = " + goMolFunCount.size());
					// efileWriter.write("GeneOntology Molecular Function
					// Enrichment Test\n");

					EnrichmentTestRemote("GO Molecular Function", goMolFunCount, egoMolFunCount.getgoMolFunctionCount(),
							goMolFSize);

					// Gene Ontology Biological Process

					BioEnrichment egoBioPCount = new BioEnrichment();
					egoBioPCount.setgoBioProcessCount1();
					egoBioPCount.setgoBioProcessCount2();
					egoBioPCount.setgoBioProcessCount3();
					// intersect = new HashSet<String>(goBioProCount.keySet());
					// intersect.retainAll(egoBioPCount.getgoBioProcessCount().keySet());

					System.out.println("Enrichment Test GOBioProcess - Sample Matrix Size = " + goBioProCount.size());
					// efileWriter.write("GeneOntology Biological Process
					// Enrichment Test\n");

					EnrichmentTestRemote("GO Bio Process", goBioProCount, egoBioPCount.getgoBioProcessCount(),
							goBioPSize);

					// Gene Ontology Cellular Component

					BioEnrichment egoCelCompCount = new BioEnrichment();
					egoCelCompCount.setgoCelCompCount();
					// intersect = new HashSet<String>(goCelCompCount.keySet());
					// intersect.retainAll(egoCelCompCount.getgoCelCompCount().keySet());

					System.out.println("Enrichment Test GOCelComp - Sample Matrix Size = " + goCelCompCount.size());
					// efileWriter.write("GeneOntology Cellular Component
					// Enrichment Test\n");

					EnrichmentTestRemote("GO Cellular Component", goCelCompCount, egoCelCompCount.getgoCelCompCount(),
							goCelCSize);

					// Reactome Pathways

					BioEnrichment erpathwayCount = new BioEnrichment();
					erpathwayCount.setreactomePathwayCount();
					// intersect = new HashSet<String>(pathwayCount.keySet());
					// intersect.retainAll(epathwayCount.getpgkbPathwayCount().keySet());

					System.out.println(
							"Enrichment Test Reactome Pathway - Sample Matrix Size = " + reactomePathCount.size());
					// efileWriter.write("PharmGKB Pathways Enrichment Test\n");

					EnrichmentTestRemote("Reactome Pathways", reactomePathCount,
							erpathwayCount.getreactomePathwayCount(), reactomePathSize);

					// PharmGKB Pathways

					BioEnrichment2 epathwayCount = new BioEnrichment2();
					epathwayCount.setpgkbPathwayCount();
					// intersect = new HashSet<String>(pathwayCount.keySet());
					// intersect.retainAll(epathwayCount.getpgkbPathwayCount().keySet());

					System.out.println("Enrichment Test PGKB Pathway - Sample Matrix Size = " + pathwayCount.size());
					// efileWriter.write("PharmGKB Pathways Enrichment Test\n");

					EnrichmentTestRemote("PharmGKB Pathways", pathwayCount, epathwayCount.getpgkbPathwayCount(),
							pgkbPathSize);

					// PharmGKB Drugs

					BioEnrichment2 edrugCount = new BioEnrichment2();
					edrugCount.setpgkbDrugCount();
					// intersect = new HashSet<String>(drugCount.keySet());
					// intersect.retainAll(edrugCount.getgoBioProcessCount().keySet());

					System.out.println("Enrichment Test PGKB Drug - Sample Matrix Size = " + drugCount.size());
					// efileWriter.write("PharmGKB Drugs Enrichment Test\n");

					EnrichmentTestRemote("PharmGKB Drugs", drugCount, edrugCount.getpgkbDrugCount(), pgkbDrugSize);

					// PharmGKB Diseases

					BioEnrichment2 ediseaseCount = new BioEnrichment2();
					ediseaseCount.setpgkbDiseaseCount();
					// intersect = new HashSet<String>(diseaseCount.keySet());
					// intersect.retainAll(ediseaseCount.getpgkbDiseaseCount().keySet());

					System.out.println("Enrichment Test PGKB Disease - Sample Matrix Size = " + diseaseCount.size());
					// efileWriter.write("PharmGKB Diseases Test\n");

					EnrichmentTestRemote("PGKB Disease", diseaseCount, ediseaseCount.getpgkbDiseaseCount(),
							pgkbDiseaseSize);

					efileWriter.close();
				}

				fileWriter.close();

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}

		}// end action

	}// end writesummary class

	class writeSummaryDB extends OneShotBehaviour {

		BufferedReader inputStream;
		BufferedWriter fileWriter;
		int transRegSize = 0;
		int functionSize = 0;
		int pgkbPathSize = 0;
		int pgkbDiseaseSize = 0;
		int pgkbDrugSize = 0;
		int omimDisorderSize = 0;
		int omimCytoLocSize = 0;
		int goBioPSize = 0;
		int goCelCSize = 0;
		int goMolFSize = 0;
		int gfTagSize = 0;

		public writeSummaryDB() {

		}

		public void action() {
			System.out.println("Agent " + getLocalName() + " writing annotation summary at " + sumfile2 + "...");

			Map<String, String> geneCount = new HashMap<String, String>();
			Map<String, String> fxnCount = new HashMap<String, String>();
			Map<String, String> chrCount = new HashMap<String, String>();

			Map<String, String> functionCount = new HashMap<String, String>();
			Map<String, String> pathwayCount = new HashMap<String, String>();
			Map<String, String> drugCount = new HashMap<String, String>();
			Map<String, String> diseaseCount = new HashMap<String, String>();
			Map<String, String> goMolFunCount = new HashMap<String, String>();
			Map<String, String> goCelCompCount = new HashMap<String, String>();
			Map<String, String> goBioProCount = new HashMap<String, String>();
			Map<String, String> cytoLocCount = new HashMap<String, String>();
			Map<String, String> disorderCount = new HashMap<String, String>();
			Map<String, String> locTypeCount = new HashMap<String, String>();
			Map<String, String> locGroupCount = new HashMap<String, String>();
			Map<String, String> gfTagCount = new HashMap<String, String>();

			// Gene Summary

			try {
				connection = myconnection.mysqlConnect();

				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT gene_symbol, count(*) FROM gene GROUP BY gene_symbol;");

				while (rs.next()) {
					String gene = rs.getString(1);
					String genCount = rs.getString(2);
					geneCount.put(gene, genCount);
					// System.out.println("Key = "+gene+"Value = "+genCount);

				} // End of rs.next while
					// rs.close();
					// st.close();
					// myconnection.mysqlDisconnect();

				// } catch (SQLException e){
				// e.printStackTrace(); }

				// Transcript Region Summary

				// try {
				// connection = myconnection.mysqlConnect();

				// Statement st_fxn = connection.createStatement();
				// ResultSet rs_fxn = st.executeQuery("SELECT subkind, count(*)
				// FROM snp GROUP BY subkind;");
				st = connection.createStatement();
				rs = st.executeQuery("SELECT subkind, count(*) FROM snp GROUP BY subkind;");

				// while (rs_fxn.next()) {
				// String fxnClass = rs_fxn.getString(1);
				// String fxnClassCount = rs_fxn.getString(2);
				// fxnCount.put(fxnClass, fxnClassCount);
				//
				// } // End of rs.next while

				while (rs.next()) {
					String fxnClass = rs.getString(1);
					String fxnClassCount = rs.getString(2);
					fxnCount.put(fxnClass, fxnClassCount);

				} // End of rs.next while

				// rs_fxn.close();
				// st_fxn.close();
				// myconnection.mysqlDisconnect();

				// } catch (SQLException e){
				// e.printStackTrace(); }

				// Chromosome Summary

				// try {
				// connection = myconnection.mysqlConnect();

				// Statement st_chr = connection.createStatement();
				// ResultSet rs_chr = st.executeQuery("SELECT chromosome,
				// count(*) FROM snp GROUP BY chromosome;");
				//
				// while (rs_chr.next()) {
				// String chr = rs_chr.getString(1);
				// String chrCCount = rs_chr.getString(2);
				// chrCount.put(chr, chrCCount);
				//
				// } // End of rs.next while
				// rs_chr.close();
				// st_chr.close();

				st = connection.createStatement();
				rs = st.executeQuery("SELECT chromosome, count(*) FROM snp GROUP BY chromosome;");

				while (rs.next()) {
					String chr = rs.getString(1);
					String chrCCount = rs.getString(2);
					chrCount.put(chr, chrCCount);

				} // End of rs.next while

				// myconnection.mysqlDisconnect();

				// } catch (SQLException e){
				// e.printStackTrace(); }

				if (getAnalysistype() == 3) { /* remote complete */

					// UCSC Function Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_func = connection.createStatement();
					// ResultSet rs_func = st.executeQuery("SELECT func,
					// count(*) FROM ucsc GROUP BY func;");
					//
					// while (rs_func.next()) {
					// String key = rs_func.getString(1);
					// String count = rs_func.getString(2);
					// functionCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_func.close();
					// st_func.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT func, count(*) FROM ucsc GROUP BY func;");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						functionCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();

					// } catch (SQLException e){
					// e.printStackTrace(); }

					// PGKB Pathway Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_path = connection.createStatement();
					// ResultSet rs_path = st.executeQuery("SELECT
					// pgkb.pathway.name, Count(DISTINCT pgkb.pathway.name) " +
					// "FROM " +
					// "pharmGKB INNER JOIN pgkb.pathway " +
					// "ON FIND_IN_SET(pgkb.pathway.name,
					// REPLACE(pharmGKB.pathway, ';', ','))>0 " +
					// "GROUP BY " +
					// "pgkb.pathway.name; ");
					//
					// while (rs_path.next()) {
					// String key = rs_path.getString(1);
					// String count = rs_path.getString(2);
					// pathwayCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_path.close();
					// st_path.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT pgkb.pathway.name, Count(DISTINCT pgkb.pathway.name) " + "FROM "
							+ "pharmGKB INNER JOIN pgkb.pathway "
							+ "ON FIND_IN_SET(pgkb.pathway.name, REPLACE(pharmGKB.pathway, ';', ','))>0 " + "GROUP BY "
							+ "pgkb.pathway.name; ");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						pathwayCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();

					// } catch (SQLException e){
					// e.printStackTrace(); }

					// PGKB Drug Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_dr = connection.createStatement();
					// ResultSet rs_dr = st.executeQuery("SELECT pgkb.drug.name,
					// Count(DISTINCT pgkb.drug.name) " +
					// "FROM " +
					// "pharmGKB INNER JOIN pgkb.drug " +
					// "ON FIND_IN_SET(pgkb.drug.name, REPLACE(pharmGKB.drugs,
					// ';', ','))>0 " +
					// "GROUP BY " +
					// "pgkb.drug.name; ");
					//
					// while (rs_dr.next()) {
					// String key = rs_dr.getString(1);
					// String count = rs_dr.getString(2);
					// drugCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_dr.close();
					// st_dr.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT pgkb.drug.name, Count(DISTINCT pgkb.drug.name) " + "FROM "
							+ "pharmGKB INNER JOIN pgkb.drug "
							+ "ON FIND_IN_SET(pgkb.drug.name, REPLACE(pharmGKB.drugs, ';', ','))>0 " + "GROUP BY "
							+ "pgkb.drug.name; ");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						drugCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();

					// } catch (SQLException e){
					// e.printStackTrace(); }

					// PGKB Disease Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_pdis = connection.createStatement();
					// ResultSet rs_pdis = st.executeQuery("SELECT
					// pgkb.disease.name, Count(DISTINCT pgkb.disease.name) " +
					// "FROM " +
					// "pharmGKB INNER JOIN pgkb.disease " +
					// "ON FIND_IN_SET(pgkb.disease.name,
					// REPLACE(pharmGKB.disease, ';', ','))>0 " +
					// "GROUP BY " +
					// "pgkb.disease.name; ");
					//
					// while (rs_pdis.next()) {
					// String key = rs_pdis.getString(1);
					// String count = rs_pdis.getString(2);
					// diseaseCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_pdis.close();
					// st_pdis.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT pgkb.disease.name, Count(DISTINCT pgkb.disease.name) " + "FROM "
							+ "pharmGKB INNER JOIN pgkb.disease "
							+ "ON FIND_IN_SET(pgkb.disease.name, REPLACE(pharmGKB.disease, ';', ','))>0 " + "GROUP BY "
							+ "pgkb.disease.name; ");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						diseaseCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();

					// } catch (SQLException e){
					// e.printStackTrace(); }

					// GO Mol Function Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_mol = connection.createStatement();
					// ResultSet rs_mol = st.executeQuery("SELECT GO.term.name,
					// Count(*) " +
					// "FROM " +
					// "geneOntology INNER JOIN GO.term " +
					// "ON FIND_IN_SET(GO.term.name,
					// REPLACE(geneOntology.molFunction, ';', ','))>0 " +
					// "WHERE GO.term.term_type = 'molecular_function' " +
					// "GROUP BY " +
					// "GO.term.name; ");
					//
					// while (rs_mol.next()) {
					// String key = rs_mol.getString(1);
					// String count = rs_mol.getString(2);
					// goMolFunCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_mol.close();
					// st_mol.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT GO.term.name, Count(*) " + "FROM " + "geneOntology INNER JOIN GO.term "
							+ "ON FIND_IN_SET(GO.term.name, REPLACE(geneOntology.molFunction, ';', ','))>0 "
							+ "WHERE GO.term.term_type = 'molecular_function' " + "GROUP BY " + "GO.term.name; ");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						goMolFunCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();

					// } catch (SQLException e){
					// e.printStackTrace(); }

					// GO Cel Component Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_cc = connection.createStatement();
					// ResultSet rs_cc = st.executeQuery("SELECT GO.term.name,
					// Count(*) " +
					// "FROM " +
					// "geneOntology INNER JOIN GO.term " +
					// "ON FIND_IN_SET(GO.term.name,
					// REPLACE(geneOntology.celComp, ';', ','))>0 " +
					// "WHERE GO.term.term_type = 'cellular_component' " +
					// "GROUP BY " +
					// "GO.term.name; ");
					//
					// while (rs_cc.next()) {
					// String key = rs_cc.getString(1);
					// String count = rs_cc.getString(2);
					// goCelCompCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_cc.close();
					// st_cc.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT GO.term.name, Count(*) " + "FROM " + "geneOntology INNER JOIN GO.term "
							+ "ON FIND_IN_SET(GO.term.name, REPLACE(geneOntology.celComp, ';', ','))>0 "
							+ "WHERE GO.term.term_type = 'cellular_component' " + "GROUP BY " + "GO.term.name; ");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						goCelCompCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();
					//
					//
					// } catch (SQLException e){
					// e.printStackTrace(); }

					// GO Bio Process Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_bp = connection.createStatement();
					// ResultSet rs_bp = st.executeQuery("SELECT GO.term.name,
					// Count(*) " +
					// "FROM " +
					// "geneOntology INNER JOIN GO.term " +
					// "ON FIND_IN_SET(GO.term.name,
					// REPLACE(geneOntology.bioProcess, ';', ','))>0 " +
					// "WHERE GO.term.term_type = 'biological_process' " +
					// "GROUP BY " +
					// "GO.term.name; ");
					//
					// while (rs_bp.next()) {
					// String key = rs_bp.getString(1);
					// String count = rs_bp.getString(2);
					// goBioProCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_bp.close();
					// st_bp.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT GO.term.name, Count(*) " + "FROM " + "geneOntology INNER JOIN GO.term "
							+ "ON FIND_IN_SET(GO.term.name, REPLACE(geneOntology.bioProcess, ';', ','))>0 "
							+ "WHERE GO.term.term_type = 'biological_process' " + "GROUP BY " + "GO.term.name; ");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						goBioProCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();
					//
					//
					// } catch (SQLException e){
					// e.printStackTrace(); }

					// OMIM CytoLoc Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_cyto = connection.createStatement();
					// ResultSet rs_cyto = st.executeQuery("SELECT cytoLoc,
					// count(*) FROM omim GROUP BY cytoLoc;");
					//
					// while (rs_cyto.next()) {
					// String key = rs_cyto.getString(1);
					// String count = rs_cyto.getString(2);
					// cytoLocCount.put(key, count);
					//
					// } // End of rs.next while
					// rs_cyto.close();
					// st_cyto.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT cytoLoc, count(*) FROM omim GROUP BY cytoLoc;");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						cytoLocCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();
					//
					//
					// } catch (SQLException e){
					// e.printStackTrace(); }

					// OMIM Disorder Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_odis = connection.createStatement();
					// ResultSet rs_odis = st.executeQuery("SELECT
					// OMIM.genemap.disorder1, Count(*) " +
					// "FROM " +
					// "omim INNER JOIN OMIM.genemap " +
					// "ON FIND_IN_SET(OMIM.genemap.disorder1,
					// REPLACE(omim.disorder, ';', ','))>0 " +
					// "GROUP BY " +
					// "OMIM.genemap.disorder1;");
					//
					// while (rs_odis.next()) {
					// String key = rs_odis.getString(1);
					// String count = rs_odis.getString(2);
					// disorderCount.put(key, count);
					//
					//
					// } // End of rs.next while
					// rs_odis.close();
					// st_odis.close();

					st = connection.createStatement();
					rs = st.executeQuery(
							"SELECT OMIM.genemap.disorder1, Count(*) " + "FROM " + "omim INNER JOIN OMIM.genemap "
									+ "ON FIND_IN_SET(OMIM.genemap.disorder1, REPLACE(omim.disorder, ';', ','))>0 "
									+ "GROUP BY " + "OMIM.genemap.disorder1;");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						disorderCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();
					//
					//
					// } catch (SQLException e){
					// e.printStackTrace(); }

					// HGNC Locus Type Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_lt = connection.createStatement();
					// ResultSet rs_lt = st.executeQuery("SELECT locusType,
					// count(*) FROM hugoDB GROUP BY locusType;");
					//
					// while (rs_lt.next()) {
					// String key = rs_lt.getString(1);
					// String count = rs_lt.getString(2);
					// locTypeCount.put(key, count);
					//
					//
					// } // End of rs.next while
					// rs_lt.close();
					// st_lt.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT locusType, count(*) FROM hugoDB GROUP BY locusType;");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						locTypeCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();
					//
					//
					// } catch (SQLException e){
					// e.printStackTrace(); }

					// HGNC Locus Group Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_lg = connection.createStatement();
					// ResultSet rs_lg = st.executeQuery("SELECT locusGroup,
					// count(*) FROM hugoDB GROUP BY locusGroup;");
					//
					// while (rs_lg.next()) {
					// String key = rs_lg.getString(1);
					// String count = rs_lg.getString(2);
					// locGroupCount.put(key, count);
					//
					//
					// } // End of rs.next while
					// rs_lg.close();
					// st_lg.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT locusGroup, count(*) FROM hugoDB GROUP BY locusGroup;");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						locGroupCount.put(key, count);

					} // End of rs.next while

					// myconnection.mysqlDisconnect();
					//
					//
					// } catch (SQLException e){
					// e.printStackTrace(); }

					// HGNC Gene Family Tag Summary

					// try {
					// connection = myconnection.mysqlConnect();

					// Statement st_gft = connection.createStatement();
					// ResultSet rs_gft = st.executeQuery("SELECT geneFamilyTag,
					// count(*) FROM hugoDB GROUP BY geneFamilyTag;");
					//
					// while (rs_gft.next()) {
					// String key = rs_gft.getString(1);
					// String count = rs_gft.getString(2);
					// gfTagCount.put(key, count);
					//
					//
					// } // End of rs.next while
					// rs_gft.close();
					// st_gft.close();

					st = connection.createStatement();
					rs = st.executeQuery("SELECT geneFamilyTag, count(*) FROM hugoDB GROUP BY geneFamilyTag;");

					while (rs.next()) {
						String key = rs.getString(1);
						String count = rs.getString(2);
						gfTagCount.put(key, count);

					} // End of rs.next while

				} // End of if analysis=3

				st.close();
				rs.close();
				myconnection.mysqlDisconnect();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// open summary file for writing
			try {

				fileWriter = new BufferedWriter(new FileWriter(sumfile2));

				// fileWriter.write("Gene Name Summary\n");

				Iterator itr = geneCount.entrySet().iterator();
				while (itr.hasNext()) {

					Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
					fileWriter.write("Gene Symbol dbSNP\t" + entry.getKey() + "\t" + entry.getValue() + "\n");

				}

				/*
				 * for (Map.Entry<String, String> entry : geneCount.entrySet())
				 * {
				 * fileWriter.write(entry.getKey()+"\t"+entry.getValue()+"\n");
				 * }
				 */

				// fileWriter.write("\nTranscript Region Summary\n");
				itr = fxnCount.entrySet().iterator();

				while (itr.hasNext()) {

					Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
					fileWriter.write("Transcript Region dbSNP\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
					transRegSize = transRegSize + Integer.valueOf(entry.getValue());

				}

				// fileWriter.write("\nChromosome Summary\n");
				itr = chrCount.entrySet().iterator();

				while (itr.hasNext()) {

					Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
					fileWriter.write("Chromosome dbSNP\t" + entry.getKey() + "\t" + entry.getValue() + "\n");

				}

				if (getAnalysistype() == 3) { /* local complete */

					// fileWriter.write("\nUCSC Function Summary\n");
					itr = functionCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Transcript region UCSC\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						// functionSize = functionSize +
						// Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nPGKB Pathway Summary\n");
					itr = pathwayCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Pathway PharmGKB\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						pgkbPathSize = pgkbPathSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nPGKB Drugs Summary\n");
					itr = drugCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Drugs PharmGKB\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						pgkbDrugSize = pgkbDrugSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nPGKB Disease Summary\n");
					itr = diseaseCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Disease PharmGKB\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						pgkbDiseaseSize = pgkbDiseaseSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nGO Molecular Function Summary\n");
					itr = goMolFunCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Molecular Function GO\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						goMolFSize = goMolFSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nGO Cellular Component Summary\n");
					itr = goCelCompCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Cellular Component GO\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						goCelCSize = goCelCSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nGO Biological Process Summary\n");
					itr = goBioProCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write(
								"Biological Process PharmGKB\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						goBioPSize = goBioPSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nOMIM CytoLoc Summary\n");
					itr = cytoLocCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("CytoLocation OMIM\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						omimCytoLocSize = omimCytoLocSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nOMIM Disorders Summary\n");
					itr = disorderCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Disorders OMIM\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						omimDisorderSize = omimDisorderSize + Integer.valueOf(entry.getValue());

					}

					// fileWriter.write("\nHGNC Locus Type Summary\n");
					itr = locTypeCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Locus Type HGNC\t" + entry.getKey() + "\t" + entry.getValue() + "\n");

					}

					// fileWriter.write("\nHGNC Locus Group Summary\n");
					itr = locGroupCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Locus Group HGNC\t" + entry.getKey() + "\t" + entry.getValue() + "\n");

					}

					// fileWriter.write("\nHGNC Gene Family Tag Summary\n");
					itr = gfTagCount.entrySet().iterator();

					while (itr.hasNext()) {

						Map.Entry<String, String> entry = (Entry<String, String>) itr.next();
						fileWriter.write("Gene Family HGNC\t" + entry.getKey() + "\t" + entry.getValue() + "\n");
						gfTagSize = gfTagSize + Integer.valueOf(entry.getValue());

					}

					// ENRICHMENT FUNCTIONS

					efileWriter = new BufferedWriter(new FileWriter(enrichfile));

					System.out.println("ENRICHMENT TEST LOCAL");

					// HGNC Gene Family

					BioEnrichment hgncCount = new BioEnrichment();
					hgncCount.setHgncGeneFamilyCount();
					// Set<String> intersect = new
					// HashSet<String>(gfTagCount.keySet());
					// intersect.retainAll(hgncCount.getHgncGeneFamilyCount().keySet());

					System.out.println("Enrichment Test GeneFamily - Sample Matrix Size = " + gfTagCount.size());
					efileWriter.write("HGNC Gene Family Enrichment Test\n");

					// EnrichmentTestLocal(gfTagCount,hgncCount.getHgncGeneFamilyCount(),gfTagSize);

					// OMIM Disorder

					BioEnrichment edisorderCount = new BioEnrichment();
					edisorderCount.setomimDisorderCount();
					// intersect = new HashSet<String>(disorderCount.keySet());
					// intersect.retainAll(edisorderCount.getomimDisorderCount().keySet());

					System.out.println("Enrichment Test OmimDisorder - Sample Matrix Size = " + disorderCount.size());
					efileWriter.write("OMIM Disorder Enrichment Test\n");

					// EnrichmentTestLocal(disorderCount,edisorderCount.getomimDisorderCount(),omimDisorderSize);

					// OMIM CytoLoc

					BioEnrichment ecytoLocCount = new BioEnrichment();
					ecytoLocCount.setomimCytoLocCount();
					// intersect = new HashSet<String>(cytoLocCount.keySet());
					// intersect.retainAll(ecytoLocCount.getomimCytoLocCount().keySet());

					System.out.println("Enrichment Test CytoLoc - Sample Matrix Size = " + cytoLocCount.size());
					efileWriter.write("OMIM CytoLoc Enrichment Test\n");

					// EnrichmentTestLocal(cytoLocCount,ecytoLocCount.getomimCytoLocCount(),omimCytoLocSize);

					// dbSNP Transcript Region

					BioEnrichment etransRegCount = new BioEnrichment();
					etransRegCount.setdbsnpTransRegCount();
					// intersect = new HashSet<String>(fxnCount.keySet());
					// intersect.retainAll(etransRegCount.getdbsnpTransRegCount().keySet());

					System.out.println("Enrichment Test TranscRegion - Sample Matrix Size = " + fxnCount.size());
					efileWriter.write("dbSNP Transcript Region Enrichment Test\n");

					// EnrichmentTestLocal(fxnCount,etransRegCount.getdbsnpTransRegCount(),transRegSize);

					// Gene Ontology Molecular Function

					BioEnrichment egoMolFunCount = new BioEnrichment();
					egoMolFunCount.setgoMolFunctionCount();
					// intersect = new HashSet<String>(goMolFunCount.keySet());
					// intersect.retainAll(egoMolFunCount.getgoMolFunctionCount().keySet());

					System.out.println("Enrichment Test GOMolFunction - Sample Matrix Size = " + goMolFunCount.size());
					efileWriter.write("GeneOntology Molecular Function Enrichment Test\n");

					// EnrichmentTestLocal(goMolFunCount,egoMolFunCount.getgoMolFunctionCount(),goMolFSize);

					// Gene Ontology Biological Process

					BioEnrichment egoBioPCount = new BioEnrichment();
					egoBioPCount.setgoBioProcessCount1();
					egoBioPCount.setgoBioProcessCount2();
					egoBioPCount.setgoBioProcessCount3();
					// intersect = new HashSet<String>(goBioProCount.keySet());
					// intersect.retainAll(egoBioPCount.getgoBioProcessCount().keySet());

					System.out.println("Enrichment Test GOBioProcess - Sample Matrix Size = " + goBioProCount.size());
					efileWriter.write("GeneOntology Biological Process Enrichment Test\n");

					// EnrichmentTestLocal(goMolFunCount,egoMolFunCount.getgoBioProcessCount(),goBioPSize);

					// Gene Ontology Cellular Component

					BioEnrichment egoCelCompCount = new BioEnrichment();
					egoCelCompCount.setgoCelCompCount();
					// intersect = new HashSet<String>(goCelCompCount.keySet());
					// intersect.retainAll(egoCelCompCount.getgoCelCompCount().keySet());

					System.out.println("Enrichment Test GOCelComp - Sample Matrix Size = " + goCelCompCount.size());
					efileWriter.write("GeneOntology Cellular Component Enrichment Test\n");

					// EnrichmentTestLocal(goCelCompCount,egoCelCompCount.getgoCelCompCount(),goCelCSize);

					// PharmGKB Drugs

					BioEnrichment2 edrugCount = new BioEnrichment2();
					edrugCount.setpgkbDrugCount();
					// intersect = new HashSet<String>(drugCount.keySet());
					// intersect.retainAll(edrugCount.getpgkbDrugCount().keySet());

					System.out.println("Enrichment Test PGKB Drug - Sample Matrix Size = " + drugCount.size());
					efileWriter.write("PharmGKB Drugs Enrichment Test\n");

					// EnrichmentTestLocal(drugCount,edrugCount.getpgkbDrugCount(),pgkbDrugSize);

					// PharmGKB Pathways

					BioEnrichment2 epathwayCount = new BioEnrichment2();
					epathwayCount.setpgkbPathwayCount();
					// intersect = new HashSet<String>(pathwayCount.keySet());
					// intersect.retainAll(epathwayCount.getpgkbPathwayCount().keySet());

					System.out.println("Enrichment Test PGKB Pathway - Sample Matrix Size = " + pathwayCount.size());
					efileWriter.write("PharnGKB Pathways Enrichment Test\n");

					// EnrichmentTestLocal(pathwayCount,epathwayCount.getpgkbPathwayCount(),pgkbPathSize);

					// PharmGKB Diseases

					BioEnrichment2 ediseaseCount = new BioEnrichment2();
					ediseaseCount.setpgkbDiseaseCount();
					// intersect = new HashSet<String>(diseaseCount.keySet());
					// intersect.retainAll(ediseaseCount.getpgkbDiseaseCount().keySet());

					System.out.println("Enrichment Test PGKB Disease - Sample Matrix Size = " + diseaseCount.size());
					efileWriter.write("PharmGKB Diseases Test\n");

					// EnrichmentTestLocal(diseaseCount,ediseaseCount.getpgkbDiseaseCount(),pgkbDiseaseSize);

				}

				fileWriter.close();
				efileWriter.close();

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}

		}// end action

	}// end writesummaryDB class

	class readDataFile extends OneShotBehaviour {

		BufferedReader inputStream;
		String[] values;

		public readDataFile() {

		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " reading " + datapath + datafilename + "...");

			try {
				inputStream = new BufferedReader(new FileReader(datapath + datafilename));
				String l;
				while ((l = inputStream.readLine()) != null) {
					if (!l.contains("rs")) {
						inputerror = true;
						break;
					} else {
						if (!l.equals("")) {
							rslist.add(l);
						}
					}
					// check if number of snps is compatible with type of
					// annotation.
					if (rslist.size() > maxsizeforremoteannot && (getAnalysistype() == 2 || getAnalysistype() == 4)) {
						sizeerror = true;
						break;
					}
				}

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}
		}

	}

	class writeAnnotatedDB extends OneShotBehaviour {

		BufferedWriter fileWriter;

		// PreparedStatement st;
		PreparedStatement stdsnp;
		PreparedStatement stucsc;
		PreparedStatement streac;
		PreparedStatement stpgkb;
		PreparedStatement stgo;
		PreparedStatement stomim;
		PreparedStatement sthgnc;
		PreparedStatement stgwas;
		PreparedStatement stpph;
		PreparedStatement stprv;
		// PreparedStatement stsift;

		// String query;
		String querysnp;
		String queryucsc;
		String queryreac;
		String querypgkb;
		String querygo;
		String queryomim;
		String queryhgnc;
		String querygwas;
		String querypph;
		String queryprv;
		// String querysift;

		String polid;

		// ResultSet rs;
		ResultSet rssnp;
		ResultSet rsucsc;
		ResultSet rsreac;
		ResultSet rspgkb;
		ResultSet rsgo;
		ResultSet rsomim;
		ResultSet rshgnc;
		ResultSet rsgwas;
		ResultSet rspph;
		ResultSet rsprv;
		// ResultSet rssift;

		int searchid;

		// Declare Variables

		public writeAnnotatedDB() {

		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " retrieving annotation from database...");

			try {

				fileWriter = new BufferedWriter(new FileWriter(datapath + annotateddatafilename));

				if (getAnalysistype() == 3) { /* local complete */
					// fileWriter.write("(1)Polymorphism Id \t (2)Polymorphism
					// Type \t (3)Gene Symbol \t (4)Gene ID \t (5)Transcript
					// Region \t (6)Nucleotide Numbering coding DNA \t
					// (7)Chromosome \t (8)Chromosome Position \t (9)Ancestral
					// Allele \t (10)Orientation \t (11)Assembly Build Version
					// \t (12)Assembly Coord Start \t (13)Assembly Coord End \t
					// (14)mRNA accession \t (15)mRNA version \t (16)Alleles \t
					// (17)Frequency \t (18)Strand \t (19)UCSC Reference \t
					// (20)UCSC Observed \t (21)UCSC Polymorphism Class \t
					// (22)UCSC Functional Class \t (23)Pathway \t (24)Disease
					// \t (25)Drug \t (26)Related Genes \t (27)Biological
					// Process \t (28)Molecular Function \t (29)Celular
					// Component \t (30)Disorder \t (31)Comments \t
					// (32)CytoLoc");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)\tSpecialistDBLinks(46)\tLocusSpecDB(47)\tEnzymeId(48)\tEntrezId(49)\tEnsemblId(50)\tPubMedIds(51)\tRefSeqIds(52)\tCCDSIds(53)\tVegaIds(54)\tUniProtId(55)\tMouseGdbId(56)\tRatGdbId(57)");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)\tPubmed(46)\tReported_Genes(47)\tStrongest_SNP_Risk_Allele(48)\tContext(49)\tP-Value(50)\tDisease_Trait(51)\tSample_and_Population(52)");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)\tPubmed(46)\tReported_Genes(47)\tStrongest_SNP_Risk_Allele(48)\tContext(49)\tP-Value(50)\tDisease_Trait(51)\tSample_and_Population(52)\tPolyPhenProteinID(53)\tPolyPhenSubstitution(54)\tPolyPhenPrediction(55)\tPolyPhenScore(56)\tProveanProteinID(57)\tProveanSubstitution(58)\tProveanProteinPos(59)\tProveanPrediction(60)\tProveanScore(61)\tSIFTPrediction(62)\tSIFTScore(63)");
					fileWriter.write(
							"PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tReactomePathways(23)\tPGKBPathways(24)\tPGKBDrugs(25)\tPGKBDisease(26)\tRelatedGenes(27)\tGOMolecularFunction(28)\tGOCellularComponent(29)\tGOBiologicalProcess(30)\tCytoloc(31)\tGeneStatus(32)\tGeneMapMethods(33)\tDisorders(34)\tMIMids(35)\tInheritance(36)\tPhenoMapMethods(37)\tComments(38)\tHgncId(39)\tGeneSymbol(40)\tGeneName(41)\tGeneSynonyms(42)\tLocusType(43)\tLocusGroup(44)\tGeneFamilyTag(45)\tGeneFamily(46)\tPubmed(47)\tReported_Genes(48)\tStrongest_SNP_Risk_Allele(49)\tContext(50)\tP-Value(51)\tDisease_Trait(52)\tSample_and_Population(53)\tPolyphenProteinID(54)\tPolyphenSubstitution(55)\tPolyphen2Prediction(56)\tPolyPhen2Prob(57)\tPolyphen2FDR(58)\tPolyphen1Prediction(59)\tProveanProteinID(60)\tProveanSubstitution(61)\tProveanProteinPos(62)\tProveanPrediction(63)\tProveanScore(64)\tSIFTPrediction(65)\tSIFTScore(66)");
					fileWriter.newLine();
				}
				if (getAnalysistype() == 1) { /* local basic */
					fileWriter.write(
							"(1)Polymorphism Id \t (2)Polymorphism Type \t (3)Gene Symbol \t (4)Gene ID \t (5)Transcript Region \t (6)Nucleotide Numbering coding DNA \t (7)Chromosome \t (8)Chromosome Position \t (9)Ancestral Allele \t (10)Orientation \t (11)Assembly Build Version \t (12)Assembly Coord Start \t (13)Assembly Coord End \t  (14)mRNA accession \t (15)mRNA version \t (16)Alleles \t (17)Frequency");
					fileWriter.newLine();
				}
				connection = myconnection.mysqlConnect();

				Iterator itr = searchidlist.iterator();

				while (itr.hasNext()) {

					int id = (Integer) itr.next();

					// String query = "SELECT * FROM snp,gene WHERE
					// snp.fk_searchid="+id+" AND gene.genepk=snp.fk_genepk";
					String querysnp = "SELECT * FROM snp,gene WHERE snp.fk_searchid=? AND gene.genepk=snp.fk_genepk";

					// System.out.println("id = "+id);

					// Statement st = connection.createStatement();
					stdsnp = connection.prepareStatement(querysnp);
					stdsnp.setInt(1, id);
					rssnp = stdsnp.executeQuery();

					while (rssnp.next()) {

						String genesymbol = rssnp.getString(22);
						// fileWriter.write("rs"+rs.getString("snpid")+
						// "\t"+rs.getString("kind")+
						// "\t"+rs.getString("gene_symbol")+
						// "\t"+rs.getString("geneid")+
						// "\t"+rs.getString("subkind")+
						// "\t"+rs.getString("coordRelGene")+
						// "\t"+rs.getString("chromosome")+
						// "\t"+rs.getString("coordRefSeq")+
						// "\t"+rs.getString("ancestral_allele")+
						// "\t"+rs.getString("orientation")+
						// "\t"+rs.getString("assm_build_version")+
						// "\t"+rs.getString("assm_coord_start")+
						// "\t"+rs.getString("assm_coord_end")+
						// "\t"+rs.getString("mrnaAcc")+
						// "\t"+rs.getString("mrnaVer")+
						// "\t"+rs.getString("referenceValue")+
						// "\t"+rs.getString("freq"));

						fileWriter.write("rs" + rssnp.getString(2) + // snpid
								"\t" + rssnp.getString(3) + // kind
								"\t" + rssnp.getString(22) + // gene_symbol
								"\t" + rssnp.getString(23) + // geneid
								"\t" + rssnp.getString(4) + // subkind
								"\t" + rssnp.getString(7) + // coordRelGene
								"\t" + rssnp.getString(8) + // chromosome
								"\t" + rssnp.getString(9) + // coordRefSeq
								"\t" + rssnp.getString(14) + // ancestral_allele
								"\t" + rssnp.getString(15) + // orientation
								"\t" + rssnp.getString(10) + // assm_build_version
								"\t" + rssnp.getString(11) + // assm_coord_start
								"\t" + rssnp.getString(12) + // assm_coord_end
								"\t" + rssnp.getString(16) + // mrnaAcc
								"\t" + rssnp.getString(17) + // mrnaVer
								"\t" + rssnp.getString(5) + // referenceValue
								"\t" + rssnp.getString(18)); // freq

						polid = "rs" + rssnp.getString(2);

						if (getAnalysistype() == 1) { /* local basic */
							fileWriter.newLine();
						}
						if (getAnalysistype() == 3) { /* local complete */
							// ucsc
							// String strand = "null";
							// String ucscref = "null";
							// String ucscobs = "null";
							// String ucscclass = "null";
							// String ucscfunc = "null";

							queryucsc = "SELECT DISTINCT * FROM ucsc WHERE fk_searchid=? AND polID=?";
							stucsc = connection.prepareStatement(queryucsc);
							stucsc.setInt(1, id);
							stucsc.setString(2, polid);
							// System.out.println("SQL:"+sti);
							rsucsc = stucsc.executeQuery();
							// while (rsi.next()){

							if (!rsucsc.isBeforeFirst()) {

								fileWriter.write(
										"\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null");

							} else {
								rsucsc.first();
								fileWriter.write("\t" + rsucsc.getString(2) + "\t" + rsucsc.getString(3) + "\t"
										+ rsucsc.getString(4) + "\t" + rsucsc.getString(5) + "\t"
										+ rsucsc.getString(6));

							}

							// strand = rsi.getString(2); // "strand"
							// ucscref = rsi.getString(3); // "refUCSC"
							// ucscobs = rsi.getString(4); // "observed"
							// ucscclass = rsi.getString(5); // "class"
							// ucscfunc = rsi.getString(6); // "func"

							// fileWriter.write("\t"+rsi.getString(2)+"\t"+rsi.getString(3)+"\t"+rsi.getString(4)+"\t"+rsi.getString(5)+"\t"+rsi.getString(6));

							// }

							// st_uc.close();
							// rs_uc.close();

							// fileWriter.write("\t"+strand+"\t"+ucscref+"\t"+ucscobs+"\t"+ucscclass+"\t"+ucscfunc);

							// reactome
							// String rpw = "null";

							queryreac = "SELECT DISTINCT * FROM reactome WHERE fk_searchid=? AND geneSymbol=?";
							streac = connection.prepareStatement(queryreac);
							streac.setInt(1, id);
							streac.setString(2, genesymbol);
							rsreac = streac.executeQuery();
							// while (rsi.next()){
							// rsi.first();

							if (!rsreac.isBeforeFirst()) {

								fileWriter.write("\t" + "null");

							} else {
								rsreac.first();
								fileWriter.write("\t" + rsreac.getString(2));

							}

							// rpw = rsi.getString(2); // "pathway"

							// fileWriter.write("\t"+rsi.getString(2));
							// }

							// fileWriter.write("\t"+rpw);
							// end reactome

							// pharmGKB
							// String pw = "null";
							// String ds = "null";
							// String dg = "null";
							// String rg = "null";

							// String query_ph = "SELECT * FROM pharmGKB WHERE
							// fk_searchid="+id+" AND
							// geneSymbol='"+genesymbol+"'";
							querypgkb = "SELECT DISTINCT * FROM pharmGKB WHERE fk_searchid=? AND geneSymbol=?";
							// System.out.println("SQL:"+query_ph);
							// Statement st_ph = connection.createStatement();
							stpgkb = connection.prepareStatement(querypgkb);
							stpgkb.setInt(1, id);
							stpgkb.setString(2, genesymbol);
							rspgkb = stpgkb.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// dg = rsi.getString(2); // "drugs"
							// pw = rsi.getString(3); // "pathway"
							// rg = rsi.getString(4); // "geneCross"
							// ds = rsi.getString(5); // "disease"

							if (!rspgkb.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null");

							} else {
								rspgkb.first();
								fileWriter.write("\t" + rspgkb.getString(3) + "\t" + rspgkb.getString(2) + "\t"
										+ rspgkb.getString(5) + "\t" + rspgkb.getString(4));

							}

							// fileWriter.write("\t"+rsi.getString(2)+"\t"+rsi.getString(3)+"\t"+rsi.getString(4)+"\t"+rsi.getString(5));

							// }

							// st_ph.close();
							// rs_ph.close();

							// fileWriter.write("\t"+pw+"\t"+dg+"\t"+ds+"\t"+rg);
							// end pharmGKB

							// geneOntology
							// String mf = "null";
							// String cc = "null";
							// String bp = "null";

							// String query_go = "SELECT * FROM geneOntology
							// WHERE fk_searchid="+id+" AND
							// gp_symbol='"+genesymbol+"'";
							querygo = "SELECT DISTINCT * FROM geneOntology WHERE fk_searchid=? AND gp_symbol=?";
							// System.out.println("SQL:"+query_go);
							// Statement st_go = connection.createStatement();
							stgo = connection.prepareStatement(querygo);
							stgo.setInt(1, id);
							stgo.setString(2, genesymbol);

							rsgo = stgo.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// mf = rsi.getString(3); // "molFunction"
							// cc = rsi.getString(4); // "celComp"
							// bp = rsi.getString(5); // "bioProcess"

							if (!rsgo.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null");

							} else {
								rsgo.first();
								fileWriter.write(
										"\t" + rsgo.getString(3) + "\t" + rsgo.getString(4) + "\t" + rsgo.getString(5));

							}

							// fileWriter.write("\t"+rsi.getString(3)+"\t"+rsi.getString(4)+"\t"+rsi.getString(5));

							// }

							// st_go.close();
							// rs_go.close();

							// fileWriter.write("\t"+mf+"\t"+cc+"\t"+bp);
							// end geneOntology

							// omim
							// String cl = "null";
							// String gs = "null";
							// String mp = "null";
							// String dr = "null";
							// String mim = "null";
							// String inh = "null";
							// String pm = "null";
							// String co = "null";

							//// String query_om = "SELECT * FROM omim WHERE
							//// fk_searchid="+id+" AND
							//// gene_symbol='"+genesymbol+"'";
							// queryi = "SELECT * FROM omim WHERE fk_searchid=?
							//// AND gene_symbol=?";
							// //System.out.println("SQL:"+query_om);
							//// Statement st_om = connection.createStatement();
							// sti = connection.prepareStatement(queryi);
							// sti.setInt(1,id);
							// sti.setString(2,genesymbol);
							//
							// rsi = sti.executeQuery();
							// while (rsi.next()){
							//
							// cl = rsi.getString(3); // "cytoLoc"
							// gs = rsi.getString(4); // "geneStatus"
							// mp = rsi.getString(5); // "methods"
							// dr = rsi.getString(6); // "disorder"
							// mim = rsi.getString(7); // "mimId"
							// co = rsi.getString(8); // "comments"
							//// pm = rsi.getString(""); //
							//
							// }
							//
							//// st_om.close();
							//// rs_om.close();
							//
							// fileWriter.write("\t"+cl+"\t"+gs+"\t"+mp+"\t"+dr+"\t"+mim+"\t"+inh+"\t"+pm+"\t"+co);
							queryomim = "SELECT DISTINCT * FROM omim WHERE fk_searchid=? AND gene_symbol=?";
							stomim = connection.prepareStatement(queryomim);
							stomim.setInt(1, id);
							stomim.setString(2, genesymbol);

							rsomim = stomim.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// cl = rsi.getString(3); // "cytoLoc"
							//// gs = rsi.getString(4); // "geneStatus"
							// mp = rsi.getString(12); // "methods"
							// dr = rsi.getString(6); // "disorder"
							// mim = rsi.getString(7); // "mimId"
							//// co = rsi.getString(8); // "comments"
							//// pm = rsi.getString(""); //

							if (!rsomim.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null" + "\t"
										+ "null" + "\t" + "null" + "\t" + "null" + "\t" + "null");

							} else {
								rsomim.first();
								fileWriter.write("\t" + rsomim.getString(3) + "\t" + rsomim.getString(4) + "\t"
										+ rsomim.getString(5) + "\t" + rsomim.getString(6) + "\t" + rsomim.getString(7)
										+ "\t" + "null" + "\t" + rsomim.getString(12) + "\t" + rsomim.getString(8));

							}

							// fileWriter.write("\t"+rsi.getString(3)+"\t"+"null"+"\t"+rsi.getString(12)+"\t"+rsi.getString(6)+"\t"+rsi.getString(7)+"\t"+"null"+"\t"+"null"+"\t"+rsi.getString(8));

							// }
							// fileWriter.write("\t"+cl+"\t"+gs+"\t"+mp+"\t"+dr+"\t"+mim+"\t"+inh+"\t"+pm+"\t"+co);
							// end omim

							// HGNC
							queryhgnc = "SELECT DISTINCT * FROM hugoDB WHERE fk_searchid=? AND approvedSymbol =?";
							// System.out.println("SQL:"+query_hgnc);
							// Statement st_hg = connection.createStatement();
							sthgnc = connection.prepareStatement(queryhgnc);
							sthgnc.setInt(1, id);
							sthgnc.setString(2, genesymbol);
							// System.out.println("SQL: "+st_hg);

							// String hugoId = "null";
							// String geneSymbol = "null";
							// String geneName = "null";
							// String prevGeneNames = "null";
							// String altGeneSymbols = "null";
							// String geneSyns = "null";
							//
							// String geneTag = "null";
							// String geneFamily = "null";
							// String geneType = "null";
							// String geneGroup = "null";

							// String link = "null";
							// String locusSpecdb = "null";

							// String accNumbers = "null";
							// String enzymeId = "null";
							// String ensemblId = "null";
							// String refSeqId = "null";
							// String ccdsId = "null";
							// String vegaId = "null";
							// String entrezGeneId = "null";
							// String omimId = "null";
							// String uniProtGeneId = "null";
							// String ucscGeneId = "null";

							// String mouseGdbId = "null";
							// String ratGdbId = "null";

							// String pmid = "null";

							rshgnc = sthgnc.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// hugoId = rsi.getString(2); //"hgncId"
							// geneSymbol = rsi.getString(3); //"approvedSymbol"
							// geneName = rsi.getString(4); // "approvedName"
							// geneType = rsi.getString(6); // "locusType"
							// geneGroup = rsi.getString(7); // "locusGroup"
							// prevGeneNames = rsi.getString(8); //
							// "previousSymbol"
							// altGeneSymbols = rsi.getString(10); // "synonyms"
							// geneSyns = rsi.getString(11); // "nameSynomys"
							//
							// geneTag = rsi.getString(26); // "geneFamilyTag"
							// geneFamily = rsi.getString(27); //
							// "geneFamilyDesc"

							// link = rs_hg.getString("specialistDBLink");
							// locusSpecdb = rs_hg.getString("LocusSpecDB");
							// pmid = rs_hg.getString("pubmedIds");

							// accNumbers = rs_hg.getString("accessionNumbers");
							// enzymeId = rs_hg.getString("enzymeId");
							// ensemblId = rs_hg.getString("ensemblId");
							// refSeqId = rs_hg.getString("refSeqIds");
							// ccdsId = rs_hg.getString("CCDSIds");
							// vegaId = rs_hg.getString("VEGAIds");
							// entrezGeneId = rs_hg.getString("entrezGeneId");
							// omimId = rs_hg.getString("omimId");
							// uniProtGeneId = rs_hg.getString("uniProtId");
							// ucscGeneId = rs_hg.getString("ucscId");

							// mouseGdbId = rs_hg.getString("mouseGdbId");
							// ratGdbId = rs_hg.getString("ratGdbId");

							if (!rshgnc.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null" + "\t"
										+ "null" + "\t" + "null" + "\t" + "null" + "\t" + "null");

							} else {
								rshgnc.first();
								fileWriter.write("\t" + rshgnc.getString(2) + "\t" + rshgnc.getString(3) + "\t"
										+ rshgnc.getString(4) + "\t" + rshgnc.getString(10) + "\t" + rshgnc.getString(6)
										+ "\t" + rshgnc.getString(7) + "\t" + rshgnc.getString(26) + "\t"
										+ rshgnc.getString(27));

							}

							// fileWriter.write("\t"+ rsi.getString(2)
							// +"\t"+rsi.getString(3)+"\t"+rsi.getString(4)+"\t"+rsi.getString(10)+"\t"
							// +rsi.getString(6)+"\t"+rsi.getString(7)+"\t"+
							// rsi.getString(26) +"\t"+rsi.getString(27));

							// } // End of while

							// st_hg.close();
							// rs_hg.close();

							/*
							 * fileWriter.write("\t"+ hugoId
							 * +"\t"+geneSymbol+"\t"+geneName+"\t"+
							 * altGeneSymbols+"\t" +
							 * geneType+"\t"+geneGroup+"\t"+ geneTag
							 * +"\t"+geneFamily+"\t"+link+"\t"+locusSpecdb+"\t"+
							 * enzymeId+"\t"+entrezGeneId+"\t"+ensemblId+"\t" +
							 * pmid +"\t" +
							 * refSeqId+"\t"+ccdsId+"\t"+vegaId+"\t"+
							 * uniProtGeneId+"\t"+mouseGdbId+"\t" + ratGdbId);
							 */

							// fileWriter.write("\t"+ hugoId
							// +"\t"+geneSymbol+"\t"+geneName+"\t"+altGeneSymbols+"\t"
							// + geneType+"\t"+geneGroup+"\t"+
							// geneTag +"\t"+geneFamily);
							// end hugo

							// gwascatalog
							// String pid = "null";
							// String rlg = "null";
							// String ra = "null";
							// String ct = "null";
							// String pv = "null";
							// String dt = "null";
							// String ss = "null";

							// String query_gc = "SELECT * FROM gwascatalog
							// WHERE fk_searchid="+id+" AND
							// snps='"+genesymbol+"'";
							querygwas = "SELECT DISTINCT * FROM gwascatalog WHERE fk_searchid=? AND snps=?";
							// System.out.println("SQL:"+query_ph);
							// Statement st_ph = connection.createStatement();
							stgwas = connection.prepareStatement(querygwas);
							stgwas.setInt(1, id);
							stgwas.setString(2, polid);
							rsgwas = stgwas.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// pid = rsi.getString(3); // "pubmedid"
							// rlg = rsi.getString(4); // "reported_genes"
							// ra = rsi.getString(5); //
							// "strongest_snp_risk_allele"
							// ct = rsi.getString(6); // "context"
							// pv = rsi.getString(9); // "pvalue"
							// dt = rsi.getString(7); // "disease_trait"
							// ss = rsi.getString(8); // "initial_sample_size"

							if (!rsgwas.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null" + "\t"
										+ "null" + "\t" + "null" + "\t" + "null");

							} else {
								rsgwas.first();
								fileWriter.write("\t" + rsgwas.getString(3) + "\t" + rsgwas.getString(4) + "\t"
										+ rsgwas.getString(5) + "\t" + rsgwas.getString(6) + "\t" + rsgwas.getString(9)
										+ "\t" + rsgwas.getString(7) + "\t" + rsgwas.getString(8));

							}

							// fileWriter.write("\t"+rsi.getString(3)+"\t"+rsi.getString(4)+"\t"+rsi.getString(5)+"\t"+rsi.getString(6)+"\t"+rsi.getString(9)+"\t"+rsi.getString(7)+"\t"+rsi.getString(8));

							// }
							// st_gc.close();
							// rs_gc.close();

							// fileWriter.write("\t"+pid+"\t"+rlg+"\t"+ra+"\t"+ct+"\t"+pv+"\t"+dt+"\t"+ss);
							// end gwascatalog

							// polyphen

							// String protid = "null";
							// String sub = "null";
							// String posProt = "null";
							// String pred = "null";
							// String prob = "null";
							// String fdr = "null";
							// String pph1pred = "null";

							querypph = "SELECT DISTINCT * FROM polyphen WHERE fk_searchid=? AND rsid=?";

							stpph = connection.prepareStatement(querypph);
							stpph.setInt(1, id);
							stpph.setString(2, polid);
							rspph = stpph.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// protid = rsi.getString(2); // "refSeqID"
							// sub = rsi.getString(3); // "substitution"
							// posProt = rsi.getString(4); // "aa position"
							// pred = rsi.getString(5); // "pph2 prediction"
							// prob = rsi.getString(6); // "pph2 prob"
							// fdr = rsi.getString(7); // "pph2 fdr"
							// pph1pred = rsi.getString(8); // "pph1 prediction"

							if (!rspph.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null" + "\t"
										+ "null" + "\t" + "null");

							} else {
								rspph.first();
								fileWriter.write("\t" + rspph.getString(2) + "\t" + rspph.getString(3) + "\t"
										+ rspph.getString(5) + "\t" + rspph.getString(6) + "\t" + rspph.getString(7)
										+ "\t" + rspph.getString(8));

							}

							// fileWriter.write("\t"+rsi.getString(2)+"\t"+rsi.getString(3)+"\t"+rsi.getString(5)+"\t"+rsi.getString(6)+"\t"+rsi.getString(7)+"\t"+rsi.getString(8));

							// System.out.println("\t"+protid+"\t"+sub+"\t"+pred+"\t"+prob+"\t"+fdr+"\t"+pph1pred);
							// }

							// fileWriter.write("\t"+protid+"\t"+sub+"\t"+pred+"\t"+prob+"\t"+fdr+"\t"+pph1pred);
							// end polyphen

							// PROVEAN

							// protid = "null";
							// sub = "null";
							// pred = "null";
							// String score = "null";
							// posProt = "null";
							// String spred = "null";
							// String sscore = "null";

							queryprv = "SELECT DISTINCT * FROM provean WHERE fk_searchid=? AND rsid=?";

							stprv = connection.prepareStatement(queryprv);
							stprv.setInt(1, id);
							stprv.setString(2, polid);
							rsprv = stprv.executeQuery();
							// while (rsi.next()){
							// rsi.first();
							// protid = rsi.getString(2); // "refSeqID"
							// sub = rsi.getString(3); // "substitution"
							// pred = rsi.getString(4); // "prediction"
							// score = rsi.getString(5); // "score"
							// posProt = rsi.getString(6); // "posProt"
							// spred = rsi.getString(8); // "siftpredict"
							// sscore = rsi.getString(7); // "siftscore"

							if (!rsprv.isBeforeFirst()) {

								fileWriter.write("\t" + "null" + "\t" + "null" + "\t" + "null" + "\t" + "null" + "\t"
										+ "null" + "\t" + "null" + "\t" + "null");

							} else {
								rsprv.first();
								fileWriter.write("\t" + rsprv.getString(2) + "\t" + rsprv.getString(3) + "\t"
										+ rsprv.getString(6) + "\t" + rsprv.getString(4) + "\t" + rsprv.getString(5)
										+ "\t" + rsprv.getString(8) + "\t" + rsprv.getString(7));

							}

							// fileWriter.write("\t"+rsi.getString(2)+"\t"+rsi.getString(3)+"\t"+rsi.getString(6)+"\t"+rsi.getString(4)+"\t"+rsi.getString(5)+"\t"+rsi.getString(8)+"\t"+rsi.getString(7));

							// System.out.println("\t"+protid+"\t"+sub+"\t"+posProt+"\t"+pred+"\t"+score+"\t"+protid+"\t"+sub+"\t"+spred+"\t"+sscore);
							// }

							// fileWriter.write("\t"+protid+"\t"+sub+"\t"+pred+"\t"+score);
							// fileWriter.write("\t"+protid+"\t"+sub+"\t"+posProt+"\t"+pred+"\t"+score+"\t"+spred+"\t"+sscore);
							// end provean

							// // sift
							//
							// protid = "null";
							// sub = "null";
							// pred = "null";
							// score = "null";
							//
							// queryi = "SELECT * FROM sift WHERE fk_searchid=?
							// AND snps=?";
							//
							// sti = connection.prepareStatement(queryi);
							// sti.setInt(1,id);
							// sti.setString(2,polid);
							// rsi = sti.executeQuery();
							// while (rsi.next()){
							// protid = rsi.getString(2); // "refSeqID"
							// sub = rsi.getString(3); // "substitution"
							// pred = rsi.getString(4); // "prediction"
							// score = rsi.getString(4); // "score"
							// }
							//
							// fileWriter.write("\t"+protid+"\t"+sub+"\t"+pred+"\t"+score);
							// // end sift

							fileWriter.newLine();

						} // End of IF ANALYSIS = 3
					} // End of WHILE rs

				} // end iterator

				fileWriter.close();
				// st.close();
				// rs.close();
				stdsnp.close();
				rssnp.close();

				if (getAnalysistype() == 3) { /* local complete */

					// stdsnp.close();
					stucsc.close();
					streac.close();
					stpgkb.close();
					stgo.close();
					stomim.close();
					sthgnc.close();
					stgwas.close();
					stpph.close();
					stprv.close();
					// stsift.close();

					// rssnp.close();
					rsucsc.close();
					rsreac.close();
					rspgkb.close();
					rsgo.close();
					rsomim.close();
					rshgnc.close();
					rsgwas.close();
					rspph.close();
					rsprv.close();
					// rssift.close();

				}

				// Write Summary File
				// writeSummaryDB();

				// myconnection.mysqlDisconnect();

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // End of try

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished writing annotated report on " + datapath
					+ annotateddatafilename);
			myconnection.mysqlDisconnect();
			end = System.currentTimeMillis(); // set end time
			exectime = (end - start) / 1000;
			System.out.println("TOTAL ANNOTATION TIME: " + exectime + "s");

			return 0;
		}
	} // END OF CLASS WRITE ANNOTATED DB

	class writeAnnotatedDB2 extends OneShotBehaviour {
		BufferedWriter fileWriter;
		PreparedStatement st;
		PreparedStatement sti;
		String queryi;
		ResultSet rs;
		ResultSet rsi;
		int searchid;

		public writeAnnotatedDB2() {

		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " retrieving annotation from database...");

			try {

				fileWriter = new BufferedWriter(new FileWriter(datapath + annotateddatafilename));

				connection = myconnection.mysqlConnect();
				Iterator itr = searchidlist.iterator();

				if (getAnalysistype() == 1) { /* local basic */
					fileWriter.write(
							"(1)Polymorphism Id \t (2)Polymorphism Type \t (3)Gene Symbol \t (4)Gene ID \t (5)Transcript Region \t (6)Nucleotide Numbering coding DNA \t (7)Chromosome \t (8)Chromosome Position \t (9)Ancestral Allele \t (10)Orientation \t (11)Assembly Build Version \t (12)Assembly Coord Start \t (13)Assembly Coord End \t  (14)mRNA accession \t (15)mRNA version \t (16)Alleles \t (17)Frequency");
					fileWriter.newLine();

					while (itr.hasNext()) {

						int id = (Integer) itr.next();

						// String query = "SELECT * FROM snp,gene WHERE
						// snp.fk_searchid="+id+" AND
						// gene.genepk=snp.fk_genepk";
						String query = "SELECT * FROM snp,gene WHERE snp.fk_searchid=? AND gene.genepk=snp.fk_genepk";

						// Statement st = connection.createStatement();
						st = connection.prepareStatement(query);
						st.setInt(1, id);
						rs = st.executeQuery();

						while (rs.next()) {

							fileWriter.write("rs" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(22)
									+ "\t" + rs.getString(23) + "\t" + rs.getString(4) + "\t" + rs.getString(7) + "\t"
									+ rs.getString(8) + "\t" + rs.getString(7) + "\t" + rs.getString(14) + "\t"
									+ rs.getString(15) + "\t" + rs.getString(10) + "\t" + rs.getString(11) + "\t"
									+ rs.getString(12) + "\t" + rs.getString(16) + "\t" + rs.getString(17) + "\t"
									+ rs.getString(5) + "\t" + rs.getString(18));

						}
					} // END OF WHILE

				} // END OF IF TYPE 1

				if (getAnalysistype() == 3) { /* local complete */
					// fileWriter.write("(1)Polymorphism Id \t (2)Polymorphism
					// Type \t (3)Gene Symbol \t (4)Gene ID \t (5)Transcript
					// Region \t (6)Nucleotide Numbering coding DNA \t
					// (7)Chromosome \t (8)Chromosome Position \t (9)Ancestral
					// Allele \t (10)Orientation \t (11)Assembly Build Version
					// \t (12)Assembly Coord Start \t (13)Assembly Coord End \t
					// (14)mRNA accession \t (15)mRNA version \t (16)Alleles \t
					// (17)Frequency \t (18)Strand \t (19)UCSC Reference \t
					// (20)UCSC Observed \t (21)UCSC Polymorphism Class \t
					// (22)UCSC Functional Class \t (23)Pathway \t (24)Disease
					// \t (25)Drug \t (26)Related Genes \t (27)Biological
					// Process \t (28)Molecular Function \t (29)Celular
					// Component \t (30)Disorder \t (31)Comments \t
					// (32)CytoLoc");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)\tSpecialistDBLinks(46)\tLocusSpecDB(47)\tEnzymeId(48)\tEntrezId(49)\tEnsemblId(50)\tPubMedIds(51)\tRefSeqIds(52)\tCCDSIds(53)\tVegaIds(54)\tUniProtId(55)\tMouseGdbId(56)\tRatGdbId(57)");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)");
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)");
					fileWriter.write(
							"PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneSymbol(39)\tGeneName(40)\tGeneSynonyms(41)\tLocusType(42)\tLocusGroup(43)\tGeneFamilyTag(44)\tGeneFamily(45)\tPubmed(46)\tReported_Genes(47)\tStrongest_SNP_Risk_Allele(48)\tContext(49)\tP-Value(50)\tDisease_Trait(51)\tSample_and_Population(52)");
					fileWriter.newLine();

					while (itr.hasNext()) {

						int id = (Integer) itr.next();

						String query = "SELECT * FROM snp,gene WHERE snp.fk_searchid=? AND gene.genepk=snp.fk_genepk";

						String strsql = "select snp.snpid, " + // 1
								"snp.kind, " + // 2
								"gene.gene_symbol, " + // 3
								"gene.geneid, " + // 4
								"snp.subkind, " + // 5
								"snp.coordRelGene, " + // 6
								"snp.chromosome, " + // 7
								"snp.coordRefSeq, " + // 8
								"snp.ancestral_allele, " + // 9
								"snp.orientation, " + // 10
								"snp.assm_build_version, " + // 11
								"snp.assm_coord_start, " + // 12
								"snp.assm_coord_end, " + // 13
								"snp.mrnaAcc, " + // 14
								"snp.mrnaVer, " + // 15
								"snp.referenceValue, " + // 16
								"snp.freq, " + // 17
								"ucsc.strand, " + // 18
								"ucsc.refUCSC, " + // 19
								"ucsc.observed, " + // 20
								"ucsc.class, " + // 21
								"ucsc.func, " + // 22
								"pharmGKB.pathway, " + // 23
								"pharmGKB.drugs, " + // 24
								"pharmGKB.disease, " + // 25
								"pharmGKB.geneCross, " + // 26
								"geneOntology.molFunction, " + // 27
								"geneOntology.celComp, " + // 28
								"geneOntology.bioProcess, " + // 29
								"omim.cytoLoc, " + // 30
								"omim.geneStatus, " + // 31
								"omim.methods, " + // 32
								"omim.disorder, " + // 33
								"omim.mimId, " + // 34
								"omim.inheritance, " + // 35
								"omim.phenmap, " + // 36
								"omim.comments, " + // 37
								"hugoDB.hgncId, " + // 38
								"hugoDB.approvedSymbol, " + // 39
								"hugoDB.approvedName, " + // 40
								"hugoDB.synonyms, " + // 41
								"hugoDB.locusType, " + // 42
								"hugoDB.locusGroup, " + // 43
								"hugoDB.geneFamilyTag, " + // 44
								"hugoDB.geneFamilyDesc, " + // 45
								"gwascatalog.pubmedid, " + // 46
								"gwascatalog.reported_genes, " + // 47
								"gwascatalog.strongest_snp_risk_allele, " + // 48
								"gwascatalog.context, " + // 49
								"gwascatalog.pvalue, " + // 50
								"gwascatalog.disease_trait, " + // 51
								"gwascatalog.initial_sample_size " + // 52
								"FROM snp " + "LEFT JOIN gene ON snp.fk_genepk = gene.genepk "
								+ "LEFT JOIN ucsc ON snp.snpid = ucsc.polID "
								+ "LEFT JOIN pharmGKB ON gene.gene_symbol = pharmGKB.geneSymbol "
								+ "LEFT JOIN geneOntology ON gene.gene_symbol = geneOntology.gp_symbol "
								+ "LEFT JOIN omim ON gene.gene_symbol = omim.geneSymbols "
								+ "LEFT JOIN hugoDB ON gene.gene_symbol = hugoDB.approvedSymbol "
								+ "LEFT JOIN gwascatalog ON snp.snpid = gwascatalog.snps " + "WHERE snp.fk_searchid=?;";

						// "INTO OUTFILE LOCAL '/home/mysql/annotationteste.txt
						// " +

						// Statement st = connection.createStatement();
						st = connection.prepareStatement(strsql);
						st.setInt(1, id);
						rs = st.executeQuery();

						while (rs.next()) {

							fileWriter.write("rs" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3)
									+ "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t"
									+ rs.getString(7) + "\t" + rs.getString(8) + "\t" + rs.getString(9) + "\t"
									+ rs.getString(10) + "\t" + rs.getString(11) + "\t" + rs.getString(12) + "\t"
									+ rs.getString(13) + "\t" + rs.getString(14) + "\t" + rs.getString(15) + "\t"
									+ rs.getString(16) + "\t" + rs.getString(17) + "\t" + rs.getString(18) + "\t"
									+ rs.getString(19) + "\t" + rs.getString(20) + "\t" + rs.getString(21) + "\t"
									+ rs.getString(22) + "\t" + rs.getString(23) + "\t" + rs.getString(24) + "\t"
									+ rs.getString(25) + "\t" + rs.getString(26) + "\t" + rs.getString(27) + "\t"
									+ rs.getString(28) + "\t" + rs.getString(29) + "\t" + rs.getString(30) + "\t"
									+ rs.getString(31) + "\t" + rs.getString(32) + "\t" + rs.getString(33) + "\t"
									+ rs.getString(34) +
									// "\t"+rs.getString(35)+
									// "\t"+rs.getString(36)+
									"\tnull" + "\tnull" + "\t" + rs.getString(37) + "\t" + rs.getString(38) + "\t"
									+ rs.getString(39) + "\t" + rs.getString(40) + "\t" + rs.getString(41) + "\t"
									+ rs.getString(42) + "\t" + rs.getString(43) + "\t" + rs.getString(44) + "\t"
									+ rs.getString(45) + "\t" + rs.getString(46) + "\t" + rs.getString(47) + "\t"
									+ rs.getString(48) + "\t" + rs.getString(49) + "\t" + rs.getString(50) + "\t"
									+ rs.getString(51) + "\t" + rs.getString(52) + "\n");

						}

					} // End while itr

				} // End IF analysis 3

				fileWriter.close();

			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished writing annotated report on " + datapath
					+ annotateddatafilename);
			myconnection.mysqlDisconnect();
			end = System.currentTimeMillis(); // set end time
			exectime = (end - start) / 1000;
			System.out.println("TOTAL ANNOTATION TIME: " + exectime + "s");

			return 0;
		}
	} // END OF CLASS WRITE ANNOTATED DB

	class cleanDB extends OneShotBehaviour {

		public cleanDB() {}

		public void action() {

			// INICIO PARTE COMENTADA 07/01/2013
			System.out.println("Agent " + getLocalName() + " cleanning up temporary annotation on local database.");
			try {
				connection = myconnection.mysqlConnect();
				Statement st = connection.createStatement();

				Iterator itr = searchidlist.iterator();
				while (itr.hasNext()) {
					int id = (Integer) itr.next();

					// delete from all local agent tables
					ResultSet rs = st.executeQuery(
							"SELECT table_name \"TABLES\" FROM information_schema.TABLES WHERE TABLES.TABLE_SCHEMA = 'annotation'");
					while (rs.next()) {
						String tablename = rs.getString("TABLES");
						if (!tablename.equals("gene")) {
							if (!tablename.equals("snp")) {
								if (!tablename.equals("search")) {
									System.out.println("Deleting from " + tablename);
									String sql = "DELETE FROM " + tablename;
									Statement st1 = connection.createStatement();
									// String sql = "DELETE FROM ?";
									// PreparedStatement st1 =
									// connection.prepareStatement(sql);
									// st1.setString(1,tablename);
									// System.out.println("Before : " +
									// st1.toString());
									st1.executeUpdate(sql);
									st1.close();
								}
							}
						}

					} // End of rs.next while
					rs.close();
					// end

					// delete from table snp
					// String sql = "DELETE FROM snp WHERE fk_searchid="+id;
					String sql = "DELETE FROM snp WHERE fk_searchid=?";
					PreparedStatement snpDelete = connection.prepareStatement(sql);
					snpDelete.setInt(1, id);
					int deletecount = snpDelete.executeUpdate();
					snpDelete.close();

					// int deletecount = st.executeUpdate(sql);
					annrscount = annrscount + deletecount;

					// delete from table gene
					// String sql1 = "DELETE FROM gene WHERE genepk!=1 AND
					// fk_searchid="+id;
					// st.executeUpdate(sql1);
					String sql1 = "DELETE FROM gene WHERE genepk!=1 AND fk_searchid=?";
					PreparedStatement geneDelete = connection.prepareStatement(sql1);
					geneDelete.setInt(1, id);
					geneDelete.executeUpdate();
					geneDelete.close();

					// delete from table search
					// String sql2 = "DELETE FROM search WHERE searchid="+id;
					// st.executeUpdate(sql2);
					String sql2 = "DELETE FROM search WHERE searchid=?";
					PreparedStatement searchDelete = connection.prepareStatement(sql2);
					searchDelete.setInt(1, id);
					searchDelete.executeUpdate();
					searchDelete.close();

				} // end iterator

				st.close();
				myconnection.mysqlDisconnect(); // Mudanca feita 07/01/2013

			} catch (SQLException e) {
				e.printStackTrace();
			}

			// FINAL PARTE COMENTADA 07/01/2013 */
		}

	}

	class preclearDB extends OneShotBehaviour {

		public preclearDB() {

		}

		public void action() {

			if (getAnalysistype() == 1 || getAnalysistype() == 3) {

				System.out.println("Pre-clearing AnnotationDB...");

				try {
					connection = myconnection.mysqlConnect();
					Statement st = connection.createStatement();

					// delete from all local agent tables

					ResultSet rs = st.executeQuery(
							"SELECT table_name \"TABLES\" FROM information_schema.TABLES WHERE TABLES.TABLE_SCHEMA = 'annotation'");

					while (rs.next()) {
						String tablename = rs.getString("TABLES");
						if (!tablename.equals("gene")) {
							if (!tablename.equals("snp")) {
								if (!tablename.equals("search")) {
									System.out.println("Deleting from " + tablename);
									String sql = "DELETE FROM " + tablename;
									Statement st1 = connection.createStatement();
									st1.executeUpdate(sql);
								}
							}
						}

					} // End of rs.next while
					rs.close();

					// end

					String sql = "DELETE FROM snp";
					st.executeUpdate(sql);

					String sql1 = "DELETE FROM gene WHERE genepk!=1";
					st.executeUpdate(sql1);

					String sql2 = "DELETE FROM search WHERE searchid!=1";
					st.executeUpdate(sql2);

					st.close();
					myconnection.mysqlDisconnect();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	class clearBDObjects extends OneShotBehaviour {

		public clearBDObjects() {

		}

		public void action() {

		}
	}

	class writeAnnotatedDataFile extends OneShotBehaviour {

		BufferedWriter fileWriter;
		String[] values;

		public writeAnnotatedDataFile() {

		}

		public void action() {

			// System.out.println("Agent " + getLocalName() +" writing
			// "+datapath+annotateddatafilename+"...");

			try {
				fileWriter = new BufferedWriter(new FileWriter(datapath + annotateddatafilename));

				if (getAnalysistype() == 2) {
					fileWriter.write(
							"PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)");
					fileWriter.newLine();

					Iterator<BioData> itr = biodatalist.iterator();
					while (itr.hasNext()) {

						BioData annotateddata = (BioData) itr.next();

						for (Polymorphism p : annotateddata.getPolymorphismList()) {
							annrscount++;
							// fileWriter.write("rs"+p.getPolymorphismCode()+"\t"+p.getKind()+"\t"+p.getSubKind()+"\t"+p.getReferenceValue()+"\t"+p.getReferenceAllele()+"\t"+p.getCoordRelGene()+"\t"+p.getChromosome()+"\t"+p.getCoordRefSeq()+"\t"+p.getAssm_build_version()+"\t"+p.getAssm_coord_start()+"\t"+p.getAssm_coord_end()+"\t"+p.getGenename()+"\t"+p.getValue()+"\t"+p.getAncestralAllele()+"\t"+p.getOrientation()+"\t"+p.getMrnaAcc()+"\t"+p.getMrnaVer()+"\t"+p.getFreq());
							fileWriter.write("rs" + p.getPolymorphismCode() + "\t" + p.getKind() + "\t"
									+ p.getGenesymbol() + "\t" + p.getGeneid() + "\t" + p.getSubKind() + "\t"
									+ p.getCoordRelGene() + "\t" + p.getChromosome() + "\t" + p.getCoordRefSeq() + "\t"
									+ p.getAncestralAllele() + "\t" + p.getOrientation() + "\t"
									+ p.getAssm_build_version() + "\t" + p.getAssm_coord_start() + "\t"
									+ p.getAssm_coord_end() + "\t" + p.getMrnaAcc() + "\t" + p.getMrnaVer() + "\t"
									+ p.getReferenceAllele() + "\t" + p.getFreq());
							fileWriter.newLine();
						}

					} // end iterator
				} // end =2

				if (getAnalysistype() == 4) {
					// fileWriter.write("PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneName(39)\tGeneSynonyms(40)\tLocusType(41)\tLocusGroup(42)\tGeneFamilyTag(43)\tGeneFamily(44)\tLocusSpecDB(45)\tEnzymeId(46)\tEntrezId(47)\tEnsemblId(48)\tPubMedIds(49)\tRefSeqIds(50)\tCCDSIds(51)\tVegaIds(52)\tUniProtId(53)\tMouseGdbId(54)\tRatGdbId(55)");
					fileWriter.write(
							"PolymorphismId(1)\tPolymorphismType(2)\tGeneSymbol(3)\tGeneId(4)\tTranscriptRegion(5)\tNucleotideNumberingCodingDNA(6)\tChromosome(7)\tChromosomePosition(8)\tAncestralAllele(9)\tOrientation(10)\tAssemblyBuildVersion(11)\tAssemblyCoordStart(12)\tAssemblyCoordEnd(13)\tmRNAaccession(14)\tmRNAversion(15)\tAlleles(16)\tFrequency(17)\tStrand(18)\tRefUCSC(19)\tObservedUCSC(20)\tPolymorphismClass(21)\tFunctionalClass(22)\tPathways(23)\tDrugs(24)\tDisease(25)\tRelatedGenes(26)\tGOMolecularFunction(27)\tGOCellularComponent(28)\tGOBiologicalProcess(29)\tCytoloc(30)\tGeneStatus(31)\tGeneMapMethods(32)\tDisorders(33)\tMIMids(34)\tInheritance(35)\tPhenoMapMethods(36)\tComments(37)\tHgncId(38)\tGeneName(39)\tGeneSynonyms(40)\tLocusType(41)\tLocusGroup(42)\tGeneFamilyTag(43)\tGeneFamily(44)");
					fileWriter.newLine();

					Iterator<BioData> itr = biodatalist.iterator();
					while (itr.hasNext()) {

						BioData annotateddata = (BioData) itr.next();

						for (Polymorphism p : annotateddata.getPolymorphismList()) {
							annrscount++;
							// fileWriter.write("rs"+p.getPolymorphismCode()+"\t"+p.getKind()+"\t"+p.getSubKind()+"\t"+p.getReferenceValue()+"\t"+p.getReferenceAllele()+"\t"+p.getCoordRelGene()+"\t"+p.getChromosome()+"\t"+p.getCoordRefSeq()+"\t"+p.getAssm_build_version()+"\t"+p.getAssm_coord_start()+"\t"+p.getAssm_coord_end()+"\t"+p.getGenename()+"\t"+p.getValue()+"\t"+p.getAncestralAllele()+"\t"+p.getOrientation()+"\t"+p.getMrnaAcc()+"\t"+p.getMrnaVer()+"\t"+p.getFreq());
							fileWriter.write("rs" + p.getPolymorphismCode() + "\t" + p.getKind() + "\t"
									+ p.getGenesymbol() + "\t" + p.getGeneid() + "\t" + p.getSubKind() + "\t"
									+ p.getCoordRelGene() + "\t" + p.getChromosome() + "\t" + p.getCoordRefSeq() + "\t"
									+ p.getAncestralAllele() + "\t" + p.getOrientation() + "\t"
									+ p.getAssm_build_version() + "\t" + p.getAssm_coord_start() + "\t"
									+ p.getAssm_coord_end() + "\t" + p.getMrnaAcc() + "\t" + p.getMrnaVer() + "\t"
									+ p.getReferenceAllele() + "\t" + p.getFreq() + "\t" + p.getStrand() + "\t"
									+ p.getRefUCSC() + "\t" + p.getObsGen() + "\t" + p.getUcscClass() + "\t"
									+ p.getUcscFunc() + "\t");
							// FOR PHARMGKB
							// pathway list
							Iterator<String> itp = p.getPathwayList().iterator();
							while (itp.hasNext()) {
								String pw = (String) itp.next();
								fileWriter.write(pw + ";");
							}
							if (p.getPathwayList().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// drugs list
							Iterator<String> itd = p.getDrugList().iterator();
							while (itd.hasNext()) {
								String dg = (String) itd.next();
								fileWriter.write(dg + ";");
							}
							if (p.getDrugList().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// disease list
							Iterator<String> its = p.getDiseaseList().iterator();
							while (its.hasNext()) {
								String ds = (String) its.next();
								fileWriter.write(ds + ";");
							}
							if (p.getDiseaseList().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// genex list
							Iterator<String> itg = p.getGenexList().iterator();
							while (itg.hasNext()) {
								String gx = (String) itg.next();
								fileWriter.write(gx + ";");
							}
							if (p.getGenexList().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// FOR GO
							// molfuncion list
							Iterator<String> itmf = p.getMolFunction().iterator();
							while (itmf.hasNext()) {
								String mf = (String) itmf.next();
								fileWriter.write(mf + ";");
							}
							if (p.getMolFunction().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// celcomponent list
							Iterator<String> itcc = p.getCelComponent().iterator();
							while (itcc.hasNext()) {
								String cc = (String) itcc.next();
								fileWriter.write(cc + ";");
							}
							if (p.getCelComponent().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// bioprocess list
							Iterator<String> itbp = p.getBioProcess().iterator();
							while (itbp.hasNext()) {
								String bp = (String) itbp.next();
								fileWriter.write(bp + ";");
							}
							if (p.getBioProcess().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");

							// FOR OMIM
							fileWriter.write(
									p.getCytoloc() + "\t" + p.getGenestatus() + "\t" + p.getGenemapmethods() + "\t");
							// disorders list
							Iterator<String> itdo = p.getDisorder().iterator();
							while (itdo.hasNext()) {
								String dor = (String) itdo.next();
								fileWriter.write(dor + ";");
							}
							if (p.getDisorder().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// mimid list
							Iterator<String> itmim = p.getMimID().iterator();
							while (itmim.hasNext()) {
								String mim = (String) itmim.next();
								fileWriter.write(mim + ";");
							}
							if (p.getMimID().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// inheritance list
							Iterator<String> itinh = p.getInheritance().iterator();
							while (itinh.hasNext()) {
								String inh = (String) itinh.next();
								fileWriter.write(inh + ";");
							}
							if (p.getInheritance().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// phenomap methods list
							Iterator<String> itpmm = p.getPhenoMapMethods().iterator();
							while (itpmm.hasNext()) {
								String pmm = (String) itpmm.next();
								fileWriter.write(pmm + ";");
							}
							if (p.getPhenoMapMethods().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");
							// comments list
							Iterator<String> itcom = p.getComments().iterator();
							while (itcom.hasNext()) {
								String com = (String) itcom.next();
								fileWriter.write(com + ";");
							}
							if (p.getComments().size() == 0) {
								fileWriter.write("null");
							}
							fileWriter.write("\t");

							// for HUGO
							// fileWriter.write(p.getHgncId()+"\t"+p.getHgGeneName()+"\t"+p.getGeneSynonyms()+"\t"+p.getLocusType()+"\t"+p.getLocusGroup()+"\t"+p.getGeneFamilyTag()+"\t"+p.getGeneFamily()+"\t"+p.getLocusSpecDB()+"\t"+p.getEnzymeId()+"\t"+p.getEntrezId()+"\t"+p.getEnsemblId()+"\t"+p.getPubMedIds()+"\t"+p.getRefSeqIds()+"\t"+p.getCCDSIds()+"\t"+p.getVegaIds()+"\t"+p.getUniProtId()+"\t"+p.getMouseGdbId()+"\t"+p.getRatGdbId());
							fileWriter.write(p.getHgncId() + "\t" + p.getHgGeneName() + "\t" + p.getGeneSynonyms()
									+ "\t" + p.getLocusType() + "\t" + p.getLocusGroup() + "\t" + p.getGeneFamilyTag()
									+ "\t" + p.getGeneFamily());
							// System.out.println("INTERFACE - Symbol:
							// "+p.getGenesymbol()+" Name: "+p.getHgGeneName()+"
							// Locus Type: "+p.getLocusType()+" Group:
							// "+p.getLocusGroup()+" Synonyms:
							// "+p.getGeneSynonyms()+" Tag:
							// "+p.getGeneFamilyTag()+" Family:
							// "+p.getGeneFamily()+" DBs:
							// "+p.getSpecialistDBLinks());

							// END values
							fileWriter.newLine();

						} // end for

					} // end iterator
				} // end =4

				fileWriter.close();
			} catch (IOException e) { // End readFromFile
				e.printStackTrace();
			}
		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " finished writing annotated file on " + datapath
					+ annotateddatafilename);
			end = System.currentTimeMillis(); // set end time
			exectime = (end - start) / 1000;
			System.out.println("TOTAL ANNOTATION TIME: " + exectime + "s");

			return 0;
		}

	}

	public void EnrichmentTestRemote(String column, Map<String, MutableInt> miniMap, Map<String, String> completeMap,
			int sampleSize) {

		// System.out.println("Enrichment Test 1 - Sample Matrix Size = "+
		// miniMap.size()+" Population Size = "+ completeMap.size());

		Iterator<Entry<String, MutableInt>> itrE1 = miniMap.entrySet().iterator();
		// Iterator<Entry<String, String>> itrE2 =
		// completeMap.entrySet().iterator();

		while (itrE1.hasNext()) {

			// System.out.println("Enrichment Test 2 - Sample Matrix Size = "+
			// miniMap.size()+" Population Size = "+ completeMap.size());
			Iterator<Entry<String, String>> itrE2 = completeMap.entrySet().iterator();
			Map.Entry<String, MutableInt> entry1 = itrE1.next();

			while (itrE2.hasNext()) {

				// System.out.println("Enrichment Test 3 - Sample Matrix Size =
				// "+ miniMap.size()+" Population Size = "+ completeMap.size());
				Map.Entry<String, String> entry2 = itrE2.next();

				// System.out.println("Enrichment Test 4 - Sample = "+
				// entry1.getKey()+" Population = "+ entry2.getKey());

				if (entry1.getKey().equalsIgnoreCase(entry2.getKey()) && !entry1.getKey().contains("null")) {

					// System.out.println("Enrichment Test 4 - Sample Matrix
					// Size = "+ miniMap.size()+" Population Size = "+
					// completeMap.size());

					// String countMiniMap = miniMap.get().getValue("Count1");
					// MutableInt count1 = miniMap.get("COUNT1");
					// int countMiniMap = count1.get();
					String countCompleteMap = completeMap.get("COUNT");

					// if(column.equalsIgnoreCase("PharmGKB Drugs")){
					// System.out.println("Sample Size " + sampleSize);
					// System.out.println("Sample IN " + entry1.getKey());
					// System.out.println("Sample IN " +
					// entry1.getValue().getGeneCount());
					// System.out.println("Sample OUT " +
					// (sampleSize-Integer.valueOf(entry1.getValue().getGeneCount())));
					// System.out.println("Sample IN " + entry1.getKey());
					// System.out.println("Pop IN " +
					// Integer.valueOf(entry2.getValue()));
					// System.out.println("Pop OUT " +
					// (Integer.valueOf(countCompleteMap)-Integer.valueOf(entry2.getValue())));
					// }
					ContingencyTable2x2 table = new ContingencyTable2x2(
							Integer.valueOf(entry1.getValue().getGeneCount()),
							sampleSize - Integer.valueOf(entry1.getValue().getGeneCount()),
							Integer.valueOf(entry2.getValue()),
							Integer.valueOf(countCompleteMap) - Integer.valueOf(entry2.getValue()));

					FishersExactTest FisherTest = new FishersExactTest(table);
					// System.out.println("Term - "+ entry1.getKey() +" ApproxSP
					// = " + FisherTest.getApproxSP()+ " ChiSquared = " +
					// FisherTest.getChiSquared() + " TailedMidP = " +
					// FisherTest.getOneTailedMidP()+
					// " TailedP = " + FisherTest.getOneTailedSP() + " OppsTail
					// = " + FisherTest.getOppositeTailProb()+ " SP = " +
					// FisherTest.getSP() +
					// " Statisc = " + FisherTest.getTestStatistic()) ;

					// BonferroniCorrection pValueAdj = new
					// BonferroniCorrection();
					// pValueAdj.adjust(0.05, minimap.size());
					// System.out.println("AdjPValue = "+ pValueAdj.toString());

					try {
						// efileWriter.write("Term - "+ entry1.getKey()
						// +"\tTailedP = " + FisherTest.getOneTailedSP()+"\n") ;
						// efileWriter.write(entry1.getKey() +"\t"+
						// entry1.getValue().get()+"\t"+
						// entry1.getValue().getGeneCount()+"\t"+entry1.getValue().getGene());
						efileWriter.write(column + "\t" + entry1.getKey() + "\t" + FisherTest.getOneTailedSP() + "\t"
								+ entry2.getValue() + "\t" + entry1.getValue().toString() + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// FishersExactTest FisherTest2 = new
					// FishersExactTest(Table,H1.LESS_THAN);
					// System.out.println("LESS Gene - "+ entry1.getKey() +"
					// ApproxSP = " + FisherTest2.getApproxSP()+ " ChiSquared =
					// " + FisherTest2.getChiSquared() + " TailedMidP = " +
					// FisherTest.getOneTailedMidP()+
					// " TailedP = " + FisherTest2.getOneTailedSP() + " OppsTail
					// = " + FisherTest2.getOppositeTailProb()+ " SP = " +
					// FisherTest2.getSP() +
					// " Statisc = " + FisherTest2.getTestStatistic()) ;
					//
					// FishersExactTest FisherTest3 = new
					// FishersExactTest(Table,H1.GREATER_THAN);
					// System.out.println("GREATER Gene - "+ entry1.getKey() +"
					// ApproxSP = " + FisherTest3.getApproxSP()+ " ChiSquared =
					// " + FisherTest3.getChiSquared() + " TailedMidP = " +
					// FisherTest.getOneTailedMidP()+
					// " TailedP = " + FisherTest3.getOneTailedSP() + " OppsTail
					// = " + FisherTest3.getOppositeTailProb()+ " SP = " +
					// FisherTest3.getSP() +
					// " Statisc = " + FisherTest3.getTestStatistic()) ;
					//
					// FishersExactTest FisherTest4 = new
					// FishersExactTest(Table,H1.NOT_EQUAL);
					// System.out.println("NOT Gene - "+ entry1.getKey() +"
					// ApproxSP = " + FisherTest4.getApproxSP()+ " ChiSquared =
					// " + FisherTest4.getChiSquared() + " TailedMidP = " +
					// FisherTest.getOneTailedMidP()+
					// " TailedP = " + FisherTest4.getOneTailedSP() + " OppsTail
					// = " + FisherTest4.getOppositeTailProb()+ " SP = " +
					// FisherTest4.getSP() +
					// " Statisc = " + FisherTest4.getTestStatistic()) ;

					// ChiSquaredTest ChiTest = new ChiSquaredTest(Table);
					// System.out.println("Gene - "+ entry1.getKey() +" SP = " +
					// ChiTest.getSP()+ " ChiSquared = " +
					// ChiTest.getTestStatistic());

					break;
				} // End IF

			} // END WHILE 2

		} // END WHILE 1

	} // End of Remote Enrichment Test

	public void EnrichmentTestLocal(Map<String, String> miniMap, Map<String, String> completeMap, int sampleSize) {

		Iterator<Entry<String, String>> itrE1 = miniMap.entrySet().iterator();

		while (itrE1.hasNext()) {

			Iterator<Entry<String, String>> itrE2 = completeMap.entrySet().iterator();
			Map.Entry<String, String> entry1 = itrE1.next();

			while (itrE2.hasNext()) {

				Map.Entry<String, String> entry2 = itrE2.next();

				// System.out.println(entry1.getKey());
				// System.out.println(entry1.getValue());
				// System.out.println(entry2.getKey());
				// System.out.println(entry2.getValue());
				// System.out.println(entry1.getKey()+""+entry1.getValue()+""+entry2.getKey()+""+entry2.getValue());

				if (entry2.getKey().equalsIgnoreCase(entry1.getKey())) {

					String countCompleteMap = completeMap.get("COUNT");

					ContingencyTable2x2 table = new ContingencyTable2x2(Integer.valueOf(entry1.getValue()),
							sampleSize - Integer.valueOf(entry1.getValue()), Integer.valueOf(entry2.getValue()),
							Integer.valueOf(countCompleteMap) - Integer.valueOf(entry2.getValue()));

					FishersExactTest FisherTest = new FishersExactTest(table);
					// System.out.println("Term - "+ entry1.getKey() +" ApproxSP
					// = " + FisherTest.getApproxSP()+ " ChiSquared = " +
					// FisherTest.getChiSquared() + " TailedMidP = " +
					// FisherTest.getOneTailedMidP()+
					// " TailedP = " + FisherTest.getOneTailedSP() + " OppsTail
					// = " + FisherTest.getOppositeTailProb()+ " SP = " +
					// FisherTest.getSP() +
					// " Statisc = " + FisherTest.getTestStatistic()) ;

					try {
						efileWriter.write(
								"Term - " + entry1.getKey() + "\tTailedP = " + FisherTest.getOneTailedSP() + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				} // End IF

			} // END WHILE 2

		} // END WHILE 1

		try {
			efileWriter.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // End of Local Enrichment Test

	/*
	 * Finds providers and sends msg Parameters: String service_type, String
	 * info_type, String Performative, BioData data
	 */
	class FindAndSend extends OneShotBehaviour {

		private String type_of_service;
		private AID[] providers;
		private ACLMessage msg;
		private AID msgreceiver;
		private String msgperformative;
		private BioDataLite msgcontentlite;
		private BioData msgcontent;

		public FindAndSend(String st, String p, BioDataLite d) {
			type_of_service = st;
			msgperformative = p;
			msgcontentlite = d;
		}

		public FindAndSend(String st, String p, BioData d) {
			type_of_service = st;
			msgperformative = p;
			msgcontent = d;
		}

		public void action() {

			// FINDING AGENTS
			System.out.println("Agent " + getLocalName() + " searching for service type \"" + type_of_service + "\"");
			try {
				// Build the description used as template for the search
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription templateSd = new ServiceDescription();
				templateSd.setType(type_of_service);
				template.addServices(templateSd);

				DFAgentDescription[] results = DFService.search(myAgent, template);
				if (results.length > 0) {
					providers = new AID[results.length];
					for (int i = 0; i < results.length; ++i) {
						System.out.println("... found agent \"" + results[i].getName());
						DFAgentDescription dfd = results[i];
						providers[i] = dfd.getName();
					}
				} else {
					System.out.println(
							"Agent " + getLocalName() + " did not find any agent for the required information.");
					this.done();
				}
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}

			// SENDING MSG
			if (providers != null) {
				msgreceiver = providers[0];
				try {
					System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
					if (msgperformative == "REQUEST") {
						msg = new ACLMessage(ACLMessage.REQUEST);
					}
					msg.addReceiver(msgreceiver);
					msg.setLanguage("English");

					if (getAnalysistype() == 1 || getAnalysistype() == 3) {
						msg.setContentObject(msgcontentlite);
					}
					if (getAnalysistype() == 2 || getAnalysistype() == 4) {
						msg.setContentObject(msgcontent);
					}

					myAgent.send(msg);

					addRequestCount();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}// END FINDANDSEND CLASS

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String s) {
		this.servicetype = s;
	}

	public String getInfoType() {
		return infotype;
	}

	public void setInfoType(String i) {
		this.infotype = i;
	}

	protected void register() {
		/* Register with DF */
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			System.out.println(
					"Agent " + getLocalName() + " registering service type \"" + this.getServicetype() + "\" with DF");

			ServiceDescription sd = new ServiceDescription();
			sd.setName(this.getInfoType());
			sd.setType(this.getServicetype());
			dfd.addServices(sd);
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	public String getDatafilename() {
		return datafilename;
	}

	public void setDatafilename(String datafilename) {
		this.datafilename = datafilename;

	}

	public int getAnalysistype() {
		return analysistype;
	}

	public void setAnalysistype(int analysistype) {
		this.analysistype = analysistype;
	}

	public String getAnnotateddatafilename() {
		return annotateddatafilename;
	}

	public void setAnnotateddatafilename(String annotateddatafilename) {
		this.annotateddatafilename = annotateddatafilename;
	}

	public void setLogfileName(String lf) {
		this.logfile = this.datapath + lf;
	}

	public void setSummaryfileName(String sf) {
		this.sumfile = this.datapath + sf;
	}

	public String getSummaryfileName() {
		return this.sumfile;
	}

	public void setEnrichfileName(String ef) {
		this.enrichfile = this.datapath + ef;
	}

	public String getEnrichfileName() {
		return this.enrichfile;
	}

	public void setSummaryfileDBName(String sf) {
		this.sumfile2 = this.datapath + sf;
	}

	public String getSummaryDBfileName() {
		return this.sumfile2;
	}

	public String getLogfileName() {
		return this.logfile;
	}

	public int getPquery() {
		return pquery;
	}

	public void setPquery(int pquery) {
		this.pquery = pquery;
	}

} // end class InterfaceAgent
