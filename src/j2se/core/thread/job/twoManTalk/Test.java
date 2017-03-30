package j2se.core.thread.job.twoManTalk;


/**
 * Created by yobdc on 2016/12/21.
 */
public class Test {
    public static void main(String[] args) {
        WangSiTu wangSiTu = new WangSiTu();
        KongMing kongMing = new KongMing(wangSiTu);

        Thread wt = new Thread(wangSiTu, "王司徒");
        Thread kt = new Thread(kongMing, "孔明");

        wt.setDaemon(true);

        kt.start();
        wt.start();

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("标准结局！");
                            }
                        }
                )
        );
    }
}
