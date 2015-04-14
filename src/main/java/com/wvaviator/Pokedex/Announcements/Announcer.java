package com.wvaviator.Pokedex.Announcements;

import java.util.List;

import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Configuration.PokedexConfiguration;
import com.wvaviator.Pokedex.Users.Chat;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

public class Announcer {
	
	static boolean annAll = PokedexConfiguration.announceForAll;
	static boolean annAdmins = PokedexConfiguration.announceForAdmins;
	static boolean annPShiny = PokedexConfiguration.announceShinies;
	static boolean annPLegends = PokedexConfiguration.announceLegendaries;
	static boolean annPAll = PokedexConfiguration.announceAllPokemon;
	
	public static void announceCapture(Announcement ann) {
		
		fixFields();
		
		String announce = buildAnnouncement(ann);
		Pokedex.logger.info(announce);
		
		if (annPShiny && ann.getIsShiny()) {
			makeAnnouncement(announce);
			return;
		}
		if (annPLegends && ann.getLegendary()) {
			makeAnnouncement(announce);
			return;
		}
		if (annPAll) {
			makeAnnouncement(announce);
			return;
		}
		
	}

	private static void fixFields() {
		
		if (annAll) annAdmins = false;
		
		if (annPAll) {
			annPShiny = false;
			annPLegends = false;
		}
		
	}

	private static void makeAnnouncement(String announce) {
			
		List<EntityPlayerMP> playerList = (List<EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		for ( EntityPlayerMP onlinePlayer : playerList ) {			
		
			if (annAll) Chat.toChat(onlinePlayer, announce);
			if (annAdmins && onlinePlayer.canUseCommand(2, "pokedex")) Chat.toChat(onlinePlayer, announce);
			
		}
		
	}

	private static String buildAnnouncement(Announcement ann) {
		
		String announce = null;
		announce = EnumChatFormatting.AQUA + ann.getPlayerName() + " has captured a ";
		
		if (ann.getIsShiny()) {
			announce += "shiny ";
		}
		
		announce += ann.getPokeName() + "!";
		
		return announce;
	}

}
