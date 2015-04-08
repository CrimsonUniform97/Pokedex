package com.wvaviator.Pokedex.Configuration;


import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class PokedexConfiguration {
	
	public static int purgeDays;
	
	public static void loadConfig(Configuration config) {
		
		config.addCustomCategoryComment("General", "General Settings");
		
		Property propertyPurgeDays = config.get("General", "Days to Keep Data", 30);
		purgeDays = propertyPurgeDays.getInt();
		propertyPurgeDays.comment = "Any data older than this will be removed from the database";
		
		
		config.save();
	}

}
