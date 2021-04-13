package com.enjoy.algorithm.binaryTree;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author wu
 * @Date 4/13/2021 5:19 PM
 * @Version 1.0
 * 二叉树：广度优先遍历
 */
public class LevelOrderTraversal {

    // A,B,E,C,F,D,G,H,K
    private static void levelOrderTraverse(Node treeNode) {
        // 连接队列顺序结构
        LinkedList<Node> queue = new LinkedList<>();
        // 加入queue尾部
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            // 从头取出并移除元素
            Node currentNode = queue.poll();
            System.out.print(currentNode.getData() + ",");
            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }
    }

    public static void main(String[] args) {
        Node treeNode = Node.createTree();
        levelOrderTraverse(treeNode);
    }

}
