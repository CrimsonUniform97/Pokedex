package com.wvaviator.Pokedex.Handlers;

import java.sql.SQLException;

import com.wvaviator.Pokedex.Users.UUIDManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class LoginHandler {
	
	@SubscribeEvent
	public void onPlayerLogin(EntityJoinWorldEvent e) {
		
		if (!(e.entity instanceof EntityPlayerMP)) {
			return;
		}
		
		EntityPlayerMP player = (EntityPlayerMP) e.entity;
		
		try {
			
			if (UUIDManager.isInDatabase(player)) {
			
				if (!(UUIDManager.didUsernameChange(player))) return;	
				UUIDManager.updateUsername(player);
				return;	
			}
		
			UUIDManager.addPlayer(player);
		
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
	}

}
