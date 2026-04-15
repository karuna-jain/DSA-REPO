
//logic
//Take first element as key
//Compare with previous elements
//Shift greater elements to right
//Insert key at correct position
//Repeat for all elements
public class Insertion {
    public static void insertionSort(int[] arr) {
        // first pass
        for (int i = 1; i < arr.length; i++) {
            // making i as the key
            int key = arr[i];
            // instatiling j
            int j = i - 1;
            // while loop to compare with previous elements
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 10, 12, 4, 22, 99, 100, 0 };
        insertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
