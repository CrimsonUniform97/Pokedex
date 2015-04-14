package com.wvaviator.Pokedex.Configuration;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class PokedexConfiguration {
	
	public static int purgeDays;
	public static int minimumDisplay;
	public static double minimumShinyRatio;
	public static double minimumLegendaryRatio;
	public static boolean announceForAll;
	public static boolean announceForAdmins;
	public static boolean announceShinies;
	public static boolean announceLegendaries;
	public static boolean announceAllPokemon;
	
	public static void loadConfig(Configuration config) {
		
		config.addCustomCategoryComment("General", "General Settings");
		
		Property propertyPurgeDays = config.get("General", "Days to Keep Data", 90);
		purgeDays = propertyPurgeDays.getInt();
		propertyPurgeDays.comment = "Any data older than this will be removed from the database";
		
		Property minDisplay = config.get("General", "Minimum Results to Display", 20);
		minimumDisplay = minDisplay.getInt();
		minDisplay.comment = "This is the maximum entries to display in chat for in-game queries";
		
		config.addCustomCategoryComment("Player Totals", "Settings for Displaying Player Totals");
		
		Property shinyPercent = config.get("Player Totals", "Shiny Captures Warning Ratio", 0.05);
		minimumShinyRatio = shinyPercent.getDouble();
		shinyPercent.comment = "This is the ratio of total captures to shiny Pixelmon captures at which admins will be warned"
				+ " that the player may be cheating. Default is 5%";
		
		Property legendaryPercent = config.get("Player Totals", "Legendary Captures Warning Ratio", 0.02);
		minimumLegendaryRatio = legendaryPercent.getDouble();
		legendaryPercent.comment = "This is the ratio of total captures to legendary Pixelmon captures at which admins will be warned"
				+ " that the player may be cheating. Default is 2%";
		
		config.addCustomCategoryComment("Announcements", "Settings for Displaying Announcements");
		
		Property announceP = config.get("Announcements", "Announce Captures to All Players", false);
		announceForAll = announceP.getBoolean();
		announceP.comment = "If true, an announcement will display for all players online when another player"
				+ " captures a Pixelmon";
		
		Property announceA = config.get("Announcements", "Announce Captures to Admins", true);
		announceForAdmins = announceA.getBoolean();
		announceA.comment = "If true, admins will receive announcements when players capture Pixelmon";
		
		Property announceShiny = config.get("Announcements", "Announce When Shiny Pixelmon are Captured", true);
		announceShinies = announceShiny.getBoolean();
		announceShiny.comment = "If true, captures of shiny Pixelmon will be announced";
		
		Property announceLegendary = config.get("Announcements", "Announce When Legendary Pixelmon are Captured", true);
		announceLegendaries = announceLegendary.getBoolean();
		announceLegendary.comment = "If true, captures of Legendary Pixelmon will be announced";
		
		Property announceAll = config.get("Announcements", "Announce the Capture of Any Pixelmon", false);
		announceAllPokemon = announceAll.getBoolean();
		announceAll.comment = "If true, any time a Pixelmon is captured an announcement will be displayed";
		
		
		config.save();
	}

}
