// LeetCode 702: Search in a Sorted Array of Unknown Size
// Time Complexity: O(log K) where K is the index of the target
// Space Complexity: O(1) auxiliary space

public class SearchInfiniteSortedArray {

    // Interface provided in LeetCode 702
    interface ArrayReader {
        int get(int index);
    }

    /**
     * Searches for a target value in an infinite sorted array (unknown size).
     * @param reader The API to read elements
     * @param target The target value to search
     * @return The index of the target if found, otherwise -1
     */
    public static int search(ArrayReader reader, int target) {
        // Step 1: Find the bounds for binary search
        int low = 0;
        int high = 1;

        // Exponentially increase the range size while the target is larger than the value at 'high'
        while (reader.get(high) < target) {
            int newLow = high + 1;
            high = high + (high - low + 1) * 2; // Double the bounds size
            low = newLow;
        }

        // Step 2: Standard binary search within [low, high]
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int val = reader.get(mid);

            if (val == target) {
                return mid;
            } else if (val < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // Mock implementation of ArrayReader for testing
    static class MockArrayReader implements ArrayReader {
        private final int[] arr;

        public MockArrayReader(int[] arr) {
            this.arr = arr;
        }

        @Override
        public int get(int index) {
            // Out of bounds in LeetCode returns 2^31 - 1 (Integer.MAX_VALUE)
            if (index >= arr.length) {
                return Integer.MAX_VALUE;
            }
            return arr[index];
        }
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12, 18, 21, 30, 35, 40, 45, 50, 55, 60, 70, 80};
        MockArrayReader reader = new MockArrayReader(arr);

        System.out.println("Searching in an array of unknown size.");
        System.out.println("Array contents: [-1, 0, 3, 5, 9, 12, 18, ...]");

        int target1 = 9;
        System.out.println("Target: " + target1);
        System.out.println("Expected index: 4, Actual: " + search(reader, target1));
        System.out.println();

        int target2 = 30;
        System.out.println("Target: " + target2);
        System.out.println("Expected index: 8, Actual: " + search(reader, target2));
        System.out.println();

        int target3 = 2; // Not present
        System.out.println("Target: " + target3);
        System.out.println("Expected index: -1, Actual: " + search(reader, target3));
        System.out.println();

        int target4 = 100; // Greater than any element
        System.out.println("Target: " + target4);
        System.out.println("Expected index: -1, Actual: " + search(reader, target4));
    }
}
