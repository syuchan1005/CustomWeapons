package com.github.syuchan1005.customweapons.model.cast;

import com.github.syuchan1005.customweapons.ModelManager;
import com.github.syuchan1005.customweapons.model.Model;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by syuchan on 2016/08/03.
 */
public class GuardCast extends CastModel {
	private List<Model> materialModel;

	public GuardCast() {
		this.materialModel = new ArrayList<>();
	}

	@Override
	public String getDisplayName() {
		return "GuardCast";
	}

	@Override
	public short getCastColor() {
		return 2;
	}

	@Override
	public ModelType getCastModelType() {
		return ModelType.GUARD;
	}

	@Override
	public void addMaterialModel(Model model) {
		materialModel.add(model);
	}

	@Override
	public List<Model> getMaterialModelList() {
		return materialModel;
	}
}
