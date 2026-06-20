// Convert a Sentence into its equivalent mobile numeric keypad sequence
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(N) for constructing the output string

public class MobileNumericKeypad {
    /**
     * Converts a given sentence into its equivalent mobile numeric keypad sequence.
     * Maps spaces to '0' and English letters (both uppercase and lowercase) to key presses.
     * Other characters are ignored.
     * 
     * @param sentence The input sentence
     * @return The keypad sequence as a String
     */
    public static String convertToNumericSequence(String sentence) {
        if (sentence == null) {
            return null;
        }

        // Map for characters 'A' through 'Z'
        String[] keypadMap = {
            "2", "22", "222",      // A, B, C
            "3", "33", "333",      // D, E, F
            "4", "44", "444",      // G, H, I
            "5", "55", "555",      // J, K, L
            "6", "66", "666",      // M, N, O
            "7", "77", "777", "7777", // P, Q, R, S
            "8", "88", "888",      // T, U, V
            "9", "99", "999", "9999"  // W, X, Y, Z
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);

            // Handle space
            if (ch == ' ') {
                result.append("0");
            } 
            // Handle uppercase letters
            else if (ch >= 'A' && ch <= 'Z') {
                result.append(keypadMap[ch - 'A']);
            } 
            // Handle lowercase letters (case-insensitive conversion)
            else if (ch >= 'a' && ch <= 'z') {
                result.append(keypadMap[ch - 'a']);
            }
            // Non-alphabetic and non-space characters are skipped (or can be appended as-is, standard is to ignore)
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String test1 = "GEEKSFORGEEKS";
        String test2 = "HELLO WORLD";
        String test3 = "a b c";
        String test4 = "";

        System.out.println("Input: \"" + test1 + "\" -> Output: \"" + convertToNumericSequence(test1) + "\"");
        System.out.println("Input: \"" + test2 + "\" -> Output: \"" + convertToNumericSequence(test2) + "\"");
        System.out.println("Input: \"" + test3 + "\" -> Output: \"" + convertToNumericSequence(test3) + "\"");
        System.out.println("Input: \"" + test4 + "\" -> Output: \"" + convertToNumericSequence(test4) + "\"");
    }
}
