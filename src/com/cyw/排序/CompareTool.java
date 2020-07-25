package com.cyw.排序;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-25-22:51
 * 这是一个对数器，旨在通过将自己实现的方法的运行结果和JDK官方实现的方法的运行结果进行对比，
 * 以确定自己方法的正确性
 */
public class CompareTool {
   //官方实现的绝对正确的排序方法
   public static void absoluteRightSort(int[] array){
       Arrays.sort(array);
   }
   //随机生成数组
    public static int[] getRandomArray(int maxSize, int maxValue){
       int[] array = new int[(int)((maxSize+1)*Math.random())];
       for (int i=0;i<array.length;i++){
           array[i] = (int)((maxValue+1)*Math.random())-(int)(maxValue*Math.random());
        }
       return array;
    }
    //复制数组
    public static int[] copyArray(int[] array){
       int[] array1 = new int[array.length];
       for (int i=0;i<array.length;i++){
           array1[i] = array[i];
       }
       return array1;
    }
    //判断两个数组是否相等
    public static boolean isEqual(int[] arr1,int[] arr2){
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
