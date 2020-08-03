package com.cyw.队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chenyuwei
 * @create 2020-08-03-16:28
 * 栈和队列的相互转换
 */
public class StackAndQueueConvert {
    public static class StackByQueue {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public StackByQueue() {
            this.data = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int element) {
            data.add(element);
        }

        public int pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("stack is null");
            }
            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        public int peek() {
            if (data.isEmpty()) {
                throw new RuntimeException("stack is null");
            }
            while (data.size() != 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        public boolean isEmpty() {
            return data.size() == 0 ? true : false;
        }

        private void swap() {
            Queue<Integer> temp = help;
            help = data;
            data = temp;
        }
    }

    public static void main(String[] args) {
        StackByQueue stackByQueue = new StackByQueue();
        stackByQueue.push(1);
        stackByQueue.push(2);
        stackByQueue.push(3);
        stackByQueue.push(4);
        stackByQueue.push(5);
        stackByQueue.pop();
        while (!stackByQueue.isEmpty()){
            System.out.println(stackByQueue.pop());
        }
    }
}
