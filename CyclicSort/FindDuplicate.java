// LeetCode 287: Find the Duplicate Number
// Time Complexity: O(N) - In each step, we either place a number in its correct position or find the duplicate.
// Space Complexity: O(1) auxiliary space (in-place modification)
// Note: LeetCode requires a read-only array solution (which is solved with Floyd's Fast & Slow Pointers).
// This file demonstrates the Cyclic Sort pattern, which modifies the array in-place to find the duplicate.

import java.util.Arrays;

public class FindDuplicate {

    /**
     * Finds the duplicate number in an array containing N + 1 integers in range [1, N].
     * @param nums The input array
     * @return The duplicate number, or -1 if none found
     */
    public static int findDuplicate(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // Check if the current element is in its correct place (nums[i] should be at index nums[i]-1)
            if (nums[i] != i + 1) {
                int correctIndex = nums[i] - 1;
                // If the target position does not have this number, swap them
                if (nums[i] != nums[correctIndex]) {
                    swap(nums, i, correctIndex);
                } else {
                    // If the target position already has the correct number, we found a duplicate!
                    return nums[i];
                }
            } else {
                i++;
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Simple Duplicate ---");
        int[] test1 = {1, 3, 4, 2, 2};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Duplicate: " + findDuplicate(test1)); // Expected: 2

        System.out.println("\n--- Test Case 2: Duplicate at the Beginning ---");
        int[] test2 = {3, 1, 3, 4, 2};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Duplicate: " + findDuplicate(test2)); // Expected: 3

        System.out.println("\n--- Test Case 3: Another Duplicate ---");
        int[] test3 = {3, 3, 3, 3, 3};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Duplicate: " + findDuplicate(test3)); // Expected: 3
    }
}
