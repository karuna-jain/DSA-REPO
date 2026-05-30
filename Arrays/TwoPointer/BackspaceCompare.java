// LeetCode 844: Backspace String Compare
// Time Complexity: O(N + M) where N and M are the lengths of strings S and T
// Space Complexity: O(1) auxiliary space

public class BackspaceCompare {

    /**
     * Checks if two strings are equal after processing backspaces ('#').
     * @param s The first string
     * @param t The second string
     * @return True if they are equal, false otherwise
     */
    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            // Find the next active character in string s
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            // Find the next active character in string t
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // Compare active characters
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                // If one string has active characters left and the other doesn't
                if ((i >= 0) != (j >= 0)) {
                    return false;
                }
            }

            i--;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "ab#c", t1 = "ad#c";
        System.out.println("Comparing \"" + s1 + "\" and \"" + t1 + "\": " + backspaceCompare(s1, t1)); // Expected: true

        String s2 = "ab##", t2 = "c#d#";
        System.out.println("Comparing \"" + s2 + "\" and \"" + t2 + "\": " + backspaceCompare(s2, t2)); // Expected: true

        String s3 = "a#c", t3 = "b";
        System.out.println("Comparing \"" + s3 + "\" and \"" + t3 + "\": " + backspaceCompare(s3, t3)); // Expected: false
    }
}
