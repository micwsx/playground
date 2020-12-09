package com.enjoy.hackerrank;

import java.util.Scanner;

/**
 * @author Michael
 * @create 12/9/2020 2:01 PM
 */
public class Regex {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String IP = in.next();
            System.out.println(IP.matches(new MyRegex().pattern));
        }

    }

    private static class MyRegex {
        public String pattern1 = "((\\d|\\d\\d|[0-1]\\d\\d|2[0-4][0-9]|25[0-5])\\.){3}(\\d|\\d\\d|[0-1]\\d\\d|2[0-4][0-9]|25[0-5])";
        public String pattern="([0-9]|[0-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[0-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    }
}
