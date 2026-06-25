import java.util.Arrays;

/**
 * Problem: Aggressive Cows (SPOJ / Love Babbar DSA Sheet)
 * 
 * Description:
 * You are given an array of N stalls, each located at a specific coordinate on a straight line.
 * You are also given C aggressive cows. You must assign the cows to the stalls such that the minimum
 * distance between any two of them is as large as possible.
 * Find the maximum possible minimum distance.
 * 
 * Time Complexity:
 *   - O(N log N + N log(max_dist)) where N is the number of stalls,
 *     and max_dist is the difference between the maximum and minimum stall positions.
 * Space Complexity:
 *   - O(1) auxiliary space (if sorting is in-place, or O(log N) due to recursive quicksort stack).
 */
public class AggressiveCows {

    /**
     * Finds the maximum possible minimum distance between C cows placed in N stalls.
     * @param stalls Coordinates of the stalls
     * @param cows Number of cows to place
     * @return The maximum possible minimum distance
     */
    public static int maxMinDistance(int[] stalls, int cows) {
        if (stalls == null || stalls.length < 2 || cows < 2) {
            return 0;
        }

        // 1. Sort the stall positions to apply binary search and greedy placement
        Arrays.sort(stalls);

        int n = stalls.length;
        
        // 2. Define the search space for the answer (distance)
        int low = 1; // Minimum possible distance
        int high = stalls[n - 1] - stalls[0]; // Maximum possible distance
        int ans = 0;

        // 3. Binary search on the answer space
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlaceCows(stalls, mid, cows)) {
                ans = mid;         // Record mid as a potential solution
                low = mid + 1;     // Try to find a larger minimum distance
            } else {
                high = mid - 1;    // Try a smaller distance
            }
        }

        return ans;
    }

    /**
     * Greedy helper to check if we can place 'cows' cows in 'stalls' with a minimum distance of 'dist'.
     */
    private static boolean canPlaceCows(int[] stalls, int dist, int cows) {
        // Place the first cow at the first stall
        int count = 1;
        int lastPlaced = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPlaced >= dist) {
                count++;
                lastPlaced = stalls[i]; // Update the position of the last placed cow
                if (count == cows) {
                    return true; // Successfully placed all cows
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Running Aggressive Cows Tests:\n");

        // Test 1: Standard case
        int[] stalls1 = {1, 2, 8, 4, 9};
        int cows1 = 3;
        // Sorted stalls: [1, 2, 4, 8, 9]
        // Placing 3 cows:
        // If dist = 3: place at 1, 4, 8. Differences: (4-1)=3, (8-4)=4. Min diff = 3.
        // If dist = 4: place at 1, 8. Can only place 2 cows.
        // Answer should be 3.
        int ans1 = maxMinDistance(stalls1, cows1);
        System.out.println("Test 1: stalls = [1, 2, 8, 4, 9], cows = 3");
        System.out.println("Expected: 3, Actual: " + ans1);
        System.out.println();

        // Test 2: Dense stalls
        int[] stalls2 = {0, 3, 4, 7, 10, 9};
        int cows2 = 4;
        // Sorted: [0, 3, 4, 7, 9, 10]
        // Try placing 4 cows:
        // If dist = 3: place at 0, 3, 7, 10. Differences: (3-0)=3, (7-3)=4, (10-7)=3. Min diff = 3.
        // Answer should be 3.
        int ans2 = maxMinDistance(stalls2, cows2);
        System.out.println("Test 2: stalls = [0, 3, 4, 7, 10, 9], cows = 4");
        System.out.println("Expected: 3, Actual: " + ans2);
        System.out.println();

        // Test 3: Large spacing
        int[] stalls3 = {10, 22, 35, 47, 60};
        int cows3 = 2;
        // Placing 2 cows: always place at boundaries stalls[0] and stalls[n-1] for max distance
        // Expected: 60 - 10 = 50.
        int ans3 = maxMinDistance(stalls3, cows3);
        System.out.println("Test 3: stalls = [10, 22, 35, 47, 60], cows = 2");
        System.out.println("Expected: 50, Actual: " + ans3);
        System.out.println();
    }
}
