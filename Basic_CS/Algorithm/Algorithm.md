#### BST(Binary Search Tree)

- 递归的时候, 到达底部后是怎么遍历返回的
- bst删除节点的时候, 节点删除是怎么进行的

- 定义:左节点的值比父节点的值要小, 右节点的值要比父节点的值大. 它的高度决定了它的查找效率. 在理性的情况下, 二叉树增删改查的时间复杂度为O(logN), 最坏的情况为O(N). 当它的高度为logN+1时, 二叉树是平衡的. 

- 两种二叉树:

  - 满二叉树: 叶子节点全都在最底层, 除了叶子节点之外, 每个节点都有左右两个子节点

  - 完全二叉树: 叶子节点都在最底下两层, 最后一层的叶子结点都靠左排列, 并且除了最后一层, 其他层的节点个数都要达到最大. 

  - ```java
    @Data
    public class Node {
        private int data;
        private Node leftNode;
        private Node rightNode;
    }
    ```

- 二叉树的两种实现方法:

  - 基于指针或者引用的二叉链式存储法: 每个节点有三个字段, 其中一个存储数据, 另外两个是指向左右子节点的指针, 通过左右子节点的指针, 把整棵树都串起来了.
  - 基于数组的顺序存储法: 把根节点存储在下标 i = 1 的位置, 那左子节点存储在下标 2 * i = 2 的位置, 右子节点存储在 2 * i + 1 = 3的位置, 之后的以此内推. 下标为 2*1 的位置存储的就是左子节点, 下标为 2\*i+1的位置存储的就是右子节点. 反过来, 下标为 i/2 的位置存储的就是它的父节点. 

- 二叉树的遍历: 前序遍历, 中序遍历, 后序遍历

  - 前序遍历: 对于树中的任意节点来说, 先打印这个节点, 然后再打印它的左子树, 最后打印它的右子树.

    ```java
    public void preOrder(Node root) {
            printNode(root);
            if (root.getLeftNode() != null) {
                preOrder(root.getLeftNode());
            }
            if (root.getRightNode() != null) {
                preOrder(root.getRightNode());
            }
        }
    ```

    

  - 中序遍历: 对于树中的任意节点来说, 先打印它的左子树, 然后再打印它本身, 最后打印它的右子树.

    ```java
    public void inOrder(Node root) {
            if (root.getLeftNode() != null) {
                inOrder(root.getLeftNode());
            }
            printNode(root);
            if (root.getRightNode() != null) {
                inOrder(root.getRightNode());
            }
     }
    ```

  - 后序遍历: 对于树中的任意节点来说, 先打印它的左子树, 然后再打印它的右子树, 最后打印这个节点本身.

    ```java
    public void postOrder(Node root) {
            if (root.getLeftNode() != null) {
                postOrder(root.getLeftNode());
            }
            if (root.getRightNode() != null) {
                postOrder(root.getRightNode());
            }
            printNode(root);
        }
    ```

    

- BST的查找操作

  - ```java
    T data = node.getData();
    Node root;
    
    while(true) {
      if (root == null) {
        break;
      }
      if (root.getData().equals(data)) {
        return root;
      } else if (data.compareTo(root.getData()) < 0) {
        root = root.getLeftNode();
      } else {
        root = root.getRightNode();
      }  
    }
    ```

- BST的插入操作

  - ```java
    Node node = new Node();
    Node root;
    Node parent = null;
    
    while(true) {
      if(root == null) break;
      parent = root;
      if (node.getData().compareTo(root.getData()) <= 0){
        root = root.getLeftNode();
      } else {
        root = root.getRightNode();
      }
    }
    if (parent != null){
      if(node.getData().compareTo(parent.getData()) <= 0){
        parent.setLeftNode(node);
      } else {
        parent.setRightNode(node);
      }
    }
    ```

  - BST的删除操作

    - 查找到要删除的节点

    - 如果要删除的节点是叶子结点, 则直接删除

    - 如果待删除的节点不是叶子结点, 则先找到待删除节点的中序遍历的后继节点, 用该后继节点的值替换待删除的节点的值, 然后删除后继节点. 

    - ```java
      public void delete(int data, Node root) {
              Node p = root;
              Node pp = null;
              while (p != null && p.getData() != data) {
                  pp = p;
                  if (data > p.getData()) {
                      p = p.getRightNode();
                  } else {
                      p = p.getLeftNode();
                  }
              }
              if (p == null) {
                  return;
              }
      
              // 要删除的节点有两个子节点
              if (p.getLeftNode() != null && p.getRightNode() != null) {
                  Node minP = p.getRightNode();
                  Node minPP = p;
                  while (minP.getLeftNode() != null) {
                      minPP = minP;
                      minP = minP.getLeftNode();
                  }
                  p.setData(minP.getData());
                  p = minP;
                  pp = minPP;
              }
      
              // 要删除的是叶子结点或者只有一个子节点
              Node child;
              if (p.getLeftNode() != null) {
                  child = p.getLeftNode();
              } else if (p.getRightNode() != null) {
                  child = p.getRightNode();
              } else {
                  child = null;
              }
      
              // 删除的是根节点
              if (pp == null) {
                  root = child;
              } else if (pp.getLeftNode() == p) {
                  pp.setLeftNode(child);
              } else {
                  pp.setRightNode(child);
              }
          }
      ```

  - BST查找最大值最小值

    - ```java
      public Node minNode(){
        if (root == null) return null;
        Node p = root;
        while (p.getLeftNode() != null){
          p = p.getLeftNode();
        }
        return p;
      }
      
      public Node maxNode(){
        if (root == null) return null;
        Node p = root;
        while (p.getRightNode() != null){
          p = p.getRightNode();
        }
        return p;
      }
      ```

  - 支持重复数据的二叉查找树

    - 第一种, 通过链表和支持动态扩容的数组等数据结构, 把值相同的数据都储存在同一个节点上
    - 第二种, 将插入的数据放到这个节点的右子树，也就是说，把这个新插入的数据当作大于这个节点的值来处理。当要查找数据的时候，遇到值相同的节点，我们并不停止查找操作，而是继续在右子树中查找，直到遇到叶子节点

