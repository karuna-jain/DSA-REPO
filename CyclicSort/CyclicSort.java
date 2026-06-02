// LeetCode Practice: Standard Cyclic Sort
// Time Complexity: O(N) - In each step, we either place an element at its correct index or move the pointer forward. Max 2N operations.
// Space Complexity: O(1) auxiliary space (in-place)

import java.util.Arrays;

public class CyclicSort {

    /**
     * Sorts an array containing numbers from 1 to N in-place using Cyclic Sort.
     * @param nums The input array of size N containing numbers from 1 to N
     */
    public static void cyclicSort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            // If the element is within range and not already at its correct index, swap
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Unsorted 1 to 5 ---");
        int[] test1 = {3, 1, 5, 4, 2};
        System.out.println("Before: " + Arrays.toString(test1));
        cyclicSort(test1);
        System.out.println("After:  " + Arrays.toString(test1)); // Expected: [1, 2, 3, 4, 5]

        System.out.println("\n--- Test Case 2: Reverse Sorted ---");
        int[] test2 = {5, 4, 3, 2, 1};
        System.out.println("Before: " + Arrays.toString(test2));
        cyclicSort(test2);
        System.out.println("After:  " + Arrays.toString(test2)); // Expected: [1, 2, 3, 4, 5]

        System.out.println("\n--- Test Case 3: Already Sorted ---");
        int[] test3 = {1, 2, 3, 4, 6, 5};
        System.out.println("Before: " + Arrays.toString(test3));
        cyclicSort(test3);
        System.out.println("After:  " + Arrays.toString(test3)); // Expected: [1, 2, 3, 4, 5, 6]

        System.out.println("\n--- Test Case 4: Single Element ---");
        int[] test4 = {1};
        System.out.println("Before: " + Arrays.toString(test4));
        cyclicSort(test4);
        System.out.println("After:  " + Arrays.toString(test4)); // Expected: [1]
    }
}
