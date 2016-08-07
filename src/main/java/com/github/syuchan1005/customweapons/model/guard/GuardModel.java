package com.github.syuchan1005.customweapons.model.guard;

import com.github.syuchan1005.customweapons.model.Model;
import org.bukkit.inventory.ItemStack;

/**
 * Created by syuchan on 2016/07/31.
 */
public abstract class GuardModel implements Model {
	public abstract String getDisplayName();

	public abstract ItemStack getMaterialItemStack();

	public abstract ItemStack getResultItemStack();

	public double getAttackDamage() { return 0D; }

	public abstract double getDurability() ;

	public double getModifier() { return 0D; }

	public ModelType getModelType() { return ModelType.GUARD; }
}
