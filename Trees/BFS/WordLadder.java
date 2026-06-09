import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 127: Word Ladder
 * Problem: Given two words (beginWord and endWord), and a dictionary's word list, 
 * find the length of shortest transformation sequence from beginWord to endWord.
 * Only one letter can be changed at a time, and each transformed word must exist in the word list.
 * 
 * Time Complexity: O(M^2 * N) - Where M is the length of each word and N is the total number of words in the input word list.
 *                  For each word of length M, we try to change each of its characters to 'a'-'z' (26 possibilities).
 *                  Generating the new string takes O(M). Checking membership and queue operations are O(M).
 * Space Complexity: O(M^2 * N) - To store visited states and the queue of words.
 */
public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Using a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // If we reached the target word, return the number of transformations (level)
                if (currentWord.equals(endWord)) {
                    return level;
                }

                char[] wordChars = currentWord.toCharArray();
                // Check all possible single-character modifications
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue;
                        }

                        wordChars[j] = c;
                        String nextWord = new String(wordChars);

                        if (wordSet.contains(nextWord) && !visited.contains(nextWord)) {
                            visited.add(nextWord);
                            queue.add(nextWord);
                        }
                    }
                    // Restore original char
                    wordChars[j] = originalChar;
                }
            }
            level++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Transformation exists ---");
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result1 = ladderLength(beginWord1, endWord1, wordList1);
        System.out.println("Begin: " + beginWord1 + ", End: " + endWord1);
        System.out.println("Word List: " + wordList1);
        System.out.println("Length of shortest path: " + result1); // Expected: 5 (hit -> hot -> dot -> dog -> cog)

        System.out.println("\n--- Test Case 2: Target not in word list ---");
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        int result2 = ladderLength(beginWord2, endWord2, wordList2);
        System.out.println("Begin: " + beginWord2 + ", End: " + endWord2);
        System.out.println("Word List: " + wordList2);
        System.out.println("Length of shortest path: " + result2); // Expected: 0
    }
}
