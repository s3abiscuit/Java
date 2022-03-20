package concurrent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;
// https://www.cnblogs.com/pcheng/p/13540619.html
public class ThreadPoolTest {
    public static void main(String[] args) {
//        createCachedThreadPool();
//        createFixedThreadPool();
        createScheduledThreadPool();
//        createThreadPool();
    }

    /*
    2021/09/22 16:35:09 pool-1-thread-7 6
    2021/09/22 16:35:09 pool-1-thread-1 0
    2021/09/22 16:35:09 pool-1-thread-3 2
    2021/09/22 16:35:09 pool-1-thread-4 3
    2021/09/22 16:35:09 pool-1-thread-2 1
    2021/09/22 16:35:09 pool-1-thread-5 4
    2021/09/22 16:35:09 pool-1-thread-8 7
    2021/09/22 16:35:09 pool-1-thread-6 5
    2021/09/22 16:35:09 pool-1-thread-9 8
    2021/09/22 16:35:09 pool-1-thread-10 9
     */
    private static void createCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(now() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /*
    2021/09/22 16:30:37 pool-1-thread-3 2
    2021/09/22 16:30:37 pool-1-thread-2 1
    2021/09/22 16:30:37 pool-1-thread-1 0
    2021/09/22 16:30:39 pool-1-thread-2 3
    2021/09/22 16:30:39 pool-1-thread-1 4
    2021/09/22 16:30:39 pool-1-thread-3 5
    2021/09/22 16:30:41 pool-1-thread-2 6
    2021/09/22 16:30:41 pool-1-thread-1 7
    2021/09/22 16:30:41 pool-1-thread-3 8
    2021/09/22 16:30:43 pool-1-thread-2 9
     */
    private static void createFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(now() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    /*
    2021/09/22 17:18:35 提交任务
    2021/09/22 17:18:38 pool-1-thread-3 2
    2021/09/22 17:18:38 pool-1-thread-2 1
    2021/09/22 17:18:38 pool-1-thread-1 0
    2021/09/22 17:18:40 pool-1-thread-3 3
    2021/09/22 17:18:40 pool-1-thread-2 4
    2021/09/22 17:18:40 pool-1-thread-1 5
    2021/09/22 17:18:42 pool-1-thread-3 6
    2021/09/22 17:18:42 pool-1-thread-1 7
    2021/09/22 17:18:42 pool-1-thread-2 8
    2021/09/22 17:18:44 pool-1-thread-3 9
     */
    private static void createScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        System.out.println(now() + " 提交任务");
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.schedule(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(now() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 3, TimeUnit.SECONDS);
        }
    }

    /*
    2021/09/22 17:01:10 pool-1-thread-4 8
    2021/09/22 17:01:10 pool-1-thread-1 0
    2021/09/22 17:01:10 pool-1-thread-2 1
    2021/09/22 17:01:10 pool-1-thread-3 7
    2021/09/22 17:01:10 pool-1-thread-5 9
    2021/09/22 17:01:12 pool-1-thread-2 3
    2021/09/22 17:01:12 pool-1-thread-4 2
    2021/09/22 17:01:12 pool-1-thread-3 4
    2021/09/22 17:01:12 pool-1-thread-1 5
    2021/09/22 17:01:12 pool-1-thread-5 6
     */
    /*
    执行流程:
    创建 0, 1 线程并进入 corePool
    创建 2, 3, 4, 5, 6 进入 ArrayBlockingQueue
    继续创建 7, 8, 9 线程
    由输出可以看出 先执行了 8, 0, 1, 7, 9
    然后服用线程池种的 5 个线程并执行了队列中的线程 2, 3, 4, 5, 6
     */
    private static void createThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(2, 10,
                1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5, true),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(now() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static String now() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
