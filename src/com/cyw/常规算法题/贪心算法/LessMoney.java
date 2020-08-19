package com.cyw.常规算法题.贪心算法;

import java.util.PriorityQueue;

/**
 * @author chenyuwei
 * @create 2020-08-19-17:17
 * 一块金条切成两半需要花费和长度数值一样的铜板，比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板
 * 一群人想整分一块金条，怎么分最省铜板？
 * 例如：给定数组{10,20,30}，代表一共三个人，整块金条长度为10+20+30=60，金条要分成10,20,30三个部分。如果先把
 * 长度60的金条分成长度为10和50，花费60，再把长度50的金条分成20和30花费50，一共花费110铜板。但是，如果先把金
 * 条分成30和30，花费60，再把30分成10和20花费30，一共花费90铜板。
 * 输入一个数组，返回分割的最小代价
 */
public class LessMoney {
    /**
     * 求分割金条的最小代价
     * @param arr
     * @return
     * 解决该题最好的办法就是借助哈夫曼树，每次将数组中的最小的两个值相加后扔回数组中，再选择最小的两个值相加
     * 由此，我们可以使用小根堆来完成。
     */
    public static int lessMoney(int[] arr){
        if (arr==null||arr.length<=0)
            return 0;
        //使用数组建立小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i=0;i<arr.length;i++){
            priorityQueue.add(arr[i]);
        }
        int cur = 0;
        int sum = 0;
        while (priorityQueue.size()>1){
            cur = priorityQueue.poll()+priorityQueue.poll();
            sum+=cur;
            priorityQueue.add(cur);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10,20,30};
        System.out.println(lessMoney(arr));
    }
}
