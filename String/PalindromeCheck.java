// Check whether a String is Palindrome or not
// Time Complexity: O(N)
// Space Complexity: O(1)

public class PalindromeCheck {
    // Basic exact check
    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Alphanumeric case-insensitive check (LeetCode style)
    public static boolean isPalindromeAlphanumeric(String str) {
        if (str == null) return false;
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(str.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(str.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(str.charAt(left)) != Character.toLowerCase(str.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] tests = {"racecar", "hello", "A man, a plan, a canal: Panama", " ", "ab_a"};
        for (String test : tests) {
            System.out.println("'" + test + "'");
            System.out.println("  Is Exact Palindrome: " + isPalindrome(test));
            System.out.println("  Is Alphanumeric Palindrome: " + isPalindromeAlphanumeric(test));
        }
    }
}
