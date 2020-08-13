package com.cyw.常规算法题.链表类;

/**
 * @author chenyuwei
 * @create 2020-08-06-21:56
 */
public class FindFirstIntersectNode {
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getFirstLoop(head1);
        Node loop2 = getFirstLoop(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(loop1, loop2, head1, head2);
        }
        return null;
    }

    private static Node bothLoop(Node loop1, Node loop2, Node head1, Node head2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {// 这种情况就和noLoop的情况很相似，只不过不是比较最后节点的地址是否相等，
            // 而是比较第一个入环节点是否相等
            cur1 = head1;
            cur2 = head2;
            int len1 = 0;
            int len2 = 0;
            while (cur1 != loop1) {
                len1++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                len2++;
                cur2 = cur2.next;
            }
            int n = Math.abs(len1 - len2);
            cur1 = len1 - len2 > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //这种情况就是从任一链表的入环节点开始遍历这个环，看是否能找到另一个链表的
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int len1 = 0;
        int len2 = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        while (cur1.next != null) {
            cur1 = cur1.next;
            len1++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            len2++;
        }
        if (cur1 != cur2) {
            return null;
        }
        int n = Math.abs(len1 - len2);
        cur1 = len1 - len2 > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    private static Node getFirstLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next; // n1 -> slow
        Node n2 = head.next.next; // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head; // n2 -> walk again from head
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }
    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        Node res = getIntersectNode(head1, head2);
        System.out.println(res.data);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        Node res1 = getIntersectNode(head1, head2);
        System.out.println(res1.data);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).data);

    }
}
