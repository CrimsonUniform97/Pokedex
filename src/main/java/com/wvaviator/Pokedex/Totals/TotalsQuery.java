package com.wvaviator.Pokedex.Totals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

import com.wvaviator.Pokedex.Database.Database;

public class TotalsQuery {
	
	private String uuid;
	private int captured;
	private int deleted;
	private int received;
	private int evolved;
	private int traded;
	private int shiny;
	private int legendary;
	private ICommandSender querier;
	
	public TotalsQuery(String uuid, ICommandSender querier) {
		this.uuid = uuid;
		this.querier = querier;
	}
	
	public void obtainValues() throws SQLException {
		Connection c = Database.getConnection();
		Statement stmt = null;
		String query = "SELECT * FROM totals WHERE uuid = '" + this.uuid + "'";
		
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (!rs.next()) {
				LogTotals.addPlayer(this.uuid);
				return;
			}
			
			this.captured = rs.getInt("captured");
			this.deleted = rs.getInt("deleted");
			this.received = rs.getInt("received");
			this.evolved = rs.getInt("evolved");
			this.traded = rs.getInt("traded");
			this.shiny = rs.getInt("shiny");
			this.legendary = rs.getInt("legendary");
			
		} finally {
			stmt.close();
			c.close();
		}
		
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public ICommandSender getSender() {
		return this.querier;
	}
	
	public int getCaptured() {
		return this.captured;
	}
	
	public int getDeleted() {
		return this.deleted;
	}
	
	public int getReceived() {
		return this.received;
	}
	
	public int getEvolved() {
		return this.evolved;
	}
	
	public int getTraded() {
		return this.traded;
	}
	
	public double getRatioShiny() {
		int shiny = this.shiny;
		int total = this.captured;	
		double ratio = shiny / total;
		return ratio;
	}
	
	public String getFormattedPercentageShiny() {
		DecimalFormat df = new DecimalFormat("#%");
		String formatted = df.format(getRatioShiny());
		return formatted;
	}
	
	public double getRatioLegendary() {
		int legendary = this.legendary;
		int total = this.captured;	
		double ratio = legendary / total;
		return ratio;
	}
	
	public String getFormattedPercentageLegendary() {
		DecimalFormat df = new DecimalFormat("#%");
		String formatted = df.format(getRatioLegendary());
		return formatted;
	}

	public int getShiny() {
		return this.shiny;
	}
	
	public int getLegendary() {
		return this.legendary;
	}

}
