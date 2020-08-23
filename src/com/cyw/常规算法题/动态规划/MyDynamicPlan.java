package com.cyw.常规算法题.动态规划;

/**
 * @author chenyuwei
 * @create 2020-07-20-15:43
 */
public class MyDynamicPlan {
    /**
     * 使用动态规划算法实现金矿挖掘的最大化
     *
     * @param n 金矿数量
     * @param w 工人数量
     * @param g 表示每座金矿含量的数组
     * @param p 表示挖掘每座金矿需要的工人数量的数组
     * @return 返回最优收益
     * 这一方法代码写着简单但是效率不高，时间复杂度达到了O(2^n)
     */
    public static int getBestGoldMining(int n, int w, int[] g, int[] p) {
        if (n == 0 || w == 0) {
            return 0;
        }
        if (w < p[n - 1]) {
            return getBestGoldMining(n - 1, w, g, p);
        }
        return Math.max(getBestGoldMining(n - 1, w, g, p), getBestGoldMining(n - 1, w - p[n - 1], g, p) + g[n - 1]);
    }

    /**
     * 为了解决递归调用的效率问题，在动态规划时使用自底向上的求解方式，用一张表记录计算结果
     *
     * @param w
     * @param g
     * @param p
     * @return 这一方法在时间上已经做到了效率最高，但是在空间上可以再提高一点，使用一维数组代替二维数组
     */
    public static int getBestGoldMining_V2(int w, int[] g, int[] p) {
        //创建二维数组存储收益结果
        int[][] result = new int[g.length + 1][w + 1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i - 1]) {
                    result[i][j] = result[i - 1][j];
                } else {
                    result[i][j] = Math.max(result[i - 1][j], result[i - 1][j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        return result[g.length][w];
    }

    /**
     * 进一步节省空间，只使用一个一维数组来存储收益结果
     *
     * @param w
     * @param g
     * @param p
     * @return
     */
    public static int getBestGoldMining_V3(int w, int[] g, int[] p) {
        //创建一位数组存储收益结果
        int[] result = new int[w + 1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if (j >= p[i - 1]) {
                    result[j] = Math.max(result[j], result[j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        return result[w];
    }

    public static void main(String[] args) {
        int w = 10;// 工人数量
        int[] p = {5, 5, 3, 4, 3};//挖掘每座金矿需要的工人
        int[] g = {400, 500, 200, 300, 350};// 每座金矿的含量
        System.out.println(" 最优收益：" + getBestGoldMining(g.length, w, g, p));
        System.out.println(" 最优收益：" + getBestGoldMining_V2(w, g, p));
        System.out.println(" 最优收益：" + getBestGoldMining_V3(w, g, p));
    }
}
