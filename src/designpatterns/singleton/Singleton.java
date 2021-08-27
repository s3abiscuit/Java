package designpatterns.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        testSingleton(); // 普通的 singleton

        testSingletonWithoutSync(); // 可能会生成多个对象的情况

        testSingletonWithSync(); // 加同步避免生成多个对象

        testSingletonWithoutSyncAndEfficiency();
    }

    private static void testSingletonWithSync() {
        try {
            Thread.sleep(1000);
            System.out.println("-----------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) new Thread(() -> System.out.println(Singleton2.getInstance())).start();
    }

    private static void testSingletonWithoutSync() {
        try {
            Thread.sleep(1000);
            System.out.println("-----------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) new Thread(() -> System.out.println(Singleton1.getInstance())).start();
    }

    private static void testSingletonWithoutSyncAndEfficiency() {
        try {
            Thread.sleep(1000);
            System.out.println("-----------------------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) new Thread(() -> System.out.println(Singleton3.getInstance())).start();
    }

    private static void testSingleton() {
        Singleton s1 = Singleton.getInstance("hello");
        Singleton s2 = Singleton.getInstance("hello");
        System.out.println(s1.getName());
        System.out.println(s1);
        System.out.println(s2);
    }
}

class Singleton {
    private static Singleton instance;
    private final String name;

    private Singleton(String str) {
        this.name = str;
    }

    public static Singleton getInstance(String para) {
        if (instance == null) instance = new Singleton(para);
        return instance;
    }

    public String getName() {
        return name;
    }
}

class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton1();
        }
        return instance;
    }
}

class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
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
    private volatile static Singleton3 instance;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) instance = new Singleton3();
            }
        }
        return instance;
    }
}