package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    public static final Thread thread = new Thread() {
        @Override
        public void run() {
            System.out.println("Thread");
        }
    };
    public static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Runnable");
        }
    };
    public static final Callable<String> callable = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return "Callable";
        }
    };

    public static void main(String[] args) {

//        threadTest();
//        executorTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(example::after);

        executorService.execute(example::before);

    }

    private static void executorTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

    private static void threadTest() {
        System.out.println("before");
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(runnable).start();
        System.out.println("after");

//        try {
//            FutureTask<String> ft = new FutureTask<>(callable);
//            new Thread(ft).start();
//            System.out.println(ft.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
    }

}

class WaitNotifyExample {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }
}
