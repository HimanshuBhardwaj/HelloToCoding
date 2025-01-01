package Y2023.july23;

import java.util.ArrayList;
import java.util.Collections;

public class GenericTree {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Himanshu");
        list.add("Bhardwaj");
        list.add("lives");
        list.add("in");
        list.add("a");
        list.add("society");
        list.add("hydrabad");


        Tree<String> tree = new Tree<>();
        for (String str:list) {
            tree.insert(str);
        }
        tree.inorder(tree.root);

        System.out.println();
        Tree<Long> tree1 = new Tree<>();
        tree1.insert(100l);
        tree1.insert(101l);
        tree1.insert(102l);
        tree1.insert(103l);
        tree1.insert(10l);
        tree1.insert(11l);
        tree1.inorder(tree1.root);

        Collections.sort(list);
        System.out.println(list);
    }
}


class Tree<T extends Comparable<T>> implements TreeOperations<T> {
    Node<T> root;
    int numNodes;

    @Override
    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        }else {
            insertHelper(root, value);
        }
        numNodes++;
    }

    private Node<T> insertHelper(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }

        if (root.value.compareTo(value) > 0) {
            root.left = insertHelper(root.left, value);
        } else {
            root.right = insertHelper(root.right, value);
        }

        return root;
    }

    @Override
    public int size() {
        return numNodes;
    }

    @Override
    public void inorder(Node<T> root) {
        if (root==null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.value.toString()+", ");
        inorder(root.right);
    }
}

class Node<T extends Comparable<T>> {
    T value;
    Node<T> left;
    Node<T> right;

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

interface TreeOperations<T extends Comparable<T>> {
    void insert(T value);
    int size();
    void inorder(Node<T> root);
}