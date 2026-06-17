// Write a program to find the longest Palindrome in a string. [Longest palindromic Substring]
// Time Complexity: O(N^2)
// Space Complexity: O(1) (Expand Around Center approach)

public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes, centered at i
            int len1 = expandAroundCenter(s, i, i);
            // Even length palindromes, centered between i and i + 1
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "racecar";

        System.out.println("Longest palindrome in \"" + s1 + "\": " + longestPalindrome(s1)); // "bab" or "aba"
        System.out.println("Longest palindrome in \"" + s2 + "\": " + longestPalindrome(s2)); // "bb"
        System.out.println("Longest palindrome in \"" + s3 + "\": " + longestPalindrome(s3)); // "a"
        System.out.println("Longest palindrome in \"" + s4 + "\": " + longestPalindrome(s4)); // "racecar"
    }
}
