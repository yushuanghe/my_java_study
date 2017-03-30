package j2se.core.io.byteStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 使用字节流读取文本内容，完成编码转换
 * 
 * @author yobdc
 *
 */
public class CharsetDemo {
	public static void main(String[] args) throws IOException {
		// 没有转换字符集
		error("txt/utf8.txt");
		System.out.println("=================");
		right("txt/gbk.txt", "gbk");
	}

	private static void right(String filename, String charset) throws IOException {
		File file = new File(filename);

		InputStreamReader input = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)), charset);

		char[] arr = new char[1024];
		int i = 0;
		while ((i = input.read(arr, 0, arr.length)) != -1) {
			for (int j = 0; j < i; j++)
				System.out.print(arr[j]);
		}
		input.close();
	}

	private static void error(String filename) throws IOException {
		File file = new File(filename);

		// BufferedReader input = new BufferedReader(new FileReader(file));
		// String s;
		// while ((s = input.readLine()) != null) {
		// System.out.println(s);
		// }

		// BufferedInputStream input = new BufferedInputStream(new
		// FileInputStream(file));
		// int i = 0;
		// while ((i = input.read()) != -1)
		// System.out.print((char) i);

		FileInputStream input = new FileInputStream(file);
		byte[] arr = new byte[1024];
		int len = 0;
		while ((len = input.read(arr, 0, arr.length)) != -1) {
			String str = new String(arr, "utf-8");
			System.out.println(str);
		}
		System.out.println();
		input.close();
	}
}
