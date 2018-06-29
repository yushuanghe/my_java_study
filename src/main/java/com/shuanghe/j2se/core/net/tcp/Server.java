package com.shuanghe.j2se.core.net.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        System.out.println("服务器启动中...");

        while (true) {
            // 主线程会一直阻塞到客户连接的传入
            Socket socket = server.accept();

            //发送数据
//            DataOutputStream output = new DataOutputStream(
//                    socket.getOutputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

//            output.writeUTF("IP:" + socket.getInetAddress());
//            output.writeUTF("PORT:" + socket.getPort());
            output.println("IP:" + socket.getInetAddress());
            output.println("PORT:" + socket.getPort());

            System.out.println("客户端连接成功！");
            System.out.println("IP:" + socket.getInetAddress() + "," + "PORT:" + socket.getPort());

            output.flush();
            output.close();
            socket.close();
        }
    }
}
