package com.aq.bo;

/**
 * @ClassName TreeNode
 * @Description :
 * @Date 2020/9/24 14:53
 * @Version 1.0
 **/
public class TreeNode {

    private Integer data;

    private TreeNode parent;

    private TreeNode left;

    private TreeNode right;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
