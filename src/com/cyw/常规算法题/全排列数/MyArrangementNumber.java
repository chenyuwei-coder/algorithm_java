package com.cyw.常规算法题.全排列数;

import java.util.Arrays;

/**
 * @author chenyuwei
 * @create 2020-07-17-11:27
 */
public class MyArrangementNumber {
    /**
     * 找出数字全排列的比它大且仅比它大的数字
     * @param numbers
     * @return
     * 字典序算法
     */
    public static int[] findNearestNumber(int[] numbers){
        //1.从后向前查看逆序区域，找到逆序的边界
        int index = findTransferPoint(numbers);
        if (index == 0)
            return null;
        //2.将逆序边界的前一位与逆序区域中的仅大于它的数字交换
        int[] numbersCopy = Arrays.copyOf(numbers,numbers.length);
        exchangeHead(numbersCopy,index);
        //3.把原来逆序的区域调整为正序
        reverse(numbersCopy,index);
        return numbersCopy;
    }

    private static int [] reverse(int[] numbersCopy, int index) {
        for (int i=numbersCopy.length-1,j=index;i>j;i--,j++){
            int temp = numbersCopy[i];
            numbersCopy[i] = numbersCopy[j];
            numbersCopy[j] = temp;
        }
        return numbersCopy;
    }

    private static int[] exchangeHead(int[] numbersCopy, int index) {
        int head = numbersCopy[index-1];
        for (int i=numbersCopy.length-1;i>=index;i--){// 这里的i>=index要特别注意
            if (head<numbersCopy[i]){
                numbersCopy[index-1] = numbersCopy[i];
                numbersCopy[i] = head;
                break;
            }
        }
        return numbersCopy;
    }

    private static int findTransferPoint(int[] numbers) {
        for (int i=numbers.length-1;i>0;i--){
            if (numbers[i]>numbers[i-1]){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2,1,4,3,5};
        for (int i=0;i<10;i++){
            numbers = findNearestNumber(numbers);
            outPut(numbers);
        }
    }

    private static void outPut(int[] numbers) {
        for (int number:numbers){
            System.out.print(number);
        }
        System.out.println();
    }
}
