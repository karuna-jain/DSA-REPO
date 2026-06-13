// LeetCode 744: Find Smallest Letter Greater Than Target
// Time Complexity: O(log N) where N is the length of the array
// Space Complexity: O(1) auxiliary space

public class NextLetter {

    /**
     * Finds the smallest character in the array that is strictly larger than the target.
     * The array is sorted in non-decreasing order and wraps around circularly.
     * @param letters The sorted character array
     * @param target The target character
     * @return The smallest character greater than target
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (letters[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Since the array wraps around circularly, if low is equal to n, 
        // low % n will wrap back to index 0.
        return letters[low % n];
    }

    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};

        System.out.println("Input letters: ['c', 'f', 'j']");

        System.out.println("Target: 'a'");
        System.out.println("Expected: 'c', Actual: '" + nextGreatestLetter(letters, 'a') + "'");
        System.out.println();

        System.out.println("Target: 'c'");
        System.out.println("Expected: 'f', Actual: '" + nextGreatestLetter(letters, 'c') + "'");
        System.out.println();

        System.out.println("Target: 'd'");
        System.out.println("Expected: 'f', Actual: '" + nextGreatestLetter(letters, 'd') + "'");
        System.out.println();

        System.out.println("Target: 'g'");
        System.out.println("Expected: 'j', Actual: '" + nextGreatestLetter(letters, 'g') + "'");
        System.out.println();

        System.out.println("Target: 'j'");
        System.out.println("Expected: 'c' (wrap around), Actual: '" + nextGreatestLetter(letters, 'j') + "'");
        System.out.println();

        System.out.println("Target: 'z'");
        System.out.println("Expected: 'c' (wrap around), Actual: '" + nextGreatestLetter(letters, 'z') + "'");
    }
}
