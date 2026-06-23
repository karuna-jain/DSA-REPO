import java.util.Arrays;

// Time Complexity: O(N) in worst case (when target is not found or elements are close), 
// but is much faster than O(N) linear search on average due to index jumping.
// Space Complexity: O(1) auxiliary space.

public class SearchAdjacentDifferByK {

    /**
     * Searches for a key in an array where adjacent elements differ by at most k.
     * Instead of scanning one-by-one, we jump by the minimum possible distance 
     * to reach the key from the current value.
     * 
     * @param arr The input array
     * @param k   The maximum difference between adjacent elements
     * @param x   The target key to find
     * @return The first index of the key if found, otherwise -1.
     */
    public static int search(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return -1;
        }

        int n = arr.length;
        int i = 0;

        while (i < n) {
            // If key is found at current index
            if (arr[i] == x) {
                return i;
            }

            // Find the minimum distance we can jump.
            // Since adjacent elements differ by at most k, the element x
            // must be at least abs(arr[i] - x) / k steps away.
            // We use Math.max(1, ...) to make sure we make progress of at least 1 step.
            int jump = Math.abs(arr[i] - x) / k;
            i += Math.max(1, jump);
        }

        return -1; // Element not found
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] arr1 = {4, 5, 6, 7, 6, 5, 6, 7, 8, 9, 8};
        int k1 = 1;
        int target1 = 9;
        System.out.println("Array: " + Arrays.toString(arr1) + ", k: " + k1 + ", Target: " + target1);
        System.out.println("Found at index: " + search(arr1, k1, target1)); // Expected: 9

        System.out.println("\n--- Test Case 2: Adjacent differ by at most 2 ---");
        int[] arr2 = {20, 40, 50, 70, 70, 60, 80};
        int k2 = 20;
        int target2 = 60;
        System.out.println("Array: " + Arrays.toString(arr2) + ", k: " + k2 + ", Target: " + target2);
        System.out.println("Found at index: " + search(arr2, k2, target2)); // Expected: 5

        System.out.println("\n--- Test Case 3: Target not present ---");
        int[] arr3 = {2, 4, 5, 7, 7, 6};
        int k3 = 2;
        int target3 = 10;
        System.out.println("Array: " + Arrays.toString(arr3) + ", k: " + k3 + ", Target: " + target3);
        System.out.println("Found at index: " + search(arr3, k3, target3)); // Expected: -1
    }
}
