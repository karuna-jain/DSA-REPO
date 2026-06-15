// Problem: Print elements in sorted order using row-column wise sorted matrix
// Time Complexity: O(R * C * log(R)) where R is number of rows, C is number of columns.
// Space Complexity: O(R) for the min-heap.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PrintSortedMatrix {

    // Node structure to store element value along with its row and column indexes
    private static class HeapNode implements Comparable<HeapNode> {
        int val;
        int row;
        int col;

        public HeapNode(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(HeapNode other) {
            return Integer.compare(this.val, other.val);
        }
    }

    /**
     * Extracts and returns all elements of a row-column sorted matrix in sorted order.
     * 
     * @param matrix The row-column wise sorted matrix
     * @return A list of all elements sorted in non-decreasing order
     */
    public static List<Integer> getSortedElements(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int R = matrix.length;
        int C = matrix[0].length;

        // Min-Heap to keep track of the minimum active element from each row
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>();

        // Insert the first element of each row into the min-heap
        for (int i = 0; i < R; i++) {
            minHeap.add(new HeapNode(matrix[i][0], i, 0));
        }

        // Process elements and push next element of the same row if available
        while (!minHeap.isEmpty()) {
            HeapNode curr = minHeap.poll();
            result.add(curr.val);

            // Move to the next element in the same row
            if (curr.col + 1 < C) {
                minHeap.add(new HeapNode(matrix[curr.row][curr.col + 1], curr.row, curr.col + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: 4x4 Row-Column Sorted Matrix ---");
        int[][] matrix1 = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {27, 29, 37, 48},
            {32, 33, 39, 50}
        };
        printMatrix(matrix1);
        
        List<Integer> sortedList1 = getSortedElements(matrix1);
        System.out.println("\nSorted Elements: " + sortedList1);
        // Expected: [10, 15, 20, 25, 27, 29, 30, 32, 33, 35, 37, 39, 40, 45, 48, 50]

        System.out.println("\n--- Test Case 2: 3x2 Rectangular Sorted Matrix ---");
        int[][] matrix2 = {
            {5, 10},
            {6, 12},
            {8, 15}
        };
        printMatrix(matrix2);
        
        List<Integer> sortedList2 = getSortedElements(matrix2);
        System.out.println("\nSorted Elements: " + sortedList2);
        // Expected: [5, 6, 8, 10, 12, 15]
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
