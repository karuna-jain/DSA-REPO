// LeetCode 57: Insert Interval
// Time Complexity: O(N) - single pass through the sorted intervals array.
// Space Complexity: O(N) - for storing the resulting list of merged intervals.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    /**
     * Inserts newInterval into intervals, keeping it sorted and merging overlapping intervals.
     * @param intervals Non-overlapping intervals sorted by start time
     * @param newInterval Interval to insert
     * @return New list of intervals
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Step 1: Add all intervals that end before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge all overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Step 3: Add the remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Simple Insertion ---");
        int[][] test1 = {{1, 3}, {6, 9}};
        int[] new1 = {2, 5};
        System.out.println("Intervals: " + Arrays.deepToString(test1) + ", New: " + Arrays.toString(new1));
        int[][] res1 = insert(test1, new1);
        System.out.println("Result:    " + Arrays.deepToString(res1)); // Expected: [[1, 5], [6, 9]]

        System.out.println("\n--- Test Case 2: Multi-interval Merging ---");
        int[][] test2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] new2 = {4, 8};
        System.out.println("Intervals: " + Arrays.deepToString(test2) + ", New: " + Arrays.toString(new2));
        int[][] res2 = insert(test2, new2);
        System.out.println("Result:    " + Arrays.deepToString(res2)); // Expected: [[1, 2], [3, 10], [12, 16]]

        System.out.println("\n--- Test Case 3: Empty Intervals List ---");
        int[][] test3 = {};
        int[] new3 = {5, 7};
        System.out.println("Intervals: " + Arrays.deepToString(test3) + ", New: " + Arrays.toString(new3));
        int[][] res3 = insert(test3, new3);
        System.out.println("Result:    " + Arrays.deepToString(res3)); // Expected: [[5, 7]]

        System.out.println("\n--- Test Case 4: Inserting at the Beginning ---");
        int[][] test4 = {{3, 5}, {6, 8}};
        int[] new4 = {1, 2};
        System.out.println("Intervals: " + Arrays.deepToString(test4) + ", New: " + Arrays.toString(new4));
        int[][] res4 = insert(test4, new4);
        System.out.println("Result:    " + Arrays.deepToString(res4)); // Expected: [[1, 2], [3, 5], [6, 8]]

        System.out.println("\n--- Test Case 5: Inserting at the End ---");
        int[][] test5 = {{1, 2}, {3, 5}};
        int[] new5 = {6, 8};
        System.out.println("Intervals: " + Arrays.deepToString(test5) + ", New: " + Arrays.toString(new5));
        int[][] res5 = insert(test5, new5);
        System.out.println("Result:    " + Arrays.deepToString(res5)); // Expected: [[1, 2], [3, 5], [6, 8]]
    }
}
