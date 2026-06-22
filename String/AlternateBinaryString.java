// GeeksforGeeks: Number of flips to make binary string alternate
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(1) auxiliary space

public class AlternateBinaryString {

    /**
     * Finds the minimum number of flips required to make a binary string alternating.
     * @param s The binary string
     * @return The minimum number of flips
     */
    public static int minFlips(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int flipsForPattern0 = 0; // count of flips if pattern starts with '0' (e.g. 0101...)
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char expectedChar = (i % 2 == 0) ? '0' : '1';
            if (s.charAt(i) != expectedChar) {
                flipsForPattern0++;
            }
        }

        // If the string starts with '1', the flips required would be (n - flipsForPattern0)
        int flipsForPattern1 = n - flipsForPattern0;

        return Math.min(flipsForPattern0, flipsForPattern1);
    }

    public static void main(String[] args) {
        String s1 = "0001010111";
        System.out.println("Input: \"0001010111\"");
        System.out.println("Expected: 2, Actual: " + minFlips(s1));
        System.out.println();

        String s2 = "001";
        System.out.println("Input: \"001\"");
        System.out.println("Expected: 1, Actual: " + minFlips(s2));
        System.out.println();

        String s3 = "1111";
        System.out.println("Input: \"1111\"");
        System.out.println("Expected: 2, Actual: " + minFlips(s3));
    }
}
