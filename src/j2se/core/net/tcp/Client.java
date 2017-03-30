package j2se.core.net.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入服务器地址:");
        String ip = scan.nextLine();
        System.out.println("请输入服务器端口:");
        int port = scan.nextInt();

        //与服务端建立连接
        Socket socket = new Socket(ip, port);

        //接收数据
//        DataInputStream input = new DataInputStream(socket.getInputStream());
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        try {
//            while (true) {
//                System.out.println(input.readUTF());
//            }
            String line = null;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            input.close();
            socket.close();
        }
    }
}
