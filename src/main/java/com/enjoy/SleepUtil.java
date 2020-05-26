package com.enjoy;

public final class SleepUtil {

    public static void sleep(long milisecond) {
        try {
            Thread.sleep(milisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
