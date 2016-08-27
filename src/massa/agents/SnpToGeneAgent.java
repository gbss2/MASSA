/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
# SnpToGeneAgent.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues
# Contributor(s):
# Updated by (and date):
#
# Command line:  
#
# Dependencies: JADE jar files (jade.jar,commons-codec-1.3.jar)
#
# Description: Maps SNPs into Genes by searching dbSNP
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
//import java.util.List;
//import java.util.ArrayList;
import java.net.URL;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import massa.biodata.BioData;

@SuppressWarnings("serial")
public class SnpToGeneAgent extends DBagent {

	BioData geneidnamelist; // stores: gene symbol, snpid

	/* Constructor */
	public SnpToGeneAgent() {
		super();
		this.setDBname("dbsnp");
		this.setInformation("snp to gene");
		this.geneidnamelist = new BioData();

	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBsnpAgent must register */
		this.register();

		// Behaviour execution
		// addBehaviour(new accessDBsnpEutils(input));
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
				sender = msg.getSender();
				System.out.println(
						"Agent " + getLocalName() + " received a REQUEST message from agent " + sender.getName());
				try {
					BioData contentdata = (BioData) msg.getContentObject();
					addBehaviour(new snpToGeneAction(contentdata.getSnpIdList(), sender));

				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				block();
			}
		}
	}

	class snpToGeneAction extends FSMBehaviour {

		private static final String STATE_A = "A";
		private static final String STATE_B = "B";

		public AID partneragent;
		public int[] snpidlist;

		public snpToGeneAction(int[] sl, AID pa) {
			partneragent = pa;
			snpidlist = sl;

			System.out.println("Agent " + getLocalName() + " executing request...");
			this.registerFirstState(new accessDBsnpEutils(snpidlist), STATE_A);
			this.registerLastState(new SendReply(partneragent, geneidnamelist), STATE_B);
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

			for (int i = 0; i < snpidlist.length; i++) {
				this.addSubBehaviour(new remoteAccessDBsnpEutils(snpidlist[i]));
			}

		}

		public int onEnd() {
			System.out.println("Agent " + getLocalName() + " parallel behaviour finished.");
			return super.onEnd();
		}

		// DB ACCESS IS IMPLEMENTED HERE!!!
		class remoteAccessDBsnpEutils extends OneShotBehaviour {

			private int snpid;
			GeneFilter genefilter;

			public remoteAccessDBsnpEutils(int l) {

				snpid = l;
				this.genefilter = new GeneFilter();

			}

			public void action() {
				BioData genex = new BioData();

				genefilter.setId(snpid);
				genefilter.readFLT();

				if (genefilter.polTable[1] != null) {
					genex.gene.setSnpid(Integer.toString(snpid));
					genex.gene.setGeneSymbol(genefilter.polTable[1]);
					genex.gene.setGeneName(genefilter.polTable[1]);

					synchronized (geneidnamelist) {
						geneidnamelist.setGeneList(genex.gene);
					}
					// System.out.println("sync!");
				}
				// System.out.println("-------------------");

			}

		}

	}

	class SendReply extends OneShotBehaviour {

		private AID msgreceiver;
		private String msgperformative;
		private BioData msgcontent;
		private ACLMessage msg;

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
				msg.setContentObject(msgcontent);
				myAgent.send(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}// END SENDMSGTOPROVIDER CLASS

} // end of Agent class

class GeneFilter {

	/*
	 * 12 geneID {FLT linha "LOC" atributo "locus_id"} *primeira linha CTG
	 * 
	 * 13 gene name {FLT linha "LOC" segundo atributo} *primeira linha
	 */

	/* Atributos */
	// ID referente ao DBSNP:
	int id;
	// Vetor :
	String[] polTable;

	/* Constructor */
	public GeneFilter() {
		polTable = new String[2];
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
				if (line.startsWith("LOC")) {
					String[] aux = new String[6];
					aux = line.split(" | "); // dividir line

					String gene_name = aux[2];
					String locus_id = aux[4];

					int pos; // posiÃ§Ã£o

					// [parsers]
					pos = locus_id.indexOf('=');
					locus_id = locus_id.substring(pos + 1);
					polTable[0] = locus_id;
					polTable[1] = gene_name;
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
	 * Atualizar a posiÃ§Ã£o 0 da tabela com o ID
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