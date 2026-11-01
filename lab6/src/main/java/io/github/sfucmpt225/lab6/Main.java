import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

/*

 * Main.java is NOT marked. The I will run my own tests, on other trees as
 * well, so passing every check here does not guarantee full marks. Add your
 * own checks: copy a check(...) line and change the tree and the answer.
 */
public class Main {

    // -------------------------------------------------------------------------
    //  The example trees from README.md. Each call builds a brand-new tree, so
    //  a check that changes its tree (mirror) cannot affect any other check.
    // -------------------------------------------------------------------------

    /** The empty tree: [] */
    static BinaryTree empty() {
        return new BinaryTree();
    }

    /** A tree with one node: [7] */
    static BinaryTree single() {
        return BinaryTree.fromLevelOrder(7);
    }

    /** Tree A, a binary search tree: [8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13] */
    static BinaryTree treeA() {
        return BinaryTree.fromLevelOrder(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, 13);
    }

    /** Tree B, not a binary search tree: [5, 9, 2, 6, null, 8, 1] */
    static BinaryTree treeB() {
        return BinaryTree.fromLevelOrder(5, 9, 2, 6, null, 8, 1);
    }

    /** Tree C, a chain in which every node has only a right child: [1, null, 2, null, 3] */
    static BinaryTree treeC() {
        return BinaryTree.fromLevelOrder(1, null, 2, null, 3);
    }

    public static void main(String[] args) {
        section("size()");
        check("empty tree", 0, () -> empty().size());
        check("single node", 1, () -> single().size());
        check("tree A", 9, () -> treeA().size());
        check("tree B", 6, () -> treeB().size());
        check("tree C", 3, () -> treeC().size());

        section("countLeaves()");
        check("empty tree", 0, () -> empty().countLeaves());
        check("single node", 1, () -> single().countLeaves());
        check("tree A", 4, () -> treeA().countLeaves());
        check("tree B", 3, () -> treeB().countLeaves());
        check("tree C", 1, () -> treeC().countLeaves());

        section("height()");
        check("empty tree", -1, () -> empty().height());
        check("single node", 0, () -> single().height());
        check("tree A", 3, () -> treeA().height());
        check("tree B", 2, () -> treeB().height());
        check("tree C", 2, () -> treeC().height());

        section("contains(int)");
        check("empty tree, 7", false, () -> empty().contains(7));
        check("single node, 7", true, () -> single().contains(7));
        check("tree A, 13", true, () -> treeA().contains(13));
        check("tree A, 5", false, () -> treeA().contains(5));
        check("tree B, 8", true, () -> treeB().contains(8));
        check("tree B, 3", false, () -> treeB().contains(3));

        section("max()");
        checkThrows("empty tree", NoSuchElementException.class, () -> empty().max());
        check("single node", 7, () -> single().max());
        check("tree A", 14, () -> treeA().max());
        check("tree B", 9, () -> treeB().max());
        check("[-4, -9, -2]", -2, () -> BinaryTree.fromLevelOrder(-4, -9, -2).max());

        section("inorder()");
        check("empty tree", Collections.emptyList(), () -> empty().inorder());
        check("single node", Arrays.asList(7), () -> single().inorder());
        check("tree A", Arrays.asList(1, 3, 4, 6, 7, 8, 10, 13, 14), () -> treeA().inorder());
        check("tree B", Arrays.asList(6, 9, 5, 8, 2, 1), () -> treeB().inorder());
        check("tree C", Arrays.asList(1, 2, 3), () -> treeC().inorder());

        section("mirror()");
        check("empty tree", "[]", () -> mirrored(empty()));
        check("single node", "[7]", () -> mirrored(single()));
        check("tree A", "[8, 10, 3, 14, null, 6, 1, null, 13, 7, 4]", () -> mirrored(treeA()));
        check("tree B", "[5, 2, 9, 1, 8, null, 6]", () -> mirrored(treeB()));
        check("tree C", "[1, 2, null, 3]", () -> mirrored(treeC()));

        section("isBST()");
        check("empty tree", true, () -> empty().isBST());
        check("single node", true, () -> single().isBST());
        check("tree A", true, () -> treeA().isBST());
        check("tree B", false, () -> treeB().isBST());
        check("tree C", true, () -> treeC().isBST());
        check("[10, 5, 15, 2, 12]", false, () -> BinaryTree.fromLevelOrder(10, 5, 15, 2, 12).isBST());

        // Add your own checks here. For example:
        // check("my tree", 5, () -> BinaryTree.fromLevelOrder(1, 2, 3, 4, 5).size());

        System.out.println();
        System.out.println("Passed " + passed + " of " + total + " checks.");
        if (errors > 0) {
            System.out.println("Careful: " + errors + " check(s) threw an exception. When your work is marked,"
                    + " a method that throws an exception the README does not allow scores 0.");
        }
    }

    // -------------------------------------------------------------------------
    //  Helpers that run a check and print the result. You do not need to read
    //  them to use them.
    // -------------------------------------------------------------------------

    private static int passed = 0;
    private static int total = 0;
    private static int errors = 0;

    /** Prints a heading for the checks of one method. */
    static void section(String method) {
        System.out.println();
        System.out.println(method);
    }

    /** Runs code and checks that it returns expected. */
    static void check(String what, Object expected, Supplier<Object> code) {
        total++;
        Object actual;
        try {
            actual = code.get();
        } catch (RuntimeException | Error e) {
            reportException(what, e);
            return;
        }
        if (Objects.equals(expected, actual)) {
            passed++;
            print("PASS", what + ": " + actual);
        } else {
            print("FAIL", what + ": expected " + expected + ", got " + actual);
        }
    }

    /** Runs code and checks that it throws an exception of the given type. */
    static void checkThrows(String what, Class<? extends Throwable> expected, Supplier<Object> code) {
        total++;
        Object actual;
        try {
            actual = code.get();
        } catch (RuntimeException | Error e) {
            if (expected.isInstance(e)) {
                passed++;
                print("PASS", what + ": threw " + e.getClass().getSimpleName());
            } else {
                reportException(what, e);
            }
            return;
        }
        print("FAIL", what + ": expected " + expected.getSimpleName() + " to be thrown, but got " + actual);
    }

    /** Calls mirror() on the tree, then returns the tree in level-order notation. */
    static String mirrored(BinaryTree tree) {
        tree.mirror();
        return tree.toString();
    }

    private static void reportException(String what, Throwable e) {
        String message = e.getMessage() == null ? "" : e.getMessage();
        if (e instanceof UnsupportedOperationException && message.endsWith("not implemented yet")) {
            print("NOT DONE", what + ": " + message);
            return;
        }
        errors++;
        String text = what + ": threw " + e.getClass().getSimpleName();
        for (StackTraceElement frame : e.getStackTrace()) {
            if (frame.getClassName().startsWith("BinaryTree")) {
                text += " at line " + frame.getLineNumber() + " of BinaryTree.java";
                break;
            }
        }
        if (e instanceof StackOverflowError) {
            text += " (infinite recursion? Check your base case)";
        } else if (!message.isEmpty()) {
            text += ": " + message;
        }
        print("ERROR", text);
    }

    private static void print(String status, String text) {
        System.out.printf("  %-9s %s%n", status, text);
    }
}
