package com.github.syuchan1005.customweapons.model.rod;

import com.github.syuchan1005.customweapons.model.Model;
import org.bukkit.inventory.ItemStack;

/**
 * Created by syuchan on 2016/07/31.
 */
public abstract class RodModel implements Model {
	public abstract String getDisplayName();

	public abstract ItemStack getItemStack();

	public double getAttackDamage() { return 0D; };

	public abstract double getDurability();

	public abstract double getModifier();

	public ModelType getModelType() { return ModelType.ROD; };
}
