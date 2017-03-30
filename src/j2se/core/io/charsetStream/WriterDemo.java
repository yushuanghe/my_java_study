package j2se.core.io.charsetStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class WriterDemo {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入你要写入的文件名");
		String filename = scanner.nextLine();
		File file = new File(filename);

		Writer writer = new FileWriter(file);

		System.out.println("请输入要写入的文件内容");
		while (true) {
			String content = scanner.nextLine();

			if (content.length() == 0)
				break;

			writer.write(content);
			writer.write("\r\n");
		}
		writer.flush();
		writer.close();
		scanner.close();
	}
}
