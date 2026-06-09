import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

/**
 * Connect All Level Order Siblings (Grokking DFS/BFS Pattern)
 * Problem: Given a binary tree, connect each node with its level order successor. 
 * The last node of each level should point to the first node of the next level.
 * The final node of the tree should point to null.
 * 
 * Time Complexity: O(N) - We visit every node in the binary tree exactly once.
 * Space Complexity: O(N) - The queue holds at most N/2 nodes (last level) at any point.
 */
public class ConnectAllLevelOrderSiblings {

    public static NodeWithNext connectAll(NodeWithNext root) {
        if (root == null) {
            return null;
        }

        Queue<NodeWithNext> queue = new LinkedList<>();
        queue.add(root);
        NodeWithNext previousNode = null;

        while (!queue.isEmpty()) {
            NodeWithNext currentNode = queue.poll();

            // Set the next pointer of the previous node to the current node
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

        // Ensure the last node points to null explicitly
        if (previousNode != null) {
            previousNode.next = null;
        }

        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Perfect Binary Tree ---");
        // Tree: [1, 2, 3, 4, 5, 6, 7]
        Integer[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        NodeWithNext root1 = NodeWithNext.createTree(arr1);
        connectAll(root1);
        List<Integer> result1 = NodeWithNext.getAllConnections(root1);
        System.out.println("Input Level Order representation: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("All Linked Connections Output: " + result1);
        // Expected connections: [1, 2, 3, 4, 5, 6, 7]

        System.out.println("\n--- Test Case 2: Unbalanced / Random Tree ---");
        // Tree: [12, 7, 1, 9, null, 10, 5]
        Integer[] arr2 = {12, 7, 1, 9, null, 10, 5};
        NodeWithNext root2 = NodeWithNext.createTree(arr2);
        connectAll(root2);
        List<Integer> result2 = NodeWithNext.getAllConnections(root2);
        System.out.println("Input Level Order representation: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("All Linked Connections Output: " + result2);
        // Expected connections: [12, 7, 1, 9, 10, 5]
    }
}
