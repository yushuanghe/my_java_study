package j2se.core.thread.MulitiThreadDemo;


/**
 * 监视线程，检测其他线程是否已经运行完毕
 * 在MultiThreadGetFile（线程调度管理器）里定义一个全局静态boolean数组，在启动每个GetFileThread worker线程时，
 * 就将对应的值设为false，对应线程结束后就改为true，在检测线程里不停地检测是否数组全部值都为true，如果为true，
 * 则全部线程运行完毕，否则继续检测。
 * 等到所有的GetFileThread worker线程运行完毕之后，调用文件合并线程，合并下载的临时文件，删除临时文件
 * Created by yobdc on 2016/12/21.
 */
public class PoliceThread implements Runnable {

    int totalThread;
    String localFileAddress;
    String localFileAddress_tmp;

    public PoliceThread(int totalThread, String localFileAddress, String localFileAddress_tmp) {
        //总线程数
        this.totalThread = totalThread;
        //本地下载文件存放路径
        this.localFileAddress = localFileAddress;
        //正在下载的文件存放路径
        this.localFileAddress_tmp = localFileAddress_tmp;
    }

    @Override
    public void run() {
        boolean isRun = true;
        int allStop = 0;
        while (isRun) {
            allStop = 0;
            for (int i = 1; i <= totalThread; i++) {
                if (MultiThreadGetFile.checkList[i]) {
                    allStop++;
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //线程全部完成
            if (allStop == totalThread)
                isRun = false;
        }

        Thread thread = new Thread(new FileCombination(localFileAddress, localFileAddress_tmp));
        thread.start();
    }
}
