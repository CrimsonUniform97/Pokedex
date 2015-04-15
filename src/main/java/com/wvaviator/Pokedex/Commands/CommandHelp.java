package com.wvaviator.Pokedex.Commands;

import com.wvaviator.Pokedex.Users.Chat;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class CommandHelp {
	
	private static String generalHelp = 
					  EnumChatFormatting.AQUA + "====== Pokedex Commands =====\n\n"
					+ EnumChatFormatting.GOLD + "/pokedex" + EnumChatFormatting.AQUA + " Display basic information about the plugin\n"
					+ EnumChatFormatting.GOLD + "/pokedex help" + EnumChatFormatting.AQUA + " Display this page with basic help information\n"
					+ EnumChatFormatting.GOLD + "/pokedex help flags" + EnumChatFormatting.AQUA + " Display a list of flags and parameters\n"
					+ EnumChatFormatting.GOLD + "/pokedex help actions" + EnumChatFormatting.AQUA + " Display a list of actions to be used with " + EnumChatFormatting.AQUA + "the action flag\n"
					+ EnumChatFormatting.GOLD + "/pokedex help top" + EnumChatFormatting.AQUA + " Display a list of categories for the top " + EnumChatFormatting.AQUA + "command\n"
					+ EnumChatFormatting.GOLD + "/pokedex display <parameters>" + EnumChatFormatting.AQUA + " Search the logs using the " + EnumChatFormatting.AQUA + "specified flags\n"
					+ EnumChatFormatting.GOLD + "/pokedex print <parameters>" + EnumChatFormatting.AQUA + " Print logs to a text file using " + EnumChatFormatting.AQUA + "the specified flags\n"
					+ EnumChatFormatting.GOLD + "/pokedex purge <parameters>" + EnumChatFormatting.AQUA + " Delete all entries found with " + EnumChatFormatting.AQUA + "the specified flags\n"
					+ EnumChatFormatting.GOLD + "/pokedex totals <server/playername>" + EnumChatFormatting.AQUA + " Show totals for a " + EnumChatFormatting.AQUA + "specific player or for the entire server\n"
					+ EnumChatFormatting.GOLD + "/pokedex top <category>" + EnumChatFormatting.AQUA + " Display a list of top players in a " + EnumChatFormatting.AQUA + "category\n"
					+ EnumChatFormatting.GOLD + "/pokedex cheat <top/player>" + EnumChatFormatting.AQUA + " Display top potential cheaters " + EnumChatFormatting.AQUA + "or a cheat report for a specific player\n";
	private static String flagsHelp =
					  EnumChatFormatting.AQUA + "===== Pokedex Flags =====\n\n"
					+ EnumChatFormatting.GOLD + "-u <player>" + EnumChatFormatting.AQUA + " Search entries matching a specific player\n"
					+ EnumChatFormatting.GOLD + "-p <pokemon>" + EnumChatFormatting.AQUA + " Search entries matching the specified Pokemon\n"
					+ EnumChatFormatting.GOLD + "-a <action>" + EnumChatFormatting.AQUA + " Search entries matching the specified action\n"
					+ EnumChatFormatting.GOLD + "-s" + EnumChatFormatting.AQUA + " Search entries where the Pokemon are shiny\n"
					+ EnumChatFormatting.GOLD + "-d <date>" + EnumChatFormatting.AQUA + " Search entries on a specific date, using the format " + EnumChatFormatting.AQUA + "DDMMYY\n"
					+ EnumChatFormatting.GOLD + "-r <date from> <date to>" + EnumChatFormatting.AQUA + " Search entries in the specified date " + EnumChatFormatting.AQUA + "range, using format DDMMYY\n"
					+ EnumChatFormatting.GOLD + "-b <lookback>" + EnumChatFormatting.AQUA + " Search entried made the specified number of " + EnumChatFormatting.AQUA + "days in the past until now\n";
	private static String actionHelp =
					EnumChatFormatting.AQUA + "===== Pokedex Action Parameters =====\n\n"
					+ EnumChatFormatting.GOLD + "Captured" + EnumChatFormatting.AQUA + " Displays when players capture a Pokemon\n"
					+ EnumChatFormatting.GOLD + "Deleted" + EnumChatFormatting.AQUA + " Displays when players delete a Pokemon\n"
					+ EnumChatFormatting.GOLD + "Evolved" + EnumChatFormatting.AQUA + " Displays when players evolve their Pokemon\n"
					+ EnumChatFormatting.GOLD + "Received" + EnumChatFormatting.AQUA + " Displays when players receive Pokemon\n"
					+ EnumChatFormatting.GOLD + "Traded" + EnumChatFormatting.AQUA + " Displays when players trade away their Pokemon\n"
					+ EnumChatFormatting.GOLD + "Obtained" + EnumChatFormatting.AQUA + " Displays when players receive a Pokemon through " + EnumChatFormatting.AQUA + "trade\n";
	private static String topHelp =
					EnumChatFormatting.AQUA + "===== Pokedex Top Categories =====\n\n"
					+ EnumChatFormatting.GOLD + "Captured" + EnumChatFormatting.AQUA + " Lists players who have captured the most Pixelmon\n"
					+ EnumChatFormatting.GOLD + "Shiny" + EnumChatFormatting.AQUA + " Lists players who have captured the most shiny Pixelmon\n"
					+ EnumChatFormatting.GOLD + "Legendary" + EnumChatFormatting.AQUA + " Lists players who have captured the most " + EnumChatFormatting.AQUA + "Legendary Pixelmon\n"
					+ EnumChatFormatting.GOLD + "Deleted" + EnumChatFormatting.AQUA + " Lists players who have deleted the most Pixelmon\n"
					+ EnumChatFormatting.GOLD + "Received" + EnumChatFormatting.AQUA + " Lists players who have received the most Pixelmon\n"
					+ EnumChatFormatting.GOLD + "Evolved" + EnumChatFormatting.AQUA + " Lists players who have evolved the most Pixelmon\n"
					+ EnumChatFormatting.GOLD + "Traded" + EnumChatFormatting.AQUA + " Lists players who have traded the most Pixelmon\n";
	
	
	public static void displayHelp(ICommandSender sender, String arg) {
		
		if (arg == null) {
			Chat.toChat(sender, generalHelp);
			return;
		}
		
		if (arg.equalsIgnoreCase("flag") || arg.equalsIgnoreCase("flags")) {
			Chat.toChat(sender, flagsHelp);
			return;
		}
		
		if (arg.equalsIgnoreCase("action") || arg.equalsIgnoreCase("actions")) {
			Chat.toChat(sender, actionHelp);
			return;
		}
		
		if (arg.equalsIgnoreCase("top")) {
			Chat.toChat(sender, topHelp);
			return;
		}
		
	}

}
