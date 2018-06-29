package com.shuanghe.j2se.core.serialize.demo01;


import java.io.*;

/**
 * Created by yobdc on 2017/01/11.
 */
public class ReadPerson {
  public static void main(String[] args) {
    String path = "serialize/myPerson.bin";
    File file = new File(path);

    ObjectInputStream in = null;

    try {
      in = new ObjectInputStream(
              new BufferedInputStream(new FileInputStream(file)));
      try {
        Person p = (Person) in.readObject();
        if (p != null)
          System.out.println(p);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null)
          in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
