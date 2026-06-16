// Problem: Rotate matrix by 90 degrees clockwise in-place
// Time Complexity: O(N^2) where N is the dimension of the square matrix.
// Space Complexity: O(1) auxiliary space (in-place modification).

import java.util.Arrays;

public class RotateMatrix {

    /**
     * Rotates an N x N square matrix by 90 degrees clockwise in-place.
     * 
     * @param matrix The N x N square matrix to be rotated
     */
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return;
        }

        int N = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: 3x3 Matrix ---");
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix1);
        rotate(matrix1);
        System.out.println("\nRotated Matrix (90 deg Clockwise):");
        printMatrix(matrix1);
        // Expected:
        // [7, 4, 1]
        // [8, 5, 2]
        // [9, 6, 3]

        System.out.println("\n--- Test Case 2: 4x4 Matrix ---");
        int[][] matrix2 = {
            { 5,  1,  9, 11},
            { 2,  4,  8, 10},
            {13,  3,  6,  7},
            {15, 14, 12, 16}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix2);
        rotate(matrix2);
        System.out.println("\nRotated Matrix (90 deg Clockwise):");
        printMatrix(matrix2);
        // Expected:
        // [15, 13, 2, 5]
        // [14, 3, 4, 1]
        // [12, 6, 8, 9]
        // [16, 7, 10, 11]
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
