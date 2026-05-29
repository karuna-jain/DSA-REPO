
public class subarray_sum {
    public static int sub(int arr[], int k) {
        int n = arr.length;
        int sum = 0, max = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        max = Math.max(sum / k, 0);
        for (int i = k; i < n; i++) {
            sum = sum - arr[i - k] + arr[i];
            max = Math.max(sum / k, 0);
        }
        return max;
    }

    public static void main(String args[]) {
        int arr[] = new int[] { 199, 89, -21, 79, 0, 10 };
        int k = 4;
        System.out.println(sub(arr, k));
    }

}
