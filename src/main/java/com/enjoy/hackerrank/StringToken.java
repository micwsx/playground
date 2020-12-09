package com.enjoy.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michael
 * @create 12/9/2020 1:21 PM
 */
public class StringToken {

    public static void main(String[] args) {
        String ssss="He is a very very good boy, isn't he?";
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String[] array = s.split("[^A-Za-z]+");
        Predicate<String> predicate=String::isEmpty;
        List<String> collect = Arrays.stream(array).filter(predicate.negate()).collect(Collectors.toList());
        System.out.println(collect.size());
        collect.forEach(System.out::println);
        scan.close();
    }
}
