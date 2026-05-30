import java.util.Arrays;

// LeetCode 259: Triplets with Smaller Sum (3Sum Smaller)
// Time Complexity: O(N^2) where N is the number of elements in the array
// Space Complexity: O(log N) to O(N) for sorting depending on implementation

public class ThreeSumSmaller {

    /**
     * Counts the number of index triplets (i, j, k) with i < j < k such that 
     * nums[i] + nums[j] + nums[k] < target.
     * @param nums The input array
     * @param target The target value
     * @return Number of such triplets
     */
    public static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        // Sort the array first
        Arrays.sort(nums);
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // If sum is smaller than target, then all elements between left and right
                // are also valid options for the third element (since the array is sorted,
                // any element between left and right is <= nums[right], hence sum remains < target).
                if (sum < target) {
                    count += (right - left);
                    left++; // Move left pointer to search for larger values
                } else {
                    right--; // Move right pointer to shrink sum
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 1, 3};
        int target = 2;
        System.out.println("Input array: " + Arrays.toString(nums) + ", Target: " + target);
        
        int result = threeSumSmaller(nums, target);
        System.out.println("Number of triplets with sum < target: " + result); // Expected: 2 ([-2, 0, 1], [-2, 0, 3])
    }
}
