// LeetCode 206: Reverse Linked List
// Time Complexity: O(N) - Single pass through the list of length N.
// Space Complexity: O(1) auxiliary space - in-place reversal.

public class ReverseLinkedList {

    /**
     * Reverses a singly-linked list in-place and returns the new head.
     * @param head The head of the linked list
     * @return The new head of the reversed linked list
     */
    public static ListNode reverse(ListNode head) {
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

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Multiple Elements ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode reversed1 = reverse(list1);
        System.out.print("Reversed: ");
        ListNode.printList(reversed1); // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        System.out.println("\n--- Test Case 2: Single Element ---");
        ListNode list2 = ListNode.createList(new int[]{1});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode reversed2 = reverse(list2);
        System.out.print("Reversed: ");
        ListNode.printList(reversed2); // Expected: [1]

        System.out.println("\n--- Test Case 3: Empty List ---");
        ListNode list3 = ListNode.createList(new int[]{});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode reversed3 = reverse(list3);
        System.out.print("Reversed: ");
        ListNode.printList(reversed3); // Expected: []
    }
}
