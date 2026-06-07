// LeetCode 986: Interval List Intersections
// Time Complexity: O(N + M) - where N and M are the sizes of firstList and secondList respectively.
// Space Complexity: O(N + M) - for storing the resulting list of intersection intervals.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalIntersection {

    /**
     * Finds the intersection of two sorted interval lists.
     * @param firstList First list of pairwise disjoint closed intervals
     * @param secondList Second list of pairwise disjoint closed intervals
     * @return The intersection of these two interval lists
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < firstList.length && j < secondList.length) {
            // Find start and end of intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);

            // If the intervals overlap, add to results
            if (start <= end) {
                result.add(new int[]{start, end});
            }

            // Move the pointer of the interval that ends first
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Multiple Intersections ---");
        int[][] listA = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] listB = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        System.out.println("List A: " + Arrays.deepToString(listA));
        System.out.println("List B: " + Arrays.deepToString(listB));
        int[][] res1 = intervalIntersection(listA, listB);
        // Expected: [[1, 2], [5, 5], [8, 10], [15, 23], [24, 24], [25, 25]]
        System.out.println("Result: " + Arrays.deepToString(res1));

        System.out.println("\n--- Test Case 2: No Intersections ---");
        int[][] listC = {{1, 3}, {5, 7}};
        int[][] listD = {{8, 10}, {12, 14}};
        System.out.println("List C: " + Arrays.deepToString(listC));
        System.out.println("List D: " + Arrays.deepToString(listD));
        int[][] res2 = intervalIntersection(listC, listD);
        System.out.println("Result: " + Arrays.deepToString(res2)); // Expected: []

        System.out.println("\n--- Test Case 3: Empty List input ---");
        int[][] listE = {};
        int[][] listF = {{1, 5}, {8, 12}};
        System.out.println("List E: " + Arrays.deepToString(listE));
        System.out.println("List F: " + Arrays.deepToString(listF));
        int[][] res3 = intervalIntersection(listE, listF);
        System.out.println("Result: " + Arrays.deepToString(res3)); // Expected: []
    }
}
