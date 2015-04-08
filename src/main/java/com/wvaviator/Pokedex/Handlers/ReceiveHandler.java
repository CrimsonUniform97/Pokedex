package com.wvaviator.Pokedex.Handlers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonRecievedEvent;
import com.wvaviator.Pokedex.Pokedex;

public class ReceiveHandler {
	
	@SubscribeEvent
	public void onPixelmonReceived(PixelmonRecievedEvent e) {
		
		Pokedex.logger.info(e.reciever.getName() + " received a " + e.pixelmonRecieved.getNickname());
		
	}

}
