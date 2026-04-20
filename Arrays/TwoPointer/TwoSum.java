// finding the targeted value is present as the sum of of two element in sorted arrays

public class TwoSum {
    // two pointer approach
    public static boolean twoSum(int arr[], int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int target = 15;
        System.out.println(twoSum(arr, target));
    }
}
