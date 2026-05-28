import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class verticalSum {

    public static void sum(Node root,
            TreeMap<Integer, Integer> map,
            int hd) {

        if (root == null)
            return;

        // Add current node value
        map.put(hd, map.getOrDefault(hd, 0) + root.data);

        // Traverse left
        sum(root.left, map, hd - 1);

        // Traverse right
        sum(root.right, map, hd + 1);
    }

    public static void main(String[] args) {

        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        TreeMap<Integer, Integer> map = new TreeMap<>();

        sum(root, map, 0);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}