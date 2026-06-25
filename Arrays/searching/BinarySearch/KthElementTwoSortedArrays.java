/**
 * Problem: K-th Element of Two Sorted Arrays
 * 
 * Description:
 * Given two sorted arrays arr1 and arr2 of size M and N respectively, and an integer K,
 * find the element that would be at the k-th position if the two arrays were merged in sorted order.
 * 
 * Time Complexity:
 *   - O(log(min(M, N))) where M and N are the sizes of the two arrays.
 *   - By binary searching on the smaller array, we minimize the search space.
 * Space Complexity:
 *   - O(1) auxiliary space.
 */
public class KthElementTwoSortedArrays {

    /**
     * Finds the K-th element (1-based index) of two sorted arrays.
     * @param arr1 First sorted array
     * @param arr2 Second sorted array
     * @param k The 1-based index of the element to find
     * @return The K-th element
     */
    public static int findKthElement(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }

        int m = arr1.length;
        int n = arr2.length;

        if (k < 1 || k > m + n) {
            throw new IllegalArgumentException("K is out of bounds");
        }

        // Always perform binary search on the smaller array to ensure O(log(min(M, N))) complexity
        if (m > n) {
            return findKthElement(arr2, arr1, k);
        }

        int low = Math.max(0, k - n); // Minimum elements we must pick from arr1
        int high = Math.min(k, m);    // Maximum elements we can pick from arr1

        while (low <= high) {
            int cut1 = low + (high - low) / 2; // Partition index in arr1
            int cut2 = k - cut1;               // Partition index in arr2

            // Elements to the left and right of partition in arr1
            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : arr1[cut1];

            // Elements to the left and right of partition in arr2
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : arr2[cut2];

            // If partition is valid
            if (left1 <= right2 && left2 <= right1) {
                return Math.max(left1, left2);
            } 
            // Too many elements from arr1, search left half
            else if (left1 > right2) {
                high = cut1 - 1;
            } 
            // Too few elements from arr1, search right half
            else {
                low = cut1 + 1;
            }
        }

        return -1; // Should not be reached for valid inputs
    }

    public static void main(String[] args) {
        System.out.println("Running K-th Element of Two Sorted Arrays Tests:\n");

        // Test 1: Standard case
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k1 = 5;
        int ans1 = findKthElement(arr1, arr2, k1);
        System.out.println("Test 1: arr1 = [2, 3, 6, 7, 9], arr2 = [1, 4, 8, 10], k = 5");
        System.out.println("Expected: 6, Actual: " + ans1);
        System.out.println();

        // Test 2: K is at the start
        int k2 = 1;
        int ans2 = findKthElement(arr1, arr2, k2);
        System.out.println("Test 2: Same arrays, k = 1");
        System.out.println("Expected: 1, Actual: " + ans2);
        System.out.println();

        // Test 3: K is at the end
        int k3 = arr1.length + arr2.length;
        int ans3 = findKthElement(arr1, arr2, k3);
        System.out.println("Test 3: Same arrays, k = " + k3);
        System.out.println("Expected: 10, Actual: " + ans3);
        System.out.println();

        // Test 4: One array is completely smaller than the other
        int[] arr3 = {100, 112, 256, 349, 770};
        int[] arr4 = {72, 86, 113, 119, 265, 445, 892};
        int k4 = 7;
        int ans4 = findKthElement(arr3, arr4, k4);
        System.out.println("Test 4: arr3 = [100, 112, 256, 349, 770], arr4 = [72, 86, 113, 119, 265, 445, 892], k = 7");
        // Merged: 72, 86, 100, 112, 113, 119, 256, 265, 349, 445, 770, 892
        // index 0: 72
        // index 1: 86
        // index 2: 100
        // index 3: 112
        // index 4: 113
        // index 5: 119
        // index 6: 256 (this is 7th element)
        System.out.println("Expected: 256, Actual: " + ans4);
        System.out.println();
    }
}
