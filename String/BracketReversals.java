// Minimum number of bracket reversals needed to make an expression balanced
// Time Complexity: O(N) where N is the length of the expression
// Space Complexity: O(1) auxiliary space (using counters instead of a stack)

public class BracketReversals {
    /**
     * Finds the minimum number of bracket reversals needed to make an expression balanced.
     * The expression is assumed to contain only curly braces '{' and '}'.
     * 
     * @param expr The input bracket expression
     * @return Minimum reversals needed, or -1 if the expression cannot be balanced (e.g. odd length)
     */
    public static int minReversals(String expr) {
        // If length is null or odd, it is impossible to balance
        if (expr == null || expr.length() % 2 != 0) {
            return -1;
        }

        int open = 0;   // Count of unmatched '{'
        int close = 0;  // Count of unmatched '}'

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '{') {
                open++;
            } else if (ch == '}') {
                if (open > 0) {
                    // Match a preceding '{'
                    open--;
                } else {
                    // No matching '{', so this '}' is unmatched
                    close++;
                }
            }
        }

        // Minimum reversals needed:
        // - (close + 1) / 2 to turn unmatched close brackets into open brackets
        // - (open + 1) / 2 to turn unmatched open brackets into close brackets
        return (close + 1) / 2 + (open + 1) / 2;
    }

    public static void main(String[] args) {
        String test1 = "}{";
        String test2 = "{{{";
        String test3 = "{{{{";
        String test4 = "{{}}";
        String test5 = "}{{}}{{{";

        System.out.println("Expression: \"" + test1 + "\" -> Min Reversals: " + minReversals(test1)); // Expected: 2
        System.out.println("Expression: \"" + test2 + "\" -> Min Reversals: " + minReversals(test2)); // Expected: -1
        System.out.println("Expression: \"" + test3 + "\" -> Min Reversals: " + minReversals(test3)); // Expected: 2
        System.out.println("Expression: \"" + test4 + "\" -> Min Reversals: " + minReversals(test4)); // Expected: 0
        System.out.println("Expression: \"" + test5 + "\" -> Min Reversals: " + minReversals(test5)); // Expected: 3
    }
}
