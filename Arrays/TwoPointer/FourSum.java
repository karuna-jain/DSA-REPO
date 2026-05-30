import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// LeetCode 18: 4Sum
// Time Complexity: O(N^3) where N is the number of elements in the array
// Space Complexity: O(log N) to O(N) for sorting depending on implementation

public class FourSum {

    /**
     * Finds all unique quadruplets in the array that sum up to target.
     * @param nums The input array
     * @param target The target sum
     * @return List of unique quadruplets
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        // Sort the array first
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Avoid duplicate quadruplets for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // Avoid duplicate quadruplets for the second element
                if (j > j + 1 - 1 && nums[j] == nums[j - 1] && j > i + 1) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Avoid duplicate third element
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // Avoid duplicate fourth element
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println("Input array: " + Arrays.toString(nums) + ", Target: " + target);
        
        List<List<Integer>> result = fourSum(nums, target);
        System.out.println("Quadruplets that sum to " + target + ": " + result);
    }
}
