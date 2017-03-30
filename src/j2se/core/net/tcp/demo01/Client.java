package j2se.core.net.tcp.demo01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by yobdc on 2016/12/27.
 */
public class Client {
    public static void main(String[] args) {
        new Client().startUp();
    }

    public void startUp() {
        Socket socket = null;
        try {
            socket = new Socket("192.168.1.102", 6666);
            System.out.println("连接成功");
            System.out.println(String.format("%s:%s", socket.getInetAddress().getHostAddress()
                    , socket.getPort()));

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = in.readLine();
            System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
