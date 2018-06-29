package com.shuanghe.j2se.core.net.job.chatRoom;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by yobdc on 2017/01/05.
 */
public class ChatClient {

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader sbr;

    private boolean isStop;

    public ChatClient() {
        this.isStop = false;
    }

    public static void main(String[] args) {
        new ChatClient().startUp();
    }

    private void startUp() {
        try {
            socket = new Socket("127.0.0.1", 6666);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            sbr = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("请输入用户名");
            String name = sbr.readLine();
            out.println(name);

            Thread thread = new Thread(new ClientReadThread(socket));
            thread.start();

            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (sbr != null)
                    sbr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage() {
        try {
            while (!isStop) {
                String line = sbr.readLine();

                if (line == null)
                    break;
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.isStop = true;

        try {
            if (sbr != null)
                sbr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientReadThread implements Runnable {
        private Socket socket;

        public ClientReadThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (!isStop) {
                    String line = in.readLine();

                    if (line == null)
                        return;

                    if ("disconnect".equals(line)) {
                        stop();
                        return;
                    } else {
                        System.out.println(line);
                    }
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
    }
}
