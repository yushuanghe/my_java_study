package com.shuanghe.j2se.core.net.job.job01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by yobdc on 2016/12/29.
 */
public class Client {

    private Socket socket;

    private Server server;

    public Client() {
        this.server = Server.newInstance();
    }

    public static void main(String[] args) {
        new Client().startUp();
    }

    private void startUp() {

        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader sbr = null;

        try {
//            socket = new Socket("192.168.5.121", 6666);
            socket = new Socket("127.0.0.1", 6666);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sbr = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("请输入用户名");
            String name = sbr.readLine();
            out.println(name + "上线了！");

            Thread clientWriteThread = new Thread(new ClientWriteThread(name, socket, out, sbr));
            clientWriteThread.start();

            Thread clientReadThread = new Thread(new ClientReadThread(socket, in));
            clientReadThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        server.clientSockets.remove(socket);

                        try {
                            if (socket != null)
                                socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
    }
}

class ClientWriteThread implements Runnable {

    private String name;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader sbr;

    public ClientWriteThread(String name, Socket socket,
                             PrintWriter out, BufferedReader sbr) {
        this.name = name;
        this.socket = socket;
        this.out = out;
        this.sbr = sbr;
    }

    @Override
    public void run() {
        String line = null;

        try {
            while ((line = sbr.readLine()) != null) {
                out.println(String.format("[%s]说：%s", name, line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sbr != null)
                    sbr.close();
                if (out != null)
                    socket.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientReadThread implements Runnable {

    private Socket socket;
    private BufferedReader in;

    public ClientReadThread(Socket socket, BufferedReader in) {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void run() {
        String result = null;
        try {
            while ((result = in.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    socket.shutdownInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
