import java.util.Arrays;

/**
 * Problem: Book Allocation Problem (GFG / Love Babbar DSA Sheet)
 * 
 * Description:
 * Given N books, the i-th book has pages[i] number of pages. You have to allocate these books
 * to M students such that:
 *   1. Each book is allocated to exactly one student.
 *   2. The books are allocated in a contiguous manner.
 *   3. All students are allocated at least one book.
 *   4. The maximum number of pages allocated to any student is minimized.
 * 
 * If it is not possible to allocate books (e.g., number of books N < M), return -1.
 * 
 * Time Complexity:
 *   - O(N log(sum_pages)) where N is the number of books, and sum_pages is the sum of all pages.
 * Space Complexity:
 *   - O(1) auxiliary space.
 */
public class BookAllocation {

    /**
     * Allocates books to students such that the maximum number of pages allocated to any student is minimized.
     * @param pages Array representing the number of pages in each book
     * @param students Number of students
     * @return The minimized maximum pages allocated, or -1 if impossible
     */
    public static int allocateBooks(int[] pages, int students) {
        if (pages == null || pages.length == 0 || students <= 0) {
            return -1;
        }

        int n = pages.length;
        if (n < students) {
            return -1; // Less books than students, cannot allocate at least one book to each
        }

        // 1. Find search space [low, high]
        int maxPages = 0;
        int sumPages = 0;
        for (int p : pages) {
            maxPages = Math.max(maxPages, p);
            sumPages += p;
        }

        int low = maxPages;  // No student can read less than the largest single book
        int high = sumPages; // Worst case: one student reads all books
        int ans = -1;

        // 2. Binary search on the answer range
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(pages, mid, students)) {
                ans = mid;         // Record mid as a valid max limit
                high = mid - 1;    // Try to find a smaller maximum limit (move left)
            } else {
                low = mid + 1;     // Increase the limit (move right)
            }
        }

        return ans;
    }

    /**
     * Helper method to verify if it is possible to allocate books to 'students'
     * such that no student reads more than 'maxPagesLimit'.
     */
    private static boolean isPossible(int[] pages, int maxPagesLimit, int students) {
        int studentCount = 1;
        int currentPagesSum = 0;

        for (int page : pages) {
            // If a single book has more pages than the limit, allocation is impossible
            if (page > maxPagesLimit) {
                return false;
            }

            if (currentPagesSum + page <= maxPagesLimit) {
                currentPagesSum += page;
            } else {
                studentCount++; // Allocate to next student
                currentPagesSum = page;

                if (studentCount > students) {
                    return false; // Requires more students than available
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Running Book Allocation Problem Tests:\n");

        // Test 1: Standard case
        int[] pages1 = {12, 34, 67, 90};
        int students1 = 2;
        // Search space: low = 90, high = 203
        // If limit = 113:
        // Student 1: 12 + 34 = 46. (46 + 67 = 113) -> Student 1: 12 + 34 + 67 = 113
        // Student 2: 90
        // Max pages: 113. This is valid.
        // If limit < 113: e.g. 112, we can't allocate.
        // So minimized maximum pages is 113.
        int ans1 = allocateBooks(pages1, students1);
        System.out.println("Test 1: pages = [12, 34, 67, 90], students = 2");
        System.out.println("Expected: 113, Actual: " + ans1);
        System.out.println();

        // Test 2: GFG standard example
        int[] pages2 = {25, 46, 28, 49, 24};
        int students2 = 4;
        // Expected: 71 (Allocations: [25, 46] -> 71, [28] -> 28, [49] -> 49, [24] -> 24)
        int ans2 = allocateBooks(pages2, students2);
        System.out.println("Test 2: pages = [25, 46, 28, 49, 24], students = 4");
        System.out.println("Expected: 71, Actual: " + ans2);
        System.out.println();

        // Test 3: Less books than students
        int[] pages3 = {10, 20};
        int students3 = 3;
        int ans3 = allocateBooks(pages3, students3);
        System.out.println("Test 3: pages = [10, 20], students = 3 (N < M case)");
        System.out.println("Expected: -1, Actual: " + ans3);
        System.out.println();
    }
}
