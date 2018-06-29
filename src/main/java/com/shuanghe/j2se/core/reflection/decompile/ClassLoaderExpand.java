package com.shuanghe.j2se.core.reflection.decompile;

import java.io.*;

/**
 * Created by yobdc on 2017/01/16.
 */
public class ClassLoaderExpand extends ClassLoader {
    protected Class<?> findClass(String classFileName, String name) {
        byte[] datas = loadClassData(classFileName);
        return defineClass(name, datas, 0, datas.length);
    }

    private byte[] loadClassData(String classFileName) {
        byte[] datas = null;

        File file = new File(classFileName + ".class");
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            in = new BufferedInputStream(new FileInputStream(file));
            out = new ByteArrayOutputStream();
            int line = 0;
            while ((line = in.read()) != -1) {
                out.write(line);
            }

            datas = out.toByteArray();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        return datas;
    }
}
