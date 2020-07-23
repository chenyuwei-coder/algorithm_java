package com.cyw.栈;

/**
 * @author chenyuwei
 * @create 2020-06-29-18:17
 */
public class LLStack {
    private static class LLNode{
        Object data;
        LLNode next;
        public LLNode(Object data){
            this.data = data;
        }
    }
    LLNode headNode;
    public LLStack(){
        headNode = new LLNode(null);
    }

    /**
     * 入栈
     * @param data
     */
    public void push(Object data){
        if(headNode.data == null){
            headNode.data = data;
        }else if(headNode == null){
            headNode = new LLNode(data);
        }else{
            LLNode NewNode = new LLNode(data);
            NewNode.next = headNode;
            headNode = NewNode;
        }
    }

    /**
     * 出栈
     * @return
     */
    public Object pop(){
        if(headNode == null){
            System.out.println("栈为空");
            return 0;
        }
        Object data = headNode.data;
        headNode = headNode.next;
        return data;
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public Object peek(){
        if(headNode == null){
            System.out.println("栈为空");
            return 0;
        }
        return headNode.data;
    }
    public boolean isEmpty(){
        return headNode == null;
    }

    public static void main(String[] args) {
        LLStack llStack = new LLStack();
        llStack.push(1);
        llStack.push(2);
        llStack.push(3);
        while (!llStack.isEmpty()){
            System.out.println(llStack.pop()+" ");
        }
    }
}
