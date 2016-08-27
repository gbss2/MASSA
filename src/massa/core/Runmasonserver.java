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


import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;

import jade.wrapper.*;

public class Runmasonserver {

	public static void main(String[] args) {
		
		try {
			// Get a hold on JADE runtime
			Runtime rt = Runtime.instance();

			// Exit the JVM when there are no more containers around
			rt.setCloseVM(true);

			// Check whether a '-container' flag was given
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("-container")) {
					LaunchContainer(rt);
				}
			}

			LaunchMainContainer(rt);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	
	}

	public static void LaunchMainContainer(Runtime rt){

		// Launch a complete platform on the 8888 port
		// Create a default Profile 
		
		String setlocalhostl = "127.0.0.1"; //localhost para linux
		String setlocalhostw = "127.0.1.1"; //localhost para windows
		Profile pMain = new ProfileImpl(setlocalhostl, 8888, null);


		System.out.println("Launching a whole in-process platform..."+pMain);
		ContainerController mc = rt.createMainContainer(pMain);

		// Set the default Profile to start a container
		ProfileImpl pContainer = new ProfileImpl(null, 8888, null);
		System.out.println("Launching the agent container ..."+pContainer);
		ContainerController cont = rt.createAgentContainer(pContainer);

		// Launch agents
		System.out.println("Launching agents on main container ...");
		/*
		// SampleDBagent
		try{
			AgentController sampledbagent = cont.createNewAgent("sampledb","diverenrich.SampleDBagent", new Object[0]);
			sampledbagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}*/
		
		// DBsnpLocalAgent agent
		try{
			AgentController dbsnpagent = cont.createNewAgent("dbsnp","diverenrich.DBsnpLocalAgent", new Object[0]);
			dbsnpagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

		try{
			AgentController dbsnpagent2 = cont.createNewAgent("dbsnp2","diverenrich.DBsnpLocalAgent", new Object[0]);
			dbsnpagent2.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

		try{
			AgentController dbsnpagent3 = cont.createNewAgent("dbsnp3","diverenrich.DBsnpLocalAgent", new Object[0]);
			dbsnpagent3.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}
		
		try{
			AgentController dbsnpagent4 = cont.createNewAgent("dbsnp4","diverenrich.DBsnpLocalAgent", new Object[0]);
			dbsnpagent4.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

		try{
			AgentController dbsnpagent5 = cont.createNewAgent("dbsnp5","diverenrich.DBsnpLocalAgent", new Object[0]);
			dbsnpagent5.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}
		
		// SnpToGene agent
		try{
			AgentController dbsnpagent = cont.createNewAgent("snptogene","diverenrich.SnpToGeneAgent", new Object[0]);
			dbsnpagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}
		
		
		// Pharmgkb agent 
		
		try{
			AgentController dbsnpagent = cont.createNewAgent("pharmgkb","diverenrich.PharmgkbAgent", new Object[0]);
			dbsnpagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

		// GO agent
		try{
			AgentController goagent = cont.createNewAgent("geneontology","diverenrich.GOAgent", new Object[0]);
			goagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}
		
		// UCSC agent
		try{
			AgentController goagent = cont.createNewAgent("ucsc","diverenrich.UCSCAgent", new Object[0]);
			goagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

		
		// Coordinator agent
		try{
			AgentController coordinatoragent = cont.createNewAgent("coordinator","diverenrich.CoordinatorAgent", new Object[0]);
			coordinatoragent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

		
		// Interface agent
	
		try{
			AgentController interfaceagent = cont.createNewAgent("interface","diverenrich.InterfaceAgent", new Object[0]);
			interfaceagent.start();
		}
		catch(StaleProxyException ie) {
			ie.printStackTrace();
		}

	}


	public static void LaunchContainer(Runtime rt){

		// Create a default profile
		Profile p = new ProfileImpl(false);

		// Create a new non-main container, connecting to the default
		// main container (i.e. on this host, port 1099)
		System.out.println("Launching the agent container ..."+p);
		AgentContainer ac = rt.createAgentContainer(p);

	}
	
}
