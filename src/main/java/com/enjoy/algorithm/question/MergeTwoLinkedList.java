package com.enjoy.algorithm.question;

/**
 * @Author wu
 * @Date 4/12/2021 1:50 PM
 * @Version 1.0
 */
public class MergeTwoLinkedList {

    private static class LinkedNode {
        private int value = 0;
        private LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
        }

        public LinkedNode() {

        }
    }

    public static LinkedNode merge(LinkedNode l1, LinkedNode l2) {
        LinkedNode head = new LinkedNode();
        head.value = l1.value < l2.value ? l1.value : l2.value;
        LinkedNode result = head;
        LinkedNode left;
        LinkedNode right;
        for (left = l1.next, right = l2.next; left != null || right != null; ) {
            if (left == null && right != null) {
                result.next = right;
                result = right;
                right = right.next;
            } else if (left != null && right == null) {
                result.next = left;
                result = left;
                right = left.next;
            } else {
                if (left.value <= right.value) {
                    result.next = left;
                    result = left;
                    left = left.next;
                } else {
                    result.next = right;
                    result = right;
                    right = right.next;
                }
            }
        }
        return head;
    }

    public static void printLink(LinkedNode node) {
        while (node != null) {
            System.out.print(node.value + ",");
            node = node.next;
        }
        System.out.println();
    }

    public static LinkedNode init(int[] array1) {
        LinkedNode node = new LinkedNode(array1[0]);
        LinkedNode current = node;
        for (int i = 1; i < array1.length; i++) {
            current.next = new LinkedNode(array1[i]);
            current = current.next;
        }
        return node;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 2, 4};
        int[] array2 = {1, 3, 3, 5};
        LinkedNode leftNode = init(array1);
        LinkedNode rightNode = init(array2);
        printLink(leftNode);
        printLink(rightNode);

        // 合并已排序后两个LinkedNode
        LinkedNode merge = merge(leftNode, rightNode);
        printLink(merge);
    }

}
