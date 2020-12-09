package com.enjoy.concurrent.customThreadPool;

/**
 * @author Michael
 * @create 12/7/2020 5:14 PM
 */
public interface PolicyHandler {
    void handle(CustomThreadPool pool,MyTask task);
}
