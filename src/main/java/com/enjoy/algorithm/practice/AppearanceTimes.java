package com.enjoy.algorithm.practice;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author wu
 * @Date 3/28/2021 8:49 PM
 * @Version 1.0
 * 1.快速找到不连续的值一个数组1-10中遗漏的值。
 * 2.快速找到出现奇数次数值
 * 3.快速找到出现2个奇数次数值.98个数出现了偶数次，2个出现奇数次。
 */
public class AppearanceTimes {

    public static void main(String[] args) {

//        int[] arrays = {1, 2, 3, 4, 6, 7, 8, 9, 10};
//        int missingNumber = question1(arrays);
//        System.out.println(missingNumber);

//        int[] oddArrays = {1, 1, 2, 2, 3, 3, 4};
//        int oddTimeNumber = question2(oddArrays);
//        System.out.println(oddTimeNumber);

        int[] arrays = {1, 1, 2, 2, 3, 3, 4, 5};
        int[] result = question3(arrays);
        System.out.println(result[0] + "," + result[1]);
    }

    //3.快速找到出现2个奇数次数值
    public static int[] question3(int[] numbers) {
        int[] result = new int[2];
        // 找到两个奇数异或结果
        int xorResult = question2(numbers);
        String binaryResult = Integer.toBinaryString(xorResult);//结果肯定不全都为0，相同数值异常等于0
//        System.out.println(binaryResult);
        // 获取其中任何位为1，知道这2个数哪位是不相同的，
        int separator = 1;
        while (0 == (xorResult & separator))
            separator <<= 1;// 左移一位，循环再次计算

        for (int i = 0; i < numbers.length; i++) {
            if (0 == (numbers[i] & separator)) {
                // 这一位是0
                result[0] ^= numbers[i];
            } else {
                // 这一位是1
                result[1] ^= numbers[i];
            }
        }


        return result;
    }

    // 2.快速找到数组中出现奇数次数值
    public static int question2(int[] numbers) {
        int reduce = Arrays.stream(numbers).reduce(0, (a, b) -> a ^= b);
        return reduce;
    }

    //    1.快速找到不连续的值一个数组1-10中遗漏的值。
    public static int question1(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();
        int total = IntStream.rangeClosed(1, 10).reduce(0, Integer::sum);
        return total - sum;
    }

}
