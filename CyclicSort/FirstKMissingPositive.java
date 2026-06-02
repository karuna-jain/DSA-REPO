// Grokking/Interview Problem: First K Missing Positive Numbers
// Time Complexity: O(N + K) - One pass to cyclic sort, one pass to scan for missing numbers, and a loop to find remaining K numbers.
// Space Complexity: O(D) where D is the number of out-of-bound or duplicate elements stored in the seen set (max O(N)).

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstKMissingPositive {

    /**
     * Finds the first K missing positive integers in an unsorted array.
     * @param nums The input array
     * @param k The number of missing positive integers to find
     * @return A list of the first K missing positive integers
     */
    public static List<Integer> findKMissingPositive(int[] nums, int k) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            // Put each positive integer within [1, N] to its correct index (nums[i] - 1)
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        Set<Integer> extraNumbers = new HashSet<>();

        // 1. Find missing numbers that are within range [1, N]
        for (int j = 0; j < nums.length && missingNumbers.size() < k; j++) {
            if (nums[j] != j + 1) {
                missingNumbers.add(j + 1);
                // Track numbers that are present in the array but in incorrect spots
                extraNumbers.add(nums[j]);
            }
        }

        // 2. Find missing numbers beyond range [1, N]
        int candidate = nums.length + 1;
        while (missingNumbers.size() < k) {
            // Only add candidate if it was not present in the original array (meaning not in extraNumbers)
            if (!extraNumbers.contains(candidate)) {
                missingNumbers.add(candidate);
            }
            candidate++;
        }

        return missingNumbers;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Simple Missing and Extra Numbers ---");
        int[] test1 = {3, -1, 4, 5, 5};
        int k1 = 3;
        System.out.println("Input:  " + Arrays.toString(test1) + ", K: " + k1);
        System.out.println("Result: " + findKMissingPositive(test1, k1)); // Expected: [1, 2, 6]

        System.out.println("\n--- Test Case 2: Out of Bound Values ---");
        int[] test2 = {2, 3, 4};
        int k2 = 3;
        System.out.println("Input:  " + Arrays.toString(test2) + ", K: " + k2);
        System.out.println("Result: " + findKMissingPositive(test2, k2)); // Expected: [1, 5, 6]

        System.out.println("\n--- Test Case 3: Negative and Duplicate Values ---");
        int[] test3 = {-2, -3, 4, 4};
        int k3 = 2;
        System.out.println("Input:  " + Arrays.toString(test3) + ", K: " + k3);
        System.out.println("Result: " + findKMissingPositive(test3, k3)); // Expected: [1, 2]
    }
}
