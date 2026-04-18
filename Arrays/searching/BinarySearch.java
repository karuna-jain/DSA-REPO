
//note : it always works on the sorted arrar;
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = { 100, 200, 300, 335, 455, 999 };
        int target = 999;
        // flag variable to check if element is found
        boolean found = false;
        // low=0;
        int low = 0;
        // high=length-1
        int high = arr.length - 1;
        // traversing till low is less then high
        while (low <= high) {
            // intializing the mid as low+high /2;
            int mid = (low + high) / 2;
            // comparing the mid with target

            if (arr[mid] == target) {
                // if mid is equal to target then return
                System.out.println("Element found at index " + mid);
                found = true;
                break;

            } else if (arr[mid] < target) {
                // if mid is less than target then search in right half
                low = mid + 1;
            } else {
                // if mid is greater than target then search in left half
                high = mid - 1;
            }

        }

        if (!found)
            System.out.println("Element not found");
    }
}
