// Find Duplicate characters in a string
// Time Complexity: O(N)
// Space Complexity: O(1) (Since character set size is bounded/constant)

import java.util.HashMap;
import java.util.Map;

public class DuplicateCharacters {
    public static void printDuplicates(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("String is empty or null.");
            return;
        }

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        boolean found = false;
        System.out.print("Duplicate characters in \"" + str + "\": ");
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.print("'" + entry.getKey() + "' (Count: " + entry.getValue() + ") ");
                found = true;
            }
        }
        if (!found) {
            System.out.print("None");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printDuplicates("geeksforgeeks");
        printDuplicates("hello");
        printDuplicates("abc");
        printDuplicates("Java programming");
    }
}
