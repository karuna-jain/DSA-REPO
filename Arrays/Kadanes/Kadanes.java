// LeetCode 53: Maximum Subarray (Kadane's Algorithm)
// Time Complexity: O(N) - single pass through the array
// Space Complexity: O(1) auxiliary space - only tracking pointers and sums

import java.util.Arrays;

public class Kadanes {

    /**
     * Finds the contiguous subarray (containing at least one number) which has the
     * largest sum and returns its sum. It also tracks the start and end indices
     * of this subarray to print the actual elements.
     * 
     * @param nums The input array of integers
     * @return The maximum subarray sum
     */
    public static int maxSubarraySum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int currentMax = nums[0];
        
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            // Decide whether to add the current element to the existing subarray
            // or start a new subarray from the current element
            if (nums[i] > currentMax + nums[i]) {
                currentMax = nums[i];
                tempStart = i;
            } else {
                currentMax = currentMax + nums[i];
            }

            // Update the global maximum and the indices of the best subarray found so far
            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
                start = tempStart;
                end = i;
            }
        }

        // Print the actual subarray for demonstration
        System.out.print("Max Subarray: [");
        for (int i = start; i <= end; i++) {
            System.out.print(nums[i] + (i < end ? ", " : ""));
        }
        System.out.println("]");

        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case with Mixed Signs ---");
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input:      " + Arrays.toString(test1));
        int sum1 = maxSubarraySum(test1);
        System.out.println("Max Sum:    " + sum1); // Expected: 6 (Subarray: [4, -1, 2, 1])

        System.out.println("\n--- Test Case 2: All Negative Elements ---");
        int[] test2 = {-5, -1, -8, -3, -4};
        System.out.println("Input:      " + Arrays.toString(test2));
        int sum2 = maxSubarraySum(test2);
        System.out.println("Max Sum:    " + sum2); // Expected: -1 (Subarray: [-1])

        System.out.println("\n--- Test Case 3: Single Element ---");
        int[] test3 = {42};
        System.out.println("Input:      " + Arrays.toString(test3));
        int sum3 = maxSubarraySum(test3);
        System.out.println("Max Sum:    " + sum3); // Expected: 42 (Subarray: [42])

        System.out.println("\n--- Test Case 4: Alternating Signs ---");
        int[] test4 = {1, -1, 1, -1, 1, -1, 1};
        System.out.println("Input:      " + Arrays.toString(test4));
        int sum4 = maxSubarraySum(test4);
        System.out.println("Max Sum:    " + sum4); // Expected: 1 (Subarray: [1])
    }
}
