package com.enjoy.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Michael
 * @create 12/9/2020 11:06 AM
 * <p>
 * if a word is palindrome, then print yes, print no otherwise.
 */
public class StringReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        //reverseString(a);
        System.out.println(new StringBuilder(a).reverse().toString().equals(a)?"yes":"no");
    }

    private static void reverseString(String a) {
        char[] chars = a.toCharArray();
        char[] newChars = Arrays.copyOf(chars, chars.length);
        for (int i = 0; i < chars.length; i++) {
            chars[i] = newChars[chars.length - 1 - i];
        }
        String s = new String(chars);
        System.out.println(s.equals(a) ? "Yes" : "No");
    }
}
