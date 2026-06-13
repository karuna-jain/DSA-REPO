// LeetCode 875: Koko Eating Bananas (Binary Search on Answer Space)
// Time Complexity: O(N log(M)) where N is the number of piles and M is the maximum size of a pile
// Space Complexity: O(1) auxiliary space

public class KokoEatingBananas {

    /**
     * Finds the minimum integer speed k such that Koko can eat all bananas in h hours.
     * @param piles Array representing the number of bananas in each pile
     * @param h The hours limit
     * @return The minimum speed k
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        
        // The maximum speed required is the maximum pile size
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canEatAll(piles, mid, h)) {
                high = mid; // Try to find a slower speed
            } else {
                low = mid + 1; // Speed is too slow, increase it
            }
        }

        return low;
    }

    // Helper method to check if speed k allows eating all bananas in h hours
    private static boolean canEatAll(int[] piles, int speed, int h) {
        long hoursNeeded = 0; // Use long to prevent integer overflow
        for (int pile : piles) {
            // Equivalent to Math.ceil((double) pile / speed) using integer arithmetic
            hoursNeeded += (pile + speed - 1) / speed;
        }
        return hoursNeeded <= h;
    }

    public static void main(String[] args) {
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Piles: [3, 6, 7, 11], Hours: 8");
        System.out.println("Expected speed: 4, Actual: " + minEatingSpeed(piles1, h1));
        System.out.println();

        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Piles: [30, 11, 23, 4, 20], Hours: 5");
        System.out.println("Expected speed: 30, Actual: " + minEatingSpeed(piles2, h2));
        System.out.println();

        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Piles: [30, 11, 23, 4, 20], Hours: 6");
        System.out.println("Expected speed: 23, Actual: " + minEatingSpeed(piles3, h3));
    }
}
