package com.cyw.常规算法题.哈希;

/**
 * @author chenyuwei
 * @create 2020-08-17-16:53
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上下左右四个位置相连，
 * 如果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中有多少个岛
 */
public class Island {
    public static int countIslands(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int x = matrix.length;
        int y = matrix[0].length;
        int res = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    infect(matrix, i, j, x, y);
                }
            }
        }
        return res;
    }

    /**
     * 感染函数，将连成一片的1感染为2,
     *
     * @param matrix
     * @param i
     * @param j
     * @param x
     * @param y
     */
    private static void infect(int[][] matrix, int i, int j, int x, int y) {
        if (i < 0 || i >= x || j < 0 || j >= y || matrix[i][j] != 1) {
            return;
        }
        matrix[i][j] = 2;
        infect(matrix, i - 1, j, x, y);
        infect(matrix, i + 1, j, x, y);
        infect(matrix, i, j - 1, x, y);
        infect(matrix, i, j + 1, x, y);
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m1));

        int[][] m2 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m2));
    }
}
