// Count and Say problem
// Time Complexity: O(2^N) approx due to exponential sequence growth
// Space Complexity: O(2^N) to store the result sequence string

public class CountAndSay {
    public static String countAndSay(int n) {
        if (n <= 0) return "";
        String current = "1";
        
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int len = current.length();
            int count = 1;
            for (int j = 1; j < len; j++) {
                if (current.charAt(j) == current.charAt(j - 1)) {
                    count++;
                } else {
                    next.append(count).append(current.charAt(j - 1));
                    count = 1;
                }
            }
            next.append(count).append(current.charAt(len - 1));
            current = next.toString();
        }
        
        return current;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            System.out.println("n = " + i + ": " + countAndSay(i));
        }
    }
}
