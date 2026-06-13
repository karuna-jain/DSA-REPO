// LeetCode 35: Search Insert Position (Find Ceiling of a Number)
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class SearchInsertPosition {

    /**
     * Finds the index of target if it exists, or the index where it would be inserted.
     * This index represents the ceiling of the number in the array.
     * @param nums The sorted array of distinct integers
     * @param target The target value
     * @return The insertion index (ceiling index)
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // At the end of the loop, 'low' will point to the smallest element 
        // that is greater than 'target' (i.e. the insertion position or ceiling).
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};

        System.out.println("Input array: [1, 3, 5, 6]");

        System.out.println("Target: 5 (exists)");
        System.out.println("Expected: 2, Actual: " + searchInsert(nums, 5));
        System.out.println();

        System.out.println("Target: 2 (does not exist, inserted between 1 and 3)");
        System.out.println("Expected: 1, Actual: " + searchInsert(nums, 2));
        System.out.println();

        System.out.println("Target: 7 (does not exist, inserted at the end)");
        System.out.println("Expected: 4, Actual: " + searchInsert(nums, 7));
        System.out.println();

        System.out.println("Target: 0 (does not exist, inserted at the beginning)");
        System.out.println("Expected: 0, Actual: " + searchInsert(nums, 0));
    }
}
