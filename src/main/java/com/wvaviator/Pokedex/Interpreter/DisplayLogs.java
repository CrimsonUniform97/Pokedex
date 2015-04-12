package com.wvaviator.Pokedex.Interpreter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Configuration.PokedexConfiguration;
import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Logging.PokedexQuery;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class DisplayLogs {

	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");

	public static void displayData(PokedexQuery pdq) throws SQLException {
		
		try {
		
		ResultSet rs = DataRetrieval.retreiveResults(pdq);
		
		if (!rs.next()) {
			Chat.toChat(pdq.getSender(), Chat.noResults);
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
			
			Chat.toChat(pdq.getSender(), displayInfo(rs));
			
		} while (rs.next());
		
		} finally {
			Database.closeConnection();
		}
		
	}

	private static String displayInfo(ResultSet rs) throws SQLException {
		
		String time = sdf.format(rs.getTimestamp("time"));
		Pokedex.logger.info("Looking up username for " + rs.getString("uuid"));
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
		
		String display = EnumChatFormatting.AQUA + time + " " + name + " " + action + " " + shiny + pokemon + additionalValue + " " + additional;
		return display;
		
	}
	
	
	
}
