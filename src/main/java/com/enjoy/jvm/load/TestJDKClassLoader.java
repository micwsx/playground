package com.enjoy.jvm.load;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;

/**
 * @author: Michael
 * @date: 1/17/2022 9:33 PM
 * bootstrap, ext, app, customized
 */

public class TestJDKClassLoader {

    public static void main(String[] args) {
        // Bootstrap class loader
        ClassLoader classLoader = String.class.getClassLoader();
        System.out.println(classLoader);//代码中设置为null
        // extend class loader
        ClassLoader classLoader1 = DESKeyFactory.class.getClassLoader();
        System.out.println(classLoader1);
        // app class loader
        ClassLoader classLoader2 = TestJDKClassLoader.class.getClassLoader();
        System.out.println(classLoader2);

//        if (classLoader1 instanceof URLClassLoader){
//            for (URL url : ((URLClassLoader) classLoader1).getURLs()) {
//                System.out.println(url);
//            }
//        }

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("appClassLoader: "+appClassLoader);

        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println("extClassLoader: "+extClassLoader);
        URL courseURL = extClassLoader.getResource("course.txt");
        System.out.println(courseURL);


        ClassLoader bootstrapClassLoader= extClassLoader.getParent();
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

        File[] extDirs = getExtDirs();

    }

    private static File[] getExtDirs(){
        String var0 = System.getProperty("java.ext.dirs");
        File[] var1;
        if (var0 != null) {
            StringTokenizer var2 = new StringTokenizer(var0, File.pathSeparator);
            int var3 = var2.countTokens();
            var1 = new File[var3];

            for(int var4 = 0; var4 < var3; ++var4) {
                var1[var4] = new File(var2.nextToken());
            }
        } else {
            var1 = new File[0];
        }

        return var1;
    }
}
