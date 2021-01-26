package com.enjoy.net.nio;

import java.nio.ByteBuffer;

/**
 * @author Michael
 * @create 1/25/2021 3:11 PM
 */
public class BufferMethod {

    public static void main(String[] args) {
//        basic();
//        putAndGetNoInfluencePos();
//        clearOrCompact();
//        rewind();
//        markAndReset();
    }

    private static void markAndReset() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.position(5);
        byteBuffer.mark();//标记当前位置
        byteBuffer.position(10);
        System.out.println(byteBuffer);//[pos=10 lim=32 cap=32]
        byteBuffer.reset();//复位pos标记的位置
        System.out.println(byteBuffer);//[pos=5 lim=32 cap=32]
    }

    private static void rewind() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.position(10);
        byteBuffer.limit(15);
        System.out.println(byteBuffer);//[pos=10 lim=15 cap=32]
        byteBuffer.rewind();//只设置pos=0
        System.out.println(byteBuffer);//[pos=0 lim=15 cap=32]
    }

    /**
     * clear:并不会清除数据，pos=0,lim=cap
     * compact:并不会清除数据,pos=remaining,lim=cap
     */
    private static void clearOrCompact() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put((byte) 'a');
        System.out.println(byteBuffer);//[pos=1 lim=32 cap=32]
        byteBuffer.clear();//并没有清空数据，只是重置pos=0,lim=cap
        System.out.println(byteBuffer);//[pos=0 lim=32 cap=32]
        System.out.println(new String(byteBuffer.array()));//a
        // clear后pos=0,再写数据时会直接覆盖a的值。
        byteBuffer.put("bcde".getBytes());
        System.out.println(byteBuffer);//[pos=4 lim=32 cap=32]
        System.out.println(new String(byteBuffer.array()));//bcde
        byteBuffer.flip();//pos=0,lim=pos
        System.out.println(byteBuffer);//[pos=0 lim=4 cap=32]
        System.out.println(byteBuffer.remaining());//4
        //将pos=remaining,lim=cap
        byteBuffer.compact();
        System.out.println(byteBuffer);//[pos=4 lim=32 cap=32]
        System.out.println(new String(byteBuffer.array()));//bcde
    }

    /**
     * 绝对方式读写buffer，不影响pos
     */
    private static void putAndGetNoInfluencePos() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        System.out.println(byteBuffer);//[pos=0 lim=32 cap=32]
        byteBuffer.put((byte) 'a');
        System.out.println(byteBuffer);//[pos=1 lim=32 cap=32]

        byteBuffer.put(2, (byte) 'b');
        System.out.println(byteBuffer);//[pos=1 lim=32 cap=32]
        System.out.println(new String(byteBuffer.array()));//ab

        System.out.println((char) byteBuffer.get(2));//b
        System.out.println(byteBuffer);//[pos=1 lim=32 cap=32]
    }

    /**
     * 常规操作get,put,flip对pos,lim,cap影响变化
     */
    private static void basic() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');
        buffer.put((byte) 'd');
        buffer.put((byte) 'f');
        System.out.println("before flip: " + buffer);//[pos=5 lim=32 cap=32]
        buffer.flip();
        System.out.println("after flip: " + buffer);//[pos=0 lim=5 cap=32]
        char currentChar = (char) buffer.get();
        System.out.println(currentChar);//a
        System.out.println("aftre get(): " + buffer);//[pos=1 lim=5 cap=32]

        byte[] dst = new byte[10];
        // buffer从目前位置读取2个长度写入到dst字节数组中指定相对起始位置1和长度为2，
        buffer.get(dst, 1, 2);
        System.out.println(buffer);//[pos=3 lim=5 cap=32]
        System.out.println(new String(dst));//bc
    }
}
