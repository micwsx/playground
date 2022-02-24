package com.enjoy.jvm.load;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @author: Michael
 * @date: 1/17/2022 9:33 PM
 * bootstrap, ext, app, customized
 */

public class TestJDKClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);//代码中设置为null

        ClassLoader classLoader1 = DESKeyFactory.class.getClassLoader();
        System.out.println(classLoader1);

        ClassLoader classLoader2 = TestJDKClassLoader.class.getClassLoader();
        System.out.println(classLoader2);

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapClassLoader= extClassLoader.getParent();
        System.out.println("appClassLoader: "+appClassLoader);
        System.out.println("extClassLoader: "+extClassLoader);
        System.out.println("bootstrapClassLoader: "+bootstrapClassLoader);

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("bootstrap class path: ");
        for (int i = 0; i < urLs.length; i++) {
            System.out.println(urLs[i]);
        }
        System.out.println("ext class path: ");
        String extClassPath = System.getProperty("java.ext.dirs");
        System.out.println("extClassPath: "+extClassPath);

        System.out.println("app class path: ");
        String classPath = System.getProperty("java.class.path");
        System.out.println("classPath:"+ classPath);
    }
}
