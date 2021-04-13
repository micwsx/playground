package com.enjoy.algorithm.question;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Author wu
 * @Date 4/13/2021 9:53 AM
 * @Version 1.0
 * 移除N位使数值最小
 * 思路：从左至右比较数值，直到某数大于右边的数。
 */
public class SmallestNumberAfterRemoveNTimes {

    /**
     * 移除n次使数值最小
     *
     * @param number 数值
     * @param n      移除次数
     */
    private static void removeKDigits(int number, int n) {
//        List<Integer> collect = String.valueOf(number).chars()
//                .mapToObj(a -> Character.getNumericValue(a))
//                .collect(Collectors.toList());

//        int[] flatNumber = String.valueOf(number).chars().toArray();

        String num = String.valueOf(number);
        for (int m = 0; m < n; m++) {
            boolean hasCut = false;
            for (int i = 0; i < num.toCharArray().length - 1; i++) {
                int j = i + 1;
                char[] chars = num.toCharArray();
                if (chars[i] > chars[j]) {
                    // 左边数值大于右边
                    // 直接移除此值
                    // 0位置开始(截取包括)，i位置结束(截取不包括)，
                    num = num.substring(0, i) + num.substring(i + 1);
                    System.out.println("after removing " + chars[i] + "-" + num);
                    hasCut = true;
                    break;
                } else {
                    // 左边数值小于等于右边,继续查找
                    continue;
                }
            }
            // 如果没有移除，则移除最后一个元素
            if (!hasCut) {
                num = num.substring(0, num.length() - 1);
            }
            // 清除最左边的0
            num = removeZero(num);
        }
    }

    private static String removeZero(String number) {
        char[] chars = number.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                return number.substring(i);
            }
        }
        return number;
    }

    public static void main(String[] args) {
        int number = 401270936;
        System.out.println(number);
        removeKDigits(number, 3);
    }
}
