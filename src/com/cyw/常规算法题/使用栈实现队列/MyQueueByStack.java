package com.cyw.常规算法题.使用栈实现队列;

import java.util.Stack;

/**
 * @author chenyuwei
 * @create 2020-07-16-16:22
 */
public class MyQueueByStack {
    private  Stack<Integer> stackA = new Stack();
    private  Stack<Integer> stackB = new Stack();

    /**
     * 入队
     * @param element
     */
    public  void enQueue(int element){
        stackA.push(element);
    }

    /**
     * 出队
     * @return
     */
    public Integer deQueue() throws Exception {
        //判断栈B是否为空，如果为空，再判断栈A是否为空，不为空则进行转移，若为空则说明队列为空
        if (stackB.isEmpty()){
            if (stackA.isEmpty()){
                throw new Exception("队列为空");
            }
            transfer();
        }
        return stackB.pop();
    }

    private void transfer() {
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        MyQueueByStack stack = new MyQueueByStack();
        stack.enQueue(1);
        stack.enQueue(2);
        stack.enQueue(3);
        System.out.println(stack.deQueue());
        stack.enQueue(4);
        stack.enQueue(5);
        System.out.println(stack.deQueue());
        System.out.println(stack.deQueue());
    }
}
