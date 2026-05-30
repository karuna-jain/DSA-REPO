// LeetCode 977: Squares of a Sorted Array
// Time Complexity: O(N) where N is the number of elements in the array
// Space Complexity: O(1) auxiliary space (O(N) to store and return the result array)

public class SquaresSortedArray {

    /**
     * Squares a sorted array and returns the sorted squares.
     * @param nums The sorted array
     * @return The sorted squared array
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        int left = 0;
        int right = n - 1;
        int index = n - 1; // Start filling result from the end (largest values)
        
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        
        System.out.print("Original sorted array: ");
        printArray(nums);
        
        int[] squared = sortedSquares(nums);
        
        System.out.print("Sorted squares: ");
        printArray(squared);
    }
    
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
