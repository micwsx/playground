package com.enjoy.concurrent.optimization;

public class FakeSharing  implements Runnable{

    public final static int NUM_THREADS =  Runtime.getRuntime().availableProcessors();
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

//    private static VolatileLongAnnotation[] longs = new VolatileLongAnnotation[NUM_THREADS];
    private static VolatileLongPadding[] longs = new VolatileLongPadding[NUM_THREADS];
//    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    static{
        /*将数组初始化*/
        for (int i = 0; i < longs.length; i++){
            longs[i] = new VolatileLongPadding();
        }
    }

    public FakeSharing(final int arrayIndex){
        this.arrayIndex = arrayIndex;
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }

    public static void main(final String[] args) throws Exception{
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException{
        /*创建和CPU数相同的线程*/
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new FakeSharing(i));
        }

        for (Thread t : threads){
            t.start();
        }

        /*等待所有线程执行完成*/
        for (Thread t : threads){
            t.join();
        }
    }




    public final static class VolatileLong {
        public volatile long value = 0L;
    }


    private static final class VolatileLongPadding {
        public long p1, p2, p3, p4, p5, p6, p7;
        public volatile long value = 0L;
        volatile long q0, q1, q2, q3, q4, q5, q6;
    }

    // Java 8中，提供了@sun.misc.Contended注解来避免伪共享，原理是在使用此注解的对象或字段的前后各增加128字节大小的padding
    // -XX:-RestrictContended
    @sun.misc.Contended
    private static final class VolatileLongAnnotation {
        public volatile long value = 0L;
    }

//    duration =30500210300 不加-XX:-RestrictContended，默认不会填充
//    duration =6212071800 加-XX:-RestrictContended，@sun.misc.Contended系统自动填充
//    duration =6225661000 手动填充不加-XX:-RestrictContended
//    duration =38987823000 不填充，很慢

}
