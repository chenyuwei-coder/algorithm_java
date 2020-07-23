package com.cyw.常规算法题.删去K个数字后的最小值;

/**
 * @author chenyuwei
 * @create 2020-07-19-15:04
 */
public class MyCutOfMinNumber {
    /**
     * 取出一个数中的k个数字后，使得得到的数尽可能小
     *
     * @param num
     * @param K
     * @return 在这里要知道数字的进位比数字本身的大小具有更大的影响力，基本思想技术从左到右遍历，找到一个比
     * 右边数字大的数，删除后，让较小的数进位。
     * 该方法存在两个改进点：1.每次删除一个数之后都要从头重新开始遍历，实际上我们可以接着往下遍历，
     * 不需要从头开始
     * 2.substring方法的性能不佳，应该尽可能少的调用
     */
    public static String removeKDigits(String num, int K) {
        String newNum = num;
        for (int i = 0; i < K; i++) {
            boolean hasCut = false;
            for (int j = 0; j < newNum.length() - 1; j++) {
                if (newNum.charAt(j) > newNum.charAt(j + 1)) {
                    newNum = newNum.substring(0, j) + newNum.substring(j + 1, newNum.length());
                    hasCut = true;
                    break;
                }
            }
            //如果遍历完也没有可删除的数字，那就去掉最末尾的数字
            if (!hasCut) {
                newNum = newNum.substring(0, newNum.length() - 1);
            }
            //去掉数字前面的0
            newNum = removeZero(newNum);
        }
        if (newNum.length() == 0)
            return "0";
        return newNum;
    }

    private static String removeZero(String newNum) {
        for (int i = 0; i < newNum.length() - 1; i++) {
            if (newNum.charAt(0) != '0') {
                break;
            }
            newNum = newNum.substring(1, newNum.length());
        }
        return newNum;
    }

    public static String removeKDigits_V2(String num, int K) {
        //新整数的最终长度
        int newNumLength = num.length()-K;
        //非零数字的索引
        int offset = 0;
        //创建一个数组接收所有的数字
        char[] stack = new char[num.length()];
        int top = 0;
        for (int i=0;i<num.length();i++){
            char c = num.charAt(i);
            while (top>0 && stack[top-1] > c && K>0){
                top-=1;
                K-=1;
            }
            stack[top++] = c;
        }
        while (offset<newNumLength && stack[offset]=='0'){
            offset++;
        }
        return offset==newNumLength? "0": new String(stack,offset,newNumLength-offset);
    }

    public static void main(String[] args) {
        System.out.println(removeKDigits("1593212", 3));
        System.out.println(removeKDigits("30200", 1));
        System.out.println(removeKDigits("541270936", 3));
        System.out.println(removeKDigits_V2("541270936", 3));
    }
}
