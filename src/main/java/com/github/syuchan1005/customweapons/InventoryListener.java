package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.model.Model;
import com.github.syuchan1005.customweapons.model.cast.CastModel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syuchan on 2016/08/02.
 */
public class InventoryListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inventory = event.getClickedInventory();
		if (inventory == null) return;
		if (!(inventory.getHolder() instanceof WorkBenchHolder)) return;
		WorkBenchHolder holder = (WorkBenchHolder) inventory.getHolder();
		int slot = event.getSlot();
		switch (holder.getWorkbenchType()) {
			case TOOLFORGE:
				if(Util.arrayContains(slot, WorkBenchType.TOOLFORGE.getMaterialNum())) break;
				slot += 1;
				ItemStack itemStack1 = event.getCursor();
				if(itemStack1 == null) break;
				ItemMeta itemMeta3 = itemStack1.getItemMeta();
				if(itemMeta3 == null) {
					ItemStack item = inventory.getItem(slot);
					ItemMeta itemMeta = item.getItemMeta();
					itemMeta.setLore(null);
					item.setItemMeta(itemMeta);
					inventory.setItem(slot, item);
					break;
				}
				List<String> lore1 = itemMeta3.getLore();
				if(lore1 == null) break;
				String s = lore1.get(lore1.size() - 1);
				if(!s.startsWith("Type: ")) return;
				lore1.remove(lore1.size() - 1);
				itemStack1 = itemStack1.clone();
				ItemMeta itemMeta1 = itemStack1.getItemMeta();
				itemMeta1.setLore(lore1);
				itemStack1.setItemMeta(itemMeta1);
				CastModel castModel1 = null;
				for (CastModel model : ModelManager.getCastModelList()) {
					if(model.getCastModelType().name().equals(s.substring(6))) castModel1 = model;
				}
				if(castModel1 == null) break;
				Model model2 = null;
				for (Model model1 : castModel1.getMaterialModelList()) {
					if(model1.getResultItemStack().equals(itemStack1)) model2 = model1;
				}
				if(model2 == null) break;
				ItemStack infoItem = inventory.getItem(slot);
				ItemMeta itemMeta2 = infoItem.getItemMeta();
				List<String> lore2 = new ArrayList<>();
				lore2.add("AttackDamage: " + model2.getAttackDamage());
				lore2.add("Durability: " + model2.getDurability());
				lore2.add("Modifier: " + model2.getModifier());
				itemMeta2.setLore(lore2);
				infoItem.setItemMeta(itemMeta2);
				break;
			case PARTBUILDER:
				if (slot == 16) {
					if (inventory.getItem(16).getType() == Material.STAINED_GLASS_PANE) break;
					inventory.setItem(15, new ItemStack(Material.AIR));
				} else if (slot == 15) {
					inventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
				} else {
					if (inventory.getItem(15) == null) break;
					slot = (slot / 8) * 5 + (slot % 9);
					List<CastModel> castModelList = ModelManager.getCastModelList();
					if (castModelList.size() < slot) break;
					ItemStack itemStack = null;
					CastModel castModel = castModelList.get(slot);
					for (Model model : castModel.getMaterialModelList()) {
						if (model.getMaterialItemStack().equals(inventory.getItem(15))) {
							itemStack = model.getResultItemStack();
							break;
						}
					}
					if (itemStack == null) {
						inventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
					} else {
						ItemMeta itemMeta = itemStack.getItemMeta();
						List<String> lore = itemMeta.getLore();
						if(lore == null) lore = new ArrayList<>();
						lore.add("Type: " + castModel.getCastModelType().name());
						itemMeta.setLore(lore);
						itemStack.setItemMeta(itemMeta);
						inventory.setItem(16, itemStack);
					}
				}
				break;
		}
		if (event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) return;
		event.setCancelled(true);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		Inventory inventory = event.getInventory();
		if (!(inventory.getHolder() instanceof WorkBenchHolder)) return;
		Location location = ((Location) event.getPlayer().getMetadata("WBLocation").get(0).value());
		event.getPlayer().removeMetadata("WBLocation", CustomWeapons.getInstance());
		location.setY(location.getY() + 1);
		for (int i : ((WorkBenchHolder) inventory.getHolder()).getWorkbenchType().getMaterialNum()) {
			if (inventory.getContents()[i] != null) {
				location.getWorld().dropItem(location, inventory.getContents()[i]);
			}
		}
	}
}
