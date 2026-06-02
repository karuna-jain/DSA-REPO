// LeetCode 448: Find All Numbers Disappeared in an Array
// Time Complexity: O(N) - One pass to cyclic sort and another to find the missing numbers.
// Space Complexity: O(1) auxiliary space (excluding result list)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllMissingNumbers {

    /**
     * Finds all numbers in the range [1, N] that do not appear in the array.
     * @param nums The input array of size N
     * @return A list of missing numbers
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
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

        List<Integer> missingNumbers = new ArrayList<>();
        // Any index j where nums[j] != j + 1 is a missing number (j + 1)
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                missingNumbers.add(j + 1);
            }
        }

        return missingNumbers;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Multiple Missing Numbers ---");
        int[] test1 = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Result: " + findDisappearedNumbers(test1)); // Expected: [5, 6]

        System.out.println("\n--- Test Case 2: One Missing Number ---");
        int[] test2 = {1, 1};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Result: " + findDisappearedNumbers(test2)); // Expected: [2]

        System.out.println("\n--- Test Case 3: No Missing Numbers ---");
        int[] test3 = {1, 2, 3, 4};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Result: " + findDisappearedNumbers(test3)); // Expected: []
    }
}
