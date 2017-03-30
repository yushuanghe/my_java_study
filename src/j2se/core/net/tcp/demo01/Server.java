package j2se.core.net.tcp.demo01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yobdc on 2016/12/27.
 */
public class Server {
    public static void main(String[] args) {
        new Server().startUp();
    }

    private void startUp() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        String name = "客户端[%s:%s]";

        try {
            serverSocket = new ServerSocket(6666);
            System.out.println("服务器启动");

            while (true) {
                socket = serverSocket.accept();

                System.out.println("已经建立起与客户端连接");
                //客户端的ip与端口
                System.out.println(String.format(name, socket.getInetAddress().getHostAddress(),
                        socket.getPort()) + "已连接！");

                //自动flush
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("你好，客户端");
                System.out.println("你好，客户端");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
