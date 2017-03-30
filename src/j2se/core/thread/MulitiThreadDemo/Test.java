package j2se.core.thread.MulitiThreadDemo;

import java.util.StringTokenizer;

/**
 * Created by yobdc on 2016/12/22.
 */
public class Test {
    public static void main(String[] args) {

        String file="C:/Users/yobdc/Documents/我的坚果云";

        StringTokenizer st = new StringTokenizer(file, "/");
        while (st.hasMoreTokens()) {
            file = st.nextToken();
        }

        System.out.println(file);
    }
}
