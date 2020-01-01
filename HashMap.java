public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {
    // 关键变量
    // capacity: (1. Default 16, 2. >=initialCapacity, 3. >= map.size())
    // loadFactor, threshold
    // table, keySet, entrySet, Values
    
    // 常量
    // 构造方法
    // 遍历 EntrySet, EntrySetIterator, KeySet, KeySetIterator, Values, ValueIterator
    // 增删改查 putVal(), removeNode(), getNode(), replace()也是getNode
    
    // 位操作, clone, TreeNode

    private static final long serialVersionUID = 362498820763181265L;
    
    
}
