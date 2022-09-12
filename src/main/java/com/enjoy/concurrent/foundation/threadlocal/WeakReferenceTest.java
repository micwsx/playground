package com.enjoy.concurrent.foundation.threadlocal;

import com.enjoy.jvm.load.User;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 9/12/2022 12:18 PM
 */
public class WeakReferenceTest {

    private static User user=new User();

    public static void main(String[] args) {
        try {
            user.setName("Michael");
            WeakReference<User> weakReference=new WeakReference<>(user);// 除了弱引用外，没有其它引用执行gc会回收对象。
//            SoftReference<User> weakReference=new SoftReference<>(user);//除了软件引用外，内存不够执行gc会回收对象。
            user=null;
            System.gc();
//            TimeUnit.SECONDS.sleep(3);
            System.out.println(weakReference.get());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
