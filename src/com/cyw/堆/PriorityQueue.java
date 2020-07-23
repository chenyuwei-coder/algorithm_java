package com.cyw.堆;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-07-15:40
 */
public class PriorityQueue {
    private int[] array;
    private int size;
    public PriorityQueue(){
        this.array = new int[32];
    }

    /**
     * 优先队列入队
     * @param data
     */
    public void enQueue(int data){
        if(size>=array.length){
            resize();
        }
        array[size++] = data;
        upAdjust();
    }

    /**
     * 堆的“上浮”操作
     */
    private void upAdjust() {
        int childIndex = size-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];
        while (childIndex>0 && temp > array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex/2;
        }
        array[childIndex] = temp;
    }

    public int deQueue() throws Exception{
        if(size<=0)
            throw new Exception("优先队列为空");
        //获取堆顶元素
        int head = array[0];
        //将最后一个叶子节点的元素提到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }

    /**
     * 堆的“下沉操作”
     */
    private void downAdjust() {
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while(childIndex<size){
            if (childIndex + 1 < size && array[childIndex]<array[childIndex+1]) {
                childIndex++;
            }
            if(temp >= array[childIndex])
                break;
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }

    private void resize() {
        int newSize = this.size*2;
        this.array = Arrays.copyOf(this.array,newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(1);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(7);
        priorityQueue.enQueue(4);
        System.out.println("最大元素出队："+priorityQueue.deQueue());
        priorityQueue.downAdjust();
        System.out.println("最大元素出队："+priorityQueue.deQueue());
    }
}