- 使用二叉树而不是散列表的原因

  - 散列表中的数据都是无序储存的, 如果要输出有序的数据, 需要先进行排序. 而对于二叉树来说, 只需要中序遍历.
  - 散列表扩容耗时很多, 当遇到散列冲突时, 性能不稳定, 平衡二叉树的性能很稳定
  - 散列表存在哈希冲突, 哈希函数本身也耗时
  - 散列表的构造需要考虑散列函数的设计, 冲突的解决办法, 扩容, 缩容等; 而平衡二叉树只需要考虑平衡性这一个问题.
  - 为了避免过多的散列冲突, 散列表的装载因子不能太大, 特别是基于开放寻址法解决冲突的散列表, 不然会浪费一定的存储空间.

### 平衡二叉树

- 定义: 二叉树中任意一个节点的左右子树的高度相差不能大于1, 完全二叉树, 满二叉树都是平衡二叉树, 非完全二叉树也有可能是平衡二叉树

#### 红黑树(Red-Black Tree)

- 红黑树的要求
  - 根节点是黑色的
  - 每个叶子节点都是黑色的空节点(NIL), 也就是说, 叶子结点不储存数据
  - 任何相邻的节点都不能同时为红色, 也就是说, 红色节点是被黑色节点隔开的
  - 每个节点, 从该节点到达其可到达叶子结点的所有路径, 都包含相同数目的黑色节点
- AVL 树: 这种树是一种高度平衡的二叉树, 每次插入、删除都要做调整, 比较复杂耗时. 所以对于有频繁的插入.删除操作的数据集合, 使用AVL树的代价就有点儿高了.

### 排序

- 公用方法: 

  - ```java
    public static boolean less(Comparable v, Comparable w){
      return v.compareTo(w) < 0;
    }
    
    public static void exch(Comparable[] a, int i, int j){
      Comparable t = a[i];
      a[i] = a[j];
      a[j] = t;
    }
    
    ```

- 选择排序

  - 首先找到数组中最小的那个元素, 其次, 将它和数组的第一个元素交换位置. 再次, 在剩下的元素中找到最小的元素, 将它与数组的第二个元素交换位置, 如此往复, 直到将整个数组排序.

  - 对于长度为N的数组, 选择排序需要N^2/2次比较和N次交换

  - 一个有序的数组或主键全部相等的数组和一个元素随机排列的数组所用的排序时间一样长. 运行时间与输入无关. 

  - ```java
    public static void selectSort(Comparable[] a){
    	int N = a.length;
      for(int i = 0; i < N; i++){
        int min = i;
        for(int j = i+1; j < N; j++){
          if(less(a[j], a[min])) min = j;
          exch(a, i, min);
        }
      }
    }
    ```

- 插入排序

  - ```java
    public static void insertSort(Comparable[] a){
      int N = a.length;
      for (int i = 1; i < N; i++){
        for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
          exch(a, j, j-1);
        }
      }
    }
    ```

  - 对于随机排列的长度为N且主键不重复的数组, 平均情况下插入排序需要N^2/4次比较以及N^2/4次交换

  - 适用的场景

    - 数组中每个元素距离它的最终位置都不远
    - 一个有序的大数组接一个小数组
    - 数组中只有几个元素的位置不正确

  - 改进方案: 在内循环中, 将较大的元素都向右移动而不总是交换

