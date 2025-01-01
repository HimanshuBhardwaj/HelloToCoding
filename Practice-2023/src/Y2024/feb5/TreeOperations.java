package Y2024.feb5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Himanshu Bhardwaj
 * @Date 2/5/2024
 */
public class TreeOperations {
    public static void main(String[] args) {
        TreeOpsI treeOpsI = new TreeOperation();
        treeOpsI.insert(10);
        treeOpsI.insert(12);
        treeOpsI.insert(8);
        treeOpsI.insert(13);
        treeOpsI.insert(11);
        treeOpsI.inorder();
        System.out.println();
        System.out.println("DFS");
        treeOpsI.DFS();
        System.out.println();
        System.out.println("BFS");
        treeOpsI.BFS();
        System.out.println();
        System.out.println("Level order");
        treeOpsI.levelOrder();
        System.out.println();
    }
}

class TreeOperation implements TreeOpsI {
    Node root;

    public TreeOperation() {
        root = null;
    }

    @Override
    public void insert(int value) {
        if (root==null) {
            root = new Node(value);
        } else {
            root = insertHelper(root,value);
        }

    }

    private Node insertHelper(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (root.value > value) {
            root.left = insertHelper(root.left, value);
        } else {
            root.right = insertHelper(root.right, value);
        }

        return root;
    }

    @Override
    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node root) {
        if (root == null) {
            return 0;
        }
        return 1+sizeHelper(root.left)+sizeHelper(root.right);
    }

    @Override
    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node root) {
        if (root==null) {
            return 0;
        }

        return 1+ Math.max(heightHelper(root.left),heightHelper(root.right));
    }

    @Override
    public void inorder() {
        inorderHelper(root);
    }

    private void inorderHelper(Node root) {
        if (root==null) {
            return;
        }
        inorderHelper(root.left);
        System.out.print(root.value+", ");
        inorderHelper(root.right);
    }

    @Override
    public void delete(int value) {
        root = deleteHelper(root, value);
    }

    private Node deleteHelper(Node root, int value) {
        if (root==null) {
            return null;
        }
        if (root.value>value) {
            root.left = deleteHelper(root.left, value);
        } else if (root.value < value) {
            root.right = deleteHelper(root.right, value);
        } else {
            if (root.left==null && root.right==null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                Node succ = getSucc(root);
                root.value = succ.value;
                root.right = deleteHelper(root.right, succ.value);
            }
        }

        return root;
    }

    private Node getSucc(Node root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    @Override
    public void DFS() {
        DFSHelper(root);

    }

    private void DFSHelper(Node root) {
        if (root== null) {
            return;
        }

        System.out.println(root.value);
        DFSHelper(root.left);
        DFSHelper(root.right);
    }

    @Override
    public void BFS() {
        BFSHelper(root);
    }

    private void BFSHelper(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            System.out.println(top.value);

            if (top.left != null) {
                queue.add(top.left);
            }

            if (top.right != null) {
                queue.add(top.right);
            }
        }
    }

    @Override
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        Node sepNode = new Node(Integer.MIN_VALUE);
        queue.add(root);
        queue.add(sepNode);
        int level=0;
        System.out.print("0:");

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            if (top.value == sepNode.value) {
                if (!queue.isEmpty()) {
                    queue.add(top);
                    level++;
                    System.out.println();
                    System.out.print(level+": ");
                }
            } else {
                System.out.print(top.value+", ");
                if (top.left != null) {
                    queue.add(top.left);
                }
                if (top.right != null) {
                    queue.add(top.right);
                }
            }
        }
    }
}
interface TreeOpsI {
    void insert(int value);
    int size();
    int height();
    void inorder();
    void delete(int value);
    void DFS();
    void BFS();
    void levelOrder();
}


class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.value);
    }
}