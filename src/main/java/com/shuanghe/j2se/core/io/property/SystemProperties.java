package com.shuanghe.j2se.core.io.property;

import java.util.Properties;

public class SystemProperties {
	public static void main(String[] args) {
		Properties prop = System.getProperties();

		// 列出全部系统属性
		// for (Entry<Object, Object> entry : prop.entrySet()) {
		// System.out.println("key:" + entry.getKey());
		// System.out.println("value:" + entry.getValue());
		// }

		// 列出全部系统属性
		prop.list(System.out);

		System.out.println("=================");

		// 获取系统指定系统属性内容
		String version = prop.getProperty("java.version");
		System.out.println(version);
		System.out.println(prop.getProperty("user.dir"));

		// 设置系统属性
		setProperty("大力", "大力出奇迹");
		System.out.println(prop.getProperty("大力"));

		prop.list(System.out);
	}

	private static void setProperty(String key, String value) {
		Properties prop = System.getProperties();

		prop.setProperty(key, value);
	}
}
