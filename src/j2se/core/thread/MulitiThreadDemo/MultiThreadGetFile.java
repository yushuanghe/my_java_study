package j2se.core.thread.MulitiThreadDemo;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * 多线程下载调度程序
 * Created by yobdc on 2016/12/21.
 */
public class MultiThreadGetFile implements Runnable {
    long startPos = 0, endPos = 0;
    // 要带上完整的路径
    String currentFileThreadName;
    // 网络文件地址
    String urlFile;
    // 网络文件名
    String urlFileName;
    // 下载文件要存放的地址
    String localFileAddress;
    // 要同时下载的线程数
    int threadNum;
    // 每个线程要下载的文件分块的大小
    long[] eachThreadLength;
    // 网络文件的大小
    long urlFileLength;
    URL url;
    HttpURLConnection httpURLConnection;
    // 检测线程
    public static boolean[] checkList;

    public MultiThreadGetFile(String urlFile, int threadNum, String localFileAddress) {
        this.urlFile = urlFile;// 要下载的网络资源
        this.threadNum = threadNum;// 要同时下载的线程数
        this.localFileAddress = localFileAddress; // 要保存的本地路径
    }

    @Override
    public void run() {
        init();
    }

    private void init() {
        //创建临时文件夹，存储临时文件
        if (!new File(localFileAddress + "tmp").mkdirs())
            System.out.println("创建文件夹失败！");

        eachThreadLength = new long[threadNum];

        try {
            url = new URL(urlFile);

            // 此处的Connection仅仅是用于获取服务端的要下载的资源的名称资源的大小，所以一旦得到后就关闭
            httpURLConnection = (HttpURLConnection) url.openConnection();
            urlFileLength = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
            System.out.println("urlFileLength:" + (urlFileLength));
            //取得服务端上的路径与文件名
            urlFileName = url.getFile();
            //只获取文件名
            urlFileName = getFileName(urlFileName);
            System.out.println("urlFileName:" + (urlFileName));
            //确定每个线程文件最终要写的文件的大小
            init_getEachThreadLength();
            //此处的Connection仅仅是用于获取服务端的要下载的资源的名称资源的大小，所以一旦得到后就关闭
            httpURLConnection.disconnect();

            //用于记载每一个线程是否下载完毕（序号从1开始）
            checkList = new boolean[threadNum + 1];

            //确定每个文件的起始，结束位置，开启线程下载
            for (int i = 1; i <= threadNum; i++) {
                if (i > 1)
                    //part2开始，每个线程文件的开头是前一个文件头+前一个文件的大小
                    startPos = startPos + eachThreadLength[i - 2];
                //每个文件的结束=文件开始+该文件大小
                endPos = startPos + eachThreadLength[i - 1];

                currentFileThreadName = localFileAddress + "tmp\\" + urlFileName + ".part" + i;
//                System.out.println("startPos:" + (startPos));
//                System.out.println("endPos:" + (endPos));
//                System.out.println("Size:" + (endPos - startPos));

                //开启worker线程下载
                Thread thread = new Thread(
                        new GetFileThread(urlFile, startPos, endPos, currentFileThreadName, i));
                thread.start();
                checkList[i] = false;
            }

            //用于监视九个线程是否已经下载完毕，如果已经下载完毕，则开始启动合并文件的操作
            Thread policeThread = new Thread(new PoliceThread(threadNum, localFileAddress, localFileAddress + "tmp"));
            policeThread.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 确定每个线程要写的文件的大小
     */
    private void init_getEachThreadLength() {
        long l;
        l = urlFileLength / threadNum;
        for (int i = 0; i < threadNum; i++) {
            //最后一个线程
            if (i == threadNum - 1) {
                //最后一个可能写不完文件就结束了
                eachThreadLength[i] = urlFileLength - i * l;
            } else {
                eachThreadLength[i] = l;
            }
        }
    }

    /**
     * 得到路径的文件名
     *
     * @param file
     * @return
     */
    private String getFileName(String file) {
        StringTokenizer st = new StringTokenizer(file, "/");
        while (st.hasMoreTokens()) {
            file = st.nextToken();
        }
        return file;
    }
}
