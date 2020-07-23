package com.cyw.常规算法题.判断二的整数次幂;

/**
 * @author chenyuwei
 * @create 2020-07-16-14:27
 */
public class MyPowerOfTwo {
    /**
     * 判断一个数是否是2的整数次幂
     * @param num
     * @return
     */
    public static boolean isPowerOfTwo(int num){
        int temp = 1;
        while (temp<=num){
            if (temp == num)
                return true;
            temp = temp<<1;
        }
        return false;
    }

    /**
     * 判断一个数是否是2的整数次幂
     * @param num
     * @return
     * 进阶版，利用与运算将时间复杂度降低到了O(1)
     */
    public static boolean isPowerOfTwoV2(int num){
        return (num&num-1)==0;
    }
    public static void main(String[] args) {
        if (isPowerOfTwo(1024)){
            System.out.println("1024是2的整数次幂");
        }else{
            System.out.println("不是");
        }
    }
}
