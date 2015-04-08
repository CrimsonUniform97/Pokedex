package com.wvaviator.Pokedex.Handlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonDeletedEvent;
import com.wvaviator.Pokedex.Pokedex;

public class DeleteHandler {
	
	@SubscribeEvent
	public void onPixelmonDelete(PixelmonDeletedEvent e) {
		
		Pokedex.logger.info(e.player.getName() + " deleted a " + e.pokemon.getString("Name"));
		
		
	}

}
