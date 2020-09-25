package com.aq.bo;

import java.util.Stack;

/**
 * @ClassName SearchTree
 * @Description :  排序/平衡二叉树的定义
 * @Date 2020/9/24 15:03
 * @Version 1.0
 **/
public class SearchTree {

    private TreeNode root;

    public long size;

    public Boolean addTreeNode(Integer data){
        if(root==null) {
            root = new TreeNode(data);
            return true;
        }

        TreeNode treeNode = new TreeNode(data);
        TreeNode currentNode = root;
        TreeNode parentNode;

        while (true) {
            parentNode = currentNode; //保存父节点

            if(currentNode.getData() > data){
                currentNode = currentNode.getLeft();

                if(null == currentNode){
                    parentNode.setLeft(treeNode);
                    treeNode.setParent(parentNode);
                    size++;
                    return true;
                }
            }else if(currentNode.getData() < data){
                currentNode = currentNode.getRight();

                if(null == currentNode){
                    parentNode.setRight(treeNode);
                    treeNode.setParent(parentNode);
                    size++;
                    return true;
                }
            }else {
                return false;
            }
        }

    }

    public TreeNode findTreeMode(Integer data) {
        if(null==root){
            return null;
        }

        TreeNode current = root;
        while(current!=null){
            if(current.getData()>data){
                current = current.getLeft();
            }else if(current.getData()<data){
                current = current.getRight();
            }else {
                return current;
            }
        }
        return null;
    }

    /**
     * @Description //前序遍历二叉数组 DLR  根-左-右。
     * @Date 14:24 2020/9/25
     * @Param [treeNode]
     * @return void
     **/
    public static void preOrderTreeNode(TreeNode treeNode,Integer type){

        //递归
        if(null != treeNode && type==1){
            System.out.print(treeNode.getData()+" ");
            if(null!=treeNode.getLeft()){
                preOrderTreeNode(treeNode.getLeft(),1);
            }
            if(null != treeNode.getRight()){
                preOrderTreeNode(treeNode.getRight(),1);
            }
        }

        if(null != treeNode && type!=1){
            //使用栈特性
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(treeNode);
            while (!stack.isEmpty()){
                TreeNode pop = stack.pop();
                System.out.print(pop.getData()+"  ");
                if(null != pop.getRight()){
                    stack.push(pop.getRight());
                }
                if(null!=pop.getLeft()){
                    stack.push(pop.getLeft());
                }
            }
        }
    }

    /**
     * @Description //中序遍历二叉数组 LDR  左-根-右。
     * @Date 14:24 2020/9/25
     * @Param [treeNode]
     * @return void
     **/
    public static void medOrderTreeNode(TreeNode treeNode,Integer type){
        //递归
        if(null != treeNode && type==1){
            if(null!=treeNode.getLeft()){
                medOrderTreeNode(treeNode.getLeft(),1);
            }
            System.out.print(treeNode.getData() + " ");
            if(null != treeNode.getRight()){
                medOrderTreeNode(treeNode.getRight(),1);
            }
        }

        if(null != treeNode && type!=1){
            //使用栈特性
            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode current = treeNode;
            while (current!=null || !stack.isEmpty()){
                while (current!=null){
                    stack.push(current);
                    current = current.getLeft();
                }
                if(!stack.isEmpty()){
                    current = stack.pop();
                    System.out.print(current.getData()+"  ");
                    current = current.getRight();
                }
            }
        }
    }

    /**
     * @Description //后序遍历二叉数组 LRD  左-右-根。
     * @Date 14:24 2020/9/25
     * @Param [treeNode]
     * @return void
     **/
    public static void postOrderTreeNode(TreeNode treeNode,Integer type){
        //递归
        if(null != treeNode && type==1){
            if(null!=treeNode.getLeft()){
                postOrderTreeNode(treeNode.getLeft(),1);
            }
            if(null != treeNode.getRight()){
                postOrderTreeNode(treeNode.getRight(),1);
            }
            System.out.print(treeNode.getData() + " ");
        }

        if(null!=treeNode && type!=1){
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = treeNode;
            TreeNode rightNode = null;

            while (current!=null || !stack.isEmpty()){
                while (current!=null){
                    stack.push(current);
                    current = current.getLeft();
                }
                current = stack.pop();
                while (current!=null && (current.getRight()==null || current.getRight() == rightNode)){
                    System.out.print(current.getData()+"  ");
                    rightNode = current;
                    if(stack.isEmpty()){
                        System.out.println();
                        return;
                    }
                    current = stack.pop();
                }
                stack.push(current);
                current = current.getRight();
            }
        }
    }

    public static void main(String[] args) {
        SearchTree tree = new SearchTree();
        tree.addTreeNode(50);
        tree.addTreeNode(80);
        tree.addTreeNode(20);
        tree.addTreeNode(60);
        tree.addTreeNode(10);
        tree.addTreeNode(30);
        tree.addTreeNode(70);

        tree.addTreeNode(90);
        tree.addTreeNode(100);
        tree.addTreeNode(40);
        System.out.println("=============================="+"采用递归的前序遍历开始"+"==============================");
        SearchTree.preOrderTreeNode(tree.root,1);
        System.out.println();
        System.out.println("=============================="+"采用循环的前序遍历开始"+"==============================");
        SearchTree.preOrderTreeNode(tree.root,2);
        System.out.println();
        System.out.println("=============================="+"采用递归的后序遍历开始"+"==============================");
        SearchTree.postOrderTreeNode(tree.root,1);
        System.out.println();
        System.out.println("=============================="+"采用循环的后序遍历开始"+"==============================");
        SearchTree.postOrderTreeNode(tree.root,2);
        System.out.println();
        System.out.println("=============================="+"采用递归的中序遍历开始"+"==============================");
        SearchTree.medOrderTreeNode(tree.root,1);
        System.out.println();
        System.out.println("=============================="+"采用循环的中序遍历开始"+"==============================");
        SearchTree.medOrderTreeNode(tree.root,2);
    }
}
