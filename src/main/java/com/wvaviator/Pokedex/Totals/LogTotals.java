package com.wvaviator.Pokedex.Totals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wvaviator.Pokedex.Database.Database;

public class LogTotals {

	public static void addPlayer(String uuid) throws SQLException {
		
		String update = "INSERT INTO totals VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0)";
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
	
	public static void addCaptured(String uuid, boolean isShiny, boolean isLegendary) throws SQLException {
		
		
		int totalCaptured = retrieve(uuid, "captured") + 1;
		store(uuid, "captured", totalCaptured);
		
		if (isShiny) {
			int totalShiny = retrieve(uuid, "shiny") + 1;
			store(uuid, "shiny", totalShiny);
		}
		
		if (isLegendary) {
			int totalLegendary = retrieve(uuid, "legendary") + 1;
			store(uuid, "legendary", totalLegendary);
		}
		
	}
	
	public static void addDeleted(String uuid) throws SQLException {
		
		int deleted = retrieve(uuid, "deleted") + 1;
		store(uuid, "deleted", deleted);
		
	}

	public static void addReceived(String uuid) throws SQLException {
		
		int received = retrieve(uuid, "received") + 1;
		store(uuid, "received", received);
		
	}
	
	public static void addEvolved(String uuid) throws SQLException {
		
		int evolved = retrieve(uuid, "evolved") + 1;
		store(uuid, "evolved", evolved);
		
	}
	
	public static void addTraded(String uuid) throws SQLException {
		
		int traded = retrieve(uuid, "traded") + 1;
		store(uuid, "traded", traded);
		
	}
	
	private static void store(String uuid, String column, int value) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String update = "UPDATE totals SET " + column + " = " + value + " WHERE uuid = '" + uuid + "'";
		
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(update);
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	private static int retrieve(String uuid, String column) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT * FROM totals WHERE uuid = '" + uuid + "'";
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				addPlayer(uuid);
				return 0;
			}
			
			int result = rs.getInt(column);
			return result;
			
		} finally {
			stmt.close();
			c.close();
		}
	}
}
