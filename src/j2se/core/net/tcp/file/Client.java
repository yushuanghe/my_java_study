package j2se.core.net.tcp.file;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 简单的传输文件范例(发送端)
 */
public class Client {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String ip = "127.0.0.1";
        int port = 6666;

        System.out.println("请输入文件名:");
        String s = scan.nextLine();

        try {
            Socket socket = new Socket(ip, port);

            BufferedOutputStream output = new BufferedOutputStream(socket
                    .getOutputStream());
            BufferedInputStream input = new BufferedInputStream(
                    new FileInputStream(new File(s)));

            try {
                byte[] data = new byte[1024];
                while (true) {
                    int i = input.read(data);
                    if (i == -1)
                        break;
                    output.write(data, 0, i);
                }
                output.flush();
            } catch (Exception e) {
                // 由于服务器主动断连接，所以会出现异常
                e.printStackTrace();
            } finally {
                output.close();
                input.close();
                socket.close();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

