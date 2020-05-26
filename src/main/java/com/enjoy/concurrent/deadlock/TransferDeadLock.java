package com.enjoy.concurrent.deadlock;

import com.enjoy.SleepUtil;

import java.util.Random;

/**
 * 死锁问题
 */
public class TransferDeadLock {


    public static void main(String[] args) {


        Account accountA = new Account("Michael", 500);
        Account accountB = new Account("Kris", 500);
        TransferDeadLock transferDeadLock = new TransferDeadLock();

        Thread thread1 = new Thread(() -> {
            int amount = 200;
            System.out.println(accountA.getName() + "向" + accountB.getName() + "开始转" + amount);
            while (!Thread.currentThread().isInterrupted()) {
//                transferDeadLock.fromToto(accountA, accountB, amount);
                transferDeadLock.saftFromToTo(accountA, accountB, amount);
            }
        }, "任务1");


        Thread thread2 = new Thread(() -> {
            int amount = 100;
            System.out.println(accountB.getName() + "向" + accountA.getName() + "开始转" + amount);
            while (!Thread.currentThread().isInterrupted()) {
//                transferDeadLock.fromToto(accountB, accountA, amount);
                transferDeadLock.saftFromToTo(accountB, accountA, amount);
            }
        }, "任务2");

        thread1.start();
        thread2.start();
    }

    /**
     * 转账优化方法，线程安全
     * @param from
     * @param to
     * @param amount
     */
    public void saftFromToTo(Account from, Account to, int amount) {
        Random random = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            // 尝试获取from账户锁
            if (from.getLock().tryLock()) {
                try {
                    //System.out.println(Thread.currentThread().getName() + ">>>" + from.getName() + " 账户开始扣钱！");
                    int balanceFrom = from.getBalance() - amount;
                    // 判断账户是否有钱
                    if (balanceFrom >= 0) {
                        // 尝试获取to账户锁
                        if (to.getLock().tryLock()) {
                            try {
                                //System.out.println(Thread.currentThread().getName() + ">>>" + to.getName() + " 账户开始收钱！");
                                int balanceTo = to.getBalance() + amount;
                                to.setBalance(balanceTo);
                                //System.out.println(Thread.currentThread().getName() + ">>>" + to.getName() + " 账户收钱成功！");
                            } finally {
                                // 释放to账户锁
                                to.getLock().unlock();
                            }
                            from.setBalance(balanceFrom);
                            System.out.println(Thread.currentThread().getName() + ">>>" +
                                    "[" + from.getName() + ":" + from.getBalance() + "]-[" + to.getName() + ":" + to.getBalance() + "]" + " 账户扣钱成功！");
                        } else {
                            System.out.println(Thread.currentThread().getName() + ">>>" +
                                    "[" + from.getName() + ":" + from.getBalance() + "] 转账 [" + to.getName() + ":" + to.getBalance() + "]" + "拿不到锁");
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + ">>>" +
                                "[" + from.getName() + ":" + from.getBalance() + "] 转账 [" + to.getName() + ":" + to.getBalance() + "] 余额不足！");
                        // 停止转账操作
                        Thread.currentThread().interrupt();
                    }
                } finally {
                    // 释放from账户锁
                    from.getLock().unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + ">>>" +
                        "[" + from.getName() + ":" + from.getBalance() + "] 转账 [" + to.getName() + ":" + to.getBalance() + "] 拿不到锁！");
            }
            // 随机休眠有效防止活锁，
            try {
                if (!Thread.currentThread().isInterrupted())
                    Thread.sleep(2);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ">>>" +
                        "[" + from.getName() + ":" + from.getBalance() + "] 转账 [" + to.getName() + ":" + to.getBalance() + "] 执行异常！已中断。！");
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * 存在线程安全问题，若客户端传入参数时to和from交换，则同样会出现拿锁顺序不一致情况造成死锁情况。
     *
     * @param from
     * @param to
     * @param amount
     */
    public void fromToto(Account from, Account to, int amount) {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + ">>>" + from.getName() + " 账户开始扣钱！");
            int balanceFrom = from.getBalance() - amount;
            if (balanceFrom > 0) {
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + ">>>" + to.getName() + " 账户开始收钱！");
                    int balanceTo = to.getBalance() + amount;
                    to.setBalance(balanceTo);
                    System.out.println(Thread.currentThread().getName() + ">>>" + to.getName() + " 账户收钱成功！");
                }
                from.setBalance(balanceFrom);
                System.out.println(Thread.currentThread().getName() + ">>>" + from.getName() + " 账户开始扣钱成功！");
            } else {
                System.out.println(from.getName() + "余额不足,为：" + from.getBalance());
                // 停止转账操作
                Thread.currentThread().interrupt();
            }
        }
    }


}
