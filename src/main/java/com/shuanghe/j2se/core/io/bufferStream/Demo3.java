package com.shuanghe.j2se.core.io.bufferStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * BufferedInputStream,BufferedOutputStream 复制文件
 * 
 * @author yobdc
 *
 */
public class Demo3 {
	public static void main(String[] args) {
		String source = "assert/大力.mp3";
		String desc = "C:/Users/yobdc/Downloads/temp/assert/大力.mp3";

		File sourceFile = new File(source);
		File descFile = new File(desc);

		if (!descFile.getParentFile().exists())
			descFile.getParentFile().mkdirs();

		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream(sourceFile));
			out = new BufferedOutputStream(new FileOutputStream(descFile));

			byte[] arr = new byte[4096];
			int len = -1;
			while ((len = in.read(arr, 0, arr.length)) != -1) {
				out.write(arr, 0, len);
				System.out.println("===");
			}
			System.out.println("数据复制结束！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
