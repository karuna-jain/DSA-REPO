// LeetCode 704: Binary Search
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class BinarySearch {

    /**
     * Searches for a target value in a sorted integer array.
     * @param nums The sorted array
     * @param target The target value to search for
     * @return The 0-based index of the target if found, otherwise -1
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            // Avoid overflow compared to (low + high) / 2
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println("Input: nums = [-1, 0, 3, 5, 9, 12], target = 9");
        System.out.println("Expected output: 4");
        System.out.println("Actual output: " + search(nums1, target1));
        System.out.println();

        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        System.out.println("Input: nums = [-1, 0, 3, 5, 9, 12], target = 2");
        System.out.println("Expected output: -1");
        System.out.println("Actual output: " + search(nums2, target2));
        System.out.println();
        
        int[] nums3 = {5};
        int target3 = 5;
        System.out.println("Input: nums = [5], target = 5");
        System.out.println("Expected output: 0");
        System.out.println("Actual output: " + search(nums3, target3));
    }
}
