package designpatterns.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        // 普通的 singleton
        testSingleton();
        // 生成多个对象的情况
        testSingletonWithoutSync();
        // 加同步避免生成多个对象
        // 但是多个线程同时获取的时候会造成阻塞
        testSingletonWithSync();

        testSingletonWithoutSyncAndEfficiency();
    }

    private static void testSingleton() {
        Singleton1 s1 = Singleton1.getInstance("hello");
        Singleton1 s2 = Singleton1.getInstance("hello");
        System.out.println(s1.getName());
        System.out.println(s1);
        System.out.println(s2);
    }

    private static void testSingletonWithoutSync() {
        try {
            Thread.sleep(1000);
            System.out.println("-----------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) new Thread(() -> System.out.println(Singleton2.getInstance())).start();
    }

    private static void testSingletonWithSync() {
        try {
            Thread.sleep(1000);
            System.out.println("-----------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) new Thread(() -> System.out.println(Singleton3.getInstance())).start();
    }

    private static void testSingletonWithoutSyncAndEfficiency() {
        try {
            Thread.sleep(1000);
            System.out.println("-----------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) new Thread(() -> System.out.println(Singleton4.getInstance())).start();
    }
}

class Singleton1 {
    private static Singleton1 instance;
    private final String name;

    private Singleton1(String str) {
        this.name = str;
    }

    public static Singleton1 getInstance(String para) {
        if (instance == null) instance = new Singleton1(para);
        return instance;
    }

    public String getName() {
        return name;
    }
}

class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton2();
        }
        return instance;
    }
}

class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance() {
        if (instance == null) instance = new Singleton3();
        return instance;
    }
}

class Singleton4 {
    private volatile static Singleton4 instance;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) instance = new Singleton4();
            }
        }
        return instance;
    }
}