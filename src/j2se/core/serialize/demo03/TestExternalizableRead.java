package j2se.core.serialize.demo03;

import java.io.*;

/**
 * Created by yobdc on 2017/01/12.
 */
public class TestExternalizableRead {
    public static void main(String[] args) {
        String path = "serialize/myExternalizable.bin";
        File file = new File(path);

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(file)));
            UserInfo info = (UserInfo) in.readObject();
            System.out.println(info);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
