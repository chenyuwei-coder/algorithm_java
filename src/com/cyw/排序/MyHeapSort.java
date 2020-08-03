package com.cyw.排序;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-08-23:55
 */
public class MyHeapSort {
    public static void downAdjust(int[] array, int parentIndex, int length){
        int temp = array[parentIndex];
        int childIndex = parentIndex*2+1;
        while (childIndex<length){
            if(childIndex+1<length && array[childIndex]<array[childIndex+1]){// 找出左右孩子中较大的一个
                childIndex++;
            }
            if(temp>array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex=childIndex;
            childIndex = parentIndex*2+1;
        }
        array[parentIndex] = temp;
    }
    public static void heapSort(int[] array){
        //把无序堆构建成最大堆
        for(int i=(array.length-2)/2;i>=0;i--){
            downAdjust(array,i,array.length);
        }
        System.out.println(Arrays.toString(array));
        //循环删除堆顶元素，将堆顶元素调整到数组尾端，然后调整生成新的大顶堆
        for(int i=array.length-1;i>0;i--){
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downAdjust(array,0,i);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,7,6,5,3,2,8,1};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
