package com.shuanghe.j2se.core.net.job.encrypt;

/**
 * 加密工具类
 * Created by yobdc on 2017/01/10.
 */
public class Encryption {
    public static void main(String[] args) {
        Encryption encryption = new Encryption();
        String str = "abcdefgxyzuvw";

        System.out.println(encryption.encrypt(str));
    }

    public static String encrypt(String str) {
        String result = "";

        if ("".equals(str) || str == null)
            return result;

        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c < 'x')
                c = (char) (c + 3);
            else
                c = (char) (c - 26 + 3);

            result += c;
        }

        return result;
    }
}
