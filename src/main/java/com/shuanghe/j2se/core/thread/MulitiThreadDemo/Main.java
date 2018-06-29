package com.shuanghe.j2se.core.thread.MulitiThreadDemo;

/**
 * Client类
 * Created by yobdc on 2016/12/21.
 */
public class Main {
    String urlFile;// 网络文件地址
    int threadNum;// 要启动下载的线程数
    String localFileAddress;// 要保存的本地地址，请保证该处没有名为"tmp"的文件夹

    public Main() {
        /**
         * 下面的由使用者自己设为定
         */
        urlFile = "http://d3kbcqa49mib13.cloudfront.net/spark-2.0.2-bin-hadoop2.7.tgz";
        threadNum = 9;// 要同时下载的线程数
        localFileAddress = "C:\\Users\\yobdc\\Downloads\\";
    }

    public Main(String urlFile, int threadNum, String localFileAddress) {
        this.urlFile = urlFile;
        this.threadNum = threadNum;
        this.localFileAddress = localFileAddress;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        //开线程做，并发，防止阻塞
        Thread thread = new Thread(
                new MultiThreadGetFile(urlFile, threadNum, localFileAddress));
        thread.start();
    }
}
