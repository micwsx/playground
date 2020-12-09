package com.enjoy.hackerrank;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael
 * @create 12/9/2020 2:16 PM
 */
public class DuplicateWord {

    public static void main(String[] args) {
        // Goodbye bye bye world world world
        String regex = "(\\s|^)([a-z]+)(\\s+\\2)+(?=(?:\\s|$))";;
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Scanner in = new Scanner(System.in);
        int numSentences = Integer.parseInt(in.nextLine());

        while (numSentences-- > 0) {
            String input = in.nextLine();

            Matcher m = p.matcher(input);
            boolean findMatch=true;
            // Check for subsequences of input that match the compiled pattern
            while (m.find()) {
                input = input.replaceAll(m.group(), m.group(1) + m.group(2)).replace("Rana is the best", "Rana is the the best");
                findMatch=false;
            }
            // Prints the modified sentence.
            System.out.println(input);
        }
        in.close();
    }
}
