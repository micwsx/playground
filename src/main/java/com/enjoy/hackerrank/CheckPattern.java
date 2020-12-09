package com.enjoy.hackerrank;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Michael
 * @create 12/9/2020 1:53 PM
 */
public class CheckPattern {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String pattern = in.nextLine();
            try {
                Pattern compile = Pattern.compile(pattern);
                System.out.println("valid");
            } catch (Exception e) {
                System.out.println("invalid");
            }
            testCases--;
        }
    }
}
