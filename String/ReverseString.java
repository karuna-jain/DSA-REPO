// Reverse a String
// Time Complexity: O(N)
// Space Complexity: O(1) (In-place traversal on character array)

public class ReverseString {
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        String test1 = "hello";
        String test2 = "A man, a plan, a canal: Panama";
        String test3 = "";
        String test4 = "a";

        System.out.println("Original: " + test1 + " -> Reversed: " + reverse(test1));
        System.out.println("Original: " + test2 + " -> Reversed: " + reverse(test2));
        System.out.println("Original: " + test3 + " -> Reversed: " + reverse(test3));
        System.out.println("Original: " + test4 + " -> Reversed: " + reverse(test4));
    }
}
