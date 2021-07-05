public class LRUBasedLinkedList<T> {
    public class SNode<T>{
        private T element;
        private SNode next;

        public SNode() {
        }

        public SNode(SNode next) {
            this.next = next;
        }

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    private SNode<T> headNode;
    private final static Integer DEFAULT_CAPACITY = 10;
    private Integer capacity;
    private Integer length;

    public LRUBasedLinkedList(Integer capacity) {
        this.headNode = new SNode<T>();
        this.length = 0;
        this.capacity = capacity;
    }

    public LRUBasedLinkedList() {
        this.headNode = new SNode<T>();
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

//        如果该节点已经存在
        if (preNode != null) {
            deleteElm(preNode);
            insertElmAtBegin(data);
        } else {
            if (length >= capacity) {
                deleteElmAtEnd();
            }
            insertElmAtBegin(data);
        }
    }

    private void deleteElmAtEnd() {
        SNode node = headNode;
        if (node.getNext() == null) {
            return;
        }
        while (node.getNext().getNext() != null) {
            node = node.getNext();
        }
        node.setNext(null);
        length--;
    }

    private void insertElmAtBegin(T data) {
        SNode node = headNode.getNext();
        headNode.setNext(new SNode(data, node));
        length++;
    }

    private void deleteElm(SNode preNode) {
        SNode temp = preNode.getNext().getNext();
        preNode.setNext(temp);
        temp = null;
        length--;
    }

    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public void printall(){
        SNode node = headNode.getNext();
        while(node!= null){
            System.out.println(node.getElement());
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        LRUBasedLinkedList<Integer> a = new LRUBasedLinkedList<>(4);
        a.add(3);
        a.add(4);
        a.add(42);
        a.add(43);
        a.printall();
    }


}
