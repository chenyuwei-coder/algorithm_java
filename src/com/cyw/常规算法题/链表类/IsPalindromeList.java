package com.cyw.常规算法题.链表类;

import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-08-05-22:22
 */
public class IsPalindromeList {
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断是否是回文链表
     * @param head
     * @return 借助栈来实现逆序，然后再次遍历链表，与栈中元素依次比较，若全都一一相等，则是回文链表
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        //遍历链表，将节点入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.data != stack.pop().data) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 判断链表是否是回文数
     * @param head
     * @return
     * 和（1）方法相比，本方法节省了一半的额外空间
     */
    public static boolean isPalindrome2(Node head) {
        if (head==null || head.next==null){
            return true;
        }
        Node slow = head.next;//之所以让slow的起始位置在fast前面，是为了保证节点数为偶数是，slow停在中间靠右的节点
        Node fast = head;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (slow!=null){
            stack.push(slow);
            slow=slow.next;
        }
        while (!stack.isEmpty()){
            if (head.data != stack.pop().data){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 判断回文链表
     * @param head
     * @return
     * 使用三个指针对链表的后半部分进行逆序，然后与前半部分进行比较，比较完之后再给逆序回去。
     */
    public static boolean isPalindrome3(Node head){
        if (head==null || head.next==null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next!=null && n2.next.next!=null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;
        n1.next=null;
        Node n3 = null;
        while (n2!=null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;
        n2 = head;
        boolean res = true;
        while (n1!=null&&n2!=null){
            if (n1.data!=n2.data){
                res = false;
            }
            n1 = n1.next;
            n2=n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        while (n1!=null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
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
        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
