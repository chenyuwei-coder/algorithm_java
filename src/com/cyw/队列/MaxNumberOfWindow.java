package com.cyw.队列;

import java.util.LinkedList;

/**
 * @author chenyuwei
 * @create 2020-09-07-19:49
 * 生成窗口最大值数组
 * 有一个整型数组arr和一个大小为w的窗口从数组最左边滑到最右边，窗口每次向右边滑一个位置，记录每一次窗口内的最大值
 */
public class MaxNumberOfWindow {
    //这是最直接的一种方法，时间复杂度为O(m*n),还不是最优的。
    public static int[] getMaxNumberOfWindow(int[] array, int w) {
        if (array == null || array.length < w || w < 1) {
            return null;
        }
        int[] result = new int[array.length - w + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= array.length - w; i++) {
            for (int j = i; j < i + w; j++) {
                if (array[j] > max) {
                    max = array[j];
                }
                result[i] = max;
            }
        }
        return result;
    }

    //使用双端队列来模拟窗口的滑动
    public static int[] getMaxNumberOfWindow_V2(int[] array, int w) {
        if (array == null || array.length < w || w < 1) {
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        int[] result = new int[array.length - w + 1];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            while (!list.isEmpty() && array[list.peekLast()] <= array[i]) {
                list.pollLast();
            }
            list.addLast(i);
            if (list.peekFirst() == i - w) {
                list.pollFirst();
            }
            if (i >= w - 1) {
                result[index++] = array[list.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] result = getMaxNumberOfWindow(array, 3);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
