package com.himanshu.coding.june10;

import java.util.LinkedList;
import java.util.Queue;

public class TreeOperations {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = tree.insert(null,10);
        root = tree.insert(root,8);
        root = tree.insert(root,7);
        root = tree.insert(root,9);
        root = tree.insert(root,12);
        root = tree.insert(root,11);
        root = tree.insert(root,13);
        System.out.println("Before removal");
        tree.inorder(root);
        tree.BFS(root);
       System.out.println();
        System.out.println("Height"+tree.height(root));
        System.out.println("Size"+tree.size(root));
        System.out.println("BFS:");

        System.out.println();
        System.out.println("after removal");
        tree.delete(root,10);
        tree.inorder(root);
        System.out.println();
        tree.BFS(root);

    }
}

class Tree {
    Node root;

    public Node insert(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            this.root=root;
            return root;
        }

        if (root.value>value) {
            root.left = insert(root.left, value);
        }else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public int height(Node root) {
        if (root==null) {
            return 0;
        }
        return  1+Math.max(height(root.left), height(root.right));
    }

    public int size(Node root) {
        if (root==null) {
            return 0;
        }
        return 1+size(root.left)+size(root.right);
    }

    public void inorder(Node node) {
        if (node==null ){
            return;
        }
        inorder(node.left);
        System.out.print(node.value+", ");
        inorder(node.right);
    }

    public void preOrder(Node node) {
        if (node==null) {
            return;
        }
        System.out.println(node.value);
        preOrder(node.right);
        preOrder(node.left);
    }

    public void postOrder(Node node) {
        if (node==null) {
            return;
        }
        postOrder(node.right);
        postOrder(node.left);
        System.out.println(node.value);
    }

    public void DFSRecursive(Node root) {
        if (root==null) {
            return;
        }
        DFSRecursive(root.left);
        System.out.print(root.value+", ");
        DFSRecursive(root.right);
    }

    public void BFS(Node root) {
        if (root==null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level=1;
        Node marker = new Node(Integer.MIN_VALUE);
        queue.add(marker);
        System.out.print("1: ");

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.value==Integer.MIN_VALUE) {
                level++;
                System.out.println();
                System.out.print(level+": ");
                if (!queue.isEmpty()) {
                    queue.add(marker);
                }
            }else {
                System.out.print(node.value+", ");
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public  Node delete(Node root, int value) {
        if (root==null) {
            return null;
        }
        if (root.value==value) {
            if (root.left==null && root.right==null) {
                return null;
            } else if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            } else {
                Node successor = getSuccessor(root);
                int temp = root.value;
                root.value=successor.value;
                successor.value=temp;
                root.right = delete(root.right,value);
                return root;
            }
        } else if(root.value<value) {
            root.right = delete(root.right,value);
        } else {
            root.left = delete(root.left,value);
        }
        return root;
    }

    private Node getSuccessor(Node root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        left=null;
        right=null;
    }
}
