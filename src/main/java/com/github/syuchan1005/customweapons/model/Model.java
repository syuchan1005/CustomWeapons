package com.github.syuchan1005.customweapons.model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by syuchan on 2016/07/31.
 */
public interface Model {
	String getDisplayName();

	ItemStack getItemStack();

	double getAttackDamage();

	double getDurability();

	double getModifier();

	ModelType getModelType();

	enum  Display {
		WOODEN,
		IRON,
		GOLD,
		DIAMOND;

		public Material getMaterial() {
			switch (this) {
				case WOODEN:
					return Material.WOOD_SWORD;
				case IRON:
					return Material.IRON_SWORD;
				case GOLD:
					return Material.GOLD_SWORD;
				case DIAMOND:
					return Material.DIAMOND_SWORD;
			}
			return null;
		}
	}

	enum ModelType {
		BLADE,
		GUARD,
		ROD,
		CAST,
	}
}
