package j2se.core.net.tcp.demo02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yobdc on 2016/12/28.
 */
public class Server {

    public static void main(String[] args) {
        new Server().startUp();
    }

    private void startUp() {
        System.out.println("服务端开启！");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);

            while (true) {
                Socket socket = serverSocket.accept();

                Thread read = new Thread(new ServerThread(socket));
                read.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}

class ServerThread implements Runnable {

    private Socket socket;

    private BufferedReader in = null;
    private PrintWriter out = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(String.format("[%s:%s]:%s",
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(), line));
                out.println(line.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}