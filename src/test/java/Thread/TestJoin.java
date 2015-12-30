package Thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by wangchongbei on 15-12-30.
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new JoinTesterA());
        Thread t2 = new Thread(new JoinTesterB());
        t1.start();
        t1.join(); // wait t1 to be finished
        t2.start();
    }
}

class JoinTesterA implements Runnable {

    private int counter;

    public void run() {
        while (counter <= 10000) {
            System.out.println("Counter = " + counter + "----" + Thread.currentThread().getName());
            counter++;
        }
    }
}

class JoinTesterB implements Runnable {

    private int i;

    public void run() {
        while (i <= 10000) {
            System.out.println("i = " + i + "----" + Thread.currentThread().getName());
            i++;
        }
    }
}