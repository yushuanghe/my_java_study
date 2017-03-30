package j2se.core.threadTest2.interview;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 当每个线程中指定的key相等时，这些相等key的线程应每隔一秒依次输出时间值（要用互斥），如果key不同，则并行执行（相互之间不互斥）
 * <p/>
 * Created by yushuanghe on 2017/03/18.
 */
//不能改动此TheThird类
public class TheThird extends Thread {

    private TestDo_2 testDo;
    private String key;
    private String value;

    public TheThird(String key, String key2, String value) {
        this.testDo = TestDo_2.getInstance();
        /**
         * 常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
         以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果
         */
        this.key = key + key2;
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException {
        TheThird a = new TheThird("1", "", "1");
        TheThird b = new TheThird("1", "", "2");
        TheThird c = new TheThird("3", "", "3");
        TheThird d = new TheThird("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    public void run() {
        testDo.doSome(key, value);
    }
}

class TestDo_2 {

    private TestDo_2() {
    }

    private static TestDo_2 _instance = new TestDo_2();

    public static TestDo_2 getInstance() {
        return _instance;
    }

    private List<Object> keys = new CopyOnWriteArrayList<>();

    public void doSome(Object key, String value) {
        Object o = key;
        if (!keys.contains(o)) {
            keys.add(o);
        } else {
            for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
                Object tmp = iterator.next();
                if (tmp.equals(o)) {
                    o = tmp;
                }
            }
        }

        synchronized (o) {
            // 以大括号内的是需要局部同步的代码，不能改动!
            {
                try {
                    Thread.sleep(1000);
                    System.out.println(key + ":" + value + ":"
                            + (System.currentTimeMillis() / 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
