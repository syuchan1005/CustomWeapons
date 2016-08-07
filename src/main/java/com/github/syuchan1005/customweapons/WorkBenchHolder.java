package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.workbench.IWorkBench;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by syuchan on 2016/08/02.
 */
public class WorkBenchHolder implements InventoryHolder {
	private IWorkBench iWorkBench;
	private int tier;

	public WorkBenchHolder(IWorkBench iWorkBench, int tier) {
		this.iWorkBench = iWorkBench;
		this.tier = tier;
	}

	@Override
	public Inventory getInventory() {
		return iWorkBench.getInventory(this);
	}

	public IWorkBench getIWorkBench() {
		return iWorkBench;
	}

	public int getTier() {
		return tier;
	}

}