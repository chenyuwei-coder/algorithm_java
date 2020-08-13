package com.cyw.常规算法题.树类;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenyuwei
 * @create 2020-08-13-15:36
 */
public class SerializeAndReconstructTree {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 使用递归前序遍历实现树的序列化
     *
     * @param head
     * @return
     */
    public static String serializeTreeByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.data + "!";
        res += serializeTreeByPre(head.left);
        res += serializeTreeByPre(head.right);
        return res;
    }

    /**
     * 使用递归前序遍历实现树的反序列化
     *
     * @param string
     * @return
     */
    public static Node reconstructTreeByPre(String string) {
        String[] data = string.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            queue.offer(data[i]);
        }
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String data = queue.poll();
        if (data.equals("#"))
            return null;
        Node head = new Node(Integer.valueOf(data));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    /**
     * 使用层序遍历实现树的序列化
     *
     * @param head
     * @return
     */
    public static String serialByLevel(Node head) {
        if (head == null)
            return "#!";
        String res = head.data + "!";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                queue.offer(head.left);
            } else {
                res += "#!";
            }
            if (head.right != null) {
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    public static Node reconstructTreeByLevel(String string) {
        String[] data = string.split("!");
        Queue<Node> queue = new LinkedList<>();
        int index = 0;
        Node head = generateNodeByString(data[index++]);
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(data[index++]);
            node.right = generateNodeByString(data[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static Node generateNodeByString(String string) {
        if (string.equals("#"))
            return null;
        return new Node(Integer.valueOf(string));
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.data + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = null;
        printTree(head);

        String pre = serializeTreeByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconstructTreeByPre(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconstructTreeByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        printTree(head);

        pre = serializeTreeByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconstructTreeByPre(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconstructTreeByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);
        printTree(head);

        pre = serializeTreeByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconstructTreeByPre(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconstructTreeByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);
        printTree(head);

        pre = serializeTreeByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconstructTreeByPre(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconstructTreeByLevel(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");

    }
}
