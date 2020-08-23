package com.cyw.常规算法题.动态规划;

import java.util.HashSet;

/**
 * @author chenyuwei
 * @create 2020-08-20-22:39
 * 打印字符串的全排列
 */
public class AllPermutations {
    /**
     * 打印字符的全排列，包含重复字符串
     * @param str
     */
    public static void printAllPermutations(String str){
        char[] chars = str.toCharArray();
        process(chars,0);
    }

    private static void process(char[] chars, int i) {
        if (i==chars.length){
            System.out.println(String.valueOf(chars));
            return;
        }
        for (int j=i;j<chars.length;j++){
            swap(chars,i,j);//让i后面的元素依次和i位置的元素交换
            process(chars,i+1);
            swap(chars,i,j);//交换之后需要将数组还原
        }
    }

    /**
     * 打印字符的全排列，不包含重复
     * @param str
     */
    public static void printAllPermutation_V2(String str){
        char[] chars = str.toCharArray();
        process1(chars,0);
    }

    private static void process1(char[] chars, int i) {
        if (i==chars.length){
            System.out.println(String.valueOf(chars));
        }
        HashSet<Character> set = new HashSet<>();
        for (int j=i;j<chars.length;j++){
            if (!set.contains(chars[j])){//一旦j位置的字符与i位置的字符重复，就跳过，不用交换
                set.add(chars[j]);
                swap(chars,i,j);
                process1(chars,i+1);
                swap(chars,i,j);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String test = "aac";
        printAllPermutation_V2(test);
    }
}
