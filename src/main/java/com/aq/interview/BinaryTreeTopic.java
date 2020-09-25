package com.aq.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * @ClassName BinaryTreeTopic
 * @Description : 实现要求：
 *                  1、根据已有的代码片段，实现一个二叉排序树；
 *                  2、用中序遍历输出排序结果，结果形如：0，1，2 ，3 ，4， 5， 6， 7， 8， 9，
 *                  3、注意编写代码注释
 * @Author YaoAqiang
 * @Date 2020/6/17 9:45
 * @Version 1.0
 **/
public class BinaryTreeTopic {

    public static void main(String[] args) {

        final int[] values = { 1, 3, 4, 5, 9, 11, 2, 8, 6, 7, 9, 0 };
        // TODO:
        Node binaryTree = null;
        for (int a: values) {
            binaryTree = createBinaryTree(binaryTree, a);
        }
        System.out.println(binaryTree.toString());

//        binaryTree.deleteByMerging(new Node(3));

        System.out.println();
        System.out.println(binaryTree.toString());

        Map<Integer, String> integerStringHashMap = new HashMap<>();
        String remove = integerStringHashMap.remove(4);

        /*inOrderTransval(binaryTree);

        System.out.println();

        medOrderMethod(binaryTree);

        System.out.println();

        preOrderMethod(binaryTree);

        System.out.println();

        postOrderMethod(binaryTree);

        System.out.println();

        Node treeNode = findTreeNode(binaryTree, 8);
        System.out.println(treeNode.toString());*/
    }

    public static Node findTreeNode(Node root, Integer data){
        if(null == root){
            return null;
        }
        Node current = root;
        while(current != null){
            if(current.getValue() > data){
                current = current.getLeft();
            }else if(current.getValue() < data){
                current = current.getRight();
            }else {
                return current;
            }
        }
        return null;
    }

    public static Node createBinaryTree(Node node, int value) {
        // TODO:
        if(Objects.isNull(node)) {
            node = new Node(value);
            return node;
        }
        Node treeNode = new Node(value);// 即将被插入的数据
        Node currentNode = node;
        Node parentNode;

        while (true) {
            parentNode = currentNode;// 保存父节点
            if (currentNode.getValue() > value) {
                currentNode = currentNode.getLeft();
                if (null == currentNode) {
                    parentNode.setLeft(treeNode);
                    return node;
                }
            } else if (currentNode.getValue() < value) {
                currentNode = currentNode.getRight();
                if (null == currentNode) {
                    parentNode.setRight(treeNode);
                    return node;
                }
            } else {
                System.out.println("输入数据与节点的数据相同"+value);
                return node;
            }
        }
    }

    public static void inOrderTransval(Node node) {
        // TODO:
        if (null != node) {
            if (null != node.getLeft()) {
                inOrderTransval(node.getLeft());
            }
            System.out.print(node.getValue() + " ");
            if (null != node.getRight()) {
                inOrderTransval(node.getRight());
            }
        }
    }

    /**
     * 循环实现中序遍历
     */
    public static void medOrderMethod(Node node){
        Stack<Node> stack = new Stack<Node>();
        Node current = node;
        while (current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            if (!stack.isEmpty()) {
                current = stack.pop();
                System.out.print(current.getValue()+" ");
                current = current.getRight();
            }
        }
    }

    public static void preOrderMethod(Node node) {
        if (null != node) {
            System.out.print(node.getValue() + " ");
            if (null != node.getLeft()) {
                preOrderMethod(node.getLeft());
            }
            if (null != node.getRight()) {
                preOrderMethod(node.getRight());

            }
        }
    }

    public static void postOrderMethod(Node node){
        if (null != node) {
            if (null != node.getLeft()) {
                postOrderMethod(node.getLeft());
            }
            if (null != node.getRight()) {
                postOrderMethod(node.getRight());
            }
            System.out.print(node.getValue() + " ");
        }

    }
}

class Node {
    // 节点值
    private int value;
    // 左节点
    private Node left;
    // 右节点
    private Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    /*
     * 合并删除
     */
    public void deleteByMerging(Node node,Node delNode){
        Node temp = delNode;
        if(node!=null){
            //若被删除节点没有右子树,用其左子树的根节点来代替被删除节点
            if(delNode.right==null){
                node=node.left;
            }else if(node.getLeft()==null){
                //若被删节点没有左子树，用其有字数的最左端的节点代替被删除的节点
                node=node.getRight();
            }else{
                //如果被删节点左右子树均存在
                temp=node.getLeft();
                while(temp.getRight()!=null){
                    temp=temp.getRight();   //一直查找到左子树的右节点
                }

                //将查找到的节点的右指针赋值为被删除节点的右子树的根
                temp.setRight(node.getRight());
                temp.left=node.left;
                node = temp;
//                temp=node;
//                node=node.getLeft();
            }
            temp=null;
        }
    }

    /*
     * 复制删除
     */
    public void deleteByCoping(Node node){
        Node pre=null;
        Node temp=node;
        //如果被删节点没有右子树，用其左子树的根节点来代替被删除节点
        if(node.getRight()==null){
            node=node.getLeft();
        }else if(node.getLeft()==null){
            node=node.getRight();
        }else{
            //如果被删节点的左右子树都存在
            temp=node.getLeft();
            pre=node;
            while(temp.getRight()!=null){
                pre=temp;
                temp=temp.getRight();   //遍历查找到左子树的最右端的节点
            }
            node.setValue(temp.getValue());      //进行赋值操作
            if(pre==node){
                pre.setLeft(node.getLeft());
            }else{
                pre.setRight(node.getRight());
            }
        }
        temp=null;
    }
}