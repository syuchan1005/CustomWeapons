package com.github.syuchan1005.customweapons;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.Arrays;
import java.util.List;

/**
 * Created by syuchan on 2016/08/02.
 */
public class InventoryListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inventory = event.getClickedInventory();
		Inventory inventory1 = event.getInventory();
		if(inventory == null) return;
		if(!(inventory.getHolder() instanceof WorkBenchHolder)) return;
		if(event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) return;
		event.setCancelled(true);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		if(!(inventory.getHolder() instanceof WorkBenchHolder)) return;
		Location location = ((Location) event.getPlayer().getMetadata("WBLocation").get(0).value());
		event.getPlayer().removeMetadata("WBLocation", CustomWeapons.getInstance());
		location.setY(location.getY() + 1);
		if(inventory.getContents()[3] != null) location.getWorld().dropItemNaturally(location, inventory.getContents()[3]);
		if(inventory.getContents()[11] != null) location.getWorld().dropItemNaturally(location, inventory.getContents()[11]);
		if(inventory.getContents()[19] != null) location.getWorld().dropItemNaturally(location, inventory.getContents()[19]);
	}
}
