package com.cyw.常规算法题.经典算法;

/**
 * @author chenyuwei
 * @create 2020-08-23-20:16
 * 给定长短两个字符串str1和str2，判断str1是否包含str2，若包含，返回在str1中的起始位置
 */
public class KMP {
    /**
     * 判断是否包含子串
     * @param string1 长字符串
     * @param string2 短字符串
     * @return 返回一个索引值
     */
    public static int getIndexOf(String string1, String string2) {
        if (string1 == null || string1.length() == 0 || string2 == null || string2.length() == 0) {
            return -1;
        }
        char[] str1 = string1.toCharArray();
        char[] str2 = string2.toCharArray();
        int[] next = getNextArray(str2);
        int i = 0, j = 0;//i是str1的指针，j是str2的指针
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == str2.length ? i - j : -1;
    }

    /**
     * 求next数组
     * @param str2 给定一个字符串
     * @return 返回一个整型数组
     */
    private static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int index = 0;
        int i = 2;
        while (i < next.length) {
            if (str2[i - 1] == str2[index]) {
                next[i++] = ++index;
            } else if (index > 0) {
                index = next[index];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "abcadef";
        String str2 = "cad";
        int index = getIndexOf(str1,str2);
        System.out.println(index);
    }
}
