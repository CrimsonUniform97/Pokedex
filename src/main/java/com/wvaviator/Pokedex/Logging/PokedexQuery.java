package com.wvaviator.Pokedex.Logging;

import java.sql.Timestamp;

import net.minecraft.entity.player.EntityPlayerMP;

public class PokedexQuery {
	
	private EntityPlayerMP player;
	private String searchUser;
	private String searchPokemon;
	private boolean searchIsShiny;
	private String searchFromDate;
	private String searchToDate;
	private String searchAction;
	private String query;
	
	public PokedexQuery(EntityPlayerMP player) {
		this.player = player;
	}
	
	public void addUser(String uuid) {
		this.searchUser = uuid;	
	}
	
	public void addPokemon(String pokemonname) {
		this.searchPokemon = pokemonname;
	}
	
	public void addShiny(boolean isShiny) {
		this.searchIsShiny = isShiny;
	}
	
	public void addAction(String action) {
		this.searchAction = action;
	}
	
	public void addDateRange(String from, String to) {
		this.searchToDate = to;
		this.searchFromDate = from;
	}
	
	public String getUUID() {
		return this.searchUser;
	}
	
	public String getFromDate() {
		return this.searchFromDate;
	}
	
	public String getToDate() {
		return this.searchFromDate;
	}
	
	public String getDate() {
		if (this.searchFromDate.equals(this.searchToDate)) {
			return this.searchFromDate;
		}
		return null;
	}
	
	public String getPokemon() {
		return this.searchPokemon;
	}
	
	public boolean getShiny() {
		return this.searchIsShiny;
	}
	
	public String getAction() {
		return this.searchAction;
	}


	public int countQueries() {
		int count = 0;
		if (this.searchUser != null) count++;
		if (this.searchPokemon != null) count++;
		if (this.searchDate != null) count++;
		if (this.searchAction != null) count++;
		if (this.searchIsShiny != false) count++;
		return count;
		
	}

}