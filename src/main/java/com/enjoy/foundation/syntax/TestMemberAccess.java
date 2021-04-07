package com.enjoy.foundation.syntax;

/**
 * @Author wu
 * @Date 4/2/2021 12:16 PM
 * @Version 1.0
 */
public class TestMemberAccess {
    public static void main(String[] args) {
        ClassOperation classOperation = new ClassOperation();
        String height = classOperation.height;
        String height1 = classOperation.getHeight();
        String age = classOperation.age;
        String grade = classOperation.grade;
        String age1 = classOperation.getAge();
        String grade1 = classOperation.getGrade();

        ClassOperation.InnerClass innerClass = classOperation.new InnerClass();




        // 直接访问内部静态类中的静态方法
        ClassOperation.InnerStaticClass.innerStaticMethod();


    }
}
