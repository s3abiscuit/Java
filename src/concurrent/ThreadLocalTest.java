package concurrent;

public class ThreadLocalTest {


    public static void set(ThreadLocal<Long> longLocal, ThreadLocal<String> stringLocal) {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public static long getLong(ThreadLocal<Long> longLocal) {
        return longLocal.get();
    }

    public static String getString(ThreadLocal<String> stringLocal) {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {

        test1();
        // test2(); // exception
//        test3();
    }

    private static void test1() throws InterruptedException {
        ThreadLocal<Long> longLocal = new ThreadLocal<>();
        ThreadLocal<String> stringLocal = new ThreadLocal<>();
        set(longLocal, stringLocal);
        System.out.println(getLong(longLocal));
        System.out.println(getString(stringLocal));

        Thread thread1 = new Thread() {
            public void run() {
                set(longLocal, stringLocal);
                System.out.println(getLong(longLocal));
                System.out.println(getString(stringLocal));
            }
        };
        thread1.start();
        thread1.join();

        System.out.println(getLong(longLocal));
        System.out.println(getString(stringLocal));
    }

    private static void test2() throws InterruptedException {
        ThreadLocal<Long> longLocal = new ThreadLocal<>();
        ThreadLocal<String> stringLocal = new ThreadLocal<>();
//        set(longLocal, stringLocal);
        System.out.println(getLong(longLocal));
        System.out.println(getString(stringLocal));

        Thread thread1 = new Thread() {
            public void run() {
                set(longLocal, stringLocal);
                System.out.println(getLong(longLocal));
                System.out.println(getString(stringLocal));
            }
        };
        thread1.start();
        thread1.join();

        System.out.println(getLong(longLocal));
        System.out.println(getString(stringLocal));
    }

    private static void test3() throws InterruptedException {
        ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
            @Override
            protected Long initialValue() {
                return Thread.currentThread().getId();
            }
        };
        ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return Thread.currentThread().getName();
            }
        };
        System.out.println(getLong(longLocal));
        System.out.println(getString(stringLocal));

        Thread thread1 = new Thread() {
            public void run() {
                System.out.println(getLong(longLocal));
                System.out.println(getString(stringLocal));
            }
        };
        thread1.start();
        thread1.join();

        System.out.println(getLong(longLocal));
        System.out.println(getString(stringLocal));
    }
}
