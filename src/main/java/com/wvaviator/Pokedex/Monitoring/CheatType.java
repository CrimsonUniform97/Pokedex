package com.wvaviator.Pokedex.Monitoring;

public enum CheatType {

	POKELOOT("Pokeloot"), 
	SHINY("Shiny"), 
	LEGENDARY("Legendary"), 
	BOSS("Boss"), 
	SHRINE("Shrine"),
	IP("Same IP Trade");
	
	public String type;
	
	private CheatType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
	
}
