package com.wvaviator.Pokedex.Handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonRecievedEvent;
import com.pixelmonmod.pixelmon.api.events.ReceiveType;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Logging.PokedexLog;

public class ReceiveHandler {
	
	@SubscribeEvent
	public void onPixelmonReceived(PixelmonRecievedEvent e) {
		
		if (e.receiveType == ReceiveType.PokeBall) {
			return;
		}
		if (!(e.reciever instanceof EntityPlayerMP)) return;
		
		EntityPlayerMP player = (EntityPlayerMP) e.reciever;
		EntityPixelmon pokemon = e.pixelmonRecieved;
		ReceiveType received = e.receiveType;
		
		PokedexLog.storeReceived(player, pokemon, received);
	}

}
