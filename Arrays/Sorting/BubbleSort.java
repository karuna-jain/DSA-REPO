// Problem: Bubble Sort
// Description: Sort an array using Bubble Sort algorithm
// Time Complexity: O(n^2)
// Space Complexity: O(1)

class sort {
    public static void bubbleSort(int[] arr) {
        // Function to perform Bubble Sort
        // Approach:
        // 1. Compare adjacent elements
        // 2. Swap if they are in wrong order
        // 3. Repeat for all elements

        // Outer loop -> number of passes
        for (int i = 0; i < arr.length; i++) {
            // Inner loop -> to compare the elements
            for (int j = 0; j < arr.length - i - 1; j++) {
                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {
                    // Swap if they are in wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        }
    }
}

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 2, 1 };
        sort.bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
