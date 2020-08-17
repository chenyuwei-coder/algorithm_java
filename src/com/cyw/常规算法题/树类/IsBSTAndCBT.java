package com.cyw.常规算法题.树类;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-08-13-21:25
 */
public class IsBSTAndCBT {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断一棵树是否是二叉搜索树
     *
     * @param head
     * @return 中序遍历的结果是升序的话，就是二叉搜索树
     */
    public static Boolean isBST(Node head) {
        if (head == null)
            return true;
        Stack<Node> stack = new Stack<>();
        int pre = -1;
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                stack.add(head);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                if (head.data - pre < 0)
                    return false;
                pre = head.data;
                head = head.right;
            }
        }
        return true;
    }

    public static boolean isCBT(Node head) {
        if (head == null)
            return true;
        Queue<Node> queue = new LinkedList<>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            //leaf表示是否开始叶节点，一旦开始叶节点，那么左右子树都不能存在
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        //head.left.left.left = new Node(7);
        boolean res = isBST(head);
        System.out.println(res);
        System.out.println(isCBT(head));
    }
}
