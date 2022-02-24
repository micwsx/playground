package com.enjoy.foundation;

/**
 * A && B: A如果false,B则不被执行
 * A || B:　A如果true,B则不被执行
 *
 * A & B: 不管A是否ture/false,B都会被执行
 * A | B: 不管A是否ture/false,B都会被执行
 *
 *
 */
public class LogicalOperation {


    private String name;

    public String getName() {

        return name;
    }

    public static void main(String[] args) {
        bitOperation();
//        singleSymbol();// &量b值的变化，加1
//        doubleSymbol();//&&变量b值没有加1

    }

    public static void singleSymbol(){
        int a = 5;
        int b = 10;

        // false & true = false
        if (a > 5 & b++ > 9) {
            System.out.println("true: "+a + "--" + b);
        } else {
            // 5--11
            System.out.println("false: "+a + "--" + b);
        }
    }

    public static void doubleSymbol(){
        int a = 5;
        int b = 10;

        // false && true = false
        if (a > 5 && b++ > 9) {
            System.out.println("true: "+a + "--" + b);
        } else {
            // 5--11
            System.out.println("false: "+a + "--" + b);
        }
    }


    public static void bitOperation(){
        int i=2;//  0000 0010
        rightMoveOneBit(i);
        rightMoveOneBitNoSymbol(i);
        i=-2;// 原码的反码+1： 1111 1111 1111 1110
        rightMoveOneBit(i);// 最高位补1
        rightMoveOneBitNoSymbol(-10);


    }

    /**
     * >>有符号位移：最高位补符号位。正数位移还是正数，负数还是负数
     * @param i
     */
    private static void rightMoveOneBit(int i) {
        String s = Integer.toBinaryString(i);
        System.out.println(i +" binary value: "+s);
        i = i >>1;
        System.out.println("(>>1) value: "+ i);
    }

    /**
     * >>>无符号位移:最高位补0
     * @param i
     */
    private static void rightMoveOneBitNoSymbol(int i) {
        String s = Integer.toBinaryString(i);
        System.out.println(i +" binary value: "+s);
        i = i >>>1;
        System.out.println("(>>>1) value: "+ i);
    }

}
