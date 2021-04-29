package com.enjoy.jvm.load;

/**
 * @Author wu
 * @Date 4/10/2021 10:07 AM
 * @Version 1.0
 */
public class Animal {
    public static String name = "Animal";
    public static final float weight = 100.0f;

    static {
        System.out.println("static Animal");
    }

    public Animal() {
        System.out.println("Animal constructor");
    }

    public static void main(String[] args) {

        try {
            // 会触发Animal静态代码块方法
            Class<?> aClass = Class.forName("com.enjoy.jvm.load.Animal");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // 只执行父类静态代码会执行
//        System.out.println(Gorilla.name);
        // 只执行父类静态代码块
//        System.out.println(Gorilla.weight);
        // 只执行父类和子类静态代码块
        System.out.println(Gorilla.height);
    }
}

class Gorilla extends Animal {

    public static double height = weight * 0.1;
    public double length = weight * 1;

    static {
        System.out.println("static Gorilla");
    }

    public Gorilla() {
        System.out.println("Gorilla constructor");
    }
}
