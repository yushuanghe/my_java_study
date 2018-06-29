package com.shuanghe.j2se.core.io.file;

import java.io.File;
import java.io.IOException;

public class DirectoryDemo {
	public static void main(String[] args) throws IOException {
		File dir = new File("src/com.shuanghe.j2se/core/io/file");
		if (dir.isDirectory()) {
			System.out.println(dir + "是一个目录！");
		}

		// 不会递归
		for (String s : dir.list()) {
			System.out.println(s);
		}

		createSingleDir("src/com.shuanghe.j2se/core/io/file/test1");
		deleteSingleDir("src/com.shuanghe.j2se/core/io/file/test1");

		createMultiDir("test2/dir");
		deleteMultiDir("test2");

		createFileWithDir("test3/haha.txt");
		deleteMultiDir("test3");
	}

	private static void createFileWithDir(String string) throws IOException {
		File file = new File(string);
		File dir = file.getParentFile();

		if (!dir.exists())
			dir.mkdirs();

		if (!file.exists()) {
			if (file.createNewFile())
				System.out.println("文件与目录创建成功！");
			else
				System.out.println("失败了！");
		}
	}

	private static void deleteMultiDir(String string) {
		File dir = new File(string);

		if (!dir.exists())
			return;
		if (deleteDir(dir))
			System.out.println("删除成功！");
		else
			System.out.println("删除失败！");
	}

	/**
	 * 递归删除文件或目录
	 * 
	 * @param string
	 */
	private static boolean deleteDir(File current) {
		boolean flag = true;
		if (current.isDirectory()) {
			for (File file : current.listFiles()) {
				flag = flag && deleteDir(file);
			}
		}
		return flag && current.delete();
	}

	private static void createMultiDir(String string) {
		File dirs = new File(string);

		if (dirs.exists()) {
			return;
		}
		if (dirs.mkdirs()) {
			System.out.println("目录组创建成功！");
		} else
			System.out.println("目录组创建失败！");
	}

	private static void deleteSingleDir(String string) {
		File dir = new File(string);

		if (!dir.exists()) {
			return;
		}

		// 删除之前必须判断，如果文件不存在删除报错
		if (dir.delete()) {
			System.out.println("删除成功！");
		} else
			System.out.println("删除失败！");
	}

	private static void createSingleDir(String string) {
		File dir = new File(string);

		if (dir.exists()) {
			return;
		}
		if (dir.mkdir()) {
			System.out.println("目录创建成功！");
		} else
			System.out.println("目录创建失败！");
	}
}
