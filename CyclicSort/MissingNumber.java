// LeetCode 268: Missing Number
// Time Complexity: O(N) - One pass to cyclic sort and another to find the missing number.
// Space Complexity: O(1) auxiliary space (in-place)

import java.util.Arrays;

public class MissingNumber {

    /**
     * Finds the missing number in the range [0, N] from an array of size N.
     * @param nums The input array of size N
     * @return The missing number
     */
    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int val = nums[i];
            // Since the range is [0, N], a number 'N' cannot be placed at its own index (out of bounds).
            // We ignore it during sorting.
            if (val < nums.length && val != nums[val]) {
                swap(nums, i, val);
            } else {
                i++;
            }
        }

        // Search for the first index 'j' where nums[j] != j
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j) {
                return j;
            }
        }

        // If all numbers from 0 to N-1 are in their correct spots, then N is the missing number
        return nums.length;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Missing 2 in [0, 3] ---");
        int[] test1 = {3, 0, 1};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Result: " + findMissingNumber(test1)); // Expected: 2

        System.out.println("\n--- Test Case 2: Missing 8 in [0, 9] ---");
        int[] test2 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Result: " + findMissingNumber(test2)); // Expected: 8

        System.out.println("\n--- Test Case 3: Missing 0 in [0, 1] ---");
        int[] test3 = {1};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Result: " + findMissingNumber(test3)); // Expected: 0

        System.out.println("\n--- Test Case 4: Missing N (all correct so far) ---");
        int[] test4 = {0, 1, 2};
        System.out.println("Input:  " + Arrays.toString(test4));
        System.out.println("Result: " + findMissingNumber(test4)); // Expected: 3
    }
}
