package j2se.core.serialize.demo02;

import com.thoughtworks.xstream.XStream;
import j2se.core.serialize.demo01.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yobdc on 2017/01/11.
 */
public class SerializeToXml {
  public static void main(String[] args) {

    List<Person> persons = new ArrayList<>();
    persons.add(new Person("大力", 40));
    persons.add(new Person("逸峰", 22));
    persons.add(new Person("百元哥", 100));

    String path = "serialize/myPerson.xml";
    File file = new File(path);
    BufferedOutputStream out = null;
    XStream xStream = new XStream();

    try {
      out = new BufferedOutputStream(new FileOutputStream(file));

      xStream.toXML(persons, out);
      System.out.println("序列化成功！");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (out != null)
          out.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
