package com.cyw.常规算法题.动态规划;

/**
 * @author chenyuwei
 * @create 2020-08-21-22:17
 * 给你一个数组arr和一个整数aim，从数组中任意选择数字相加，能否得到aim，若能，返回true，否则返回false
 */
public class EqualToAim {
    /**
     * @param arr
     * @param i
     * @param sum
     * @param aim
     * @return
     * 本方法采用暴力递归的方式
     */
    public static boolean isSum(int[] arr, int i, int sum, int aim) {
        if (i == arr.length) {
            return sum == aim;
        }
        return isSum(arr, i + 1, sum, aim) || isSum(arr, i + 1, sum + arr[i], aim);
    }

    /**
     *
     * @param arr
     * @param aim
     * @return
     * 本方法运用动态规划算法，解决了递归方式中的大量重复计算
     */
    public static boolean isSum_V2(int[] arr, int aim) {
        if (arr == null)
            return false;
        int total = 0;
        for (int n = 0; n < arr.length; n++) {
            total += arr[n];
        }
        if (total < aim) {
            return false;
        }
        Boolean[][] dp = new Boolean[arr.length + 1][total + 1];
        for (int n = 0; n < total + 1; n++) {
            if (n == aim) {
                dp[arr.length][n] = true;
            } else {
                dp[arr.length][n] = false;
            }
        }
        for (int m = dp.length - 2; m >= 0; m--) {
            for (int n = dp[0].length-1; n >= 0; n--) {
                if (n+arr[m]>=dp[0].length){
                    dp[m][n] = dp[m+1][n];
                }else{
                    dp[m][n] = dp[m + 1][n] || dp[m + 1][n + arr[m]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,1,5};
        int aim = 7;
        System.out.println(isSum(arr, 0, 0, aim));
        System.out.println(isSum_V2(arr, aim));
//        isSum_V2(arr,aim);
    }
}
