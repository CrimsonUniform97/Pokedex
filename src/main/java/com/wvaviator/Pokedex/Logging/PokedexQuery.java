package com.wvaviator.Pokedex.Logging;

import java.sql.Timestamp;
import java.util.Date;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class PokedexQuery {
	
	private ICommandSender sender;
	private String searchUser;
	private String searchPokemon;
	private boolean searchIsShiny;
	private Timestamp searchFromDate;
	private Timestamp searchToDate;
	private String searchAction;
	private String query;
	private boolean canceled;
	
	public PokedexQuery(ICommandSender sender) {
		this.sender = sender;
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
	
	public void addDateRange(Timestamp from, Timestamp to) {
		this.searchToDate = to;
		this.searchFromDate = from;
	}
	
	public String getUUID() {
		return this.searchUser;
	}
	
	public Timestamp getFromDate() {
		return this.searchFromDate;
	}
	
	public Timestamp getToDate() {
		return this.searchToDate;
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
	
	public ICommandSender getSender() {
		return this.sender;
	}
	
	public void setCanceled() {
		this.canceled = true;
	}
	
	public boolean isCanceled() {
		return this.canceled;
	}

	public int countQueries() {
		int count = 0;
		if (this.searchUser != null) count++;
		if (this.searchPokemon != null) count++;
		if (this.searchToDate != null) count++;
		if (this.searchAction != null) count++;
		if (this.searchIsShiny != false) count++;
		return count;
		
	}

}
