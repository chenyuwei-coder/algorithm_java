package com.cyw.数组的插入;

/**
 * @author chenyuwei
 * @create 2020-06-28-14:34
 */
public class MyArray {
    private int array[];
    private int size; //size表示数组中实际的元素个数

    public MyArray(int capacity){
        this.array = new int[capacity];
        size = 0;
    }

    /**
     * 在数组中间插入元素
     * @param index 插入的位置
     * @param element 插入的元素
     */
    public void insert(int element, int index) throws Exception{
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("插入的位置索引超出数组的范围");
        }
        // 如果数组已经满了，还想要插入元素的话，就需要将数组扩容
        if(size>=array.length){
            resize();
        }
        // 从右向左，将index及其以后的元素都向右移动一个位置
        for(int i=size-1; i>=index; i--){
            array[i+1] = array[i];
        }
        // 腾出的地方插入元素
        array[index] = element;
        size++;
    }

    /**
     * 数组的扩容
     */
    private void resize() {
        int[] NewArray = new int[array.length*2];
        System.arraycopy(array,0,NewArray,0,array.length);
        array = NewArray;
    }

    /**
     * 删除数组中的元素
     * @param index 索引值
     * @return 返回被删除的元素
     */
    public int delete(int index){
        // 判断索引是否越界
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException("索引超出元素范围");
        }
        int deleteElement = array[index];
        for(int i=index;i<size-1;i++){
            array[i]= array[i+1];
        }
        size--;
        return deleteElement;
    }
    /**
     * 输出数组
     */
    public void output(){
        for(int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        MyArray myArray = new MyArray(4);
        myArray.insert(3,0);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.output();
        System.out.println("被删除的元素："+myArray.delete(0));
        myArray.output();
    }
}
