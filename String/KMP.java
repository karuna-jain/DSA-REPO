// KMP Algorithm
// Time Complexity: O(N + M) where N is the length of text and M is the length of pattern
// Space Complexity: O(M) for the LPS array storing pattern prefix information

import java.util.ArrayList;
import java.util.List;

public class KMP {
    /**
     * Searches for occurrences of a pattern within a text using the Knuth-Morris-Pratt (KMP) algorithm.
     * 
     * @param pat The pattern to search for
     * @param txt The text containing the content
     * @return List of starting indices where the pattern is found in the text
     */
    public static List<Integer> search(String pat, String txt) {
        List<Integer> result = new ArrayList<>();

        if (pat == null || txt == null) {
            return result;
        }

        int m = pat.length();
        int n = txt.length();

        // Edge case: Empty pattern or pattern is longer than the text
        if (m == 0 || m > n) {
            return result;
        }

        // Create lps[] that will hold the longest prefix suffix values for pattern
        int[] lps = computeLPSArray(pat);

        int i = 0; // index for txt[]
        int j = 0; // index for pat[]

        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                // Pattern match found at index (i - j)
                result.add(i - j);
                // Backtrack using the LPS array to look for overlapping matches
                j = lps[j - 1];
            } 
            // Mismatch after j matches
            else if (i < n && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters, they will match anyway
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    /**
     * Precomputes the Longest Proper Prefix which is also Suffix (LPS) array for the pattern.
     * 
     * @param pat The pattern string
     * @return The LPS array
     */
    private static int[] computeLPSArray(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;
        
        lps[0] = 0; // lps[0] is always 0

        // The loop calculates lps[i] for i = 1 to m-1
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // (pat.charAt(i) != pat.charAt(len))
                if (len != 0) {
                    len = lps[len - 1];
                    // Also note that we do not increment i here
                } else {
                    // if (len == 0)
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }

    public static void main(String[] args) {
        String txt1 = "ABABDABACDABABCABAB";
        String pat1 = "ABABCABAB";
        System.out.println("Text: \"" + txt1 + "\", Pattern: \"" + pat1 + "\" -> Match indices: " + search(pat1, txt1)); // Expected: [10]

        String txt2 = "AABAACAADAABAABA";
        String pat2 = "AABA";
        System.out.println("Text: \"" + txt2 + "\", Pattern: \"" + pat2 + "\" -> Match indices: " + search(pat2, txt2)); // Expected: [0, 9, 12]

        String txt3 = "AAAAA";
        String pat3 = "AA";
        System.out.println("Text: \"" + txt3 + "\", Pattern: \"" + pat3 + "\" -> Match indices: " + search(pat3, txt3)); // Expected: [0, 1, 2, 3]
    }
}
