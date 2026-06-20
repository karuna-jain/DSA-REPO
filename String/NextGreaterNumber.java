// Find next greater number with same set of digits
// Time Complexity: O(D) where D is the number of digits in the number
// Space Complexity: O(D) to store the digit representation of the number

import java.util.Arrays;

public class NextGreaterNumber {
    /**
     * Finds the next greater number with the same set of digits for an integer.
     * If no such number exists, or if it exceeds 32-bit signed integer limits, returns -1.
     * 
     * @param n The input integer
     * @return The next greater integer, or -1 if impossible
     */
    public static int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        
        // Find the next permutation of the digit array
        boolean hasNext = findNextPermutation(digits);
        if (!hasNext) {
            return -1;
        }
        
        // Convert back to number and check for 32-bit integer overflow
        try {
            long val = Long.parseLong(new String(digits));
            if (val > Integer.MAX_VALUE) {
                return -1;
            }
            return (int) val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Finds the next greater number with the same set of digits for a String of digits.
     * This is useful for numbers that are too large to fit in basic integer types.
     * 
     * @param numStr The input string of digits
     * @return The next lexicographically greater numeric string, or null if none exists
     */
    public static String nextGreaterNumber(String numStr) {
        if (numStr == null || numStr.length() <= 1) {
            return null;
        }

        char[] digits = numStr.toCharArray();
        boolean hasNext = findNextPermutation(digits);
        if (!hasNext) {
            return null;
        }

        return new String(digits);
    }

    /**
     * Rearranges the character array into its lexicographically next greater permutation.
     * Modifies the array in place.
     * 
     * @param arr The character array
     * @return True if a next permutation was found; false if the array was in descending order
     */
    private static boolean findNextPermutation(char[] arr) {
        // Step 1: Find the first decreasing digit from the right (pivot)
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        // If no pivot is found, the digits are in descending order, meaning no greater number is possible
        if (i < 0) {
            return false;
        }

        // Step 2: Find the smallest digit in the suffix that is strictly greater than the pivot
        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }

        // Step 3: Swap the pivot with its successor
        swap(arr, i, j);

        // Step 4: Reverse the suffix starting from i + 1 to get the smallest lexicographical arrangement
        reverse(arr, i + 1, arr.length - 1);
        
        return true;
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }

    public static void main(String[] args) {
        // Test integer values (LeetCode 556 style)
        int intTest1 = 12;
        int intTest2 = 21;
        int intTest3 = 1999999999; // Will overflow if permuted
        int intTest4 = 218765;

        System.out.println("Integer Test Cases:");
        System.out.println("Input: " + intTest1 + " -> Next Greater: " + nextGreaterElement(intTest1)); // Expected: 21
        System.out.println("Input: " + intTest2 + " -> Next Greater: " + nextGreaterElement(intTest2)); // Expected: -1
        System.out.println("Input: " + intTest3 + " -> Next Greater: " + nextGreaterElement(intTest3)); // Expected: -1 (overflow)
        System.out.println("Input: " + intTest4 + " -> Next Greater: " + nextGreaterElement(intTest4)); // Expected: 251678

        // Test arbitrary length strings
        String strTest1 = "123456";
        String strTest2 = "4321";
        String strTest3 = "58476531";

        System.out.println("\nString Digit Test Cases:");
        System.out.println("Input: \"" + strTest1 + "\" -> Next Greater: \"" + nextGreaterNumber(strTest1) + "\""); // Expected: "123465"
        System.out.println("Input: \"" + strTest2 + "\" -> Next Greater: \"" + nextGreaterNumber(strTest2) + "\""); // Expected: null
        System.out.println("Input: \"" + strTest3 + "\" -> Next Greater: \"" + nextGreaterNumber(strTest3) + "\""); // Expected: "58513467"
    }
}
