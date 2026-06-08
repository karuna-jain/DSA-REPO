import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 637: Average of Levels in Binary Tree
 * Time Complexity: O(N) - We visit every node in the binary tree exactly once.
 * Space Complexity: O(N) - In the worst case, the queue contains the nodes of the last level (up to N/2 nodes).
 */
public class LevelAverages {

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0; // Using double to prevent integer overflow

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                levelSum += currentNode.val;

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            result.add(levelSum / levelSize);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Normal Binary Tree ---");
        // Tree: [3, 9, 20, null, null, 15, 7]
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = TreeNode.createTree(arr1);
        List<Double> result1 = averageOfLevels(root1);
        System.out.println("Input Level Order representation: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Output: " + result1); // Expected: [3.0, 14.5, 11.0]

        System.out.println("\n--- Test Case 2: Tree with Negative and Large Values ---");
        // Tree: [3, 9, 20, 15, 7]
        Integer[] arr2 = {3, 9, 20, 15, 7};
        TreeNode root2 = TreeNode.createTree(arr2);
        List<Double> result2 = averageOfLevels(root2);
        System.out.println("Input Level Order representation: [3, 9, 20, 15, 7]");
        System.out.println("Output: " + result2); // Expected: [3.0, 14.5, 11.0]
    }
}
