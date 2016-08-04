package com.github.syuchan1005.customweapons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syuchan on 2016/08/02.
 */
public class WorkBenchHolder implements InventoryHolder {
	private static Map<WorkBenchType, Inventory> inventories = new HashMap<>();
	private WorkBenchType workBenchType;
	private int tier;

	public WorkBenchHolder(WorkBenchType type, int tier) {
		this.workBenchType = type;
		this.tier = tier;
	}

	@Override
	public Inventory getInventory() {
		return getWorkbenchType().getInventory(this);
	}

	public WorkBenchType getWorkbenchType() {
		return workBenchType;
	}

	public int getTier() {
		return tier;
	}

}