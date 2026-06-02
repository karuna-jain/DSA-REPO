// LeetCode 645: Set Mismatch (Find Duplicate and Missing Number)
// Time Complexity: O(N) - One pass to cyclic sort and another to find the error numbers.
// Space Complexity: O(1) auxiliary space (in-place modification)

import java.util.Arrays;

public class FindDuplicateAndMissing {

    /**
     * Finds the duplicate and the missing number in an array representing the set [1, N].
     * @param nums The input array
     * @return An array of size 2 containing [duplicate, missing]
     */
    public static int[] findErrorNums(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            // Place each element at its correct index (nums[i] goes to index nums[i]-1)
            // If the element is not at the correct index and the element at correctIndex is different, swap
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        // Find the index j where nums[j] != j + 1
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return new int[] {nums[j], j + 1}; // [duplicate, missing]
            }
        }

        return new int[] {-1, -1};
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Duplicate 2, Missing 3 ---");
        int[] test1 = {1, 2, 2, 4};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Result: " + Arrays.toString(findErrorNums(test1))); // Expected: [2, 3]

        System.out.println("\n--- Test Case 2: Duplicate 1, Missing 2 ---");
        int[] test2 = {1, 1};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Result: " + Arrays.toString(findErrorNums(test2))); // Expected: [1, 2]

        System.out.println("\n--- Test Case 3: Duplicate 3, Missing 1 ---");
        int[] test3 = {3, 2, 3, 4};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Result: " + Arrays.toString(findErrorNums(test3))); // Expected: [3, 1]
    }
}
