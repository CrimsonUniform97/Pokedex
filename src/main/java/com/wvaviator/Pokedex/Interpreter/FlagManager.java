package com.wvaviator.Pokedex.Interpreter;

import java.sql.SQLException;
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
	
	public static void applyFlag(String flag, String[] args, PokedexQuery pdq, EntityPlayerMP querier) {
		
		if (flag.equalsIgnoreCase("-u")) {
			
			String playerName = args[0];
			String uuid = null;
			
			try {
				uuid = UUIDManager.getUUID(playerName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (uuid == null) {
				Chat.toChat(querier, Chat.playerNotFound);
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
				Chat.toChat(querier, Chat.pokeNotFound);
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
			
			Chat.toChat(querier, Chat.invalidAction);
			
		}
		
		if (flag.equalsIgnoreCase("-s")) {
			pdq.addShiny(true);
		}
		
		if (flag.equalsIgnoreCase("-d")) {
			String input = args[0];
			SimpleDateFormat f = new SimpleDateFormat("ddMMyy");
			String date = null;
			
			try {
				date = f.parse(input).toString();
			} catch (ParseException e) {
				Chat.toChat(querier, Chat.invalidDate);
				return;
			}
			
			pdq.addDateRange(date, date);		
			
		}
		
		if (flag.equalsIgnoreCase("-r")) {
			
			SimpleDateFormat f = new SimpleDateFormat("ddMMyy");
			
			String from = null;
			String to = null;
			
			try {
			from = f.parse(args[0]).toString();
			to = f.parse(args[1]).toString();
			} catch (ParseException e) {
				Chat.toChat(querier, Chat.invalidDate);
			}
			
			pdq.addDateRange(from, to);

			
		}
		
		if (flag.equalsIgnoreCase("-b")) {
			
			SimpleDateFormat f = new SimpleDateFormat("ddMMyy");
			
			String from = null;
			String to = null;
			
			long current = System.currentTimeMillis();
			Date date = new Date(current);
			to = f.format(date).toString();
			////////
			
			pdq.addDateRange(from, to);
		}
		
	}

}
