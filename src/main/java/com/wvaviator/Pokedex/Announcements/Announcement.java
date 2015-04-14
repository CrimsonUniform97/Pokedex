package com.wvaviator.Pokedex.Announcements;

import java.util.ArrayList;

import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;

import net.minecraft.entity.player.EntityPlayerMP;

public class Announcement {
	
	private EntityPlayerMP player;
	private EntityPixelmon pixelmon;
	private boolean shiny;
	private boolean legendary;
	private String playerName;
	private String pokeName;
	
	
	public Announcement(EntityPlayerMP player, EntityPixelmon pixelmon) {
		this.player = player;
		this.pixelmon = pixelmon;
		this.shiny = pixelmon.getIsShiny();
		this.legendary = getIsLegendary(pixelmon);
		this.playerName = player.getName();
		this.pokeName = pixelmon.baseStats.pokemon + "";
	}


	private static boolean getIsLegendary(EntityPixelmon pokemon) {
		ArrayList<String> legendaries = EnumPokemon.legendaries;
		String pokeToCheck = pokemon.baseStats.pokemon + "";
		for (String poke : legendaries) {
			if (pokeToCheck.equalsIgnoreCase(poke)) return true;
		}
		return false;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public String getPokeName() {
		return this.pokeName;
	}
	
	public boolean getIsShiny() {
		return this.shiny;
	}
	
	public boolean getLegendary() {
		return this.legendary;
	}
	
	public EntityPlayerMP getPlayer() {
		return this.player;
	}
	
	public EntityPixelmon getPixelmon() {
		return this.pixelmon;
	}
	

}
