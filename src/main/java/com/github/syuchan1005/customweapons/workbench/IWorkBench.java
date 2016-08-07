package com.github.syuchan1005.customweapons.workbench;

import com.github.syuchan1005.customweapons.WorkBenchHolder;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by syuchan on 2016/08/06.
 */
public interface IWorkBench {
	String getWorkBenchName();

	Inventory getInventory(WorkBenchHolder holder);

	boolean onClick(InventoryClickEvent event, Location workBenchLocation);

	void onClose(InventoryCloseEvent event, Location workBenchLocation);
}
