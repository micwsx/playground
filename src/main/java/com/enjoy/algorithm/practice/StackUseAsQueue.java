package com.enjoy.algorithm.practice;

import java.util.Stack;

/**
 * @Author wu
 * @Date 3/27/2021 8:51 PM
 * @Version 1.0
 * 通过Stack实现队列功能
 */
public class StackUseAsQueue {


    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void enqueue(int element) {
        // 入栈
        stack1.push(element);
    }

    public Integer dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StackUseAsQueue stackUseAsQueue=new StackUseAsQueue();
        stackUseAsQueue.enqueue(1);
        stackUseAsQueue.enqueue(2);
        stackUseAsQueue.enqueue(3);
        stackUseAsQueue.enqueue(4);

        System.out.println(stackUseAsQueue.dequeue());
        System.out.println(stackUseAsQueue.dequeue());
        System.out.println(stackUseAsQueue.dequeue());
        stackUseAsQueue.enqueue(5);
        stackUseAsQueue.enqueue(6);
        stackUseAsQueue.enqueue(7);
        stackUseAsQueue.enqueue(8);
        System.out.println(stackUseAsQueue.dequeue());
        System.out.println(stackUseAsQueue.dequeue());
        System.out.println(stackUseAsQueue.dequeue());
        System.out.println(stackUseAsQueue.dequeue());
        System.out.println(stackUseAsQueue.dequeue());
    }

}
