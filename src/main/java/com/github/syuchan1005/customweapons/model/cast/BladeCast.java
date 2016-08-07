package com.github.syuchan1005.customweapons.model.cast;

import com.github.syuchan1005.customweapons.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syuchan on 2016/08/03.
 */
public class BladeCast extends CastModel {
	private static List<Model> materialModel;

	public BladeCast() {
		this.materialModel = new ArrayList<>();
	}

	@Override
	public String getDisplayName() {
		return "BladeCast";
	}

	@Override
	public short getCastColor() {
		return 1;
	}

	@Override
	public ModelType getCastModelType() {
		return ModelType.BLADE;
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
