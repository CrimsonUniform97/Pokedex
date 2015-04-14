package com.wvaviator.Pokedex.Handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PokeLootClaimedEvent;
import com.wvaviator.Pokedex.Monitoring.CheatManager;

public class PokeLootHandler {
	
	@SubscribeEvent
	public void onPokeLootClaimed(PokeLootClaimedEvent e) {
		
		if (!(e.player instanceof EntityPlayerMP)) return;
		EntityPlayerMP player = (EntityPlayerMP) e.player;
		CheatManager.pokeLootCheater(player);
		
	}

}
