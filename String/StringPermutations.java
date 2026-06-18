// Print all the permutations of the given string
// Time Complexity: O(N * N!) where N is the length of the string
// Space Complexity: O(N * N!) to store results, or O(N) auxiliary stack space

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringPermutations {
    /**
     * Generates all unique permutations of a string.
     * 
     * @param str The input string
     * @return List of all unique permutations in sorted order
     */
    public static List<String> getPermutations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return result;
        }
        
        char[] chars = str.toCharArray();
        Set<String> uniquePerms = new HashSet<>();
        backtrack(chars, 0, uniquePerms);
        
        result.addAll(uniquePerms);
        Collections.sort(result); // Optional: sort lexicographically
        return result;
    }

    private static void backtrack(char[] chars, int index, Set<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            backtrack(chars, index + 1, result);
            swap(chars, index, i); // Backtrack
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String testInput1 = "abc";
        System.out.println("Permutations of \"" + testInput1 + "\":");
        List<String> perms1 = getPermutations(testInput1);
        System.out.println("  Total count: " + perms1.size());
        System.out.println("  Permutations: " + perms1);
        System.out.println();

        String testInput2 = "aac";
        System.out.println("Permutations of \"" + testInput2 + "\" (with duplicates):");
        List<String> perms2 = getPermutations(testInput2);
        System.out.println("  Total count: " + perms2.size());
        System.out.println("  Permutations: " + perms2);
    }
}
