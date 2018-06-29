package com.shuanghe.j2se.core.io.byteStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shuanghe.oop.Person;

public class ObjectStreamDemo {
	private static final String FILENAME = "data/object.dat";

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Person[] persons = { new Person("zhang", 32, true), new Person("lisi", 31, false) };
		writeToFile(persons);

		persons = readFromFile();
		System.out.println(Arrays.toString(persons));
	}

	private static Person[] readFromFile() throws IOException, ClassNotFoundException {
		File file = new File(FILENAME);
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
		List<Person> list = new ArrayList<Person>();
		try {
			while (true) {
				Person p = (Person) in.readObject();
				list.add(p);
			}
		} catch (EOFException e) {
		}
		in.close();
		return list.toArray(new Person[0]);
	}

	private static void writeToFile(Person[] persons) throws IOException {
		File file = new File(FILENAME);
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		for (Person p : persons) {
			out.writeObject(p);
		}
		Person person = new Person("大力", 44, true);
		out.writeObject(person);
		out.flush();
		out.close();
	}
}
