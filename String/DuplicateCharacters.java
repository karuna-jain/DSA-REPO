// Find Duplicate characters in a string
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(K) where K is the number of unique characters in the string (bounded by alphabet size, i.e., O(1) auxiliary space)

import java.util.HashMap;
import java.util.Map;

public class DuplicateCharacters {
    /**
     * Finds and prints characters in the input string that appear more than once.
     * Utilizes a Hash Map to compute frequency count.
     * 
     * @param str The string to analyze
     */
    public static void printDuplicates(String str) {
        // Handle boundary validation for empty or null strings
        if (str == null || str.isEmpty()) {
            System.out.println("String is empty or null.");
            return;
        }

        // Initialize Hash Map to store character counts
        Map<Character, Integer> charCountMap = new HashMap<>();
        
        // Traverse character array and increment counts in map
        for (char c : str.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // Track if any duplicates are detected
        boolean found = false;
        System.out.print("Duplicate characters in \"" + str + "\": ");
        
        // Iterate over entry set of the map to print keys with value > 1
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.print("'" + entry.getKey() + "' (Count: " + entry.getValue() + ") ");
                found = true;
            }
        }
        
        // Output fallback message if no characters are repeated
        if (!found) {
            System.out.print("None");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Run test scenarios
        printDuplicates("geeksforgeeks");
        printDuplicates("hello");
        printDuplicates("abc");
        printDuplicates("Java programming");
    }
}
