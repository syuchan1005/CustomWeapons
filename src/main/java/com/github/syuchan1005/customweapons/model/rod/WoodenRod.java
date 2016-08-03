package com.github.syuchan1005.customweapons.model.rod;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by syuchan on 2016/07/31.
 */
public class WoodenRod extends RodModel {
	@Override
	public String getDisplayName() {
		return "Wooden";
	}

	@Override
	public ItemStack getItemStack() {
		return new ItemStack(Material.LOG);
	}

	@Override
	public double getDurability() {
		return 25;
	}

	@Override
	public double getModifier() {
		return 1D;
	}
}
