/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  CoordinatorAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues
# Contributor(s):
# Updated by (and date):
#
# Command line:  java diverenrich.CoordinatorAgent
#
# Dependencies:  JADE jar files (jade.jar,commons-codec-1.3.jar)
#
# Description: Coordinator agent class.
#
# Parameters:
#
#/
 */

package massa.agents;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mysql.jdbc.PreparedStatement;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioData;
import massa.biodata.BioDataLite;
import massa.biodata.GeneOntology;
import massa.biodata.HUGO;
import massa.biodata.OMIM;
import massa.biodata.PharmGKB;
import massa.biodata.Polymorphism;
import massa.biodata.UCSC;
import massa.database.MySQLcon;

public class CoordinatorAgent extends Agent {

	/* Attributes */
	private String[] service_types;
	private String[] info_types;
	private int analysistype;
	private Hashtable<AID, String> partner_list;
	private Hashtable<AID, Long> partner_tstart;
	private Hashtable<AID, Long> partner_tend;
	private long partner_exectime;

	public List<Integer> searchidlist;
	public List<BioData> biodatalist;
	public List<BioDataLite> biodatalitelist;
	private String myservicetype;
	private String myinformation;
	public boolean errflag;
	public int dbsearchid;

	public AID InterfaceAgent;

	public String sn = "localhost";
	public String dbname = "annotation";
	public String dbUser = "annotation";
	public String dbKey = "1masannotation1";
	MySQLcon myannconnection;
	public Connection connection;

	public CoordinatorAgent() {

		partner_list = new Hashtable<AID, String>();
		biodatalist = new ArrayList<BioData>();
		biodatalitelist = new ArrayList<BioDataLite>();
		searchidlist = new ArrayList<Integer>();
		partner_tstart = new Hashtable<AID, Long>();
		partner_tend = new Hashtable<AID, Long>();
		partner_exectime = 0;

		this.setMyservicetype("coordinator");
		this.setMyinformation("coordinator");
		// Set possible types of information and services available in the
		// system
		service_types = new String[1];
		service_types[0] = "database_search";

		info_types = new String[12];
		info_types[0] = "sample";
		info_types[1] = "snp";
		info_types[2] = "pharmgkb";
		info_types[3] = "snp to gene";
		info_types[4] = "gene ontology";
		info_types[5] = "ucsc";
		info_types[6] = "omim";
		info_types[7] = "hugo";
		info_types[8] = "gwas";
		info_types[9] = "polyphen";
		info_types[10] = "provean";
		info_types[11] = "reactome";
		// info_types[x] = "sift";

		errflag = false;

	}

	protected void setup() {

		System.out.println("Agent " + getLocalName() + " started.");

		/* Here Agent must register */
		register();

		/* Here Agent must connect to annotation db */
		// myconnection = new MySQLcon(sn, dbname, dbUser, dbKey);
		myannconnection = new MySQLcon(sn, dbname, dbUser, dbKey);
		connection = myannconnection.mysqlConnect();

		// Add behaviour

		addBehaviour(new waitMsg());

	}

	protected void takeDown() {
		myannconnection.mysqlDisconnect();
		System.out.println("Agent" + getLocalName() + " shutdown.");
	}

	/* Behaviours */

	class waitMsg extends CyclicBehaviour {

		private String previous_info_type;
		private AID sender;
		private String msgperformative;
		private String setinfotype;

		public waitMsg() {

		}

