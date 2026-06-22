// GeeksforGeeks: Find the first repeated word in string
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(K) where K is the number of unique words

import java.util.HashSet;

public class FirstRepeatedWord {

    /**
     * Finds the first repeated word in the given string.
     * The first repeated word is defined as the word whose second occurrence appears earliest.
     * @param s The input string
     * @return The first repeated word, or null if there are no repeats
     */
    public static String findFirstRepeatedWord(String s) {
        if (s == null || s.trim().isEmpty()) {
            return null;
        }

        // Split by spaces and common punctuation marks
        String[] words = s.split("[\\s.,!?;:]+");
        HashSet<String> seen = new HashSet<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            // If the word has already been seen, it is the first repeated word
            if (seen.contains(word)) {
                return word;
            }
            seen.add(word);
        }

        return null; // No repeated word found
    }

    public static void main(String[] args) {
        String s1 = "Ravi had been saying that he had been there";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Expected: \"had\", Actual: \"" + findFirstRepeatedWord(s1) + "\"");
        System.out.println();

        String s2 = "He had quite a lot of work, and he did it.";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Expected: null (case-sensitive: 'He' != 'he'), Actual: \"" + findFirstRepeatedWord(s2) + "\"");
        System.out.println();

        String s3 = "No repeated words here";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Expected: null, Actual: " + findFirstRepeatedWord(s3));
    }
}
