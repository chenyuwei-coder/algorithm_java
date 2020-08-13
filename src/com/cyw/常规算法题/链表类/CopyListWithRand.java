package com.cyw.常规算法题.链表类;

import java.util.HashMap;

/**
 * @author chenyuwei
 * @create 2020-08-06-14:22
 */
public class CopyListWithRand {
    public static class Node {
        public int data;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 深度拷贝带有随机指针的链表
     *
     * @param head
     * @return 本方法借助哈希数组实现深度拷贝，需要O(N)的额外空间
     */
    public static Node copyListWithRand1(Node head) {
        if (head == null)
            return null;
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.data));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 深拷贝带有随机指针的链表
     * @param head
     * @return
     */
    public static Node copyListWithRand2(Node head) {
        if (head == null)
            return null;
        Node cur = head;
        Node next = null;
        //在原数组中复制插入一个相同的节点，例：1->2->3->4变为1->1'->2->2'->3->3'->4->4'
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = next;
            cur = next;
        }
        //复制节点的随机指针
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }
        Node res = head.next;
        //新旧链表之间的随机指针是互不干扰的，所以不用管，我们需要拆分新旧链表的next指针
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next.next = next != null ? next.next : null;
            cur.next = next;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
