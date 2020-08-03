package com.cyw.常规算法题.小和问题;

/**
 * @author chenyuwei
 * @create 2020-07-30-22:25
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和
 */
public class SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr,0,arr.length-1);
    }
    public static int mergeSort(int[] arr, int left, int right){
        if (left==right){
            return 0;
        }
        int mid = ((right-left)>>1)+left;//在这一定注意，是以left为基准索引进行偏移
        return mergeSort(arr,left,mid)+mergeSort(arr,mid+1,right)+merge(arr,left,mid,right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= right) {
            res += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[left+i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,4,2,5};
        int smallSum = smallSum(array);
        System.out.println(smallSum);
    }
}
