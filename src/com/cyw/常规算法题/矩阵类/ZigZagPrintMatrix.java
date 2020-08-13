package com.cyw.常规算法题.矩阵类;

/**
 * @author chenyuwei
 * @create 2020-08-04-15:21
 * "之"字形打印矩阵
 */
public class ZigZagPrintMatrix {
    public static void printZigZagMatrix(int[][] matrix) {
        int aR = 0;
        int aC = 0;
        int bR = 0;
        int bC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (aR != endR + 1) {
            printDiagonal(matrix, aR, aC, bR, bC, fromUp);
            aR = aC == endC ? aR + 1 : aR;
            aC = aC == endC ? aC : aC + 1;
            bC = bR == endR ? bC + 1 : bC;
            bR = bR == endR ? bR : bR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    private static void printDiagonal(int[][] matrix, int aR, int aC, int bR, int bC, boolean fromUp) {
        if (fromUp) {
            while (aR != bR + 1) {
                System.out.print(matrix[aR++][aC--] + " ");
            }
        } else {
            while (bC != aC + 1) {
                System.out.print(matrix[bR--][bC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printZigZagMatrix(matrix);
    }
}
