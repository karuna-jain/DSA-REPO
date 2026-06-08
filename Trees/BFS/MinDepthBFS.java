import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 111: Minimum Depth of Binary Tree (using BFS Traversal)
 * Time Complexity: O(N) - In the worst case (e.g., a balanced tree or a linked list), we may visit all nodes.
 *                  However, BFS is highly optimized for this because it terminates as soon as it finds the FIRST leaf node.
 * Space Complexity: O(N) - In the worst case, the queue contains the nodes of the last level (up to N/2 nodes).
 */
public class MinDepthBFS {

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // First leaf node encountered in BFS is at the minimum depth
                if (currentNode.left == null && currentNode.right == null) {
                    return depth;
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            depth++;
        }

        return depth;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Normal Unbalanced Tree ---");
        // Tree: [3, 9, 20, null, null, 15, 7]
        // Node 9 is a leaf node at depth 2.
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = TreeNode.createTree(arr1);
        int result1 = minDepth(root1);
        System.out.println("Input Level Order representation: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Output: " + result1); // Expected: 2

        System.out.println("\n--- Test Case 2: Right-Skewed Tree (Linked List-like) ---");
        // Tree: [2, null, 3, null, 4, null, 5, null, 6]
        // Leaves are at depth 5.
        Integer[] arr2 = {2, null, 3, null, 4, null, 5, null, 6};
        TreeNode root2 = TreeNode.createTree(arr2);
        int result2 = minDepth(root2);
        System.out.println("Input Level Order representation: [2, null, 3, null, 4, null, 5, null, 6]");
        System.out.println("Output: " + result2); // Expected: 5

        System.out.println("\n--- Test Case 3: Empty Tree ---");
        Integer[] arr3 = {};
        TreeNode root3 = TreeNode.createTree(arr3);
        int result3 = minDepth(root3);
        System.out.println("Input Level Order representation: []");
        System.out.println("Output: " + result3); // Expected: 0
    }
}
