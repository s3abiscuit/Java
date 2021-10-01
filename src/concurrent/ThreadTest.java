package concurrent;

import java.util.concurrent.*;

public class ThreadTest {

    public static final Thread thread = new Thread() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "Thread");
        }
    };
    public static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "Runnable");
        }
    };
    public static final Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return Thread.currentThread() + "Callable";
        }
    };

    public static void main(String[] args) {

        threadTest();
        executorTest();

    }

    private static void threadTest() {
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(runnable).start();

        try {
            FutureTask<String> ft = new FutureTask<>(callable);
            new Thread(ft).start();
            System.out.println(ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // 使用线程池可以复用创建的线程
    // 例子中执行10个线程 但是只创建了5个
    private static void executorTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

}

/* output
Thread[Thread-0,5,main]Thread
Thread[Thread-1,5,main]Runnable
Thread[Thread-2,5,main]Callable
Thread[pool-1-thread-1,5,main]Runnable
Thread[pool-1-thread-2,5,main]Runnable
Thread[pool-1-thread-3,5,main]Runnable
Thread[pool-1-thread-3,5,main]Runnable
Thread[pool-1-thread-4,5,main]Runnable
Thread[pool-1-thread-2,5,main]Runnable
Thread[pool-1-thread-4,5,main]Runnable
Thread[pool-1-thread-1,5,main]Runnable
Thread[pool-1-thread-3,5,main]Runnable
Thread[pool-1-thread-5,5,main]Runnable
 */

