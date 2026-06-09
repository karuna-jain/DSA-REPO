import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 545: Boundary of Binary Tree (Premium)
 * Problem: Return the values of the boundary of a binary tree in anti-clockwise order starting from the root.
 * The boundary includes:
 * 1. The root node (if it's not a leaf node).
 * 2. The left boundary (traversing left-most nodes, excluding leaf nodes).
 * 3. The leaves (traversing from left to right).
 * 4. The right boundary (traversing right-most nodes from bottom to top, excluding leaf nodes).
 * 
 * Time Complexity: O(N) - We traverse the left boundary, right boundary, and all leaves. Each node is visited at most a constant number of times.
 * Space Complexity: O(N) - Recursion stack for leaf traversal (O(H) where H is tree height) and O(N) to store the boundary nodes.
 */
public class TreeBoundary {

    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // Add root node if it's not a leaf node
        if (!isLeaf(root)) {
            result.add(root.val);
        }

        // 1. Traverse and add the Left Boundary
        addLeftBoundary(root.left, result);

        // 2. Traverse and add all Leaf Nodes (left to right)
        addLeaves(root, result);

        // 3. Traverse and add the Right Boundary (reversing it)
        addRightBoundary(root.right, result);

        return result;
    }

    private static boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private static void addLeftBoundary(TreeNode node, List<Integer> result) {
        TreeNode current = node;
        while (current != null) {
            if (!isLeaf(current)) {
                result.add(current.val);
            }
            if (current.left != null) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    private static void addLeaves(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (isLeaf(node)) {
            result.add(node.val);
            return;
        }
        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    private static void addRightBoundary(TreeNode node, List<Integer> result) {
        TreeNode current = node;
        List<Integer> temp = new ArrayList<>();
        while (current != null) {
            if (!isLeaf(current)) {
                temp.add(current.val);
            }
            if (current.right != null) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        // Add right boundary in reverse order (bottom to top)
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Simple Binary Tree ---");
        // Tree: [1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10]
        // Left boundary: 2
        // Leaves: 4, 7, 8, 9, 10
        // Right boundary: 3, 6 (reversed: 6, 3)
        // Root: 1
        // Expected boundary: [1, 2, 4, 7, 8, 9, 10, 6, 3] (note: 9 and 10 are children of 6)
        Integer[] arr1 = {1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10};
        TreeNode root1 = TreeNode.createTree(arr1);
        List<Integer> result1 = boundaryOfBinaryTree(root1);
        System.out.println("Input Level Order representation: [1, 2, 3, 4, 5, 6, null, null, null, 7, 8, 9, 10]");
        System.out.println("Boundary: " + result1);

        System.out.println("\n--- Test Case 2: Only Left Nodes (Skewed Left) ---");
        Integer[] arr2 = {1, 2, null, 3, null, null, null};
        TreeNode root2 = TreeNode.createTree(arr2);
        List<Integer> result2 = boundaryOfBinaryTree(root2);
        System.out.println("Input Level Order representation: [1, 2, null, 3]");
        System.out.println("Boundary: " + result2); // Expected: [1, 2, 3]

        System.out.println("\n--- Test Case 3: Single Node Tree ---");
        Integer[] arr3 = {1};
        TreeNode root3 = TreeNode.createTree(arr3);
        List<Integer> result3 = boundaryOfBinaryTree(root3);
        System.out.println("Input Level Order representation: [1]");
        System.out.println("Boundary: " + result3); // Expected: [1]
    }
}
