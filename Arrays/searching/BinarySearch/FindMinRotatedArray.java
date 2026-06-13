// LeetCode 153: Find Minimum in Rotated Sorted Array
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class FindMinRotatedArray {

    /**
     * Finds the minimum element in a rotated sorted array of unique integers.
     * @param nums The rotated sorted array
     * @return The minimum element
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is greater than high element, the minimum must be in the right half
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } 
            // Otherwise, the minimum is either at mid or in the left half
            else {
                high = mid;
            }
        }

        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Input array: [3, 4, 5, 1, 2]");
        System.out.println("Expected: 1, Actual: " + findMin(nums1));
        System.out.println();

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Input array: [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Expected: 0, Actual: " + findMin(nums2));
        System.out.println();

        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Input array: [11, 13, 15, 17] (not rotated)");
        System.out.println("Expected: 11, Actual: " + findMin(nums3));
    }
}
