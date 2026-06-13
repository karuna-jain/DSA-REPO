// Problem: Minimum Difference Element (Closest equivalent: LeetCode 658)
// Time Complexity: O(log N) where N is the size of the array
// Space Complexity: O(1) auxiliary space

public class MinimumDifferenceElement {

    /**
     * Finds the element in a sorted array that has the minimum absolute difference to the key.
     * If there is a tie, the smaller element is returned.
     * @param arr The sorted integer array
     * @param key The key to compare differences with
     * @return The element with the minimum difference
     */
    public static int searchMinDiffElement(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int n = arr.length;
        // Edge cases where the key is outside the array range
        if (key <= arr[0]) {
            return arr[0];
        }
        if (key >= arr[n - 1]) {
            return arr[n - 1];
        }

        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return arr[mid]; // Exact match has 0 difference
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // At the end of the loop, 'low' points to the ceiling and 'high' points to the floor.
        // Compare the absolute difference of both elements and return the closest one.
        if ((arr[low] - key) < (key - arr[high])) {
            return arr[low];
        }
        
        return arr[high];
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 6, 10};
        System.out.println("Array: [4, 6, 10]");
        System.out.println("Key: 7 -> Expected: 6, Actual: " + searchMinDiffElement(arr1, 7));
        System.out.println("Key: 4 -> Expected: 4, Actual: " + searchMinDiffElement(arr1, 4));
        System.out.println("Key: 11 -> Expected: 10, Actual: " + searchMinDiffElement(arr1, 11));
        System.out.println();

        int[] arr2 = {1, 3, 8, 10, 15};
        System.out.println("Array: [1, 3, 8, 10, 15]");
        System.out.println("Key: 12 -> Expected: 10, Actual: " + searchMinDiffElement(arr2, 12));
        System.out.println("Key: 5 -> Expected: 6 (4 or 6 are equally close, returns 4 or 6 depending on array elements; for 5 in [1,3,8] 3 and 8 are adjacent. Diff for 3 is 2, diff for 8 is 3, so expected 3) -> Actual: " + searchMinDiffElement(arr2, 5));
    }
}
