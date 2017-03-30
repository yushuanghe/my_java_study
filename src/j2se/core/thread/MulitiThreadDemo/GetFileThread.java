package j2se.core.thread.MulitiThreadDemo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载线程：
 * 根据传入的下载开始点以及下载结束点，利用HttpURLConnection的RANGE属性获取（只有起始位置），
 * 从网络文件开始下载，并且结合判断是否下载的文件大小已经==（文件下载结束点-文件下载起始点），
 * 这里结合断点续传原理，可以更快，更有效的下载文件
 * Created by yobdc on 2016/12/21.
 */
public class GetFileThread implements Runnable {

    // 传入的文件下载开始、结束点
    long startPos, endPos;
    // 要带上完整的路径
    String currentFileThreadName;
    // 网络文件地址
    String urlFile;
    // 当前是那个线程，这主要是用于下载完成后将对应的检测标志设为true，表示下载完成
    int currentThread;

    public GetFileThread(String urlFile, long startPos, long endPos, String currentFileThreadName, int currentThread) {
        //开始下载点
        this.startPos = startPos;
        //结束下载点
        this.endPos = endPos;
        //当前线程文件的完程路径及名字
        this.currentFileThreadName = currentFileThreadName;
        //网络文件地址
        this.urlFile = urlFile;
        //当前线程编号
        this.currentThread = currentThread;
    }

    @Override
    public void run() {
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        DataOutputStream out = null;
        BufferedInputStream in = null;
        FileOutputStream fos = null;
        //文件保存的地方及文件名，具体情况可以改
        String localFile = currentFileThreadName;
        //未下载完文件加.tp扩展名，以便于区别
        String localFile_tp = localFile + ".tp";
        //在断点续传中，用于取得当前文件已经下载的大小
        long fileSize = 0;
        RandomAccessFile raFile = null;
        //当前块要下载的文件总大小
        long totalSize = 0;

        int len = 0;
        byte[] bt = new byte[1024];
        try {
            url = new URL(urlFile);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //获取该线程需要写的文件的总大小
            totalSize = endPos - startPos;
            //获取已经下载的大小
            long downSize = 0;

            //确定临时文件是否存在
            if (fileExists(localFile_tp)) {
                //有.tp的临时文件，代表需要断点续传
                System.out.println("文件续传中...");

                //取得已经下载的大小，以便确定随机写入的位置
                fileSize = new File(localFile_tp).length();
                downSize = fileSize;
                //断点续传开始位置（文件头+已下载文件大小）
                fileSize = fileSize + startPos;

                //保证最后的part文件开始位置不会超出资源限制
                if (fileSize < endPos) {
                    /**
                     * httpURLConnection属性的设置一定要在得到输入流之前，否则会报已经连接的错误
                     */
                    //设置断点续传的开始位置，必须加 -
                    httpURLConnection.setRequestProperty("RANGE", "bytes=" + fileSize + "-");
                    //设置接收类型
                    httpURLConnection.setRequestProperty("Accept", "image/gif,image/x-xbitmap,application/msword,*/*");
                    raFile = new RandomAccessFile(localFile_tp, "rw");
                    //定位指针到tp文件末尾，续写
                    raFile.seek(downSize);

                    in = new BufferedInputStream(httpURLConnection.getInputStream());

                    while ((len = in.read(bt)) > 0) {
                        if (downSize < totalSize) {

                            downSize += len;
                            //文件末尾
                            if (downSize > totalSize) {
                                //上一步多加一个len，减去
                                len = (int) (totalSize - (downSize - len));
                            }
                            //写到endPos结束
                            raFile.write(bt, 0, len);


//                        if ((totalSize - downSize) > len) {
//                            raFile.write(bt, 0, len);
//                            downSize += len;
//                        } else {
//                            raFile.write(bt, 0, (int) (totalSize - downSize));
//                            downSize = totalSize;
//                        }

                        } else
                            break;
                    }
                }
                System.out.println("文件续传接收完毕！");
            } else if (!fileExists(localFile)) {
                //采用原始下载，但保证该文件没有下载

                //设置开始位置
                httpURLConnection.setRequestProperty("RANGE", "bytes=" + startPos + "-");
                in = new BufferedInputStream(httpURLConnection.getInputStream());
                //未下载完的为临时文件，命名.tp
                fos = new FileOutputStream(localFile_tp);
                out = new DataOutputStream(fos);
                System.out.println("正在接收文件...");

                while ((len = in.read(bt)) > 0) {
                    if (downSize < totalSize) {

                        if ((totalSize - downSize) > len) {
                            out.write(bt, 0, len);
                            downSize += len;
                        } else {
                            out.write(bt, 0, (int) (totalSize - downSize));
                            downSize = totalSize;
                        }

                    } else
                        break;
                }
                System.out.println("文件下载完毕！");
            }
            //文件下载完毕

            //线程结束，修改结束标志
            MultiThreadGetFile.checkList[currentThread] = true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //将临时文件改名
            if (getFileSize(localFile_tp) == totalSize) {
                fileRename(localFile_tp, localFile);
            }

            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (fos != null)
                    fos.close();
                if (raFile != null)
                    raFile.close();
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fileRename(String localFile_tp, String localFile) {
        File file = new File(localFile_tp);
        file.renameTo(new File(localFile));
        file.delete();
    }

    private long getFileSize(String localFile_tp) {
        File file = new File(localFile_tp);
        return file.length();
    }

    private boolean fileExists(String localFile_tp) {
        File file = new File(localFile_tp);
        return file.exists();
    }
}
