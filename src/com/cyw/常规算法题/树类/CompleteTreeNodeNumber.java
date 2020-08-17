package com.cyw.常规算法题.树类;

/**
 * @author chenyuwei
 * @create 2020-08-14-20:29
 * 已知一棵完全二叉树，求其节点的个数
 */
public class CompleteTreeNodeNumber {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null)
            return 0;
        return sumOfNode(head, 1, mostLeftLevel(head, 1));
    }

    private static int sumOfNode(Node node, int level, int high) {
        if (level == high)
            return 1;
        if (mostLeftLevel(node.right, level + 1) == high) {
            return (1 << (high - level)) + sumOfNode(node.right, level + 1, high);
        } else {
            return (1 << (high - level - 1)) + sumOfNode(node.left, level + 1, high);
        }
    }

    private static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }
}
