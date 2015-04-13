package com.wvaviator.Pokedex.Interpreter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.minecraft.util.EnumChatFormatting;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Logging.PokedexQuery;
import com.wvaviator.Pokedex.Users.Chat;
import com.wvaviator.Pokedex.Users.UUIDManager;

public class PrintLogs {
	
	static String logsDirectory = Pixelmon.modDirectory.getPath() + "/Pokedex";
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	static SimpleDateFormat fileDF = new SimpleDateFormat("dd-MM-yy HH.mm.ss");

	public static void printData(PokedexQuery pdq) throws SQLException {

			File logDir = new File(logsDirectory);
			if (!(logDir.exists())) logDir.mkdir();

			String time = fileDF.format(System.currentTimeMillis());
			File logFile = new File(logsDirectory + "/" + time + ".txt");
			
			try {
				
				ResultSet rs = DataRetrieval.retreiveResults(pdq);
				
				if (!rs.next()) {
					Chat.toChat(pdq.getSender(), Chat.noResults);
					return;
				}

				try {
					
					writeDataToFile(rs, logFile, pdq);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			} finally {
				Database.closeConnection();
			}

	}

	private static void writeDataToFile(ResultSet rs, File logFile, PokedexQuery pdq) throws IOException {
		
		FileOutputStream fos = new FileOutputStream(logFile);
		BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(fos));
		
		String date = sdf.format(System.currentTimeMillis());
		
		buf.write("Pokedex Logs for " + date);
		buf.newLine();
		buf.newLine();
		buf.write("Query initiated by " + pdq.getSender().getName());
		buf.newLine();
		buf.write("Query includes the following search parameters:");
		buf.newLine();
		
		if (pdq.getUUID() != null) {
			buf.write("UUID = " + pdq.getUUID());
			buf.newLine();
			
			try {
				buf.write("Name = " + UUIDManager.getUsername(pdq.getUUID()));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			buf.newLine();
			
		}
		
		if (pdq.getPokemon() != null) {
			buf.write("Pokemon = " + pdq.getPokemon());
			buf.newLine();
		}
		
		if (pdq.getAction() != null) {
			buf.write("Action = " + pdq.getAction());
			buf.newLine();
		}
		
		if (pdq.getShiny()) {
			buf.write("Shiny = " + pdq.getShiny());
			buf.newLine();
		}
		
		if (pdq.getUUID() == null && pdq.getPokemon() == null && pdq.getAction() == null && pdq.getToDate() == null && pdq.getShiny() == false) {
			buf.write("No parameters specified, retrieving all logs");
			buf.newLine();
		}
		
		buf.newLine();
		
		try {
			do {
				
				buf.write(displayInfo(rs));
				buf.newLine();	
			
			} while (rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		buf.close();
		fos.close();
		
	}
	
	private static String displayInfo(ResultSet rs) throws SQLException {
		
		String time = sdf.format(rs.getTimestamp("time"));
		String name = UUIDManager.getUsername(rs.getString("uuid"));
		String pokemon = rs.getString("pokemon");
		String action = rs.getString("action");
		boolean shinyB = rs.getBoolean("shiny");
		String additional = rs.getString("additional");
		if (additional.equals(".")) additional = "";
		String shiny = "";
		if (shinyB == true) shiny = "Shiny ";
		String additionalValue = "";
		if (action.equalsIgnoreCase("obtained")) additionalValue = " from";
		if (action.equalsIgnoreCase("received")) additionalValue = " by";
		if (action.equalsIgnoreCase("traded")) additionalValue = " to";
		if (action.equalsIgnoreCase("evolved")) additionalValue = " into";
		
		String display = time + " " + name + " " + action + " " + shiny + pokemon + additionalValue + " " + additional;
		return display;
		
	}

}
