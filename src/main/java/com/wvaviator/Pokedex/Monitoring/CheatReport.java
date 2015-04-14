package com.wvaviator.Pokedex.Monitoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

import com.wvaviator.Pokedex.Configuration.PokedexConfiguration;
import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.PlayerTime;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class CheatReport {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
	
	public static void displayCheatReport(ICommandSender sender, String uuid) throws SQLException {
		
		String query = "SELECT * FROM cheatlogs WHERE uuid = '" + uuid + "'";
		Connection c = Database.getConnection();
		Statement stmt = null;
		
		try {
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				Chat.toChat(sender, Chat.noResults);
				return;
			}
			
			int count = 0;
			do {
				count++;
			} while (rs.next());

			int reduce = PokedexConfiguration.minimumDisplay;
			
			if (count < reduce) reduce = count;
			
			while(reduce != 0) {
				reduce--;
				rs.previous();
			}
			
			String name = UUIDManager.getUsername(uuid);
			displayHeader(sender, name);
			
			do {
				
				String time = sdf.format(rs.getTimestamp("time"));
				String cheat = rs.getString("cheat");
				
				Chat.toChat(sender, displayInfo(time, cheat));
				
			} while (rs.next());
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}

	private static void displayHeader(ICommandSender sender, String name) {
		
		String header = EnumChatFormatting.AQUA + "===== Cheat Report for " + name + " =====\n\n";		
		Chat.toChat(sender, header);
		
	}

	private static String displayInfo(String time, String cheat) {
		
		String display = EnumChatFormatting.AQUA + time + " " + EnumChatFormatting.GOLD + cheat;	
		return display;
	}

}
