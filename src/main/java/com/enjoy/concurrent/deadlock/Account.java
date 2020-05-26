package com.enjoy.concurrent.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private ReentrantLock lock;
    private String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
        lock = new ReentrantLock();
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
