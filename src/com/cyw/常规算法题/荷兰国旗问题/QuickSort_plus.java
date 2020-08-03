package com.cyw.常规算法题.荷兰国旗问题;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-08-01-16:39
 * 结合荷兰国旗问题改进过的随机快速排序
 */
public class QuickSort_plus {
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr,L+(int) (Math.random()*(R-L+1)),R);//加上这一行就变成了随机快速排序
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int little = l - 1;
        int big = r;
        int cur = l;
        while (cur < big) {
            if (arr[cur] < arr[r]) {
                swap(arr, ++little, cur++);
            } else if (arr[cur] > arr[r]) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        swap(arr, big, r);
        return new int[]{little + 1, big};
    }

    private static void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,4,2,5,6,9,7,5,5};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
