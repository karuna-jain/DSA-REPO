// Count the numbers in a range [L, R] whose first and last digits are equal.

public class CountSameFirstLastDigit {

    // Helper function to count numbers in range [1, N] with same first and last digit.
    // This runs in O(log N) time and O(1) space.
    public static long countSameFirstLast(long n) {
        if (n <= 0) {
            return 0;
        }
        if (n < 10) {
            return n;
        }

        long count = 9 + (n / 10);
        
        // Find the first digit
        long firstDigit = n;
        while (firstDigit >= 10) {
            firstDigit /= 10;
        }

        // Find the last digit
        long lastDigit = n % 10;

        // If last digit is less than first digit, we haven't reached the
        // number with the same first and last digit in the current decade.
        if (lastDigit < firstDigit) {
            count--;
        }

        return count;
    }

    // Function to count numbers in range [L, R] with same first and last digit.
    public static long countInRange(long l, long r) {
        if (l > r) {
            return 0;
        }
        return countSameFirstLast(r) - countSameFirstLast(l - 1);
    }

    public static void main(String[] args) {
        long l = 99;
        long r = 1024;
        System.out.println(countInRange(l, r));
    }
}
