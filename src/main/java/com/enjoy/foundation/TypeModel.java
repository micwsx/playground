package com.enjoy.foundation;

import java.util.List;

/**
 * @author: Michael
 * @date: 11/27/2021 3:14 PM
 *
 * 通过反映获取类中的所有变量或方法参数类型信息：类型参数、参数化类型，泛型数组
 */

public class TypeModel<T extends Object> {

    // type parameter
    private T t;

    public String name;

    // parameterized type
    private List<T> list1;

    private List<String> list2;

    private  T[] array;

    public <K extends Object> T test(T t1,K k1 ){
        return t1;
    }

    public static void main(String[] args) {

    }

}
