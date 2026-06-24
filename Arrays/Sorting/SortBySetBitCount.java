import java.util.Arrays;

// Time Complexity: 
//   - Timsort: O(N log N) - Built-in stable sort on object wrapper.
//   - Insertion Sort: O(N^2) - Custom stable in-place sort.
// Space Complexity:
//   - Timsort: O(N) auxiliary space for the wrapper array.
//   - Insertion Sort: O(1) auxiliary space (completely in-place).

public class SortBySetBitCount {

    /**
     * Approach 1: Stable sorting using Timsort (Java's standard Arrays.sort on Objects)
     * Sorts the array in place in descending order of set bit counts.
     * 
     * @param arr The input array to sort
     */
    public static void sortBySetBitCountTimsort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Convert primitive int[] to wrapper Integer[] to allow custom Comparator 
        // and guarantee stable Timsort.
        Integer[] wrapperArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            wrapperArr[i] = arr[i];
        }

        // Sort in descending order of set bit count.
        // Timsort is stable: elements with equal set bit counts preserve original relative order.
        Arrays.sort(wrapperArr, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            // Compare countB to countA for descending order
            return Integer.compare(countB, countA);
        });

        // Copy back to the original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = wrapperArr[i];
        }
    }

    /**
     * Approach 2: Custom Stable In-Place Insertion Sort
     * Sorts the array in place in descending order of set bit counts with O(1) auxiliary space.
     * 
     * @param arr The input array to sort
     */
    public static void sortBySetBitCountInsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int keyBitCount = Integer.bitCount(key);
            int j = i - 1;

            // Shift elements that have fewer set bits than 'key'
            // To maintain stability, we only shift if the bit count is strictly less.
            // If they are equal, they remain in the original relative order.
            while (j >= 0 && Integer.bitCount(arr[j]) < keyBitCount) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Timsort Method ---");
        int[] arr1 = {5, 2, 3, 9, 4, 6, 7, 15, 32};
        // Set bits count:
        // 15 (4 bits), 7 (3 bits), 5 (2 bits), 3 (2 bits), 9 (2 bits), 6 (2 bits),
        // 2 (1 bit), 4 (1 bit), 32 (1 bit)
        System.out.println("Original: " + Arrays.toString(arr1));
        sortBySetBitCountTimsort(arr1);
        System.out.println("Sorted:   " + Arrays.toString(arr1));
        // Expected stable output: [15, 7, 5, 3, 9, 6, 2, 4, 32]

        System.out.println("\n--- Test Case 2: Insertion Sort Method ---");
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};
        // Set bits count:
        // 7 (3 bits), 3 (2 bits), 5 (2 bits), 6 (2 bits), 1 (1 bit), 2 (1 bit), 4 (1 bit), 8 (1 bit)
        System.out.println("Original: " + Arrays.toString(arr2));
        sortBySetBitCountInsertionSort(arr2);
        System.out.println("Sorted:   " + Arrays.toString(arr2));
        // Expected stable output: [7, 3, 5, 6, 1, 2, 4, 8]
    }
}
