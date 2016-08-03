package com.github.syuchan1005.customweapons;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by syuchan on 2016/08/02.
 */
public class WorkBenchItemStacks {
	private static List<String> lore = Arrays.asList("Custom Weapons", "Type: ", "Tier: ");
	private static ItemStack toolForge;

	public static ItemStack getWorkBench(WorkBenchType type, int tier) {
		if (toolForge == null) {
			toolForge = new ItemStack(Material.FURNACE);
			ItemMeta itemMeta = toolForge.getItemMeta();
			itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			toolForge.setItemMeta(itemMeta);
		}
		ItemMeta itemMeta = toolForge.getItemMeta();
		itemMeta.setDisplayName(type.name());
		itemMeta.setLore(Arrays.asList("Custom Weapons", "Type: " + type.name(), "Tier: " + tier));
		toolForge.setItemMeta(itemMeta);
		return toolForge;
	}

	public static WorkBenchType isWorkBench(ItemStack itemStack) {
		if (itemStack.getType() != Material.FURNACE) return null;
		if (!itemStack.getItemMeta().hasLore()) return null;
		List<String> lore = itemStack.getItemMeta().getLore();
		if (lore.isEmpty() || !lore.get(0).equals("Custom Weapons")) return null;
		return WorkBenchType.valueOf(lore.get(1).substring(6));
	}
}
