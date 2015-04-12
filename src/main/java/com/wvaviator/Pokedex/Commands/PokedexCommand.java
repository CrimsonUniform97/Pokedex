package com.wvaviator.Pokedex.Commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Interpreter.DisplayLogs;
import com.wvaviator.Pokedex.Interpreter.FlagManager;
import com.wvaviator.Pokedex.Interpreter.PrintLogs;
import com.wvaviator.Pokedex.Interpreter.PurgeLogs;
import com.wvaviator.Pokedex.Logging.PokedexLog;
import com.wvaviator.Pokedex.Logging.PokedexQuery;
import com.wvaviator.Pokedex.Users.Chat;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class PokedexCommand implements ICommand {
	
	private List aliases;
	public PokedexCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("pokedex");
		this.aliases.add("pd");
		this.aliases.add("pdx");
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getName() {
		String name = "pokedex";
		return name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		String usage = "/pokedex";
		return usage;
	}

	@Override
	public List getAliases() {
		return this.aliases;
	}

	@Override
	public void execute(ICommandSender sender, String[] args)
			throws CommandException {
		
		if (args.length == 0) {
			Chat.toChat(sender, Chat.noArgs);
			return;
		}
		
		if (args[0].equalsIgnoreCase("help")) {
			CommandHelp.displayHelp(sender, args[1]);
		}
		
		PokedexQuery pdq = new PokedexQuery(sender);
		
		if (args[0].equalsIgnoreCase("print")) {
			
			int total = args.length - 1;
			ArrayList<String> array = new ArrayList<String>();
			for (int x = 1; x == total; x++) {
				
				String flag = args[x];
				
				if (args.length >= x+2) {
					if (args.length >= x+3) {
						array.add(args[x+2]);
					} else {
						array.add(args[x+1]);
					}
				}
				
				FlagManager.applyFlag(flag, array, pdq);
				
			}
			
			try {
				PrintLogs.printData(pdq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if (args[0].equalsIgnoreCase("display")) {
			
			int total = args.length - 1;
			Pokedex.logger.info("Initiating display command");
			ArrayList<String> array = new ArrayList<String>();
			
			for (int x = 0; x < args.length; x++) {
				
				String flag = args[x];
				Pokedex.logger.info("Flag is " + flag);
				
				if (args.length > x+1) {
					Pokedex.logger.info("Args were greater than x+2");
					if (args.length > x+2) {
						Pokedex.logger.info("Args were greater than x+3");
						array.add(args[x+1]);
						array.add(args[x+2]);
						Pokedex.logger.info("Adding " + args[x+1] + " and " + args[x+2] + " to array");
					} else {
						array.add(args[x+1]);
						Pokedex.logger.info("Adding " + args[x+1] + " to array");
					}
				}
				
				//Pokedex.logger.info("Applying flag with " + flag + " and " + array.get(0) + " " + array.get(1));
				FlagManager.applyFlag(flag, array, pdq);
				
			}
			
			try {
				DisplayLogs.displayData(pdq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if (args[0].equalsIgnoreCase("purge")) {
			
			int total = args.length - 1;
			ArrayList<String> array = new ArrayList<String>();
			for (int x = 1; x == total; x++) {
				
				String flag = args[x];
				
				if (args.length >= x+2) {
					if (args.length >= x+3) {
						array.add(args[x+2]);
					} else {
						array.add(args[x+1]);
					}
				}
				
				FlagManager.applyFlag(flag, array, pdq);
				
			}
			
				PurgeLogs.purgeData(pdq);
		}
		
	}
	
	@Override
	public boolean canCommandSenderUse(ICommandSender sender) {
		if (sender.canUseCommand(2, getName())) return true;
		return false;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args,
			BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
