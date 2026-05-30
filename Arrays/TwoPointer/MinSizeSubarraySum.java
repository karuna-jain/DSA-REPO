import java.util.Arrays;

// LeetCode 209: Minimum Size Subarray Sum
// Time Complexity: O(N) where N is the number of elements in the array
// Space Complexity: O(1) auxiliary space

public class MinSizeSubarraySum {

    /**
     * Finds the minimal length of a contiguous subarray of which the sum is >= target.
     * @param target The target sum
     * @param nums The input array of positive integers
     * @return Minimal length of the subarray, or 0 if no such subarray exists
     */
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            // Try to shrink the window from the left as long as the sum is >= target
            while (sum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println("Input array: " + Arrays.toString(nums) + ", Target: " + target);
        
        int result = minSubArrayLen(target, nums);
        System.out.println("Minimal length of subarray: " + result); // Expected: 2 ([4, 3])
    }
}
