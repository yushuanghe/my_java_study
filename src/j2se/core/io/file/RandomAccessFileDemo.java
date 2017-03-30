package j2se.core.io.file;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import oop.Person;

public class RandomAccessFileDemo {
	private static List<Long> pointer;

	public static void main(String[] args) throws Exception {
		Person[] persons = new Person[] { new Person("one", 32, true), new Person("two", 31, false),
				new Person("three", 30, true), new Person("four", 29, false) };

		write(persons);

		Person p = read(2);
		System.out.println(p);
	}

	private static Person read(int i) throws Exception {
		long point = pointer.get(i);

		RandomAccessFile file = new RandomAccessFile("data/store.txt", "r");

		file.seek(point);

		String name = file.readUTF();
		System.out.println(name + ":" + file.getFilePointer());
		int age = file.readInt();
		System.out.println(age + ":" + file.getFilePointer());
		boolean sex = file.readBoolean();
		System.out.println(sex + ":" + file.getFilePointer());

		file.close();

		return new Person(name, age, sex);
	}

	private static void write(Person[] persons) throws Exception {
		File _file = new File("data/store.txt");
		if (!_file.exists()) {
			File dir = _file.getParentFile();
			dir.mkdirs();
		}
		RandomAccessFile file = new RandomAccessFile("data/store.txt", "rw");

		// 记录每一个对象的指针位置
		pointer = new ArrayList<Long>();

		for (Person p : persons) {
			// 写之前，将指针位置记录
			pointer.add(file.getFilePointer());

			file.writeUTF(p.getName());
			System.out.println("name:" + file.getFilePointer());
			file.writeInt(p.getAge());
			System.out.println("age:" + file.getFilePointer());
			file.writeBoolean(p.getSex());
			System.out.println("sex:" + file.getFilePointer());
		}
		file.close();

	}
}
