package com.wvaviator.Pokedex.Monitoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class TopCheaters {
	
	public static void displayTopCheaters(ICommandSender sender) throws SQLException {
	
		String query = "SELECT * FROM players ORDER BY cf DESC";
		Connection c = Database.getConnection();
		Statement stmt = null;
		String display = EnumChatFormatting.AQUA + "===== Top Potential Cheaters =====\n";
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				Chat.toChat(sender, Chat.noResults);
				return;
			}
			
			for (int i = 1; i < 11; i++) {
				
				display += "\n" + EnumChatFormatting.AQUA;
				display += i + ". " + EnumChatFormatting.GOLD + UUIDManager.getUsername(rs.getString("uuid"));
				display += "    " + EnumChatFormatting.AQUA + rs.getInt("cf") + "%";
				if (!rs.next()) break;
			
			}
		
			Chat.toChat(sender, display);
			
		} finally {
			
			stmt.close();
			c.close();
		}
	}
}
