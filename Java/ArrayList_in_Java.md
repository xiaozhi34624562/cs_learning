# ArrayList
继承了AbstractList，RandomAccess，Cloneable，Serializable。底层是一个Object数组。
### 构造方法
- public ArrayList(int capacity)此时的ArrayList大小为capacity
- public ArrayList()此时的public ArrayList()大小为10
- public ArrayList(Collection<? extends E> c)此时的大小为c.size()
### 方法
- public void trimToSize()使得ArrayList的大小等于本身的size
- public void ensureCapacity(int minCapacity) 此时数组的长度 Math.max(current * 1.5, minCapacity)
- public int size() 数组里面元素的个数
- public boolean isEmpty() 数组是否为空
- public boolean contains(Object e) 判断数组里是否包含此元素
- public int indexOf(Object e) 顺序循环数组找到e的索引
- public int lastIndexOf(Object e) 逆序循环数组找到e的索引
- public Object clone() 数组的shallow copy（元素没有被cloned）, 指向同一个元素
- **如果是deepcopy，指向不同的元素，两个数组完全独立**
- public Object[] toArray() 生成一个新的array，这个array是独立于ArrayList
- public <T> T[] toArray(T[] a)
- public E get(int index) 返回index对应的元素
- public E set(int index, E e) 返回index开始对应的元素
- public boolean add(E e) 数组的末尾增加一个元素
- public void add(int index, E e) 指定位置插入e，index及后面元素通过复制，向后移动一位
- public E remove(int index) 删除index对应的元素，index之后的元素通过复制，向前移动一位
- public void clear() 通过Arrays.fill(data, 0, size, null)清除数组
- public boolean addAll(Collection<? extends E> c) 调用addAll(size, c)
- public boolean addAll(int index, Collection<? extends E> c) 首先判断数组是否需要扩容，然后复制原先数组到新的数组，最后在新的数组里面插入c
- protected void removeRange(int fromIndex, int toIndex) 复制新的数组，去除掉range之间的部分 System.arraycopy(data, toIndex, data, fromIndex, size - toIndex)
- private void checkBoundInclusive(int index) 检测是否在 if (index > size)
- private void checkBoundExclusive(int index) 检测是否在 if (index >= size)
- boolean removeAllInternal(Collection<?> c) 循环遍历删除数组里面含有的c
- boolean retainAllInternal(Collection<?> c) 留下两个数组的公共元素
