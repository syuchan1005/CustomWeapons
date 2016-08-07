package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.workbench.IWorkBench;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by syuchan on 2016/08/06.
 */
public class WorkBenchManager {
	private static List<IWorkBench> workBenchList = new ArrayList<>();

	public static void addWorkBench(IWorkBench workBench) {
		workBenchList.add(workBench);
	}

	public static List<IWorkBench> getWorkBenchList() {
		return workBenchList;
	}

	public static IWorkBench getWorkBench(String workBenchName) {
		for (IWorkBench iWorkBench : workBenchList) {
			if (iWorkBench.getWorkBenchName().equals(workBenchName)) return iWorkBench;
		}
		return null;
	}

	public static ItemStack getWorkBenchStack(IWorkBench iWorkBench, int tier) {
		ItemStack itemStack = new ItemStack(Material.FURNACE);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 0, true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemMeta.setDisplayName(iWorkBench.getWorkBenchName());
		itemMeta.setLore(Arrays.asList(
				"Custom Weapons",
				"Type: " + iWorkBench.getWorkBenchName(),
				"Tier: " + tier));
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static boolean isWorkBench(ItemStack itemStack) {
		List<String> lore = itemStack.getItemMeta().getLore();
		return itemStack.getType() == Material.FURNACE &&
				itemStack.hasItemMeta() &&
				lore != null &&
				lore.size() >= 3 &&
				lore.get(0).equals("Custom Weapons") &&
				lore.get(1).startsWith("Type: ") &&
				lore.get(2).startsWith("Tier: ");

	}
}
