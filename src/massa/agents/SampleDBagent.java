/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  SampleDBagent.java
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
# Description:  Sample instantiation of generic class DBAgent
#
# Parameters:
#
#/
*/
package massa.agents;

//import java.util.List;
//import java.util.ArrayList;
//import java.util.Iterator;

//import jade.core.AID;
//import jade.core.Agent;
//import jade.domain.DFService;
//import jade.domain.FIPAException;
//import jade.domain.FIPANames;
//import jade.domain.FIPAAgentManagement.DFAgentDescription;
//import jade.domain.FIPAAgentManagement.ServiceDescription;

public class SampleDBagent extends DBagent {

	/* Constructor */
	public SampleDBagent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("sampledb");
		this.setInformation("sample");

	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		/* Add a behaviour */
		// addBehaviour()
	}

}
