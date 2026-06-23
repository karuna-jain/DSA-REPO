import java.util.Arrays;

// Time Complexity: O(N) - Single pass through the array.
// Space Complexity: O(1) auxiliary space - Space optimized to two state variables.

public class MaxSumNonAdjacent {

    /**
     * Calculates the maximum sum of elements such that no two elements are adjacent.
     * This is a classic Dynamic Programming problem (equivalent to House Robber).
     * 
     * @param arr The input array containing non-negative numbers.
     * @return The maximum subsequence sum.
     */
    public static int findMaxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        // 'incl' keeps track of the max sum including the current element.
        // 'excl' keeps track of the max sum excluding the current element.
        int incl = arr[0];
        int excl = 0;

        for (int i = 1; i < arr.length; i++) {
            // Max sum excluding current element is the max of (incl_prev, excl_prev)
            int newExcl = Math.max(incl, excl);

            // Max sum including current element is excl_prev + current element
            incl = excl + arr[i];
            excl = newExcl;
        }

        // Return the max of including and excluding the last element
        return Math.max(incl, excl);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] test1 = {5, 5, 10, 100, 10, 5};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Result: " + findMaxSum(test1)); // Expected: 110 (5 + 100 + 5)

        System.out.println("\n--- Test Case 2: Sorted Incremental ---");
        int[] test2 = {3, 2, 7, 10};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Result: " + findMaxSum(test2)); // Expected: 13 (3 + 10)

        System.out.println("\n--- Test Case 3: Empty and Single Element ---");
        int[] test3_empty = {};
        int[] test3_single = {42};
        System.out.println("Empty input result: " + findMaxSum(test3_empty));   // Expected: 0
        System.out.println("Single element result: " + findMaxSum(test3_single)); // Expected: 42

        System.out.println("\n--- Test Case 4: Alternating Peak Case ---");
        int[] test4 = {1, 20, 3};
        System.out.println("Input:  " + Arrays.toString(test4));
        System.out.println("Result: " + findMaxSum(test4)); // Expected: 20
    }
}
