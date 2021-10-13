package concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ref: https://www.liaoxuefeng.com/wiki/1252599548343744/1306580767211554
// ref: https://juejin.cn/post/6844904023330390029
public class InterruptTest {
    /*
    interrupt() 的作用只是给线程设置一个标记
    sleep() join(), wait() 都会响应中断
    synchronized 不会响应中断
    1、java.lang.Thread#interrupt
    中断目标线程，给目标线程发一个中断信号，线程被打上中断标记。
    2、java.lang.Thread#isInterrupted()
    判断目标线程是否被中断，不会清除中断标记。
    3、java.lang.Thread#interrupted
    判断目标线程是否被中断，会清除中断标记。
     */
    public static void main(String[] args) throws InterruptedException {
        // 测试中断
        //test1();
        // sleep 响应中断并清除中断标记
        //test2();
        //test3();
        // lockInterruptibly() 响应中断
        test4();
    }

    private static void test1() throws InterruptedException {
        WorkThread1 workThread = new WorkThread1();
        workThread.start();
        // 让 wordThread工作 1000ms
        Thread.sleep(1);
        // 发出中断指令
        workThread.interrupt();
    }

    private static void test2() throws InterruptedException {
        WorkThread2 t = new WorkThread2();
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }

    private static void test3() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                WorkThread2 hello = new WorkThread2();
                hello.start(); // 启动hello线程
                try {
                    hello.join(); // 等待hello线程结束
                } catch (InterruptedException e) {
                    System.out.println("join 响应中断");
                }
                hello.interrupt();
            }
        });
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束
        System.out.println("end");
    }

    private static void test4() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted.");
                }
            }
        }, "child thread -1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }

    static class WorkThread1 extends Thread {
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + " hello!");
            }
        }
    }

    static class WorkThread2 extends Thread {
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + " hello!");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // sleep 响应中断后中断标志被清除
                    System.out.println("sleep 响应中断");
                    // break;
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}


