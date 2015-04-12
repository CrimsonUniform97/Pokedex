package com.wvaviator.Pokedex.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraft.entity.player.EntityPlayerMP;

import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Database.Database;

public class UUIDManager {

	public static String getUsername(String uuid) throws SQLException {
		
		String query = "SELECT name FROM players WHERE uuid = '" + uuid + "'";
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				Pokedex.logger.error("Player not found");
				return null;
			}
			
			String username = rs.getString("name");

			return username;
			
		} finally {
			stmt.close();
			c.close();	
		}
		
	}
	
	public static boolean isInDatabase(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String query = "SELECT uuid FROM players WHERE uuid = '" + uuid + "'";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(!rs.next()) {
				return false;
			} else {
				return true;
			}
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static void addPlayer(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String username = player.getName();
		
		String update = "INSERT INTO players VALUES ('" + uuid + "', '" + username + "')";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);
			
		} finally {
			stmt.close();
			c.close();
			
		}
		
	}
	
	public static boolean didUsernameChange(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String username = getUsername(uuid);
		
		String nameToCheck = player.getName();
		
		if (username.equalsIgnoreCase(nameToCheck)) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static void updateUsername(EntityPlayerMP player) throws SQLException {
		
		String uuid = player.getUniqueID().toString();
		String username = player.getName();
		String update = "UPDATE players SET name = '" + username + "' WHERE  uuid = '" + uuid + "'";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);
			
		} finally {
			stmt.close();
			c.close();
			
		}
		
	}

	public static String getUUID(String playerName) throws SQLException {
		String name = playerName.toUpperCase();
		String query = "SELECT uuid FROM players WHERE UPPER(name) LIKE UPPER('" + name + "')";
		
		Statement stmt = null;
		Connection c = Database.getConnection();
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(!rs.next()) {
				return null;
			} else {
				return rs.getString("uuid");
			}
			
		} finally {
			stmt.close();
			c.close();
		}
	}
	
}
