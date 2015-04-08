package com.wvaviator.Pokedex.Handlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonEvolveEvent;
import com.wvaviator.Pokedex.Pokedex;

public class EvolveHandler {

	@SubscribeEvent
	public void onPixelmonEvolve(PixelmonEvolveEvent e) {
		
		Pokedex.logger.info(e.player.getName() + " evolved a " + e.preEvo.getNickname() + " into a " + e.postEvo.getNickname());
		
	}
	
}
