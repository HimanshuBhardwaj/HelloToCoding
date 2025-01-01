package com.himanshu.coding.sept2;

import java.util.LinkedList;
import java.util.Queue;

public class TreeOperations {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.insert(root,8);
        root.insert(root,12);
        root.insert(root,11);
        root.insert(root,9);

        root.inorder(root);
        System.out.println();
        root = root.delete(root,12);
        root.inorder(root);
        System.out.println();


    }
}

//12:21
//insert,height,inorder,levelorder,postorder,BFS,numnodes
class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        } else {
            if (root.value > value) {
                root.left = insert(root.left,value);
            } else {
                root.right = insert(root.right,value);
            }
            return root;
        }
    }

    Node delete(Node root, int value) {
        if(root==null){
            return null;
        }

        if(root.value == value) {
            if (root.left==null && root.right == null) {
                return null;
            } else if(root.left == null) {
                return  root.right;
            } else if(root.right == null) {
                return root.left;
            } else {
                Node successor = inorderSuccessor(root);
                root.value = successor.value;
                successor.value = value;
                root.right=delete(root.right,value);
                return root;
            }
        } else if (root.value > value) {
            root.left= delete(root.left,value);
        } else {
            root.right = delete(root.right,value);
        }
        return root;
    }

    private Node inorderSuccessor(Node root) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            return  1+ Math.max(height(node.left),height(node.right));
        }
    }

    void inorder(Node root){
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value+", ");
        inorder(root.right);
    }

    void preorder(Node root) {
        if (root == null) {
            return;
        }
        preorder(root.left);
        preorder(root.right);
        System.out.print(root.value+", ");
    }


    void  postorder(Node root) {
        if(root == null) {
            return;
        }
        System.out.println( root.value+", ");
        postorder(root.left);
        postorder(root.right);
    }

    //Done
    void BFS(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node head = queue.poll();
            System.out.print(head.value+", ");
            if (head.left != null) {
                queue.add(head.left);
            }

            if (head.right != null) {
                queue.add(head.right);
            }
        }
    }

    //done
    int numNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1+numNodes(root.left)+numNodes(root.right);

    }

}
