package concurrent;

// ref: https://www.liaoxuefeng.com/wiki/1252599548343744/1306580767211554
public class InterruptTest {
    /*
    interrupt() 的作用只是给线程设置一个标记
    sleep() join(), wait() 都会响应中断
    synchronized 不会响应中断
     */
    public static void main(String[] args) throws InterruptedException {
        //test1();  // 测试中断
        //test2(); // sleep 响应中断并清除中断标记
        test3();
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


