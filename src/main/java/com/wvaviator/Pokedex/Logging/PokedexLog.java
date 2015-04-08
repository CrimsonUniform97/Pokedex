package com.wvaviator.Pokedex.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.wvaviator.Pokedex.Database.Database;

public class PokedexLog {
	
	public static void storeData(String uuid, String action, String pokemon, String additional, boolean isShiny) throws SQLException {
		
		String insert = "INSERT INTO pixelmonlogs (time, uuid, action, pokemon, additional, shiny) VALUES (?, ?, ?, ?, ?, ?)";
		
		Connection c = Database.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = c.prepareStatement(insert);
			
			stmt.setString(2, uuid);
			stmt.setString(3,  action);
			stmt.setString(4, pokemon);
			stmt.setString(5, additional);
			stmt.setBoolean(6, isShiny);
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			stmt.setTimestamp(1, ts);
			
			stmt.execute();
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}

}
