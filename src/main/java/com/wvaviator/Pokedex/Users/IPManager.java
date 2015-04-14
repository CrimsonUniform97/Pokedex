package com.wvaviator.Pokedex.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wvaviator.Pokedex.Database.Database;

public class IPManager {
	
	public static String getIPFromUUID(String uuid) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT ip FROM players WHERE uuid = '" + uuid + "'";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return null;
			
			return rs.getString("ip");
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}

	public static String getIPFromName(String name) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT ip FROM players WHERE name = '" + name + "'";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return null;
			
			return rs.getString("ip");
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static boolean isDuplicateIP(String ip) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT ip FROM players";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return false;
			
			do {
				
				if (rs.getString("ip").equalsIgnoreCase(ip)) return true;
				
			} while (rs.next());
			
			return false;
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static String getUUIDMatchingIP(String ip) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT uuid, ip FROM players";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return null;
			
			do {
				
				if (rs.getString("ip").equalsIgnoreCase(ip)) return rs.getString("uuid");
				
			} while (rs.next());
			
			return null;
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static String getUsernameMatchingIP(String ip) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT name, ip FROM players";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return null;
			
			do {
				
				if (rs.getString("ip").equalsIgnoreCase(ip)) return rs.getString("name");
				
			} while (rs.next());
			
			return null;
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
}
