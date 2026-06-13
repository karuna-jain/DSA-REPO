// LeetCode 33: Search in Rotated Sorted Array
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class SearchRotatedArray {

    /**
     * Searches for a target value in a rotated sorted array.
     * @param nums The rotated sorted array
     * @param target The target value
     * @return The index of target if found, otherwise -1
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if the left half is sorted
            if (nums[low] <= nums[mid]) {
                // If target lies within the sorted left half range
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } 
            // Otherwise, the right half must be sorted
            else {
                // If target lies within the sorted right half range
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println("Input array: [4, 5, 6, 7, 0, 1, 2]");

        int target1 = 0;
        System.out.println("Target: 0");
        System.out.println("Expected: 4, Actual: " + search(nums, target1));
        System.out.println();

        int target2 = 3;
        System.out.println("Target: 3");
        System.out.println("Expected: -1, Actual: " + search(nums, target2));
        System.out.println();

        int target3 = 4;
        System.out.println("Target: 4");
        System.out.println("Expected: 0, Actual: " + search(nums, target3));
    }
}
