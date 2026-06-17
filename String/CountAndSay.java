// Count and Say problem
// Time Complexity: O(2^N) approximately due to exponential growth of successive string lengths
// Space Complexity: O(2^N) to store the generated run-length encoding string

public class CountAndSay {
    /**
     * Generates the n-th term of the count-and-say sequence.
     * The sequence is recursive, defined as:
     * - countAndSay(1) = "1"
     * - countAndSay(n) = run-length encoding of countAndSay(n-1)
     * 
     * @param n The term index of the sequence to generate
     * @return The sequence term as a String
     */
    public static String countAndSay(int n) {
        // Handle invalid inputs
        if (n <= 0) return "";
        
        // Base case: The first term is always "1"
        String current = "1";
        
        // Iteratively compute subsequent terms from 2 up to n
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int len = current.length();
            int count = 1; // Tracks occurrences of the current repeating character
            
            // Scan through the characters of the current string
            for (int j = 1; j < len; j++) {
                // If character is identical to the previous, increment count
                if (current.charAt(j) == current.charAt(j - 1)) {
                    count++;
                } else {
                    // Otherwise, record current count and character, then reset count to 1
                    next.append(count).append(current.charAt(j - 1));
                    count = 1;
                }
            }
            // Append the final group's count and character value
            next.append(count).append(current.charAt(len - 1));
            
            // Update reference for the next iteration pass
            current = next.toString();
        }
        
        return current;
    }

    public static void main(String[] args) {
        // Output terms for validation
        for (int i = 1; i <= 6; i++) {
            System.out.println("n = " + i + ": " + countAndSay(i));
        }
    }
}
