// Find Longest Recurring Subsequence in String
// Time Complexity: O(N^2) where N is the length of the string
// Space Complexity: O(N^2) to store the DP table

public class LongestRecurringSubsequence {
    /**
     * Finds the length of the longest recurring subsequence in the given string.
     * A recurring subsequence is a subsequence that appears at least twice in the string
     * with different index combinations.
     * 
     * @param str The input string
     * @return The length of the longest recurring subsequence
     */
    public static int findLRS(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match and indices are different
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][n];
    }

    /**
     * Reconstructs and returns the actual longest recurring subsequence.
     * 
     * @param str The input string
     * @return The longest recurring subsequence string
     */
    public static String getLRSString(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        // Fill DP table first
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct the string from DP table
        StringBuilder res = new StringBuilder();
        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                res.append(str.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // The reconstructed string will be in reverse order
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String[] testStrings = {
            "AABEBCDD",
            "aabb",
            "axxxy",
            "aab",
            "abc"
        };

        for (String test : testStrings) {
            int length = findLRS(test);
            String lrs = getLRSString(test);
            System.out.println("String: \"" + test + "\"");
            System.out.println("  LRS Length: " + length);
            System.out.println("  LRS String: \"" + lrs + "\"");
            System.out.println();
        }
    }
}
