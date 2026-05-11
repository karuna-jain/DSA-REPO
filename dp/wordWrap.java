
import java.util.*;

class wordWrap {

    public int solve(int arr[], int i, int k, int dp[]) {
        // base case
        if (arr.length == i)
            return 0;
        // to check if computed or not
        if (dp[i] != -1)
            return dp[i];
        // initialize ans
        int ans = Integer.MAX_VALUE;
        // intiallize length
        int len = 0;
        for (int j = i; j < arr.length; j++) {
            len += arr[j];
            // beacuse extra space is needed
            int total = len + (j - i);
            // if toatl is grater than k then cost =0;
            int cost;
            if (total > k)
                break;
            // if length and k are qual then cost is also 0
            else if (j == arr.length - 1)
                cost = 0;
            else {
                // cal the extra space needed
                int x = k - total;
                cost = x * x;

            }
            // call the recursive call
            ans = Math.min(ans, cost + solve(arr, j + 1, k, dp));
        }

        return dp[i] = ans;

    }

    public int wordWrap(int arr[], int k) {
        int dp[] = new int[arr.length];
        // fill the array with -1;
        Arrays.fill(dp, -1);
        return solve(arr, 0, k, dp);
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5 };
        int k = 10;
        wordWrap ob = new wordWrap();
        System.out.println(ob.wordWrap(arr, k));
    }
}