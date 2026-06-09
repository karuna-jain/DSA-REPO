import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

/**
 * Node definition containing a 'next' pointer for BFS sibling connection problems.
 * No package declaration is used to match repository standards.
 */
public class NodeWithNext {
    public int val;
    public NodeWithNext left;
    public NodeWithNext right;
    public NodeWithNext next;

    public NodeWithNext() {}

    public NodeWithNext(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.next = null;
    }

    public NodeWithNext(int val, NodeWithNext left, NodeWithNext right, NodeWithNext next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    /**
     * Helper method to build a binary tree from a level-order array representation (which can contain nulls).
     *
     * @param arr The array representing the binary tree level-order traversal.
     * @return The root of the constructed binary tree.
     */
    public static NodeWithNext createTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        NodeWithNext root = new NodeWithNext(arr[0]);
        Queue<NodeWithNext> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < arr.length && !queue.isEmpty()) {
            NodeWithNext current = queue.poll();

            // Left child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.left = new NodeWithNext(arr[i]);
                    queue.add(current.left);
                }
                i++;
            }

            // Right child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.right = new NodeWithNext(arr[i]);
                    queue.add(current.right);
                }
                i++;
            }
        }

        return root;
    }

    /**
     * Formats next connections level by level to assist in verification.
     * Starts from the leftmost node of each level and traverses the next pointers.
     */
    public static List<List<Integer>> getLevelConnections(NodeWithNext root) {
        List<List<Integer>> levels = new ArrayList<>();
        NodeWithNext leftmost = root;
        while (leftmost != null) {
            List<Integer> levelValues = new ArrayList<>();
            NodeWithNext current = leftmost;
            while (current != null) {
                levelValues.add(current.val);
                current = current.next;
            }
            levels.add(levelValues);
            
            // Find the first non-null left or right child of the current level to find the next level's leftmost node
            NodeWithNext temp = leftmost;
            leftmost = null;
            while (temp != null) {
                if (temp.left != null) {
                    leftmost = temp.left;
                    break;
                }
                if (temp.right != null) {
                    leftmost = temp.right;
                    break;
                }
                temp = temp.next;
            }
        }
        return levels;
    }

    /**
     * Formats all next connections as a single list.
     * Starts from the root and traverses the next pointers.
     */
    public static List<Integer> getAllConnections(NodeWithNext root) {
        List<Integer> result = new ArrayList<>();
        NodeWithNext current = root;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }
}
