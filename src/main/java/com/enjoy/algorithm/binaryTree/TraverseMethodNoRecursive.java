package com.enjoy.algorithm.binaryTree;

import java.util.Stack;

/**
 * @Author wu
 * @Date 3/30/2021 2:51 PM
 * @Version 1.0
 * 以下都是二叉树的深度深度优先遍历方式。
 * NLR  前序遍历
 * LNR  中序遍历
 * LRN  后序遍历
 * 非递归实现二叉树遍历
 */
public class TraverseMethodNoRecursive {

    public static void main(String[] args) {
        Node root = Node.createTree();
        beforeTraverse(root);
        System.out.println();
        middleTraverse(root);
        System.out.println();
        afterTraverse(root);
    }


    // 前序遍历-根结点,左结点,右结点. ABCDEFGHK
    public static void beforeTraverse(Node root) {
        Stack<Node> stack = new Stack<>();
        Node treeNode = root;
        // 结点不为空或者栈不为空
        while (treeNode != null || !stack.isEmpty()) {

            while (treeNode != null) {
                // 打印根结点数据
                System.out.print(treeNode.getData() + ",");
                // 存放栈中
                stack.push(treeNode);
                // 再从左结点开始
                treeNode = treeNode.getLeft();
            }
            //最后到了左结点的叶子结点，则开始回溯到上个结点。
            if (!stack.isEmpty()) {
                treeNode = stack.pop(); //去掉当前结点
                treeNode = treeNode.getRight();//切面到右结点
            }
        }
    }


    // 中序遍历-左结点,根结点,右结点. BDCAEHGKF
    public static void middleTraverse(Node root) {
        Stack<Node> stack = new Stack<>();
        Node treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                // 加入当前根结点
                stack.push(treeNode);
                // 指向下一个左结点
                treeNode = treeNode.getLeft();
            }
            // 直到左边最后一个子结点
            if (!stack.isEmpty()) {
                // 这个结点是下面空结点的父结点
                Node currentNode = stack.pop();
                // 打印此时的根节点
                System.out.print(currentNode.getData() + ",");
                // 切换到右结点(叶子结点的右结点为null)
                treeNode = currentNode.getRight();
            }
        }
    }


    // 后序遍历-左结点,右结点,根结点. DCBHKGFEA
    public static void afterTraverse(Node root) {
        Stack<Node> stack = new Stack<>();
        Node treeNode = root;
        Node pre = null;//上一次访问节点
        while (treeNode != null || !stack.isEmpty()) {
            // 1.将根结点及左孩子入栈
            while (treeNode != null) {
                // 根结点加入栈
                stack.push(treeNode);
                // 切换左结点
                treeNode = treeNode.getLeft();
            }
            // 遍历左结点最后一个叶子结点
            if (!stack.isEmpty()) {
                // 2.这个结点是下面空结点的父结点，这里只拿出来元素并不移除元素
                Node currentCode = stack.peek();
                // 3.没有右孩子或者右孩子已经被访问
                if (currentCode.getRight() == null || pre == currentCode.getRight()) {
                    //直接移除结点元素
                    currentCode = stack.pop();
                    pre = currentCode;
                    // 打印这个结点
                    System.out.print(currentCode.getData() + ",");
                } else {
                    // 4.存在没有被访问的右结点
                    treeNode = currentCode.getRight();
                }
            }
        }


    }


}
