package com.cyw.常规算法题.无序数组排序后的最大相邻差;

/**
 * @author chenyuwei
 * @create 2020-07-16-15:19
 */
public class MyMostDistance {
    public static class Bucket{
        Integer max;
        Integer min;
    }

    /**
     * 寻找无序数组排序后的最大相邻差
     * @param array
     * @return
     * 只存在一个桶排序的时间，所以时间复杂度是O(n)
     */
    public static int getMaxSortedDistance(int [] array){
        //1.获取数组中的最大值和最小值，并计算差值
        int max = array[0];
        int min = array[0];
        for (int i=1;i<array.length;i++){
            if (array[i]>max){
                max = array[i];
            }
            if (array[i]<min){
                min = array[i];
            }
        }
        int d = max - min;
        if (d ==0)
            return 0;
        //2.初始化桶
        int bucketNum = array.length;
        Bucket[] buckets = new Bucket[bucketNum];
        for (int i=0;i<bucketNum;i++){
            buckets[i] = new Bucket();
        }
        //3.遍历原始数组，确定每个桶的最大值和最小值
        for (int i=0;i<array.length;i++){
            //确定数组元素所归属的桶下标
            int index = ((array[i]-min)*(bucketNum-1)/d);
            if (buckets[index].min == null || buckets[index].min>array[i]){
                buckets[index].min = array[i];
            }
            if (buckets[index].max == null || buckets[index].max<array[i]){
                buckets[index].max = array[i];
            }
        }
        //4.寻找桶之间的最大差值
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for (int i=1;i<bucketNum;i++){
            if (buckets[i].min == null)
                continue;
            if ((buckets[i].min-leftMax)>maxDistance){
                maxDistance = buckets[i].min-leftMax;
            }
            leftMax = buckets[i].max;
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        int[] array = new int[] {2,6,3,4,5,10,9};
        System.out.println("数组的最大差值为："+getMaxSortedDistance(array));
    }
}
