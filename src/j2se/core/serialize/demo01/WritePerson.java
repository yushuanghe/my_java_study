package j2se.core.serialize.demo01;


import java.io.*;

/**
 * 把Person类序列化
 * Created by yobdc on 2017/01/11.
 */
public class WritePerson {
    public static void main(String[] args) {
        String path = "serialize/myPerson.bin";
        File file = new File(path);
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)));
            Person p = new Person("大力", 40);
            out.writeObject(p);

            out.flush();
            System.out.println("序列化对象成功！");
        } catch (IOException e) {
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
