package j2se.core.net.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLDemo {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://cn.bing.com/search?q=spark%E6%8B%9B%E8%81%98&qs=n&form=QBRE&sp=-1&pq=spark%E6%8B%9B%E8%81%98&sc=1-7&sk=&cvid=43F0B673BD97413A8B2CBCB40D35809B");
        //访问本地：file://
        InputStream input = url.openStream();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, "UTF-8"));

        while (true) {
            String s = reader.readLine();
            if (s == null)
                break;
            System.out.println(s);
        }

        reader.close();
    }
}
