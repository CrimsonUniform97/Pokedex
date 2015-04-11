package com.wvaviator.Pokedex.Interpreter;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.wvaviator.Pokedex.Pokedex;
import com.wvaviator.Pokedex.Logging.PokedexQuery;

public class PrintLogs {
	
	static String logsDirectory = Pokedex.configurationDirectory.getPath() + "/Pokedex";
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

	public static void printData(PokedexQuery pdq) throws SQLException {

			File logDir = new File(logsDirectory);
			if (!(logDir.exists())) logDir.mkdir();
		
			ResultSet rs = DataRetrieval.retreiveResults(pdq);
			String time = sdf.format(System.currentTimeMillis());
			
			File logFile = new File(logsDirectory + "/" + time + ".txt");
			
			writeDataToFile(rs, logFile);

	}

	private static void writeDataToFile(ResultSet rs, File logFile) {
		// TODO Auto-generated method stub
		
	}

}
