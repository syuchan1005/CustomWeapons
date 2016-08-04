package com.github.syuchan1005.customweapons.model.cast;

import com.github.syuchan1005.customweapons.model.Model;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by syuchan on 2016/08/03.
 */
public abstract class CastModel implements Model {
	public abstract String getDisplayName();

	public abstract short getCastColor();

	public abstract ModelType getCastModelType();

	public abstract void addMaterialModel(Model model);

	public abstract List<Model> getMaterialModelList();

	public double getAttackDamage() {
		return 0D;
	}

	public double getDurability() {
		return 0D;
	}

	public double getModifier() {
		return 0D;
	}

	public ItemStack getMaterialItemStack() {
		return new ItemStack(Material.AIR);
	}

	public ItemStack getResultItemStack() {
		return new ItemStack(Material.AIR);
	}

	public ModelType getModelType() {
		return ModelType.CAST;
	}

}
