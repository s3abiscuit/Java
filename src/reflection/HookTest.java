//package reflection;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//
///*
//  对于任何一个类，我们都能够知道这个类有哪些方法和属性。对于任何一个对象，
//  我们都能够对它的方法和属性进行调用。
//  我们把这种动态获取对象信息和调用对象方法的功能称之为 反射机制
// */
//
///**
// * 所谓反射其实是获取类的字节码文件，
// * 也就是.class文件，那么我们就可以通过Class这个对象进行获取
// */
//public class HookTest {
//
//    public static void main(String[] args) {
//        //第一种方式
//        LoopTest loopTest = new LoopTest();
//        Class aClass = loopTest.getClass();
//        System.out.println(aClass.getName());
//        //第二种方式
//        Class<LoopTest> aclass2 = LoopTest.class;
//        System.out.println(aclass2.getName());
//        //第三种方式
//        try {
//            Class aclass3 = Class.forName("LoopTest");
//            System.out.println(aclass3.getName());
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//
//        /**
//         * 那么这3中方式我们一般选用哪种方式呢？第一种已经创建了对象，那么这个时候就不需要去进行反射了，
//         * 显得有点多此一举。第二种需要导入类的包，依赖性太强。所以我们一般选中第三种方式。
//         */
//
//        /**
//         * 三、通过反射获取类的构造方法、方法以及属性
//         */
//
//        /**
//         * 1、获取构造方法
//         */
//        Constructor[] constructors = aclass2.getConstructors();
//        System.out.println("获取构造方法:");
//        for (Constructor constructor1 : constructors) {
//            System.out.println(constructor1.getName());
//        }
//        System.out.println("获取类的属性:");
//        Field[] fields = aclass2.getFields();
//        //88888
//        System.out.println("获取类的方法:");
//        Method[] methods = aclass2.getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//
//        /*
//          反射执行方法
//         */
//
//        try {
//            Class<?> aclass4 = Class.forName("LoopTest");
//            Method method = aclass4.getDeclaredMethod("method", String.class);
//            Constructor<?> ct = aclass4.getConstructor(null);
//            Object obj = ct.newInstance(null);
//            method.invoke(obj, "反射调用");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        /**
//         * Android中使用场景：其实很多用过的EventBus 、Retrofit 都有涉猎 可以去看看源码
//         */
//    }
//
//}
//
