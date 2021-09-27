TLDR;

- Collection
    - List->ArrayList/Vector/LinkedList/Stack
    - Set->HashSet/LinkedHashSet/TreeSet
    - Queue->PriorityQueue
    - Deque->LinkedList/ArrayDeque
- Map->HashMap/LinkedHashMap/TreeMap

## Collection 接口

**增删查**, 没有涉及到顺序, 顺序由 list 扩展完成

add
- boolean add(E e);
- boolean addAll(Collection<? extends E> c);

delete
- boolean remove(Object o);
- boolean removeAll(Collection<?> c);
- removeIf(Predicate<? super E> filter);
- void clear();

query
- Iterator<E> iterator();
- default Spliterator<E> spliterator()
- boolean contains(Object o);
- boolean containsAll(Collection<?> c);
- boolean retainAll(Collection<?> c);
- Object[] toArray();
- <T> T[] toArray(T[] a);
- default <T> T[] toArray(IntFunction<T[]> generator)
- boolean isEmpty();
- int size();

## Set 接口基本和Collection一样

扩展了两个方法

- static <E> Set<E> copyOf(Collection<? extends E> coll)
- static <E> Set<E> of(E... elements)

## List 接口 扩展了 Collection 接口添加了index相关操作

add
- void add(int index, E element);  // index是list独有
- boolean addAll(int index, Collection<? extends E> c);

delete
- E remove(int index);
- boolean retainAll(Collection<?> c);

modify
- default void sort(Comparator<? super E> c);
- E set(int index, E element);

query
- E get(int index);
- int indexOf(Object o);
- ListIterator<E> listIterator();
- ListIterator<E> listIterator(int index);
- Object[] toArray();
- <T> T[] toArray(T[] a);

扩展了两个方法

- static <E> Set<E> copyOf(Collection<? extends E> coll)
- static <E> Set<E> of(E... elements)

**ArrayList**

`public class ArrayList<E> extends AbstractList<E>
implements List<E>, RandomAccess, Cloneable, java.io.Serializable
`

**LinkedList**

`public class LinkedList<E>
extends AbstractSequentialList<E>
implements List<E>, Deque<E>, Cloneable, java.io.Serializable
`

**Vector**

`public class Vector<E>
extends AbstractList<E>
implements List<E>, RandomAccess, Cloneable, java.io.Serializable
`

## Deque 扩展了 Collection 添加了头尾的操作

**as stack**

- E peek();
- E pop();
- void push(E e);


**as queue**

throw exception

- boolean add(E e);
- E remove();
- E element();

return null

- boolean offer(E e);
- E poll();
- E peek();

**as deque**

throw exception

- boolean addFirst/Last(E e);
- E removeFirst/Last();
- E getFirst/Last();

return null

- boolean offerFirst/Last(E e);
- E pollFirst/Last();
- E peekFirst/Last();

**LinkedList**

底层实现方式是 双向链表, 有 head 和 tail 指针

**ArrayDeque**

底层实现方式是循环数组, 有 head 和 tail 指针

## Map

增
- V put(K key, V value);
- void putAll(Map<? extends K, ? extends V> m);
- default V putIfAbsent(K key, V value) {

删
- V remove(Object key);
- default boolean remove(Object key, Object value)
- clear()

改
- default V replace(K key, V value) {
- default boolean replace(K key, V oldValue, V newValue)
- default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)

查
- Set<Map.Entry<K, V>> entrySet();
- Set<K> keySet();
- Collection<V> values();
- V get(Object key);
- default V getOrDefault(Object key, V defaultValue)
