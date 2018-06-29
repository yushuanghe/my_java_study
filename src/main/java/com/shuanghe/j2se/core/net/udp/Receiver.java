package com.shuanghe.j2se.core.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 通过 UDP 接收数据的简单范例
 */
public class Receiver {
    public static void main(String[] args) throws IOException {
        // 使用指定的端口，创建 数据报嵌套字(用于接收数据)
        DatagramSocket socket = new DatagramSocket(6666);

        // 创建长度为 1024 个字节的数据报对象
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        while (true) {
            socket.receive(packet);
            byte[] data = packet.getData();

            String s = new String(data, 0, packet.getLength());

            if (s.equals("exit"))
                break;
            System.out.println("接收到的内容:" + s);
        }
        System.out.println("通信结束！");
        socket.close();
    }
}
