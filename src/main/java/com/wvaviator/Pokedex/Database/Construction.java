package com.wvaviator.Pokedex.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Construction {
	
	public static void establishPlayerTable() throws SQLException {
		
		String update = "CREATE TABLE players (uuid VARCHAR(40), name VARCHAR(20), PRIMARY KEY (uuid))";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);

		} finally {
			stmt.close();
			c.close();
		}
		
		
	}
	
	public static void establishLogTable() throws SQLException {
		
		String update = "CREATE TABLE pixelmonlogs ("
				+ "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "time TIMESTAMP, "
				+ "uuid VARCHAR(40), "
				+ "action VARCHAR(20), "
				+ "pokemon VARCHAR(40), "
				+ "additional VARCHAR(40), "
				+ "shiny BOOL NOT NULL DEFAULT 'false')";
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(update);	
		} finally {
			stmt.close();
			c.close();
		}
				
	}
	
	public static void establishTotalsTable() throws SQLException {
		
		String update = "CREATE TABLE totals (uuid VARCHAR(40), captured INT NOT NULL, deleted INT NOT NULL, received INT NOT NULL, traded INT NOT NULL, evolved INT NOT NULL, shiny INT NOT NULL, legendary INT NOT NULL, PRIMARY KEY (uuid))";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);

		} finally {
			stmt.close();
			c.close();
		}
		
		addServerTotals();
		
	}

	private static void addServerTotals() throws SQLException {
		
		String update = "INSERT INTO totals VALUES ('SERVER', 0, 0, 0, 0, 0, 0, 0)";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);

		} finally {
			stmt.close();
			c.close();
		}
		
	}

}
