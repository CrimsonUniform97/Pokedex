package com.wvaviator.Pokedex.Top;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wvaviator.Pokedex.Database.Database;

public class TopDataRetrieval {
	
	private static ResultSet getOrderedResults(String column) {
		
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT * FROM totals ORDER BY " + column + " DESC";
		ResultSet rs;
		
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return rs;
		
		
	}
	
	public static ResultSet getTopCaptured() {
		return getOrderedResults("captured");
	}
	
	public static ResultSet getTopDeleted() {
		return getOrderedResults("deleted");
	}
	
	public static ResultSet getTopReceived() {
		return getOrderedResults("received");
	}
	
	public static ResultSet getTopEvolved() {
		return getOrderedResults("evolved");
	}
	
	public static ResultSet getTopTraded() {
		return getOrderedResults("traded");
	}
	
	public static ResultSet getTopShiny() {
		return getOrderedResults("shiny");
	}
	
	public static ResultSet getTopLegendary() {
		return getOrderedResults("legendary");
	}

}
