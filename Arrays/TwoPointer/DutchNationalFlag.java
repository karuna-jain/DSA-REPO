import java.util.Arrays;

// LeetCode 75: Sort Colors (Dutch National Flag Problem)
// Time Complexity: O(N) where N is the number of elements in the array
// Space Complexity: O(1) auxiliary space

public class DutchNationalFlag {

    /**
     * Sorts the colors in-place using the three-pointer approach.
     * 0 represents red, 1 represents white, and 2 represents blue.
     * @param nums The input array of colors
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int low = 0;          // Boundary for 0s
        int mid = 0;          // Current scanning pointer
        int high = nums.length - 1; // Boundary for 2s

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
                // Note: We do not increment mid here because the swapped element from
                // high could be a 0 or 1, which must be processed in the next step.
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        System.out.println("Original array: " + Arrays.toString(nums));
        
        sortColors(nums);
        System.out.println("Sorted colors:  " + Arrays.toString(nums)); // Expected: [0, 0, 1, 1, 2, 2]
    }
}
