package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.model.Model;
import com.github.syuchan1005.customweapons.model.cast.CastModel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syuchan on 2016/08/01.
 */
public class ModelManager {
	private static List<CastModel> castModelList = new ArrayList<>();

	public static void addCast(CastModel model) {
		castModelList.add(model);
	}

	public static List<CastModel> getCastModelList() {
		return castModelList;
	}

	public static Model getModel(ItemStack itemStack) {
		if (itemStack == null || itemStack.getType() == Material.AIR) return null;
		ItemMeta itemMeta = itemStack.getItemMeta();
		List<String> lore = itemMeta.getLore();
		if (lore == null) return null;
		String s = lore.get(lore.size() - 1);
		if (!s.startsWith("Type: ")) return null;
		lore.remove(lore.size() - 1);
		itemStack = itemStack.clone();
		ItemMeta itemMeta1 = itemStack.getItemMeta();
		itemMeta1.setLore(lore);
		itemStack.setItemMeta(itemMeta1);
		CastModel castModel = null;
		for (CastModel model : ModelManager.getCastModelList()) {
			if (model.getCastModelType().name().equals(s.substring(6))) castModel = model;
		}
		if (castModel == null) return null;
		Model model = null;
		for (Model model1 : castModel.getMaterialModelList()) {
			if (Util.equalsItemStack(model1.getResultItemStack(), itemStack)) model = model1;
		}
		return model;
	}

	public static void setModelData(ItemStack itemStack, Model model) {
		if (model == null) return;
		List<String> lore2 = new ArrayList<>();
		lore2.add("AttackDamage: " + model.getAttackDamage());
		lore2.add("Durability: " + model.getDurability());
		lore2.add("Modifier: " + model.getModifier());
		Util.setItemStackName(itemStack, ChatColor.DARK_PURPLE + "Type: " + model.getModelType().name());
		Util.setItemStackLore(itemStack, lore2, true);
	}
}