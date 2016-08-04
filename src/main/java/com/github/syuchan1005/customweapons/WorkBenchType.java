package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.model.cast.CastModel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

/**
 * Created by syuchan on 2016/08/02.
 */
public enum WorkBenchType {
	TOOLFORGE {
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
					contents[3].setType(Material.AIR); contents[11].setType(Material.AIR); contents[19].setType(Material.AIR);
					contents[4].setDurability((short) 5); contents[12].setDurability((short) 5); contents[20].setDurability((short) 5);
					Inventory inventory = Bukkit.createInventory(holder, 27, this.name());
					inventory.setContents(contents);
					return inventory;
				default:
					return Bukkit.createInventory(holder, 27, this.name());
			}
		}

		@Override
		public int[] getMaterialNum() {
			return new int[] { 3, 11, 19 };
		}
	},
	PARTBUILDER {
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
					Inventory inventory = Bukkit.createInventory(new WorkBenchHolder(this, 1), 27, this.name());
					inventory.setContents(contents);
					return inventory;
				default:
					return Bukkit.createInventory(holder, 27, this.name());
			}
		}

		@Override
		public int[] getMaterialNum() {
			return new int[] { 15 };
		}
	};

	public Inventory getInventory(WorkBenchHolder holder) {
		return Bukkit.createInventory(holder, 27);
	}

	public int[] getMaterialNum() { return new int[] { -1 }; };
}