		public void action() {
			ACLMessage msg = myAgent.receive();

			if (msg != null) {
				sender = msg.getSender();
				msgperformative = msg.getPerformative(msg.getPerformative());

				// check sender
				if (partner_list.containsKey(sender)) {

					previous_info_type = partner_list.get(sender);
					partner_list.remove(sender);
					partner_tend.put(sender, System.currentTimeMillis());
					setpartnerexectime(sender);

					System.out.println("Agent " + getLocalName() + " received a REPLY from agent " + sender.getName()
							+ " info type was " + previous_info_type);

					try {

						if (getAnalysistype() == 1) { /* local basic */
							BioDataLite contentdata = (BioDataLite) msg.getContentObject();

							if (!searchidlist.contains(contentdata.getSearchid())) {
								searchidlist.add(contentdata.getSearchid());
							}
							System.out.println("Partnerlist size is " + partner_list.size());
							if (partner_list.size() == 0) {
								// combine annotation results and send
								// annotation result back to interface agent
								BioDataLite combinedbdl = new BioDataLite();
								Iterator itr = searchidlist.iterator();
								while (itr.hasNext()) {
									Integer sid = (Integer) itr.next();
									combinedbdl.setSearchidItem(sid);
								}
								addBehaviour(new SendReply(InterfaceAgent, combinedbdl));
							}
						}

						if (getAnalysistype() == 2) { /* remote basic */
							BioData contentdata = (BioData) msg.getContentObject();
							// send annotation result back to interface agent
							addBehaviour(new SendReply(InterfaceAgent, contentdata));
						}

						if (getAnalysistype() == 3) { /* local complete */
							BioDataLite contentdata = (BioDataLite) msg.getContentObject();

							if (!searchidlist.contains(contentdata.getSearchid())) {
								searchidlist.add(contentdata.getSearchid());
								// biodatalitelist.add(contentdata);
							}

							if (previous_info_type == "snp") {
								// CALL GO AGENT
								// set infotype for next annotation (4 = go)
								setinfotype = info_types[4];
								// call agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", contentdata));

								// CALL OMIM AGENT
								// set infotype for next annotation (6 = omim)
								setinfotype = info_types[6];
								// call agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", contentdata));

								// CALL PHARMGKB AGENT
								// set infotype for next annotation (2 = pharm)
								setinfotype = info_types[2];
								// call pharmgkb agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", contentdata));

								// CALL HUGO AGENT
								// set infotype for next annotation (7 = hugo)
								setinfotype = info_types[7];
								// call hugo agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", contentdata));

								// CALL REACTOME AGENT
								// set infotype for next annotation (11 =
								// reactome)
								setinfotype = info_types[11];
								// call reactome agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", contentdata));

							}
							System.out.println("Partnerlist size is " + partner_list.size());
							if (partner_list.size() == 0 && previous_info_type != "snp") {
								// combine annotation results and send
								// annotation result back to interface agent
								BioDataLite combinedbdl = new BioDataLite();
								Iterator itr = searchidlist.iterator();
								while (itr.hasNext()) {
									Integer sid = (Integer) itr.next();
									combinedbdl.setSearchidItem(sid);
								}
								addBehaviour(new SendReply(InterfaceAgent, combinedbdl));
							}

						}

						if (getAnalysistype() == 4) { /* remote complete */
							BioData contentdata = (BioData) msg.getContentObject();
							biodatalist.add(contentdata);

							if (previous_info_type == "snp") {
								// CALL PHARMGKB AGENT
								// set infotype for next annotation (2 = pharm)
								setinfotype = info_types[2];
								// create BioData object with hash{snp,gene} to
								// send to pharmgkb agent
								BioData objecttosend = new BioData();
								objecttosend.setSnpGene(contentdata.createSnpGeneAssoc());
								// call pharmgkb agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", objecttosend));

								// CALL GO AGENT
								// set infotype for next annotation (4 = go)
								setinfotype = info_types[4];
								// create BioData object with hash{snp,gene} to
								// send to agent
								BioData objecttogo = new BioData();
								objecttogo.setSnpGene(contentdata.createSnpGeneAssoc());
								// call agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", objecttogo));

								// CALL OMIM AGENT
								// set infotype for next annotation (6 = omim)
								setinfotype = info_types[6];
								// create BioData object with hash{snp,gene} to
								// send to agent
								BioData objecttoomim = new BioData();
								objecttoomim.setSnpGene(contentdata.createSnpGeneAssoc());
								// call agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", objecttoomim));

								// CALL HUGO AGENT
								// set infotype for next annotation (7 = hugo)
								setinfotype = info_types[7];
								// create BioData object with hash{snp,gene} to
								// send to agent
								BioData objecttohugo = new BioData();
								objecttohugo.setSnpGene(contentdata.createSnpGeneAssoc());
								// call agent
								addBehaviour(new FindAndSend(service_types[0], setinfotype, "REQUEST", objecttohugo));

							}
							System.out.println("Partnerlist size is " + partner_list.size());
							if (partner_list.size() == 0 && previous_info_type != "snp") {
								// combine annotation results and send
								// annotation result back to interface agent
								System.out.println("Sending Combined Annotation");
								addBehaviour(new combineAnnotationAndSend(InterfaceAgent));
							}

						}

					} catch (UnreadableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					if (msgperformative == "REQUEST") {
						System.out.println("Agent " + getLocalName() + " received a " + msgperformative + " from agent "
								+ sender.getName());
						// INTERFACE AGENT IS THE SENDER
						InterfaceAgent = msg.getSender();
						try {
							// set infotype for current annotation (1 = snp)
							setinfotype = info_types[1];
							System.out.println("Agent " + getLocalName() + " infotype is " + setinfotype);

							if (msg.getContentObject() instanceof BioData) { /*
																				 * for
																				 * remote
																				 * annotation
																				 */
								System.out.println("Agent " + getLocalName() + " content is BioData.");
								BioData contentdata = (BioData) msg.getContentObject();
								// CALL DBsnp Agent
								setAnalysistype(contentdata.getAnalysistype());
								addBehaviour(new checkInputAction(contentdata, setinfotype));

								// CALL UCSC AGENT
								// set infotype for next annotation (5 = ucsc)
								setinfotype = info_types[5];
								setAnalysistype(contentdata.getAnalysistype());
								addBehaviour(new checkInputAction(contentdata, setinfotype));

							}
							if (msg.getContentObject() instanceof BioDataLite) { /*
																					 * for
																					 * local
																					 * annotation
																					 */
								System.out.println("Agent " + getLocalName() + " content is BioDataLite.");
								BioDataLite contentdatalite = (BioDataLite) msg.getContentObject();
								// CALL dbSNP AGENT
								setAnalysistype(contentdatalite.getAnalysistype());
								addBehaviour(new checkInputAction(contentdatalite, setinfotype));

								// CALL UCSC AGENT
								// set infotype for next annotation (5 = ucsc)
								setinfotype = info_types[5];
								setAnalysistype(contentdatalite.getAnalysistype());
								addBehaviour(new checkInputAction(contentdatalite, setinfotype));

								// CALL GWAS AGENT
								// set infotype for next annotation (8 = gwas)
								setinfotype = info_types[8];
								setAnalysistype(contentdatalite.getAnalysistype());
								addBehaviour(new checkInputAction(contentdatalite, setinfotype));

								// CALL POLYPHEN AGENT
								// set infotype for next annotation (9 =
								// polyphen)
								setinfotype = info_types[9];
								setAnalysistype(contentdatalite.getAnalysistype());
								addBehaviour(new checkInputAction(contentdatalite, setinfotype));

								// CALL PROVEAN AGENT
								// set infotype for next annotation (10 =
								// provean)
								setinfotype = info_types[10];
								setAnalysistype(contentdatalite.getAnalysistype());
								addBehaviour(new checkInputAction(contentdatalite, setinfotype));

								// CALL SIFT AGENT
								// set infotype for next annotation (X = sift)
								// setinfotype = info_types[X];
								// setAnalysistype(contentdatalite.getAnalysistype());
								// addBehaviour(new
								// checkInputAction(contentdatalite,setinfotype));

							}

						} catch (UnreadableException e) {
							e.printStackTrace();
						}

					} else {
						System.out.println("Agent " + getLocalName() + " does not know agent " + sender.getName());
					}
				}
				// end check sender if
			} else {
				if (partner_exectime > 0) {
					// check if there is a delayed agent
					checkdelay();
				}
				block();
			}
		}

		public void setpartnerexectime(AID a) {

			long exectime = partner_tend.get(a) - partner_tstart.get(a);
			if (exectime > partner_exectime) {
				partner_exectime = exectime;
			}
		}

		public void checkdelay() {
			Map<AID, String> map = partner_list;

			Iterator<Map.Entry<AID, String>> it = map.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<AID, String> entry = it.next();
				// test
				Long remaintime = System.currentTimeMillis() - partner_tstart.get(entry.getKey());
				if (remaintime > (partner_exectime * 2)) {
					System.out.println("Agent " + entry.getKey().getName() + " is delayed");
				}
			}

		}

	} // end waitReply

