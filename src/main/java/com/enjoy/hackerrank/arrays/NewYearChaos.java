package com.enjoy.hackerrank.arrays;

import java.util.ArrayList;

/**
 * @author Michael
 * @create 12/10/2020 1:05 PM
 */
public class NewYearChaos {
    public static void main(String[] args) {
        int[] q = new int[]{2, 1, 5, 3, 4};
//        int[] q = new int[]{2, 5, 1, 3, 4};
        minimumBribes2(q);
    }

    static void minimumBribes2(int[] q) {
        int n = q.length;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = n; i >= 1; i--) {
            list.add(i);
        }
        int[] want = new int[n];
        for (int i = 0; i < q.length; i++) {
            want[i] = q[i];
        }
        int ret = 0;
        for (int curr : want) {
            if (list.get(list.size() - 1) == curr) {
                list.remove(list.size() - 1);
            } else if (list.get(list.size() - 2) == curr) {
                ret++;
                list.remove(list.size() - 2);
            } else if (list.get(list.size() - 3) == curr) {
                ret += 2;
                list.remove(list.size() - 3);
            } else {
                ret = -1000000000;
            }
        }
        if (ret < 0) {
            System.out.println("Too chaotic");
            return;
        }
        System.out.println(ret);

    }


    static void minimumBribes(int[] q) {
        int minBribes = 0;
        boolean chaotic = false;
        for (int i = 0; i < q.length; i++) {
            int countBribe = 0;
            for (int j = i + 1; j < q.length; j++) {
                if (q[i] > q[j]) {
                    countBribe++;
                    if (countBribe > 2) {
                        chaotic = true;
                        System.out.println("Too chaotic");
                        return;
                    } else {
                        minBribes++;
                    }
                } else {
                    continue;
                }
            }
        }
        System.out.println(minBribes);
    }

}
