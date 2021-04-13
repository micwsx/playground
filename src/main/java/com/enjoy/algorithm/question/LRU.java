package com.enjoy.algorithm.question;

import com.enjoy.concurrent.ch8a.PendingJobPool;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Author wu
 * @Date 4/13/2021 12:22 PM
 * @Version 1.0
 * Latest recent use.
 * 使用链表结构
 */
public class LRU {

    private Node head;
    private Node tail;
    private int capacity;
    private HashMap<String, Node> hashMap = new HashMap<>();

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public void put(String key, String value) {
        Node currentNode = hashMap.get(key);
        if (currentNode != null) {
            // 已存在
            currentNode.value = value;
            refreshNode(currentNode);
        } else {
            // 不存在
            if (hashMap.size() >= capacity) {
                // 满了，移除头结点
                String removedKey = removeNode(head);
                hashMap.remove(removedKey);
            }
            // 添加结点
            currentNode = new Node(key, value);
            addNode(currentNode);
            hashMap.put(key, currentNode);
        }
    }

    // 访问后再更新一下结点
    public String get(String key) {
        Node node = hashMap.get(key);
        if (node != null) {
            refreshNode(node);
            return node.value;
        }
        return null;
    }

    private void refreshNode(Node node) {
        if (node == tail) {
            return;
        }
        // 把最结点关系左右断开，移到最后面。
        removeNode(node);
        // 当空间满了就把最前面head结点移除。
        addNode(node);
    }

    /**
     * 删除节点
     *
     * @param node
     * @return
     */
    private String removeNode(Node node) {
        if (node == head && node == tail) {
            // 移除唯一的节点
            head = null;
            tail = null;
        } else if (node == tail) {
            // 如果是尾节点
            tail = node.pre;
            tail.next = null;
        } else if (node == head) {
            // 头节点
            head = node.next;
        } else {
            // 中间节点
            Node currentPre = node.pre;
            Node currentNext = node.next;
            currentNext.pre = currentPre;
            currentPre.next = currentNext;
        }
        return node.key;
    }

    private void addNode(Node node) {
        if (tail != null) {
            // 尾结点不为空
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    // 私有静态内部类
    private static class Node {
        private String value;
        private String key;
        private Node next;
        private Node pre;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private void printLinkedNode() {
        System.out.println(String.format("%s:%s", head.key, head.value));
        Node current = head;
        while (current.next != null) {
            Node node = current.next;
            System.out.println(String.format("%s:%s", node.key, node.value));
            current = current.next;
        }
    }

    private void printHashMap() {
        Iterator<String> iterator = this.hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Node node = hashMap.get(key);
            System.out.println(String.format("%s:%s", node.key, node.value));
        }
    }

    public static void main(String[] args) {

        LRU lru = new LRU(3);
        lru.put("001", "A");
        lru.put("002", "B");
        lru.put("003", "C");
        lru.printLinkedNode();
        System.out.println("--------------");
        lru.put("004", "D");
        lru.put("005", "E");
        lru.printLinkedNode();
        String s = lru.get("003");
        System.out.println("get key:" + s);
        lru.printLinkedNode();
        System.out.println("---print hashmap");
        lru.printHashMap();

    }
}
