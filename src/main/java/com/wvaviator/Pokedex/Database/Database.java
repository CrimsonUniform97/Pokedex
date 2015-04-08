package com.wvaviator.Pokedex.Database;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModClassLoader;

import com.wvaviator.Pokedex.Pokedex;

public class Database {
	
	public static Connection c = null;
	private final static String DRIVER = "h2-1.3.173.jar";
	private final static String DATABASE = "pokedex";
	private static String databaseDir = Pokedex.databaseDirectory.getPath() + "/";
	private static String databasePath = databaseDir + DATABASE;
	private static String driverPath = databaseDir + DRIVER;
	private static File driverFile = null;

	
	public static Connection getConnection() {
		
		driverFile = new File(driverPath);
		if (!(driverFile.exists())) {
			Pokedex.logger.error("Could not find driver file at " + driverPath);
		}
		
		try {
		
			((ModClassLoader) Loader.instance().getModClassLoader()).addFile(driverFile);

			Class.forName("org.h2.Driver");
			c = DriverManager.getConnection("jdbc:h2:file:" + databasePath);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;
		
	}
	
	public static void closeConnection() {
		
		try {
			
			if (!(c.isClosed())) {
				c.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initializeTables() {
		
		if (Analyzer.playersTableExists() == false) {
			
			
			try {
				
				Construction.establishPlayerTable();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		if (Analyzer.logsTableExists() == false) {
			
			try {
				
				Construction.establishLogTable();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public static void createDatabase() {
		
		getConnection();
		closeConnection();
		
	}

}
