package com.shuanghe.j2se.core.net.job.encrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by yobdc on 2017/01/11.
 */
public class Client {

    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader sbr;

    public Client() {

    }

    public static void main(String[] args) {
        new Client().startUp();
    }

    private void startUp() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 6666);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.sbr = new BufferedReader(new InputStreamReader(System.in));

            Thread thread = new Thread(new ClientReadThread(socket));
            thread.start();

            String line = null;
            while ((line = sbr.readLine()) != null) {
                out.println(Encryption.encrypt(line));
            }
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

    class ClientReadThread implements Runnable {
        private Socket socket;
        private boolean isRun;

        public ClientReadThread(Socket socket) {
            this.socket = socket;
            this.isRun = true;
        }

        @Override
        public void run() {
            receiveMessage();
        }

        private void receiveMessage() {
            try {
                while (isRun) {
                    String line = null;
                    if ((line = in.readLine()) != null) {
                        line = Decryption.decrypt(line);
                        if ("disconnect".equals(line)) {
                            close();
                        } else
                            System.out.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void close() {
            this.isRun = false;
            System.out.println("客户端关闭");
        }
    }
}
