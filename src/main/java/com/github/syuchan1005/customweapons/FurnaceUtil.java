package com.github.syuchan1005.customweapons;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Furnace;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by syuchan on 2016/08/02.
 */
public class FurnaceUtil {
	private static Method tileEntityFurnaceMethod;
	private static Method getNameMethod;

	private static Constructor nbtTagCompoundConstructor, blockPositionConstructor;
	private static Method aMethod, saveMethod;
	private static Field tileEntityFurnaceField;
	private static Method setStringMethod;
	private static Method updateMethod, notifyMethod;

	private static Method getHandleMethod, getTypeMethod, getBlockMethod;

	public static String getInventoryName(Furnace furnace) throws ReflectiveOperationException {
		Object tileEntityFurnace = getTileEntityFurnace(furnace);
		if(getNameMethod == null) getNameMethod = tileEntityFurnace.getClass().getMethod("getName");
		return (String) getNameMethod.invoke(tileEntityFurnace);
	}

	public static void setInventoryName(Location location, Furnace furnace, String title) throws ReflectiveOperationException {
		Object tileEntityFurnace = getTileEntityFurnace(furnace);
		if (nbtTagCompoundConstructor == null) nbtTagCompoundConstructor = Class.forName(getNMSPackageName() + ".NBTTagCompound").getConstructor();
		if (saveMethod == null) saveMethod = tileEntityFurnace.getClass().getMethod("save", nbtTagCompoundConstructor.getDeclaringClass());
		if (aMethod == null) aMethod = tileEntityFurnace.getClass().getMethod("a", nbtTagCompoundConstructor.getDeclaringClass());

		Object nbtTagCompound = nbtTagCompoundConstructor.newInstance();
		saveMethod.invoke(tileEntityFurnace, nbtTagCompound);
		setCustomName(nbtTagCompound, title);
		aMethod.invoke(tileEntityFurnace, nbtTagCompound);
		setTileEntityFurnace(furnace, tileEntityFurnace);
		reg(location);
	}

	private static void reg(Location location) throws ReflectiveOperationException {
		if (getHandleMethod == null) getHandleMethod = location.getWorld().getClass().getMethod("getHandle");
		if (blockPositionConstructor == null) blockPositionConstructor = Class.forName(getNMSPackageName() + ".BlockPosition").getConstructor(double.class, double.class, double.class);
		Object world = getHandleMethod.invoke(location.getWorld());
		if (getTypeMethod == null) getTypeMethod = world.getClass().getMethod("getType", blockPositionConstructor.getDeclaringClass());
		Object blockPosition = blockPositionConstructor.newInstance(location.getX(), location.getY(), location.getZ());
		Object iBlockData = getTypeMethod.invoke(world, blockPosition);
		if (getBlockMethod == null) {
			getBlockMethod = iBlockData.getClass().getMethod("getBlock");
			getBlockMethod.setAccessible(true);
		}
		Object block = getBlockMethod.invoke(iBlockData);
		worldUpdate(world, blockPosition, block);
		worldNotify(world, blockPosition, iBlockData);
	}

	private static void worldUpdate(Object world, Object blockPosition, Object block) throws ReflectiveOperationException {
		if (updateMethod == null) {
			updateMethod = world.getClass().getMethod("update", blockPosition.getClass(), Class.forName(getNMSPackageName() + ".Block"));
		}
		updateMethod.invoke(world, blockPosition, block);
	}

	private static void worldNotify(Object world, Object blockPosition, Object iBlockData) throws ReflectiveOperationException {
		if (notifyMethod == null) {
			Class<?> iBlockDataClass = Class.forName(getNMSPackageName() + ".IBlockData");
			notifyMethod = world.getClass().getMethod("notify", blockPosition.getClass(), iBlockDataClass, iBlockDataClass, int.class);
		}
		notifyMethod.invoke(world, blockPosition, iBlockData, iBlockData, 3);
	}

	private static Object getTileEntityFurnace(Furnace furnace) throws ReflectiveOperationException {
		if(tileEntityFurnaceMethod == null) {
			tileEntityFurnaceMethod = furnace.getClass().getMethod("getTileEntity");
		}
		return tileEntityFurnaceMethod.invoke(furnace);
	}

	private static void setTileEntityFurnace(Furnace furnace, Object tileEntityFurnace) throws ReflectiveOperationException {
		if (tileEntityFurnaceField == null) {
			tileEntityFurnaceField = furnace.getClass().getDeclaredField("furnace");
			tileEntityFurnaceField.setAccessible(true);
		}
		tileEntityFurnaceField.set(furnace, tileEntityFurnace);
	}

	private static String getNMSPackageName() {
		return getOBCPackageName().replace("org.bukkit.craftbukkit", "net.minecraft.server");
	}

	private static String getOBCPackageName() {
		return Bukkit.getServer().getClass().getPackage().getName();
	}

	private static void setCustomName(Object nbtTagCompound, String value) throws ReflectiveOperationException {
		if(setStringMethod == null) setStringMethod = nbtTagCompound.getClass().getMethod("setString", String.class, String.class);
		setStringMethod.invoke(nbtTagCompound, "CustomName", value);
	}
}
