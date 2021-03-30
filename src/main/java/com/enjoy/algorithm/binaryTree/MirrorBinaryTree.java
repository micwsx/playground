package com.enjoy.algorithm.binaryTree;

/**
 * @Author wu
 * @Date 3/30/2021 3:11 PM
 * @Version 1.0
 * 将二叉树所有结点的左右结点互换
 */
public class MirrorBinaryTree {

    public static void main(String[] args) {
        Node root = Node.createTree();
        mirror(root);
        // 最后使用前序遍布打印树结点,期望结果是A,E,F,G,K,H,B,C,D
        TraverseMethod.beforeTraverse(root);
    }

    public static void mirror(Node root) {
        //中序遍历方式，依次交换左右结点。
        if (root != null) {
            Node node = root.getRight();
            root.setRight(root.getLeft());
            root.setLeft(node);
        }
        if (root.getLeft() != null)
            mirror(root.getLeft());
        if (root.getRight() != null)
            mirror(root.getRight());
    }


}
