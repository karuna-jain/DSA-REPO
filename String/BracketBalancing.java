// GeeksforGeeks: Minimum number of swaps for bracket balancing
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(1) auxiliary space

public class BracketBalancing {

    /**
     * Calculates the minimum number of adjacent swaps needed to balance a string of brackets.
     * The input string contains equal number of '[' and ']'.
     * @param s The input string of brackets
     * @return The minimum number of adjacent swaps
     */
    public static int minimumSwaps(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int open = 0;
        int close = 0;
        int swaps = 0;
        int imbalance = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '[') {
                open++;
                // If there was an active imbalance (excess of closing brackets),
                // this open bracket will be swapped with the earliest unmatched closing bracket.
                if (imbalance > 0) {
                    swaps += imbalance;
                    // One closing bracket is now balanced
                    imbalance--;
                }
            } else if (ch == ']') {
                close++;
                // Imbalance is the number of closing brackets that do not have matching open brackets yet
                imbalance = close - open;
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        String s1 = "[]][][";
        System.out.println("Input: \"[]][][\"");
        System.out.println("Expected: 2, Actual: " + minimumSwaps(s1));
        System.out.println();

        String s2 = "[[][]]";
        System.out.println("Input: \"[[][]]\"");
        System.out.println("Expected: 0, Actual: " + minimumSwaps(s2));
        System.out.println();

        String s3 = "]]][[[";
        System.out.println("Input: \"]]][[[\"");
        System.out.println("Expected: 6, Actual: " + minimumSwaps(s3));
    }
}