	class combineAnnotationAndSend extends OneShotBehaviour {
		private BioData combinedbd;
		private BioData combinedlists;
		private AID receiver;

		public combineAnnotationAndSend(AID tosend) {
			this.receiver = tosend;
		}

		public void action() {

			System.out.println("Agent " + getLocalName() + " is combining results... ");
			// combine all results in one BioData combinedbd
			this.combinedlists = new BioData();
			Iterator<BioData> itr = biodatalist.iterator();
			while (itr.hasNext()) {
				BioData bd = (BioData) itr.next();

				// combining Polymorphism annotation
				if (bd.getPolymorphismList() != null) {
					for (Polymorphism p : bd.getPolymorphismList()) {
						combinedlists.setPolymorphismList(p);
					}
				}

				// combining UCSC annotation
				if (bd.getUCSCList() != null) {
					// System.out.println("Object is UCSC... ");
					for (UCSC us : bd.getUCSCList()) {
						combinedlists.setUCSCList(us);
					}
				}

				// combining Pharmgkb annotation
				if (bd.getPharmgkbList() != null) {
					for (PharmGKB ph : bd.getPharmgkbList()) {
						combinedlists.setPharmgkbList(ph);
					}
				}

				// combining GO annotation
				if (bd.getGOList() != null) {
					for (GeneOntology go : bd.getGOList()) {
						combinedlists.setGOList(go);
					}
				}

				// combining OMIM annotation
				if (bd.getOMIMList() != null) {
					for (OMIM om : bd.getOMIMList()) {
						combinedlists.setOMIMList(om);
					}
				}

				// combining HUGO annotation
				if (bd.getHUGOList() != null) {
					// System.out.println("Object is HUGO... ");
					for (HUGO hg : bd.getHUGOList()) {
						combinedlists.setHUGOList(hg);
						// System.out.println("Object HUGO is: "+
						// bd.getHUGOList().toString());
					}
				}

			} // end while

			// arrange results in Polymorphism objects and send to interface
			// agent
			combinedbd = new BioData();
			Iterator<Polymorphism> itrp = combinedlists.getPolymorphismList().iterator();
			while (itrp.hasNext()) {
				Polymorphism pol = (Polymorphism) itrp.next();
				// System.out.println("SNPid is "+pol.getPolymorphismCode());
				PharmGKB ph = combinedlists.getPharmGKBObject(pol.getPolymorphismCode(),
						combinedlists.getPharmgkbList());
				pol.setPathwayList(ph.getPathwayList());
				pol.setDrugList(ph.getDrugList());
				pol.setDiseaseList(ph.getDiseaseList());
				pol.setGenexList(ph.getGenexList());

				GeneOntology go = combinedlists.getGOobject(pol.getPolymorphismCode(), combinedlists.getGOList());
				pol.setMolFunctionList(go.getMolFunction());
				pol.setBioProcessList(go.getBioProcess());
				pol.setCelComponentList(go.getCelComponent());

				UCSC us = combinedlists.getUCSCobject(pol.getPolymorphismCode(), combinedlists.getUCSCList());
				pol.setStrand(us.getStrand());
				pol.setRefUCSC(us.getRefUCSC());
				pol.setObsGen(us.getObsGen());
				pol.setUcscClass(us.getUcscClass());
				pol.setUcscFunc(us.getUcscFunc());

				OMIM om = combinedlists.getOMIMobject(pol.getPolymorphismCode(), combinedlists.getOMIMList());
				pol.setCytoloc(om.getCytoloc());
				pol.setGenestatus(om.getGenestatus());
				pol.setDisorderList(om.getDisorder());
				pol.setMimIDList(om.getMimID());
				pol.setGenemapmethods(om.getGenemapmethods());
				pol.setInheritanceList(om.getInheritance());
				pol.setPhenoMapMethodsList(om.getPhenoMapMethods());
				pol.setCommentsList(om.getComments());

				HUGO hg = combinedlists.getHUGOobject(pol.getPolymorphismCode(), combinedlists.getHUGOList());
				// System.out.println("COORD Symbol: "+hg.getGeneSymbol()+"
				// Name: "+hg.getHgGeneName()+" Locus Type:
				// "+hg.getLocusType()+" Group: "+hg.getLocusGroup()+" Synonyms:
				// "+hg.getGeneSynonyms()+" Tag: "+hg.getGeneFamilyTag()+"
				// Family: "+hg.getGeneFamily()+" DBs:
				// "+hg.getSpecialistDBLinks());
				pol.setHgncId(hg.getHgncId());
				pol.setHgGeneName(hg.getHgGeneName());
				pol.setGeneSynonyms(hg.getGeneSynonyms());
				pol.setLocusType(hg.getLocusType());
				pol.setLocusGroup(hg.getLocusGroup());
				pol.setGeneFamilyTag(hg.getGeneFamilyTag());
				pol.setGeneFamily(hg.getGeneFamily());
				pol.setSpecialistDBLinks(hg.getSpecialistDBLinks());
				pol.setLocusSpecDB(hg.getLocusSpecDB());
				pol.setEnzymeId(hg.getEnzymeId());
				pol.setEntrezId(hg.getEntrezId());
				pol.setEnsemblId(hg.getEnsemblId());
				pol.setPubMedIds(hg.getPubMedIds());
				pol.setRefSeqIds(hg.getRefSeqIds());
				pol.setCCDSIds(hg.getCCDSIds());
				pol.setVegaIds(hg.getVegaIds());
				pol.setUniProtId(hg.getUniProtId());
				pol.setMouseGdbId(hg.getMouseGdbId());
				pol.setRatGdbId(hg.getRatGdbId());

				combinedbd.setPolymorphismList(pol);
			}
			// send each BioData/Polymorphism to Interface agent
			// System.out.println("Sending BioData Polumorphism");
			addBehaviour(new SendReply(receiver, combinedbd));

		} // end action
	} // end class

