package com.enjoy.foundation;


import com.enjoy.foundation.syntax.ClassOperation;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class BootstrapSample {
    public static void main(String[] args) {

        ClassOperation classOperation=new ClassOperation();



        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            System.out.println(classLoader);
            Enumeration<URL> enumeration = classLoader.getResources("");
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                System.out.println(url);
            }
            Class<?> clazz = classLoader.loadClass("com.enjoy.foundation.LogicalOperation");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassLoader parentClassLoader= classLoader.getParent();
        System.out.println("扩展类加载器: "+parentClassLoader);
        System.out.println("扩展路径属性："+System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器parent: "+parentClassLoader.getParent());


        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }


    }
}
