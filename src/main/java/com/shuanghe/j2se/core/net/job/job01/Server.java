package com.shuanghe.j2se.core.net.job.job01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by yobdc on 2016/12/29.
 */
public class Server {

    public CopyOnWriteArrayList<Socket> clientSockets;

    private BlockingQueue<String> messages;

    private static Server server;

    private Server() {
        this.clientSockets = new CopyOnWriteArrayList<Socket>();
        this.messages = new LinkedBlockingQueue<String>();
    }

    public static Server newInstance() {
        if (server == null)
            return new Server();
        else
            return server;
    }

    public static void main(String[] args) {
        new Server().startUp();
    }

    void startUp() {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务端开启！");

            Thread serverWriteThread = new Thread(
                    new ServerWriteThread(clientSockets, messages));
            serverWriteThread.start();

            while (true) {
                Socket socket = serverSocket.accept();
                clientSockets.add(socket);

                //添加上线信息
//                messages.offer(String.format(""));

                Thread serverReadThread = new Thread(new ServerReadThread(socket, messages));
                serverReadThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerReadThread implements Runnable {

    private Socket socket;

    private BlockingQueue<String> messages;

    public ServerReadThread(Socket socket, BlockingQueue<String> messages) {
        this.socket = socket;
        this.messages = messages;
    }

    @Override
    public void run() {
        BufferedReader in = null;

        try {
            if (!socket.isClosed()) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = null;

                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    messages.offer(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class ServerWriteThread implements Runnable {

    private CopyOnWriteArrayList<Socket> clientSockets;

    private BlockingQueue<String> messages;

    public ServerWriteThread(CopyOnWriteArrayList<Socket> clientSockets,
                             BlockingQueue<String> messages) {
        this.clientSockets = clientSockets;
        this.messages = messages;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        String message = null;

        try {
            while (true) {
                while ((message = messages.poll()) != null) {
                    for (Socket socket : clientSockets) {
                        if (!socket.isClosed()) {
                            out = new PrintWriter(socket.getOutputStream(), true);
                            out.println(message);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }
}
