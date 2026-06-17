// Check whether a String is Palindrome or not
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(1) auxiliary space (two-pointer comparison)

public class PalindromeCheck {
    /**
     * Basic exact character-by-character palindrome check.
     * Checks if the string reads the same backward as forward, case-sensitively.
     * 
     * @param str The string to inspect
     * @return true if exact palindrome, false otherwise
     */
    public static boolean isPalindrome(String str) {
        // Edge case: A null reference is not a valid palindrome
        if (str == null) return false;
        
        // Initialize two pointers: left scanning forward, right scanning backward
        int left = 0;
        int right = str.length() - 1;
        
        // Loop until pointers meet in the middle
        while (left < right) {
            // If characters at boundary pointers mismatch, it's not a palindrome
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            
            // Advance pointers inwards
            left++;
            right--;
        }
        
        // If pointers successfully meet, all bounds matched correctly
        return true;
    }

    /**
     * Alphanumeric case-insensitive palindrome check (LeetCode 125 style).
     * Ignores non-alphanumeric characters (spaces, punctuation) and matches case-insensitively.
     * 
     * @param str The string to inspect
     * @return true if alphanumeric palindrome, false otherwise
     */
    public static boolean isPalindromeAlphanumeric(String str) {
        // Edge case: Null reference is invalid
        if (str == null) return false;
        
        // Initialize boundary pointers
        int left = 0;
        int right = str.length() - 1;
        
        // Loop inwards
        while (left < right) {
            // Skip non-alphanumeric characters from the left side
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from the right side
            while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }
            
            // Convert characters to lowercase and compare them
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                return false;
            }
            
            // Advance pointers
            left++;
            right--;
        }
        
        // Return true if all checks passed
        return true;
    }

    public static void main(String[] args) {
        // Define representative test cases
        String[] tests = {"racecar", "hello", "A man, a plan, a canal: Panama", " ", "ab_a"};
        
        // Execute and print evaluations
        for (String test : tests) {
            System.out.println("'" + test + "'");
            System.out.println("  Is Exact Palindrome: " + isPalindrome(test));
            System.out.println("  Is Alphanumeric Palindrome: " + isPalindromeAlphanumeric(test));
        }
    }
}
