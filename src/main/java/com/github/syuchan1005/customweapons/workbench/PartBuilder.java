package com.github.syuchan1005.customweapons.workbench;

import com.github.syuchan1005.customweapons.ModelManager;
import com.github.syuchan1005.customweapons.Util;
import com.github.syuchan1005.customweapons.WorkBenchHolder;
import com.github.syuchan1005.customweapons.model.Model;
import com.github.syuchan1005.customweapons.model.cast.CastModel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

/**
 * Created by syuchan on 2016/08/06.
 */
public class PartBuilder implements IWorkBench {
	@Override
	public String getWorkBenchName() {
		return "PartBuilder";
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
				contents[15].setType(Material.AIR);
				List<CastModel> castModelList = ModelManager.getCastModelList();
				for (int i = 0; i < castModelList.size(); i++) {
					contents[((i / 5 * 9) + (i % 5))] = new ItemStack(Material.STAINED_GLASS_PANE, 1, castModelList.get(i).getCastColor());
					ItemMeta itemMeta = contents[((i / 5 * 9) + (i % 5))].getItemMeta();
					itemMeta.setDisplayName(castModelList.get(i).getDisplayName());
					contents[((i / 5 * 9) + (i % 5))].setItemMeta(itemMeta);
				}
				Inventory inventory = Bukkit.createInventory(new WorkBenchHolder(this, 1), 27, this.getWorkBenchName());
				inventory.setContents(contents);
				return inventory;
			default:
				return Bukkit.createInventory(holder, 27, this.getWorkBenchName());
		}
	}

	@Override
	public boolean onClick(InventoryClickEvent event, Location workBenchLocation) {
		Inventory inventory = event.getClickedInventory();
		int slot = event.getSlot();

		if (slot == 16) {
			ItemStack item = inventory.getItem(slot);
			if (item == null || item.getType() == Material.STAINED_GLASS_PANE) return true;
			Model model = ModelManager.getModel(inventory.getItem(16));
			ItemStack oldItem = inventory.getItem(15);
			int amount = oldItem.getAmount() - model.getMaterialItemStack().getAmount();
			if (amount == 0) inventory.setItem(15, null);
			else if (amount < 0) return true;
			else oldItem.setAmount(amount);
			return false;
		} else if (slot == 15) {
			if (inventory.getItem(16) == null || inventory.getItem(16).getType() != Material.STAINED_GLASS_PANE) {
				inventory.setItem(16, Util.setItemStackName(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7), " "));
			}
			return false;
		} else {
			if (inventory.getItem(15) == null) return true;
			slot = (slot / 8) * 5 + (slot % 9);
			List<CastModel> castModelList = ModelManager.getCastModelList();
			if ((castModelList.size() - 1) < slot) return true;
			ItemStack itemStack = null;
			CastModel castModel = castModelList.get(slot);
			for (Model model : castModel.getMaterialModelList()) {
				if (Util.equalsItemStack(model.getMaterialItemStack(), inventory.getItem(15))) {
					itemStack = model.getResultItemStack();
					break;
				}
			}
			if (itemStack == null) {
				itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			} else {
				Util.setItemStackLore(itemStack, Collections.singletonList("Type: " + castModel.getCastModelType().name()), false);
			}
			inventory.setItem(16, itemStack);
		}
		return true;
	}

	@Override
	public void onClose(InventoryCloseEvent event, Location workBenchLocation) {
		Inventory inventory = event.getInventory();
		workBenchLocation.setY(workBenchLocation.getY() + 1);
		ItemStack itemStack = inventory.getContents()[15];
		if (itemStack != null && itemStack.getType() != Material.STAINED_GLASS_PANE) {
			workBenchLocation.getWorld().dropItem(workBenchLocation, itemStack);
		}
	}
}
