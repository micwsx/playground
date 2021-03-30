package com.enjoy.algorithm.practice;

/**
 * @Author wu
 * @Date 3/27/2021 6:19 PM
 * @Version 1.0
 */
public class GreatestCommonDivisor {

    /**
     * 获取2数最大公约数(a,b);
     * 1.根据欧几里德算法 a,b最大公约数（a>b）=a % b 结果与b的最大公约数。e.g. 25%10=5， 求5与10的最大公约数。
     *
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisor(int a, int b) {
        int big = a > b ? a : b;
        int small = a > b ? b : a;
        int mod = big % small;
        if (mod == 0) return small;
        return getGreatestCommonDivisor(mod, small);
    }

    /**
     * 获取2数最大公约数(a,b);
     * 2.更相减损术： (a>b), a-b的差值c和较小数b的最大公约数。
     *
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisorV2(int a, int b) {
        if (a == b) return a;//直接这两个值相等
        int big = a > b ? a : b;
        int small = a > b ? b : a;
        return getGreatestCommonDivisorV2(big - small, small);
    }


    /**
     * 终极方案; 获取2数最大公约数(a,b);
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        if (a == b) return a;
        if ((a & 1) == 0 && (b & 1) == 0) {
            // 都是偶数
            return gcd(a >> 1, b >> 1) << 1;
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            // a是偶数，b是奇数
            return gcd(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            // a是奇数，b是偶数
            return gcd(a, b >> 1);
        } else {
            // 都是奇数
            int big = a > b ? a : b;
            int small = a > b ? b : a;
            return gcd(big - small, small);
        }
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int greatestCommonDivisor = getGreatestCommonDivisor(a, b);
        System.out.println(greatestCommonDivisor);
    }


}
