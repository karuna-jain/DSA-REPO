import java.util.Arrays;

// Time Complexity: O(N) - Two linear passes (one to find candidate, one to verify).
// Space Complexity: O(1) auxiliary space.

public class MajorityElement {

    /**
     * Finds the majority element in the array using Boyer-Moore Voting Algorithm.
     * A majority element is an element that appears more than N/2 times.
     * If no such element exists, returns -1.
     * 
     * @param nums The input array.
     * @return The majority element if it exists; otherwise -1.
     */
    public static int findMajorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Phase 1: Find a candidate
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Phase 2: Verify the candidate
        int verificationCount = 0;
        for (int num : nums) {
            if (num == candidate) {
                verificationCount++;
            }
        }

        // Majority element must appear more than N/2 times
        if (verificationCount > nums.length / 2) {
            return candidate;
        }

        return -1; // No majority element found
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Majority Element Exists ---");
        int[] test1 = {3, 3, 4, 2, 4, 4, 2, 4, 4}; // N = 9, 4 appears 5 times (5 > 4.5)
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Result: " + findMajorityElement(test1)); // Expected: 4

        System.out.println("\n--- Test Case 2: No Majority Element ---");
        int[] test2 = {3, 3, 4, 2, 4, 4, 2, 4}; // N = 8, 4 appears 4 times (not > 4)
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Result: " + findMajorityElement(test2)); // Expected: -1

        System.out.println("\n--- Test Case 3: Single Element Array ---");
        int[] test3 = {10}; // N = 1, 10 appears 1 time (1 > 0.5)
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Result: " + findMajorityElement(test3)); // Expected: 10

        System.out.println("\n--- Test Case 4: Equal Frequency ---");
        int[] test4 = {1, 2, 1, 2, 1, 2}; // N = 6, no element appears > 3 times
        System.out.println("Input:  " + Arrays.toString(test4));
        System.out.println("Result: " + findMajorityElement(test4)); // Expected: -1
    }
}
