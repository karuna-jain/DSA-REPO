// LeetCode 34: Find First and Last Position of Element in Sorted Array
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class FindRange {

    /**
     * Finds the first and last position of a target value in a sorted array.
     * @param nums The sorted array of integers
     * @param target The target value
     * @return An array of two integers containing the first and last position, or [-1, -1] if not found
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        result[0] = findBound(nums, target, true);
        
        // If target doesn't exist, we don't need to search for the right bound
        if (result[0] != -1) {
            result[1] = findBound(nums, target, false);
        }

        return result;
    }

    // Helper method to find the leftmost or rightmost index of target
    private static int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0;
        int high = nums.length - 1;
        int bound = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    high = mid - 1; // Keep searching left to find first occurrence
                } else {
                    low = mid + 1;  // Keep searching right to find last occurrence
                }
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};

        System.out.println("Input array: [5, 7, 7, 8, 8, 10]");

        int[] range1 = searchRange(nums, 8);
        System.out.println("Target: 8");
        System.out.println("Expected: [3, 4], Actual: [" + range1[0] + ", " + range1[1] + "]");
        System.out.println();

        int[] range2 = searchRange(nums, 6);
        System.out.println("Target: 6");
        System.out.println("Expected: [-1, -1], Actual: [" + range2[0] + ", " + range2[1] + "]");
        System.out.println();

        int[] range3 = searchRange(new int[]{}, 0);
        System.out.println("Target: 0 in empty array");
        System.out.println("Expected: [-1, -1], Actual: [" + range3[0] + ", " + range3[1] + "]");
    }
}
