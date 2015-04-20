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
import com.wvaviator.Pokedex.Monitoring.CheatReport;
import com.wvaviator.Pokedex.Monitoring.TopCheaters;
import com.wvaviator.Pokedex.Top.DisplayTop;
import com.wvaviator.Pokedex.Totals.DisplayTotals;
import com.wvaviator.Pokedex.Totals.TotalsQuery;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

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
			if (args.length < 2) {
				
				CommandHelp.displayHelp(sender, null);
				return;
				
			} else {
			
				CommandHelp.displayHelp(sender, args[1]);
				
			}
		}
		
		PokedexQuery pdq = new PokedexQuery(sender);
		
		if (args[0].equalsIgnoreCase("print")) {
			
			
			for (int x = 0; x < args.length; x++) {
				
				String flag = args[x];
				int first = x + 1;
				int second = x + 2;
				ArrayList<String> array = new ArrayList<String>();
				
				if (args.length > first) {
					if (args.length > second) {
						array.add(args[first]);
						array.add(args[second]);
					} else {
						array.add(args[first]);
					}
				}
				
				FlagManager.applyFlag(flag, array, pdq);		
			}
			
			if (pdq.isCanceled()) return;
			
			try {
				PrintLogs.printData(pdq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if (args[0].equalsIgnoreCase("display")) {
			
			
			for (int x = 0; x < args.length; x++) {
				
				String flag = args[x];
				int first = x + 1;
				int second = x + 2;
				ArrayList<String> array = new ArrayList<String>();
				
				if (args.length > first) {
					if (args.length > second) {
						array.add(args[first]);
						array.add(args[second]);
					} else {
						array.add(args[first]);
					}
				}
				
				FlagManager.applyFlag(flag, array, pdq);		
			}
			
			if (pdq.isCanceled()) return;
			
			try {
				DisplayLogs.displayData(pdq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if (args[0].equalsIgnoreCase("purge")) {
			
			
			for (int x = 0; x < args.length; x++) {
				
				String flag = args[x];
				int first = x + 1;
				int second = x + 2;
				ArrayList<String> array = new ArrayList<String>();
				
				if (args.length > first) {
					if (args.length > second) {
						array.add(args[first]);
						array.add(args[second]);
					} else {
						array.add(args[first]);
					}
				}
				
				FlagManager.applyFlag(flag, array, pdq);		
			}
			
				if (pdq.isCanceled()) return;
				PurgeLogs.purgeData(pdq);
		}
		
		if (args[0].equalsIgnoreCase("totals") || args[0].equalsIgnoreCase("total")) {
			
			if (args.length < 2 || args.length > 2) {
				Chat.toChat(sender, Chat.totalsHelp);
				return;
			}
			
			String uuid = null;
			String username = null;
			
			if (args[1].equalsIgnoreCase("server")) {
				uuid = "SERVER";
				TotalsQuery tq = new TotalsQuery(uuid, sender);
				
				DisplayTotals.displayServerTotals(tq);
				return;
				
			} else {
				
				username = args[1];
				
				try {
					uuid = UUIDManager.getUUID(username);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if (uuid == null) {
					Chat.toChat(sender, Chat.playerNotFound);
					return;
				}
				
				TotalsQuery tq = new TotalsQuery(uuid, sender);
				
				DisplayTotals.displayTotals(tq);
			}
			
		}
		
		if (args[0].equalsIgnoreCase("top")) {
			
			if (args.length < 2 || args.length > 2) {
				Chat.toChat(sender, Chat.topHelp);
				return;
			}
			
			String category = args[1];
			
			try {
				
				DisplayTop.displayTop(sender, category);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if (args[0].equalsIgnoreCase("cheat") || args[0].equalsIgnoreCase("cheats") || args[0].equalsIgnoreCase("cheaters")) {
			
			if (args.length < 2 || args.length > 2) {
				Chat.toChat(sender, Chat.cheatHelp);
				return;
			}
			
			if (args[1].equalsIgnoreCase("top")) {
				try {
					TopCheaters.displayTopCheaters(sender);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return;
			}
			
			String uuid = null;
			
			try {
				uuid = UUIDManager.getUUID(args[1]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (uuid == null) {
				Chat.toChat(sender, Chat.playerNotFound);
				return;
			}
			
			try {
				CheatReport.displayCheatReport(sender, uuid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
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
