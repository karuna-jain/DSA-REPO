import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem: Kth Smallest Number Again (HackerEarth / Love Babbar DSA Sheet)
 * 
 * Description:
 * Given a set of N intervals [A_i, B_i], find the K-th smallest number in the union
 * of all these intervals. Distinct elements are considered in increasing order.
 * Since multiple queries are asked, the approach pre-merges intervals and uses
 * binary search on the prefix sums of interval sizes to answer queries in O(log M) time.
 * 
 * Time Complexity:
 *   - Preprocessing: O(N log N) to sort and merge intervals.
 *   - Querying: O(log M) per query, where M is the number of merged intervals (M <= N).
 * Space Complexity:
 *   - O(N) auxiliary space to store the merged intervals and prefix sums.
 */
public class KthSmallestNumberAgain {

    public static class Interval implements Comparable<Interval> {
        long start;
        long end;

        public Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval other) {
            if (this.start != other.start) {
                return Long.compare(this.start, other.start);
            }
            return Long.compare(this.end, other.end);
        }
    }

    /**
     * Merges overlapping intervals and returns a sorted list of disjoint intervals.
     */
    public static List<Interval> mergeIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new ArrayList<>();
        }

        // Sort intervals by start time
        Arrays.sort(intervals);

        List<Interval> merged = new ArrayList<>();
        Interval current = new Interval(intervals[0].start, intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            Interval next = intervals[i];
            if (next.start <= current.end) {
                // Overlapping or adjacent, merge them
                current.end = Math.max(current.end, next.end);
            } else {
                // Disjoint, add current to list and move to next
                merged.add(current);
                current = new Interval(next.start, next.end);
            }
        }
        merged.add(current);
        return merged;
    }

    /**
     * Answers queries for the K-th smallest number.
     * @param intervals Array of input intervals
     * @param queries Array of K-th queries (1-based index)
     * @return Array of answers for each query, where -1 indicates K is out of bounds
     */
    public static long[] solve(Interval[] intervals, long[] queries) {
        List<Interval> merged = mergeIntervals(intervals);
        int m = merged.size();

        // Precompute prefix sums of sizes of merged intervals
        long[] pref = new long[m];
        if (m > 0) {
            pref[0] = merged.get(0).end - merged.get(0).start + 1;
            for (int i = 1; i < m; i++) {
                long size = merged.get(i).end - merged.get(i).start + 1;
                pref[i] = pref[i - 1] + size;
            }
        }

        long[] results = new long[queries.length];
        for (int q = 0; q < queries.length; q++) {
            long k = queries[q];

            // Use binary search to find the interval containing the K-th element
            int idx = findIntervalIndex(pref, k);

            if (idx == -1) {
                results[q] = -1; // K is out of bounds
            } else {
                long prevCount = (idx == 0) ? 0 : pref[idx - 1];
                long offset = k - prevCount - 1;
                results[q] = merged.get(idx).start + offset;
            }
        }

        return results;
    }

    /**
     * Binary search to find the first index where pref[mid] >= K.
     */
    private static int findIntervalIndex(long[] pref, long k) {
        int low = 0;
        int high = pref.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (pref[mid] >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller index
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Running K-th Smallest Number Again Tests:\n");

        // Test 1: Simple non-overlapping intervals
        Interval[] intervals1 = {
            new Interval(1, 5),
            new Interval(10, 15)
        };
        long[] queries1 = {2, 5, 6, 11, 12};
        // Union elements: 1, 2, 3, 4, 5 (1 to 5), 10, 11, 12, 13, 14, 15 (6 to 11)
        // K=2 -> 2
        // K=5 -> 5
        // K=6 -> 10
        // K=11 -> 15
        // K=12 -> -1
        long[] res1 = solve(intervals1, queries1);
        System.out.println("Test 1: Non-overlapping intervals [1, 5] and [10, 15]");
        System.out.println("Queries: " + Arrays.toString(queries1));
        System.out.println("Expected: [2, 5, 10, 15, -1]");
        System.out.println("Actual:   " + Arrays.toString(res1));
        System.out.println();

        // Test 2: Overlapping and unsorted intervals
        Interval[] intervals2 = {
            new Interval(5, 10),
            new Interval(2, 6),
            new Interval(15, 20),
            new Interval(12, 17)
        };
        long[] queries2 = {1, 5, 9, 10, 15, 16};
        // Sorted: [2, 6], [5, 10], [12, 17], [15, 20]
        // Merged: [2, 10] (size 9), [12, 20] (size 9). Total size = 18.
        // Elements: 2,3,4,5,6,7,8,9,10, 12,13,14,15,16,17,18,19,20
        // K=1 -> 2
        // K=5 -> 6
        // K=9 -> 10
        // K=10 -> 12
        // K=15 -> 17
        // K=16 -> 18
        long[] res2 = solve(intervals2, queries2);
        System.out.println("Test 2: Overlapping intervals {[5,10], [2,6], [15,20], [12,17]} -> Merged: [2,10], [12,20]");
        System.out.println("Queries: " + Arrays.toString(queries2));
        System.out.println("Expected: [2, 6, 10, 12, 17, 18]");
        System.out.println("Actual:   " + Arrays.toString(res2));
        System.out.println();
    }
}
