public class LinkedListPractise {
    /**
     * 单链表反转
     * 链表中环的检测
     * 两个有序的链表合并
     * 删除链表倒数第n个节点
     * 删除链表的中间节点
     * @param <T>
     */
    public static class Node<T> {
        private Integer data;
        private Node<Integer> next;

        public Node(Integer data, Node<Integer> next) {
            this.data = data;
            this.next = next;
        }

        public Integer getData() {
            return data;
        }
    }

    /**
     * 单链表反转
     */
    public static Node singleLinkedListReverse(Node list){
        Node pre = null;
        Node node = list;
        while (node != null) {
            Node temp = node.next;
            node.next = pre;
            pre = node;
            node = temp;
        }
        return pre;
    }

    /**
     * 链表中环的检测
     */
    public static boolean checkCircle(Node list){
        if (list == null) {
            return false;
        }

        Node fast = list;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 两个有序链表的合并(采用哨兵模式）
     */
    public static Node mergeSortedLists(Node la, Node lb){
        Node solider = new Node(100, null);
        Node p = solider;
        if (la == null) return lb;
        if (lb == null) return la;

        while (la != null && lb != null) {
            if (la.getData() < lb.getData()) {
                p.next = la;
                la = la.next;
            } else {
                p.next = lb;
                lb = lb.next;
            }
            p = p.next;
        }

        if (la == null) p.next = lb;
        if (lb == null) p.next = la;

        return solider.next;
    }

    /**
     * 删除链表倒数第n个结点
     */
    public static Node deleteLastKthNode(Node list, int k) {
        Node node = list;
        int i = 1;
        while (node != null && i < k) {
            node = node.next;
            i++;
        }
        if (node == null) {
            return list;
        }

        Node pre = null;
        Node slow = list;
        while (node.next != null) {
            node = node.next;
            pre = slow;
            slow = slow.next;
        }

        if (pre == null) {
            list = list.next;
        } else {
            pre.next = pre.next.next;
        }
        return list;
    }

    /**
     * 求链表的中间结点
     */
    public static Node findMiddleNode(Node list){
        if (list == null) {
            return null;
        }

        Node slow = list;
        Node fast = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


}
