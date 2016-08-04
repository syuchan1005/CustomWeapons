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
public class RodCast extends CastModel {
	private List<Model> materialModel;

	public RodCast() {
		this.materialModel = new ArrayList<>();
	}

	@Override
	public String getDisplayName() {
		return "RodCast";
	}

	@Override
	public short getCastColor() {
		return 3;
	}

	@Override
	public ModelType getCastModelType() {
		return ModelType.ROD;
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

