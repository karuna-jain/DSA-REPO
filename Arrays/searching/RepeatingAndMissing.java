import java.util.Arrays;

// Time Complexity: O(N) - Single pass for both Math and XOR approaches.
// Space Complexity: O(1) auxiliary space - In-place computations.

public class RepeatingAndMissing {

    /**
     * Approach 1: Mathematical Equations
     * Let x be the repeating number and y be the missing number.
     * Sum(actual) - Sum(1..N) = x - y
     * SumSquares(actual) - SumSquares(1..N) = x^2 - y^2 = (x - y)(x + y)
     * From these equations, we solve for x and y.
     * 
     * @param nums The input array representing numbers from 1 to N with one repeating and one missing.
     * @return An array containing [repeating, missing]
     */
    public static int[] findUsingMath(int[] nums) {
        long n = nums.length;
        
        // Expected sum and sum of squares
        long sumIdeal = (n * (n + 1)) / 2;
        long sumSqIdeal = (n * (n + 1) * (2 * n + 1)) / 6;
        
        long sumActual = 0;
        long sumSqActual = 0;
        
        for (int num : nums) {
            sumActual += num;
            sumSqActual += (long) num * num;
        }
        
        // diff = x - y
        long diff = sumActual - sumIdeal;
        
        // diffSq = x^2 - y^2 = (x - y)(x + y)
        long diffSq = sumSqActual - sumSqIdeal;
        
        // sumXY = x + y = (x^2 - y^2) / (x - y)
        long sumXY = diffSq / diff;
        
        // x = ((x + y) + (x - y)) / 2
        int repeating = (int) ((sumXY + diff) / 2);
        
        // y = (x + y) - x
        int missing = (int) (sumXY - repeating);
        
        return new int[] {repeating, missing};
    }

    /**
     * Approach 2: Bitwise XOR
     * XOR all elements of the array and all numbers from 1 to N.
     * The result will be (x XOR y).
     * We find the rightmost set bit, and split numbers into two groups (one where bit is set, one where not).
     * XORing each group separately isolates x and y.
     * 
     * @param nums The input array.
     * @return An array containing [repeating, missing]
     */
    public static int[] findUsingXOR(int[] nums) {
        int n = nums.length;
        int xorSum = 0;
        
        // XOR all array elements
        for (int num : nums) {
            xorSum ^= num;
        }
        
        // XOR all numbers from 1 to N
        for (int i = 1; i <= n; i++) {
            xorSum ^= i;
        }
        
        // xorSum now holds (repeating ^ missing).
        // Find the rightmost set bit (only one of repeating or missing will have this bit set)
        int rightmostSetBit = xorSum & ~(xorSum - 1);
        
        int group1 = 0; // Numbers with rightmostSetBit set
        int group2 = 0; // Numbers with rightmostSetBit not set
        
        // Distribute array elements
        for (int num : nums) {
            if ((num & rightmostSetBit) != 0) {
                group1 ^= num;
            } else {
                group2 ^= num;
            }
        }
        
        // Distribute numbers 1..N
        for (int i = 1; i <= n; i++) {
            if ((i & rightmostSetBit) != 0) {
                group1 ^= i;
            } else {
                group2 ^= i;
            }
        }
        
        // One of the groups has the repeating number, the other has the missing.
        // We verify which is in the array.
        for (int num : nums) {
            if (num == group1) {
                return new int[] {group1, group2}; // group1 is repeating, group2 is missing
            }
        }
        
        return new int[] {group2, group1}; // group2 is repeating, group1 is missing
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Case ---");
        int[] test1 = {4, 3, 6, 2, 1, 1}; // N = 6, Repeating: 1, Missing: 5
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Math Method result: " + Arrays.toString(findUsingMath(test1))); // Expected: [1, 5]
        System.out.println("XOR Method result:  " + Arrays.toString(findUsingXOR(test1)));  // Expected: [1, 5]

        System.out.println("\n--- Test Case 2: Adjacent Swap ---");
        int[] test2 = {1, 2, 2, 4}; // N = 4, Repeating: 2, Missing: 3
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Math Method result: " + Arrays.toString(findUsingMath(test2))); // Expected: [2, 3]
        System.out.println("XOR Method result:  " + Arrays.toString(findUsingXOR(test2)));  // Expected: [2, 3]

        System.out.println("\n--- Test Case 3: Edge Case (N = 2) ---");
        int[] test3 = {1, 1}; // N = 2, Repeating: 1, Missing: 2
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Math Method result: " + Arrays.toString(findUsingMath(test3))); // Expected: [1, 2]
        System.out.println("XOR Method result:  " + Arrays.toString(findUsingXOR(test3)));  // Expected: [1, 2]
    }
}
