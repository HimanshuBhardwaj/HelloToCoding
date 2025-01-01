package Y2023.aug3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TreeIterator {
    public static void main(String[] args) {
        Node<String> root = new Node<>("Root");
        root = root.insert(root, "Himanshu");
        root = root.insert(root, "Himanshu2");
        root = root.insert(root, "Himanshu1");
        root = root.insert(root, "Himanshu5");
        root = root.insert(root, "Himanshu4");
        root = root.insert(root, "Himanshu2");
        root = root.insert(root, "Roo5");
        root = root.insert(root, "Roo6");
        root = root.insert(root, "Roo4");

        root.inorder(root);

        ArrayList<String> list = new ArrayList<>();
        list.add("Root");
        list.add("Himanshu");
        list.add("Himanshu2");
        list.add("Himanshu1");
        list.add("Himanshu5");
        list.add("Himanshu4");
        list.add("Himanshu2");
        list.add("Roo5");
        list.add("Roo6");
        list.add("Roo4");
        Collections.sort(list);
        System.out.println();
        System.out.println(list);
        System.out.println();

        IteratorI<String> iterator = new TreeIteratorImpl<>(root);

        while (iterator.hasNext()) {
            System.out.print(iterator.getNext()+", ");
        }
        System.out.println();

    }
}



class Node <T extends Comparable<T>> implements TreeI<T> {
    T value;
    Node<T> left;
    Node<T> right;

    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    @Override
    public Node insert(Node root, T value) {
        if (root == null) {
            return new Node(value);
        }

        if (root.value.compareTo(value) > 0) {
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
        System.out.print(root.value+", ");
        inorder(root.right);
    }
}

class TreeIteratorImpl<T extends Comparable<T>>  implements IteratorI<T>{
    Stack<Node<T>> stack;

    public TreeIteratorImpl(Node<T> root) {
        stack = new Stack<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T getNext() {
        Node<T> top = stack.pop();
        Node<T> nodeToReturn = top;
        top = top.right;

        while (top != null) {
            stack.push(top);
            top = top.left;
        }

        return nodeToReturn.value;
    }
}

interface IteratorI<T> {
    boolean hasNext();
    T getNext();
}

interface TreeI<T> {
    Node insert(Node root, T value);
    void inorder(Node root);
}