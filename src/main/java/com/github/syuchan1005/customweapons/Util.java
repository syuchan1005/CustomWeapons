package com.github.syuchan1005.customweapons;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by syuchan on 2016/08/05.
 */
public class Util {

	public static boolean arrayContains(Object object, Object... objects) {
		return Arrays.asList(objects).contains(object);
	}

	public static void setItemStackLore(ItemStack itemStack, List<String> lore, boolean override) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (override) itemMeta.setLore(lore);
		else {
			List<String> lore1 = itemMeta.getLore();
			if (lore1 == null) lore1 = new ArrayList<>();
			for (String s : lore) {
				lore1.add(s);
			}
			itemMeta.setLore(lore1);
		}
		itemStack.setItemMeta(itemMeta);
	}

	public static ItemStack setItemStackName(ItemStack itemStack, String name) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static boolean equalsItemStack(ItemStack i1, ItemStack i2) {
		return i1 == i2 ||
				i1.getType() == i2.getType() &&
						i1.getAmount() <= i2.getAmount() &&
						i1.getDurability() == i2.getDurability() &&
						i1.hasItemMeta() == i2.hasItemMeta() &&
						(!i1.hasItemMeta() || Bukkit.getItemFactory().equals(i1.getItemMeta(), i2.getItemMeta()));
	}
}
