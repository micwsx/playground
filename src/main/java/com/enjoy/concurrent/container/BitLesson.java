package com.enjoy.concurrent.container;

import java.security.AllPermission;

/**
 * 位运算
 */
public class BitLesson {

   private static final int ALLOW_SELECT = 1 << 0;
    private static final int ALLOW_INSERT = 1 << 1;
    private static final int ALLOW_UPDATE = 1 << 2;
    private static final int ALLOW_DELETE = 1 << 3;
    private static int privilige = 0;

    public static void main(String[] args) {

        // basic();

        permission();
    }

    // 添加权限
    public static void add(int permissions) {
        privilige=privilige|permissions;
    }

    // 移除权限
    public static void remove(int permission) {
        privilige=privilige^permission;
    }

    // 检查权限
    public static boolean hasPriviliges(int permissions) {
        return (privilige & permissions) == permissions;
    }

    //
    public static boolean hasNoPriviliges(int permissions) {
        return (privilige & permissions) == 0;
    }



    private static void permission() {
        add(ALLOW_SELECT|ALLOW_INSERT | ALLOW_UPDATE | ALLOW_DELETE);
        //System.out.println(privilige);
        // 移除权限ALLOW_SELECT|ALLOW_UPDATE
        remove(ALLOW_SELECT|ALLOW_UPDATE);
        // 检查权限ALLOW_SELECT
        System.out.println(hasPriviliges(ALLOW_SELECT)? "拥有ALLOW_SELECT" : "没有ALLOW_SELECT");
        // 检查权限ALLOW_UPDATE
        System.out.println(hasPriviliges(ALLOW_UPDATE)? "拥有ALLOW_UPDATE" : "没有ALLOW_UPDATE");
        // 检查是否没有ALLOW_SELECT
        System.out.println(hasNoPriviliges(ALLOW_SELECT)? "没有ALLOW_SELECT" : "拥有ALLOW_SELECT");



    }

    private static void basic() {
        int octalVal = 020;//八进制
        int hexVal = 0X10;//十六进制
        System.out.println(octalVal + "-" + hexVal);

        // 有符号整数，高位补符号位
        int value = -16;
        System.out.println(value + " 二进制数：" + Integer.toBinaryString(value) + " 长度：" + Integer.toBinaryString(value).length());
        System.out.println(value + " 八进制数：" + Integer.toOctalString(value));
        System.out.println(value + " 十六进制数：" + Integer.toHexString(value));
        // 左移
        System.out.println(value >> 3);
        // 右移
        System.out.println(value << 3);

        // &(与)，或(|),非(~),^(异或)
        System.out.println("4 二进制数：" + Integer.toBinaryString(4));
        System.out.println("6 二进制数：" + Integer.toBinaryString(6));
        System.out.println("4&6 :" + Integer.toBinaryString((4 & 6)));
        System.out.println("4|6 :" + Integer.toBinaryString((4 | 6)));
        System.out.println("4^6 :" + Integer.toBinaryString((4 ^ 6)));
        System.out.println("~4 :" + Integer.toBinaryString(~4));

        System.out.println("4^4 :" + Integer.toBinaryString((4 ^ 4)));
        // 2次异常等于自己
        System.out.println("4^4^4 :" + Integer.toBinaryString((4 ^ 4 ^ 4)));


        // 求余操作如果这个数是2的指数，则a % (2^n) ==== a & (2^n - 1)； 例如：10%4 = 10&(2^2-1)
        System.out.println("10%4: " + (10 % 4) + "=" + (10 & 3));
        // 判断一个数是不是偶数
        int even = 10 & (int) (Math.pow(2, 1) - 1);
        System.out.println("10是偶数吗(10 &（2^1-1）)==0?" + (even == 0));
    }


}
