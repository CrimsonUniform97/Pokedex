package com.wvaviator.Pokedex.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraft.entity.player.EntityPlayerMP;

import com.wvaviator.Pokedex.Database.Database;

public class PlayerCheats {
	
	public static int getCFFromUUID(String uuid) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT cf FROM players WHERE uuid = '" + uuid + "'";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return 0;
			
			return rs.getInt("cf");
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static void updateCF(String uuid, int cf) throws SQLException {
		
		
		String update = "UPDATE players SET cf = " + cf + " WHERE  uuid = '" + uuid + "'";
		
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

}
