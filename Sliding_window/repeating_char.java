import java.util.*;

public class repeating_char {
    public static int cal(String s) {
        int left = 0;
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;

            }
            set.add(c);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String args[]) {
        String s = "abcabcbb";
        // repeating_char obj = new repeating_char();
        System.out.println(cal(s));

    }
}