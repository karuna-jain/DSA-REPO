import java.util.LinkedList;
import java.util.Queue;

/**
 * Standard TreeNode definition for Binary Tree problems.
 * No package declaration is used to match repository standards.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Helper method to build a binary tree from a level-order array representation (which can contain nulls).
     * E.g., for input: [3, 9, 20, null, null, 15, 7]
     *
     * @param arr The array representing the binary tree level-order traversal.
     * @return The root of the constructed binary tree.
     */
    public static TreeNode createTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < arr.length && !queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Left child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.left = new TreeNode(arr[i]);
                    queue.add(current.left);
                }
                i++;
            }

            // Right child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.right = new TreeNode(arr[i]);
                    queue.add(current.right);
                }
                i++;
            }
        }

        return root;
    }
}
