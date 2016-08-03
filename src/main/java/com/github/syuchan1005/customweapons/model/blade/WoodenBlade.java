package com.github.syuchan1005.customweapons.model.blade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by syuchan on 2016/07/31.
 */
public class WoodenBlade extends BladeModel {
	@Override
	public String getDisplayName() {
		return "Wooden";
	}

	@Override
	public ItemStack getItemStack() {
		return new ItemStack(Material.LOG);
	}

	@Override
	public double getAttackDamage() {
		return 1.5;
	}

	@Override
	public double getDurability() {
		return 120;
	}

	@Override
	public Display getDisplayModel() {
		return Display.WOODEN;
	}

}
