// Write a Program to check whether a string is a valid shuffle of two strings or not
// Time Complexity: O(M * N) where M = s1.length(), N = s2.length()
// Space Complexity: O(M * N) for the DP table

public class ValidShuffle {
    /**
     * Check if a result string is a valid shuffle (interleaving) of s1 and s2.
     * The relative order of characters of s1 and s2 must be preserved in result.
     */
    public static boolean isValidShuffle(String s1, String s2, String result) {
        if (s1 == null || s2 == null || result == null) {
            return false;
        }
        if (s1.length() + s2.length() != result.length()) {
            return false;
        }

        int m = s1.length();
        int n = s2.length();
        
        // dp[i][j] will be true if result[0..i+j-1] is a valid shuffle of s1[0..i-1] and s2[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && (s2.charAt(j - 1) == result.charAt(i + j - 1));
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && (s1.charAt(i - 1) == result.charAt(i + j - 1));
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == result.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == result.charAt(i + j - 1));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "def";
        String r1 = "dabecf"; // true (relative order of abc and def is preserved)
        String r2 = "cbadef"; // false (abc relative order is violated: c before b, a)
        String r3 = "adbecf"; // true

        System.out.println("Is \"" + r1 + "\" a valid shuffle of \"" + s1 + "\" & \"" + s2 + "\"? " + isValidShuffle(s1, s2, r1));
        System.out.println("Is \"" + r2 + "\" a valid shuffle of \"" + s1 + "\" & \"" + s2 + "\"? " + isValidShuffle(s1, s2, r2));
        System.out.println("Is \"" + r3 + "\" a valid shuffle of \"" + s1 + "\" & \"" + s2 + "\"? " + isValidShuffle(s1, s2, r3));

        // Test with duplicates
        String s1_dup = "aab";
        String s2_dup = "aac";
        String r_dup = "aacaab"; // true
        System.out.println("Is \"" + r_dup + "\" a valid shuffle of \"" + s1_dup + "\" & \"" + s2_dup + "\"? " + isValidShuffle(s1_dup, s2_dup, r_dup));
    }
}
