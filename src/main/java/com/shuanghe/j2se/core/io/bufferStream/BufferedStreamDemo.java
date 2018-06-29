package com.shuanghe.j2se.core.io.bufferStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferedStreamDemo {
	public static void main(String[] args) throws IOException {
		// 拿到项目主目录
		String dir = System.getProperty("user.dir");
		System.out.println(dir);

		File file = new File(dir + "/src/com.shuanghe.j2se/core/io/bufferStream/BufferedStreamDemo.java");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));

		String line;
		while ((line = reader.readLine()) != null) {
			// writer.write(line);
			// writer.write("\r\n");
			writer.println(line);
		}
		writer.flush();
		writer.close();
		reader.close();
	}
}
