package com.shuanghe.j2se.core.net.base;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        //获取本地ip地址
        InetAddress ip = InetAddress.getLocalHost();
        showIpInfo(ip);

        System.out.println("---------------");

        //获取指定域名的ip地址
        ip = InetAddress.getByName("www.163.com");
        showIpInfo(ip);

        System.out.println("---------------");

        // 获取指定域名的多个 IP 地址
        InetAddress[] ips = InetAddress.getAllByName("www.163.com");
        for (InetAddress i : ips) {
            System.out.println(i.getHostAddress());
        }
    }

    private static void showIpInfo(InetAddress ip) {
        // 获取主机名称
        System.out.println(ip.getHostName());
        // 获取 IP 地址
        System.out.println(ip.getHostAddress());
    }

}
