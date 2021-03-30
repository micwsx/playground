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
        subBeforeTraverse(root);
    }

    private static void subBeforeTraverse(Node root) {
        // 先打印根结点
        System.out.print(root.getData() + ",");
        // 再左结点
        if (root.getLeft() != null)
            subBeforeTraverse(root.getLeft());
        if (root.getRight() != null)
            subBeforeTraverse(root.getRight());
    }


    // 中序遍历-左结点,根结点,右结点. BDCAEHGKF
    public static void middleTraverse(Node root) {
        subMiddleTraverse(root);
    }

    private static void subMiddleTraverse(Node root) {
        // 先左结点
        if (root.getLeft() != null)
            subMiddleTraverse(root.getLeft());
        // 再根结点
        System.out.print(root.getData() + ",");
        // 最后右结点
        if (root.getRight() != null)
            subMiddleTraverse(root.getRight());
    }

    // 后序遍历-左结点,右结点,根结点. DCBHKGFEA
    public static void afterTraverse(Node root) {
        subAfterTraverse(root);
    }

    private static void subAfterTraverse(Node root) {
        // 先左结点
        if (root.getLeft() != null)
            subAfterTraverse(root.getLeft());
        // 再右结点
        if (root.getRight() != null)
            subAfterTraverse(root.getRight());
        // 最后根结点
        System.out.print(root.getData() + ",");
    }
}
