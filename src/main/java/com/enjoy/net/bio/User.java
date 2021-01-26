package com.enjoy.net.bio;

import java.io.*;
import java.net.URL;


public class User implements Serializable {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 自定义实现对象序列化，不需要对象实现Serializable接口
     *
     * @return
     * @throws IOException
     */
    public static byte[] codeC(User user) throws IOException {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(outputStream);) {
            byte[] bytes = user.name.getBytes();
            out.write(bytes.length);
            out.write(bytes);
            out.write(user.age);
            out.flush();
            byte[] result = outputStream.toByteArray();
            return result;
        }
    }

    /**
     * 自定义实现对象反序列化，不需要对象实现Serializable接口
     *
     * @param bytes
     * @return
     * @throws IOException
     */
    public static User decodeC(byte[] bytes) throws IOException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(inputStream);) {
            int length = in.read();
            byte[] data = new byte[length];
            in.read(data, 0, length);
            int age = in.read();
            User user = new User(new String(data, "utf-8"), age);
            return user;
        }
    }

    public static void main(String[] args) {
        // JDK ByteBuffer flip操作对pos,limit,cap影响。
//        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
//        System.out.println(byteBuffer.remaining());
//        byteBuffer.putInt(100);// pos=4,limit=4,cap=4
//        byteBuffer.flip();// pos=0,limit=4,cap=4;
//        byte[] result = new byte[byteBuffer.remaining()];//limit-pos=4
//        System.out.println(byteBuffer.remaining());
//        byteBuffer.get(result);
//        System.out.println(byteBuffer.remaining());

        try {
            User user = new User("Michael", 40);
            // 自定义序列化与反序列化
            byte[] bytes = User.codeC(user);
            User user1 = user.decodeC(bytes);
            System.out.println(user1);

            // JDK 序列化与反序列化
            serializeObject(user);
            System.out.println("done");
            User restoreUser = deserializeObject();
            System.out.println(restoreUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JDK内部序列化,必须要求User实现Serializable接口
     *
     * @param user
     */
    private static void serializeObject(User user) {
        try {
            URL resource = User.class.getClassLoader().getResource("");
            String file = resource.getFile();
            String path = new File(file + File.separator + "user.txt").getPath();
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
                 ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);) {
                // JDK负责将对象转化成二进制流
                out.writeObject(user);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JDK内部反序列化,必须要求User实现Serializable接口
     *
     * @return
     */
    private static User deserializeObject() {
        User user = null;
        try {
            URL resource = User.class.getClassLoader().getResource("");
            String file = resource.getFile();
            String path = new File(file + File.separator + "user.txt").getPath();
            try (FileInputStream fileInputStream = new FileInputStream(new File(path));
                 ObjectInputStream in = new ObjectInputStream(fileInputStream);) {
                // JDK负责将二进制流转化成对象
                user = (User) in.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

}
