package com.cyw.常规算法题.大整数相加;

/**
 * @author chenyuwei
 * @create 2020-07-19-23:35
 */
public class MySumOfBigNumber {
    public static String bigNumberSum(String bigNumberA, String bigNumberB) {
        int maxLength = bigNumberA.length() > bigNumberB.length() ? bigNumberA.length() : bigNumberB.length();
        int[] arrayA = new int[maxLength + 1];
        int[] arrayB = new int[maxLength + 1];
        int[] result = new int[maxLength + 1];
        for (int i = 0; i < bigNumberA.length(); i++) {
            arrayA[i] = bigNumberA.charAt(bigNumberA.length() - 1 - i) - '0';//减'0'是为了将char类型转换为int类型
        }
        for (int i = 0; i < bigNumberB.length(); i++) {
            arrayB[i] = bigNumberB.charAt(bigNumberB.length() - 1 - i) - '0';
        }
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];
            if (temp >= 10) {
                temp = temp - 10;
                result[i + 1] = 1;
            }
            result[i] = temp;
        }
        //把result数组再次逆序，并转成String类型
        StringBuilder sb = new StringBuilder();
        boolean findFirst = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (!findFirst) {
                if (result[i] == 0) {
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(bigNumberSum("426709752318", "95481253129"));
    }
}
