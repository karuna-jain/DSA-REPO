// LeetCode 74: Search a 2D Matrix
// Time Complexity: O(log(M * N)) where M is the number of rows and N is the number of columns
// Space Complexity: O(1) auxiliary space

public class Search2DMatrix {

    /**
     * Searches for a target value in an M x N matrix.
     * @param matrix The sorted 2D matrix
     * @param target The target value
     * @return True if target exists, otherwise false
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Map virtual 1D index to 2D coordinates
            int row = mid / n;
            int col = mid % n;
            int val = matrix[row][col];

            if (val == target) {
                return true;
            } else if (val < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
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

        System.out.println("Input Matrix:");
        for (int[] row : matrix) {
            System.out.print("  [ ");
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("]");
        }
        System.out.println();

        System.out.println("Target: 3");
        System.out.println("Expected: true, Actual: " + searchMatrix(matrix, 3));
        System.out.println();

        System.out.println("Target: 13");
        System.out.println("Expected: false, Actual: " + searchMatrix(matrix, 13));
        System.out.println();

        System.out.println("Target: 60");
        System.out.println("Expected: true, Actual: " + searchMatrix(matrix, 60));
    }
}
