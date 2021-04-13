package com.enjoy.algorithm.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author wu
 * @Date 4/13/2021 2:02 PM
 * @Version 1.0
 */
public class DivideRedPacket {


    /**
     * 分红包
     *
     * @param money  总金额
     * @param number 数量
     * @return
     */
    private static List<Double> divideRedPacket(double money, int number) {
        List<Double> list = new ArrayList<>();

        double balance = money;
        Random random = new Random();
        for (int i = 0; i < number - 1; i++) {
            double cost = random.nextDouble() * balance / (number - i);
            list.add(cost);
            balance -= cost;
        }
        list.add(balance);
        return list;
    }

    /**
     * 二倍法
     *
     * @param money
     * @param number
     * @return
     */
    private static List<Integer> divideRedPacket(int money, int number) {
        List<Integer> list = new ArrayList<>();
        int restAmount = money;
        int restPeopleNum = number;
        Random random = new Random();
        for (int i = 0; i < number - 1; i++) {
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            list.add(amount);
        }
        list.add(restAmount);
        return list;
    }

    public static void main(String[] args) {
//        List<Double> list = divideRedPacket(100D, 5);
//        System.out.println(list.toString());
        List<Integer> list = divideRedPacket(100, 5);
        System.out.printf(list.toString());

    }
}
