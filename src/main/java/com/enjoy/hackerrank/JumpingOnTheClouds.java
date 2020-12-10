package com.enjoy.hackerrank;

/**
 * @author Michael
 * @create 12/9/2020 3:38 PM
 */
public class JumpingOnTheClouds {
    public static void main(String[] args) {
        int[] array = new int[]{0, 0, 1, 0, 0, 1, 0};//7
        int jump_step = 0;
        int step=0;
        for (int i = 0; i < array.length; i++) {
            System.out.print(i+"->");
            if ((i < array.length - 2) && array[i + 2] == 0) {
                System.out.print("在第" + i + "开始起跳->");
                i++;
                jump_step++;
            }
            ++step;
        }
        System.out.println("总跳数:"+jump_step);
        System.out.println(--step);
    }
}
