package com.enjoy.concurrent.foundation;

import java.util.concurrent.TimeUnit;

/**
 * java内存JMM模型解析可见性问题，JMM线程之通讯共享模型。
 * JVM storeLoad/...
 * jvm volatile 修饰变量底层解释成lock前缀指令
 *
 * volatile
 *
 * 单例 又重校验为什么加上volatile.
 *
 * 分配空间
 * 初始化
 * 内存地址
 *
 * if(instance==null){
 * }
 * return instance; // 地址分配后但还没能初始化，此时没有volatile会出现问题。
 *
 *
 * 可见性:当前线程修改变量，其它线程能看到修改变化。
 * 	volatile,内存屏障，synchronized,lock,final
 * 有序性：程序执行顺序按代码先后顺序执行，不会被编译器优化重排序。
 * 	volatile,内存屏障，synchronized,lock
 * 原子性（个人感觉是同步性）：一个或多个操作，要么全部执行，要么都不执行。
 * 	cas,lock,synchronized
 *
 * Lock指令
 * 1 . 确保后续指令执行的原子性。 在Pentium及之前的处理器中， 带有lock前缀的指令在执 行期间会锁住总线， 使得其它处理器暂时无法通过总线访问内存， 很显然， 这个开销很 大。
 * 在新的处理器中， I ntel使用缓存锁定来保证指令执行的原子性， 缓存锁定将大大降低 l ock前缀指令的执行开销。
 * 2. LOCK前缀指令具有类似于内存屏障的功能， 禁止该指令与前面和后面的读写指令重排 序。
 * 3. LOCK前缀指令会等待它之前所有的指令完成、 并且所有缓冲的写操作写回内存(也就是 将store buffer中的内容写入内存)之后才开始执行， 并且根据缓存一致性协议，
 * 刷新 store buffer的操作会导致其他cache中的副本失效。
 *
 * 添加下面的jvm参数查看之前可见性Demo的汇编指令，能看到volatitle修饰的变量lock addl 指令
 * 	‐XX:+UnlockDiagnosticVMOptions ‐XX:+PrintAssembly ‐Xcomp
 */
public class VolatileDemo {

    private boolean flag=true;
    private int count=0;

    public void setFlag(){
        flag=false;
        System.out.println(Thread.currentThread().getName()+" changed the flag."+flag);
    }

    public void start(){
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        while (flag){
            // 1. flag,count没有加任何修饰，并能不结束。
            count++;
            // 2. 通过unsafe对象调用storeFence()方法，内存屏障，能结束线程。
            //UnsafeFactory.getUnsafe().storeFence();
            //3.释放CPU时间片，上下文切换，主程序加载最新值，能结束线程
            //Thread.yield();
            //4.内部有synchronized关键字，底层还是调用内存屏障能结束线程
            //System.out.println(count);
            //5.唤醒当前线程，内存屏障
            //LockSupport.unpark(Thread.currentThread());

            // 6.volatile修饰flag变量，可以结束
            // 7.volatile修饰count变量，可以结束
            // 8.count 使用Integer,final关键字修饰value值保证可见性。

            //9.sleep内存屏障,sleep调用操作系统代码fence()方法
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //总结：  Java中可见性如何保证？ 方式归类有两种：
            //1.  jvm层面 storeLoad内存屏障    ===>  x86   lock替代了mfence
            // 2.  上下文切换   Thread.yield();

        }
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
    }

    public void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }

    public static void main(String[] args) {

        VolatileDemo demo=new VolatileDemo();
        Thread threadA=new Thread(()->{
            demo.start();
        },"ThreadA");

        threadA.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB=new Thread(()->{
            demo.setFlag();
        },"ThreadB");

        threadB.start();
    }

}
