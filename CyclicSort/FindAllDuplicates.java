// LeetCode 442: Find All Duplicates in an Array
// Time Complexity: O(N) - One pass to cyclic sort and another to gather all duplicates.
// Space Complexity: O(1) auxiliary space (excluding result list)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllDuplicates {

    /**
     * Finds all duplicates in an array where elements are in the range [1, N].
     * @param nums The input array
     * @return A list of duplicate numbers
     */
    public static List<Integer> findDuplicates(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            // Place each number at its correct index (nums[i] goes to index nums[i]-1)
            // If the element is not at the correct index and the element at correctIndex is different, swap
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        List<Integer> duplicates = new ArrayList<>();
        // Any index j where nums[j] != j + 1 indicates that nums[j] is a duplicate
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                duplicates.add(nums[j]);
            }
        }

        return duplicates;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Multiple Duplicates ---");
        int[] test1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Duplicates: " + findDuplicates(test1)); // Expected: [3, 2] (or [2, 3] depending on traversal order)

        System.out.println("\n--- Test Case 2: One Duplicate ---");
        int[] test2 = {1, 1, 2};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Duplicates: " + findDuplicates(test2)); // Expected: [1]

        System.out.println("\n--- Test Case 3: No Duplicates ---");
        int[] test3 = {1, 2, 3, 4};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Duplicates: " + findDuplicates(test3)); // Expected: []
    }
}
