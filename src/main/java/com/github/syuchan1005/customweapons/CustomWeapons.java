package com.github.syuchan1005.customweapons;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomWeapons extends JavaPlugin {
	private static Logger logger;
	private static CustomWeapons instance;

	@Override
	public void onEnable() {
		instance = this;
		logger = this.getLogger();
		PluginManager pluginManager = this.getServer().getPluginManager();
		logger.info("CustomWeapons Plugin Start!");
		pluginManager.registerEvents(new WorkBenchListener(), this);
		pluginManager.registerEvents(new InventoryListener(), this);
		this.getCommand("customweapons").setExecutor(new CWCommand());
	}

	public static CustomWeapons getInstance() {
		return instance;
	}
}
