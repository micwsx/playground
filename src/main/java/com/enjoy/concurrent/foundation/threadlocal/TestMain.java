package com.enjoy.concurrent.foundation.threadlocal;

/**
 * @author: Michael
 * @date: 9/12/2022 12:04 PM
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadLocalStorage2(), "test");
        thread.start();

        thread.join();
        System.out.println("END");
    }
}
