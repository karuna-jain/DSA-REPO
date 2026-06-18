// Print all Subsequences of a string
// Time Complexity: O(2^N) where N is the length of the string
// Space Complexity: O(N) auxiliary space (recursion stack depth)

import java.util.ArrayList;
import java.util.List;

public class PrintSubsequences {
    /**
     * Generates all subsequences of a string using backtracking.
     * 
     * @param str The input string
     * @return List of all subsequences
     */
    public static List<String> getSubsequences(String str) {
        List<String> result = new ArrayList<>();
        if (str == null) {
            return result;
        }
        generate(str, 0, new StringBuilder(), result);
        return result;
    }

    private static void generate(String str, int index, StringBuilder current, List<String> result) {
        // Base case: if we've made a decision for every character in the string
        if (index == str.length()) {
            result.add(current.toString());
            return;
        }

        // Option 1: Exclude the current character
        generate(str, index + 1, current, result);

        // Option 2: Include the current character
        current.append(str.charAt(index));
        generate(str, index + 1, current, result);
        current.deleteCharAt(current.length() - 1); // Backtrack
    }

    public static void main(String[] args) {
        String testInput = "abc";
        System.out.println("Generating subsequences for: \"" + testInput + "\"");
        
        List<String> subsequences = getSubsequences(testInput);
        System.out.println("Total Subsequences: " + subsequences.size());
        System.out.println("Subsequences list: " + subsequences);
        
        System.out.println("\n--- Testing empty and single char cases ---");
        System.out.println("Subsequences of \"\": " + getSubsequences(""));
        System.out.println("Subsequences of \"a\": " + getSubsequences("a"));
    }
}
