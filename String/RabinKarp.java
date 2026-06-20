// Rabin Karp Algorithm
// Time Complexity: O(N + M) average and best case, O(N * M) worst case (due to hash collisions)
// Space Complexity: O(1) auxiliary space (returning index list is O(K) where K is occurrences)

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
    // Number of characters in the input alphabet (base value)
    private static final int D = 256;
    
    // A prime number for hashing (helps reduce collisions)
    private static final int Q = 101;

    /**
     * Searches for a pattern within a text using the Rabin-Karp rolling hash algorithm.
     * 
     * @param pat The pattern to search for
     * @param txt The text containing the content
     * @return List of indices where the pattern starts in the text
     */
    public static List<Integer> search(String pat, String txt) {
        List<Integer> result = new ArrayList<>();
        
        if (pat == null || txt == null) {
            return result;
        }

        int m = pat.length();
        int n = txt.length();
        
        // If pattern is empty or longer than text, no match is possible
        if (m == 0 || m > n) {
            return result;
        }

        int pHash = 0; // Hash value for pattern
        int tHash = 0; // Hash value for text window
        int h = 1;

        // The value of h would be "pow(d, m-1) % q"
        for (int i = 0; i < m - 1; i++) {
            h = (h * D) % Q;
        }

        // Calculate the hash value of pattern and first window of text
        for (int i = 0; i < m; i++) {
            pHash = (D * pHash + pat.charAt(i)) % Q;
            tHash = (D * tHash + txt.charAt(i)) % Q;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= n - m; i++) {
            // Check the hash values of current window of text and pattern.
            // If the hash values match then only check for characters one by one.
            if (pHash == tHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                
                // If pHash == tHash and character matching matches, add to result
                if (match) {
                    result.add(i);
                }
            }

            // Calculate hash value for next window of text: Remove leading digit, add trailing digit
            if (i < n - m) {
                tHash = (D * (tHash - txt.charAt(i) * h) + txt.charAt(i + m)) % Q;

                // We might get negative value of tHash, converting it to positive
                if (tHash < 0) {
                    tHash = (tHash + Q);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String txt1 = "GEEKS FOR GEEKS";
        String pat1 = "GEEK";
        System.out.println("Text: \"" + txt1 + "\", Pattern: \"" + pat1 + "\" -> Match indices: " + search(pat1, txt1));

        String txt2 = "AABAACAADAABAABA";
        String pat2 = "AABA";
        System.out.println("Text: \"" + txt2 + "\", Pattern: \"" + pat2 + "\" -> Match indices: " + search(pat2, txt2));

        String txt3 = "AAAAA";
        String pat3 = "AA";
        System.out.println("Text: \"" + txt3 + "\", Pattern: \"" + pat3 + "\" -> Match indices: " + search(pat3, txt3));
    }
}
