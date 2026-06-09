import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 199: Binary Tree Right Side View
 * Problem: Given a binary tree, imagine yourself standing on the right side of it. 
 * Return the values of the nodes you can see ordered from top to bottom.
 * 
 * Time Complexity: O(N) - We visit every node in the binary tree exactly once.
 * Space Complexity: O(N) - The queue holds at most N/2 nodes (last level) at any point.
 */
public class RightViewBinaryTree {

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // If this is the last node of the current level, it's visible on the right side
                if (i == levelSize - 1) {
                    result.add(currentNode.val);
                }

                // Enqueue children
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Normal Tree with Missing Left/Right nodes ---");
        // Tree: [1, 2, 3, null, 5, null, 4]
        // Level 1: [1] -> Rightmost: 1
        // Level 2: [2, 3] -> Rightmost: 3
        // Level 3: [5, 4] -> Rightmost: 4
        Integer[] arr1 = {1, 2, 3, null, 5, null, 4};
        TreeNode root1 = TreeNode.createTree(arr1);
        List<Integer> result1 = rightSideView(root1);
        System.out.println("Input Level Order representation: [1, 2, 3, null, 5, null, 4]");
        System.out.println("Right Side View Output: " + result1); // Expected: [1, 3, 4]

        System.out.println("\n--- Test Case 2: Tree with left subtree taller than right subtree ---");
        // Tree: [1, 2, 3, 4]
        // Level 1: [1] -> Rightmost: 1
        // Level 2: [2, 3] -> Rightmost: 3
        // Level 3: [4] -> Rightmost: 4
        Integer[] arr2 = {1, 2, 3, 4};
        TreeNode root2 = TreeNode.createTree(arr2);
        List<Integer> result2 = rightSideView(root2);
        System.out.println("Input Level Order representation: [1, 2, 3, 4]");
        System.out.println("Right Side View Output: " + result2); // Expected: [1, 3, 4]

        System.out.println("\n--- Test Case 3: Single Node Tree ---");
        Integer[] arr3 = {1};
        TreeNode root3 = TreeNode.createTree(arr3);
        List<Integer> result3 = rightSideView(root3);
        System.out.println("Input Level Order representation: [1]");
        System.out.println("Right Side View Output: " + result3); // Expected: [1]
        
        System.out.println("\n--- Test Case 4: Empty Tree ---");
        Integer[] arr4 = {};
        TreeNode root4 = TreeNode.createTree(arr4);
        List<Integer> result4 = rightSideView(root4);
        System.out.println("Input Level Order representation: []");
        System.out.println("Right Side View Output: " + result4); // Expected: []
    }
}
