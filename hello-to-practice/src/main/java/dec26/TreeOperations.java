package dec26;

import java.util.Stack;

public class TreeOperations {
    public static void main(String[] args) {
        TreeOperationsI treeOperations = new TreeOps();
        Node root = treeOperations.insert(null,10);
        //root = treeOperations.insert(root,12);
        //root = treeOperations.insert(root,11);
        //root = treeOperations.insert(root,13);
        //root = treeOperations.insert(root,10);
        //root = treeOperations.insert(root,15);

        treeOperations.inorder(root);
        System.out.println();
        treeOperations.iterativeDFS(root);
    }
}

class TreeOps implements TreeOperationsI {

    @Override
    public Node insert(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }

        if (root.value > value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    @Override
    public int size(Node root) {
        if (root==null) {
            return 0;
        }
        return 1+size(root.left)+size(root.right);
    }

    @Override
    public int height(Node root) {
        if (root==null) {
            return 0;
        }

        return 1+ Math.max(height(root.left), height(root.right));
    }

    @Override
    public boolean contains(Node root, int value) {
        if (root==null) {
            return false;
        }

        if (root.value==value) {
            return true;
        }
        if (root.value > value) {
            return contains(root.left, value);
        } else {
            return contains(root.right, value);
        }
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

    @Override
    public void preOrder(Node root) {

        if (root == null) {
            return;
        }

        preOrder(root.left);
        preOrder(root.right);
        System.out.print(root.value+", ");
    }

    //8:41 - 8:45
    @Override
    public Node delete(Node root, int value) {
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
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node succ = successor(root);
                root.value = succ.value;
                root.right = delete(root.right, succ.value);
            }
        }

        return root;

    }

    @Override
    public void iterativeDFS(Node root) {
        if (root==null) {
            return;
        }
        System.out.println("Iterative DFS");

        Stack<Node> stack = new Stack<>();

        while (root != null) {
            stack.add(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            Node r = stack.pop();
            System.out.print(r.value+", ");
            r = r.right;

            while (r!=null) {
                stack.add(r);
                r = r.left;
            }
        }
        System.out.println();
    }

    private Node successor(Node root) {
        if (root==null) {
            return root;
        }
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    private Node getSuccessor(Node root) {
        if (root == null) {
            return null;
        }
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}

// 6:30 - 6:42
class Node {
    int value;
    Node left;
    Node right;


    public Node(int v) {
        this.value = v;
        this.left = null;
        this.right = null;
    }


    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                "}";
    }
}

interface TreeOperationsI {
    Node insert(Node root, int value);
    int size(Node root);
    int height(Node root);
    boolean contains(Node root, int value);
    void inorder(Node root);
    void preOrder(Node root);
    Node delete(Node root, int value);

    void iterativeDFS(Node root);
}