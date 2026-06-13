// LeetCode 81: Search in Rotated Sorted Array II (With Duplicates)
// Time Complexity: O(N) worst case (all elements duplicate), O(log N) average case
// Space Complexity: O(1) auxiliary space

public class SearchRotatedArrayII {

    /**
     * Searches for a target value in a rotated sorted array that may contain duplicates.
     * @param nums The rotated sorted array (can contain duplicates)
     * @param target The target value to search
     * @return True if target exists in the array, otherwise false
     */
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // If left, mid, and right are all duplicate values, we cannot determine 
            // which side is sorted. We shrink the bounds by skipping the duplicate ends.
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            } 
            // Left side is sorted
            else if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } 
            // Right side is sorted
            else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 0, 0, 1, 2};

        System.out.println("Input array: [2, 5, 6, 0, 0, 1, 2] (contains duplicate 2)");

        int target1 = 0;
        System.out.println("Target: 0");
        System.out.println("Expected: true, Actual: " + search(nums, target1));
        System.out.println();

        int target2 = 3;
        System.out.println("Target: 3");
        System.out.println("Expected: false, Actual: " + search(nums, target2));
        System.out.println();

        int[] numsAllDuplicates = {1, 0, 1, 1, 1};
        System.out.println("Input array: [1, 0, 1, 1, 1]");
        int target3 = 0;
        System.out.println("Target: 0");
        System.out.println("Expected: true, Actual: " + search(numsAllDuplicates, target3));
    }
}
