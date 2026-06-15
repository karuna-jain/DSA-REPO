// Problem: Search an element in a Matrix (LeetCode 74 & LeetCode 240)
// Time Complexity: 
//   - Method 1 (Binary Search): O(log(M * N)) for a fully sorted 2D matrix.
//   - Method 2 (Staircase Search): O(M + N) for a row-column wise sorted matrix.
// Space Complexity: O(1) auxiliary space.

import java.util.Arrays;

public class SearchMatrix {

    /**
     * Method 1: Binary Search (LeetCode 74)
     * Assumes the matrix is fully sorted:
     * 1. Integers in each row are sorted from left to right.
     * 2. The first integer of each row is greater than the last integer of the previous row.
     * 
     * @param matrix The 2D matrix
     * @param target The element to search for
     * @return true if target is found, false otherwise
     */
    public static boolean searchMatrixBinary(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int low = 0;
        int high = rows * cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // Map 1D index back to 2D row and column
            int r = mid / cols;
            int c = mid % cols;
            int midVal = matrix[r][c];

            if (midVal == target) {
                return true;
            } else if (midVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    /**
     * Method 2: Staircase Search / Search in a 2D Matrix II (LeetCode 240)
     * Assumes a row-column sorted matrix:
     * 1. Integers in each row are sorted from left to right.
     * 2. Integers in each column are sorted from top to bottom.
     * 
     * @param matrix The 2D matrix
     * @param target The element to search for
     * @return true if target is found, false otherwise
     */
    public static boolean searchMatrixStaircase(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int r = 0;
        int c = matrix[0].length - 1; // Start from top-right corner

        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--; // Move left
            } else {
                r++; // Move down
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        System.out.println("--- Input Matrix ---");
        printMatrix(matrix);

        System.out.println("\n--- Testing Method 1: Binary Search ---");
        int[] targetsToTest = {3, 16, 60, 0, 15, 65};
        for (int target : targetsToTest) {
            boolean found = searchMatrixBinary(matrix, target);
            System.out.println("Target: " + target + " -> Found: " + found);
        }

        System.out.println("\n--- Testing Method 2: Staircase Search ---");
        for (int target : targetsToTest) {
            boolean found = searchMatrixStaircase(matrix, target);
            System.out.println("Target: " + target + " -> Found: " + found);
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
