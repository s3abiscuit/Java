public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
// 1. 继承结构 AbstractList, List, AbstractCollection,
// 辅助函数 Arrays.copyOf()
// elementData = Arrays.copyOf(elementData, size, Object[].class);
// elementData = Arrays.copyOf(elementData, size);

// 辅助函数 System.arraycopy(Object[] from, index1, Object[] to, index2, num)
// System.arraycopy(elementData, index, elementData, index + 1, size - index); 添加一个元素
// System.arraycopy(elementData, index+1, elementData, index, numMoved); 删除一个元素
// System.arraycopy(a, 0, elementData, size, numNew); 将array a追加到elementData中
}
