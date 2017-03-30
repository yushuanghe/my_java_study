package j2se.core.io.property;

import java.util.Map;
import java.util.Map.Entry;

public class Environment {
	public static void main(String[] args) {
		// 得到系统环境变量
		Map<String, String> env = System.getenv();

		for (Entry<String, String> entry : env.entrySet()) {
			System.out.println("key:" + entry.getKey());
			System.out.println("value:" + entry.getValue());
		}

		// 得到系统环境变量
		String s = System.getenv("JAVA_HOME");
		System.out.println(s);
	}
}
