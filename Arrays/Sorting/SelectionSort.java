/*Start from index 0
Find the minimum element in the remaining array
Swap it with current index
Move to next index
Repeat until array is sorted
*/

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        // Outer loop -> number of passes
        for (int i = 0; i < arr.length; i++) {
            // Inner loop -> to find the minimum element
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // if min is less then current element then swap

                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 2, 1 };
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
