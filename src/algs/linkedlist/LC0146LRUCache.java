//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
//
//
//
// 实现 LRUCache 类：
//
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
//
//
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
// 示例：
//
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
//
//
//
//
// 提示：
//
//
// 1 <= capacity <= 3000
// 0 <= key <= 10000
// 0 <= value <= 10⁵
// 最多调用 2 * 10⁵ 次 get 和 put
//
// Related Topics 设计 哈希表 链表 双向链表 👍 1620 👎 0
package algs.linkedlist;

import java.util.HashMap;

public class LC0146LRUCache {
    public static void main(String[] args) {
        /* 缓存容量为 2 */
        LRUCache cache = new LRUCache(2);
        // 你可以把 cache 理解成一个队列
        // 假设左边是队头，右边是队尾
        // 最近使用的排在队头，久未使用的排在队尾
        // 圆括号表示键值对 (key, val)

        cache.put(1, 1);
        // cache = [(1, 1)]
        cache.put(2, 2);
        // cache = [(2, 2), (1, 1)]
        cache.get(1);       // 返回 1
        // cache = [(1, 1), (2, 2)]
        // 解释：因为最近访问了键 1，所以提前至队头
        // 返回键 1 对应的值 1
        cache.put(3, 3);
        // cache = [(3, 3), (1, 1)]
        // 解释：缓存容量已满，需要删除内容空出位置
        // 优先删除久未使用的数据，也就是队尾的数据
        // 然后把新的数据插入队头
        cache.get(2);       // 返回 -1 (未找到)
        // cache = [(3, 3), (1, 1)]
        // 解释：cache 中不存在键为 2 的数据
        cache.put(1, 4);
        // cache = [(1, 4), (3, 3)]
        // 解释：键 1 已存在，把原始值 1 覆盖为 4
        // 不要忘了也要将键值对提前到队头
    }

    static class LRUCache {

        class Node {
            public int key, val;
            public Node next, prev;

            public Node(int k, int v) {
                this.key = k;
                this.val = v;
            }
        }

        class DoubleList {

            private Node head, tail;
            private int size;

            public void addFirst(Node node) {
                if (head == null) {
                    head = tail = node;
                } else {
                    Node n = head;
                    n.prev = node;
                    node.next = n;
                    head = node;
                }
                size++;
            }

            public void remove(Node node) {
                if (head == node && tail == node) {
                    head = null;
                    tail = null;
                } else if (tail == node) {
                    node.prev.next = null;
                    tail = node.prev;
                } else if (head == node) {
                    node.next.prev = null;
                    head = node.next;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                size--;
            }

            public Node removeLast() {
                Node node = tail;
                remove(tail);
                return node;
            }

            public int size() {
                return size;
            }
        }

        private HashMap<Integer, Node> map;
        private DoubleList cache;
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            int val = map.get(key).val;
            put(key, val);
            return val;
        }

        public void put(int key, int value) {
            Node x = new Node(key, value);

            if (map.containsKey(key)) {
                cache.remove(map.get(key));
                cache.addFirst(x);
                map.put(key, x);
            } else {
                if (cap == cache.size()) {
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                cache.addFirst(x);
                map.put(key, x);
            }
        }
    }
}