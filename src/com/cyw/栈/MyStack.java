package com.cyw.栈;

/**
 * @author chenyuwei
 * @create 2020-06-29-16:36
 */
public class MyStack {
    private final int DEFAULT_STACK_SIZE = 10;
    private int top = -1;
    // 用数组实现栈
    String[] objects = null;
    public MyStack(){
        objects =  new String[DEFAULT_STACK_SIZE];
    }

    /**
     * 入栈
     * @param data 入栈的元素
     */
    public void push(String data){
        if(top >= DEFAULT_STACK_SIZE-1){
            throw new IllegalStateException("栈已满");
        }
        objects[++top] = data;
    }

    /**
     * 出栈
     * @return 返回出栈的元素
     */
    public String pop(){
        if(isEmpty()){
            return null;
        }
        return objects[top--];
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public String peek(){
        if(isEmpty()){
            return null;
        }
        return objects[top];
    }

    /**
     * 清空栈
     */
    public void clear(){
        objects = new String[DEFAULT_STACK_SIZE];
        top = -1;
    }
    /**
     * 判断栈是否为空
     * @return 返回true 或 false
     */
    private boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push("小");
        myStack.push("学");
        myStack.push("姐");
        myStack.push("是");
        myStack.push("渣");
        myStack.push("男");
        while (!myStack.isEmpty()){
            System.out.print(myStack.pop()+" ");
        }
    }
}
