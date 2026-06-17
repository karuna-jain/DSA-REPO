// Write a Program to check whether a string is a valid shuffle of two strings or not
// Time Complexity: O(M * N) where M is s1.length() and N is s2.length()
// Space Complexity: O(M * N) auxiliary space for the DP lookup table

public class ValidShuffle {
    /**
     * Checks if the result string is a valid shuffle (interleaving) of strings s1 and s2.
     * A valid shuffle preserves the relative order of characters from both source strings.
     * Uses a 2D Dynamic Programming matrix to prevent greedy matching failure on duplicate characters.
     * 
     * @param s1 The first source string
     * @param s2 The second source string
     * @param result The combined target string to evaluate
     * @return true if result is a valid shuffle, false otherwise
     */
    public static boolean isValidShuffle(String s1, String s2, String result) {
        // Boundary validation: If any reference is null, return false
        if (s1 == null || s2 == null || result == null) {
            return false;
        }
        
        // The total length of the shuffled result must exactly equal the sum of s1 and s2 lengths
        if (s1.length() + s2.length() != result.length()) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();
        
        // dp[i][j] will be true if the prefix result[0..i+j-1] is a valid shuffle of
        // prefixes s1[0..i-1] and s2[0..j-1].
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Fill the DP matrix
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                
                // Base Case: Both source strings are empty, which can form an empty result
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    
                // Case 1: s1 is empty. Check if s2 suffix matches result suffix and prior states match
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && (s2.charAt(j - 1) == result.charAt(i + j - 1));
                    
                // Case 2: s2 is empty. Check if s1 suffix matches result suffix and prior states match
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && (s1.charAt(i - 1) == result.charAt(i + j - 1));
                    
                // Case 3: Both s1 and s2 are non-empty. We have two options:
                // Option A: Take current character from s1 (if character matches result[i+j-1] and dp[i-1][j] is true)
                // Option B: Take current character from s2 (if character matches result[i+j-1] and dp[i][j-1] is true)
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == result.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == result.charAt(i + j - 1));
                }
            }
        }

        // Return the final cell mapping containing the full string evaluations
        return dp[m][n];
    }

    public static void main(String[] args) {
        // Define test cases
        String s1 = "abc";
        String s2 = "def";
        String r1 = "dabecf"; // true (relative order of abc and def is preserved)
        String r2 = "cbadef"; // false (abc relative order is violated: c before b, a)
        String r3 = "adbecf"; // true

        // Print evaluations
        System.out.println("Is \"" + r1 + "\" a valid shuffle of \"" + s1 + "\" & \"" + s2 + "\"? " + isValidShuffle(s1, s2, r1));
        System.out.println("Is \"" + r2 + "\" a valid shuffle of \"" + s1 + "\" & \"" + s2 + "\"? " + isValidShuffle(s1, s2, r2));
        System.out.println("Is \"" + r3 + "\" a valid shuffle of \"" + s1 + "\" & \"" + s2 + "\"? " + isValidShuffle(s1, s2, r3));

        // Test with duplicates to demonstrate why DP is required over simple two pointers
        String s1_dup = "aab";
        String s2_dup = "aac";
        String r_dup = "aacaab"; // true
        System.out.println("Is \"" + r_dup + "\" a valid shuffle of \"" + s1_dup + "\" & \"" + s2_dup + "\"? " + isValidShuffle(s1_dup, s2_dup, r_dup));
    }
}
