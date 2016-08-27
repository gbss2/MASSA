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
import java.util.List;

import jade.content.onto.basic.Action;
import jade.core.ContainerID;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
//import jade.core.behaviours.OneShotBehaviour;

import jade.domain.JADEAgentManagement.JADEManagementOntology;
//import jade.domain.introspection.ACLMessage;
import jade.lang.acl.ACLMessage;
import jade.wrapper.*;

public class Runmassa {

	public static String slash; // for Operating system control
	public static String os;    // for Operating system control (Linux,Windows)
	public static String configpath;
	public static String userdir;
	public static String configfile;

	private static int analysistype;
	private static int pquery;
	
	public static void main(String[] args) {
		
		// Initializing attributes
		slash = System.getProperty("file.separator");
		os    = System.getProperty("os.name");
		userdir = System.getProperty("user.dir");
		configpath = userdir+slash+"config"+slash;

		configfile = configpath+"masconfig.txt";
		
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
			if(args.length > 0) {
				if(args[0].equalsIgnoreCase("-container")) {
					LaunchContainer(rt);
				}
			}

			//LaunchMainContainer(rt);
			LaunchContainer(rt);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	
	}

	public static void readConfigFile() {

		BufferedReader inputStream;
		String[] values;

		System.out.println("System is reading "+configfile+"...");

			try {
				inputStream = new BufferedReader(new FileReader(configfile));
				String l;
				while ((l = inputStream.readLine()) != null) {
					values = l.split("=");
					if(values[0].equals("analysis")){
						setAnalysistype(Integer.parseInt(values[1]));
					}

					if(values[0].equals("pquery")){
						setPquery(Integer.parseInt(values[1]));
					}

				}

			} catch (IOException e) { // End readFromFile
				e.printStackTrace(); }

			System.out.println("Analysis type    = "+getAnalysistype()+".");	
			System.out.println("Parallel queries = "+getPquery()+".");
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
		
		//NEW CODE
		// create the containerid of this container
		ContainerID myContainerID;
			for(ContainerID cid:getContainerIDs()){
			    System.out.println("------>CONTAINER: "+cid.getName());
				//myContainerID = cid;
				//break;
			}
		
		//END NEW CODE
		// Launch agents
		//System.out.println("Launching Interface agent on main container ...");

		// Interface agent
		//try{
		//	AgentController interfaceagent = cont.createNewAgent("interface","diverenrich.InterfaceAgent", new Object[0]);
		//	interfaceagent.start();
		//}
		//catch(StaleProxyException ie) {
		//	ie.printStackTrace();
		//}

	}

	// method for getting the containerids from the ams
	protected static List<ContainerID> getContainerIDs(){
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		msg.setProtocol(jade.domain.FIPANames.InteractionProtocol.FIPA_REQUEST);
		msg.setOntology(JADEManagementOntology.NAME);
		msg.setLanguage(jade.domain.FIPANames.ContentLanguage.FIPA_SL0);
/*		try {
		    // send a request to the AMS
						
			
		    getContentManager().fillContent(msg, new Action(getAMS(),new QueryPlatformLocationsAction()));
		    
		    msg.addReceiver(getAMS());
		    send(msg);
		    
		    // wait for the answer from the ams
		    msg = blockingReceive(MessageTemplate.MatchOntology(JADEManagementOntology.NAME));
		    //extract the content and cast to type Result
		    ContentElement ce = getContentManager().extractContent(msg);
		    Result res = null;
		    if (ce instanceof Result) {
			res = (Result) ce;
		    } else{
			return null;
		    }
		    
		    // make a list of all ContainerID's given in the result
		    jade.util.leap.Iterator it = res.getItems().iterator();
		    List<ContainerID> result = new LinkedList<ContainerID>();
		    while (it.hasNext()) {
			result.add((ContainerID) it.next());
		    }
		    return result;
		} catch (OntologyException e) {
		    return null;
		} catch(CodecException e){
		    return null;
		}
		*/
			return null;
	    }

	public static void LaunchContainer(Runtime rt){

		// Create a default profile
		Profile p = new ProfileImpl(false);

		// Create a new non-main container, connecting to the default
		// main container (i.e. on this host, port 1099)
		System.out.println("Launching the agent container ..."+p);
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
