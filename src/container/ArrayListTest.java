package container;

import java.lang.reflect.Field;
import java.util.*;

public class ArrayListTest {
    private static final ArrayList<String> listWithSize9 = new ArrayList<>(Arrays.asList("1", "2",
            "3", "4", "5", "6", "7", "8", "9"));
    private static final ArrayList<String> listWithSize11 = new ArrayList<>(Arrays.asList("1", "2",
            "3", "4", "5", "6", "7", "8", "9", "10", "11"));


    public static void main(String[] args) {
//        testConstructor();
//        testResize();
//
//        testTraverse();
//        testToString();
//        testListToArray();

        testModCount();
    }

    private static void testModCount() {
        ArrayList<String> list = new ArrayList<String>();
        // CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        Iterator<String> iterator = list.iterator();
        try {
            while(iterator.hasNext()){
                String str = iterator.next();
                list.remove(str);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException");
        }
    }

    private static void testConstructor() {
        System.out.println("-------------------构造方法----------------------");
        constructorWithoutParam();
        constructorWithInitialCapacity();
        constructorWithCollection();
    }

    private static void testResize() {
        System.out.println("-------------------resize----------------------");

        // 第一种扩容方式 *1.5
        ArrayList<String> list1 = new ArrayList<>(listWithSize9);
        System.out.println("扩容前大小" + getArrayListCapacity(list1));
        list1.add("0");
        System.out.println("1.5倍扩容后的大小" + getArrayListCapacity(list1));

        // 第二种扩容方式 +n
        ArrayList<String> list2 = new ArrayList<>(listWithSize9);
        System.out.println("扩容前大小" + getArrayListCapacity(list2));
        list2.addAll(listWithSize11);
        System.out.println("+n扩容后的大小" + getArrayListCapacity(list2));
    }

    private static void constructorWithoutParam() {
        // 空构造方法初始化并添加一个元素, 结果是 elementData 大小为 10,
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("0");
        System.out.println("无参造方法初始化并添加1个元素后 elementData 数组大小为: " + getArrayListCapacity(list1));

        // 空构造方法并添加 n 个元素, 如果 n <=10 结果是 elementData 大小为 10,
        ArrayList<String> list2 = new ArrayList<>();
        list2.addAll(listWithSize9);
        System.out.println("无参构造方法初始化并添加9个元素后 elementData 数组大小为: " + getArrayListCapacity(list2));

        // 如果 n > 10 elementData 大小为 n
        ArrayList<String> list3 = new ArrayList<>();
        list3.addAll(listWithSize11);
        System.out.println("无参构造方法初始化并添加11个元素后 elementData 数组大小为: " + getArrayListCapacity(list3));
    }

    private static void constructorWithInitialCapacity() {
        // initialCapacity的构造方法初始化 elementData 大小为 initialCapacity
        ArrayList<String> list1 = new ArrayList<>(9);
        System.out.println("initialCapacity 设置为2的构造方法 elementData 数组大小为: " + getArrayListCapacity(list1));

        ArrayList<String> list2 = new ArrayList<>(11);
        System.out.println("initialCapacity 设置为11的构造方法 elementData 数组大小为: " + getArrayListCapacity(list2));
    }

    private static void constructorWithCollection() {
        // 使用传入 collection 的构造方法 结果是 elementData 大小为 collection 的大小
        ArrayList<String> list1 = new ArrayList<>(listWithSize9);
        System.out.println("传入 collection 大小为9的构造方法 elementData 数组大小为: " + getArrayListCapacity(list1));

        ArrayList<String> list2 = new ArrayList<>(listWithSize11);
        System.out.println("传入 collection 大小为11的构造方法 elementData 数组大小为: " + getArrayListCapacity(list2));
    }

    // https://www.cnblogs.com/hs2018/p/10449970.html
    private static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            //获取 elementData 字段
            Field field = arrayListClass.getDeclaredField("elementData");
            //开始访问权限
            field.setAccessible(true);
            //把示例传入get，获取实例字段elementData的值
            Object[] objects = (Object[]) field.get(arrayList);
            //返回当前ArrayList实例的容量值
            return objects.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void testToString() {
        System.out.println("-------------------toString----------------------");
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        for (String str : list) {
            System.out.println(str);
        }
        // ArrayList->AbstractList->AbstractCollection.toString()
        System.out.println(list);
    }

    private static void testListToArray() {
        System.out.println("-------------------List to Array----------------------");
        // 数组转List
        String[] src = {"aaa", "bbb", "ccc", "ddd"};
        List<String> list = new ArrayList<>(Arrays.asList(src));
        list.add("eee");

        // toArray()方法返回的是Object[] 类型
        Object[] srtArray = list.toArray();

        // list转数组
        // String[] srtArray1 = (String[])list.toArray();  // does not work
//        for (Object str : srtArray) System.out.println(str);
        System.out.println(Arrays.toString(srtArray));

        // toArray(String[] args)由两种情况
        // 1. args的长度小于list的元素的size, 这个时候返回一个新创建的数组
        // 2. args的长度不小于list的元素的size, 这个时候把list中的元素复制到args数组中
        String[] arr = list.toArray(new String[0]);  // preferred way
        System.out.println(Arrays.toString(arr));
    }

    private static void testTraverse() {
        System.out.println("-------------------iterate----------------------");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++)
            list.add(i);
        // RandomAccess 最快
        iteratorThroughRandomAccess(list);
        // iterator 是 RandomAccess 的包装
        iteratorThroughListIterator(list);
        iteratorThroughIterator(list);
        // For2 又是 iterator 的包装
        iteratorThroughForEach(list);
        iteratorThroughFor2(list);
    }

