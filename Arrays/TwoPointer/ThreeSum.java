import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// LeetCode 15: 3Sum
// Time Complexity: O(N^2) where N is the number of elements in the array
// Space Complexity: O(log N) to O(N) for sorting depending on implementation

public class ThreeSum {

    /**
     * Finds all unique triplets in the array that sum up to zero.
     * @param nums The input array
     * @return List of unique triplets
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        // Sort the array first
        Arrays.sort(nums);
        
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // Avoid duplicate triplets by skipping duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int target = -nums[i];
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for the second element
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // Skip duplicates for the third element
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
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("Input array: " + Arrays.toString(nums));
        
        List<List<Integer>> triplets = threeSum(nums);
        System.out.println("Triplets that sum to 0: " + triplets);
    }
}
