package j2se.core.net.job.chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by yobdc on 2017/01/05.
 */
public class ChatServer {

    //维护客户端线程的集合（实际维护的是与客户端通信的socket对象）
    private CopyOnWriteArrayList<ServerThread> clientSockets;

    public ChatServer() {
        this.clientSockets = new CopyOnWriteArrayList<ServerThread>();
    }

    public static void main(String[] args) {
        System.out.println("服务端启动！");
        new ChatServer().startUp();
    }

    private void startUp() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(6666);

            while (true) {
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new ServerThread(socket));
                thread.start();
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

    class ServerThread implements Runnable {

        private Socket socket;

        private BufferedReader in;
        private PrintWriter out;
        private String name;
        private boolean isStop;

        public ServerThread(Socket socket) {
            try {
                this.socket = socket;
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
//                name = String.format("[%s:%s]", socket.getInetAddress().getHostAddress(), socket.getPort());
                name = null;
                clientSockets.add(this);
                this.isStop = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!isStop) {
                receiveMessage();
            }
        }

        /**
         * 给每一个客户端发送消息
         */
        public void sendMessage(String msg) {
            for (ServerThread thread : clientSockets) {
                thread.out.println(msg);
            }
        }

        /**
         * 读取客户端消息
         */
        public void receiveMessage() {
            String line = null;
            try {
                while (!isStop) {
                    while ((line = in.readLine()) != null) {

                        if (name != null) {
                            if ("quit".equalsIgnoreCase(line)) {
                                //客户端发送退出指令
                                this.out.println("disconnect");
                                stop();
                                return;
                            } else {
                                sendMessage(name + "说：" + line);
                            }
                        } else {
                            name = line;
                            sendMessage(name + "上线了！");
                        }
                    }
                }
            } catch (SocketException e) {
                if (name != null)
                    System.out.println(name + "非法退出！");
                stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 停止
         */
        public void stop() {
            //给客户端发送停止指令
            if (name != null)
                sendMessage(name + "下线了！");
            clientSockets.remove(this);
            this.isStop = true;
        }
    }
}
