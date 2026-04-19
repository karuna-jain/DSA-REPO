
//Find the maximum sum subarray (contiguous)
//“Keep extending while beneficial, restart when harmful.”

public class minSum {
    public static void main(String[] args) {
        int[] arr = { 10, -12, 4, -22, 99, 100, 0 };
        // intiall min sum is the max value
        int minSum = Integer.MAX_VALUE;
        // current sum of array at intial stage is zero
        int currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            // add the curr element in the current sum
            currentSum += arr[i];
            // if current sum is less than min sum then update min sum
            if (currentSum < minSum) {
                minSum = currentSum;
            }
            // if current sum is greater than 0 then reset it to 0
            if (currentSum > 0) {
                currentSum = 0;
            }
        }
        System.out.println(minSum);
    }

}
