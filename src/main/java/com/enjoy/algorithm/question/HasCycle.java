package com.enjoy.algorithm.question;

/**
 * @Author wu
 * @Date 4/13/2021 2:28 PM
 * @Version 1.0
 * 判断单向链表是否有环
 */
public class HasCycle {


//        1->2->3->4->5->6->7
//                 ^       |
//                 |       |
//                 <-------

    private static boolean hasCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    private static int cycleLength(Node head) {
        Node p1 = head;
        Node p2 = head;
        int times = 0;
        int length = 0;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                if (++times == 2) {
                    // 第二次相遇
                    System.out.println("环长度： " + length);
                    break;
                }
            }
            if (times == 1) {
                length++;
            }
        }
        return length;
    }

    // 入环点
    private static Node entryCyclePoint(Node head) {
        // 相遇结点
        Node p2 = meetNode(head);
        // p1再从起点开始
        Node p1 = head;

        // 直到两个结点相遇及得到入环点
        while (p1 != p2) {
            // 都向前移一步
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    private static Node meetNode(Node head) {
        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                // 相遇
                return p2;
            }
        }
        return null;
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        Node linkedNode = createLinkedNode();
//        boolean b = hasCycle(linkedNode);
//        System.out.println(b);

//        int length = cycleLength(linkedNode);
//        System.out.println(length);
        Node entryNode = entryCyclePoint(linkedNode);
        System.out.println(entryNode.value);
    }

    private static Node createLinkedNode() {
//        1->2->3->4->5->6->7
//                 ^       |
//                 |       |
//                 <-------
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node4;

        return node1;
    }
}
