package com.wvaviator.Pokedex;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.wvaviator.Pokedex.Commands.PokedexCommand;
import com.wvaviator.Pokedex.Configuration.PokedexConfiguration;
import com.wvaviator.Pokedex.Database.Database;
import com.wvaviator.Pokedex.Handlers.CaptureHandler;
import com.wvaviator.Pokedex.Handlers.DeleteHandler;
import com.wvaviator.Pokedex.Handlers.EvolveHandler;
import com.wvaviator.Pokedex.Handlers.LoginHandler;
import com.wvaviator.Pokedex.Handlers.ReceiveHandler;
import com.wvaviator.Pokedex.Handlers.TradeHandler;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = "pokedex", name = "Pokedex", version = "0.0.1", acceptableRemoteVersions = "*")
public class Pokedex {
	
	Configuration config;
	public static File configurationDirectory;
	public static File databaseDirectory;
	public static Logger logger;

	
	@EventHandler
	public void onPreInitialization(FMLPreInitializationEvent e) {
		
		this.config = new Configuration(e.getSuggestedConfigurationFile());
		this.config.load();
		PokedexConfiguration.loadConfig(this.config);
		
		logger = e.getModLog();
		
		//if (!(Loader.isModLoaded("Pixelmon"))) {
		//	logger.error("Could not find Pixelmon! Pokedex cannot run without Pixelmon!");
		//	  FMLCommonHandler.instance().handleExit(1);
		//}
		
		databaseDirectory = new File(Pixelmon.modDirectory + "/database");
		logger.info("Loading database and driver from " + databaseDirectory.getPath());
		
	}
	
	@EventHandler
	public void onInitialization(FMLInitializationEvent e) {
		
		Pixelmon.EVENT_BUS.register(new CaptureHandler());
		Pixelmon.EVENT_BUS.register(new ReceiveHandler());
		Pixelmon.EVENT_BUS.register(new TradeHandler());
		Pixelmon.EVENT_BUS.register(new EvolveHandler());
		Pixelmon.EVENT_BUS.register(new DeleteHandler());
		
		MinecraftForge.EVENT_BUS.register(new LoginHandler());
		
		Database.createDatabase();	
		Database.initializeTables();
		
	}
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent e) {
		
		e.registerServerCommand(new PokedexCommand());
		
	}
	
	@EventHandler
	public void onServerStop(FMLServerStoppingEvent e) {
		
		Database.closeConnection();
		
	}
	
	
	
}
