// Problem: Find Row with Maximum Number of 1's
// Time Complexity: O(R + C) where R is number of rows, C is number of columns.
// Space Complexity: O(1) auxiliary space.

import java.util.Arrays;

public class RowWithMaxOnes {

    /**
     * Finds the 0-based index of the row with the maximum number of 1's.
     * Assumes each row of the binary matrix is sorted (0s followed by 1s).
     * 
     * @param matrix The 2D binary matrix
     * @return Index of the row with maximum 1's, or -1 if no 1's are present.
     */
    public static int rowWithMax1s(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }

        int R = matrix.length;
        int C = matrix[0].length;
        int maxRowIndex = -1;
        int j = C - 1; // Start from top-right corner

        for (int i = 0; i < R; i++) {
            boolean rowUpdated = false;
            // Move left as long as we see 1
            while (j >= 0 && matrix[i][j] == 1) {
                j--;
                rowUpdated = true;
            }
            // If we successfully moved left, this row has more 1's than any previous row
            if (rowUpdated) {
                maxRowIndex = i;
            }
        }

        return maxRowIndex;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Matrix ---");
        int[][] matrix1 = {
            {0, 1, 1, 1},
            {0, 0, 1, 1},
            {1, 1, 1, 1}, // Row 2 has the most 1's (four 1's)
            {0, 0, 0, 0}
        };
        printMatrix(matrix1);
        System.out.println("Row with max 1's: " + rowWithMax1s(matrix1)); // Expected: 2

        System.out.println("\n--- Test Case 2: All Zeros ---");
        int[][] matrix2 = {
            {0, 0, 0},
            {0, 0, 0}
        };
        printMatrix(matrix2);
        System.out.println("Row with max 1's: " + rowWithMax1s(matrix2)); // Expected: -1

        System.out.println("\n--- Test Case 3: Single Row Matrix ---");
        int[][] matrix3 = {
            {0, 0, 1, 1, 1}
        };
        printMatrix(matrix3);
        System.out.println("Row with max 1's: " + rowWithMax1s(matrix3)); // Expected: 0
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
