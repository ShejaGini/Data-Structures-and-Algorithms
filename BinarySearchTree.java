class TreeNode<T> {
    T value;
    TreeNode<T> left, right;

    public TreeNode(T value) {
        this.value = value;
        left = right = null;
    }
}

class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T value) {
        if (root == null) {
            root = new TreeNode<>(value);
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    public boolean search(T value) {
        return searchRec(root, value) != null;
    }

    private TreeNode<T> searchRec(TreeNode<T> root, T value) {
        if (root == null || root.value.equals(value)) {
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            return searchRec(root.left, value);
        }
        return searchRec(root.right, value);
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode<T> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }

    public void preorder() {
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root.value + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorder() {
        System.out.print("Postorder: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode<T> root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.value + " ");
        }
    }

    
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal:");
        tree.inorder();

        System.out.println("\nSearch 60: " + tree.search(60));
        System.out.println("Search 100: " + tree.search(100));
    }


}


   