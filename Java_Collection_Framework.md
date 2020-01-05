# Java Collection Framework

- Collection
  - List
    - ArrayList
    - LinkedList
    - Vector
      - Stack
  - Queue -> Deque
    - LinkedList
  - Set
    - HashSet, TreeSet, LinkedHashSet
- Map

## 记住

1. Arrays.asList(T... t)返回的是固定容量的ArrayList
2. LinkedList遍历使用listIterator效率高, 因为会记住上一个遍历的位置
3. Iterator的remove()方法使用前必须先调用next()方法
