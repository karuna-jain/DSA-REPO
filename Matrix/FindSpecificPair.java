// Problem: Find a specific pair in matrix
// Given an N x N matrix, find the maximum value of matrix[c][d] - matrix[a][b]
// such that c > a and d > b.
// Time Complexity: O(N^2) where N is the dimension of the square matrix.
// Space Complexity: O(N^2) for the maxMat lookup table.

import java.util.Arrays;

public class FindSpecificPair {

    /**
     * Finds the maximum value of matrix[c][d] - matrix[a][b] such that c > a and d > b.
     * 
     * @param matrix The N x N square matrix
     * @return The maximum difference, or Integer.MIN_VALUE if a valid pair cannot be formed.
     */
    public static int findMaxValue(int[][] matrix) {
        if (matrix == null || matrix.length < 2 || matrix[0].length < 2) {
            return Integer.MIN_VALUE; // A valid pair requires at least a 2x2 subgrid
        }

        int N = matrix.length;
        // maxMat[i][j] stores the maximum value in the submatrix from matrix[i][j] to matrix[N-1][N-1]
        int[][] maxMat = new int[N][N];

        // Base case: The bottom-right corner
        maxMat[N-1][N-1] = matrix[N-1][N-1];

        // Preprocess the last row
        int maxVal = matrix[N-1][N-1];
        for (int j = N - 2; j >= 0; j--) {
            if (matrix[N-1][j] > maxVal) {
                maxVal = matrix[N-1][j];
            }
            maxMat[N-1][j] = maxVal;
        }

        // Preprocess the last column
        maxVal = matrix[N-1][N-1];
        for (int i = N - 2; i >= 0; i--) {
            if (matrix[i][N-1] > maxVal) {
                maxVal = matrix[i][N-1];
            }
            maxMat[i][N-1] = maxVal;
        }

        int maxDiff = Integer.MIN_VALUE;

        // Process the rest of the matrix from bottom-right to top-left
        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 2; j >= 0; j--) {
                // Update the maximum difference using the precomputed max value in the submatrix starting at (i+1, j+1)
                int diff = maxMat[i+1][j+1] - matrix[i][j];
                if (diff > maxDiff) {
                    maxDiff = diff;
                }

                // Update maxMat[i][j] with the maximum element in the submatrix starting at (i, j)
                maxMat[i][j] = Math.max(matrix[i][j], 
                                        Math.max(maxMat[i+1][j], maxMat[i][j+1]));
            }
        }

        return maxDiff;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard 5x5 Matrix ---");
        int[][] matrix1 = {
            { 1,  2, -1, -4, -20 },
            {-8, -3,  4,  2,   1 },
            { 3,  8,  6,  1,   3 },
            {-4, -1,  1,  7,  -6 },
            { 0,  1, -5,  7,  -8 }
        };
        printMatrix(matrix1);
        System.out.println("Max Value: " + findMaxValue(matrix1)); // Expected: 16 (matrix[2][1] - matrix[1][0] = 8 - (-8) = 16)

        System.out.println("\n--- Test Case 2: Simple 2x2 Matrix ---");
        int[][] matrix2 = {
            { 5,  3 },
            { 2, 10 }
        };
        printMatrix(matrix2);
        System.out.println("Max Value: " + findMaxValue(matrix2)); // Expected: 5 (matrix[1][1] - matrix[0][0] = 10 - 5 = 5)

        System.out.println("\n--- Test Case 3: Matrix too small ---");
        int[][] matrix3 = {
            { 1, 2, 3 }
        };
        printMatrix(matrix3);
        System.out.println("Max Value: " + findMaxValue(matrix3)); // Expected: -2147483648 (Integer.MIN_VALUE)
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
