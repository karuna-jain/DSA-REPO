// LeetCode 4: Median of Two Sorted Arrays
// Time Complexity: O(log(min(M, N))) where M and N are the sizes of the two arrays
// Space Complexity: O(1) auxiliary space

public class MedianTwoSortedArrays {

    /**
     * Finds the median of two sorted arrays.
     * @param nums1 The first sorted array
     * @param nums2 The second sorted array
     * @return The median value
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize binary search time to O(log(min(M, N)))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;

        while (low <= high) {
            int partitionX = low + (high - low) / 2;
            // partitionY is calculated such that left side of both arrays has half elements
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Boundary values if partition is at the extreme ends
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Correct partition found
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If the total size is odd
                if ((m + n) % 2 != 0) {
                    return Math.max(maxLeftX, maxLeftY);
                } 
                // If the total size is even
                else {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                }
            } 
            // Partition is too far right, move left
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            // Partition is too far left, move right
            else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or contain invalid elements");
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("nums1 = [1, 3], nums2 = [2]");
        System.out.println("Expected median: 2.0, Actual: " + findMedianSortedArrays(nums1, nums2));
        System.out.println();

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("nums1 = [1, 2], nums2 = [3, 4]");
        System.out.println("Expected median: 2.5, Actual: " + findMedianSortedArrays(nums3, nums4));
        System.out.println();

        int[] nums5 = {0, 0};
        int[] nums6 = {0, 0};
        System.out.println("nums1 = [0, 0], nums2 = [0, 0]");
        System.out.println("Expected median: 0.0, Actual: " + findMedianSortedArrays(nums5, nums6));
    }
}
