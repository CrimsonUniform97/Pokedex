package com.wvaviator.Pokedex.Totals;

import java.sql.SQLException;

import net.minecraft.util.EnumChatFormatting;

import com.wvaviator.Pokedex.Configuration.PokedexConfiguration;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class DisplayTotals {

	public static void displayTotals(TotalsQuery tq) {
		
		String name;
		
		try {
			name = UUIDManager.getUsername(tq.getUUID());
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		
		String shinyPercent;
		double shinyRatio = tq.getRatioShiny();
		double maxRatio = PokedexConfiguration.minimumShinyRatio;
		
		
		if ((maxRatio * 0.75) < shinyRatio) {
			if ((maxRatio * 0.90) < shinyRatio) {
				if ((maxRatio < shinyRatio)) {
					shinyPercent = EnumChatFormatting.DARK_RED + tq.getFormattedPercentageShiny();
				} else {
					shinyPercent = EnumChatFormatting.RED + tq.getFormattedPercentageShiny();
				}
			} else {
				shinyPercent = EnumChatFormatting.YELLOW + tq.getFormattedPercentageShiny();
			}
		} else {
			shinyPercent = EnumChatFormatting.GREEN + tq.getFormattedPercentageShiny();
		}
		
		String legendaryPercent;
		double legendaryRatio = tq.getRatioLegendary();
		double maxLegendaryRatio = PokedexConfiguration.minimumLegendaryRatio;
		
		
		if ((maxLegendaryRatio * 0.75) < legendaryRatio) {
			if ((maxLegendaryRatio * 0.90) < legendaryRatio) {
				if ((maxLegendaryRatio < legendaryRatio)) {
					legendaryPercent = EnumChatFormatting.DARK_RED + tq.getFormattedPercentageLegendary();
				} else {
					legendaryPercent = EnumChatFormatting.RED + tq.getFormattedPercentageLegendary();
				}
			} else {
				legendaryPercent = EnumChatFormatting.YELLOW + tq.getFormattedPercentageLegendary();
			}
		} else {
			legendaryPercent = EnumChatFormatting.GREEN + tq.getFormattedPercentageLegendary();
		}
		
		
		
		String totalsString = 
				EnumChatFormatting.AQUA + "===== Totals for " + name + " =====\n\n" +
				EnumChatFormatting.AQUA + "Total Captured: " + EnumChatFormatting.GREEN + tq.getCaptured() + "\n" +
				EnumChatFormatting.AQUA + "   Percentage Shiny: " + shinyPercent + "\n" +
				EnumChatFormatting.AQUA + "   Percentage Legendary: " + legendaryPercent + "\n\n" +
				EnumChatFormatting.AQUA + "Total Deleted: " + EnumChatFormatting.GREEN + tq.getDeleted() + "\n" +
				EnumChatFormatting.AQUA + "Total Received: " + EnumChatFormatting.GREEN + tq.getReceived() + "\n" +
				EnumChatFormatting.AQUA + "Total Evolved: " + EnumChatFormatting.GREEN + tq.getEvolved() + "\n" +
				EnumChatFormatting.AQUA + "Total Traded: " + EnumChatFormatting.GREEN + tq.getTraded() + "\n";
		
		Chat.toChat(tq.getSender(), totalsString);
		
		
	}

	public static void displayServerTotals(TotalsQuery tq) {

		String totalsString =
				EnumChatFormatting.AQUA + "===== Server Totals =====\n\n" +
				EnumChatFormatting.AQUA + "Total Captured: " + EnumChatFormatting.GREEN + tq.getCaptured() + "\n" +
				EnumChatFormatting.AQUA + "   Total Shiny: " + EnumChatFormatting.GREEN + tq.getShiny() + "\n" +
				EnumChatFormatting.AQUA + "   Total Legendary: " + EnumChatFormatting.GREEN + tq.getLegendary() + "\n" +
				EnumChatFormatting.AQUA + "Total Deleted: " + EnumChatFormatting.GREEN + tq.getDeleted() + "\n" +
				EnumChatFormatting.AQUA + "Total Received: " + EnumChatFormatting.GREEN + tq.getReceived() + "\n" +
				EnumChatFormatting.AQUA + "Total Evolved: " + EnumChatFormatting.GREEN + tq.getEvolved() + "\n" +
				EnumChatFormatting.AQUA + "Total Traded: " + EnumChatFormatting.GREEN + tq.getTraded() + "\n";
		
	}
	
}
