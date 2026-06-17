// Write a Code to check whether one string is a rotation of another
// Time Complexity: O(N) (using substring check contains())
// Space Complexity: O(N) (due to concatenated temporary string)

public class StringRotation {
    public static boolean isRotation(String s1, String s2) {
        // If lengths are different or either is null, they cannot be rotations
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        // If they are empty and of same length, they are rotations
        if (s1.isEmpty()) {
            return true;
        }
        // Concatenate s1 with itself
        String temp = s1 + s1;
        // Check if s2 is a substring of s1 + s1
        return temp.contains(s2);
    }

    public static void main(String[] args) {
        String s1 = "ABCD";
        String s2 = "CDAB";
        String s3 = "ACBD";

        System.out.println("Is \"" + s2 + "\" a rotation of \"" + s1 + "\"? " + isRotation(s1, s2)); // true
        System.out.println("Is \"" + s3 + "\" a rotation of \"" + s1 + "\"? " + isRotation(s1, s3)); // false
        System.out.println("Is \"\" a rotation of \"\"? " + isRotation("", "")); // true
    }
}
