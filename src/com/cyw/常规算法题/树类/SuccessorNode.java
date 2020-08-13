package com.cyw.常规算法题.树类;

/**
 * @author chenyuwei
 * @create 2020-08-09-20:29
 * 获取节点的后继节点和前驱节点
 */
public class SuccessorNode {
    public static class Node {
        public int data;
        public Node parent;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 获取当前节点的后继节点
     *
     * @param node 任意节点
     * @return 返回给定节点的后继节点
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null)
            return null;
        if (node.right != null) {
            return getMostLeftNode(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.left != node) {//当前节点有父节点，且当前节点为其父节点的左孩子时停止
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 找到当前节点的右子树上最左边的节点
     *
     * @param node
     * @return
     */
    private static Node getMostLeftNode(Node node) {
        if (node == null)
            return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 获取给定节点的前驱节点
     *
     * @param node
     * @return
     */
    public static Node getPrecursorNode(Node node) {
        if (node == null)
            return null;
        if (node.left != null) {
            return getMostRightNode(node.left);
        } else {
            Node parent = node.parent;
            while (parent != null && parent.right != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 获取当前节点左子树的最右节点
     *
     * @param node
     * @return
     */
    private static Node getMostRightNode(Node node) {
        if (node == null)
            return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        //输出的时候尤其注意第一个节点的前驱节点和最后一个节点的后继节点都是null，所以没有data可以引用，会报空指针异常
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test));
        test = head.left.left.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.left;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.left.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.left.right.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.right.left.left;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.right.left;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.right;
        System.out.println(test.data + " next: " + getSuccessorNode(test).data + " last:" + getPrecursorNode(test).data);
        test = head.right.right; // 10's next is null
        System.out.println(test.data + " next: " + getSuccessorNode(test) + " last:" + getPrecursorNode(test).data);
    }
}
