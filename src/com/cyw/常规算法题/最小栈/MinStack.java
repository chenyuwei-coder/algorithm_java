package com.cyw.常规算法题.最小栈;

import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-07-12-14:43
 */
public class MinStack {
    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    /**
     * 入栈
     *
     * @param element
     */
    public void push(int element) {
        mainStack.push(element);
        if (minStack.isEmpty() || minStack.peek() > element) {
            minStack.push(element);
        }
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return mainStack.pop();
    }

    public int getMin() throws Exception {
        if (mainStack.empty()) {
            throw new Exception("栈为空");
        }
        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinStack minStack = new MinStack();
        minStack.push(4);
        minStack.push(9);
        minStack.push(7);
        minStack.push(3);
        minStack.push(8);
        minStack.push(5);
        System.out.println("此时栈中的最小值为：" + minStack.getMin());
        minStack.pop();
        minStack.pop();
        minStack.pop();
        System.out.println("此时栈中的最小值为：" + minStack.getMin());
    }
}
