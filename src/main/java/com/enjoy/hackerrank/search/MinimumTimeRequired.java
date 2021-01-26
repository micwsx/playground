package com.enjoy.hackerrank.search;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael
 * @create 12/29/2020 7:17 PM
 * Binary Search
 */
public class MinimumTimeRequired {

    public static void main(String[] args) {


        long[] machines = new long[]{63, 2, 26, 59, 16, 55, 99, 21, 98, 65};
//        long[] machines = new long[]{4, 5, 6};

//       long[] machines = new long[]{2, 3, 2};
//        long[] machines = new long[]{2, 3};
        long goal = 56;
        long time = minTime(machines, goal);
        System.out.println(time);

    }

    /**
     * 使用二分查找法O(log(n))时间复杂度快。
     *
     * @param machines
     * @param goal
     * @return
     */
    static long minTime(long[] machines, long goal) {
        long low = 1;
        long high = (long) 1E18;
        long ans = 0L;
        while (low <= high) {
            long mid = (low + high) / 2;
            long result = 0L;
            for (long days : machines) {
                result += mid / days;
                if (result >= goal)
                    break;
            }
            if (result >= goal) {
                // 从左边找
                high = mid - 1;
                ans = (long) mid;
            } else {
                // 从右边找
                low = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 结果不对而且线性时间复杂度O(n)太慢
     *
     * @param machines
     * @param goal
     * @return
     */
    static long minTime2(long[] machines, long goal) {

        // 保存天数出现次数
        HashMap<Long, Long> fre = new HashMap<>();
        for (long requiredDays : machines) {
            Long count = fre.putIfAbsent(requiredDays, 1L);
            if (count != null) {
                fre.put(requiredDays, ++count);
            }
        }
        double cacl = 0.0;
        for (Map.Entry<Long, Long> entry : fre.entrySet()) {
            cacl += entry.getValue() * 1.0 / entry.getKey();
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String value = decimalFormat.format(goal / cacl);
        long result = (long) Math.ceil(Double.parseDouble(value));
        return result;
    }
}
