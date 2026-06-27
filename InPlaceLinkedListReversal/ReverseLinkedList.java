// LeetCode 206: Reverse Linked List
// Time Complexity: O(N) - Single pass through the list of length N.
// Space Complexity: O(1) auxiliary space - in-place reversal.

public class ReverseLinkedList {

    /**
     * Reverses a singly-linked list in-place iteratively and returns the new head.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * @param head The head of the linked list
     * @return The new head of the reversed linked list
     */
    public static ListNode reverseIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextNode = curr.next; // Store next node
            curr.next = prev;              // Reverse current node's pointer
            prev = curr;                   // Move prev to current
            curr = nextNode;               // Move curr to next node
        }

        return prev;
    }

    /**
     * Reverses a singly-linked list recursively and returns the new head.
     * Time Complexity: O(N)
     * Space Complexity: O(N) - due to the recursion call stack
     * @param head The head of the linked list
     * @return The new head of the reversed linked list
     */
    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Multiple Elements (Iterative) ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode reversed1 = reverseIterative(list1);
        System.out.print("Reversed: ");
        ListNode.printList(reversed1); // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        System.out.println("\n--- Test Case 2: Multiple Elements (Recursive) ---");
        ListNode list2 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode reversed2 = reverseRecursive(list2);
        System.out.print("Reversed: ");
        ListNode.printList(reversed2); // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        System.out.println("\n--- Test Case 3: Single Element (Recursive) ---");
        ListNode list3 = ListNode.createList(new int[]{1});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode reversed3 = reverseRecursive(list3);
        System.out.print("Reversed: ");
        ListNode.printList(reversed3); // Expected: [1]

        System.out.println("\n--- Test Case 4: Empty List ---");
        ListNode list4 = ListNode.createList(new int[]{});
        System.out.print("Original: ");
        ListNode.printList(list4);
        ListNode reversed4 = reverseIterative(list4);
        System.out.print("Reversed: ");
        ListNode.printList(reversed4); // Expected: []
    }
}
