package com.enjoy.algorithm.question;

/**
 * @Author wu
 * @Date 4/13/2021 8:38 AM
 * @Version 1.0
 * Bitmap
 */
public class BitmapAlgorithm {

    private int size = 64;// bitmap总长度大小
    private long[] words;//根据size大小使用以64位单位bitmap数组存储。

    public BitmapAlgorithm(int size) {
        if (size > 0)
            this.size = size;
        // size=64,length=1;size=128,length=2;
        int length = ((size - 1) / Long.SIZE) + 1;
        words = new long[length];
    }

    /**
     * 获取word数组索引下标
     *
     * @param bitNumber 位数值; e.g. 128-获取第128位的word索引是1。
     * @return
     * @throws Throwable
     */
    private int getWordsIndex(int bitNumber) throws Throwable {
        checkIndex(bitNumber);
        // 找到数组索引下标。
        int i = (bitNumber - 1) / Long.SIZE;
        return i;
    }

    private void checkIndex(int bitNumber) throws Throwable {
        // 128对应的索引下标
        if (bitNumber > this.size - 1 || bitNumber < 0) {
            throw new RuntimeException("out of the index: " + bitNumber);
        }
    }

    // 置1操作
    public void setFlag(int bitNumber) throws Throwable {
        checkIndex(bitNumber);
        int index = getWordsIndex(bitNumber);
        int offset = bitNumber % Long.SIZE;
        words[index] |= (1L << offset);
    }

    // 置0操作
    public void removeFlag(int bitNumber) throws Throwable {
        checkIndex(bitNumber);
        int index = getWordsIndex(bitNumber);
        int offset = bitNumber % Long.SIZE;
        words[index] &= ~(1 << offset);
    }

    // 获取值是true还是false
    public boolean getBit(int bitNumber) throws Throwable {
        checkIndex(bitNumber);
        int index = getWordsIndex(bitNumber);
        int offset = bitNumber % Long.SIZE;
        return (words[index] & (1 << offset)) != 0;
    }


    public static void main(String[] args) {
        try {
            
            BitmapAlgorithm bitmapAlgorithm = new BitmapAlgorithm(128);
            bitmapAlgorithm.setFlag(127);
            bitmapAlgorithm.setFlag(63);
            boolean bit = bitmapAlgorithm.getBit(127);
            System.out.println(bit);
            System.out.println(bitmapAlgorithm.getBit(63));

            bitmapAlgorithm.removeFlag(63);
            System.out.println(bitmapAlgorithm.getBit(63));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
