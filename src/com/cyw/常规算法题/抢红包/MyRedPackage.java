package com.cyw.常规算法题.抢红包;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chenyuwei
 * @create 2020-07-23-11:20
 */
public class MyRedPackage {
    /**
     * 抢红包算法
     * @param totalMoney 金额
     * @param totalPeople 人数
     * @return 返回一个随机分好红包金额的集合
     * 比较取巧的是，为了防止并发操作引起的错误，红包并不是在抢的时候才计算的，而是提前分好了红包
     * 并将之存储到一个数组或集合中，然后让人凭运气抢。
     * 需要注意的是要采取算法保证每次红包金额划分的公平性，该方法采取“二倍均值法”
     * 每次抢到的金额=随机区间[0.01，剩余金额/剩余人数*2-0.01]元
     */
    public static List<Integer> divideRedPackage(Integer totalMoney, Integer totalPeople){
        //创建一个集合用于存放随机划分好的红包
        List<Integer> amountList = new ArrayList<>();
        Integer restMoney = totalMoney;
        Integer restPeople = totalPeople;
        Random random = new Random();
        for (int i=0;i<totalPeople-1;i++){
            int amountMoney = random.nextInt(restMoney/restPeople*2-1)+1;
            amountList.add(amountMoney);
            restMoney = restMoney-amountMoney;
            restPeople--;
        }
        amountList.add(restMoney);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(1000,10);
        for (Integer amount:amountList){
            System.out.println("抢到的金额："+new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }
}
