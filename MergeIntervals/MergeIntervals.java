// LeetCode 56: Merge Intervals
// Time Complexity: O(N log N) - sorting the intervals takes O(N log N) time.
// Space Complexity: O(N) or O(log N) - space required for sorting or output array.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    /**
     * Merges all overlapping intervals.
     * @param intervals Array of intervals where intervals[i] = [start_i, end_i]
     * @return An array of the non-overlapping intervals
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int i = 1; i < intervals.length; i++) {
            int currentEnd = currentInterval[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if (currentEnd >= nextStart) { // Overlap, merge them
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else { // No overlap, add nextInterval and update currentInterval pointer
                currentInterval = intervals[i];
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Overlapping Intervals ---");
        int[][] test1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Before: " + Arrays.deepToString(test1));
        int[][] res1 = merge(test1);
        System.out.println("After:  " + Arrays.deepToString(res1)); // Expected: [[1, 6], [8, 10], [15, 18]]

        System.out.println("\n--- Test Case 2: Adjacent / Touching Intervals ---");
        int[][] test2 = {{1, 4}, {4, 5}};
        System.out.println("Before: " + Arrays.deepToString(test2));
        int[][] res2 = merge(test2);
        System.out.println("After:  " + Arrays.deepToString(res2)); // Expected: [[1, 5]]

        System.out.println("\n--- Test Case 3: Fully Contained Intervals ---");
        int[][] test3 = {{1, 10}, {2, 6}, {3, 7}};
        System.out.println("Before: " + Arrays.deepToString(test3));
        int[][] res3 = merge(test3);
        System.out.println("After:  " + Arrays.deepToString(res3)); // Expected: [[1, 10]]

        System.out.println("\n--- Test Case 4: No Overlapping ---");
        int[][] test4 = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("Before: " + Arrays.deepToString(test4));
        int[][] res4 = merge(test4);
        System.out.println("After:  " + Arrays.deepToString(res4)); // Expected: [[1, 2], [3, 4], [5, 6]]
    }
}
