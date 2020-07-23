package com.cyw.排序;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-07-23:06
 */
public class BubbleSort {
    /**
     * 最原始的冒泡排序算法，第一层循环控制回合数，第二层循环处理具体的冒泡
     * @param array
     */
    public static void bubbleSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-i-1;j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序的第一版改进
     * @param array
     * 在排序过程中，有可能经过几轮排序之后，数组就已经有序了，但是原始的冒泡还是会继续将元素遍历完，
     * 这就花费了时间，对其进行改进，添加一个布尔变量，来判断数组是否已经有序。
     */
    public static void bubbleSort1(int[] array){
        for(int i=0;i<array.length-1;i++){
            boolean isSort = true;//默认每一回合都是有序的
            for(int j=0;j<array.length-i-1;j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSort = false;// 只要进入到这个if判断判断中，就说明数组发生了交换，即数组还是无序的
                }
            }
            if(isSort)
                break;;
        }
    }

    /**
     * 冒泡排序的第二版改进
     * @param array
     * 针对形如3 4 2 1 5 6 7 8这样的数组，其后半部分已经是有序的了，无需再如原始冒泡那样再遍历一遍，
     * 可以用变量记录一下有序区与无序区的边界，然后每一回合只需要对无序区进行冒泡。
     */
    public static void bubbleSort2(int[] array){
        int lastExchangeIndex = 0;
        int sortBorder = array.length-1;
        for(int i=0;i<array.length-1;i++){
            boolean isSort = true;//默认每一回合都是有序的
            for(int j=0;j<sortBorder;j++){
                if (array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isSort = false;// 只要进入到这个if判断判断中，就说明数组发生了交换，即数组还是无序的
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if(isSort) {
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序
     * @param array
     * 这是冒泡排序的一种更加彻底的改进方式，通过从左到右以及从右到左的交替冒泡，来缩短排序的回合
     * 适用于大多数地方有序而局部无序的状态
     */
    public static void cocktailSort(int[] array){
        for(int i=0;i<array.length/2;i++){
            boolean isSort = true;
            for(int j=i;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    isSort = false;
                }
            }

            if(isSort)
                break;
            isSort = true;
            for (int j=array.length-1;j>i;j--){
                if(array[j]<array[j-1]){
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    isSort = false;
                }
            }
            if (isSort)
                break;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
        int[] array1 = new int[]{3,4,2,1,5,6,7,8};
        long startTime = System.currentTimeMillis();
        bubbleSort(array1);
        long endTime = System.currentTimeMillis();
        long execTime = endTime-startTime;
        System.out.println("执行时间："+execTime);

        long startTime1 = System.currentTimeMillis();
        bubbleSort2(array1);
        long endTime1 = System.currentTimeMillis();
        long execTime1 = endTime1-startTime1;
        System.out.println("执行时间："+execTime1);
        int [] array3 = new int[]{2,3,4,5,6,7,8,1};
        cocktailSort(array3);
        System.out.println(Arrays.toString(array3));

    }
}
