package j2se.core.net.job.job02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Client {
    private PrintWriter out;

    public static void main(String[] args) {
        new Client().startup();
    }


    public void startup() {
        Socket s = null;
        try {
            s = new Socket("127.0.0.1", 7777);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            while (true) {
                String str = br.readLine();
                handle(str);
                out.println("recevie " + str);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handle(String str) {
        String con = str.substring(0, 2);
        String msg = str.substring(2);
        if (con.equalsIgnoreCase("1-")) {
            getFile(msg);
        } else if (con.equalsIgnoreCase("2-")) {
            adv(msg);
        }
    }

    private void adv(String msg) {
        new MyFrame(msg);
    }

    private void getFile(String path) {
        //遍历文件夹
        File f = new File(path);
        if (f.exists()) {
            String[] fs = f.list();
            StringBuilder sb = new StringBuilder();
            for (String fn : fs) {
                sb.append(fn).append("[##]");
            }
            out.println(sb.toString());
        } else {
            out.println("文件夹不存在");
        }
    }

    private class MyFrame extends JFrame {
        public MyFrame(String str) {
            this.setLocation(100, 100);
            this.setSize(300, 200);
            this.setResizable(false);
            this.setTitle("注意：小广告来了");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            JLabel jl = new JLabel("<html>" + str + "</html>");
            this.add(jl);
            this.setVisible(true);
        }
    }
}
