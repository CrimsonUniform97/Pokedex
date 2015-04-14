package com.wvaviator.Pokedex.Monitoring;

import java.sql.SQLException;

import net.minecraft.entity.player.EntityPlayerMP;

public class CheatManager {
	
	private static long pokeLootTime = 60000;
	private static long shinyTime = 86400000;
	private static long legendaryTime = 172800000;
	private static long shrineTime = 86400000;
	private static long bossTime = 1200000;
	
	
	public static void pokeLootCheater(EntityPlayerMP player) {
	
		if (pokeLootTime <= 0) return;
		
		String uuid = player.getUniqueID().toString();
		CheatFactor cf = new CheatFactor(uuid);
		Cheat cheat = new Cheat(uuid, CheatType.POKELOOT);
		long timeSince = 0;
		try {
			timeSince = cheat.timeSince(CheatData.getLastTime(cheat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double cheatChance = pokeLootTime / timeSince;
		if (timeSince > 1) timeSince = 1;
		double newCf = cheatChance * 20;
		
		cf.addToCF((int) newCf);
		
		
		try {
			CheatData.logCheat(cheat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void shinyCheater(EntityPlayerMP player) {
		
		if (shinyTime <= 0) return;
		
		String uuid = player.getUniqueID().toString();
		CheatFactor cf = new CheatFactor(uuid);
		Cheat cheat = new Cheat(uuid, CheatType.SHINY);
		long timeSince = 0;
		try {
			timeSince = cheat.timeSince(CheatData.getLastTime(cheat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double cheatChance = shinyTime / timeSince;
		if (timeSince > 1) timeSince = 1;
		double newCf = cheatChance * 100;
		
		cf.addToCF((int) newCf);
		
		try {
			CheatData.logCheat(cheat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void legendaryCheater(EntityPlayerMP player) {
		
		if (legendaryTime <= 0) return;
		
		String uuid = player.getUniqueID().toString();
		CheatFactor cf = new CheatFactor(uuid);
		Cheat cheat = new Cheat(uuid, CheatType.LEGENDARY);
		long timeSince = 0;
		try {
			timeSince = cheat.timeSince(CheatData.getLastTime(cheat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double cheatChance = legendaryTime / timeSince;
		if (timeSince > 1) timeSince = 1;
		double newCf = cheatChance * 100;
		
		cf.addToCF((int) newCf);
		
		try {
			CheatData.logCheat(cheat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void bossCheater(EntityPlayerMP player) {
		
		if (bossTime <= 0) return;
		
		String uuid = player.getUniqueID().toString();
		CheatFactor cf = new CheatFactor(uuid);
		Cheat cheat = new Cheat(uuid, CheatType.BOSS);
		long timeSince = 0;
		try {
			timeSince = cheat.timeSince(CheatData.getLastTime(cheat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double cheatChance = bossTime / timeSince;
		if (timeSince > 1) timeSince = 1;
		double newCf = cheatChance * 50;
		
		cf.addToCF((int) newCf);
		
		try {
			CheatData.logCheat(cheat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void shrineCheater(EntityPlayerMP player) {
		
		if (shrineTime <= 0) return;
		
		String uuid = player.getUniqueID().toString();
		CheatFactor cf = new CheatFactor(uuid);
		Cheat cheat = new Cheat(uuid, CheatType.SHRINE);
		long timeSince = 0;
		try {
			timeSince = cheat.timeSince(CheatData.getLastTime(cheat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double cheatChance = shrineTime / timeSince;
		if (timeSince > 1) timeSince = 1;
		double newCf = cheatChance * 40;
		
		cf.addToCF((int) newCf);
		
		try {
			CheatData.logCheat(cheat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
