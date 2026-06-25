/**
 * Problem: Find Pivot Element in a Sorted and Rotated Array
 * 
 * Description:
 * A sorted array of size N is rotated by some pivot. The task is to find the index of the pivot.
 * Depending on the context, the "pivot" can be defined in two ways:
 *   1. The largest element (the inflection point where the values drop: nums[i] > nums[i+1]).
 *   2. The smallest element (the minimum element, which is the start of the original sorted array).
 * 
 * This class provides highly optimized binary search implementations for both definitions:
 *   - finding the index of the LARGEST element (Standard Rotation Pivot).
 *   - finding the index of the SMALLEST element (Minimum Element Pivot).
 * Both methods handle unique elements in O(log N) time, and handle duplicate elements
 * by shrinking boundaries.
 * 
 * Time Complexity:
 *   - Average/Best: O(log N)
 *   - Worst: O(N) when there are many duplicate elements (e.g., [2, 2, 2, 0, 2])
 * Space Complexity:
 *   - O(1) auxiliary space
 */
public class FindPivot {

    /**
     * Finds the index of the LARGEST element (pivot point where array values drop).
     * Works for unique elements.
     * If array is not rotated, returns the last index.
     */
    public static int findPivotUnique(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int low = 0;
        int high = nums.length - 1;

        // If array is not rotated at all
        if (nums[low] <= nums[high]) {
            return high;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Case 1: mid is the pivot because the next element is smaller
            if (mid < high && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // Case 2: mid - 1 is the pivot because mid is smaller than it
            if (mid > low && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }

            // Case 3: If start element is greater than or equal to mid element,
            // the pivot must be in the left half
            if (nums[low] >= nums[mid]) {
                high = mid - 1;
            } 
            // Case 4: If start element is smaller than mid element,
            // the pivot must be in the right half
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the LARGEST element (pivot point) in an array that may contain duplicates.
     */
    public static int findPivotWithDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Case 1: mid is the pivot because the next element is smaller
            if (mid < high && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // Case 2: mid - 1 is the pivot because mid is smaller than it
            if (mid > low && nums[mid] < nums[mid - 1]) {
                return mid - 1;
            }

            // If elements at low, mid, and high are equal, we cannot decide which half is sorted.
            // We skip duplicates but must check if the boundary elements were pivots.
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                // Check if low is the pivot
                if (low < high && nums[low] > nums[low + 1]) {
                    return low;
                }
                low++;

                // Check if high - 1 is the pivot
                if (high > low && nums[high - 1] > nums[high]) {
                    return high - 1;
                }
                high--;
            }
            // Left side is sorted, so pivot must be in the right side
            else if (nums[low] < nums[mid] || (nums[low] == nums[mid] && nums[mid] > nums[high])) {
                low = mid + 1;
            } 
            // Right side is sorted, so pivot must be in the left side
            else {
                high = mid - 1;
            }
        }
        // If not rotated, the largest element is the last element
        return nums.length - 1;
    }

    /**
     * Finds the index of the SMALLEST element (minimum element pivot).
     * Works for unique elements in O(log N) time.
     */
    public static int findMinPivotUnique(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                // Minimum is in the right half
                low = mid + 1;
            } else {
                // Minimum is at mid or in the left half
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("Running Find Pivot Element Tests:\n");

        // Test 1: Unique rotated array (Pivot is largest element)
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        int pivot1 = findPivotUnique(arr1);
        int minPivot1 = findMinPivotUnique(arr1);
        System.out.println("Test 1: Unique Rotated Array [4, 5, 6, 7, 0, 1, 2]");
        System.out.println("Largest Element (Pivot) Index - Expected: 3 (val: 7), Actual: " + pivot1 + " (val: " + arr1[pivot1] + ")");
        System.out.println("Smallest Element Index        - Expected: 4 (val: 0), Actual: " + minPivot1 + " (val: " + arr1[minPivot1] + ")");
        System.out.println();

        // Test 2: Non-rotated sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        int pivot2 = findPivotUnique(arr2);
        int minPivot2 = findMinPivotUnique(arr2);
        System.out.println("Test 2: Non-rotated Sorted Array [1, 2, 3, 4, 5]");
        System.out.println("Largest Element (Pivot) Index - Expected: 4 (val: 5), Actual: " + pivot2 + " (val: " + arr2[pivot2] + ")");
        System.out.println("Smallest Element Index        - Expected: 0 (val: 1), Actual: " + minPivot2 + " (val: " + arr2[minPivot2] + ")");
        System.out.println();

        // Test 3: Rotated array with duplicates
        int[] arr3 = {2, 2, 2, 9, 2, 2};
        int pivot3 = findPivotWithDuplicates(arr3);
        System.out.println("Test 3: Rotated Array with Duplicates [2, 2, 2, 9, 2, 2]");
        System.out.println("Largest Element (Pivot) Index - Expected: 3 (val: 9), Actual: " + pivot3 + " (val: " + arr3[pivot3] + ")");
        System.out.println();
        
        // Test 4: Rotated array with duplicates at boundaries
        int[] arr4 = {10, 1, 10, 10, 10};
        int pivot4 = findPivotWithDuplicates(arr4);
        System.out.println("Test 4: Rotated Array with Duplicates [10, 1, 10, 10, 10]");
        System.out.println("Largest Element (Pivot) Index - Expected: 0 or 4 (val: 10), Actual: " + pivot4 + " (val: " + arr4[pivot4] + ")");
        System.out.println();
    }
}
