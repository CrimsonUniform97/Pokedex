package com.wvaviator.Pokedex.Interpreter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.minecraft.command.ICommandSender;

import com.wvaviator.Pokedex.Configuration.PokedexConfiguration;
import com.wvaviator.Pokedex.Logging.PokedexQuery;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class DisplayLogs {

	static SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy HH:mm:ss");

	public static void displayData(ICommandSender sender, PokedexQuery pdq) throws SQLException {
		
		ResultSet rs = DataRetrieval.retreiveResults(pdq);
		
		if (!rs.next()) {
			Chat.toChat(sender, Chat.noResults);
			return;
		}
		
		int count = 0;
		do {
			count++;
		} while (rs.next());
		
		int reduce = PokedexConfiguration.minimumDisplay;
		if (count < reduce) reduce = count;
		
		while(reduce != 0) {
			reduce--;
			rs.previous();
		}
		
		do {
			
			Chat.toChat(sender, displayInfo(rs));
			
		} while (rs.next());
		
	}

	private static String displayInfo(ResultSet rs) throws SQLException {
		
		String time = sdf.format(rs.getTimestamp("time"));
		String name = UUIDManager.getUsername(rs.getString("uuid"));
		String pokemon = rs.getString("pokemon");
		String action = rs.getString("action");
		boolean shinyB = rs.getBoolean("shiny");
		String additional = rs.getString("additional");
		if (additional.equals(".")) additional = "";
		String shiny = "";
		if (shinyB == true) shiny = "Shiny ";
		String additionalValue = "";
		if (action.equalsIgnoreCase("received") || action.equalsIgnoreCase("obtained")) additionalValue = " from";
		if (action.equalsIgnoreCase("traded")) additionalValue = " to";
		
		String display = time + " " + name + " " + action + " " + shiny + pokemon + additionalValue + " " + additional;
		return display;
		
	}
	
	
	
}
