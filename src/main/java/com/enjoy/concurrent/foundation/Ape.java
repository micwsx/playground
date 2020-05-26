package com.enjoy.concurrent.foundation;

/**
 * start,sleep,interrupt,isInterrupted,join,yield,wait,notify
 * join方法控制线程执行顺序
 */
public class Ape extends Thread{

    public Ape() {
        super("Ape");
    }

    @Override
    public void run() {
        System.out.println("Ape starts playing.");
    }

    public static void main(String[] args) {
        Ape ape=new Ape();
        Gorilla gorilla=new Gorilla(ape);
        Chimp chimp=new Chimp(gorilla);
        chimp.start();
        gorilla.start();
        ape.start();
    }
}

class Chimp extends Thread {
    private Thread thread;
    public Chimp(Thread thread) {
        super("Chimp");
        this.thread=thread;
    }

    @Override
    public void run() {
        System.out.println("Waiting for "+thread.getName());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Chimp starts playing.");
    }
}

class Gorilla extends Thread {

    private Thread thread;
    public Gorilla(Thread thread) {
        super("Gorilla");
        this.thread=thread;
    }

    @Override
    public void run() {
        System.out.println("Waiting for "+thread.getName());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Gorilla starts playing.");
    }
}
