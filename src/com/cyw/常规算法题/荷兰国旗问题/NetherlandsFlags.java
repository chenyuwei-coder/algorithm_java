package com.cyw.常规算法题.荷兰国旗问题;

/**
 * @author chenyuwei
 * @create 2020-08-01-15:14
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数
 * 放在数组的右边
 * 要求：空间复杂度为O(1)，时间复杂度为O(N)
 */
public class NetherlandsFlags {
    public static int[] partition(int[] arr, int L, int R, int num) {
        int little = L - 1;
        int big = R + 1;
        int cur = L;
        while (cur < big) {
            if (arr[cur] < num) {
                swap(arr,++little, cur++);
            }else if (arr[cur]>num){
                swap(arr,cur,--big);
            }else{
                cur++;
            }
        }
        return new int[]{little+1,big-1};
    }

    private static void swap(int[] arr,int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,4,2,5,6,9,7,5,5};
        int num =5;
        int[] res = partition(array,0,array.length-1,num);
        System.out.println(res[0]+" "+res[1]);
    }
}
