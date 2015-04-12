package com.wvaviator.Pokedex.Handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonEvolveEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Logging.PokedexLog;

public class EvolveHandler {

	@SubscribeEvent
	public void onPixelmonEvolve(PixelmonEvolveEvent e) {
		
		if (!(e.player instanceof EntityPlayerMP)) return;
		EntityPlayerMP player = (EntityPlayerMP) e.player;
		
		EntityPixelmon pre = e.preEvo;
		EntityPixelmon post = e.postEvo;
		
		PokedexLog.storeEvolved(player, pre, post);
		
		
	}
	
}
