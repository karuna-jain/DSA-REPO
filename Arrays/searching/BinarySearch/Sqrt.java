// LeetCode 69: Sqrt(x) / GeeksforGeeks: Square root of an integer
// Time Complexity: O(log X) where X is the input integer
// Space Complexity: O(1) auxiliary space

public class Sqrt {

    /**
     * Computes the square root of a non-negative integer x.
     * If x is not a perfect square, returns the floor of the square root.
     * @param x The non-negative integer
     * @return The floor value of the square root
     */
    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int low = 1;
        int high = x;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Using division check (mid <= x / mid) instead of (mid * mid <= x)
            // to prevent integer overflow when mid * mid exceeds Integer.MAX_VALUE.
            if (mid <= x / mid) {
                ans = mid; // Candidate root
                low = mid + 1; // Try to find a larger value
            } else {
                high = mid - 1; // Search in the lower half
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Square root of 4:");
        System.out.println("Expected: 2, Actual: " + mySqrt(4));
        System.out.println();

        System.out.println("Square root of 8:");
        System.out.println("Expected: 2, Actual: " + mySqrt(8));
        System.out.println();

        System.out.println("Square root of 0:");
        System.out.println("Expected: 0, Actual: " + mySqrt(0));
        System.out.println();

        System.out.println("Square root of 1:");
        System.out.println("Expected: 1, Actual: " + mySqrt(1));
        System.out.println();

        System.out.println("Square root of 2147483647 (Integer.MAX_VALUE):");
        System.out.println("Expected: 46340, Actual: " + mySqrt(2147483647));
    }
}