    private static void iteratorThroughRandomAccess(List<Integer> list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughRandomAccess：" + interval + " ms");
    }


    private static void iteratorThroughListIterator(List<Integer> list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (Iterator iter = list.listIterator(); iter.hasNext(); ) {
            iter.next();
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughListIterator：" + interval + " ms");
    }

    private static void iteratorThroughIterator(List<Integer> list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            iter.next();
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughIterator：" + interval + " ms");
    }

    private static void iteratorThroughFor2(List<Integer> list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (Integer itg : list)
            ;
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughFor2：" + interval + " ms");
    }

    private static void iteratorThroughForEach(List<Integer> list) {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        list.forEach(item -> {
        });
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughForEach：" + interval + " ms");
    }
}
/* output
-------------------构造方法----------------------
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by container.ArrayListTest (file:/D:/dev/javaworkspace/JavaDemo-master/out/production/JavaDemo-master/) to field java.util.ArrayList.elementData
WARNING: Please consider reporting this to the maintainers of container.ArrayListTest
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
无参造方法初始化并添加1个元素后 elementData 数组大小为: 10
无参构造方法初始化并添加9个元素后 elementData 数组大小为: 10
无参构造方法初始化并添加11个元素后 elementData 数组大小为: 11
initialCapacity 设置为2的构造方法 elementData 数组大小为: 9
initialCapacity 设置为11的构造方法 elementData 数组大小为: 11
传入 collection 大小为9的构造方法 elementData 数组大小为: 9
传入 collection 大小为11的构造方法 elementData 数组大小为: 11
-------------------resize----------------------
扩容前大小9
1.5倍扩容后的大小13
扩容前大小9
+n扩容后的大小20
-------------------iterate----------------------
iteratorThroughRandomAccess：67 ms
iteratorThroughListIterator：100 ms
iteratorThroughIterator：97 ms
iteratorThroughForEach：189 ms
iteratorThroughFor2：250 ms
-------------------toString----------------------
aaa
bbb
ccc
ddd
[aaa, bbb, ccc, ddd]
-------------------List to Array----------------------
[aaa, bbb, ccc, ddd, eee]
[aaa, bbb, ccc, ddd, eee]
 */


