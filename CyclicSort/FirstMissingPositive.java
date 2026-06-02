// LeetCode 41: First Missing Positive
// Time Complexity: O(N) - In each step, we either place a number in its correct position or move the pointer forward.
// Space Complexity: O(1) auxiliary space (in-place modification)

import java.util.Arrays;

public class FirstMissingPositive {

    /**
     * Finds the smallest missing positive integer in an unsorted array.
     * @param nums The input array
     * @return The first missing positive integer
     */
    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            // Cyclic Sort Conditions:
            // 1. Value must be positive (nums[i] > 0)
            // 2. Value must be within range of indices (nums[i] <= nums.length)
            // 3. Value must not already be in its correct place (nums[i] != nums[correctIndex])
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        // Search for the first index where nums[j] != j + 1
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }

        // If all positive numbers from 1 to N are in their correct indices, then N + 1 is the smallest missing positive
        return nums.length + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Positive and Negative Numbers ---");
        int[] test1 = {1, 2, 0};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Smallest Missing Positive: " + firstMissingPositive(test1)); // Expected: 3

        System.out.println("\n--- Test Case 2: Unsorted Positive Numbers ---");
        int[] test2 = {3, 4, -1, 1};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Smallest Missing Positive: " + firstMissingPositive(test2)); // Expected: 2

        System.out.println("\n--- Test Case 3: Out-of-Bound Numbers ---");
        int[] test3 = {7, 8, 9, 11, 12};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Smallest Missing Positive: " + firstMissingPositive(test3)); // Expected: 1
    }
}
