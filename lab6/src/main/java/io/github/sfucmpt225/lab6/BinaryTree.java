import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * A binary tree that stores int values. Complete the eight methods in the "YOUR CODE" section.
 */
public class BinaryTree {

    // =========================================================================
    //  PROVIDED CODE: do NOT change anything in this section.
    // =========================================================================

    /** One node of the tree. */
    private static class Node {
        int value;
        Node left;    // the left child, or null if there is none
        Node right;   // the right child, or null if there is none

        Node(int value) {
            this.value = value;
        }
    }

    /** The root (top node) of the tree, or null if the tree is empty. */
    private Node root;

    /** Creates an empty tree. */
    public BinaryTree() {
        root = null;
    }

    /**
     * Builds a tree from its level-order listing.
     * For example, BinaryTree tree = BinaryTree.fromLevelOrder(8, 3, 10, 1, 6, null, 14);
     * Values are listed level by level, left to right, and null marks a
     * missing child. fromLevelOrder() with no values gives the empty tree.
     */
    public static BinaryTree fromLevelOrder(Integer... values) {
        BinaryTree tree = new BinaryTree();
        if (values.length == 0 || values[0] == null) {
            return tree;
        }
        tree.root = new Node(values[0]);
        Queue<Node> waiting = new ArrayDeque<>();   // nodes whose children come next
        waiting.add(tree.root);
        int i = 1;
        while (i < values.length) {
            if (waiting.isEmpty()) {
                throw new IllegalArgumentException("The value at position " + i
                        + " has no parent node in " + Arrays.toString(values));
            }
            Node parent = waiting.remove();
            if (values[i] != null) {
                parent.left = new Node(values[i]);
                waiting.add(parent.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                parent.right = new Node(values[i]);
                waiting.add(parent.right);
            }
            i++;
        }
        return tree;
    }

    /**
     * Returns the tree in level-order notation, e.g. "[8, 3, 10, 1, 6, null, 14]".
     * The empty tree is "[]".
     */
    @Override
    public String toString() {
        List<String> items = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();   // a LinkedList can hold null
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node == null) {
                items.add("null");
            } else {
                items.add(String.valueOf(node.value));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        while (!items.isEmpty() && items.get(items.size() - 1).equals("null")) {
            items.remove(items.size() - 1);   // nulls at the end add nothing
        }
        return "[" + String.join(", ", items) + "]";
    }

    // =========================================================================
    //  YOUR CODE: complete the eight methods below.
    //
    //  - Replace each "throw new UnsupportedOperationException(...)" line
    //    with your code.
    //  - You may add private helper methods. Most of these methods need one.
    //  - Do not add fields (instance or static variables).
    //  - Only mirror() may change the tree.
    // =========================================================================

    /**
     * Returns the number of nodes in the tree. The empty tree has size 0.
     */
    public int size() {
        // TODO
        throw new UnsupportedOperationException("size() is not implemented yet");
    }

    /**
     * Returns the number of leaves in the tree. A leaf is a node with no
     * children. The empty tree has 0 leaves.
     */
    public int countLeaves() {
        // TODO
        throw new UnsupportedOperationException("countLeaves() is not implemented yet");
    }

    /**
     * Returns the height of the tree: the number of edges on the longest path
     * from the root down to a leaf. A tree with one node has height 0, and the
     * empty tree has height -1.
     */
    public int height() {
        // TODO
        throw new UnsupportedOperationException("height() is not implemented yet");
    }

    /**
     * Returns true if some node in the tree holds target, and false otherwise.
     * The tree is not necessarily a binary search tree.
     */
    public boolean contains(int target) {
        // TODO
        throw new UnsupportedOperationException("contains() is not implemented yet");
    }

    /**
     * Returns the largest value in the tree.
     *
     * @throws NoSuchElementException if the tree is empty
     */
    public int max() {
        // TODO
        throw new UnsupportedOperationException("max() is not implemented yet");
    }

    /**
     * Returns a new list of the values in the tree, in in-order: for every
     * node, first the values in its left subtree, then its own value, then the
     * values in its right subtree. The empty tree gives an empty list.
     */
    public List<Integer> inorder() {
        // TODO
        throw new UnsupportedOperationException("inorder() is not implemented yet");
    }

    /**
     * Changes the tree into its mirror image, by swapping the left and right
     * children of every node.
     */
    public void mirror() {
        // TODO
        throw new UnsupportedOperationException("mirror() is not implemented yet");
    }

    /**
     * Returns true if the tree is a binary search tree: for every node, all
     * values in its left subtree are smaller than its value, and all values in
     * its right subtree are larger. The empty tree is a binary search tree.
     */
    public boolean isBST() {
        // TODO
        throw new UnsupportedOperationException("isBST() is not implemented yet");
    }
}
