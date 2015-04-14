package com.wvaviator.Pokedex.Announcements;

import java.sql.SQLException;
import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

import com.wvaviator.Pokedex.Monitoring.Cheat;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class Notifications {
	
	public static void notifyOfCheat(Cheat cheat) {
		
		String name = null;
		try {
			name = UUIDManager.getUsername(cheat.getUUID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String cheater = EnumChatFormatting.GOLD + name + 
				EnumChatFormatting.AQUA + " may be cheating! Use " + EnumChatFormatting.GOLD +
				"/pokedex cheat " + name + EnumChatFormatting.AQUA + " for more information";
		
		List<EntityPlayerMP> playerList = (List<EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		for ( EntityPlayerMP onlinePlayer : playerList ) {			
		
			if (onlinePlayer.canUseCommand(2, "pokedex")) {
				
				Chat.toChat(onlinePlayer, cheater);
				
			}
			
		}
		
	}

}
