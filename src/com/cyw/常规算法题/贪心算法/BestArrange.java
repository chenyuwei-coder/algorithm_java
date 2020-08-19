package com.cyw.常规算法题.贪心算法;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chenyuwei
 * @create 2020-08-19-22:27
 *一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。给你每一个项目开始时间和结束时间
 * 怎样安排能进行最多场次的宣讲，返回最多的宣讲场次
 * 思路：使用贪心算法，每次都选择结束时间最早的宣讲会
 */
public class BestArrange {
    public static class Program{
        public int start;
        public int end;

        public Program(int start,int end){
            this.start = start;
            this.end =end;
        }
    }
    //比较器
    public static class ProgramComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end-o2.end;
        }
    }
    public static int bestArrange(Program[] programs,int curTime){
        Arrays.sort(programs,new ProgramComparator());
        int result=0;
        for (int i=0;i<programs.length;i++){
            if (curTime<=programs[i].start){
                result++;
                curTime = programs[i].end;
            }
        }
        return result;
    }
}
