package com.wvaviator.Pokedex.Users;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Chat {

	public static String playerNotFound = EnumChatFormatting.AQUA + "Player not found!";
	public static String pokeNotFound = EnumChatFormatting.AQUA + "Pokemon not found!";
	public static String invalidAction = EnumChatFormatting.AQUA + "The specified action is not valid!\nPlease use " 
										+ EnumChatFormatting.GOLD + "/pokedex flags action " + EnumChatFormatting.AQUA + 
										"to see available actions.";
	public static String invalidDate = EnumChatFormatting.AQUA + "Could not read date flag!\n"
									+ "The correct format is " + EnumChatFormatting.GOLD + "DDMMYY";
	public static String badFlag = EnumChatFormatting.AQUA + "Error found in your query! Check your flags.";
	
	
	
	
	
	public static void toChat(EntityPlayerMP player, String message) {
		ChatComponentText c = new ChatComponentText(message);
		player.addChatMessage(c);
	}
	
	
}