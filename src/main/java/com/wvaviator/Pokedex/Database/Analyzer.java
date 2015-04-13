package com.wvaviator.Pokedex.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Analyzer {
	
	public static boolean playersTableExists() {

		try {
			
			return tableExists("players");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean logsTableExists() {
		
		try {
			
			return tableExists("pixelmonlogs");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean totalsTableExists() {
		
		try {
			
			return tableExists("totals");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private static boolean tableExists(String table) throws SQLException {
		
		Connection c = Database.getConnection();
		DatabaseMetaData dbm = null;
		
		try {
			dbm = c.getMetaData();
			ResultSet rs = dbm.getTables(null, null, table.toUpperCase(), new String[] {"TABLE"});
			
			if (!rs.next()) {
				return false;
			}
			
			return true;
			
		} finally {
			c.close();
		}
		
	}

}
