package com.enjoy.hackerrank.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Michael
 * @create 12/28/2020 12:44 PM
 */
public class SwapNodes {

    public static void main(String[] args) {

//        int[] binaryTree = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16};
//        printBinaryTree(binaryTree);

        int[][] indexes = new int[][]{{2, 3}, {4, -1}, {5, -1}, {6, -1}, {7, 8}, {-1, 9}, {-1, -1}, {10, 11}, {-1, -1}, {-1, -1}, {-1, -1}};
        int[] queries = new int[]{2, 4};

       swapNodes(indexes, queries);
    }

    private static void printBinaryTree(int[] binaryTree) {
//        int[] binaryTree = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15，16};

        long depth = (int) Math.ceil(Math.log(binaryTree.length + 1) / Math.log(2)) - 1;//tree depth based 0;

        for (int i = 0; i <= depth; i++) {
            int spaceNum = (int) Math.pow(2, depth - i) - 1;
            // 左边
            for (int left = 0; left < spaceNum; left++) {
                System.out.print("-");
            }

            // 打印中间数字
            int numCount = (int) Math.pow(2, i);// 打印数字个数
            for (int j = 0; j < numCount; j++) {
                // 打印数字
                int index = j + (int) Math.pow(2, i) - 1;
                if (index < binaryTree.length) {
                    System.out.print(binaryTree[index]);
                } else {
                    System.out.print("X");
                }
                // 打印数字间隔中空格
                if (j < numCount - 1) {
                    int spaceBetweenNum = (int) Math.pow(2, depth + 1 - i) - 1;
                    for (int k = 0; k < spaceBetweenNum; k++) {
                        System.out.print("-");
                    }
                }
            }

            // 右边空格
            for (int right = 0; right < spaceNum; right++) {
                System.out.print("-");
            }
            // new line
            System.out.println();
        }
    }


    /**
     * @param indexes 二叉树二维数组
     * @param queries 第几层需要交换子结点
     * @return
     */
    static void swapNodes(int[][] indexes, int[] queries) {

        Node tree = buildTree(indexes);
//        printLeftRootRight(tree);

        for (int i = 0; i < queries.length; i++) {
            int level = queries[i];
            swapChildren(tree, level);
            printLeftRootRight(tree);
            System.out.println();
        }
    }

    public static void swapChildren(Node node, int depth) {
        if (node == null)
            return;
        if (node.depth % depth == 0) {
            Node temp = node.right;
            node.right = node.left;
            node.left = temp;
        }
        swapChildren(node.left, depth);
        swapChildren(node.right, depth);
    }

    static int index = 0;

    private static void printLeftRootRight(Node tree) {
        if (tree == null)
            return;
        if (tree.left != null)
            printLeftRootRight(tree.left);
        if (tree.data != -1)
            System.out.print(tree.data + " ");
        printLeftRootRight(tree.right);
    }


    private static Node buildTree(int[][] indexes) {
        HashMap<Integer, Node> cache = new HashMap<>();
        Node root = new Node(1, 1, null, null);
        cache.put(1, root);
        for (int i = 0; i < indexes.length; i++) {
            int L = indexes[i][0];
            int R = indexes[i][1];
            Node parent = cache.get(i + 1);
            Node leftNode = buildNode(L, parent.depth + 1);
            Node rightNode = buildNode(R, parent.depth + 1);
            if (L != -1) {
                cache.put(L, leftNode);
            }
            if (R != -1) {
                cache.put(R, rightNode);
            }
            parent.right = rightNode;
            parent.left = leftNode;
        }
        return root;
    }

    public static Node buildNode(int data, int depth) {
        return new Node(data, depth, null, null);
    }

    public static class Node {
        private int data;
        private int depth;
        private Node left;
        private Node right;

        public Node(int data, int depth, Node left, Node right) {
            this.data = data;
            this.depth = depth;
            this.left = left;
            this.right = right;
        }
    }


}
