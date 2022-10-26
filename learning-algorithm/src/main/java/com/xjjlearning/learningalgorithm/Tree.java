package com.xjjlearning.learningalgorithm;

import java.util.*;

public class Tree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void frontTrival(TreeNode root) {
//        Stack<TreeNode> stack = new Stack<>();
//        List<Integer> list = new ArrayList<>();
//        stack.push(root);
//        while(!stack.isEmpty()){
//            TreeNode node = stack.pop();
//            list.add(node.val);//右插
//            if(node.right!= null) stack.push(node.right);//先压右再压左
//            if(node.left!= null) stack.push(node.left);
//        }
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    //中序遍历意味着root放在中间访问, 左中右
    public static void midTrival(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.println(node.val);
                node = node.right;
            }
        }
    }
    private static void lastTravel(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if(node.left!= null) stack.push(node.left);//先压左再压右
            if(node.right!= null) stack.push(node.right);
        }
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
    private static TreeNode buildTree(int n){
        TreeNode root = new TreeNode(1);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);
        for (int i = 2;;) {
            TreeNode node = stack.poll();
            node.left = new TreeNode(i++);
            if (i > n)break;
            node.right = new TreeNode(i++);
            if (i > n)break;
            stack.offer(node.left);
            stack.offer(node.right);
        }
        return root;
    }

    public static void main(String[] args) {
        /**
         *      1
         *    2   3
         *  4  5 6  7
         */
        //前 1 2 4 5 3 6 7
        //中 4 2 5 1 6 3 7
        //后 4 5 2 6 7 3 1
        TreeNode root = buildTree(7);
        frontTrival(root);
    }
}
