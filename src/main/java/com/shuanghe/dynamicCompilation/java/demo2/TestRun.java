package com.shuanghe.dynamicCompilation.java.demo2;

import java.io.*;

/**
 * 动态执行一段代码(生成文件->编译->执行)
 * Created by yushuanghe on 2017/08/25.
 */
public class TestRun {
    private String fileName = "Test.java";
    private String className = "Test.class";

    public TestRun() {
        File f = new File(fileName);
        if (f.exists()) f.delete();

        f = new File(className);
        if (f.exists()) f.delete();
    }

    /**
     * 创建java文件
     */
    public void createJavaFile(String body) {
        String head = "public class Test{\r\n public static void runCode(){";

        String end = "\r\n }\r\n}";
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                    fileName));
            dos.writeBytes(head);
            dos.writeBytes(body);
            dos.writeBytes(end);
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 编译
     */
    public int makeJavaFile() {
        int ret = 0;
        try {
            Runtime rt = Runtime.getRuntime();
            Process ps = rt.exec("javac " + fileName);
            ps.waitFor();
            byte[] out = new byte[1024];
            DataInputStream dos = new DataInputStream(ps.getInputStream());
            dos.read(out);
            String s = new String(out);
            if (s.indexOf("Exception") > 0) {
                ret = -1;
            }
        } catch (Exception e) {
            ret = -1;
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 反射执行
     */
    public void run() {
        try {
            //Class.forName("Test").getMethod("runCode", new Class[]{}).invoke(null, new Object[]{});

            Runtime rt = Runtime.getRuntime();
            Process ps = rt.exec("java " + className);
            InputStreamReader ir = new InputStreamReader(ps.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            System.out.println("===");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("===");
            ps.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {

        String cmd = "System.out.println(\"usage:java TestRun int i=1; System.out.println(i+100);\");";
        //if (args.length >= 1) {
        //    cmd = args[0];
        //}
        TestRun t = new TestRun();
        t.createJavaFile(cmd);
        if (t.makeJavaFile() == 0) {
            t.run();
        }
    }
}
