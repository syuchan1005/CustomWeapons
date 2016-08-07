package com.github.syuchan1005.customweapons.model.cast;

import com.github.syuchan1005.customweapons.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syuchan on 2016/08/03.
 */
public class RodCast extends CastModel {
	private static List<Model> materialModel;

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

