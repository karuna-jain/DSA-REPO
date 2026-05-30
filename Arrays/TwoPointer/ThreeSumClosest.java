import java.util.Arrays;

// LeetCode 16: 3Sum Closest
// Time Complexity: O(N^2) where N is the number of elements in the array
// Space Complexity: O(log N) to O(N) for sorting depending on implementation

public class ThreeSumClosest {

    /**
     * Finds three integers in the array such that the sum is closest to target.
     * @param nums The input array
     * @param target The target sum
     * @return The sum of the three integers
     */
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Array must have at least 3 elements.");
        }

        // Sort the array first
        Arrays.sort(nums);
        
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If we found exact sum, return it immediately
                if (currentSum == target) {
                    return target;
                }
                
                // Update closest sum if current one is closer to target
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                }
                
                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        System.out.println("Input array: " + Arrays.toString(nums) + ", Target: " + target);
        
        int result = threeSumClosest(nums, target);
        System.out.println("Sum closest to target: " + result); // Expected: 2 ((-1) + 2 + 1 = 2)
    }
}