	class checkInputAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";
		private static final String STATE_C = "C";

		public checkInputAction(BioDataLite bd,
				String ift) { /* local annotation */

			this.registerFirstState(new checkInfoType(bd), STATE_A);
			this.registerState(new annotationDBconnect(bd), STATE_B);

			System.out.println("Threads ativas:" + Thread.getAllStackTraces().size());

			this.registerLastState(new FindAndSend(service_types[0], ift, "REQUEST", bd), STATE_C);
			this.registerDefaultTransition(STATE_A, STATE_B);
			this.registerDefaultTransition(STATE_B, STATE_C);
		}

		public checkInputAction(BioData bd,
				String ift) { /* remote annotation */

			this.registerFirstState(new checkInfoType(bd), STATE_A);
			this.registerLastState(new FindAndSend(service_types[0], ift, "REQUEST", bd), STATE_B);
			this.registerDefaultTransition(STATE_A, STATE_B);
		}

		public int onEnd() {
			// System.out.println("Agent " + getLocalName() +" finished task.");
			return super.onEnd();
		}
	}

	class annotationDBconnect extends OneShotBehaviour {

		private BioDataLite bdlite;

		public annotationDBconnect(BioDataLite b) {
			this.bdlite = b;
		}

		public void action() {
			// connection = myannconnection.mysqlConnect();

			String sql = "insert into search (searchid) values (null)";

			try {

				// int[] autoGeneratedKeys = null;
				// Statement stm = connection.createStatement();
				PreparedStatement stm = (PreparedStatement) connection.prepareStatement(sql);
				stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				// stm.executeUpdate();

				/* Recover auto-generated search id */
				ResultSet rs = stm.getGeneratedKeys();

				if (rs.next()) {
					dbsearchid = rs.getInt(1);
				}

				bdlite.setSearchid(dbsearchid);

				stm.close();
				rs.close();

			} catch (SQLException e) {
				System.out.println("SQL Exception... Error inserting value in annotation table.");
				e.printStackTrace();

				errflag = true;
			} // End of Catch Exception

			// System.out.println("Generated id:"+dbsearchid);

		}

		public int onEnd() {
			// myannconnection.mysqlDisconnect();
			return 0;
		}

	}

	class checkInfoType extends OneShotBehaviour {
		String[] rsarray;
		int[] idarray;
		private BioDataLite bdlite;
		private BioData bd;

		public checkInfoType(BioDataLite b) {
			this.bdlite = b;
		}

		public checkInfoType(BioData b) {
			this.bd = b;
		}

		public void action() {

			if (getAnalysistype() == 1 || getAnalysistype() == 3) {
				rsarray = bdlite.getSnpRsList();
			}
			if (getAnalysistype() == 2 || getAnalysistype() == 4) {
				rsarray = bd.getSnpRsList();
			}

			idarray = new int[rsarray.length];

			// System.out.println("Matching "+rsarray[0]);
			if (rsarray[0].contains("rs")) {
				for (int i = 0; i < rsarray.length; ++i) {
					idarray[i] = Integer.parseInt(rsarray[i].substring(2));
					// System.out.println(idarray[i]);

				}

			} else {
				for (int i = 0; i < rsarray.length; ++i) {
					idarray[i] = Integer.parseInt(rsarray[i]);
				}
			}

			if (getAnalysistype() == 1 || getAnalysistype() == 3) {
				bdlite.setSnpIdList(idarray);
			}
			if (getAnalysistype() == 2 || getAnalysistype() == 4) {
				bd.setSnpIdList(idarray);
			}

		}
	}

	class printResultSNPGene extends OneShotBehaviour {

		public printResultSNPGene(BioData bd) {
			System.out.println("--------------------------------");
			System.out.println("------* SNPtoGENE RESULTS *-----");
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
		}

		public void action() {

		}
	}

	class printResultPharmGKB extends OneShotBehaviour {

		public printResultPharmGKB(BioData bd) {
			System.out.println("--------------------------------");
			System.out.println("------* PHARMGKB RESULTS *------");
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
		}

		public void action() {

		}

	}

	/*
	 * Finds providers and sends msg Parameters: String service_type, String
	 * info_type, String Performative, BioData data
	 */
	class FindAndSend extends OneShotBehaviour {

		private String type_of_service;
		private String type_of_info;
		private AID[] providers;
		private ACLMessage msg;
		private AID msgreceiver;
		private String msgperformative;
		private BioDataLite msgcontentlite;
		private BioData msgcontent;

		public FindAndSend(String st, String it, String p, BioDataLite d) {
			type_of_service = st;
			type_of_info = it;
			msgperformative = p;
			msgcontentlite = d;

			if (errflag) {
				this.done(); // this is not working. CHECK!!
			}
		}

		public FindAndSend(String st, String it, String p, BioData d) {
			type_of_service = st;
			type_of_info = it;
			msgperformative = p;
			msgcontent = d;

			if (errflag) {
				this.done(); // this is not working. CHECK!!
			}
		}

		public void action() {

			// FINDING AGENTS
			System.out.println("Agent " + getLocalName() + " searching for service type \"" + type_of_service
					+ "\" and information \"" + type_of_info + "\" ");
			try {
				// Build the description used as template for the search
				DFAgentDescription template = new DFAgentDescription();
				ServiceDescription templateSd = new ServiceDescription();
				templateSd.setType(type_of_service);
				templateSd.setName(type_of_info);
				template.addServices(templateSd);

				DFAgentDescription[] results = DFService.search(myAgent, template);
				if (results.length > 0) {
					providers = new AID[results.length];
					for (int i = 0; i < results.length; ++i) {
						// System.out.println("... found agent
						// \""+results[i].getName());
						DFAgentDescription dfd = results[i];
						providers[i] = dfd.getName();
					}

					System.out.println("Agent " + getLocalName() + " found " + providers.length + " providers.");
				} else {
					System.out.println("Agent " + getLocalName()
							+ " did not find any agent for the required information: " + type_of_info);
					this.done();
				}
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}

			// SENDING MSG
			if (providers != null) {
				Random rand;
				int rindex;

				rand = new Random();

				rindex = rand.nextInt(providers.length - 1 - 0 + 1) + 0;
				msgreceiver = providers[rindex];

				while (partner_list.containsKey(msgreceiver)) {
					rindex = rand.nextInt(providers.length - 1 - 0 + 1) + 0;
					msgreceiver = providers[rindex];
				}

				try {
					if (msgperformative == "REQUEST") {
						msg = new ACLMessage(ACLMessage.REQUEST);
						System.out.println("... sending " + msg.getPerformative(msg.getPerformative()) + " to agent("
								+ rindex + "): \"" + msgreceiver.getName());
					}
					msg.addReceiver(msgreceiver);
					msg.setLanguage("English");

					if (getAnalysistype() == 1 || getAnalysistype() == 3) { /* local */
						msg.setContentObject(msgcontentlite);
					}
					if (getAnalysistype() == 2 || getAnalysistype() == 4) { /* remote */
						msg.setContentObject(msgcontent);
					}

					myAgent.send(msg);

					synchronized (partner_list) {
						partner_list.put(msgreceiver, type_of_info);
						partner_tstart.put(msgreceiver, System.currentTimeMillis());
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}// END FINDANDSEND CLASS

	class SendReply extends OneShotBehaviour {

		private AID msgreceiver;
		private String msgperformative;
		private BioDataLite msgcontentlite;
		private BioData msgcontent;
		private ACLMessage msg;

		public SendReply(AID p, BioDataLite d) {
			msgreceiver = p;
			msgcontentlite = new BioDataLite();
			msgcontentlite = d;
			msgperformative = "INFORM";
		}

		public SendReply(AID p, BioData d) {
			msgreceiver = p;
			msgcontent = new BioData();
			msgcontent = d;
			msgperformative = "INFORM";
		}

		public void action() {

			try {
				System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
				msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(msgreceiver);
				msg.setLanguage("English");

				if (getAnalysistype() == 1 || getAnalysistype() == 3) {
					msg.setContentObject(msgcontentlite);
				}
				if (getAnalysistype() == 2 || getAnalysistype() == 4) {
					msg.setContentObject(msgcontent);
				}

				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	/*
	 * Send msg to one provider in providers[AID] Parameters: AID provider,
	 * String PERFORMATIVE, BioData d
	 */
	class SendMsgtoProvider extends OneShotBehaviour {

		private AID msgreceiver;
		private String msgperformative;
		private BioDataLite msgcontent;
		private ACLMessage msg;

		public SendMsgtoProvider(AID p, String f, BioDataLite d) {
			msgreceiver = p;
			msgperformative = f;
			msgcontent = new BioDataLite();
			msgcontent = d;
		}

		public void action() {

			try {
				System.out.println("... sending " + msgperformative + " to agent: \"" + msgreceiver.getName());
				if (msgperformative == "REQUEST") {
					msg = new ACLMessage(ACLMessage.REQUEST);
				}
				msg.addReceiver(msgreceiver);
				msg.setLanguage("English");
				msg.setContentObject(msgcontent);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

	protected void register() {
		/* Register with DF */
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			System.out.println("Agent " + getLocalName() + " registering service type \"" + this.getMyServiceType()
					+ "\" and info " + this.getMyinformation() + " with DF");

			ServiceDescription sd = new ServiceDescription();
			sd.setName(this.getMyinformation());
			sd.setType(this.getMyServiceType());
			dfd.addServices(sd);
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	public String getMyServiceType() {
		return myservicetype;
	}

	public void setMyservicetype(String myservicetype) {
		this.myservicetype = myservicetype;
	}

	public String getMyinformation() {
		return myinformation;
	}

	public void setMyinformation(String myinformation) {
		this.myinformation = myinformation;
	}

	public int getAnalysistype() {
		return analysistype;
	}

	public void setAnalysistype(int analysistype) {
		this.analysistype = analysistype;
	}

}
