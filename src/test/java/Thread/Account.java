package Thread;
// http://www.iteye.com/topic/806990
// http://www.iteye.com/topic/808550

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 测试多线程
 */
public class Account {

    public final Log LOG = LogFactory.getLog(this.getClass());
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void add(int num) {
        balance = balance + num;
    }

    public void withdraw(int num) {
        balance = balance - num;
    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000);
        Thread a = new Thread(new AddThread(account, 20), "add");
        Thread b = new Thread(new WithdrawThread(account, 20), "withdraw");
        a.start();
        b.start();
        a.join();//join() 方法主要是让调用该方法的thread完成run方法里面的东西后， 再执行join()方法后面的代码。
        b.join();
        System.out.println(account.getBalance());
    }

    static class AddThread implements Runnable {
        Account account;
        int amount;

        public AddThread(Account account, int amount) {
            this.account = account;
            this.amount = amount;
        }

        public void run() {
            for (int i = 0; i < 200000; i++) {
                account.add(amount);
            }
        }
    }

    static class WithdrawThread implements Runnable {
        Account account;
        int amount;

        public WithdrawThread(Account account, int amount) {
            this.account = account;
            this.amount = amount;
        }

        public void run() {
            for (int i = 0; i < 100000; i++) {
                account.withdraw(amount);
            }
        }
    }
}