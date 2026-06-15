// Problem: Find Median in a Row-wise Sorted Matrix
// Time Complexity: O(R * log(C) * log(Max - Min)) where R is number of rows, C is number of columns.
// Space Complexity: O(1) auxiliary space.

import java.util.Arrays;

public class MedianRowWiseSorted {

    /**
     * Finds the median of a row-wise sorted matrix.
     * Assumes that the total number of elements (R * C) is odd.
     * 
     * @param matrix The 2D matrix
     * @return The median of the matrix
     */
    public static int findMedian(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }

        int R = matrix.length;
        int C = matrix[0].length;

        // Find the overall minimum and maximum elements in the matrix
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < R; i++) {
            min = Math.min(min, matrix[i][0]);
            max = Math.max(max, matrix[i][C - 1]);
        }

        int desiredCount = (R * C + 1) / 2;

        while (min < max) {
            int mid = min + (max - min) / 2;
            int count = 0;

            // Count the elements less than or equal to mid in each row
            for (int i = 0; i < R; i++) {
                count += countLessOrEqual(matrix[i], mid);
            }

            if (count < desiredCount) {
                min = mid + 1; // Median must be larger
            } else {
                max = mid; // Median is <= mid
            }
        }

        return min;
    }

    /**
     * Helper to perform binary search (upper bound) to find count of elements <= target.
     */
    private static int countLessOrEqual(int[] row, int target) {
        int low = 0;
        int high = row.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (row[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard 3x3 Matrix ---");
        int[][] matrix1 = {
            {1, 3, 5},
            {2, 6, 9},
            {3, 6, 9}
        };
        // Flattened sorted list: [1, 2, 3, 3, 5, 6, 6, 9, 9] -> Median is 5
        printMatrix(matrix1);
        System.out.println("Median: " + findMedian(matrix1)); // Expected: 5

        System.out.println("\n--- Test Case 2: 3x3 Matrix ---");
        int[][] matrix2 = {
            {1, 3, 4},
            {2, 5, 6},
            {7, 8, 9}
        };
        // Flattened sorted list: [1, 2, 3, 4, 5, 6, 7, 8, 9] -> Median is 5
        printMatrix(matrix2);
        System.out.println("Median: " + findMedian(matrix2)); // Expected: 5

        System.out.println("\n--- Test Case 3: Matrix with Duplicates ---");
        int[][] matrix3 = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        printMatrix(matrix3);
        System.out.println("Median: " + findMedian(matrix3)); // Expected: 1
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
