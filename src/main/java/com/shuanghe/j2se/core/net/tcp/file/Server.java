package com.shuanghe.j2se.core.net.tcp.file;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单的传输文件范例(接收端)
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // 在6666端口创建一个 ServerSocket
        ServerSocket server = new ServerSocket(6666);
        // 循环等待客户的连接
        while (true) {
            // 主线程会一直阻塞到客户连接的传入
            Socket socket;
            try {
                socket = server.accept();
                BufferedOutputStream output = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(socket.getPort() + ".tmp")));
                byte[] data = new byte[1024];
                while (true) {
                    int i = socket.getInputStream().read(data);
                    if (i == -1)
                        break;
                    output.write(data, 0, i);
                }

                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
