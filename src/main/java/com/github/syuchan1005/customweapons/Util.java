package com.github.syuchan1005.customweapons;

import java.util.Arrays;

/**
 * Created by syuchan on 2016/08/05.
 */
public class Util {

	public static boolean arrayContains(Object obj, Object... objs) {
		return Arrays.asList(objs).contains(obj);
	}
}
