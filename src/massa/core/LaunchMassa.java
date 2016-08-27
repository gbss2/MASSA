/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  Runmas.java
#  -----------------
#
# Original Author: Maira Ribeiro Rodrigues
# Contributor(s):
# Updated by (and date):
#
# Command line:  java diverenrich.Runmas
#
# Dependencies:  JADE jar files (jade.jar,commons-codec-1.3.jar)
#
# Description: Starts the MASenrich on JADE, including all agents.
#
# Parameters:
#
#/
*/
package massa.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jade.core.Profile;
import jade.core.ProfileImpl;
//import jade.core.behaviours.OneShotBehaviour;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class LaunchMassa {

	public static String slash; // for Operating system control
	public static String os; // for Operating system control (Linux,Windows)
	public static String configpath;
	public static String userdir;
	public static String configfile;

	private static int analysistype;
	private static int pquery;

	public static void main(String[] args) {

		// Initializing attributes
		slash = System.getProperty("file.separator");
		os = System.getProperty("os.name");
		userdir = System.getProperty("user.dir");
		configpath = userdir + slash + "config" + slash;

		configfile = configpath + "masconfig.txt";

		System.out.println("-------------------------------------------------");
		System.out.println("MAS Annotation System (c) LDGH and EPIGEN Project");
		System.out.println("-------------------------------------------------");

		readConfigFile();

		try {
			// Get a hold on JADE runtime
			Runtime rt = Runtime.instance();

			// Exit the JVM when there are no more containers around
			rt.setCloseVM(true);

			// Check whether a '-container' flag was given
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("-container")) {
					LaunchContainer(rt);
				}
			}

			LaunchMainContainer(rt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readConfigFile() {

		BufferedReader inputStream;
		String[] values;

		System.out.println("System is reading " + configfile + "...");

		try {
			inputStream = new BufferedReader(new FileReader(configfile));
			String l;
			while ((l = inputStream.readLine()) != null) {
				values = l.split("=");
				if (values[0].equals("analysis")) {
					setAnalysistype(Integer.parseInt(values[1]));
				}

				if (values[0].equals("pquery")) {
					setPquery(Integer.parseInt(values[1]));
				}

			}

		} catch (IOException e) { // End readFromFile
			e.printStackTrace();
		}

		System.out.println("Analysis type    = " + getAnalysistype() + ".");
		System.out.println("Parallel queries = " + getPquery() + ".");
	}

	public static void LaunchMainContainer(Runtime rt) {

		// Launch a complete platform on the 8888 port
		// Create a default Profile

		String setlocalhostl = "127.0.0.1"; // localhost para linux
		String setlocalhostw = "127.0.1.1"; // localhost para windows
		Profile pMain = new ProfileImpl(setlocalhostl, 8888, null);

		System.out.println("Launching a whole in-process platform..." + pMain);
		ContainerController mc = rt.createMainContainer(pMain);
		// Set the default Profile to start a container
		ProfileImpl pContainer = new ProfileImpl(null, 8888, null);

		System.out.println("Launching the agent container ..." + pContainer);
		ContainerController cont = rt.createAgentContainer(pContainer);

		// Launch agents
		System.out.println("Launching agents on main container ...");
		/*
		 * // SampleDBagent try{ AgentController sampledbagent =
		 * cont.createNewAgent("sampledb","diverenrich.SampleDBagent", new
		 * Object[0]); sampledbagent.start(); } catch(StaleProxyException ie) {
		 * ie.printStackTrace(); }
		 */

		// Agent dynamic names
		String dbsnpagent_name;
		String goagent_name;
		String pharmagent_name;
		String ucscagent_name;
		String omim_name;
		String hugo_name;
		String gwas_name;

		if (getAnalysistype() == 2) { // remote analysis
			// dbSNP agent
			for (int i = 1; i <= (getPquery() + 2); i++) {
				try {
					dbsnpagent_name = "dbsnp" + Integer.toString(i);
					AgentController dbsnpagent = cont.createNewAgent(dbsnpagent_name, "diverenrich.DBsnpAgent",
							new Object[0]);
					dbsnpagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}
			}
		} // end if

		if (getAnalysistype() == 4) { // remote comlete analysis

			for (int i = 1; i <= (getPquery() + 2); i++) {
				// dbSNP agent
				try {
					dbsnpagent_name = "dbsnp" + Integer.toString(i);
					AgentController dbsnpagent = cont.createNewAgent(dbsnpagent_name, "diverenrich.DBsnpAgent",
							new Object[0]);
					dbsnpagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}
				// GO agent
				try {
					goagent_name = "geneontology" + Integer.toString(i);
					AgentController goagent = cont.createNewAgent(goagent_name, "diverenrich.GOAgent", new Object[0]);
					goagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// PharmGKB agent
				try {
					pharmagent_name = "pharmgkb" + Integer.toString(i);
					AgentController pharmgkbagent = cont.createNewAgent(pharmagent_name, "diverenrich.PharmgkbAgent",
							new Object[0]);
					pharmgkbagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// UCSC agent
				try {
					ucscagent_name = "ucsc" + Integer.toString(i);
					AgentController ucscagent = cont.createNewAgent(ucscagent_name, "diverenrich.UCSCAgent",
							new Object[0]);
					ucscagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// OMIM agent
				try {
					omim_name = "omim" + Integer.toString(i);
					AgentController omimagent = cont.createNewAgent(omim_name, "diverenrich.omimAgent", new Object[0]);
					omimagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// HUGO agent
				try {
					hugo_name = "hugo" + Integer.toString(i);
					AgentController hugoagent = cont.createNewAgent(hugo_name, "diverenrich.HGNCAgent", new Object[0]);
					hugoagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

			}

		} // end if

		if (getAnalysistype() == 1) { // local analysis

			for (int i = 1; i <= (getPquery() + 2); i++) {
				try {
					dbsnpagent_name = "dbsnp" + Integer.toString(i);
					AgentController dbsnpagent = cont.createNewAgent(dbsnpagent_name, "diverenrich.DBsnpLocalAgent",
							new Object[0]);
					dbsnpagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}
			}

		} // end if

		if (getAnalysistype() == 3) { // local complete analysis

			for (int i = 1; i <= (getPquery() + 2); i++) {
				// dbSNP agent
				try {
					dbsnpagent_name = "dbsnp" + Integer.toString(i);
					AgentController dbsnpagent = cont.createNewAgent(dbsnpagent_name, "diverenrich.DBsnpLocalAgent",
							new Object[0]);
					dbsnpagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// GO agent
				try {
					goagent_name = "geneontology" + Integer.toString(i);
					AgentController goagent = cont.createNewAgent(goagent_name, "diverenrich.GOLocalAgent",
							new Object[0]);
					goagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// PHARMGKB agent
				try {
					pharmagent_name = "pharmgkb" + Integer.toString(i);
					AgentController pharmagent = cont.createNewAgent(pharmagent_name, "diverenrich.PharmgkbLocalAgent",
							new Object[0]);
					pharmagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// OMIM agent
				try {
					omim_name = "omim" + Integer.toString(i);
					AgentController omimagent = cont.createNewAgent(omim_name, "diverenrich.omimLocalAgent",
							new Object[0]);
					omimagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// UCSC agent
				try {
					ucscagent_name = "ucsc" + Integer.toString(i);
					AgentController ucscagent = cont.createNewAgent(ucscagent_name, "diverenrich.UCSCLocalAgent",
							new Object[0]);
					ucscagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// HUGO agent
				try {
					hugo_name = "hugo" + Integer.toString(i);
					AgentController hugoagent = cont.createNewAgent(hugo_name, "diverenrich.HGNCLocalAgent",
							new Object[0]);
					hugoagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

				// GWAS agent
				try {
					gwas_name = "gwas" + Integer.toString(i);
					AgentController gwasagent = cont.createNewAgent(gwas_name, "diverenrich.GWAScatalogLocalAgent",
							new Object[0]);
					gwasagent.start();
				} catch (StaleProxyException ie) {
					ie.printStackTrace();
				}

			}

		} // end if

		// SnpToGene agent
		try {
			AgentController dbsnpagent = cont.createNewAgent("snptogene", "diverenrich.SnpToGeneAgent", new Object[0]);
			dbsnpagent.start();
		} catch (StaleProxyException ie) {
			ie.printStackTrace();
		}

		// Coordinator agent
		try {
			AgentController coordinatoragent = cont.createNewAgent("coordinator", "diverenrich.CoordinatorAgent",
					new Object[0]);
			coordinatoragent.start();
		} catch (StaleProxyException ie) {
			ie.printStackTrace();
		}

		// Interface agent
		/*
		 * try{ AgentController interfaceagent =
		 * cont.createNewAgent("interface","diverenrich.InterfaceAgent", new
		 * Object[0]); interfaceagent.start(); } catch(StaleProxyException ie) {
		 * ie.printStackTrace(); }
		 */

	}

	public static void LaunchContainer(Runtime rt) {

		// Create a default profile
		Profile p = new ProfileImpl(false);

		// Create a new non-main container, connecting to the default
		// main container (i.e. on this host, port 1099)
		System.out.println("Launching the agent container ..." + p);
		AgentContainer ac = rt.createAgentContainer(p);

	}

	public static int getAnalysistype() {
		return analysistype;
	}

	public static void setAnalysistype(int at) {
		analysistype = at;
	}

	public static int getPquery() {
		return pquery;
	}

	public static void setPquery(int pq) {
		pquery = pq;
	}

}
