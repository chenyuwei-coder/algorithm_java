package com.cyw.常规算法题.链表类;

/**
 * @author chenyuwei
 * @create 2020-08-05-14:06
 * 给定一个链表，和一个数num，调整链表节点，使得小于num的节点在左边，等于num的在中间，大于num的在右边
 */
public class SmallerEqualBigger {
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 本方法借助了一个数组，将链表节点依次存储到数组中，转变成数组的荷兰国旗问题，不具有稳定性
     *
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition1(Node head, int pivot) {
        if (head == null)
            return null;
        Node cur = head;
        int i = 0;
        while (cur != null) {
            cur = cur.next;
            i++;
        }
        Node[] array = new Node[i];
        cur = head;
        for (i = 0; i < array.length; i++) {
            array[i] = cur;
            cur = cur.next;
        }
        arrayPartition(array, pivot);
        for (i = 1; i < array.length; i++) {
            array[i - 1].next = array[i];
        }
        array[i - 1].next = null;
        return array[0];
    }

    /**
     * 这一步其实就是荷兰国旗问题
     *
     * @param array
     * @param pivot
     */
    private static void arrayPartition(Node[] array, int pivot) {
        int small = -1;
        int big = array.length;
        int index = 0;
        while (index != big) {
            if (array[index].data < pivot) {
                swap(array, ++small, index++);
            } else if (array[index].data > pivot) {
                swap(array, --big, index);
            } else {
                index++;
            }
        }
    }

    private static void swap(Node[] array, int i, int j) {
        Node temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 本方法借助六个变量将链表剪碎成小于等于和大于的三段，然后在将三段链表连接起来，具有稳定性
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition2(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;
        Node temp;
        while (head != null) {
            temp = head.next;
            head.next = null;
            if (head.data < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = smallTail.next;
                }
            }
            if (head.data == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = equalTail.next;
                }
            }
            if (head.data > pivot) {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = bigTail.next;
                }
            }
            head = temp;
        }
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        //head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
