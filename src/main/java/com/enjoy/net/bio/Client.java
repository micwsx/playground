package com.enjoy.net.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket = null;

    public Client() {
        try {
            socket = new Socket();
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", Server.PORT);
            socket.connect(socketAddress);
            System.out.println("Client started");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(User user) {
        DataOutputStream out = null;
        ObjectInputStream in = null;
        // 获取输出流
        try {
             // 简单点使用java jdk内部序列化过程使用ObjectOutputStream,然后writeObject(user)
            // out=new ObjectOutputStream(socket.getOutputStream());
            // 使用DataOutputStream直接输入自定义序列化对象二进制流
            out = new DataOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // 使用自定义序列化方法
            byte[] userData = User.codeC(user);
            //总数据长度
            out.writeInt(userData.length);
            out.write(userData);
            out.flush();

            System.out.println("Client sent: " + user.toString());

            // 获取用户数据输入流
            String reply = "Client received: " + in.readUTF();
            System.out.println(reply);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                socket.close();
                out.close();
                in.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.sendMessage(new User("Michael", 30));
    }
}
