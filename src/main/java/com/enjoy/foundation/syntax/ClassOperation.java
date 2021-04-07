package com.enjoy.foundation.syntax;

/**
 * @Author wu
 * @Date 4/2/2021 11:56 AM
 * @Version 1.0
 * 不添加任何修饰，同一个包下可以访问此类，不同包不能访问些类。
 */
public class ClassOperation {

    // 仅对该类内部可见
    private String name;

    // 同一个包和子类可见
    protected String age;

    // 任何地方均可见
    public String grade;

    // 同一个包可见
    String height;

    // 同一个包可见
    String getHeight() {
        return height;
    }

    // 仅对该类内部可见
    private String getName() {
        return name;
    }

    // 同一个包和子类可见
    protected final String getAge() {
        return age;
    }

    // 任何地方可见
    public String getGrade() {
        return grade;
    }

    // 不添加任何修饰符：包内可见，包外不可见
    class InnerClass {

        private void innerPrivateMethod() {
            String name = getName();

        }

        protected void innerProtectedMethod() {
            String name = getName();
        }
    }

    // 不添加任何修饰符：包内可见，包外不可见
    static class InnerStaticClass {
        // 这个方法虽然可以定义成功，但不能访问，静态类中的成员都要是静态方法。
        public void innerMethod() {
            innerStaticMethod();
        }
        //  ClassOperation.InnerStaticClass.innerStaticMethod();
        // 若内部声名静态方法, InnerClass类必须是静态
        public static void innerStaticMethod() {
        }
    }
}



