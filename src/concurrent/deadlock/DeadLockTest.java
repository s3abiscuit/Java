package concurrent.deadlock;

public class DeadLockTest {
    public static void main(String[] args) {

        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName() + "username = ");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按lock1->lock2代码顺序执行了");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "username = ");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("按lock1->lock2代码顺序执行了");
                    }
                }
            }
        }).start();
    }
}
