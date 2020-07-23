package com.cyw.队列;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyuwei
 * @create 2020-07-02-15:44
 */
public class DoubleEndQueue {
    private int size;
    private LNode front;
    private LNode rear;

    public DoubleEndQueue(){
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    private class LNode{
        int data;
        LNode pre;
        LNode next;

        public LNode(int data, LNode pre, LNode next){
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 头部入队
     * @param element
     */
    public void enDequeFromHead(int element){
        LNode newNode = new LNode(element,null, front);// 生成的新节点的下一个节点指向头部节点
        if(isEmpty()){
            rear = newNode;
            front = newNode;
        }
        front.pre = newNode;
        front = newNode;
        size++;
    }

    /**
     * 头部出队
     * @return
     * @throws Exception
     */
    public int deDequeFromHead() throws Exception {
        if(isEmpty()){
            throw new Exception("双端队列为空");
        }
        int temp = front.data;
        if(size == 1){
            front = rear = null;
            size=0;
        }else{
            front = front.next;
            size--;
        }
        return temp;
    }

    /**
     * 从尾部入队
     * @param element
     */
    public void enDequeFromTail(int element){
        LNode newNode = new LNode(element,rear,null);// 生成的新节点的pre指向尾结点
        if(isEmpty()){
            rear = front = newNode;
        }
        rear.next = newNode;
        rear = newNode;
        size++;
    }

    public int deDequeFromTail() throws Exception {
        if (isEmpty()){
            throw new Exception("双端队列为空");
        }
        int temp = rear.data;
        if(size == 1){
            rear = front = null;
            size=0;
        }else {
            rear = rear.pre;
            size--;
        }
        return temp;
    }

    public int getFirst(){
        return front.data;
    }
    public int getLast(){
        return rear.data;
    }
    private boolean isEmpty() {
        return size>0 ? false : true;
    }

    public static void main(String[] args) throws Exception {
        DoubleEndQueue doubleEndQueue = new DoubleEndQueue();
        doubleEndQueue.enDequeFromHead(1);//1
        doubleEndQueue.enDequeFromHead(2);//21
        doubleEndQueue.enDequeFromHead(3);//321
        doubleEndQueue.enDequeFromTail(5);//3215
        doubleEndQueue.enDequeFromTail(6);//32156
        doubleEndQueue.enDequeFromTail(7);//321567
        System.out.println("头部第一个元素"+doubleEndQueue.getFirst());
        System.out.println("尾部第一个元素"+doubleEndQueue.getLast());
        doubleEndQueue.deDequeFromHead();
        doubleEndQueue.deDequeFromTail();
        System.out.println("头部第一个元素"+doubleEndQueue.getFirst());
        System.out.println("尾部第一个元素"+doubleEndQueue.getLast());
        Map map = new HashMap();
    }
}
