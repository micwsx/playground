package com.enjoy.concurrent.language;

import com.enjoy.SleepUtil;
import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteFutureUsage {


    private static class Task extends Thread {
        private CompletableFuture<Integer> f;

        Task(String threadName, CompletableFuture<Integer> f) {
            super(threadName);
            this.f = f;
        }

        @Override
        public void run() {
            try {
                System.out.println("waiting result.....");
                System.out.println(this.getName() + ": " + f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        thenAccept();
//        runAfterBothAsync();
//        handle();
//        allOf();
//        getAndJoin();
//        basicUsage();
    }

    private static void thenAccept() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept(s -> System.out.println(s + " world"));
    }

    private static void runAfterBothAsync() {
        CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleep(1000);
            System.out.println("s1");
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            //SleepUtil.sleep(2000);
            System.out.println("s2");
            return "s2";
        }), () -> System.out.println("hello world"));
        SleepUtil.sleep(3000);
    }

    private static void handle() {
        /*出现异常时*/
        String result = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleep(1000);
            //出现异常
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);

        /*未出现异常时*/
        String result2 = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleep(1000);
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result2);
    }

    private static void allOf() {
        Random rand = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成");
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2完成");
            return "abc";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future3完成");
            return "123abc";
        });

        System.out.println("----------------");
        // 并不会阻塞主线程
        CompletableFuture.allOf(future1, future2, future3).thenRun(() -> {
            System.out.println("All done!");
        });
        System.out.println("----------------");

        System.out.println("***********");
        try {

            CompletableFuture<Object> f = CompletableFuture.anyOf(future1, future2, future3);
            // 阻塞主线程
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("***********");

        SleepUtil.sleep(5000);

    }

    private static void getAndJoin() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //int i = 1/0;
            return 100;
        });
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Integer result = future.join();
        System.out.println(result);
    }

    private static void basicUsage() {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();

        new Thread(new Task("Michael", completableFuture)).start();
        new Thread(new Task("Jack", completableFuture)).start();

        System.out.println("Sleeping 2s...");
        SleepUtil.sleep(2000);
//        completableFuture.complete(100);
        completableFuture.completeExceptionally(new Exception());
    }
}
