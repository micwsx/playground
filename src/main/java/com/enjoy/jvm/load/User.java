package com.enjoy.jvm.load;

import java.util.ArrayList;

/**
 * @author: Michael
 * @date: 1/17/2022 11:10 PM
 */

public class User {

    private static ArrayList<User> arrayList=new ArrayList<>();

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {

//        arrayList.add(this); 在对象快回收之前，再引用一下挽回。
        System.out.println("释放对象"+this.id);
    }

    public void say(){
        System.out.println("hello.");
    }
}