- 希尔排序

  - 数组中任意间隔为h的元素都是有效的.是非稳定排序算法.

  - 实现方法: 对于每一个h, 用插入排序将h个子数组独立的排序. 但因为子数组是相互独立的, 一个更简单的方法是在h子数组中, 将每个元素交换到比他大的元素之前去. 只需要在插入排序的代码中将移动元素的距离由1改为h即可. 这样, 希尔排序的实现就转化为一个类似于插入排序但使用不同增量

  - ```java
    public static void shellSort(Comparable[] a){
      int N = a.length;
      int h = 1;
      while(h < N / 3){
        h = 3 * h + 1;
      }
      while (h >= 1){
        for(int i = h; i < N; i++){
          for(int j = i; j >= h && less(a[j], a[j-h]); j -= h){
            exch(a, j, j-h);
          }
        }
        h = h / 3;
      }
    }
    ```

- 归并排序

  - 将两个有序的数组并成一个更大的有序数组.

  - 它保证将任意长度为N的数组排序所需时间和NlogN成正比, 缺点是他所需要的额外空间和N成正比

  - 原地归并的抽象方法

    - ```java
      public static void abstractInPlaceMerge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        
        // 将a数组拷贝到aux数组
        for(int k = lo; k <= hi, k++){
          aux[k] = a[k];
        }
        
        for (int k = lo; k <= hi; k++){
          if (i > mid) a[k] = aux[j++]; // 左半边用尽, 取右边元素
          else if (j > hi) a[k] = aux[i++]; // 右边用尽, 取左边的元素
          else if (less(aux[j], aux[i])) a[k] = aux[j++]; //右半边的元素小于左半边的当前元素, 取右半边的元素
          else a[k] = aux[i++]; //右半边的当前元素大于等于左半边的当前元素, 取左半边的元素
        }
      }
      ```

  - 自顶向下的归并排序

    - ```java
      public class topDownMergeSort{
        private static Comparable[] aux; // 归并所需要的辅助数组
        
        public static void sort(Comparable[] a){
          aux = new Comparable[a.length]; // 一次性分配空间
          sort(a, 0, a.length - 1);
        }
        
        private static void sort(Comparable[] a, int lo, int hi){
          if (hi <= lo) return;
          int mid = lo + (hi - lo)/2;
          sort(a, lo, mid); // 将左半边排序
          sort(a, mid+1, hi); // 将右半边排序
          abstractInPlaceMerge(a, lo, mid, hi);
        }
      }
      ```

    - 对于长度为N的任意数组, 自顶向下的归并排序需要1/2NlogN至NlogN次比较

    - 使用小规模的数组(比如长度小于15), 一般可以将归并排序的运行时间缩短10%~15%

  - 自底向上的归并排序

    - ```java
      public static bottomUpMergeSort(Comparable a){
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        
        for (int sz = 1; sz < N; sz = sz + sz){
          for(int lo = 0; lo = N -sz; lo += sz + sz){
            merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1))
          }
        }
      }
      ```

    - 对于长度为N的任意数组, 自顶向下的归并排序需要1/2NlogN至NlogN次比较, 最多访问数组6NlogN次

- 快速排序

  - 归并排序是将一个数组分成两个子数组分别排序, 并将有序的子数组归并以将整个数组排序; 快速排序是当两个子数组都有序时, 整个数组也就自然有序了.

  - ```java
    public class Quick{
      public static void sort(Comparablr[] a){
        sort(a, 0, a.length - 1);
      }
      
      private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // 切分
        sort(a, lo, j-1); // 将左半部分排序
        sort(a, j+1, hi); // 将右半部分排序
      }
    }
    ```

  - 

    - 切分: 

      - 定义: 对于某个j, a[j]已经排定; a[lo] 到 a[j-1]中的所有元素都不大于a[j]; a[j+1] 到 a[hi]中的所有元素都不小于a[j]

      - ```java
         private static int partition(Comparable[] a, int lo, int hi){
            int i = lo, j = hi + 1;
            Comparable v = a[lo];
            while(true) {
              // 左右扫描, 检查扫描是否结束并交换元素
              while (less(a[++i], v)){
                if (i == hi) break;
              }
              while (less(v, a[--j])){
                if (j == lo) break;
              }
              if (i >= j) break;
              exch(a, i, j);
            }
            exch(a, lo, j);
            return j;
          }
        ```

      - 需要2NlogN次比较, 最多需要N^2/2次比较

- 算法改进

  - 对于小数组, 快速排序比插入排序慢; 因为递归, 快速排序的sort()方法在小数组中也会调用自己. 因此使用`if (hi <= lo + M) {Insertion.sort(a, lo, hi); return}`

  - 三取样切分, 使用子数组的一小部分元素的中位数来切分数组, 但是需要计算中位数. 将取样大小设置为3, 并用大小居中的元素切分的效果最好

    - 这种方式的切分能够将和切分元素相等的元素归位

    - ```java
      private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt){
          int cmp = a[i].compartTo(v);
          if (cmp < 0) exch(a, lt++, i++);
          if (cmp > 0) exch(a, i, gt--);
          else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
      }
      ```

    - 