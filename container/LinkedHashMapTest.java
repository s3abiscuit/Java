package container;

import java.util.*;
public class LinkedHashMapTest {
    public static void main(String[] args) {
        System.out.println("---AccessOrder set as default, false---");
        testAccessOrder(false);
        System.out.println();
        System.out.println("---AccessOrder set as true---");
        testAccessOrder(true);
        System.out.println();
        System.out.println("---test custom LRU---");
        testLRU();
    }

    static void testAccessOrder(boolean flag) {
        Map<String, String> map = new LinkedHashMap<>(16, 0.75f, flag);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        //new add
        map.get("1");
        map.get("2");

        for (String name : map.values()) {
            System.out.print(name);
        }
    }

    static void testLRU(){
        LRUCache<String,Object> cache = new LRUCache<>(3);
        cache.put("a","abstract");
        cache.put("b","basic");
        cache.put("c","call");
        cache.get("a");
        cache.put("d","滴滴滴");
        // 最终的访问的顺序为 b, c, a, d
        // 上限为3 所以b被删除
        System.out.println(cache);
    }

    private static class LRUCache<K, V> extends LinkedHashMap<K, V> {

        private final int maxEntries;

        public LRUCache(int maxEntries) {
            super(16, 0.75f, true);
            this.maxEntries = maxEntries;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxEntries;
        }
    }

}
