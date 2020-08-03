package com.cyw.常规算法题.小和问题;

/**
 * @author chenyuwei
 * @create 2020-07-31-17:10
 * 求数组的逆序对，可以借助归并排序的过程，在过程中记录逆序数对
 */
public class ReverseOrder {
    public static int findCastleWalls(int[] arr) {
        if (arr == null && arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return  mergeSort(arr, l, mid)+ mergeSort(arr, mid + 1, r)+ merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        int res =0;
        while (p1 <= mid && p2 <= r) {
            if (arr[p1] > arr[p2]) {
                res += mid-p1+1;
                for (int m = p1; m <= mid; m++) {
                    System.out.println("逆序对："+arr[m]+" "+arr[p2]);
                }
            }
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 2, 5};
        int total = findCastleWalls(array);
        System.out.println("逆序对总数："+total);
    }
}