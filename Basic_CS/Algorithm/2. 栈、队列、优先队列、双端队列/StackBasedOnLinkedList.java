public class StackBasedOnLinkedList {
    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }


    }

    private Node top = null;
    public StackBasedOnLinkedList() {}

    public void push(int value) {
            Node newNode = new Node(value, null);
            if (top == null) {
                top = newNode;
            } else {
                newNode.next = top;
                top = newNode;
            }
        }

        public int pop() {
            if (top == null) {
                return -1;
            }
            int value = top.data;
            top = top.next;
            return value;
        }

        public void printall() {
            Node p = top;
            while (p != null) {
                System.out.println(p.data);
                p = p.next;
            }
        }


    public static void main(String[] args) {
        StackBasedOnLinkedList s = new StackBasedOnLinkedList();
        s.push(11);
        s.push(12);
        s.push(13);
        s.push(14);
        s.push(15);
        s.printall();
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
        }

    }

}
