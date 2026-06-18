// Split the Binary string into substrings with equal 0’s and 1’s
// Time Complexity: O(N) where N is the length of the binary string
// Space Complexity: O(1) auxiliary space

public class SplitBinaryString {
    /**
     * Splits a binary string into the maximum number of consecutive substrings 
     * where each substring contains an equal count of '0's and '1's.
     * 
     * @param str The binary string
     * @return The maximum number of split substrings, or -1 if the string cannot be split
     */
    public static int maxSubstrings(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }

        int count0 = 0;
        int count1 = 0;
        int substringCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '0') {
                count0++;
            } else if (ch == '1') {
                count1++;
            }

            // Whenever the count of 0s and 1s becomes equal, we increment the substring count
            if (count0 == count1) {
                substringCount++;
            }
        }

        // If the entire string is traversed and counts are not equal, then it is impossible to split
        if (count0 != count1) {
            return -1;
        }

        return substringCount;
    }

    public static void main(String[] args) {
        String[] testStrings = {
            "0100110101", // "01", "0011", "01", "01" -> 4 substrings
            "0111100010", // "01", "111000", "10" -> 3 substrings
            "0000000000", // Impossible -> -1
            "1010",       // "10", "10" -> 2 substrings
            "001"         // Impossible -> -1
        };

        for (String test : testStrings) {
            int result = maxSubstrings(test);
            System.out.println("Binary String: \"" + test + "\"");
            System.out.println("  Max Balanced Substrings: " + result);
            System.out.println();
        }
    }
}
