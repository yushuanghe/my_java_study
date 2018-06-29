package com.shuanghe.j2se.core.io.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();

		File input = new File("config.properties");
		// 把文件加载到prop(把键值对添加到HashMap)
		prop.load(new FileInputStream(input));

		System.out.println(prop.getProperty("ip"));
		// 在内存中prop对象添加键值对
		prop.setProperty("key", "value");

		File output = new File("new.properties");
		prop.store(new FileOutputStream(output), "列表属性的描述");
	}
}
