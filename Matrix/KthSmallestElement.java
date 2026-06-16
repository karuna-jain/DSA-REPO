// Problem: Kth smallest element in a row-column wise sorted matrix
// Time Complexity: O((R + C) * log(Max - Min)) where R is the number of rows, 
//                  C is the number of columns, Max is the largest element and Min is the smallest.
// Space Complexity: O(1) auxiliary space.

import java.util.Arrays;

public class KthSmallestElement {

    /**
     * Finds the kth smallest element in a row-column wise sorted matrix.
     * 
     * @param matrix The 2D row-column wise sorted matrix
     * @param k The 1-based index of the smallest element to find
     * @return The kth smallest element, or Integer.MIN_VALUE if matrix is empty/invalid.
     */
    public static int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }

        int R = matrix.length;
        int C = matrix[0].length;

        // Verify k is valid
        if (k < 1 || k > R * C) {
            throw new IllegalArgumentException("k is out of bounds");
        }

        int low = matrix[0][0];
        int high = matrix[R - 1][C - 1];

        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLessOrEqual(matrix, mid);

            if (count < k) {
                // kth smallest must be greater than mid
                low = mid + 1;
            } else {
                // mid could be the kth smallest, search in left half
                high = mid;
            }
        }

        return low;
    }

    /**
     * Helper to count elements in the matrix that are less than or equal to the target.
     * Starts from the top-right corner and moves down or left in O(R + C) time.
     */
    private static int countLessOrEqual(int[][] matrix, int target) {
        int R = matrix.length;
        int C = matrix[0].length;
        int count = 0;

        int r = 0;
        int c = C - 1;

        while (r < R && c >= 0) {
            if (matrix[r][c] <= target) {
                // Since row r is sorted, matrix[r][0...c] are all <= target
                count += (c + 1);
                r++; // Move to next row
            } else {
                c--; // Move left to search for smaller elements
            }
        }

        return count;
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
        int k1 = 8;
        System.out.println("k = " + k1 + "-th smallest element: " + kthSmallest(matrix1, k1)); // Expected: 32

        int k2 = 3;
        System.out.println("k = " + k2 + "-th smallest element: " + kthSmallest(matrix1, k2)); // Expected: 20

        System.out.println("\n--- Test Case 2: 3x2 Rectangular Sorted Matrix ---");
        int[][] matrix2 = {
            {5, 10},
            {6, 12},
            {8, 15}
        };
        printMatrix(matrix2);
        int k3 = 4;
        System.out.println("k = " + k3 + "-th smallest element: " + kthSmallest(matrix2, k3)); // Expected: 10
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
