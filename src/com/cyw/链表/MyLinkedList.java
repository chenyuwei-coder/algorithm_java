package com.cyw.链表;

/**
 * @author chenyuwei
 * @create 2020-06-28-22:02
 */
public class MyLinkedList {
    /**
     * 链表节点的定义
     */
    private static class Node{
        int data;// 链表节点的数据部分
        Node next;//链表节点的指针部分
        Node(int data){
            this.data = data;
        }
    }
    private Node head;//头结点指针
    private Node tail;//尾结点指针
    private int size;//链表的实际长度

    /**
     * 向链表中插入节点
     * @param index 插入的节点索引
     * @param data 节点数据
     * @throws Exception
     */
    public void insert(int index, int data) throws Exception{
        //判断索引是否越界
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node insertNode = new Node(data);
        //如果插入的链表为空
        if(size == 0){
            head = insertNode;
            tail = insertNode;
        }else if(index == size){// 插入尾部
            tail.next = insertNode;
            insertNode.next = null;
            tail = insertNode;
        }else if(index == 0){// 插入头部
            insertNode.next = head;
            head = insertNode;
        }else{//在中部插入
            //获取待插入节点的前一个节点
            Node preNode = get(index-1);
            insertNode.next = preNode.next;
            preNode.next = insertNode;
        }
        size++;
    }
    public Node delete(int index) throws Exception {
        if(index <0 || index > size){
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node deleteNode = null;
        if(index == 0){
            deleteNode = head;
            head = head.next;
        }else if(index == size){
            deleteNode = tail;
            Node preNode = get(index-1);
            preNode.next = null;
            tail = preNode;
        }else{
            Node preNode = get(index-1);
            deleteNode = preNode.next;
            preNode.next = preNode.next.next;
        }
        size--;
        return deleteNode;
    }
    /**
     * 查找链表中的节点
     * @param index 节点的索引
     * @return 返回找到的节点
     * @throws Exception
     */
    private Node get(int index) throws Exception{
        if(index<0 || index>= size){
            throw new IndexOutOfBoundsException("索引越界");
        }
        Node temp = head;
        for(int i=0;i<index;i++){
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 输出链表
     */
    private void output(){
        Node temp = head;
        while(temp != null){
            if(temp.next == null){
                System.out.println(temp.data);
            }else{
                System.out.print(temp.data+"->");
            }
            temp = temp.next;
        }
    }
    public static void main(String[] args) throws Exception {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(0,3);
        myLinkedList.insert(1,7);
        myLinkedList.insert(2,5);
        myLinkedList.insert(3,9);
        myLinkedList.insert(2,4);
        myLinkedList.output();
        myLinkedList.delete(2);
        myLinkedList.output();
    }
}
