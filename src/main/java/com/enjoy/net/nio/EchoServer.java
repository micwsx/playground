package com.enjoy.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Michael
 * @create 1/25/2021 11:09 AM
 *
 */
public class EchoServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public EchoServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            InetSocketAddress socketAddress = new InetSocketAddress(port);
            serverSocketChannel.bind(socketAddress);
            //先开始，关注客户端连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
                selector.select(1000);
                // 阻塞监听触发事件的通道
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    // 从队列中移除
                    iterator.remove();
                    try {
                        handleEvent(selectionKey);
                    } catch (IOException e) {
                        if (selectionKey.channel() instanceof SocketChannel) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            InetSocketAddress address = (InetSocketAddress) socketChannel.getLocalAddress();
                            System.out.println("客户端【"+address.getAddress().getHostAddress() + ":" + address.getPort()+"】断开连接...");
                        }
                        //断开连接后，取消关注事件键，不然selector会一直得到此selectKey
                        selectionKey.cancel();
                        //关闭通道连接
                        selectionKey.channel().close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEvent(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isValid()) {
            if (selectionKey.isAcceptable()) {
                // 客户端注册Connection事件
                ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                InetSocketAddress localAddress = (InetSocketAddress) socketChannel.getLocalAddress();
                System.out.println("建立客户端【" + localAddress.getAddress().getHostAddress() + ":" + localAddress.getPort() + "】连接...");
                // 服务器一般只关注读事件-客户端发送数据
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                // 表示客户端有数据发送信号，数据已准备好可以读取
                SocketChannel channel = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //从channel中获得数据
                int readBytes = channel.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    //获取真实数据
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    String message = new String(data, "utf-8");
                    // 打印收到数据
                    System.out.println(message);
                    reply(channel, "Server received: " + message);
                } else if (readBytes < 0) {
                    //取消特定注册关系
                    selectionKey.cancel();
                    //关闭通道
                    channel.close();
                }
            }
        }
    }

    private void reply(SocketChannel channel, String message) throws IOException {
        byte[] msg = message.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length);
        // 写入bytebuffer
        byteBuffer.put(msg);
        byteBuffer.flip();
        // 写入channel
        channel.write(byteBuffer);

        System.out.println(message);
    }

    public static void main(String[] args) {
        EchoServer server = new EchoServer(12345);
        server.start();
    }
}
