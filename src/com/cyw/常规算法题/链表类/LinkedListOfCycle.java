package com.cyw.常规算法题.链表类;

/**
 * @author chenyuwei
 * @create 2020-07-11-22:05
 */
public class LinkedListOfCycle {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断链表是否有环
     *
     * @param node
     * @return 借助两个指针，一个步频为1，一个步频为2，形成追及问题
     */
    public static boolean isCycle(Node node) {
        Node p1 = node;
        Node p2 = node;
        int count = 0;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            count++;
            if (p1 == p2) {
                System.out.println("环长："+count);
                p1 = node;
                while (p1 != p2){
                    p1 = p1.next;
                    p2 = p2.next;
                }
                System.out.println("入环点为："+p1.data);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        Node node6 = new Node(8);
        Node node7 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node4;
        if (isCycle(node1)){
            System.out.println("链表中存在环");
        }else{
            System.out.println("链表中没有环");
        }
    }
}
