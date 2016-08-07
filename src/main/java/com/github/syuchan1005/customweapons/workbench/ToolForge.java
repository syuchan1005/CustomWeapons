package com.github.syuchan1005.customweapons.workbench;

import com.github.syuchan1005.customweapons.ModelManager;
import com.github.syuchan1005.customweapons.Util;
import com.github.syuchan1005.customweapons.WorkBenchHolder;
import com.github.syuchan1005.customweapons.model.Model;
import com.github.syuchan1005.customweapons.model.blade.BladeModel;
import com.github.syuchan1005.customweapons.model.guard.GuardModel;
import com.github.syuchan1005.customweapons.model.rod.RodModel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by syuchan on 2016/08/06.
 */
public class ToolForge implements IWorkBench {
	@Override
	public String getWorkBenchName() {
		return "ToolForge";
	}

	@Override
	public Inventory getInventory(WorkBenchHolder holder) {
		switch (holder.getTier()) {
			case 1:
				ItemStack[] contents = new ItemStack[27];
				for (int i = 0; i < 27; i++) {
					contents[i] = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
					ItemMeta itemMeta = contents[i].getItemMeta();
					itemMeta.setDisplayName(" ");
					contents[i].setItemMeta(itemMeta);
				}
				contents[3].setType(Material.AIR);
				contents[11].setType(Material.AIR);
				contents[19].setType(Material.AIR);
				contents[4].setDurability((short) 5);
				contents[12].setDurability((short) 5);
				contents[20].setDurability((short) 5);
				contents[14].setDurability((short) 14);
				Util.setItemStackName(contents[14], "Build");
				Inventory inventory = Bukkit.createInventory(holder, 27, getWorkBenchName());
				inventory.setContents(contents);
				return inventory;
			default:
				return Bukkit.createInventory(holder, 27, getWorkBenchName());
		}
	}

	@Override
	public boolean onClick(InventoryClickEvent event, Location workBenchLocation) {
		Inventory inventory = event.getClickedInventory();
		int slot = event.getSlot();
		if (slot == 16) {
			ItemStack item = inventory.getItem(16);
			return (item != null && item.getType() == Material.STAINED_GLASS_PANE);
		} else if (slot == 14) {
			if (inventory.getItem(3) == null || inventory.getItem(11) == null || inventory.getItem(19) == null)
				return true;
			Model model1 = ModelManager.getModel(inventory.getItem(3));
			Model model2 = ModelManager.getModel(inventory.getItem(11));
			Model model3 = ModelManager.getModel(inventory.getItem(19));
			if (!(model1 instanceof BladeModel) || !(model2 instanceof GuardModel) || !(model3 instanceof RodModel))
				return true;
			Material material = ((BladeModel) model1).getDisplayModel().getMaterial();
			if (material == null) return true;
			ItemStack buildItem = new ItemStack(material);
			Util.setItemStackLore(buildItem, Arrays.asList("Custom Weapons",
					"AttackDamage: " + (model1.getAttackDamage() + 2),
					"Durability: " + (model1.getDurability() + model3.getDurability()),
					"Modifier: " + (model2.getModifier())), true);
			inventory.setItem(16, buildItem);
			inventory.setItem(3, null);
			inventory.setItem(11, null);
			inventory.setItem(19, null);
			return true;
		}
		ItemStack item = inventory.getItem(slot);
		if (!Util.arrayContains(slot, 3, 11, 19) && item != null && item.getType() == Material.STAINED_GLASS_PANE) {
			return true;
		}
		slot += 1;
		if (event.getCursor() == null) {
			Util.setItemStackLore(inventory.getItem(slot), null, true);
		} else {
			ModelManager.setModelData(inventory.getItem(slot), ModelManager.getModel(event.getCursor()));
		}
		return false;
	}

	@Override
	public void onClose(InventoryCloseEvent event, Location workBenchLocation) {
		Inventory inventory = event.getInventory();
		workBenchLocation.setY(workBenchLocation.getY() + 1);
		for (int i : new int[]{3, 11, 19, 16}) {
			ItemStack itemStack = inventory.getContents()[i];
			if (i != 16) {
				if (itemStack != null) workBenchLocation.getWorld().dropItem(workBenchLocation, itemStack);
			} else {
				if (itemStack != null && itemStack.getType() != Material.STAINED_GLASS_PANE) {
					workBenchLocation.getWorld().dropItem(workBenchLocation, itemStack);
				}
			}
		}
	}
}
