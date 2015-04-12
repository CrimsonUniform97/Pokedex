package com.wvaviator.Pokedex.Interpreter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Logging.PokedexQuery;

public class DataRetrieval {

	public static ResultSet retreiveResults(PokedexQuery pdq) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = generateQueryString(pdq, "SELECT * FROM pixelmonlogs");
	
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);
			return rs;
	
	}
	
	public static void deleteResults(PokedexQuery pdq) throws SQLException {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String update = generateQueryString(pdq, "DELETE FROM pixelmonlogs");
		
		try {
			
			stmt = c.createStatement();
			stmt.executeUpdate(update);
			
		} finally {
			
			stmt.close();
			c.close();
		}
		
	}

	private static String generateQueryString(PokedexQuery pdq, String baseQuery) {
		
		String query = baseQuery;
		int count = pdq.countQueries();
		if (count == 0) return query;
		query += " WHERE ";
		
		if (pdq.getUUID() != null) {
			query += "uuid = '" + pdq.getUUID() + "'";
			count--;
			if (count == 0) return query;
			query += " AND ";
		}
		
		if (pdq.getPokemon() != null) {
			query += "pokemon = '" + pdq.getPokemon() + "'";
			count--;
			if (count == 0) return query;
			query += " AND ";
		}
		
		if (pdq.getAction() != null) {
			query += "action = '" + pdq.getAction() + "'";
			count--;
			if (count == 0) return query;
			query += " AND ";
		}
		
		if (pdq.getShiny() != false) {
			query += "shiny = 'true'";
			count--;
			if (count == 0) return query;
			query += " AND ";
		}
		
		if (pdq.getToDate() != null) {
			query += "time > '" + pdq.getFromDate().toString() + "' AND time < '" + pdq.getToDate().toString() + "'";
		}
		
		return null;
	}
	
	
}
