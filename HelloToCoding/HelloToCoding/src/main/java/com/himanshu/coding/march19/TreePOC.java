package com.himanshu.coding.march19;

public class TreePOC {
    public static void main(String[] args) {
        Node root = new Node(10);
        root  = root.insert(13,root);
        root  = root.insert(14,root);
        root  = root.insert(12,root);
        root  = root.insert(9,root);
        root.inorder(root);
    }
}




class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int v) {
        this.value = v;
        this.left=null;
        this.right = null;
    }

    public Node insert(int value, Node root) {
        if(root == null) {
            return new Node(value);
        }

        if (root.value > value) {
            root.left = insert(value,root.left);
        } else {
            root.right = insert(value, root.right);
        }
        return root;
    }

    public void  inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value+", ");
        inorder(root.right);
    }


}
