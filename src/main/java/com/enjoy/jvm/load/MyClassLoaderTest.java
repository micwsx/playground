package com.enjoy.jvm.load;

import sun.misc.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 1/17/2022 10:13 PM
 */

public class MyClassLoaderTest {

    static class MyClassLoader extends ClassLoader {

        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();

                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    // 打破双亲委派（除了com.enjoy外包的类）由boostrap,ext,app父类加载外，其它都自己加载
                    if (!name.startsWith("com.enjoy")){
                        c=getParent().loadClass(name);
                    }else{
                        c = findClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String path = name.replace('.', '/').concat(".class");
            try {
                try (FileInputStream fileInputStream = new FileInputStream(classPath + "/" + path)) {
                    int len = fileInputStream.available();
                    byte[] data = new byte[len];
                    fileInputStream.read(data);
                    return defineClass(name,data,0,data.length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
//        loadCustomizedString();
        MyClassLoader myClassLoader=new MyClassLoader("C:/test");
        try {
            Class<?> clazz = myClassLoader.loadClass("com.enjoy.jvm.load.User");
            Object o = clazz.newInstance();
            Method method = clazz.getMethod("say");
            method.invoke(o,null);
            System.out.println(clazz.getClassLoader().getClass().getName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java基本类String,Object都不会允许除bootstrap外其它类加载器加载。
     * Exception in thread "main" java.lang.SecurityException: Prohibited package name: java.lang
     * 	at java.lang.ClassLoader.preDefineClass(ClassLoader.java:655)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:754)
     * 	at java.lang.ClassLoader.defineClass(ClassLoader.java:635)
     * 	at com.test.MyClassLoaderTest$MyClassLoader.findClass(MyClassLoaderTest.java:56)
     * 	at com.test.MyClassLoaderTest$MyClassLoader.loadClass(MyClassLoaderTest.java:34)
     * 	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
     * 	at com.test.MyClassLoaderTest.main(MyClassLoaderTest.java:68)
     */
    private static void loadCustomizedString() {
        MyClassLoader myClassLoader=new MyClassLoader("C:/test");
        try {
            Class<?> clazz = myClassLoader.loadClass("java.lang.String");
            Object o = clazz.newInstance();
            Method method = clazz.getMethod("sout");
            method.invoke(o,null);
            System.out.println(clazz.getClassLoader().getClass().getName());
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}
