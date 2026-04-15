
public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = { 10, 12, 4, 22, 99, 100, 0 };
        int target = 22;
        // flag variable to check if element is found
        boolean found = false;
        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            // compare the value with current element
            if (arr[i] == target) {
                // print the index if found
                System.out.println("Element found at index " + i);
                found = true;
                break;
            }
        }
        // print if element is not found
        if (!found)
            System.out.println("Element not found");
    }
}
