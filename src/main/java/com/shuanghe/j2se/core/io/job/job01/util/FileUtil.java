package com.shuanghe.j2se.core.io.job.job01.util;

import java.io.*;

/**
 * Created by yobdc on 2016/12/14.
 */
public class FileUtil {
    private File file;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader in;
    private PrintWriter out;

    public FileUtil(String fileName) throws IOException {
        this.file = new File(fileName);
        try {
            this.in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //自动flush
        this.out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)), true);
    }

    public String readLine() throws IOException {
        if (in != null)
            return in.readLine();
        return null;
    }

    public void writeLine(String str) {
        if (out != null)
            out.println(str);
    }

    public void close() throws IOException {
        if (in != null)
            in.close();
        if (out != null)
            out.close();
    }

    public void flush() {
        if (out != null) {
            out.flush();
        }
    }

    public String readAll() {
        StringBuilder sb = new StringBuilder();
        String str = null;
        try {
            while ((str = in.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
