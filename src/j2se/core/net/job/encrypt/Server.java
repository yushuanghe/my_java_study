package j2se.core.net.job.encrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yobdc on 2017/01/11.
 */
public class Server {

    private CopyOnWriteArrayList<ServerThread> clients;

    public Server() {
        this.clients = new CopyOnWriteArrayList<>();
    }

    public static void main(String[] args) {
        new Server().startUp();
    }

    private void startUp() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(6666);

            while (true) {
                Socket socket = serverSocket.accept();

                ServerThread st = new ServerThread(socket);
                Thread thread = new Thread(st);
                thread.start();

                clients.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handlerMessage(String str) {
        String result = null;

        return result;
    }

    class ServerThread implements Runnable {
        private Socket socket;

        private BufferedReader in;
        private PrintWriter out;

        private boolean isRun;

        public ServerThread(Socket socket) {
            this.socket = socket;
            this.isRun = true;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            receiveMessage();
        }

        private void receiveMessage() {
            try {
                while (isRun) {
                    String line = null;

                    while ((line = in.readLine()) != null) {
                        if ("quit".equals(Decryption.decrypt(line))) {
                            close();
                        } else {
                            System.out.println(line);
                            sendMessage(line);
                        }
                    }
                }
            } catch (SocketException e) {
                System.out.println("非法退出！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendMessage(String line) {
            if (line != null) {
                for (ServerThread s : clients) {
                    s.out.println(line);
                }
            }
        }

        private void close() {
            this.isRun = false;
            this.out.println(Encryption.encrypt("disconnect"));
            clients.remove(this);
        }
    }
}
