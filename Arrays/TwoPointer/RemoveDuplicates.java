// LeetCode 26: Remove Duplicates from Sorted Array
// Time Complexity: O(N) where N is the number of elements in the array
// Space Complexity: O(1) auxiliary space

public class RemoveDuplicates {
    
    /**
     * Removes duplicates from a sorted array in-place.
     * @param nums The sorted array
     * @return The number of unique elements
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // i is the slow pointer representing the last unique element index
        int i = 0;
        
        // j is the fast pointer scanning through the array
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        
        // Number of unique elements is index + 1
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        
        System.out.println("Original array: ");
        printArray(nums);
        
        int k = removeDuplicates(nums);
        
        System.out.println("After removing duplicates, number of unique elements: " + k);
        System.out.println("Modified array: ");
        printArray(nums, k);
    }
    
    private static void printArray(int[] arr) {
        printArray(arr, arr.length);
    }
    
    private static void printArray(int[] arr, int limit) {
        System.out.print("[");
        for (int i = 0; i < limit; i++) {
            System.out.print(arr[i]);
            if (i < limit - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
