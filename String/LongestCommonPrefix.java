// LeetCode 14: Longest Common Prefix
// Time Complexity: O(N * M) where N is the number of strings and M is the length of the shortest string
// Space Complexity: O(1) auxiliary space

public class LongestCommonPrefix {

    /**
     * Finds the longest common prefix string amongst an array of strings.
     * @param strs The array of strings
     * @return The longest common prefix, or "" if there is none
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Vertical scanning
        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                // If the index exceeds current string's length or characters mismatch
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    public static void main(String[] args) {
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("Input: [\"flower\", \"flow\", \"flight\"]");
        System.out.println("Expected: \"fl\", Actual: \"" + longestCommonPrefix(strs1) + "\"");
        System.out.println();

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Input: [\"dog\", \"racecar\", \"car\"]");
        System.out.println("Expected: \"\", Actual: \"" + longestCommonPrefix(strs2) + "\"");
        System.out.println();

        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        System.out.println("Input: [\"interspecies\", \"interstellar\", \"interstate\"]");
        System.out.println("Expected: \"inters\", Actual: \"" + longestCommonPrefix(strs3) + "\"");
    }
}
