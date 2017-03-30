package j2se.core.serialize.demo03;

import java.io.*;

/**
 * Created by yobdc on 2017/01/12.
 */
public class TestExternalizableWrite {
    public static void main(String[] args) {
        String path = "serialize/myExternalizable.bin";
        File file = new File(path);

        UserInfo info = new UserInfo("大力", "大力出奇迹！", 40);

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)));
            out.writeObject(info);
            out.flush();
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
