package com.enjoy.hackerrank.arrays;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * @author Michael
 * @create 12/10/2020 10:38 AM
 */
public class LeftRotation {

    public static void main(String[] args) {
        int[] a = {33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60, 87, 97};
        int d = 13;
        //87 97 33 47 70 37 8 53 13 93 71 72 51 100 60

        arrayLeftRotation(a, a.length, d);

//        int[] result = rotLeft(a, d);
//        System.out.println(Arrays.toString(result));


    }

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        // Rotate in-place
        int[] temp = new int[k];
        System.arraycopy(a, 0, temp, 0, k);
        System.out.println(Arrays.toString(temp));
        System.arraycopy(a, k, a, 0, n - k);
        System.arraycopy(temp, 0, a, n - k, k);
        return a;
    }

    static int[] rotLeft3(int[] a, int d) {
        int[] temp = new int[d];
        System.arraycopy(a, 0, temp, 0, d);
        System.arraycopy(a, d, a, 0, a.length - d);
        System.arraycopy(temp, 0, a, a.length - d, d);
        return a;
    }


    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        int[] result = new int[a.length];
        int hit_index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == d)
                hit_index = i;
        }
        int new_hit = a.length - hit_index - 1;
        result[new_hit] = d;
        int up = 0;
        for (int i = 0; i < a.length; i++) {
            if (i <= hit_index) {
                //left
                result[new_hit + i] = a[i];
            } else if (i > hit_index) {
                //right
                result[up++] = a[i];
            }
        }
        return result;
    }


}
