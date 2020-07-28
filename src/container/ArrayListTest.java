package container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        testToString();
        testListToArray();
        testTraverse();
    }

    private static void testToString(){
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

    private static void testListToArray(){
        // 数组转List
        String[] src = {"aaa", "bbb", "ccc", "ddd"};
        List<String> list = new ArrayList<>(Arrays.asList(src));
        list.add("eee");
        Object[] srtArray = list.toArray();

        // list转数组
        // String[] srtArray1 = (String[])list.toArray();  // does not work
        for(Object str : srtArray) System.out.println(str);
        String[] arr = list.toArray(new String[0]);  // preferred way
        for(String str : arr) System.out.println(str);
    }

    private static void testTraverse() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000000; i++)
            list.add(i);
        iteratorThroughRandomAccess(list);
        iteratorThroughListIterator(list);
        iteratorThroughIterator(list);
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
}
/*
iteratorThroughRandomAccess：78 ms
iteratorThroughListIterator：94 ms
iteratorThroughIterator：102 ms
iteratorThroughFor2：266 ms
 */
