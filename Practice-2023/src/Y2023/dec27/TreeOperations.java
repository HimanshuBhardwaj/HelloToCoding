package Y2023.dec27;

/**
 * @author Himanshu Bhardwaj
 * @Date 12/27/2023
 */
public class TreeOperations {
    public static void main(String[] args) {
        Node<String> root = new Node("3");
        root = root.insert(root,"2");
        root = root.insert(root,"3");
        root = root.insert(root,"4");
        root = root.insert(root,"5");
        root = root.insert(root,"0");
        root = root.insert(root,"1");

        root.inorder(root);
        System.out.println();

        System.out.println("Deleting 3");
        root = root.delete(root,"3");
        root.inorder(root);
        System.out.println();
        System.out.println("Deleting 3");
        root = root.delete(root,"3");
        root.inorder(root);
        System.out.println();
        System.out.println("Deleting 0");
        root = root.delete(root,"0");
        root.inorder(root);

        System.out.println();
        System.out.println("Deleting 1");
        root = root.delete(root,"1");
        root.inorder(root);

        System.out.println();
        System.out.println("Deleting 2");
        root = root.delete(root,"2");
        root.inorder(root);

        System.out.println();
        System.out.println("Deleting 4");
        root = root.delete(root,"4");
        root.inorder(root);
        
    }
}


class Node<T extends Comparable> implements TreeOperationsI<T> {
    T value;
    Node left;
    Node right;

    int diam=0;

    public Node(T value) {
        this.value = value;
        this.left= null;
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

    @Override
    public int height(Node root) {
        if (root==null) {
            return 0;
        }
        return 1+Math.max(size(root.left), size(root.left));
    }

    @Override
    public int diameter(Node root) {
        if (root== null) {
            return 0;
        }

        int hl = diameter(root.left);
        int hr = diameter(root.right);
        diam = Math.max(diam, hl+hr+1);
        return Math.max(hl,hr)+1;
    }

    @Override
    public int size(Node root) {
        if (root==null) {
            return 0;
        }
        return 1+size(root.left)+size(root.right);
    }

    @Override
    public Node delete(Node root, T value) {
        if (root==null) {
            return null;
        }

        if (root.value.compareTo(value)==0) {
            if (root.left == null && root.right==null) {
                return null;
            } else if (root.left != null && root.right==null) {
                return root.left;
            } else if (root.right != null && root.left==null) {
                return root.right;
            } else {
                Node<T> suc = getSuccessor(root);
                root.value = suc.value;
                root.right = delete(root.right, suc.value);
            }
        } else if (root.value.compareTo(value) > 0) {
            root.left = delete(root.left, value);
        } else {
            root.right = delete(root.right, value);
        }
        return root;
    }

    private Node<T> getSuccessor(Node root) {
        root = root.right;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}


interface TreeOperationsI<T extends Comparable> {
    Node insert(Node root, T value);
    void inorder(Node root);
    int height(Node root);
    int diameter(Node root);
    int size(Node root);

    Node delete(Node root, T value);
}