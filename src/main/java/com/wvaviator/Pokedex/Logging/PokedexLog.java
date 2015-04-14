package com.wvaviator.Pokedex.Logging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

import com.pixelmonmod.pixelmon.api.events.ReceiveType;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Monitoring.CheatManager;
import com.wvaviator.Pokedex.Totals.LogTotals;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class PokedexLog {
	
	private static void storeData(String uuid, String action, String pokemon, String additional, boolean isShiny) throws SQLException {
		
		String insert = "INSERT INTO pixelmonlogs (time, uuid, action, pokemon, additional, shiny) VALUES (?, ?, ?, ?, ?, ?)";
		
		Connection c = Database.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = c.prepareStatement(insert);
			
			stmt.setString(2, uuid);
			stmt.setString(3,  action);
			stmt.setString(4, pokemon);
			stmt.setString(5, additional);
			stmt.setBoolean(6, isShiny);
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			
			stmt.setTimestamp(1, ts);
			
			stmt.execute();
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public static void storeCaught(EntityPlayerMP player, EntityPixelmon pokemon) {
		
		String uuid = player.getUniqueID().toString();
		String action = "captured";
		String poke = pokemon.baseStats.pokemon + "";
		String additional = ".";
		boolean isShiny = pokemon.getIsShiny();
		boolean isLegendary = getIsLegendary(pokemon);
		
		if (isShiny) CheatManager.shinyCheater(player);
		if (isLegendary) CheatManager.legendaryCheater(player);
		
		
		try {
			
			storeData(uuid, action, poke, additional, isShiny);
			LogTotals.addCaptured(uuid, isShiny, isLegendary);
			LogTotals.addCaptured("SERVER", isShiny, isLegendary);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static boolean getIsLegendary(EntityPixelmon pokemon) {
		ArrayList<String> legendaries = EnumPokemon.legendaries;
		String pokeToCheck = pokemon.baseStats.pokemon + "";
		for (String poke : legendaries) {
			if (pokeToCheck.equalsIgnoreCase(poke)) return true;
		}
		return false;
	}

	public static void storeDeleted(EntityPlayerMP player, NBTTagCompound poke) {
		
		String uuid = player.getUniqueID().toString();
		String action = "deleted";
		String pokemon = poke.getString("Name");
		String additional = ".";
		boolean isShiny = poke.getBoolean("IsShiny");
		
		
		try {
			
			storeData(uuid, action, pokemon, additional, isShiny);
			LogTotals.addDeleted(uuid);
			LogTotals.addDeleted("SERVER");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void storeEvolved(EntityPlayerMP player, EntityPixelmon pre, EntityPixelmon post) {
		
		String uuid = player.getUniqueID().toString();
		String action = "evolved";
		String pokemon = pre.baseStats.pokemon + "";
		String additional = post.baseStats.pokemon + "";
		boolean isShiny = pre.getIsShiny();
		
		
		try {
			
			storeData(uuid, action, pokemon, additional, isShiny);
			LogTotals.addEvolved(uuid);
			LogTotals.addEvolved("SERVER");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void storeReceived(EntityPlayerMP player, EntityPixelmon pokemon, ReceiveType received) {
		
		String uuid = player.getUniqueID().toString();
		String action = "received";
		String poke = pokemon.baseStats.pokemon + "";
		String additional = receivedToString(received);
		boolean isShiny = pokemon.getIsShiny();
		
		
		try {
			
			storeData(uuid, action, poke, additional, isShiny);
			LogTotals.addReceived(uuid);
			LogTotals.addReceived("SERVER");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void storeTrade(EntityPlayerMP player, NBTTagCompound pokemon, EntityPlayerMP player2, NBTTagCompound pokemon2) {
		
		String uuid = player.getUniqueID().toString();
		String uuid2 = player2.getUniqueID().toString();
		String action = "traded";
		String action2 = "obtained";
		String poke = pokemon.getString("Name");
		String poke2 = pokemon2.getString("Name");
		String additional = null;
		String additional2 = null;
		
		try {
			additional = UUIDManager.getUsername(uuid2);
			additional2 = UUIDManager.getUsername(uuid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		CheatManager.checkTrade(uuid, uuid2);
		
		boolean isShiny = pokemon.getBoolean("IsShiny");
		boolean isShiny2 = pokemon2.getBoolean("IsShiny");
		
		
		try {
			
			storeData(uuid, action, poke, additional, isShiny);
			storeData(uuid, action2, poke2, additional, isShiny2);
			storeData(uuid2, action, poke2, additional2, isShiny2);
			storeData(uuid2, action2, poke, additional2, isShiny);
			LogTotals.addTraded(uuid2);
			LogTotals.addTraded(uuid);
			LogTotals.addTraded("SERVER");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private static String receivedToString(ReceiveType r) {
		
		if (r == ReceiveType.Fossil) return "fossil";
		if (r == ReceiveType.Starter) return "starter";
		//if (r.equals(ReceiveType.Halloween)) return "halloween";
		//if (r.equals(ReceiveType.Christmas)) return "christmas";
		//if (r.equals(ReceiveType.Command)) return "command";
		
		return null;
	}

}
