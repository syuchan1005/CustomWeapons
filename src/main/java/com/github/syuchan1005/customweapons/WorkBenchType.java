package com.github.syuchan1005.customweapons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by syuchan on 2016/08/02.
 */
public enum WorkBenchType {
	TOOLFORGE {
		@Override
		public Inventory getInventory() {
			ItemStack[] contents = new ItemStack[27];
			for (int i = 0; i < 27; i++) {
				contents[i] = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
				ItemMeta itemMeta = contents[i].getItemMeta();
				itemMeta.setDisplayName(" ");
				contents[i].setItemMeta(itemMeta);
			}
			contents[3].setType(Material.AIR); contents[11].setType(Material.AIR); contents[19].setType(Material.AIR);
			contents[4].setDurability((short) 5); contents[12].setDurability((short) 5); contents[20].setDurability((short) 5);
			Inventory inventory = Bukkit.createInventory(new WorkBenchHolder(TOOLFORGE, 1), 27, this.name());
			inventory.setContents(contents);
			return inventory;
		}
	},
	PARTBUILDER;

	public Inventory getInventory() {
		return Bukkit.createInventory(null, 27);
	}
}
