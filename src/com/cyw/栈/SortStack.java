package com.cyw.栈;

import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-09-07-14:43
 * 一个栈中元素的类型为整型，现在要将该栈从顶到底按从小到大的顺序排序，只许申请一个栈
 */
public class SortStack {
    /**
     * 对栈进行排序
     * @param stack 输入一个无序的栈
     * 将stack栈的栈顶弹出与help栈的栈顶进行比较，如果help栈顶元素小，就将栈顶元素压入stack中，直到栈顶元素等于或大于时
     * 将其压入help中，最后再将所有的元素从help压入stack中。
     */
    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }
}
