package com.github.syuchan1005.customweapons;

import org.bukkit.block.Furnace;

import java.lang.reflect.Method;

/**
 * Created by syuchan on 2016/08/02.
 */
public class FurnaceUtil {
	private static Method tileEntityFurnaceMethod;
	private static Method getNameMethod, setNameMethod;

	public static String getInventoryName(Furnace furnace) throws ReflectiveOperationException {
		Object tileEntityFurnace = getTileEntityFurnace(furnace);
		if(getNameMethod == null) getNameMethod = tileEntityFurnace.getClass().getMethod("getName");
		return (String) getNameMethod.invoke(tileEntityFurnace);
	}

	public static void setInventoryName(Furnace furnace, String title) throws ReflectiveOperationException {
		Object tileEntityFurnace = getTileEntityFurnace(furnace);
		if(setNameMethod == null) setNameMethod = tileEntityFurnace.getClass().getMethod("a", String.class);
		setNameMethod.invoke(tileEntityFurnace, title);
	}

	private static Object getTileEntityFurnace(Furnace furnace) throws ReflectiveOperationException {
		if(tileEntityFurnaceMethod == null) {
			tileEntityFurnaceMethod = furnace.getClass().getMethod("getTileEntity");
		}
		return tileEntityFurnaceMethod.invoke(furnace);
	}
}
