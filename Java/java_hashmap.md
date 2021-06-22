# HashMap

## HashMap, HashTable, LinkedHashMap, TreeMap

- HashMap: 它的键根据hashCode值存储数据, 大多数情况下可以直接定位到它的值, 因而具有很快的访问速度, 但遍历顺序却是不确定的. HashMap最多允许一条记录的键为null, 允许多条记录的值为null. 线程不安全的, 如果需要线程安全, 可以用Collections的synchronizedMap方法使HashMap具有线程安全的能力, 或者使用ConcurrentHashMap.
- LinkedHashMap: HashMap的一个子类, 保存了记录的插入顺序, 在用iterator遍历LinkedHashMap时, 先得到的记录肯定是先插入的, 也可以在构造时带参数, 按照访问次序排序.
- TreeMap: 实现了AbstractMap和SortedMap接口, 能够把它保存的记录根据键排序, 默认的是按键值的升序排序, 也可以指定排序的比较器, 当用iterator遍历TreeMap时, 得到的记录是排序过的. key必须实现Comparable接口 后者在构造TreeMap传入自定义的Comparator, 否则ClassCastException. 
- HashTable: 继承自Map和Dictionary类, 线程安全的, 任意时间只有一个线程能写HashTable, 并发行不如ConcurrentHashMap(引入了分段锁). 不需要线程安全的场合可以用HashMap替换，需要线程安全的场合可以用ConcurrentHashMap替换。

## HashMap的内部实现

#### 存储结构

- HashMap是一个Node的数组, Node[] table;
- 哈希表为解决冲突, 可以采用开放地址法和链地址法等来解决问题, HashMap采用了链地址法.
  - `threshold`: HashMap所能容纳的最大数据量, length*loadFactor. length的默认值是16. threshold超过了最大值, 就重新resize, 扩容后的HashMap容量是之前容量的两倍. 
  - `loadFactor`: 默认是0.75. 如果内存空间很多, 对时间效率要求很高, 可以降低负载因子的值; 如果内存紧张, 对时间效率要求不高吗可以增加负载因子的值, 这个值可以大于1.
  - `size`: HashMap中实际存在的键值对数量. length 是table的长度. 
  - `modCount`: 记录HashMap内部结构发生变化的次数, 主要用于迭代的快速失败. 内部结构发生变化指的是结构发生变化，例如put新键值对，但是某个key对应的value值被覆盖不属于结构变化
- 在HashMap中, 哈希桶数组table的长度length大小必须是2的n次方(不同于常规的使用素数, HashTable的初始化桶大小为11, 设计为素数, 扩容后不能保证还是素数). HashMap的这种设计, 主要是为了在取值和扩容时左右花, 同时未来减少冲突, HashMap定位哈希桶索引位置时, 也加入了高位参与运算的过程. 

#### 实现方法

- 确定哈希桶数组索引位置
  - `hash = (h = k.hasHash()) ^ (h >>> 16)); (n - 1) & hash `取索引位置
    - 首先取得key的h值, 然后取h的高16位值, 再做异或计算, 这样高16位与低16位都参与了运算
    - `n - 1`: 和hash做与运算, 相当于对hash取模(n-1)
- HashMap的put方法
  1. 判断键值对数组table[i]是否为空或为null，否则执行resize()进行扩容； 
  2. 根据键值key计算hash值得到插入的数组索引i，如果table[i]==null，直接新建节点添加，转向6; 如果table[i]不为空，转向3
  3. 判断table[i]的首个元素是否和key一样，如果相同直接覆盖value，否则转向4，这里的相同指的是hashCode以及equals；
  4. 判断table[i] 是否为treeNode，即table[i] 是否是红黑树，如果是红黑树，则直接在树中插入键值对，否则转向5
  5. 遍历table[i]，判断链表长度是否大于8，大于8的话把链表转换为红黑树，在红黑树中执行插入操作，否则进行链表的插入操作；遍历过程中若发现key已经存在直接覆盖value即可；
  6. 插入成功后，判断实际存在的键值对数量size是否超多了最大容量threshold，如果超过，进行扩容
- 扩容机制
  - jdk1.7中newTable[i]的引用赋值给了e.next, 也就是使用了单链表的头插入方式∂
- JDK1.8与JDK1.7的性能对比
  - Hash均匀的时候, JDK1.8的性能高于JDK1.7 15%以上
  - Hash不均匀的时候, 由于1.8里面使用了红黑树, 性能提高很多.

