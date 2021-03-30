package com.enjoy.algorithm.practice;

import javax.xml.stream.events.Characters;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author wu
 * @Date 3/28/2021 11:29 AM
 * @Version 1.0
 * 查找一个大于当前数值最近的数。
 * 1.从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界。
 * 2.让逆序区域的前一位和逆区域中大于它的最小的数字交换的位置。
 * 3.把原来的逆序区域转为顺序状态。
 */
public class NearestNumber {


    public static void main(String[] args) {

//        int value = 12435;
        int value = 1432;
//        int value = 12345;
        System.out.println(value);
        String nearestNumbers = findNearestNumbers(value);
        System.out.println(nearestNumbers);

    }

    public static String findNearestNumbers(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        int[] arrays = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int numericValue = Character.getNumericValue(chars[i]);
            arrays[i] = numericValue;
        }

        int transferPoint = findTransferPoint(arrays);
        if (transferPoint == 0) {
            // 都是顺序，54321,则没有最近的大于此数的值。
            return null;
        }

        // 把逆序区域的前一位与逆序区域中刚刚大于它数字交换位置。
        int[] numbersCopy = Arrays.copyOf(arrays, arrays.length);
        exchangeHead(numbersCopy, transferPoint);
        reverse(numbersCopy, transferPoint);
        String result = Arrays.toString(numbersCopy);
        System.out.println(result);

//        List<Integer> collect = String.valueOf(number).chars()
//                .mapToObj(a -> Character.getNumericValue(a))
//                .collect(Collectors.toList());
//

        return result;
    }


    // 最后将index后面的数字再顺序排序，使用整体数值最小。
    public static void reverse(int[] arrays, int index) {
        for (int i = index, j = arrays.length - 1; i < j; i++,j--) {
            swap(arrays, i, j);
        }
    }

    private static void exchangeHead(int[] arrays, int index) {
        // 从后往前找到比index前一个元素大的元素，并交换位置
        int transferValue = arrays[index - 1];
        for (int i = arrays.length - 1; i >= 0; i--) {
            // 从后向前找到比升序点transferValue位置大的值。
            if (arrays[i] > transferValue) {
                // 交换位置
                arrays[index - 1] = arrays[i];
                arrays[i] = transferValue;
                break;
            }
        }
    }


    // 找到逆序位置，这个位置的前一个元素比当前位置元素大
    private static int findTransferPoint(int[] arrays) {
        // 从后向前找降序位置
        for (int i = arrays.length - 1; i > 0; i--) {
            // 后面大于前面数
            if (arrays[i] > arrays[i - 1]) {
                return i;
            }
        }
        return 0;
    }


    private static void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
