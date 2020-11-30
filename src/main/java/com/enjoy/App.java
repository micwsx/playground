package com.enjoy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String a=new String("abc");//堆上创建对象，引用了字面量
        String b=new String("abc");//堆上创建对象，引用同一个字面量
        System.out.println(a==b);//不相等
        System.out.println(a.equals(b));//相等
        // intern拿到字面量的值
        System.out.println(a.intern()==b.intern());//相等

        String a_s="abc";//引用的是字面量
        String b_s="abc";//引用同一个字面量
        System.out.println(a_s==b_s);//相等
        System.out.println(a_s.equals(b_s));//相等

        System.out.println(a==a_s);//不相等
        System.out.println(a.intern()==a_s);//相等

    }
}
