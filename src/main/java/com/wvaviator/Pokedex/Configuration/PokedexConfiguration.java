package com.wvaviator.Pokedex.Configuration;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class PokedexConfiguration {
	
	public static int purgeDays;
	public static int minimumDisplay;
	
	public static void loadConfig(Configuration config) {
		
		config.addCustomCategoryComment("General", "General Settings");
		
		Property propertyPurgeDays = config.get("General", "Days to Keep Data", 90);
		purgeDays = propertyPurgeDays.getInt();
		propertyPurgeDays.comment = "Any data older than this will be removed from the database";
		
		Property minDisplay = config.get("General", "Minimum Results to Display", 20);
		minimumDisplay = minDisplay.getInt();
		minDisplay.comment = "This is the maximum entries to display in chat for in-game queries";
		
		config.save();
	}

}
