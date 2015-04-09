package com.wvaviator.Pokedex.Handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.pixelmonmod.pixelmon.api.events.PixelmonTradeEvent;
import com.wvaviator.Pokedex.Logging.PokedexLog;

public class TradeHandler {
	
	@SubscribeEvent
	public void onPixelmonTraded(PixelmonTradeEvent e) {
		
		EntityPlayerMP player = (EntityPlayerMP) e.player1;
		EntityPlayerMP player2 = (EntityPlayerMP) e.player2;
		NBTTagCompound pokemon = e.pokemon1;
		NBTTagCompound pokemon2 = e.pokemon2;
		
		PokedexLog.storeTrade(player, pokemon, player2, pokemon2);
		
		
	}

}
