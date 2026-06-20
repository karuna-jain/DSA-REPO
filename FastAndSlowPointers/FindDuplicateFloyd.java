// LeetCode 287: Find the Duplicate Number
// Time Complexity: O(N) where N is the length of the array
// Space Complexity: O(1) auxiliary space (read-only array, no modifications)

import java.util.Arrays;

public class FindDuplicateFloyd {

    /**
     * Finds the duplicate number in an array containing N + 1 integers in range [1, N]
     * using Floyd's Tortoise and Hare algorithm. This solves the problem without
     * modifying the array and in O(1) auxiliary space.
     * 
     * @param nums The input array of size N + 1 with elements in [1, N]
     * @return The duplicate number
     */
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }

        // Step 1: Detect intersection point in cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Step 2: Find the entrance to the cycle (the duplicate element)
        int ptr1 = nums[0];
        int ptr2 = slow;

        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Simple Duplicate ---");
        int[] test1 = {1, 3, 4, 2, 2};
        System.out.println("Input:      " + Arrays.toString(test1));
        System.out.println("Duplicate:  " + findDuplicate(test1)); // Expected: 2

        System.out.println("\n--- Test Case 2: Duplicate at the Beginning ---");
        int[] test2 = {3, 1, 3, 4, 2};
        System.out.println("Input:      " + Arrays.toString(test2));
        System.out.println("Duplicate:  " + findDuplicate(test2)); // Expected: 3

        System.out.println("\n--- Test Case 3: Multiple occurrences of duplicate ---");
        int[] test3 = {3, 3, 3, 3, 3};
        System.out.println("Input:      " + Arrays.toString(test3));
        System.out.println("Duplicate:  " + findDuplicate(test3)); // Expected: 3
    }
}
