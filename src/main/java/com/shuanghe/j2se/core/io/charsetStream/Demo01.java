package com.shuanghe.j2se.core.io.charsetStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * BufferedReader,BufferedWriter 复制文件
 *
 * @author yobdc
 */
public class Demo01 {
    public static void main(String[] args) {
        File inFile = new File("src/com.shuanghe.j2se/core/io/charsetStream/Demo01.java");
        File outFile = new File("FileDemo1.txt");

        Reader reader = null;
        Writer writer = null;

        try {
            reader = new BufferedReader(new FileReader(inFile));
            writer = new BufferedWriter(new FileWriter(outFile));

            char[] arr = new char[20];
            int len = 0;
            while ((len = reader.read(arr, 0, arr.length)) != -1) {
                System.out.println(len);
                writer.write(arr, 0, len);
            }
            System.out.println(len);
            // String line;
            // while ((line = reader.readLine()) != null) {
            // writer.println(line);
            // }
            System.out.println("复制完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
