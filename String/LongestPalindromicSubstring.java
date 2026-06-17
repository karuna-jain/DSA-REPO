// Write a program to find the longest Palindrome in a string. [Longest palindromic Substring]
// Time Complexity: O(N^2) where N is the length of the string
// Space Complexity: O(1) auxiliary space (using Expand Around Center approach)

public class LongestPalindromicSubstring {
    /**
     * Finds the longest palindromic substring in the input string.
     * Evaluates centers of potential palindromes at each index (both odd and even lengths).
     * 
     * @param s The source string
     * @return The longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        // If string is null or empty, return an empty string
        if (s == null || s.length() < 1) {
            return "";
        }
        
        // Track the starting and ending indices of the longest palindrome found so far
        int start = 0, end = 0;
        
        // Loop over each index, treating it as the center of potential palindromes
        for (int i = 0; i < s.length(); i++) {
            
            // Odd length palindromes: centered at index 'i' (e.g. "aba", center is 'b')
            int len1 = expandAroundCenter(s, i, i);
            
            // Even length palindromes: centered between index 'i' and 'i+1' (e.g. "abba", center is between 'b' and 'b')
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Determine the maximum length found for the current center i
            int len = Math.max(len1, len2);
            
            // If the found palindrome is longer than the previous record, update start and end bounds
            if (len > end - start) {
                // Calculate new start position
                start = i - (len - 1) / 2;
                // Calculate new end position
                end = i + len / 2;
            }
        }
        
        // Return the substring corresponding to the longest bounds detected
        return s.substring(start, end + 1);
    }

    /**
     * Helper helper to expand outward from a given center point as long as bounds match.
     * 
     * @param s The source string
     * @param left The left index pointer
     * @param right The right index pointer
     * @return The length of the verified palindrome segment
     */
    private static int expandAroundCenter(String s, int left, int right) {
        // Expand outwards as long as left and right pointers are inside string bounds
        // and characters at those positions match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // Return the length of the palindrome substring.
        // After exiting the loop, left and right pointers have moved 1 step beyond the palindrome bounds,
        // so length is: (right - 1) - (left + 1) + 1 = right - left - 1.
        return right - left - 1;
    }

    public static void main(String[] args) {
        // Define representative test cases
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "a";
        String s4 = "racecar";

        // Print output evaluations
        System.out.println("Longest palindrome in \"" + s1 + "\": " + longestPalindrome(s1)); // "bab" or "aba"
        System.out.println("Longest palindrome in \"" + s2 + "\": " + longestPalindrome(s2)); // "bb"
        System.out.println("Longest palindrome in \"" + s3 + "\": " + longestPalindrome(s3)); // "a"
        System.out.println("Longest palindrome in \"" + s4 + "\": " + longestPalindrome(s4)); // "racecar"
    }
}
