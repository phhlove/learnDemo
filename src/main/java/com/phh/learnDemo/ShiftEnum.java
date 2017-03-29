package com.phh.learnDemo;

import java.util.EnumMap;

/**
 * 
 *
 * @Description: 接口 shift班次 字段的枚举
 * @author phh
 * @date 2016年7月18日
 *
 */
public enum ShiftEnum  {
	A1(1, "白金卡"), A2(6, "金卡"), A3(3, "普通"); 
	
	
	private EnumMap<ShiftEnum ,String> urls = new EnumMap<ShiftEnum, String>(ShiftEnum.class);

	private int index;
	private String name;

	private ShiftEnum(int index, String name) {

		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 普通方法
	public static String getName(int index) {
		for (ShiftEnum c : ShiftEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

}
