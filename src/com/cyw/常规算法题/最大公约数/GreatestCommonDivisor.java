package com.cyw.常规算法题.最大公约数;

/**
 * @author chenyuwei
 * @create 2020-07-16-10:46
 */
public class GreatestCommonDivisor {
    /**
     * 获取最大公约数
     * @param a
     * @param b
     * @return 返回最大公约数
     * 这是最初级，效率最低的版本
     */
    public static int getGreatestCommonDivisor(int a, int b){
        int big = a>b? a:b;
        int small = a<b? a:b;
        if (big%small==0){
            return small;
        }
        for (int i=small/2;i>1;i--){
            if (small%i==0 && big%i==0){
                return i;
            }
        }
        return 1;
    }

    /**
     * 获取最大公约数
     * @param a
     * @param b
     * @return
     * 利用辗转相除法，提高了效率，但是当两个数很大时，取模运算比较耗时
     */
    public static int getGreatestCommonDivisorV2(int a, int b){
        int small = a<b?a:b;
        int big = a>b?a:b;
        if (big%small == 0){
            return small;
        }
        return getGreatestCommonDivisorV2(big%small, small);
    }

    /**
     * 获取最大公约数
     * @param a
     * @param b
     * @return
     * 利用移位运算代替取模，提高效率，结合更相减损术
     */
    public static int getGreatestCommonDivisorV3(int a, int b){
        if (a == b)
            return a;
        if ((a&1)==0 && (b&1)==0){
            return getGreatestCommonDivisorV3(a>>1,b>>1)<<1;
        }else if ((a&1)==0 && (b&1)!=0){
            return getGreatestCommonDivisorV3(a>>1,b);
        }else if ((a&1)!=0 && (b&1)==0){
            return getGreatestCommonDivisorV3(a,b>>1);
        }else {
            int big = a>b? a:b;
            int small = a<b? a:b;
            return getGreatestCommonDivisorV3(big-small,small);
        }
    }
    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor(50,70));
        System.out.println(getGreatestCommonDivisorV2(50,70));
        System.out.println(getGreatestCommonDivisorV3(50,70));
    }
}
