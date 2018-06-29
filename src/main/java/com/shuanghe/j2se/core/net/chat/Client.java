package com.shuanghe.j2se.core.net.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 简单的聊天时范例(客户端)
 */
public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        final Socket socket = new Socket("127.0.0.1", 6666);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                DataInputStream input;
                try {
                    input = new DataInputStream(socket.getInputStream());
                    while (true) {
                        String s = input.readUTF();
                        System.out.println("服务端说:" + s);
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
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
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
