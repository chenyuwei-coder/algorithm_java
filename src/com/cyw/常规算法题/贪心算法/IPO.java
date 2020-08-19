package com.cyw.常规算法题.贪心算法;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chenyuwei
 * @create 2020-08-19-21:32
 * 给定初始资金w，做项目需要的代价数组cost{...}，收益数组profit{...}，做k个项目，怎样选择项目能让资金最大化
 * 这个问题实质上就是背包问题的变形
 */
public class IPO {
    public static class Item {
        public int cost;
        public int profit;

        public Item(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
    //实现比较器，用于构建堆
    public static class MinCostComparator implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.cost-o2.cost;
        }
    }
    public static class MaxProfitComparator implements Comparator<Item>{
        @Override
        public int compare(Item o1, Item o2) {
            return o2.profit-o1.profit;
        }
    }

    /**
     * 求最大化资金
     *
     * @param costs   代价数组
     * @param profits 收益数组
     * @param k       可以做的最大项目数量
     * @param w       资金
     * @return 最大化资金
     */
    public static int findMaximizedCapital(int[] costs, int[] profits, int k, int w) {
        if (costs.length != profits.length || k <= 0) {
            return w;
        }
        //将代价和收益都封装到每个项目中
        Item[] items = new Item[costs.length];
        for (int i=0;i<costs.length;i++){
            items[i].cost = costs[i];
            items[i].profit = profits[i];
        }
        //构建关于代价的小根堆和关于收益的大根堆
        PriorityQueue<Item> minCostHeap = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Item> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());
        for (int i=0;i<items.length;i++){
            minCostHeap.add(items[i]);
        }
        for (int i=0;i<k;i++){
            while (!minCostHeap.isEmpty()&&minCostHeap.peek().cost<=w){
                maxProfitHeap.add(minCostHeap.poll());
            }
            //当大根堆为空时表示没有可以做的项目
            if (maxProfitHeap.isEmpty()){
                return w;
            }
            w+=maxProfitHeap.poll().profit;
        }
        return w;
    }
}
