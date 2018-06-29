package com.shuanghe.j2se.core.net.udp;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * 通过 UDP 发送数据的简单范例
 */
public class Sender {

    public static void main(String[] args) throws IOException {

        // 使用指定的端口，创建 数据报嵌套字(用于发送数据)
        DatagramSocket socket = new DatagramSocket();
        // 创建数据报发送目的地的地址对象
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        // 接收用户输入
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String content = scanner.nextLine();
            if (content.length() == 0)
                break;

            byte[] message = content.getBytes();
            //byte数组，数组长度，地址
            DatagramPacket packet = new DatagramPacket(message, message.length, address);
            //发送数据报
            socket.send(packet);
        }

        socket.close();
    }

}
