package com.enjoy.net.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class Server {

    public static final int PORT = 12345;

    private ServerSocket serverSocket;

    public Server() {
        try {
            //创建serversocket
            serverSocket = new ServerSocket();
            //创建服务器地址
            InetSocketAddress socketAddress = new InetSocketAddress(PORT);
            //绑定地址
            serverSocket.bind(socketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收对象序列化二进制数据，通过自定义反序列化方法生成User并打印
     */
    public void start() {
        try {
            System.out.println("Server started...");
            while (true) {
                // 使用线程池节省系统开销
                new Thread(() -> {
                    try {
                        // 阻塞获取获取客户端socket连接
                        Socket socket = serverSocket.accept();
                        // 获取用户数据输入流
                        try (InputStream inputStream = socket.getInputStream();
                             ObjectInputStream in = new ObjectInputStream(inputStream);) {

                            byte[] data = new byte[in.available()];
                            in.read(data, 0, data.length);
                            User restoreUser = User.decodeC(data);
                            System.out.println(restoreUser);

                            String reply = "Server received: " + restoreUser.toString();
                            OutputStream outputStream = null;
                            ObjectOutputStream out = null;
                            // 获取输出流
                            try {
                                outputStream = socket.getOutputStream();
                                out = new ObjectOutputStream(outputStream);
                                out.writeBytes(reply);
                                out.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (out != null) out.close();
                                if (outputStream != null) outputStream.close();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *将User信息保存到本地文件
     */
    public void startFile() {
        try {
            System.out.println("Server started...");
            while (true) {
                // 使用线程池节省系统开销
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        // 阻塞获取获取客户端socket连接
                        URL resource = User.class.getClassLoader().getResource("");
                        String file = resource.getFile();
                        File destFile = new File(file + File.separator + "server_receive.txt");
                        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
                        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
                        // 获取用户数据输入流
                        try (DataInputStream in = new DataInputStream(socket.getInputStream());
                             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                            int totalLen = in.readInt();
                            int count = 0;
                            byte[] buffer = new byte[1024];
                            while (count<totalLen){
                                int temp = in.read(buffer, 0, buffer.length);
                                byteArrayOutputStream.write(buffer, 0, temp);
                                count+=temp;
                            }
                            //获得全部数据后反序列化生成User
                            byte[] receivedData=byteArrayOutputStream.toByteArray();
                            User restoreUser = User.decodeC(receivedData);
                            //反序列化后将User信息保存到本地文件。
                            outputStreamWriter.write(restoreUser.toString());
                            outputStreamWriter.flush();
                            outputStreamWriter.close();

                            System.out.println("Server received: "+restoreUser.toString());
                            System.out.println("start reply...");
                            // 获取输出流
                            out.writeUTF("Server done");
                            out.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startFile();
    }

}
