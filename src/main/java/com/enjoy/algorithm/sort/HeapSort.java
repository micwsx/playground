package com.enjoy.algorithm.sort;

import java.util.Arrays;

/**
 * @Author wu
 * @Date 3/27/2021 9:46 AM
 * @Version 1.0
 * 堆并排序：初始化数组时构建一个最大堆，调整堆，然后把根元素与最后叶子结点交互，再从根结点开始调整。
 *
 */
public class HeapSort  {


    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] sort = heapSort.sort(new int[]{1, 4, 2, 3, 7, 5,6});
//        int[] sort = heapSort.sort(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        System.out.println(Arrays.toString(sort));

    }


    public int[] sort(int[] array) {

        // 数组看成一个二叉树存储

        // 根据数组长度计算需要调整的结点数量计算，排除叶子结点，叶子结点先不用调整。
        // 2^n-1=len, 2^(n-1)-1=x; 可求出x即结点数量。除以2就可以获取需要排序的非叶子结点个数。
        //堆排序： 对非叶子结点调整，让父结点比左右结点都大,从根结点开始调整。
        for (int i = (array.length / 2) - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }

        for (int j = array.length - 1; j >= 0; j--) {
            // 根结点与最后一个叶子结点交换
            swap(array, 0, j);
            // 再调整
            adjustHeap(array, 0, j);
        }
        return array;
    }

    /**
     * 从pos位置开始对此节点进行调整，将节点的左右节点比较，最后将最大的放入根结点位置，结束。
     * @param array
     * @param pos
     * @param len
     */
    public void adjustHeap(int[] array, int pos, int len) {
        int root = array[pos];
        // 从pos位置的左子结点开始即2n+1
        for (int i = pos * 2 + 1; i < len; i = i * 2 + 1) {
            //存在右结点 并且右结点>左结点值
            if (i + 1 < len && array[i + 1] > array[i]) ++i;
            // 左右子结点都小于父结点则结束循环。
            if (array[i] <= root) break;
            // 直接左右结点中最大值赋值给根结点
            array[pos] = array[i];
            // 将子结点索引设置为刚开始位置
            pos = i;
        }
        // 空位置再放根元素
        array[pos]=root;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
