package com.wvaviator.Pokedex.Handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonDeletedEvent;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Logging.PokedexLog;

public class DeleteHandler {
	
	@SubscribeEvent
	public void onPixelmonDelete(PixelmonDeletedEvent e) {
		
		Pokedex.logger.info(e.player.getName() + " deleted a " + e.pokemon.getString("Name"));
		
		EntityPlayerMP player = e.player;
		NBTTagCompound poke = e.pokemon;
		
		PokedexLog.storeDeleted(player, poke);
		
	}

}
