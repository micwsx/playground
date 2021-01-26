package com.enjoy.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Michael
 * @create 1/25/2021 11:09 AM
 * 》》与BIO不同,NIO显然只使用单个线程，节省系统开销
 * 》》通道与业务之间需要ByteBuffer(JDK自带),Buffer中以Chunk单元传送数据较快。
 * 》》减少数据拷贝:(DirectBuffer)JVM缓冲区到JVM堆内存拷贝过程。()
 * <p>
 * 多路复用监听技术：
 * 1.只有一个线程用于监听,通过Selector能监听多个用户请求事件。
 * 2.ServerSocketChannel向Selector注册连接Accept事件。
 * 3.通道连接成功后向Selector注册感兴趣读写事件。
 * 4.Selector底层操作系统会将触发事件后会通知对应的Channel，处理相关数据。
 * 5.单个线程同步非阻塞，避免上下文切换。
 */
public class EchoClient implements Runnable {

    private SocketChannel socketChannel;
    private Selector selector;
    private int port;

    public EchoClient(int port) {
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
            this.port = port;

            System.out.println("Client started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // 先主动连接，先确认连接成功
            connect();

            while (true) {
                //System.out.println("开始监听通道");
                // 超时阻塞获取是否有触发事件的通道。
                selector.select(1000);
                // 获取通道事件集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //循环获取每个触发事件SelectionKey
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = null;
                    try {
                        selectionKey = iterator.next();
                        // 处理后的事件Key从键集合中移除
                        iterator.remove();
                        handleEvent(selectionKey);
                    } catch (Exception ex){
                        // 处理后的事件key取消关注
                        if (selectionKey != null){
                            System.out.println("关闭通道");
                            selectionKey.cancel();
                            if (selectionKey.channel()!=null){
                                selectionKey.channel().close();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleEvent(SelectionKey selectionKey) throws Exception {
        if (selectionKey.isValid()) {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            if (selectionKey.isConnectable()) {
                // 连接成功后关注读事件,
                if (channel.finishConnect()){
                    System.out.println("连接成功");
                    // 一般客户端只关注读事件-服务发送数据
                    // 注意：这里是socketChannel，而不是selectionKey获取的channel(其实都可以)
                    socketChannel.register(selector, SelectionKey.OP_READ);
//                    channel.register(selector, SelectionKey.OP_READ);
                }
                else
                    System.exit(1);
            } else if (selectionKey.isReadable()) {
                // 表示服务器有数据发送信号
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                // 向buffer中读数据
                int readBytes = channel.read(byteBuffer);
                // 读有数据
                if (readBytes > 0) {
                    byteBuffer.flip();
                    byte[] receivedData = new byte[byteBuffer.remaining()];
                    byteBuffer.get(receivedData);
                    System.out.println("Client recieved: " + new String(receivedData, "utf-8"));
                } else if (readBytes < 0) {
                    //没有数据取消关注事件
                    selectionKey.cancel();
                    //当前通道关闭
                    channel.close();
                }
            }
        }
    }

    /**
     * 连接可能出现异常
     *
     * @return
     */
    private void connect() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(port);
            boolean connected = socketChannel.connect(socketAddress);
            if (connected) {
                // 连接成功，则注册读事件
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                // 没有连接成功，则注册连接事件
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            connect();
            e.printStackTrace();
        }
    }

    /**
     * 发送服务器数据
     *
     * @param message
     */
    public boolean send(String message) {
        try {
            byte[] dataByte = message.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(dataByte.length);
            byteBuffer.put(dataByte);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        EchoClient client = new EchoClient(12345);
        //开启一个线程执行客户端启动连接，
        new Thread(client).start();
        Scanner scanner = new Scanner(System.in);
        while (client.send(scanner.nextLine())) ;
    }

}
