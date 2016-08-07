package com.github.syuchan1005.customweapons;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by syuchan on 2016/08/02.
 */
public class InventoryListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inventory = event.getClickedInventory();
		if (inventory == null) return;
		if (!(inventory.getHolder() instanceof WorkBenchHolder)) return;
		WorkBenchHolder holder = ((WorkBenchHolder) inventory.getHolder());
		Player player = (Player) event.getWhoClicked();
		try {
			event.setCancelled(holder.getIWorkBench().onClick(event, (Location) player.getMetadata("WBLocation").get(0).value()));
		} catch (Exception e) {
			e.printStackTrace();
			event.setCancelled(true);
			player.updateInventory();
		}
	}

	@EventHandler
	public void onDrag(InventoryDragEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		if (!(inventory.getHolder() instanceof WorkBenchHolder)) return;
		WorkBenchHolder holder = ((WorkBenchHolder) inventory.getHolder());
		Location location = ((Location) event.getPlayer().getMetadata("WBLocation").get(0).value());
		event.getPlayer().removeMetadata("WBLocation", CustomWeapons.getInstance());
		holder.getIWorkBench().onClose(event, location);
	}

}
