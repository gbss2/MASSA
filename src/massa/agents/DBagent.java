/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  DBagent.java
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
# Description:  Generic class for DBAgents
#
# Parameters:
#
#/
*/
package massa.agents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class DBagent extends Agent {

	/* Attributes */
	private String dbname;

	private List<String> information = new ArrayList<String>();
	private static String servicetype; // ainda n�o sei o que vai aqui como tipo
										// de servi�o. Talvez Database Search.

	public static String slash; // for Operating system control
	public static String os; // for Operating system control (Linux,Windows)

	// Annotation database
	public String annsn = "localhost";
	public String anndbname = "annotation";
	public String anndbUser = "annotation";
	public String anndbKey = "1masannotation1";

	public static Connection annconnection;

	// Local databases
	static String driverName = "com.mysql.jdbc.Driver";
	private String serverName;
	private String mydatabase;
	private String user;
	private String key;
	public ResultSet rs;
	public String sql = null;
	public Statement stm;
	public Connection conn = null;
	public int q;

	private int annsearchid;
	public String[] polTable;

	/* Constructor */
	public DBagent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */
		this.servicetype = "database_search";
		polTable = new String[21];
		this.annConnect();
	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		// this.register();
		// this.annConnect();

		/* Add a behaviour */
		// addBehaviour()
	}

	public static class PooledMysqlConn {

		public static final BasicDataSource dbSource = new BasicDataSource();

		/* Constructor */
		public PooledMysqlConn() {

			dbSource.setDriverClassName(driverName);
			dbSource.setUrl("jdbc:mysql://localhost:3306/annotation");
			dbSource.setUsername("annotation");
			dbSource.setPassword("1masannotation1");
			dbSource.setPoolPreparedStatements(true);
			dbSource.setMaxOpenPreparedStatements(100);

		} // END constructor

		public static Connection getConnection() throws SQLException {
			return dbSource.getConnection();
		}

	}

	protected void takeDown() {
		System.out.println("Agent" + getLocalName() + " shutdown.");
	}

	protected void setDBname(String n) {
		this.dbname = n;
	}

	protected String getDBname() {
		return this.dbname;
	}

	protected String getServicetype() {
		return this.servicetype;
	}

	protected void setInformation(String s) {
		this.information.add(s);
	}

	protected List<String> getInformation() {
		return this.information;
	}

	public void annConnect() {
		MySQLcon(annsn, anndbname, anndbUser, anndbKey);
		annconnection = mysqlConnect();
	}

	public void annDisconnect() {
		mysqlDisconnect(annconnection);
	}

	public void dbConnect(String sname, String database, String useR, String passKey) {
		MySQLcon(sname, database, useR, passKey);
		conn = mysqlConnect();
	}

	public void dbDisconnect() {
		mysqlDisconnect(conn);
	}

	public void setAnnConnection(Connection c) {
		annconnection = c;
	}

	protected void register() {

		String info;
		/* Register with DF */

		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());

			System.out.println(
					"Agent " + getLocalName() + " registering service type \"" + this.getServicetype() + "\" with DF");

			/* Register all information */
			Iterator<String> it = this.information.iterator();
			while (it.hasNext()) {
				info = it.next();

				System.out.println("Agent " + getLocalName() + " registering information \"" + info + "\" with DF");
				ServiceDescription sd = new ServiceDescription();
				sd.setName(info);
				sd.setType(this.getServicetype());

				/* Other options to register: */
				// sd.addOntologies("weather-forecast-ontology");
				// sd.addLanguages(FIPANames.ContentLanguage.FIPA_SL);
				// sd.addProperties(new Property("organism", p_organism));
				// sd.addProperties(new Property("taxon", p_taxon));
				// sd.addProperties(new Property("format", p_format));
				dfd.addServices(sd);
			}
			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

	}

	public int getAnnsearchid() {
		return annsearchid;
	}

	public String getServerName() {
		return serverName;
	}

	public String getMyDatabase() {
		return mydatabase;
	}

	public String getUser() {
		return user;
	}

	public String getKey() {
		return key;
	}

	public void setAnnsearchid(int annsearchid) {
		this.annsearchid = annsearchid;
	}

	private void MySQLcon(String sname, String database, String useR, String passKey) {

		serverName = sname;
		mydatabase = database;
		user = useR;
		key = passKey;

	} // END

	public Connection mysqlConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println("Class strife. " + e);
		}

		try {

			conn = DriverManager.getConnection("jdbc:mysql://" + getServerName() + "/" + getMyDatabase(), getUser(),
					getKey());
			// Do something with the Connection
			System.out.println("Successful Connection ");

			return conn;
		} catch (SQLException e) {
			// handle any errors
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());

			return null;
		}
	}

	public void mysqlDisconnect(Connection c) {

		try {
			c.close();
			System.out.println("Disconnected");
		} catch (SQLException e) {
			System.out.println("Error while Disconnecting");
			e.printStackTrace();
		}

	}

	public void clearPolTable() {

		if (polTable != null) {
			for (int x = 0; x < polTable.length; x++) {
				polTable[x] = null;
			}
		}

	}

} // end class