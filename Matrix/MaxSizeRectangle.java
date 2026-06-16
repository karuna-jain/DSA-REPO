// Problem: Maximum size rectangle binary submatrix with all 1's
// Time Complexity: O(R * C) where R is the number of rows, C is the number of columns.
// Space Complexity: O(C) auxiliary space for the histogram heights array and stack.

import java.util.Arrays;
import java.util.Stack;

public class MaxSizeRectangle {

    /**
     * Finds the maximum area of a 2D binary submatrix with all 1's.
     * 
     * @param matrix The 2D binary matrix
     * @return The maximum area of a rectangle of 1's, or 0 if matrix is empty/null.
     */
    public static int maxRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int R = matrix.length;
        int C = matrix[0].length;
        int[] heights = new int[C];
        int maxArea = 0;

        for (int i = 0; i < R; i++) {
            // Update heights array for the current row
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            // Calculate max area for the histogram at the current row
            maxArea = Math.max(maxArea, maxHistogramArea(heights));
        }

        return maxArea;
    }

    /**
     * Calculates the maximum area of a histogram represented by an array of heights.
     */
    private static int maxHistogramArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            // Use 0 as a sentinel height to clear out remaining elements in stack
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Binary Matrix ---");
        int[][] matrix1 = {
            {0, 1, 1, 0},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0}
        };
        printMatrix(matrix1);
        System.out.println("Max Rectangle Area: " + maxRectangle(matrix1)); // Expected: 8 (from index 1,0 to 2,3)

        System.out.println("\n--- Test Case 2: All Zeros ---");
        int[][] matrix2 = {
            {0, 0},
            {0, 0}
        };
        printMatrix(matrix2);
        System.out.println("Max Rectangle Area: " + maxRectangle(matrix2)); // Expected: 0

        System.out.println("\n--- Test Case 3: All Ones ---");
        int[][] matrix3 = {
            {1, 1, 1},
            {1, 1, 1}
        };
        printMatrix(matrix3);
        System.out.println("Max Rectangle Area: " + maxRectangle(matrix3)); // Expected: 6
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
