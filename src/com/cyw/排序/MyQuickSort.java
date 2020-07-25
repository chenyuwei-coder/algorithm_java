package com.cyw.排序;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-07-08-17:06
 */
public class MyQuickSort {
    /**
     * 快速排序（递归调用）
     *
     * @param array
     * @param startIndex
     * @param endIndex
     */
    public static void quickSort(int[] array, int startIndex, int endIndex) {
        //递归结束的条件
        if (startIndex >= endIndex)
            return;
        int pivotIndex = partition1(array, startIndex, endIndex);
        quickSort(array, startIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, endIndex);
    }

    /**
     * 快速排序（使用栈实现非递归调用）
     *
     * @param array
     * @param startIndex
     * @param endIndex
     */
    public static void quickSortWithStack(int[] array, int startIndex, int endIndex) {
        Stack<Map<String, Integer>> quickStack = new Stack<Map<String, Integer>>();
        Map<String, Integer> rootParam = new HashMap<>();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickStack.push(rootParam);
        while (!quickStack.isEmpty()) {
            Map<String, Integer> param = quickStack.pop();
            int pivotIndex = partition1(array, param.get("startIndex"), param.get("endIndex"));
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickStack.push(leftParam);
            }
            if (pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickStack.push(rightParam);
            }
        }
    }

    /**
     * 分治（双边循环）
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partition(int[] array, int startIndex, int endIndex) {
        int left = startIndex, right = endIndex;
        int pivot = array[startIndex];
        while (left != right) {
            while (left < right && array[right] > pivot) {
                right--;
            }
            while (left < right && array[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
            }
        }
        //pivot和左右指针重合点交换
        array[startIndex] = array[left];
        array[left] = pivot;
        return left;
    }

    /**
     * 分治（单边循环）
     *
     * @param array
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int partition1(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark++;
                int temp = array[mark];
                array[mark] = array[i];
                array[i] = temp;
            }
        }
        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{4,4,6,5,3,2,8,1};
//        quickSort(array,0,array.length-1);
//        System.out.println(Arrays.toString(array));
//        int[] array1 = new int[]{4,7,6,5,3,2,8,1};
//        quickSortWithStack(array1,0,array1.length-1);
//        System.out.println(Arrays.toString(array1));
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = CompareTool.getRandomArray(maxSize, maxValue);
            int[] arr2 = CompareTool.copyArray(arr1);
            quickSort(arr1, 0, arr1.length - 1);
            CompareTool.absoluteRightSort(arr2);
            if (!CompareTool.isEqual(arr1, arr2)) {
                succeed = false;
                CompareTool.printArray(arr1);
                CompareTool.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }
}
