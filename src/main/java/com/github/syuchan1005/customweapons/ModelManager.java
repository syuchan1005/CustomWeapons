package com.github.syuchan1005.customweapons;

import com.github.syuchan1005.customweapons.model.blade.BladeModel;
import com.github.syuchan1005.customweapons.model.cast.CastModel;
import com.github.syuchan1005.customweapons.model.guard.GuardModel;
import com.github.syuchan1005.customweapons.model.rod.RodModel;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syuchan on 2016/08/01.
 */
public class ModelManager {
	private static List<CastModel> castModelList = new ArrayList<>();
	private static List<BladeModel> bladeModelList = new ArrayList<>();
	private static List<GuardModel> guardModelList = new ArrayList<>();
	private static List<RodModel> rodModelList = new ArrayList<>();

	public static void addCast(CastModel model) {
		castModelList.add(model);
	}

	public static CastModel containsCastModel(ItemStack itemStack) {
		for (CastModel castModel : castModelList) {
			if(castModel.getItemStack() == itemStack) return castModel;
		}
		return null;
	}

	public static void addBlade(BladeModel model) {
		bladeModelList.add(model);
	}

	public static BladeModel containsBladeModel(ItemStack itemStack) {
		for (BladeModel bladeModel : bladeModelList) {
			if(bladeModel.getItemStack() == itemStack) return bladeModel;
		}
		return null;
	}

	public static void addGuard(GuardModel model) {
		guardModelList.add(model);
	}

	public static GuardModel containsGuardModel(ItemStack itemStack) {
		for (GuardModel guardModel : guardModelList) {
			if(guardModel.getItemStack() == itemStack) return guardModel;
		}
		return null;
	}

	public static void addRod(RodModel model) {
		rodModelList.add(model);
	}

	public static RodModel containsRodModel(ItemStack itemStack) {
		for (RodModel rodModel : rodModelList) {
			if(rodModel.getItemStack() == itemStack) return rodModel;
		}
		return null;
	}

	public static ItemStack createSwordWeapon(ItemStack bladeItem, ItemStack guardItem, ItemStack rodItem) {
		BladeModel bladeModel = containsBladeModel(bladeItem);
		if(bladeModel == null) return null;
		GuardModel guardModel = containsGuardModel(guardItem);
		if(guardModel == null) return null;
		RodModel rodModel = containsRodModel(rodItem);
		if(rodModel == null) return null;
		return createSwordWeapon(bladeModel, guardModel, rodModel);
	}

	protected static ItemStack createSwordWeapon(BladeModel bladeModel, GuardModel guardModel, RodModel rodModel) {
		return new ItemStack(bladeModel.getDisplayModel().getMaterial());
	}
}