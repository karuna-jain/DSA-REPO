// LeetCode 234: Palindrome Linked List
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class PalindromeLinkedList {

    /**
     * Checks if a singly-linked list is a palindrome.
     * Restores the list structure before returning (clean and mutation-safe).
     * @param head The head of the linked list
     * @return True if palindrome, false otherwise
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the linked list
        ListNode secondHalfHead = reverseList(slow.next);

        // Step 3: Compare both halves
        ListNode firstHalfPtr = head;
        ListNode secondHalfPtr = secondHalfHead;
        boolean result = true;
        
        while (secondHalfPtr != null) {
            if (firstHalfPtr.val != secondHalfPtr.val) {
                result = false;
                break;
            }
            firstHalfPtr = firstHalfPtr.next;
            secondHalfPtr = secondHalfPtr.next;
        }

        // Step 4: Restore the list (re-reverse the second half back to original)
        slow.next = reverseList(secondHalfHead);

        return result;
    }

    // Helper method to reverse a singly-linked list
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static void main(String[] args) {
        // Test Case 1: Palindrome list [1, 2, 2, 1]
        int[] arr1 = {1, 2, 2, 1};
        ListNode head1 = ListNode.createList(arr1);
        System.out.print("List 1: ");
        ListNode.printList(head1);
        System.out.println("Is Palindrome? " + isPalindrome(head1)); // Expected: true
        System.out.print("List 1 (Restored): ");
        ListNode.printList(head1); // Expected unchanged list

        // Test Case 2: Non-palindrome list [1, 2, 3]
        int[] arr2 = {1, 2, 3};
        ListNode head2 = ListNode.createList(arr2);
        System.out.print("\nList 2: ");
        ListNode.printList(head2);
        System.out.println("Is Palindrome? " + isPalindrome(head2)); // Expected: false
        System.out.print("List 2 (Restored): ");
        ListNode.printList(head2); // Expected unchanged list
    }
}
