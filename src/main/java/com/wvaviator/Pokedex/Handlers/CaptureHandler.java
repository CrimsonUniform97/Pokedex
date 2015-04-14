package com.wvaviator.Pokedex.Handlers;

import com.pixelmonmod.pixelmon.api.events.PixelmonCaptureEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Announcements.Announcement;
import com.wvaviator.Pokedex.Announcements.Announcer;
import com.wvaviator.Pokedex.Logging.PokedexLog;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CaptureHandler {
	
	@SubscribeEvent
	public void onPixelmonCapture(PixelmonCaptureEvent e) {
		
		EntityPixelmon pokemon = e.capturedPixelmon;
		EntityPlayerMP player = (EntityPlayerMP) e.player;
		
		PokedexLog.storeCaught(player, pokemon);
		
		Announcement ann = new Announcement(player, pokemon);
		Announcer.announceCapture(ann);
		
	}

}
