package j2se.core.serialize.demo02;

import com.thoughtworks.xstream.XStream;
import j2se.core.serialize.demo01.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yobdc on 2017/01/11.
 */
public class SerializeFromXml {
  public static void main(String[] args) {
    String path = "serialize/myPerson.xml";
    File file = new File(path);

    XStream xStream = new XStream();

    BufferedInputStream in = null;
    try {
      in = new BufferedInputStream(new FileInputStream(file));
      List<Person> list = (ArrayList<Person>) xStream.fromXML(in);

      for (Person p : list) {
        System.out.println(p);
      }
    } catch (FileNotFoundException e) {
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
