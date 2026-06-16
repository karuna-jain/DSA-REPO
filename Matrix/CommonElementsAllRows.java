// Problem: Common elements in all rows of a given matrix
// Time Complexity: O(R * C) where R is the number of rows, C is the number of columns.
// Space Complexity: O(C) auxiliary space to store unique elements of the first row in a HashMap.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonElementsAllRows {

    /**
     * Finds all unique common elements present in all rows of the given matrix.
     * 
     * @param matrix The 2D matrix
     * @return A list of elements present in all rows
     */
    public static List<Integer> findCommonElements(int[][] matrix) {
        List<Integer> commonElements = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return commonElements;
        }

        int R = matrix.length;
        int C = matrix[0].length;
        Map<Integer, Integer> elementCounts = new HashMap<>();

        // Initialize elementCounts map with the elements of the first row
        for (int j = 0; j < C; j++) {
            elementCounts.put(matrix[0][j], 1);
        }

        // Traverse the remaining rows of the matrix
        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int val = matrix[i][j];
                // If the element is in the map and its count matches the current row index,
                // it means this is the first time we've seen it in the current row,
                // and it was present in all previous rows.
                if (elementCounts.containsKey(val) && elementCounts.get(val) == i) {
                    elementCounts.put(val, i + 1);
                }
            }
        }

        // Collect all elements that have a count equal to R (number of rows)
        for (Map.Entry<Integer, Integer> entry : elementCounts.entrySet()) {
            if (entry.getValue() == R) {
                commonElements.add(entry.getKey());
            }
        }

        return commonElements;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Matrix ---");
        int[][] matrix1 = {
            {1, 2, 1, 4, 8},
            {3, 7, 8, 5, 1},
            {8, 7, 7, 3, 1},
            {8, 1, 2, 7, 9}
        };
        printMatrix(matrix1);
        System.out.println("Common Elements: " + findCommonElements(matrix1)); // Expected: [1, 8] (order may vary)

        System.out.println("\n--- Test Case 2: All Rows Same ---");
        int[][] matrix2 = {
            {1, 2, 3},
            {3, 2, 1},
            {2, 3, 1}
        };
        printMatrix(matrix2);
        System.out.println("Common Elements: " + findCommonElements(matrix2)); // Expected: [1, 2, 3]

        System.out.println("\n--- Test Case 3: No Common Elements ---");
        int[][] matrix3 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        printMatrix(matrix3);
        System.out.println("Common Elements: " + findCommonElements(matrix3)); // Expected: []
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
