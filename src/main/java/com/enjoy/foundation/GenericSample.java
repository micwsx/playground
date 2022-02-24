package com.enjoy.foundation;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射获取泛型类信息
 * <？ extends Fruit> 上界通配符（Upper Bounds Wildcards)
 * <？ super Fruit> 下界通配符（Lower Bounds Wildcards)
 */
public class GenericSample {

    public static <T> void test(T t){
        T[] array=(T[])Array.newInstance(t.getClass().getComponentType(),1);
        System.out.println(array);
    }

    public static void main(String[] args) {

        System.out.println(Serializable.class.isAssignableFrom(Class.class));

//        Class<?> clazz = GenricSample3.class;
//        ClassUtil.println(clazz);
//        ClassUtil.printlnFields(clazz);

//        System.out.println(clazz.getMethods().length);
//        for (Method method : clazz.getMethods()) {
//            ClassUtil.printMethodInfo(method);
//        }

       // System.out.println(clazz.getDeclaredMethods().length);
    }


    private static abstract class Base implements Serializable {

        public Base() {
        }

        public String test;

        private String genricName;

        public String getGenricName() {

            return genricName;
        }

        public class B {
        }
    }

    public static class GenricSample3<K extends Object, V extends Object> extends Base implements Cloneable  {

        public GenricSample3() {
        }

        public GenricSample3(Map<K, V> map) {
            this.map = map;
        }

        private Map<K, V> map = new HashMap<>();

        public K[] array=(K[])new Object[10];


        public Map<K, V> getMap() {
            return map;
        }


        public void put(K k, V v) {
            map.put(k, v);
        }

        public <T> T getInstance(Class<T> clazz) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        public int getint( String a){return 1;}
        public <T extends General & Behavior> void run(T t) {
            t.run();
        }

        private class A {
        }

    }


    private interface Behavior {
        void info();

        void run();
    }

    public class Dog implements Behavior {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Dog() {
        }

        public Dog(String name) {
            this.name = name;
        }

        @Override
        public void info() {
            System.out.println("Dog's name is " + this.name);
        }

        @Override
        public void run() {
            System.out.println(this.name + " is running!");
        }
    }

    public class General extends Dog {

        public General(String name, int age) {
            super(name);
            this.age = age;
        }

        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public void run() {
            super.run();
        }
    }



}
