package com.enjoy.hackerrank.string;

/**
 * @author Michael
 * @create 12/21/2020 2:06 PM
 */
public class AlternatingCharacters {

    public static void main(String[] args) {

//        String s = "AAAA";
//        String s="BBBBB";
        String s="ABCBABAB";
//        String s="BABABA";
//        String s="AAABBB";
        int i = alternatingCharacters(s);
        System.out.println(i);


    }

    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
                System.out.println("remove " + chars[i]);
            }
        }
        return count;
    }


}
