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
	public static void addCast(CastModel model) {
		castModelList.add(model);
	}

	public static List<CastModel> getCastModelList() {
		return castModelList;
	}
}