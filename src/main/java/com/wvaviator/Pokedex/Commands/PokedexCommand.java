package com.wvaviator.Pokedex.Commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wvaviator.Pokedex.Logging.PokedexLog;

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
