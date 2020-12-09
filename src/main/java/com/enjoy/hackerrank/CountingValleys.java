package com.enjoy.hackerrank;

/**
 * @author Michael
 * @create 12/9/2020 3:33 PM
 */
public class CountingValleys {
    public static void main(String[] args) {
        String array="DDUUDD";
        char[] chars = array.toCharArray();
        int level=0;
        int valley=0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]=='U'){
                if (++level==0){
                    valley++;
                }
            }else{
                level--;
            }
        }
        System.out.println(valley);

    }
}
