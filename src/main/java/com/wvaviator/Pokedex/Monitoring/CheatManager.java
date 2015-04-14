package com.wvaviator.Pokedex.Monitoring;

import java.sql.SQLException;

import com.wvaviator.Pokedex.Announcements.Notifications;
import com.wvaviator.Pokedex.Users.IPManager;

import net.minecraft.entity.player.EntityPlayerMP;

public class CheatManager {
	
	private static long pokeLootTime = 60000;
	private static long shinyTime = 86400000;
	private static long legendaryTime = 172800000;
	private static long shrineTime = 86400000;
	private static long bossTime = 1200000;
	private static int maxCf = 100;
	
	
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
		
		if (cf.getCF() >= maxCf ) Notifications.notifyOfCheat(cheat);
		
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
		if (cf.getCF() >= maxCf ) Notifications.notifyOfCheat(cheat);
		
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
		if (cf.getCF() >= maxCf ) Notifications.notifyOfCheat(cheat);
		
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
		if (cf.getCF() >= maxCf ) Notifications.notifyOfCheat(cheat);
		
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
		if (cf.getCF() >= maxCf ) Notifications.notifyOfCheat(cheat);
		
		try {
			CheatData.logCheat(cheat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void checkTrade(String uuid, String uuid2) {
		
		String ip = null;
		String ip2 = null;
		
		try {
			ip = IPManager.getIPFromUUID(uuid);
			ip2 = IPManager.getIPFromUUID(uuid2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (ip.equalsIgnoreCase(ip2)) {
			
			Cheat cheat = new Cheat(uuid, CheatType.IP);
			Cheat cheat2 = new Cheat(uuid2, CheatType.IP);
			
			CheatFactor cf = new CheatFactor(uuid);
			CheatFactor cf2 = new CheatFactor(uuid2);
			
			cf.addToCF(Math.abs(cf.getCF() - cf2.getCF()));
			cf2.addToCF(Math.abs(cf.getCF() - cf2.getCF()));
			
			cf.addToCF(10);
			cf2.addToCF(10);
			
			try {
				CheatData.logCheat(cheat);
				CheatData.logCheat(cheat2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (cf.getCF() >= maxCf) {
				Notifications.notifyOfCheat(cheat);
			}
			
		}
		
		
	}

}
