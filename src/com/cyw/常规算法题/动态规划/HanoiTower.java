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
     * @return 返回移动的步数
     */
    public static int process(int N, String from, String to, String help) {
        if (N == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return 1;
        } else {
            int part1 = process(N - 1, from, help, to);
            System.out.println("move " + N + " from " + from + " to " + to);
            int part2 = 1;
            int part3 = process(N - 1, help, to, from);
            return part1+part2+part3;
        }
    }

    /**
     * 汉诺塔问题变换，塔必须借助中间柱子到右边
     * @param N
     * @param left
     * @param mid
     * @param right
     * @param from
     * @param to
     * @return
     */
    public static int process_v2(int N, String left, String mid,String right,String from, String to){
        if (N<1){
            return 0;
        }
        //递归出口，当只有一层塔时，若这一层塔在中间柱子，那么只需要移动一步，否则就需要移动两步。
        if (N==1){
            if (from.equals(mid)||to.equals(mid)){
                System.out.println("move 1 from "+from+" to "+to);
                return 1;
            }else{
                System.out.println("move 1 from "+from+" to "+mid);
                System.out.println("move 1 from "+mid+" to "+to);
                return 2;
            }
        }
        if (from.equals(mid)||to.equals(mid)){
            String other = (from.equals(left)||to.equals(left))?right:left;
            int part1 = process_v2(N-1,left,mid,right,from,other);
            int part2 = 1;
            System.out.println("move " +N+" from "+from+" to "+to);
            int part3 = process_v2(N-1,left,mid,right,other,to);
            return part1+part2+part3;
        }else{
            int part1 = process_v2(N-1,left,mid,right,from,to);
            int part2 = 1;
            System.out.println("move "+N+" from "+from+" to "+mid);
            int part3 = process_v2(N-1,left,mid,right,to,from);
            int part4 = 1;
            System.out.println("move "+N+" from "+mid+" to "+to);
            int part5 = process_v2(N-1,left,mid,right,from,to);
            return part1+part2+part3+part4+part5;
        }
    }
    public static void main(String[] args) {
        int steps = process(3, "左", "右", "中");
        System.out.println(steps);
        int steps2 = process_v2(3,"左","中","右","左","右");
        System.out.println(steps2);
    }
}
