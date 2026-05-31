// LeetCode 202: Happy Number
// Time Complexity: O(log N) - The number of digits in n is log10(n), and the sum of digit squares drops very quickly.
// Space Complexity: O(1) auxiliary space

public class HappyNumber {

    /**
     * Determines if a number is a happy number.
     * @param n The input number
     * @return True if it is happy, false otherwise
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = getDigitSquareSum(slow);             // Move 1 step
            fast = getDigitSquareSum(getDigitSquareSum(fast)); // Move 2 steps
            
            if (fast == 1) {
                return true;
            }
        } while (slow != fast);

        return slow == 1;
    }

    // Helper method to compute the sum of the squares of digits
    private static int getDigitSquareSum(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int num1 = 19;
        System.out.println("Is " + num1 + " a Happy Number? " + isHappy(num1)); // Expected: true

        int num2 = 2;
        System.out.println("Is " + num2 + " a Happy Number? " + isHappy(num2)); // Expected: false
    }
}
