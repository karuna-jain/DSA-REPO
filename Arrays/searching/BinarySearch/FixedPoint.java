// GeeksforGeeks: Find a Fixed Point (Value equal to index) in a given array
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class FixedPoint {

    /**
     * Finds a fixed point (an index i such that arr[i] == i) in a sorted array of distinct integers.
     * @param arr The sorted array of distinct integers
     * @return The fixed point index if found, otherwise -1
     */
    public static int findFixedPoint(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == mid) {
                return mid; // Fixed point found
            } else if (arr[mid] > mid) {
                // Since the elements are distinct and sorted in ascending order,
                // for any index k > mid, we must have arr[k] > k.
                // Thus, the fixed point cannot lie to the right of mid.
                high = mid - 1;
            } else {
                // Similarly, for any index k < mid, we must have arr[k] < k.
                // Thus, the fixed point cannot lie to the left of mid.
                low = mid + 1;
            }
        }

        return -1; // No fixed point found
    }

    public static void main(String[] args) {
        int[] arr1 = {-10, -5, 0, 3, 7};
        System.out.println("Input array: [-10, -5, 0, 3, 7]");
        System.out.println("Expected: 3, Actual: " + findFixedPoint(arr1));
        System.out.println();

        int[] arr2 = {0, 2, 5, 8, 17};
        System.out.println("Input array: [0, 2, 5, 8, 17]");
        System.out.println("Expected: 0, Actual: " + findFixedPoint(arr2));
        System.out.println();

        int[] arr3 = {-10, -5, 3, 4, 7, 9};
        System.out.println("Input array: [-10, -5, 3, 4, 7, 9]");
        System.out.println("Expected: -1, Actual: " + findFixedPoint(arr3));
        System.out.println();

        int[] arr4 = {};
        System.out.println("Input array: []");
        System.out.println("Expected: -1, Actual: " + findFixedPoint(arr4));
    }
}
