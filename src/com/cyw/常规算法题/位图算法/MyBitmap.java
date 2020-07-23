package com.cyw.常规算法题.位图算法;

/**
 * @author chenyuwei
 * @create 2020-07-21-23:27
 */
public class MyBitmap {
    private long[] words;
    private int size;

    public MyBitmap(int size) {
        this.size = size;
        this.words = new long[getWordIndex(size - 1) + 1];
    }

    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超出Bitmap的范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超出Bitmap的范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    private int getWordIndex(int i) {
        return i >> 6;
    }

    public static void main(String[] args) {
        MyBitmap bitMap = new MyBitmap(128);
        bitMap.setBit(126);
        bitMap.setBit(75);
        System.out.println(bitMap.getBit(126));
        System.out.println(bitMap.getBit(78));
    }
}
