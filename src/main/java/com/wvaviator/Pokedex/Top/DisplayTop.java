package com.wvaviator.Pokedex.Top;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class DisplayTop {
	
	public static void displayTop(ICommandSender sender, String category) throws SQLException {
		
		String header = designHeader(category);
		
		if (header == null) {
			Chat.toChat(sender, Chat.topHelp);
			return;
		}
		
		String display = EnumChatFormatting.AQUA + header;
		
		try {
		
			ResultSet rs = null;
			if (category.equalsIgnoreCase("captured")) rs = TopDataRetrieval.getTopCaptured();
			if (category.equalsIgnoreCase("deleted")) rs = TopDataRetrieval.getTopDeleted();
			if (category.equalsIgnoreCase("received")) rs = TopDataRetrieval.getTopReceived();
			if (category.equalsIgnoreCase("evolved")) rs = TopDataRetrieval.getTopEvolved();
			if (category.equalsIgnoreCase("traded")) rs = TopDataRetrieval.getTopTraded();
			if (category.equalsIgnoreCase("shiny")) rs = TopDataRetrieval.getTopShiny();
			if (category.equalsIgnoreCase("legendary")) rs = TopDataRetrieval.getTopLegendary();
		
			if (!rs.next()) {
				Chat.toChat(sender,Chat.noResults);
				return;
			}
		
			for (int i = 1; i < 11; i++) {
			
				if (rs.getString("uuid").equalsIgnoreCase("SERVER")) {
					if (!rs.next()) break;
					continue;
				}
			
				display += "\n" + EnumChatFormatting.AQUA;
				display += i + ". " + EnumChatFormatting.GOLD + UUIDManager.getUsername(rs.getString("uuid"));
				display += "    " + EnumChatFormatting.AQUA + rs.getInt(category.toLowerCase());
				display += " " + category.toLowerCase() + "\n";
				if (!rs.next()) break;
			
			}
		
			Chat.toChat(sender, display);
		
		} finally {
		
		Database.closeConnection();
		
		}
	}

	private static String designHeader(String category) {
		
		if (category.equalsIgnoreCase("captured")) return "===== Most Pixelmon Captured =====\n\n";
		if (category.equalsIgnoreCase("deleted")) return "===== Most Pixelmon Deleted =====\n\n";
		if (category.equalsIgnoreCase("received")) return "===== Most Pixelmon Received =====\n\n";
		if (category.equalsIgnoreCase("evolved")) return "===== Most Evolutions =====\n\n";
		if (category.equalsIgnoreCase("traded")) return "===== Most Trades =====\n\n";
		if (category.equalsIgnoreCase("shiny")) return "===== Most Shiny Captures =====\n\n";
		if (category.equalsIgnoreCase("legendary")) return "===== Most Legendary Captures =====\n\n";
		
		return null;
	}

}
