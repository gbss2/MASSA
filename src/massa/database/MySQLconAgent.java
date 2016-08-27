/*#==========================================
# DIVERGENOMEnrich MAS Project
#==========================================
#
# (C) Copyright 2012, by LDGH and Contributors.
#
# /
#/ -----------------
#  MySQLconAgent.java
#  -----------------
#
# Original Author: Giordano Bruno Soares Souza
# Contributor(s):
# Updated by (and date):
#
# Command line:  
#
# Dependencies: JADE jar files (jade.jar,commons-codec-1.3.jar)
#
# Description:  Generic class for MySQL Connections
#
# Parameters:
#
#/
*/

package massa.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jade.core.behaviours.OneShotBehaviour;
import massa.agents.DBagent;

public class MySQLconAgent extends DBagent {

	String driverName = "com.mysql.jdbc.Driver";
	String serverName = "150.164.28.6:3306";
	String mydatabase = "giordano";
	String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	String user = "giordano1";
	String key = "giordano";
	ResultSet rs; // = new ArrayList<String>();
	// String Statement = "";
	public static String sql = "Select * from example_autoincrement";
	public static Statement stm;
	public static Connection conn = null;

	/* Constructor */
	public MySQLconAgent() {
		/*
		 * Here the dbname and information must be set. Example: this.setDBname
		 * = "dbsnp"; this.setInformation("snp");
		 */

		this.setDBname("mysql");
		this.setInformation("mysql");

	}

	/* Agent setup */
	protected void setup() {
		System.out.println("Agent " + getLocalName() + " started.");

		/* Here DBagents must register */
		this.register();

		/* Add a behaviour */
		// addBehaviour()

		addBehaviour(new OneShotBehaviour(this) {

			private ResultSet rs;

			public void action() {

				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (Exception e) {
					System.out.println("Class strife. " + e);
				}

				try {

					conn = DriverManager.getConnection(url, user, key);

					// Do something with the Connection
					System.out.println("Successful Connection ");

				} catch (SQLException e) {
					// handle any errors
					System.out.println("SQLException: " + e.getMessage());
					System.out.println("SQLState: " + e.getSQLState());
					System.out.println("VendorError: " + e.getErrorCode());
				}

				QueryOne();

				try {
					conn.close();
					System.out.println("Disconnected");
				} catch (SQLException erro) {
					System.out.println("Error while Disconnecting");
					erro.printStackTrace();
				}

			}// End of public void action()

		}); // End of OneShotBehaviour

	} // End of protected void setup()

	public static void QueryOne() {
		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			// conn.ResultSet executeQuery(sql);
			System.out.println("Successful Query");

			while (rs.next()) {
				Integer id_gp = rs.getInt("id");
				String nome_gp = rs.getString("data");
				System.out.println("ID: " + id_gp + " Data: " + nome_gp);

			}
		} catch (SQLException e) {
			System.out.println("SQL Exception... Error in querying:");
			e.printStackTrace();
		}

	}

} // End of Class MySQLAgent
