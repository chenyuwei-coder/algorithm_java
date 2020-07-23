package com.cyw.排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author chenyuwei
 * @create 2020-07-10-19:59
 */
public class MyBucketSort {
    /**
     * 桶排序
     * @param array
     * @return
     */
    public static double[] bucketSort(double[] array){
        //1.遍历获得数组中的最大值和最小值，并计算差值
        double min = array[0];
        double max = array[0];
        for (int i=0;i<array.length;i++){
            if(array[i]<min){
                min = array[i];
            }
            if (array[i]>max){
                max = array[i];
            }
        }
        double d = max-min;
        //2.创建桶，并初始化
        int bucketNum = array.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
        for(int i=0;i<bucketNum;i++){
            bucketList.add(new LinkedList<Double>());
        }
        //3.遍历原始数组，将元素放入对应的桶中
        for (int i=0;i<array.length;i++){
            int num = (int)((array[i]-min) * (bucketNum-1)/d);//这一步计算没太懂
            bucketList.get(num).add(array[i]);
        }
        //4.遍历桶，对桶中的元素进行排序
        for (int i=0;i<bucketList.size();i++){
            Collections.sort(bucketList.get(i));
        }
        //5.遍历桶，输出桶中的元素
        double[] sortedArray = new double[array.length];
        int index = 0;
        for (LinkedList<Double> list:bucketList){
            for (double element:list){
                sortedArray[index] = element;
                index++;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[]{4.12,6.421,0.0023,3.0,2.123,8.122,4.12, 10.09};
        double[] sortedArray = bucketSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
