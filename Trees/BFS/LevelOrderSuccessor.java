import java.util.LinkedList;
import java.util.Queue;

/**
 * Level Order Successor (Grokking DFS/BFS Pattern)
 * Problem: Given a binary tree and a node, find the level order successor of the given node in the tree.
 * The level order successor is the node that appears right after the given node in the level order traversal.
 * 
 * Time Complexity: O(N) - We traverse the tree level by level. In the worst case, we might visit all nodes.
 * Space Complexity: O(N) - The queue holds at most N/2 nodes (last level) at any point.
 */
public class LevelOrderSuccessor {

    public static TreeNode findSuccessor(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // Add children to the queue first
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }

            // If we found the target node, the next node to be popped from the queue is the successor
            if (currentNode.val == key) {
                return queue.peek();
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Target is in middle of tree ---");
        // Tree: [1, 2, 3, 4, 5]
        // Level order: 1, 2, 3, 4, 5
        // Successor of 3 is 4
        Integer[] arr1 = {1, 2, 3, 4, 5};
        TreeNode root1 = TreeNode.createTree(arr1);
        TreeNode result1 = findSuccessor(root1, 3);
        System.out.println("Tree representation: [1, 2, 3, 4, 5]");
        System.out.println("Target: 3");
        System.out.println("Output Successor: " + (result1 != null ? result1.val : "null")); // Expected: 4

        System.out.println("\n--- Test Case 2: Target is the last node ---");
        // Successor of 5 is null (last node)
        TreeNode result2 = findSuccessor(root1, 5);
        System.out.println("Target: 5");
        System.out.println("Output Successor: " + (result2 != null ? result2.val : "null")); // Expected: null

        System.out.println("\n--- Test Case 3: Target is the root node ---");
        // Successor of 1 is 2
        TreeNode result3 = findSuccessor(root1, 1);
        System.out.println("Target: 1");
        System.out.println("Output Successor: " + (result3 != null ? result3.val : "null")); // Expected: 2
        
        System.out.println("\n--- Test Case 4: Larger Tree ---");
        // Tree: [12, 7, 1, 9, null, 10, 5]
        // Level order: 12, 7, 1, 9, 10, 5
        // Successor of 9 is 10
        Integer[] arr4 = {12, 7, 1, 9, null, 10, 5};
        TreeNode root4 = TreeNode.createTree(arr4);
        TreeNode result4 = findSuccessor(root4, 9);
        System.out.println("Tree representation: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("Target: 9");
        System.out.println("Output Successor: " + (result4 != null ? result4.val : "null")); // Expected: 10
    }
}
