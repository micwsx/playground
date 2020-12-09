package com.enjoy.concurrent.customThreadPool;

/**
 * @author Michael
 * @create 12/7/2020 4:28 PM
 */
public class MyWorkThread extends Thread {
    private Runnable target;
    private CustomThreadPool pool;

    public MyWorkThread(Runnable target, String name, CustomThreadPool pool) {
        super(name);
        this.target = target;
        this.pool = pool;
    }

    @Override
    public void run() {
        //如果执行完后，再从任务队列中获取并移除任务
        while (this.target != null || (this.target = pool.getTaskQueue().poll(2)) != null) {
            this.target.run();
            this.target = null;
        }
    }
}
