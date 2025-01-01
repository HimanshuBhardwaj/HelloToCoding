package oct24;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Node root = tree.insert(null,9);
        root = tree.insert(root,11);
        root = tree.insert(root,10);
        root = tree.insert(root,8);
        root = tree.insert(root,19);
        root = tree.insert(root,7);

        tree.inorder(root);

        System.out.println();
        System.out.println("Height: "+tree.height(root));
        System.out.println("Size: "+tree.size());
        tree.inorder(root);
        System.out.println();
        System.out.println("DFS");
        tree.DFS(root);

        System.out.println("BFS");
        tree.BFS(root);



        /*
        root = tree.delete(root, 9);
        tree.inorder(root);
        System.out.println();
        System.out.println("..");
        root = tree.delete(root, 7);
        root = tree.delete(root, 8);
        root = tree.delete(root, 11);
        root = tree.delete(root, 10);
        root = tree.delete(root, 19);
        tree.inorder(root);
        System.out.println();

         */
    }
}

class Tree {
    int size;

    Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            size++;
        } else if (root.value > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    void DFS(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value+", ");
        DFS(node.left);
        DFS(node.right);
    }

    void BFS(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            System.out.print(top.value+", ");
            if (top.left != null) {
                queue.add(top.left);
            }

            if (top.right != null) {
                queue.add(top.right);
            }
        }
    }

    int size() {
        return this.size;
    }

    int height(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }

    void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value + ", ");
        inorder(root.right);
    }



    void DFSIterative(Node root) {
        System.out.println("Iterative DFS");
        if (root == null) {
            return;
        }
    }

    Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value > value) {
            root.left = delete(root.left, value);
        } else if (root.value < value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if(root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node successor = getSuccessor(root, value);
                root.value = successor.value;
                root.right = delete(root.right, successor.value);
            }
        }
        return root;
    }

    private Node getSuccessor(Node root, int value) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    Node find(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (node.value == value) {
            return node;
        } else if (node.value > value) {
            return find(node.left, value);
        } else {
            return find(node.right, value);
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}