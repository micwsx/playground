package com.enjoy.hackerrank;

/**
 * @author Michael
 * @create 12/9/2020 5:04 PM
 */
public class RepeatString {
    public static void main(String[] args) {
        String s = "aba";
        long n = 10;
        long repeated = n / s.length();
        long remain = n % s.length();

        int count = 0;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                count++;
            }
        }

        int extra = 0;
        for (int i = 0; i < remain; i++) {
            if (s.charAt(i) == 'a') {
                extra++;
            }
        }
        long res = extra + count * repeated;
        System.out.println(res);

    }
}
