package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.model.blade.WoodenBlade;
import com.github.syuchan1005.customweapons.model.cast.BladeCast;
import com.github.syuchan1005.customweapons.model.cast.GuardCast;
import com.github.syuchan1005.customweapons.model.cast.RodCast;
import com.github.syuchan1005.customweapons.model.guard.WoodenGuard;
import com.github.syuchan1005.customweapons.model.rod.WoodenRod;
import com.github.syuchan1005.customweapons.workbench.PartBuilder;
import com.github.syuchan1005.customweapons.workbench.ToolForge;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CustomWeapons extends JavaPlugin {
	private static CustomWeapons instance;

	@Override
	public void onEnable() {
		instance = this;
		Logger logger = this.getLogger();
		PluginManager pluginManager = this.getServer().getPluginManager();
		logger.info("Plugin start!");
		this.setWorkBenches();
		this.setModels();
		pluginManager.registerEvents(new WorkBenchListener(), this);
		pluginManager.registerEvents(new InventoryListener(), this);
		this.getCommand("customweapons").setExecutor(new CWCommand());
		logger.info("Plugin initialize complete!");
	}

	private void setWorkBenches() {
		WorkBenchManager.addWorkBench(new ToolForge());
		WorkBenchManager.addWorkBench(new PartBuilder());
	}

	private void setModels() {
		BladeCast bladeCast = new BladeCast();
		bladeCast.addMaterialModel(new WoodenBlade());
		GuardCast guardCast = new GuardCast();
		guardCast.addMaterialModel(new WoodenGuard());
		RodCast rodCast = new RodCast();
		rodCast.addMaterialModel(new WoodenRod());
		ModelManager.addCast(bladeCast);
		ModelManager.addCast(guardCast);
		ModelManager.addCast(rodCast);
	}

	public static CustomWeapons getInstance() {
		return instance;
	}
}
