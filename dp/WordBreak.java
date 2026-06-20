// Word Break Problem
// Time Complexity: O(N^2) where N is the length of the string
// Space Complexity: O(N) for the DP array and dictionary set

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    /**
     * Determines if a string can be segmented into space-separated sequence of dictionary words.
     * 
     * @param s The input string
     * @param wordDict The list of dictionary words
     * @return True if the string can be segmented, false otherwise
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null) {
            return false;
        }

        // Convert the dictionary list to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // Find the maximum length of any word in the dictionary to optimize substring search
        int maxWordLength = 0;
        for (String word : wordDict) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }

        // dp[i] will be true if s[0...i-1] can be segmented into dictionary words
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        
        // Base case: An empty string can always be segmented
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            // Check substrings starting from j and ending at i
            // We only need to check j such that the word length (i - j) does not exceed maxWordLength
            int startLimit = Math.max(0, i - maxWordLength);
            for (int j = i - 1; j >= startLimit; j--) {
                // If s[0...j-1] is segmentable (dp[j]) and s[j...i-1] is a valid word, then dp[i] is true
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Word breaks found for index i, no need to search further
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> dict1 = new ArrayList<>();
        dict1.add("leet");
        dict1.add("code");

        String s2 = "applepenapple";
        List<String> dict2 = new ArrayList<>();
        dict2.add("apple");
        dict2.add("pen");

        String s3 = "catsandog";
        List<String> dict3 = new ArrayList<>();
        dict3.add("cats");
        dict3.add("dog");
        dict3.add("sand");
        dict3.add("and");
        dict3.add("cat");

        System.out.println("String: \"" + s1 + "\" -> Word Break: " + wordBreak(s1, dict1)); // Expected: true
        System.out.println("String: \"" + s2 + "\" -> Word Break: " + wordBreak(s2, dict2)); // Expected: true
        System.out.println("String: \"" + s3 + "\" -> Word Break: " + wordBreak(s3, dict3)); // Expected: false
    }
}
