package com.shuanghe.j2se.core.io.file;

import java.io.File;

public class FileDemo1 {
	public static void main(String[] args) {
		File file = new File("C:\\java\\workspace\\my_java_study\\new.properties");

		System.out.println(file.getAbsolutePath());
		System.out.println(file.getName());
		System.out.println(file.getPath());
		System.out.println(file.getParent());
		System.out.println(file.isFile());
		System.out.println(file.exists());

		System.out.println(File.separator);
	}
}
