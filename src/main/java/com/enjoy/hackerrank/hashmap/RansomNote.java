package com.enjoy.hackerrank.hashmap;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author Michael
 * @create 12/24/2020 1:03 PM
 */
public class RansomNote {

    public static void main(String[] args) {


//        String s1 = "ive got a lovely brunch of coconuts";
//        String s2 = "ive got some coconuts";

        String s1 = "avtq ekpvq z rdvzf m zu bof pfkzl ekpvq pfkzl bof zu ekpvq ekpvq ekpvq ekpvq z";
        String s2 = "m z z avtq zu bof pfkzl pfkzl pfkzl rdvzf rdvzf avtq ekpvq rdvzf avtq";

        String[] magazine = s1.split(" ");
        String[] note = s2.split(" ");
        checkMagazine(magazine, note);

    }

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {

        System.out.println(Arrays.toString(magazine));
        System.out.println(Arrays.toString(note));

        HashMap<String, Integer> freq = new HashMap<>();

        for (int i = 0; i < note.length; i++) {
            if (freq.containsKey(note[i])) {
                Integer times = freq.get(note[i]);
                freq.put(note[i], ++times);
            } else {
                freq.put(note[i], 1);
            }
        }

        for (int j = 0; j < magazine.length; j++) {
            if (freq.containsKey(magazine[j])) {
                Integer times = freq.get(magazine[j]);
                freq.put(magazine[j], --times);
            }
        }

        boolean exist = freq.values().stream().anyMatch(i -> i > 0);
        if (exist) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

}
