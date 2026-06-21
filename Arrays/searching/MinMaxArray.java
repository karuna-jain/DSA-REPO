// GeeksforGeeks: Maximum and minimum of an array using minimum number of comparisons
// Time Complexity: O(N)
// Space Complexity: O(1) auxiliary space

public class MinMaxArray {

    /**
     * Structure to hold both minimum and maximum values of the array.
     */
    public static class Pair {
        public int min;
        public int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Min: " + min + ", Max: " + max;
        }
    }

    /**
     * Finds the minimum and maximum elements in an array using the minimum number of comparisons.
     * By comparing elements in pairs, the number of comparisons is reduced to:
     * - 3 * (N - 1) / 2 for odd N
     * - 1 + 3 * (N - 2) / 2 for even N
     * This is roughly 1.5 * N comparisons, compared to 2 * N in a naive linear scan.
     * 
     * @param arr The input array of integers
     * @return A Pair containing the min and max elements, or null if array is empty
     */
    public static Pair getMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int n = arr.length;
        int minVal;
        int maxVal;
        int i;

        // If array has even number of elements, initialize min/max with the first two elements
        if (n % 2 == 0) {
            if (arr[0] > arr[1]) {
                maxVal = arr[0];
                minVal = arr[1];
            } else {
                maxVal = arr[1];
                minVal = arr[0];
            }
            i = 2; // start loop from index 2
        } else {
            // If array has odd number of elements, initialize min/max with the first element
            maxVal = arr[0];
            minVal = arr[0];
            i = 1; // start loop from index 1
        }

        // Loop through the remaining elements in pairs
        while (i < n - 1) {
            int num1 = arr[i];
            int num2 = arr[i + 1];

            // 1 comparison to find the relation between the pair elements
            if (num1 > num2) {
                // 2 more comparisons: compare larger with maxVal, smaller with minVal
                if (num1 > maxVal) {
                    maxVal = num1;
                }
                if (num2 < minVal) {
                    minVal = num2;
                }
            } else {
                // 2 more comparisons: compare larger with maxVal, smaller with minVal
                if (num2 > maxVal) {
                    maxVal = num2;
                }
                if (num1 < minVal) {
                    minVal = num1;
                }
            }
            i += 2; // move to next pair
        }

        return new Pair(minVal, maxVal);
    }

    public static void main(String[] args) {
        int[] arr1 = {1000, 11, 445, 1, 330, 3000};
        System.out.println("Input array (even size): [1000, 11, 445, 1, 330, 3000]");
        Pair result1 = getMinMax(arr1);
        System.out.println("Expected: Min: 1, Max: 3000");
        System.out.println("Actual:   " + result1);
        System.out.println();

        int[] arr2 = {12, 1234, 45, 67, 1};
        System.out.println("Input array (odd size): [12, 1234, 45, 67, 1]");
        Pair result2 = getMinMax(arr2);
        System.out.println("Expected: Min: 1, Max: 1234");
        System.out.println("Actual:   " + result2);
        System.out.println();

        int[] arr3 = {7};
        System.out.println("Input array (single element): [7]");
        Pair result3 = getMinMax(arr3);
        System.out.println("Expected: Min: 7, Max: 7");
        System.out.println("Actual:   " + result3);
    }
}
