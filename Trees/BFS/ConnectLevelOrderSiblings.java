import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * LeetCode 116 & 117: Populating Next Right Pointers in Each Node (I & II)
 * Problem: Connect level order siblings. Each node should point to its next right sibling.
 * For the rightmost node of each level, its next pointer should point to null.
 * 
 * Time Complexity: O(N) - We visit every node in the binary tree exactly once.
 * Space Complexity: O(N) - The queue holds at most N/2 nodes (last level) at any point.
 */
public class ConnectLevelOrderSiblings {

    public static NodeWithNext connect(NodeWithNext root) {
        if (root == null) {
            return null;
        }

        Queue<NodeWithNext> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            NodeWithNext previousNode = null;

            for (int i = 0; i < levelSize; i++) {
                NodeWithNext currentNode = queue.poll();

                // If this is not the first node in the level, set the next pointer of the previous node
                if (previousNode != null) {
                    previousNode.next = currentNode;
                }
                previousNode = currentNode;

                // Enqueue children
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Perfect Binary Tree ---");
        // Tree: [1, 2, 3, 4, 5, 6, 7]
        Integer[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        NodeWithNext root1 = NodeWithNext.createTree(arr1);
        connect(root1);
        List<List<Integer>> result1 = NodeWithNext.getLevelConnections(root1);
        System.out.println("Input Level Order representation: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Connected Levels Output: " + result1);
        // Expected levels connected: [[1], [2, 3], [4, 5, 6, 7]]

        System.out.println("\n--- Test Case 2: Unbalanced / Random Tree ---");
        // Tree: [12, 7, 1, 9, null, 10, 5]
        Integer[] arr2 = {12, 7, 1, 9, null, 10, 5};
        NodeWithNext root2 = NodeWithNext.createTree(arr2);
        connect(root2);
        List<List<Integer>> result2 = NodeWithNext.getLevelConnections(root2);
        System.out.println("Input Level Order representation: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("Connected Levels Output: " + result2);
        // Expected levels connected: [[12], [7, 1], [9, 10, 5]]
    }
}
