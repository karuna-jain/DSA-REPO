import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * LeetCode 987: Vertical Order Traversal of a Binary Tree
 * Problem: Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * For each node at position (row, col), its left child will be at (row + 1, col - 1) 
 * and its right child will be at (row + 1, col + 1). The root is at (0, 0).
 * 
 * The vertical order traversal is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column.
 * If two nodes have the same position (row, col), the node with the smaller value should appear first.
 * 
 * Time Complexity: O(N log N) - Where N is the number of nodes in the binary tree. 
 *                  We do a BFS traversal taking O(N) time and then sort the nodes taking O(N log N) time.
 * Space Complexity: O(N) - To store node coordinates and traversal queue.
 */
public class VerticalOrderTraversal {

    private static class NodeInfo implements Comparable<NodeInfo> {
        TreeNode node;
        int row;
        int col;

        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(NodeInfo other) {
            if (this.col != other.col) {
                return Integer.compare(this.col, other.col);
            }
            if (this.row != other.row) {
                return Integer.compare(this.row, other.row);
            }
            return Integer.compare(this.node.val, other.node.val);
        }
    }

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<NodeInfo> nodeList = new ArrayList<>();
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.add(new NodeInfo(root, 0, 0));

        while (!queue.isEmpty()) {
            NodeInfo current = queue.poll();
            nodeList.add(current);

            if (current.node.left != null) {
                queue.add(new NodeInfo(current.node.left, current.row + 1, current.col - 1));
            }
            if (current.node.right != null) {
                queue.add(new NodeInfo(current.node.right, current.row + 1, current.col + 1));
            }
        }

        // Sort based on col, then row, then node value
        Collections.sort(nodeList);

        // Group by column using a TreeMap (which keeps keys sorted in ascending order)
        Map<Integer, List<Integer>> colMap = new TreeMap<>();
        for (NodeInfo info : nodeList) {
            colMap.putIfAbsent(info.col, new ArrayList<>());
            colMap.get(info.col).add(info.node.val);
        }

        result.addAll(colMap.values());
        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Binary Tree ---");
        // Tree: [3, 9, 20, null, null, 15, 7]
        // Vertical order: Column -1: [9], Column 0: [3, 15], Column 1: [20], Column 2: [7]
        // Expected: [[9], [3, 15], [20], [7]]
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = TreeNode.createTree(arr1);
        List<List<Integer>> result1 = verticalTraversal(root1);
        System.out.println("Input Level Order representation: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Vertical Order Traversal: " + result1);

        System.out.println("\n--- Test Case 2: Tree with overlapping nodes (same coordinate) ---");
        // Tree: [1, 2, 3, 4, 5, 6, 7]
        // Coordinates:
        // 1: (0, 0)
        // 2: (1, -1), 3: (1, 1)
        // 4: (2, -2), 5: (2, 0), 6: (2, 0), 7: (2, 2)
        // Note: 5 and 6 are both at (2, 0). They should be sorted by value: [5, 6].
        // Expected: [[4], [2], [1, 5, 6], [3], [7]]
        Integer[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root2 = TreeNode.createTree(arr2);
        List<List<Integer>> result2 = verticalTraversal(root2);
        System.out.println("Input Level Order representation: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Vertical Order Traversal: " + result2);
    }
}
