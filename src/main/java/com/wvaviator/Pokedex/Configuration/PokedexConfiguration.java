package com.wvaviator.Pokedex.Configuration;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class PokedexConfiguration {
	
	public static int purgeDays;
	public static int minimumDisplay;
	public static double minimumShinyRatio;
	public static double minimumLegendaryRatio;
	
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
		
		config.save();
	}

}
