// Reverse a String
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(1) auxiliary space (reversing in-place on a character array)

public class ReverseString {
    /**
     * Reverses the input string in-place on a character array using two pointers.
     * 
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverse(String str) {
        // Edge case: If string is null, return null to avoid NullPointerException
        if (str == null) {
            return null;
        }
        
        // Convert the string to a character array to allow in-place modification
        char[] charArray = str.toCharArray();
        
        // Initialize two pointers: 'left' at the beginning and 'right' at the end
        int left = 0;
        int right = charArray.length - 1;
        
        // Move pointers towards the middle, swapping characters at each step
        while (left < right) {
            // Temporarily store the left character
            char temp = charArray[left];
            
            // Swap: Assign right character to left position
            charArray[left] = charArray[right];
            
            // Swap: Assign temp character to right position
            charArray[right] = temp;
            
            // Increment left pointer to move forward
            left++;
            
            // Decrement right pointer to move backward
            right--;
        }
        
        // Construct and return a new String from the modified character array
        return new String(charArray);
    }

    public static void main(String[] args) {
        // Define various test scenarios
        String test1 = "hello";
        String test2 = "A man, a plan, a canal: Panama";
        String test3 = "";
        String test4 = "a";

        // Execute and print results
        System.out.println("Original: " + test1 + " -> Reversed: " + reverse(test1));
        System.out.println("Original: " + test2 + " -> Reversed: " + reverse(test2));
        System.out.println("Original: " + test3 + " -> Reversed: " + reverse(test3));
        System.out.println("Original: " + test4 + " -> Reversed: " + reverse(test4));
    }
}
