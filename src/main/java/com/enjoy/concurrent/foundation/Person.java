package com.enjoy.concurrent.foundation;

import com.enjoy.SleepUtil;

public class Person {

    private String name="Empty Name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        Thread father = new Thread(new Father(person));
        Thread bureau = new Thread(new PoliceBureau(person));
        bureau.start();
        SleepUtil.sleep(1000);
        father.start();

    }
}

class Father implements Runnable {

    private Person person;

    public Father(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println("Father is naming.");
        synchronized (person) {
            person.setName("Michael");
            System.out.println("修改名字为：Michael" );
            person.notifyAll();
        }
    }
}

class PoliceBureau implements Runnable {

    private Person person;

    public PoliceBureau(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println("PoliceBureau is checking.");
        synchronized (person) {
            while (!person.getName().equals("Michael")) {
                try {
                    System.out.println(person.getName() + " is not equal 'Michael', wating father changing it.");
                    person.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(person.getName() + " is born!");
        }
    }
}
