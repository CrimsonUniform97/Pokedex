package com.wvaviator.Pokedex.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.wvaviator.Pokedex.Database.Database;

public class PlayerTime {
	
	public static Timestamp getFirstJoinTime(String uuid) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT datejoined FROM players WHERE uuid = '" + uuid + "'";
		
		try {
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) return null;
			
			return rs.getTimestamp("datejoined");
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}

}
