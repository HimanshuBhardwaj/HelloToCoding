package Y2023.july18;

public class TreeReconstruction {
    public static void main(String[] args) {
        int inorder [] = new int[] {-11};
        int preorder [] = new int[] {-11};

        TreeGeneration treeGeneration = new TreeOperations();
        Node root = treeGeneration.inorderPreOrderTraversal(inorder, preorder);
        System.out.println();
        root.postOrder(root);
    }
}

class TreeOperations implements TreeGeneration {
    int preOrderIndex;
    Node root;

    @Override
    public Node inorderPreOrderTraversal(int[] inorder, int[] preOrder) {
        inorderPreOrderTraversalHelper(inorder, preOrder, 0, preOrder.length-1);
        preOrderIndex = 0;
        Node tRoot=this.root;
        this.root = null;
        return tRoot;
    }

    private Node inorderPreOrderTraversalHelper(int[] inorder, int[] preOrder, int start, int end) {
        if (start>end || start<=-1 || end<=-1) {
            return null;
        }

        Node nRoot = new Node(preOrder[preOrderIndex++]);
        if (this.root == null) {
            this.root = nRoot;
        }
        int pos=-1;

        for (int i=start;i<=end;i++) {
            if (inorder[i] == nRoot.value) {
                pos=i;
                break;
            }
        }

        nRoot.left = inorderPreOrderTraversalHelper(inorder, preOrder, start, pos-1);
        nRoot.right = inorderPreOrderTraversalHelper(inorder, preOrder, pos+1, end);

        return nRoot;
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int v) {
        this.value = v;
        this.left = null;
        this.right = null;
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.value+", ");
        inorder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value+", ");
    }
}

interface TreeGeneration {
    Node inorderPreOrderTraversal(int [] inorder, int [] preOrder);
}