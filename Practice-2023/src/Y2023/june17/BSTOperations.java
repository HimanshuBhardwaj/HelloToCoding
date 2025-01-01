package Y2023.june17;

import java.util.LinkedList;
import java.util.Queue;

public class BSTOperations {
    public static void main(String[] args) {
        Node root = null;
        Tree tree = new Tree();
        root = tree.insert(root, 1);
        root = tree.insert(root, 1);
        root = tree.insert(root, -2);
        root = tree.insert(root, -772);
        root = tree.insert(root, 3);
        root = tree.insert(root, -1);
        root = tree.insert(root, -1);
        root = tree.insert(root, -1);

//        tree.inorder(root);
//        System.out.println();
//        System.out.println("Contains "+tree.contains(root, 1)+"\tSize: "+tree.size);
//
//        root = tree.delete(root, 1);
//        root = tree.delete(root, 1);
//        root = tree.delete(root, -1);
//        root = tree.delete(root, -2);
//        root = tree.delete(root, 3);
//        root = tree.delete(root, -772);
//        tree.inorder(root);
//        System.out.println("Size: "+tree.size);

        tree.BFS(root);
        System.out.println();
        tree.DFS(root);
    }
}



class Tree implements TreeI {
    int size;

    public Tree() {
        this.size = 0;
    }

    @Override
    public Node insert(Node root, int value) {
        if (root == null) {
            root= new Node(value);
            size++;
            return root;
        }

        if (root.value > value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    @Override
    public void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.value + ", ");
        inorder(root.right);
    }

    @Override
    public boolean contains(Node root, int value) {
        if (root==null) {
            return false;
        }

        if (root.value == value) {
            return true;
        }
        return contains(root.left, value) || contains(root.right, value);
    }

    @Override
    public Node delete(Node root, int value) {
        if (root == null) {
            return root;
        }
        if (root.value == value) {
            if (root.left == null && root.right == null) {
                size--;
                return null;
            } else if (root.left == null) {

                size--;
                return root.right;
            } else if (root.right == null) {
                size--;
                return root.left;
            } else {
                Node successor = getSuccessor(root, value);
                int temp = root.value;
                root.value = successor.value;
                successor.value = temp;

                root.right = delete(root.right, value);
            }

        } else if (root.value > value) {
            root.left = delete(root.left, value);
        } else {
            root.right = delete(root.right, value);
        }

        return root;
    }

    @Override
    public void BFS(Node root) {
        if (root == null) {
            return;
        }

        int level=0;
        Node levelChange = new Node(Integer.MIN_VALUE);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(levelChange);

        System.out.print(level+": ");
        while (!queue.isEmpty()) {
            Node first = queue.poll();

            if (first.value == Integer.MIN_VALUE) {
                level++;
                System.out.println();
                if (!queue.isEmpty()) {
                    System.out.print(level+": ");
                    queue.add(first);
                }
            } else {
                System.out.print(first.value+", ");
                if (first.left != null) {
                    queue.add(first.left);
                }
                if (first.right != null) {
                    queue.add(first.right);
                }
            }
        }
    }

    @Override
    public void DFS(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value+", ");
        DFS(root.left);
        DFS(root.right);
    }

    private Node getSuccessor(Node root, int value) {
        if (root == null) {
            return null;
        }
        root = root.right;

        while (root!= null && root.left != null) {
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
        this.left = null;
        this.right = null;
    }

    public Node() {
    }
}


interface TreeI {
    Node insert(Node root, int value);
    void inorder(Node root);
    boolean contains(Node root, int value);

    Node delete(Node root, int value);

    void BFS(Node root);

    void DFS(Node root);
}