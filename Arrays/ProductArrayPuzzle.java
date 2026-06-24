import java.util.Arrays;

// Time Complexity: O(N) - Two passes through the array.
// Space Complexity: O(1) auxiliary space (excluding the output array).

public class ProductArrayPuzzle {

    /**
     * Constructs a product array P such that P[i] is equal to the product 
     * of all elements of nums except nums[i].
     * Solve without using the division operator and in O(1) auxiliary space.
     * 
     * @param nums The input array of integers
     * @return A product array of type long[] to prevent integer overflow
     */
    public static long[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new long[0];
        }

        int n = nums.length;
        long[] prod = new long[n];

        // Step 1: Initialize the product array.
        // prod[i] will store the product of all elements to the left of nums[i].
        prod[0] = 1;
        for (int i = 1; i < n; i++) {
            prod[i] = prod[i - 1] * nums[i - 1];
        }

        // Step 2: Traverse backwards and calculate suffix products on the fly.
        // right variable keeps track of the product of elements to the right of nums[i].
        long right = 1;
        for (int i = n - 1; i >= 0; i--) {
            prod[i] = prod[i] * right;
            right = right * nums[i];
        }

        return prod;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard positive numbers ---");
        int[] test1 = {10, 3, 5, 6, 2};
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Result: " + Arrays.toString(productExceptSelf(test1)));
        // Expected: [180, 600, 360, 300, 900]

        System.out.println("\n--- Test Case 2: Array containing one zero ---");
        int[] test2 = {1, 2, 0, 4};
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Result: " + Arrays.toString(productExceptSelf(test2)));
        // Expected: [0, 0, 8, 0]

        System.out.println("\n--- Test Case 3: Array containing multiple zeroes ---");
        int[] test3 = {1, 0, 3, 0};
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Result: " + Arrays.toString(productExceptSelf(test3)));
        // Expected: [0, 0, 0, 0]

        System.out.println("\n--- Test Case 4: Negative numbers ---");
        int[] test4 = {-1, 1, 0, -3, 3};
        System.out.println("Input:  " + Arrays.toString(test4));
        System.out.println("Result: " + Arrays.toString(productExceptSelf(test4)));
        // Expected: [0, 0, 9, 0, 0]
    }
}
