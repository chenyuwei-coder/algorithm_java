package com.cyw.常规算法题.动态规划;

/**
 * @author chenyuwei
 * @create 2020-08-20-21:01
 * 打印字符串的所有子串
 */
public class AllSubString {
    public static void printAllSub(char[] str,int i,String result){
        if (i==str.length){
            System.out.println(result);
            return;
        }
        printAllSub(str,i+1,result);
        printAllSub(str,i+1,result+String.valueOf(str[i]));
    }

    public static void main(String[] args) {
        String test = "abcde";
        printAllSub(test.toCharArray(),0,"");
    }
}
