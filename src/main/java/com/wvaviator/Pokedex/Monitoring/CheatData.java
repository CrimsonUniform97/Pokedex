package com.wvaviator.Pokedex.Monitoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Users.PlayerTime;

public class CheatData {
	
	public static void logCheat(Cheat cheat) throws SQLException {
		
		Connection c = Database.getConnection();
		PreparedStatement stmt = null;
		String update = "INSERT INTO cheatlogs (uuid, cheat, time) VALUES (?, ?, ?)";
		
		try {
			
			stmt = c.prepareStatement(update);
			stmt.setString(1, cheat.getUUID());
			stmt.setString(2, cheat.getStringCheatType());
			stmt.setTimestamp(3, cheat.getTime());
			stmt.execute();
			
		} finally {
			stmt.close();
			c.close();
		}
		
		
	}
	
	public static Timestamp getLastTime(Cheat cheat) throws SQLException {
		
		String query = "SELECT * FROM cheatlogs WHERE uuid = '" + cheat.getUUID() 
				+ "' AND cheat = '" + cheat.getStringCheatType() + "'";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return PlayerTime.getFirstJoinTime(cheat.getUUID());
			
			rs.last();
			
			return rs.getTimestamp("time");
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}

}
