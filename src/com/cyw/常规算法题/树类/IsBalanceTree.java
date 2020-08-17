package com.cyw.常规算法题.树类;

/**
 * @author chenyuwei
 * @create 2020-08-13-20:47
 * 判断一棵树是否是平衡二叉树
 */
public class IsBalanceTree {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 设计返回的数据结构
     */
    public static class ReturnData {
        public boolean isBalance;
        public int height;

        public ReturnData(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    /**
     * 判断一颗二叉树是否是平衡二叉树
     *
     * @param head 树的头结点
     * @return 返回true或者false
     */
    public static boolean isBalance(Node head) {
        return process(head).isBalance;
    }

    private static ReturnData process(Node head) {
        if (head == null) {
            return new ReturnData(true, 0);//空树是平衡二叉树
        }
        ReturnData leftData = process(head.left);//递归判断左子树
        if (!leftData.isBalance) {
            return new ReturnData(false, 0);//如果左子树不是平衡二叉树，那么整棵树就不是，程序终止
        }
        ReturnData rightData = process(head.right);//递归判断右子树
        if (!rightData.isBalance) {
            return new ReturnData(false, 0);//如果右子树不是平衡二叉树，整棵树也不是
        }
        if (Math.abs(leftData.height - rightData.height) > 1) {
            return new ReturnData(false, 0);//判断子树是否是平衡二叉树
        }
        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        //head.right.right.right = new Node(8);
        //head.right.right.right.right = new Node(9);

        System.out.println(isBalance(head));
    }
}
