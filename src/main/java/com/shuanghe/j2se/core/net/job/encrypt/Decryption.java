package com.shuanghe.j2se.core.net.job.encrypt;

/**
 * 解密工具类
 * Created by yobdc on 2017/01/10.
 */
public class Decryption {
    public static void main(String[] args) {
        Decryption decryption = new Decryption();
        String str = "defghijabcxyz";

        System.out.println(decryption.decrypt(str));
    }

    public static String decrypt(String str) {
        String result = "";
        if ("".equals(str) || str == null)
            return result;

        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c > 'c')
                c = (char) (c - 3);
            else
                c = (char) (c + 26 - 3);

            result += c;
        }

        return result;
    }
}
