// Count All Palindromic Subsequences in a given String
// Time Complexity: O(N^2) where N is the length of the string
// Space Complexity: O(N^2) for the DP table

public class CountPalindromicSubsequences {
    /**
     * Counts all palindromic subsequences in a given string.
     * Calculated modulo 10^9 + 7 to handle large results.
     * 
     * @param str The input string
     * @return The total number of palindromic subsequences modulo 10^9 + 7
     */
    public static int countPalindromicSubsequences(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n][n];
        int MOD = 1000000007;

        // Base case: Substrings of length 1 are always palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the DP table bottom-up
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (str.charAt(i) == str.charAt(j)) {
                    // If first and last characters are equal:
                    // dp[i][j] = dp[i+1][j] + dp[i][j-1] + 1
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] + 1) % MOD;
                } else {
                    // If first and last characters are not equal:
                    // dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1]
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]) % MOD;
                    
                    // Handle negative values in Java modulo arithmetic
                    if (dp[i][j] < 0) {
                        dp[i][j] += MOD;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String test1 = "aba";
        String test2 = "abcd";
        String test3 = "aaaa";
        String test4 = "a";

        System.out.println("String: \"" + test1 + "\" -> Palindromic Subsequences: " + countPalindromicSubsequences(test1)); // Expected: 5
        System.out.println("String: \"" + test2 + "\" -> Palindromic Subsequences: " + countPalindromicSubsequences(test2)); // Expected: 4
        System.out.println("String: \"" + test3 + "\" -> Palindromic Subsequences: " + countPalindromicSubsequences(test3)); // Expected: 15
        System.out.println("String: \"" + test4 + "\" -> Palindromic Subsequences: " + countPalindromicSubsequences(test4)); // Expected: 1
    }
}
