// Balanced Parenthesis problem
// Time Complexity: O(N) where N is the length of the string
// Space Complexity: O(N) for the character stack

import java.util.Stack;

public class BalancedParenthesis {
    /**
     * Checks if the bracket expression is balanced.
     * Supports matching for '(', ')', '{', '}', '[' and ']'.
     * Any other characters are ignored.
     * 
     * @param str The input bracket expression
     * @return True if the expression is balanced, false otherwise
     */
    public static boolean isValid(String str) {
        if (str == null) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // If it is an opening bracket, push it to the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 
            // If it is a closing bracket, check for matching top of stack
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If stack is empty, we have a closing bracket without any opening partner
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check if the current closing bracket matches the popped opening bracket
                if (ch == ')' && top != '(') {
                    return false;
                }
                if (ch == '}' && top != '{') {
                    return false;
                }
                if (ch == ']' && top != '[') {
                    return false;
                }
            }
        }

        // The expression is balanced only if the stack is completely empty
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "{[()]}";
        String test2 = "{[(])}";
        String test3 = "{{[[(())]]}}";
        String test4 = "";
        String test5 = "a(b[c]d)e"; // Mixed characters

        System.out.println("Expression: \"" + test1 + "\" -> Is Balanced? " + isValid(test1)); // Expected: true
        System.out.println("Expression: \"" + test2 + "\" -> Is Balanced? " + isValid(test2)); // Expected: false
        System.out.println("Expression: \"" + test3 + "\" -> Is Balanced? " + isValid(test3)); // Expected: true
        System.out.println("Expression: \"" + test4 + "\" -> Is Balanced? " + isValid(test4)); // Expected: true
        System.out.println("Expression: \"" + test5 + "\" -> Is Balanced? " + isValid(test5)); // Expected: true
    }
}
