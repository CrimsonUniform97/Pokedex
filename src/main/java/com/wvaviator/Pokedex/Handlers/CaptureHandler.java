package com.wvaviator.Pokedex.Handlers;

import com.pixelmonmod.pixelmon.api.events.PixelmonCaptureEvent;
import com.wvaviator.Pokedex.Pokedex;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CaptureHandler {
	
	@SubscribeEvent
	public void onPixelmonCapture(PixelmonCaptureEvent e) {
		
		Pokedex.logger.info(e.player.getName() + " captured a " + e.capturedPixelmon.getNickname());
		
	}

}
