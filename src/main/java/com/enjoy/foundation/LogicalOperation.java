package com.enjoy.foundation;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LogicalOperation {


    private String name;

    public String getName() {

        return name;
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        // false && true = false
        if (a > 5 || b++ > 9) {
            System.out.println(a + "--" + b);
        } else {
            // 5--11
            System.out.println(a + "--" + b);
        }
    }
}
