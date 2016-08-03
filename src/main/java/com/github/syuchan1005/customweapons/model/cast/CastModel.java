package com.github.syuchan1005.customweapons.model.cast;

import com.github.syuchan1005.customweapons.model.Model;
import org.bukkit.inventory.ItemStack;

/**
 * Created by syuchan on 2016/08/03.
 */
public abstract class CastModel implements Model {
	public abstract String getDisplayName();

	public abstract ItemStack getItemStack();

	public double getAttackDamage() { return 0D; };

	public double getDurability() { return 0D; }

	public double getModifier() { return 0D; };

	public ModelType getModelType() {
		return ModelType.CAST;
	}

	public abstract ItemStack getResultItemStack();
}
