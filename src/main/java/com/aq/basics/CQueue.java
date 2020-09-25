package com.aq.basics;

import java.util.Stack;

/**
 * @ClassName CQueue
 * @Description : 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * @Date 2020/6/30 18:39
 **/
public class CQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;
    Integer length;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        length = 0;
    }

    public void appendTail(int value) {
        stack1.push(value);
        length++;
    }

    public int deleteHead() {
        if(!stack2.isEmpty()) {
            Integer pop = stack2.pop();
            System.out.println(pop);
            return pop;
        }

        if(length>0) {
            while (!stack1.isEmpty()) {
                length--;
                stack2.push(stack1.pop());
            }
        }

        if(!stack2.isEmpty()) {
            Integer pop1 = stack2.pop();
            System.out.println(pop1);
            return pop1;
        }else {
            System.out.println(-1);
            return -1;
        }


        /*if(stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()) {
            System.out.println(-1);
            return -1;
        }else {
            Integer pop = stack2.pop();
            System.out.println(pop);
            return pop;
        }*/
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(3);
        cQueue.deleteHead();
        cQueue.appendTail(5);
        cQueue.appendTail(7);
        cQueue.deleteHead();
        cQueue.appendTail(9);
        cQueue.deleteHead();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
