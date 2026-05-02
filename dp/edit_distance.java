//Given two strings s1 and s2. Return the minimum number of operations required to convert s1 to s2.
// The possible operations are permitted:
// 
// Insert a character at any position of the string.
// Remove any character from the string.
// Replace any character from the string with any other character.

import java.util.*;

class edit_distance {

    // Recursive function with memoization
    public int solve(String s1, String s2, int i, int j, int[][] dp) {

        // Base Case:
        // If s1 खत्म हो गया → remaining characters of s2 insert करने पड़ेंगे
        if (i < 0)
            return j + 1;

        // If s2 खत्म हो गया → remaining characters of s1 delete करने पड़ेंगे
        if (j < 0)
            return i + 1;

        // अगर पहले से calculate किया हुआ है → directly return
        if (dp[i][j] != -1)
            return dp[i][j];

        // अगर characters same हैं → कोई operation नहीं
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = solve(s1, s2, i - 1, j - 1, dp);
        } else {
            // तीन operations try करते हैं:

            // Insert → s2 में move (j-1)
            int insert = solve(s1, s2, i, j - 1, dp);

            // Delete → s1 में move (i-1)
            int delete = solve(s1, s2, i - 1, j, dp);

            // Replace → दोनों में move (i-1, j-1)
            int replace = solve(s1, s2, i - 1, j - 1, dp);

            // minimum operation choose करेंगे
            return dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
        }
    }

    // Main function
    public int editDistance(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // dp[i][j] = answer for s1[0..i] and s2[0..j]
        int[][] dp = new int[n][m];

        // initialize with -1 (means not calculated yet)
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // recursion start from last index
        return solve(s1, s2, n - 1, m - 1, dp);
    }

    // Driver code
    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";

        edit_distance ed = new edit_distance();

        // Expected output: 3
        System.out.println(ed.editDistance(s1, s2));
    }
}