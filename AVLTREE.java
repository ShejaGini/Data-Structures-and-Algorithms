class AVLNode<T extends Comparable<T>> {
    T key;
    int height;
    AVLNode<T> left, right;

    AVLNode(T key) {
        this.key = key;
        this.height = 1;
    }
}

class AVLTree<T extends Comparable<T>> {
    AVLNode<T> root;

    int height(AVLNode<T> N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    AVLNode<T> rightRotate(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    AVLNode<T> leftRotate(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    int getBalance(AVLNode<T> N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    AVLNode<T> insert(AVLNode<T> node, T key) {
        if (node == null)
            return (new AVLNode<>(key));

        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareTo(node.right.key) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    AVLNode<T> minValueNode(AVLNode<T> node) {
        AVLNode<T> current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    AVLNode<T> deleteNode(AVLNode<T> root, T key) {
        if (root == null)
            return root;

        if (key.compareTo(root.key) < 0)
            root.left = deleteNode(root.left, key);

        else if (key.compareTo(root.key) > 0)
            root.right = deleteNode(root.right, key);

        else {
            if ((root.left == null) || (root.right == null)) {
                AVLNode<T> temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                AVLNode<T> temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void preOrder(AVLNode<T> node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    AVLNode<T> search(AVLNode<T> root, T key) {
        if (root == null || root.key.equals(key))
            return root;

        if (root.key.compareTo(key) > 0)
            return search(root.left, key);

        return search(root.right, key);
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);

        System.out.println("Preorder traversal of constructed AVL Tree is :");
        tree.preOrder(tree.root);

        System.out.println("\n\nAfter deletion of 10:");
        tree.root = tree.deleteNode(tree.root, 10);
        tree.preOrder(tree.root);

        int key = 6;
        AVLNode<Integer> result = tree.search(tree.root, key);
        if (result != null) {
            System.out.println("\n\nKey " + key + " is present in the AVL Tree");
        } else {
            System.out.println("\n\nKey " + key + " is not present in the AVL Tree");
        }
    }
}

