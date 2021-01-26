package com.enjoy.hackerrank.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Michael
 * @create 12/28/2020 10:42 AM
 */
public class IceCreamParlor {

    public static void main(String[] args) {

        int[] cost = new int[]{1, 4,  5, 3, 2};
//        int[] cost = new int[]{2, 2, 4, 3};
        int money = 4;

        whatFlavors(cost, money);
    }

    static void whatFlavors(int[] cost, int money) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : cost) {
            Integer fre = map.putIfAbsent(i, 1);
            if (fre != null) {
                map.put(i, fre + 1);
            }
        }


        int ans1 = -1, ans2 = -1;
        for (int i = 0; i < cost.length; i++) {
            int item1 = cost[i], item2 = money - item1;
            if (item1 != item2) {
                // 判断map是否都出现这两个价格数值，有则直接获取这2个价格结束。
                if (map.containsKey(item1) && map.containsKey(item2)) {
                    ans1 = item1;
                    ans2 = item2;
                    break;
                }
            } else {
                // 出现了多次,满足条件则直接结束。
                if (map.get(item1) > 1) {
                    ans1 = ans2 = item1;
                    break;
                }
            }
        }

        // 根据找到的数值，获取索引值
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < cost.length; i++) {
            if (cost[i] == ans1 || cost[i] == ans2) {
                set.add(i + 1);
            }
        }
        System.out.println(set.pollFirst() + " " + set.pollFirst());
    }


    // Complete the whatFlavors function below.
    static void whatFlavors2(int[] cost, int money) {
        for (int i = 1; i <= cost.length; i++) {
            int item1 = cost[i - 1];
            for (int j = i; j < cost.length; j++) {
                int item2 = cost[j];
                if (item1 + item2 == money) {
                    String msg = i > (j + 1) ? (j + 1) + " " + i : i + " " + (j + 1);
                    System.out.println(msg);
                    return;
                }
            }
        }
    }


}


