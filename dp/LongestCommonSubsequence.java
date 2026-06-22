// LeetCode 1143: Longest Common Subsequence
// Time Complexity: O(M * N) where M and N are lengths of the two strings
// Space Complexity: O(M * N) auxiliary space (using dynamic programming table)

public class LongestCommonSubsequence {

    /**
     * Finds the length of the longest common subsequence of two strings.
     * @param text1 First string
     * @param text2 Second string
     * @return The length of the longest common subsequence
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Bottom-up Dynamic Programming tabulation
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String t1 = "abcde";
        String t2 = "ace";
        System.out.println("Text1: \"abcde\", Text2: \"ace\"");
        System.out.println("Expected: 3, Actual: " + longestCommonSubsequence(t1, t2));
        System.out.println();

        String t3 = "abc";
        String t4 = "abc";
        System.out.println("Text1: \"abc\", Text2: \"abc\"");
        System.out.println("Expected: 3, Actual: " + longestCommonSubsequence(t3, t4));
        System.out.println();

        String t5 = "abc";
        String t6 = "def";
        System.out.println("Text1: \"abc\", Text2: \"def\"");
        System.out.println("Expected: 0, Actual: " + longestCommonSubsequence(t5, t6));
    }
}
