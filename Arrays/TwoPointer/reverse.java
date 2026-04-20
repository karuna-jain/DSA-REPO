
public class reverse {
    public static void rev(int arr[]) {
        // two pointer approach
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // swap
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            // move pointers
            start++;
            end--;
        }

    }

    public static void main(String[] args) {
        int arr[] = { 99, 88, 77, 66, 55, 44, 33, 22, 11, 0 };
        rev(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
