// LeetCode 162: Find Peak Element
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class FindPeakElement {

    /**
     * Finds a peak element in an array and returns its index.
     * A peak element is an element that is strictly greater than its neighbors.
     * @param nums The input array
     * @return The index of one of the peak elements
     */
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid is less than its right neighbor, we are on an ascending slope,
            // so a peak must exist to the right.
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } 
            // Otherwise, we are on a descending slope, so a peak must exist 
            // at mid or to the left of mid.
            else {
                high = mid;
            }
        }

        // 'low' and 'high' converge to the peak element index.
        return low;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Input array: [1, 2, 3, 1]");
        System.out.println("Expected peak index: 2 (value 3)");
        System.out.println("Actual peak index: " + findPeakElement(nums1));
        System.out.println();

        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Input array: [1, 2, 1, 3, 5, 6, 4]");
        System.out.println("Expected peak index: 1 (value 2) or 5 (value 6)");
        System.out.println("Actual peak index: " + findPeakElement(nums2));
    }
}
