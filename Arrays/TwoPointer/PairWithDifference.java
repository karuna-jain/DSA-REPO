import java.util.Arrays;
import java.util.HashSet;

// Time Complexity: 
//   - Two Pointer: O(N log N) for sorting, then O(N) traversal.
//   - HashSet: O(N) average time complexity.
// Space Complexity: 
//   - Two Pointer: O(1) auxiliary space (if sorting is in-place).
//   - HashSet: O(N) space.

public class PairWithDifference {

    /**
     * Approach 1: Two-Pointer Technique (requires sorted array)
     * We sort the array, and use left and right pointers moving in the same direction.
     * 
     * @param arr  The input array
     * @param diff The target difference
     * @return An array of the pair [element1, element2] if found; otherwise null.
     */
    public static int[] findPairTwoPointer(int[] arr, int diff) {
        if (arr == null || arr.length < 2) {
            return null;
        }

        // Sort the array
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        // Target difference is positive for absolute difference
        diff = Math.abs(diff);

        int left = 0;
        int right = 1;
        int n = sorted.length;

        while (left < n && right < n) {
            if (left != right && sorted[right] - sorted[left] == diff) {
                return new int[] {sorted[left], sorted[right]};
            } else if (sorted[right] - sorted[left] < diff) {
                right++;
            } else {
                left++;
            }
            
            // Pointers must be distinct
            if (left == right) {
                right++;
            }
        }

        return null;
    }

    /**
     * Approach 2: HashSet (works on unsorted array, handles negative/positive values)
     * For each element, checks if (element + diff) or (element - diff) exists in the set.
     * 
     * @param arr  The input array
     * @param diff The target difference
     * @return An array of the pair [element1, element2] if found; otherwise null.
     */
    public static int[] findPairHashSet(int[] arr, int diff) {
        if (arr == null || arr.length < 2) {
            return null;
        }

        HashSet<Integer> seen = new HashSet<>();
        
        for (int num : arr) {
            // Check if there is an element already seen such that:
            // seen_element - num = diff => seen_element = num + diff
            // num - seen_element = diff => seen_element = num - diff
            if (seen.contains(num + diff)) {
                return new int[] {num, num + diff};
            }
            if (seen.contains(num - diff)) {
                return new int[] {num - diff, num};
            }
            seen.add(num);
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Positive Difference ---");
        int[] arr1 = {5, 20, 3, 2, 50, 80};
        int diff1 = 78;
        System.out.println("Input: " + Arrays.toString(arr1) + ", Target Diff: " + diff1);
        System.out.println("Two-pointer: " + Arrays.toString(findPairTwoPointer(arr1, diff1))); // Expected: [2, 80]
        System.out.println("HashSet:     " + Arrays.toString(findPairHashSet(arr1, diff1)));     // Expected: [2, 80] or similar pair

        System.out.println("\n--- Test Case 2: Difference 0 (checking unique indices) ---");
        int[] arr2 = {1, 8, 30, 40, 100};
        int diff2 = 0;
        System.out.println("Input: " + Arrays.toString(arr2) + ", Target Diff: " + diff2);
        System.out.println("Two-pointer: " + Arrays.toString(findPairTwoPointer(arr2, diff2))); // Expected: null (no duplicates)
        System.out.println("HashSet:     " + Arrays.toString(findPairHashSet(arr2, diff2)));     // Expected: null

        System.out.println("\n--- Test Case 3: Duplicate elements with diff 0 ---");
        int[] arr3 = {1, 8, 30, 8, 100};
        int diff3 = 0;
        System.out.println("Input: " + Arrays.toString(arr3) + ", Target Diff: " + diff3);
        System.out.println("Two-pointer: " + Arrays.toString(findPairTwoPointer(arr3, diff3))); // Expected: [8, 8]
        System.out.println("HashSet:     " + Arrays.toString(findPairHashSet(arr3, diff3)));     // Expected: [8, 8]
    }
}
