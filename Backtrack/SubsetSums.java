// Problem: Subset Sums (GeeksforGeeks)
// Given a list of N integers, return the sums of all subsets in it.
// Output should be sorted in increasing order of sums.
// Time Complexity: O(2^N * log(2^N)) - Due to sorting 2^N elements.
// Space Complexity: O(2^N) - Auxiliary space to store the sums of 2^N subsets.

import java.util.ArrayList;
import java.util.Collections;

public class SubsetSums {

    /**
     * Computes the sums of all subsets of the given array.
     * 
     * @param arr The input array of integers
     * @return A sorted list containing the sums of all subsets
     */
    public static ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> sums = new ArrayList<>();
        if (arr == null) {
            return sums;
        }
        generateSubsetSums(arr, 0, 0, sums);
        Collections.sort(sums);
        return sums;
    }

    private static void generateSubsetSums(int[] arr, int index, int currentSum, ArrayList<Integer> sums) {
        if (index == arr.length) {
            sums.add(currentSum);
            return;
        }

        // Decision 1: Include the current element in the subset
        generateSubsetSums(arr, index + 1, currentSum + arr[index], sums);

        // Decision 2: Exclude the current element from the subset
        generateSubsetSums(arr, index + 1, currentSum, sums);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] arr1 = {2, 3};
        System.out.println("Input: [2, 3]");
        System.out.println("Subset Sums: " + subsetSums(arr1)); // Expected: [0, 2, 3, 5]

        System.out.println("\n--- Test Case 2: Array with 3 elements ---");
        int[] arr2 = {1, 2, 1};
        System.out.println("Input: [1, 2, 1]");
        System.out.println("Subset Sums: " + subsetSums(arr2)); // Expected: [0, 1, 1, 2, 2, 3, 3, 4]

        System.out.println("\n--- Test Case 3: Empty Array ---");
        int[] arr3 = {};
        System.out.println("Input: []");
        System.out.println("Subset Sums: " + subsetSums(arr3)); // Expected: [0]

        System.out.println("\n--- Test Case 4: Single Element ---");
        int[] arr4 = {5};
        System.out.println("Input: [5]");
        System.out.println("Subset Sums: " + subsetSums(arr4)); // Expected: [0, 5]
    }
}
