// Problem: Count Inversions in an Array
// Time Complexity: O(N log N) - based on Merge Sort
// Space Complexity: O(N) auxiliary space - for the temporary array during merging

import java.util.Arrays;

public class CountInversion {

    /**
     * Counts the number of inversions in the input array.
     * An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
     * To avoid mutating the original input, this method copies the array before counting.
     * 
     * @param arr The input array of integers
     * @return The total number of inversions
     */
    public static long countInversions(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        // Copy the array to preserve original input
        int[] copy = arr.clone();
        return mergeSortAndCount(copy, 0, copy.length - 1);
    }

    private static long mergeSortAndCount(int[] arr, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Count inversions in left half
            count += mergeSortAndCount(arr, left, mid);
            // Count inversions in right half
            count += mergeSortAndCount(arr, mid + 1, right);
            // Count split inversions during merge
            count += mergeAndCount(arr, left, mid, right);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;      // Starting index for left subarray
        int j = mid + 1;   // Starting index for right subarray
        int k = 0;         // Starting index for temp array
        long swaps = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // Since left and right halves are sorted, if arr[i] > arr[j],
                // then all elements from index i to mid in the left half are > arr[j].
                swaps += (mid + 1 - i);
            }
        }

        // Copy remaining elements of left subarray
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements of right subarray
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy merged elements back to original array
        for (i = left; i <= right; i++) {
            arr[i] = temp[i - left];
        }

        return swaps;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] test1 = {8, 4, 2, 1};
        System.out.println("Input:      " + Arrays.toString(test1));
        System.out.println("Inversions: " + countInversions(test1)); // Expected: 6 (Pairs: (8,4), (8,2), (8,1), (4,2), (4,1), (2,1))

        System.out.println("\n--- Test Case 2: Sorted Array (No Inversions) ---");
        int[] test2 = {1, 2, 3, 4, 5};
        System.out.println("Input:      " + Arrays.toString(test2));
        System.out.println("Inversions: " + countInversions(test2)); // Expected: 0

        System.out.println("\n--- Test Case 3: Reverse Sorted Array ---");
        int[] test3 = {5, 4, 3, 2, 1};
        System.out.println("Input:      " + Arrays.toString(test3));
        System.out.println("Inversions: " + countInversions(test3)); // Expected: 10 (N * (N - 1) / 2)

        System.out.println("\n--- Test Case 4: Array with Duplicates ---");
        int[] test4 = {2, 4, 1, 3, 5, 2};
        System.out.println("Input:      " + Arrays.toString(test4));
        System.out.println("Inversions: " + countInversions(test4)); // Expected: 5 (Pairs: (2,1), (4,1), (4,3), (4,2), (3,2))
    }
}
