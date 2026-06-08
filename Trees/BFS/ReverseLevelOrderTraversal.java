import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 107: Binary Tree Level Order Traversal II (Reverse Level Order Traversal)
 * Time Complexity: O(N) - We visit every node in the binary tree exactly once.
 * Space Complexity: O(N) - In the worst case, the queue contains the nodes of the last level (up to N/2 nodes).
 */
public class ReverseLevelOrderTraversal {

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        // Using LinkedList so that adding elements to the beginning (index 0 / addFirst) is O(1)
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Prepend the current level list to the result
            result.addFirst(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Normal Binary Tree ---");
        // Tree: [3, 9, 20, null, null, 15, 7]
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = TreeNode.createTree(arr1);
        List<List<Integer>> result1 = levelOrderBottom(root1);
        System.out.println("Input Level Order representation: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Output: " + result1); // Expected: [[15, 7], [9, 20], [3]]

        System.out.println("\n--- Test Case 2: Single Node ---");
        Integer[] arr2 = {1};
        TreeNode root2 = TreeNode.createTree(arr2);
        List<List<Integer>> result2 = levelOrderBottom(root2);
        System.out.println("Input Level Order representation: [1]");
        System.out.println("Output: " + result2); // Expected: [[1]]

        System.out.println("\n--- Test Case 3: Empty Tree ---");
        Integer[] arr3 = {};
        TreeNode root3 = TreeNode.createTree(arr3);
        List<List<Integer>> result3 = levelOrderBottom(root3);
        System.out.println("Input Level Order representation: []");
        System.out.println("Output: " + result3); // Expected: []
    }
}
