package j2se.core.io.charsetStream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderDemo {
	public static void main(String[] args) throws IOException {
		File file = new File("src/j2se/core/io/file/FileDemo.java");
		Reader reader = new FileReader(file);

		// 读取单个字符
		int i = reader.read();
		System.out.println(i + " " + (char) i);

		char[] array = new char[200];
		// while (true) {
		// // 将字符读入到字符数组，返回值是读取到的字符数
		// i = reader.read(array);
		// // 读取到文件末尾
		// if (i == -1)
		// break;
		// for (int j = 0; j < i; j++)
		// System.out.print(array[j]);
		// }
		int len = -1;
		// 最后一次读取后假设读取到10个字符，len=10，到文件末尾，再读len=-1
		while ((len = reader.read(array, 0, array.length)) != -1) {
			for (int j = 0; j < len; j++)
				System.out.print(array[j]);
		}
		reader.close();
	}
}
