package com.enjoy.designpattern.singleton;

import java.io.*;

/**
 * 多线程下能保证单例，但单例对象的序列化与反序列化不一定能保证单例.
 * 可序列化对象要实现Serializable接口
 */
public class SingletonSerialization implements Serializable {

    private SingletonSerialization(){}

    private static class InnerClass{
        public static SingletonSerialization instance=new SingletonSerialization();
    }

    /**deserializeObject()保证多次反序列化后的对象都是同一个
     * readResolve()方法到底是何方神圣，其实当JVM从内存中反序列化地"组装"一个新对象时，就会自动调用这个 readResolve方法来返回我们指定好的对象了
     * @return
     */
    protected Object readResolve(){
        System.out.println("invoke the SingletonSerialization.readResolve()");
        return InnerClass.instance;
    }

    public static SingletonSerialization getInstance(){return InnerClass.instance;}

    public static void main(String[] args) {
        SingletonSerialization instance=SingletonSerialization.getInstance();
        System.out.println(instance);

        serializeObject(instance);
        // 可以看到多次反序列化后的对象是不一样的。
        deserializeObject();
        deserializeObject();

    }

    /**
     *
     * @param instance
     */
    public static void serializeObject(Object instance){
        try(FileOutputStream fileOutputStream=new FileOutputStream("tem");
            ObjectOutputStream objectInputStream=new ObjectOutputStream(fileOutputStream);){
            objectInputStream.writeObject(instance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeObject(){
        try(FileInputStream fileInputStream=new FileInputStream("tem");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);){
            SingletonSerialization instance=(SingletonSerialization)objectInputStream.readObject();
            System.out.println(instance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
