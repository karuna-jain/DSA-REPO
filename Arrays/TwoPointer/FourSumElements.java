import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Time Complexity: O(N^2) on average - double loops to compute pair sums, and average O(1) map lookups.
// Space Complexity: O(N^2) auxiliary space - to store up to N*(N-1)/2 pairs in the HashMap.

public class FourSumElements {

    // Helper class to represent a pair of indices
    private static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Finds all unique quadruplets in the array that sum up to target using a HashMap.
     * Storing pair sums enables O(N^2) average-time complexity by finding 
     * two pairs that sum to target and ensuring their indices are disjoint.
     * 
     * @param nums   The input array
     * @param target The target sum
     * @return List of unique quadruplets
     */
    public static List<List<Integer>> fourSumHashMap(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        int n = nums.length;
        // Map to store sum -> list of pairs that generate this sum
        Map<Integer, List<Pair>> sumMap = new HashMap<>();

        // Populate the map with all pairs
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                sumMap.putIfAbsent(sum, new ArrayList<>());
                sumMap.get(sum).add(new Pair(i, j));
            }
        }

        // Set to store unique quadruplets (represented as sorted list of elements) to avoid duplicates
        Set<List<Integer>> uniqueQuadruplets = new HashSet<>();

        // Scan all pairs again and search for target - sum
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                int complement = target - sum;

                if (sumMap.containsKey(complement)) {
                    List<Pair> pairs = sumMap.get(complement);
                    
                    for (Pair p : pairs) {
                        // Ensure all 4 indices are disjoint
                        if (p.first != i && p.first != j && p.second != i && p.second != j) {
                            // Extract elements
                            int[] quad = {nums[i], nums[j], nums[p.first], nums[p.second]};
                            Arrays.sort(quad); // Sort to ensure uniqueness in hash set
                            
                            List<Integer> list = Arrays.asList(quad[0], quad[1], quad[2], quad[3]);
                            uniqueQuadruplets.add(list);
                        }
                    }
                }
            }
        }

        result.addAll(uniqueQuadruplets);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Quadruplets ---");
        int[] nums1 = {3, 4, 7, 1, 2, 9, 8};
        int target1 = 17;
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: " + target1);
        List<List<Integer>> res1 = fourSumHashMap(nums1, target1);
        System.out.println("Quadruplets: " + res1);
        // Expected quadruplets like: [1, 2, 6, 8] -> check values:
        // e.g. 1 + 3 + 4 + 9 = 17 => nums index: 1(3), 2(4), 3(1), 5(9) -> [1, 3, 4, 9] etc.

        System.out.println("\n--- Test Case 2: Duplicates & Negative Numbers ---");
        int[] nums2 = {1, 0, -1, 0, -2, 2};
        int target2 = 0;
        System.out.println("Input: " + Arrays.toString(nums2) + ", Target: " + target2);
        List<List<Integer>> res2 = fourSumHashMap(nums2, target2);
        System.out.println("Quadruplets: " + res2);
        // Expected unique quadruplets: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
    }
}
