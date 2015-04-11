package com.wvaviator.Pokedex.Interpreter;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.minecraft.entity.player.EntityPlayerMP;

import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.wvaviator.Pokedex.Logging.PokedexQuery;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class FlagManager {
	
	public static void applyFlag(String flag, String[] args, PokedexQuery pdq) {
		
		if (flag.equalsIgnoreCase("-u")) {
			
			String playerName = args[0];
			String uuid = null;
			
			try {
				uuid = UUIDManager.getUUID(playerName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (uuid == null) {
				Chat.toChat(pdq.getSender(), Chat.playerNotFound);
				return;
			}
			
			pdq.addUser(uuid);
			
		}
		
		if (flag.equalsIgnoreCase("-p")) {
			
			String pokemon = args[0];
			boolean matches = false;
			
			for (EnumPokemon p : EnumPokemon.values()) {
				if (pokemon.equalsIgnoreCase(p.name)) matches = true;
			}
			
			if (matches == false) {
				Chat.toChat(pdq.getSender(), Chat.pokeNotFound);
				return;
			}
			
			pdq.addPokemon(pokemon);
			
		}
		
		if (flag.equalsIgnoreCase("-a")) {
			
			String action = args[0];
			
			if (action.equalsIgnoreCase("captured")) {
				pdq.addAction(action.toLowerCase());
				return;
			}
			if (action.equalsIgnoreCase("deleted")) {
				pdq.addAction(action.toLowerCase());
				return;
			}
			if (action.equalsIgnoreCase("received")) {
				pdq.addAction(action.toLowerCase());
				return;
			}
			if (action.equalsIgnoreCase("evolved")) {
				pdq.addAction(action.toLowerCase());
				return;
			}
			if (action.equalsIgnoreCase("traded")) {
				pdq.addAction(action.toLowerCase());
				return;
			}
			if (action.equalsIgnoreCase("obtained")) {
				pdq.addAction(action.toLowerCase());
				return;
			}
			
			Chat.toChat(pdq.getSender(), Chat.invalidAction);
			
		}
		
		if (flag.equalsIgnoreCase("-s")) {
			pdq.addShiny(true);
		}
		
		if (flag.equalsIgnoreCase("-d")) {
			
			Timestamp from = null;
			Timestamp to = null;
			
			String input = args[0];
			SimpleDateFormat f = new SimpleDateFormat("ddMMyy");
			Date date = null;
			
			try {
				date = f.parse(input);
			} catch (ParseException e) {
				Chat.toChat(pdq.getSender(), Chat.invalidDate);
				return;
			}
			
			long time = date.getTime();
			
			from = new Timestamp(time);
			to = new Timestamp(time + 86400000);
			
			pdq.addDateRange(from, to);		
			
		}
		
		if (flag.equalsIgnoreCase("-r")) {
			
			SimpleDateFormat f = new SimpleDateFormat("ddMMyy");
			
			Timestamp from = null;
			Timestamp to = null;
			
			try {
			from = new Timestamp(f.parse(args[0]).getTime());
			to = new Timestamp(f.parse(args[1]).getTime() + 86400000);
			} catch (ParseException e) {
				Chat.toChat(pdq.getSender(), Chat.invalidDate);
			}
			
			pdq.addDateRange(from, to);

			
		}
		
		if (flag.equalsIgnoreCase("-b")) {
			
			SimpleDateFormat f = new SimpleDateFormat("ddMMyy");
			
			Timestamp from = null;
			Timestamp to = null;
			
			long current = System.currentTimeMillis();
			to = new Timestamp(current);
			int daysBack = 0;
			try {
				daysBack = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				Chat.toChat(pdq.getSender(), Chat.badFlag);
				return;
			}
			
			long daysInMs = current - (daysBack * 86400000);
			
			from = new Timestamp(daysInMs);
		
			pdq.addDateRange(from, to);
			
		}
		
	}

}
