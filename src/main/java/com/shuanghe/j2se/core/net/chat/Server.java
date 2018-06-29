package com.shuanghe.j2se.core.net.chat;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 简单的聊天室范例(服务器端)
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);

        while (true) {
            final Socket socket = server.accept();

            Thread t1 = new Thread() {
                @Override
                public void run() {
                    DataInputStream input;
                    try {
                        input = new DataInputStream(socket.getInputStream());
                        while (true) {
                            String s = input.readUTF();
                            System.out.println("客户端说:" + s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t1.start();

            Thread t2 = new Thread() {
                @Override
                public void run() {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(System.in));
                    try {
                        DataOutputStream out = new DataOutputStream(
                                socket.getOutputStream());
                        while (true) {
                            String s = br.readLine();
                            out.writeUTF(s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t2.start();
        }
    }

}
