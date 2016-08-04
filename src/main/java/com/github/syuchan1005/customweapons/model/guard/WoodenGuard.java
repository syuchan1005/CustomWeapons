package com.github.syuchan1005.customweapons.model.guard;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by syuchan on 2016/07/31.
 */
public class WoodenGuard extends GuardModel {
	@Override
	public String getDisplayName() {
		return "Wooden";
	}

	@Override
	public ItemStack getMaterialItemStack() {
		return new ItemStack(Material.WOOD);
	}

	@Override
	public ItemStack getResultItemStack() {
		ItemStack itemStack = new ItemStack(Material.WOOD_STEP);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Wooden Guard");
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}


	@Override
	public double getDurability() {
		return 15;
	}
}
