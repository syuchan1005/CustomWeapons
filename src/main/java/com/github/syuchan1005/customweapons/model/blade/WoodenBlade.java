package com.github.syuchan1005.customweapons.model.blade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by syuchan on 2016/07/31.
 */
public class WoodenBlade extends BladeModel {
	@Override
	public String getDisplayName() {
		return "Wooden";
	}

	@Override
	public ItemStack getMaterialItemStack() {
		return new ItemStack(Material.LOG);
	}

	@Override
	public ItemStack getResultItemStack() {
		ItemStack itemStack = new ItemStack(Material.ARROW);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Wooden Blade");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
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
