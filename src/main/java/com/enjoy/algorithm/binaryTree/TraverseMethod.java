package com.enjoy.algorithm.binaryTree;

/**
 * @Author wu
 * @Date 3/30/2021 2:51 PM
 * @Version 1.0
 * NLR  前序遍历
 * LNR  中序遍历
 * LRN  后序遍历
 */
public class TraverseMethod {

    public static void main(String[] args) {

        Node root = Node.createTree();
//        beforeTraverse(root);
//        middleTraverse(root);
//        afterTraverse(root);
    }


    // 前序遍历-根结点,左结点,右结点. ABCDEFGHK
    public static void beforeTraverse(Node root) {
        // 先打印根结点
        System.out.print(root.getData() + ",");
        // 再左结点
        if (root.getLeft() != null)
            beforeTraverse(root.getLeft());
        if (root.getRight() != null)
            beforeTraverse(root.getRight());
    }



    // 中序遍历-左结点,根结点,右结点. BDCAEHGKF
    public static void middleTraverse(Node root) {
        // 先左结点
        if (root.getLeft() != null)
            middleTraverse(root.getLeft());
        // 再根结点
        System.out.print(root.getData() + ",");
        // 最后右结点
        if (root.getRight() != null)
            middleTraverse(root.getRight());
    }



    // 后序遍历-左结点,右结点,根结点. DCBHKGFEA
    public static void afterTraverse(Node root) {
        // 先左结点
        if (root.getLeft() != null)
            afterTraverse(root.getLeft());
        // 再右结点
        if (root.getRight() != null)
            afterTraverse(root.getRight());
        // 最后根结点
        System.out.print(root.getData() + ",");
    }



}
