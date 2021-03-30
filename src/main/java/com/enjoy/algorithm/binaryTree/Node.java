package com.enjoy.algorithm.binaryTree;

/**
 * @Author wu
 * @Date 3/30/2021 2:52 PM
 * @Version 1.0
 */
public class Node {
    private String data;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public static Node createTree(){
        Node D = new Node("D", null, null);
        Node C = new Node("C", D, null);
        Node B = new Node("B", null, C);

        Node H = new Node("H", null, null);
        Node K = new Node("K", null, null);
        Node G = new Node("G", H, K);
        Node F = new Node("F", G, null);
        Node E = new Node("E", null, F);

        Node A = new Node("A", B, E);
        return A;
    }
}
