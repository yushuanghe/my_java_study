package j2se.core.net.tcp.command;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
        Thread t = new Thread(new ClientRunner(socket));
        t.start();
    }

}

class ClientRunner implements Runnable {

    private Socket socket;

    public ClientRunner(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            DataOutputStream output = new DataOutputStream(socket
                    .getOutputStream());
            while (true) {
                // GET:src/Demo.java
                // 接收键盘输入的指令
                String command = scanner.nextLine();
                // 发送指令
                output.writeUTF(command);
                // 处理回应
                if ("exit".equals(command)) {
                    break;
                }
                while (true) {
                    String response = input.readLine();
                    if ("EOF".equals(response))
                        break;
                    System.out.println(response);
                }
            }
            System.out.println("game over!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
