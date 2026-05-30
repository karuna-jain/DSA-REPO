// LeetCode 11: Container With Most Water
// Time Complexity: O(N) where N is the number of elements in the array
// Space Complexity: O(1) auxiliary space

public class ContainerWithMostWater {

    /**
     * Finds the maximum area of water a container can store.
     * @param height An array representing vertical heights of bars
     * @return The maximum water area
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int maxVal = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int area = width * currentHeight;
            
            maxVal = Math.max(maxVal, area);

            // Move the pointer that points to the shorter bar
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxVal;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.print("Heights array: ");
        printArray(height);
        
        int maxWater = maxArea(height);
        System.out.println("Maximum area of water trapped: " + maxWater); // Expected: 49
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
