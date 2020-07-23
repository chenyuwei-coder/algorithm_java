package com.cyw.队列;

/**
 * @author chenyuwei
 * @create 2020-07-02-14:26
 */
public class MyQueue {
    private int[] array;
    private int front;
    private int rear;

    public MyQueue(int size){
        this.array = new int[size];
    }

    /**
     * 入队
     * @param element
     * @throws Exception
     */
    public void enQueue(int element) throws Exception {
        if((rear+1) % array.length == front){
            throw new Exception("队列已满");
        }
        array[rear] = element;
        rear = (rear+1)% array.length;
    }

    /**
     * 出队
     * @return 返回出队的元素
     * @throws Exception
     */
    public int deQueue() throws Exception {
        if(rear == front){
            throw new Exception("队列已空");
        }
        int temp = array[front];
        front = (front+1) % array.length;
        return temp;
    }

    /**
     * 输出队列元素
     */
    public void show(){
        for(int i=front;i!=rear;i=(i+1)%array.length){
            System.out.print(array[i]+" ");
        }
    }

    public static void main(String[] args) throws Exception {
        MyQueue myQueue = new MyQueue(10);
        myQueue.enQueue(3);
        myQueue.enQueue(2);
        myQueue.enQueue(5);
        myQueue.enQueue(7);
        myQueue.enQueue(9);
        myQueue.deQueue();
        myQueue.show();
    }
}
