package com.cyw.排序;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-10-9:52
 */
public class MyCountSort {
    public static int[] countSort(int[] array){
        //1.遍历数组，获取数组的最大值，以确定统计数组的长度
        int max = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]>max){
                max = array[i];
            }
        }
        int[] countArray = new int[max+1];
        //2.遍历数组，将数组中每个元素出现的次数记录到统计数组中
        for (int i=0;i<array.length;i++){
            countArray[array[i]]++;
        }
        //3.遍历统计数组，根据每个元素的值，确定将该值的下标输出几次
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i=0;i<countArray.length;i++){
            for(int j=0;j<countArray[i];j++){
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    public static int[] countSortPlus(int[] array){
        //1.找出数组的最大值和最小值，并计算出偏差
        int min = array[0];
        int max = array[0];
        for (int i=0;i<array.length;i++){
            if(array[i]<min){
                min = array[i];
            }
            if(array[i]>max){
                max = array[i];
            }
        }
        int bias = max-min;
        //2.创建统计数组
        int[] countArray = new int[bias+1];
        //3.遍历原始数组，记录元素出现的次数
        for(int i=0;i<array.length;i++){
            countArray[array[i]-min]++;
        }
        //4.对统计数组进行变换
        for(int i=1;i<countArray.length;i++){
            countArray[i] += countArray[i-1];
        }
        //5.倒序遍历原始数组，在统计数组中找到相应的位置，然后根据这个位置排名在sortedArray中填入值
        int[] sortedArray = new int[array.length];
        for(int i = array.length-1;i>=0;i--){
            sortedArray[countArray[array[i]-min]-1] = array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
        int[] array1 = new int[]{95,94,91,98,99,90,99,93,91,92};
        int[] sortedArray1 = countSortPlus(array1);
        System.out.println(Arrays.toString(sortedArray1));
    }
}
