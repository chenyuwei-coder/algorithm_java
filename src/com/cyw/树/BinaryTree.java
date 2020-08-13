package com.cyw.树;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-07-05-22:36
 */
public class BinaryTree {
    /**
     * 树的节点结构
     */
    private static class TreeNode{
        int data;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode(int data){
            this.data = data;
        }
    }

    /**
     * 构建二叉树
     * @param inputList 输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if(inputList == null || inputList.isEmpty()){
            return null;
        }
        Integer data = inputList.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.leftNode = createBinaryTree(inputList);
            node.rightNode = createBinaryTree(inputList);
        }
        return node;
    }

    /**
     * 二叉树前序遍历
     * @param node
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null)
            return;
        System.out.print(node.data+" ");
        preOrderTraveral(node.leftNode);
        preOrderTraveral(node.rightNode);
    }

    /**
     * 二叉树中序遍历
     * @param node
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null)
            return;
        inOrderTraveral(node.leftNode);
        System.out.print(node.data+" ");
        inOrderTraveral(node.rightNode);
    }

    /**
     * 二叉树后序遍历
     * @param node
     */
    public static void postOrderTraveral(TreeNode node){
        if(node == null)
            return;
        postOrderTraveral(node.leftNode);
        postOrderTraveral(node.rightNode);
        System.out.print(node.data+" ");
    }

    /**
     * 借助栈实现非递归的前序遍历
     * @param node
     */
    public static void preOrderTraveralWithStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode!=null || !stack.isEmpty()){
            while (treeNode!=null){
                System.out.print(treeNode.data+" ");
                stack.push(treeNode);
                treeNode = treeNode.leftNode;
            }
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.rightNode;
            }
        }
        System.out.println();
    }

    /**
     * 借助栈实现二叉树的中序遍历
     * @param node
     */
    public static void inOrderTraveralWithStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = node;
        while (treeNode!=null || !stack.isEmpty()){
            while (treeNode!=null){
                stack.push(treeNode);
                treeNode = treeNode.leftNode;
            }
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                System.out.print(treeNode.data+" ");
                treeNode = treeNode.rightNode;
            }
        }
        System.out.println();
    }

    /**
     * 借助栈实现二叉树的非递归后序遍历
     * @param node
     */
    public static void postOrderTravealWithStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node, pre = null;
        while (p != null || !stack.isEmpty()){
            while (p!=null){
                stack.push(p);
                p = p.leftNode;
            }
            if(!stack.isEmpty()){
                TreeNode top = stack.peek();
                if(top.rightNode == null || pre == top.rightNode){
                    System.out.print(top.data+" ");
                    stack.pop();
                    pre = top;
                }else{
                    p = top.rightNode;
                }
            }
        }
        System.out.println();
    }
    public static void posOrderUnRecur(TreeNode head){
        if (head!=null){
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.add(head);
            while (!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if (head.leftNode!=null){
                    stack1.push(head.leftNode);
                }
                if (head.rightNode!=null){
                    stack1.push(head.rightNode);
                }
            }
            while (!stack2.isEmpty()){
                System.out.print(stack2.pop().data+" ");
            }
        }
        System.out.println();
    }

    /**
     * 借助队列实现二叉树的层次遍历
     * @param node
     */
    public static void levelOrderTraveal(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            TreeNode p = queue.poll();
            System.out.print(p.data+" ");
            if(p.leftNode!=null){
                queue.offer(p.leftNode);
            }
            if(p.rightNode!=null){
                queue.offer(p.rightNode);
            }
        }
    }
    public static void main(String[] args) {
        LinkedList<Integer> inputList =
                new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode node = createBinaryTree(inputList);
        System.out.println("前序遍历：");
        //preOrderTraveral(node);
        preOrderTraveralWithStack(node);
        System.out.println("中序遍历：");
        inOrderTraveral(node);
        System.out.println("后序遍历：");
//        postOrderTraveral(node);
        posOrderUnRecur(node);
        System.out.println("层次遍历：");
        levelOrderTraveal(node);
    }
}
