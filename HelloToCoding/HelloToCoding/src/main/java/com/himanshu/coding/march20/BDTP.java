package com.himanshu.coding.march20;

public class BDTP {
    public static void main(String[] args) {
        Node node = new Node(7);
        node =node.insert(12,node);
        node =node.insert(11,node);
        node =node.insert(13,node);
        node =node.insert(8,node);
        node =node.insert(9,node);
        node =node.insert(70,node);

        node.inorder(node);
        System.out.println();
        node = node.delete(node,70);
        node.inorder(node);


    }
}

class  Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public Node  insert(int value, Node node) {
        if (node == null) {
            return new Node(value);
        } else {
            if (node.value > value) {
                node.left = insert(value,node.left);
            } else {
                node.right = insert(value,node.right);
            }
        }
        return node;
    }

    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) {
            if (root.right==null && root.left==null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node inorderPredessasor = predecassor(root);
                System.out.println(root.value+", p:"+inorderPredessasor.value);
                int temp = inorderPredessasor.value;
                inorderPredessasor.value = root.value;
                root.value = temp;
                return delete(root.left,value);
            }
        } else if (root.value > value) {
            root = delete(root.left,value);
        } else {
            root = delete(root.right,value);
        }
        return root;
    }

    private Node predecassor(Node root) {
        root = root.left;

        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    public void inorder(Node node) {
        if (node!=null) {
            inorder(node.left);
            System.out.print(node.value+", ");
            inorder(node.right);
        }
    }
}