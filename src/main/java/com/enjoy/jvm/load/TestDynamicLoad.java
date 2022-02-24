package com.enjoy.jvm.load;

/**
 * @author: Michael
 * @date: 1/17/2022 9:25 PM
 *  类加载过程：加载->验证->准备->解析->使用->卸载
 */
public class TestDynamicLoad {
    static {
        System.out.println("*********Load TestDynamic*******");
    }

    public static void main(String[] args) {

        new A();
        B b=null;// B静态代码并不会执行

    }

    private static class  A{
        static {
            System.out.println("*********Load A*******");
        }

        public A() {
            System.out.println("*********A Constructor*******");
        }
    }

    private static class  B{
        static {
            System.out.println("*********Load B*******");
        }
        public B() {
            System.out.println("*********B Constructor*******");
        }
    }

}
