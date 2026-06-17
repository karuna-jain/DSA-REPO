// Write a Code to check whether one string is a rotation of another
// Time Complexity: O(N) where N is the length of the string s1 (assuming contains() search is linear)
// Space Complexity: O(N) auxiliary space to construct the concatenated temporary string

public class StringRotation {
    /**
     * Checks if string s2 is a valid rotation of s1.
     * Core logic: If s2 is a rotation of s1, then s2 must be a substring of (s1 + s1),
     * and their lengths must be exactly equal.
     * 
     * @param s1 The original string
     * @param s2 The rotated target string to verify
     * @return true if s2 is a rotation of s1, false otherwise
     */
    public static boolean isRotation(String s1, String s2) {
        // If either string is null or their lengths differ, they cannot be rotations
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        // If both are empty and of the same length, they are trivially rotations
        if (s1.isEmpty()) {
            return true;
        }
        
        // Concatenate the original string s1 with itself.
        // For s1 = "ABCD", temp = "ABCDABCD".
        // Notice that "ABCDABCD" contains all possible rotations of "ABCD" as substrings:
        // "ABCD", "BCDA", "CDAB", "DABC".
        String temp = s1 + s1;
        
        // Check if the rotated candidate s2 is present as a substring of temp
        return temp.contains(s2);
    }

    public static void main(String[] args) {
        // Define test cases
        String s1 = "ABCD";
        String s2 = "CDAB";
        String s3 = "ACBD";

        // Print evaluations
        System.out.println("Is \"" + s2 + "\" a rotation of \"" + s1 + "\"? " + isRotation(s1, s2)); // true
        System.out.println("Is \"" + s3 + "\" a rotation of \"" + s1 + "\"? " + isRotation(s1, s3)); // false
        System.out.println("Is \"\" a rotation of \"\"? " + isRotation("", "")); // true
    }
}
