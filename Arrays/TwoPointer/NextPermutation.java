// LeetCode 31: Next Permutation
// Time Complexity: O(N) - single pass to find pivot, successor, and reverse suffix
// Space Complexity: O(1) auxiliary space - in-place modification

import java.util.Arrays;

public class NextPermutation {

    /**
     * Rearranges numbers into the lexicographically next greater permutation of numbers.
     * If such an arrangement is not possible, it rearranges it as the lowest possible order
     * (i.e., sorted in ascending order).
     * 
     * @param nums The input array of integers
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // Step 1: Find the first decreasing element from the right (pivot)
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If a pivot is found, find the element just larger than nums[i] from the right
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap the pivot with its successor
            swap(nums, i, j);
        }

        // Step 3: Reverse the suffix starting at i + 1 to get the smallest lexicographical order
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] test1 = {1, 2, 3};
        System.out.println("Input:  " + Arrays.toString(test1));
        nextPermutation(test1);
        System.out.println("Output: " + Arrays.toString(test1)); // Expected: [1, 3, 2]

        System.out.println("\n--- Test Case 2: Descending Order (Wrap Around) ---");
        int[] test2 = {3, 2, 1};
        System.out.println("Input:  " + Arrays.toString(test2));
        nextPermutation(test2);
        System.out.println("Output: " + Arrays.toString(test2)); // Expected: [1, 2, 3]

        System.out.println("\n--- Test Case 3: Duplicate Elements ---");
        int[] test3 = {1, 1, 5};
        System.out.println("Input:  " + Arrays.toString(test3));
        nextPermutation(test3);
        System.out.println("Output: " + Arrays.toString(test3)); // Expected: [1, 5, 1]

        System.out.println("\n--- Test Case 4: Larger Array ---");
        int[] test4 = {1, 5, 8, 4, 7, 6, 5, 3, 1};
        System.out.println("Input:  " + Arrays.toString(test4));
        nextPermutation(test4);
        System.out.println("Output: " + Arrays.toString(test4)); // Expected: [1, 5, 8, 5, 1, 3, 4, 6, 7]
    }
}
