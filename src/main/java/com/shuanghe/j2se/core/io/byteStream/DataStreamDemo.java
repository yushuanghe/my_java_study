package com.shuanghe.j2se.core.io.byteStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shuanghe.oop.Person;

public class DataStreamDemo {

	private static final String FILENAME = "data/data.dat";

	public static void main(String[] args) throws IOException {
		Person[] persons = { new Person("zhang", 32, true), new Person("lisi", 31, false) };
		writeToFile(persons);

		persons = readFromFile();
		System.out.println(Arrays.toString(persons));
	}

	private static Person[] readFromFile() throws IOException {
		File file = new File(FILENAME);
		DataInputStream input = null;
		List<Person> list = null;
		try {
			input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			list = new ArrayList<Person>();
			while (true) {
				Person p = new Person();
				p.setName(input.readUTF());
				p.setAge(input.readInt());
				p.setSex(input.readBoolean());
				list.add(p);
			}
		} catch (EOFException e) {
		}
		input.close();
		// 集合转换为数组
		return list.toArray(new Person[0]);
	}

	private static void writeToFile(Person[] persons) throws IOException {
		File file = new File(FILENAME);

		DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		for (Person p : persons) {
			output.writeUTF(p.getName());
			output.writeInt(p.getAge());
			output.writeBoolean(p.getSex());
		}
		output.flush();
		output.close();
	}
}
