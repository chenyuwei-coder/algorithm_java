package com.cyw.常规算法题.动态规划;

/**
 * @author chenyuwei
 * @create 2020-08-20-10:46
 * 汉诺塔问题
 */
public class HanoiTower {
    /**
     * 打印汉诺塔的移动步骤
     *
     * @param N    层数
     * @param from 初始停留的柱
     * @param to   要移动到的柱
     * @param help 用于帮助移动的柱
     */
    public static void process(int N, String from, String to, String help) {
        if (N == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            process(N - 1, from, help, to);
            System.out.println("move " + N + " from " + from + " to " + to);
            process(N - 1, help, to, from);
        }
    }

    public static void main(String[] args) {
        process(3, "左", "右", "中");
    }
}
