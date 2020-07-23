package com.cyw.堆;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-07-10:03
 */
public class MyHeap {
    /**
     * 向上调整堆
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];
        while(childIndex>0 && temp<array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[parentIndex] = temp;
    }

    /**
     * "下沉"操作
     * @param array 待调整的堆
     * @param parentIndex 父亲节点的索引
     * @param length 存储堆的数组的长度
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex < length){
            if(childIndex+1 < length && array[childIndex] > array[childIndex+1]){
                childIndex++;
            }
            if(temp <= array[childIndex])
                break;
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     * @param array 待调整的堆
     */
    public static void buildHeap(int[] array){
        for(int i=(array.length-2)/2;i>=0;i--){
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
