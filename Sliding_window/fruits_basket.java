import java.util.*;

public class fruits_basket {
    public static int cal(int arr[]) {
        int left = 0;
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < arr.length; right++) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
            while (map.size() > 2) {
                map.put(arr[left], map.get(arr[left]) - 1);
                if (map.get(arr[left]) == 0) {
                    map.remove(arr[left]);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = new int[] { 1, 2, 1, 2, 3, 4 };
        System.out.println(cal(arr));
    }
}