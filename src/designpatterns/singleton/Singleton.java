package singleton;

// 多线程的时候会生成多个对象
class Singleton1 {
    private static Singleton1 uniqueInstance;

    private Singleton1(){}

    public static Singleton1 getInstance(){
        if(uniqueInstance == null) 
				uniqueInstance = new Singleton1();
        return uniqueInstance;
    }
}

// 加锁效率低下
// 因为每次调用getInstance都会加锁, 其他线程都要等待
class Singleton2 {
    private static Singleton2 uniqueInstance;

    private Singleton2(){}

    public static synchronized Singleton2 getInstance(){
        if(uniqueInstance == null) 
				uniqueInstance = new Singleton2();
        return uniqueInstance;
    }
}

// double check
// 只有第一次创建instance的时候才会加锁
// 创建完成instance后, 多个线程都能获取instance
class Singleton3 {
    private volatile static Singleton3 uniqueInstance;

    private Singleton3(){}

    public static Singleton3 getInstance(){
        if(uniqueInstance == null) {
            synchronized (Singleton3.class) {
                if (uniqueInstance == null)
                    uniqueInstance = new Singleton3();
            }
        }
        return uniqueInstance;
    }
}