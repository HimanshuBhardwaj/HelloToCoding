package Y2023.aug15;

import java.util.Stack;

public class TreeIteratorImpl {
    public static void main(String[] args) {
        Node<String> root = new Node<>("Himanshu");
        root = root.insert(root, "Bhardwaj");
        root = root.insert(root, "is");
        root = root.insert(root, "a");
        root = root.insert(root, "great");
        root = root.insert(root, "man");

        root.inorder(root);
        System.out.println();


        Node<Long> root2 = new Node<>(10l);
        root2 = root2.insert(root2, 11l);
        root2 = root2.insert(root2, 12l);
        root2 = root2.insert(root2, 8l);
        root2 = root2.insert(root2, 9l);
        root2 = root2.insert(root2, 7l);
        root2 = root2.insert(root2, 4l);
        root2 = root2.insert(root2, 5l);
        root2 = root2.insert(root2, 6l);

        root2.inorder(root2);

        System.out.println("\n\nIterator:\n");

        Iterator<Long> iterator = new TreeIterator(root);

        while (iterator.hasNext()) {
            System.out.println(iterator.getNext());
        }

    }
}

class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
    Stack<Node<T>> stack = new Stack<>();

    public TreeIterator(Node<T> root) {
        while (root != null) {
            stack.add(root);
            root = root.left;
        }
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T getNext() {
        Node<T> value = stack.pop();
        Node<T> succ = value.right;

        while (succ != null) {
            stack.push(succ);
            succ = succ.left;
        }

        return value.value;
    }
}

interface Iterator<T> {
    boolean hasNext();

    T getNext();
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

    public Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }

        if (root.value.compareTo(value) < 0) {
            root.right = insert(root.right, value);
        } else {
            root.left = insert(root.left, value);
        }

        return root;
    }

    public void inorder(Node<T> root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.value+", ");
            inorder(root.right);
        }
    }
}