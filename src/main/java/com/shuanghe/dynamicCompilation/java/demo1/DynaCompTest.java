package com.shuanghe.dynamicCompilation.java.demo1;

import java.io.*;

/**
 * Created by yushuanghe on 2017/08/24.
 */
public class DynaCompTest {
    private static DynamicEngine de = DynamicEngine.getInstance();

    public static void main(String[] args) throws Exception {
        String className = "Test01";
        String code=readCodeFile("C:\\Users\\yushuanghe\\Desktop\\test.txt");

        System.out.println(code);

        Object instance = de.javaCodeToObject(className, code);
        System.out.println(instance);
    }

    /**
     * 读取代码文件，返回代码字符串
     *
     * @param filePath
     * @return
     */
    public static String readCodeFile(String filePath) {
        StringBuffer sb = new StringBuffer();

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
