// Problem: Spiral Traversal on a Matrix (LeetCode 54: Spiral Matrix)
// Time Complexity: O(M * N) - Every element is visited exactly once.
// Space Complexity: O(1) auxiliary space (excluding the output list).

import java.util.ArrayList;
import java.util.List;

public class SpiralTraversal {

    /**
     * Traverses a 2D matrix in spiral order.
     * 
     * @param matrix The 2D matrix
     * @return List of integers in spiral order
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = cols - 1;

        while (top <= bottom && left <= right) {
            // 1. Traverse from left to right across the top row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // Move the top boundary down

            // 2. Traverse from top to bottom down the right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Move the right boundary left

            // 3. Traverse from right to left across the bottom row (if still within bounds)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // Move the bottom boundary up
            }

            // 4. Traverse from bottom to top up the left column (if still within bounds)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Move the left boundary right
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: 3x3 Standard Matrix ---");
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        printMatrix(matrix1);
        System.out.println("Spiral Order: " + spiralOrder(matrix1));
        // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        System.out.println("\n--- Test Case 2: 3x4 Rectangular Matrix ---");
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        printMatrix(matrix2);
        System.out.println("Spiral Order: " + spiralOrder(matrix2));
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

        System.out.println("\n--- Test Case 3: Single Row Matrix ---");
        int[][] matrix3 = {
            {1, 2, 3, 4}
        };
        printMatrix(matrix3);
        System.out.println("Spiral Order: " + spiralOrder(matrix3));
        // Expected: [1, 2, 3, 4]

        System.out.println("\n--- Test Case 4: Single Column Matrix ---");
        int[][] matrix4 = {
            {1},
            {2},
            {3},
            {4}
        };
        printMatrix(matrix4);
        System.out.println("Spiral Order: " + spiralOrder(matrix4));
        // Expected: [1, 2, 3, 4]
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
