package com.cyw.常规算法题.动态规划;

/**
 * @author chenyuwei
 * @create 2020-08-21-16:39
 */
public class MinPath {
    /**
     * 求最短路径
     * @param matrix 初始矩阵
     * @param i 起始点的横坐标
     * @param j 起始点的纵坐标
     * @return 返回最短路径值
     * 这是暴力递归的方法，会有大量重复的计算步骤
     */
    public static int walk(int[][] matrix,int i,int j){
        if (i==matrix.length-1&&j==matrix[0].length-1){
            return matrix[i][j];
        }
        if (i==matrix.length-1){
            return matrix[i][j]+walk(matrix,i,j+1);
        }
        if (j==matrix[0].length-1){
            return matrix[i][j]+walk(matrix,i+1,j);
        }
        return matrix[i][j]+Math.min(walk(matrix,i,j+1),walk(matrix,i+1,j));
    }

    /**
     * 求最短路径
     * @param matrix 初始矩阵
     * @return 返回最短路径
     * 本方法采用动态规划的方法，使用一张二维表来存储计算结果，减少了重复计算
     */
    public static int walk_v2(int[][] matrix){
        if (matrix==null||matrix.length==0||matrix[0]==null||matrix[0].length==0){
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        for (int i=1;i<matrix.length;i++){
            dp[i][0] = dp[i-1][0]+matrix[i][0];
        }
        for (int j=1;j<matrix[0].length;j++){
            dp[0][j] = dp[0][j-1]+matrix[0][j];
        }
        for (int i=1;i<matrix.length;i++){
            for (int j=1;j<matrix[0].length;j++){
                dp[i][j] = matrix[i][j]+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[matrix.length-1][matrix[0].length-1];
    }
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = generateRandomMatrix(10,10);
        System.out.println(walk(matrix,0,0));
        System.out.println(walk_v2(matrix));
    }
}
