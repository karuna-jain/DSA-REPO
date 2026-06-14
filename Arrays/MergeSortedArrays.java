// Problem: Merge two sorted arrays of size N and M in-place without using extra space.
// Time Complexity: O((N + M) log(N + M)) - standard Gap Method (Shell Sort variant)
// Space Complexity: O(1) auxiliary space - in-place swaps only

import java.util.Arrays;

public class MergeSortedArrays {

    /**
     * Merges two sorted arrays arr1 and arr2 in-place.
     * After merging, arr1 will contain the first N smallest elements sorted,
     * and arr2 will contain the remaining M elements sorted.
     * 
     * @param arr1 First sorted array of size N
     * @param arr2 Second sorted array of size M
     */
    public static void merge(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length == 0 && arr2.length == 0) {
            return;
        }

        int n = arr1.length;
        int m = arr2.length;
        int totalLen = n + m;

        // Start with the initial gap: ceil((n + m) / 2)
        int gap = (totalLen / 2) + (totalLen % 2);

        while (gap > 0) {
            int left = 0;
            int right = left + gap;

            while (right < totalLen) {
                // Case 1: Both pointers are in the first array
                if (left < n && right < n) {
                    if (arr1[left] > arr1[right]) {
                        swap(arr1, left, arr1, right);
                    }
                }
                // Case 2: Left pointer is in the first array, right is in the second array
                else if (left < n && right >= n) {
                    if (arr1[left] > arr2[right - n]) {
                        swap(arr1, left, arr2, right - n);
                    }
                }
                // Case 3: Both pointers are in the second array
                else if (left >= n && right >= n) {
                    if (arr2[left - n] > arr2[right - n]) {
                        swap(arr2, left - n, arr2, right - n);
                    }
                }

                left++;
                right++;
            }

            // If gap is 1, we are done after this pass
            if (gap == 1) {
                break;
            }

            // Reduce the gap: ceil(gap / 2)
            gap = (gap / 2) + (gap % 2);
        }
    }

    private static void swap(int[] arr1, int idx1, int[] arr2, int idx2) {
        int temp = arr1[idx1];
        arr1[idx1] = arr2[idx2];
        arr2[idx2] = temp;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {0, 2, 6, 8, 9};
        System.out.println("Before Merge:");
        System.out.println("arr1: " + Arrays.toString(arr1));
        System.out.println("arr2: " + Arrays.toString(arr2));
        
        merge(arr1, arr2);
        
        System.out.println("After Merge:");
        System.out.println("arr1: " + Arrays.toString(arr1)); // Expected: [0, 1, 2, 3]
        System.out.println("arr2: " + Arrays.toString(arr2)); // Expected: [5, 6, 7, 8, 9]

        System.out.println("\n--- Test Case 2: One Array Has All Smaller Elements ---");
        int[] arr3 = {10, 12, 14};
        int[] arr4 = {1, 3, 5};
        System.out.println("Before Merge:");
        System.out.println("arr3: " + Arrays.toString(arr3));
        System.out.println("arr4: " + Arrays.toString(arr4));
        
        merge(arr3, arr4);
        
        System.out.println("After Merge:");
        System.out.println("arr3: " + Arrays.toString(arr3)); // Expected: [1, 3, 5]
        System.out.println("arr4: " + Arrays.toString(arr4)); // Expected: [10, 12, 14]

        System.out.println("\n--- Test Case 3: Empty Array Handling ---");
        int[] arr5 = {};
        int[] arr6 = {2, 4, 6};
        System.out.println("Before Merge:");
        System.out.println("arr5: " + Arrays.toString(arr5));
        System.out.println("arr6: " + Arrays.toString(arr6));
        
        merge(arr5, arr6);
        
        System.out.println("After Merge:");
        System.out.println("arr5: " + Arrays.toString(arr5)); // Expected: []
        System.out.println("arr6: " + Arrays.toString(arr6)); // Expected: [2, 4, 6]
    }
}
