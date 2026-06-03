// LeetCode 92: Reverse Linked List II (Reverse a Sub-list)
// Time Complexity: O(N) - We pass through the list at most once.
// Space Complexity: O(1) auxiliary space - in-place modification.

public class ReverseSubList {

    /**
     * Reverses a sub-list from position left to right (1-indexed) in-place.
     * @param head The head of the linked list
     * @param left The start position (1-indexed)
     * @param right The end position (1-indexed)
     * @return The head of the modified linked list
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Step 1: Move prev to the node right before the sub-list (at left - 1 position)
        for (int i = 0; i < left - 1 && prev != null; i++) {
            prev = prev.next;
        }

        if (prev == null || prev.next == null) {
            return head;
        }

        // Step 2: Reverse the sub-list from left to right
        ListNode curr = prev.next;
        ListNode nextNode = null;
        ListNode subListHead = curr; // This will become the tail of the sub-list after reversal

        // We will reverse the portion of the list
        ListNode tempPrev = null;
        for (int i = 0; i < right - left + 1 && curr != null; i++) {
            nextNode = curr.next;
            curr.next = tempPrev;
            tempPrev = curr;
            curr = nextNode;
        }

        // Step 3: Connect the reversed sub-list back to the original list
        prev.next = tempPrev;
        subListHead.next = curr;

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Sub-list in the middle ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = reverseBetween(list1, 2, 4);
        System.out.print("Modified: ");
        ListNode.printList(result1); // Expected: [1 -> 4 -> 3 -> 2 -> 5]

        System.out.println("\n--- Test Case 2: Sub-list from start (left = 1) ---");
        ListNode list2 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = reverseBetween(list2, 1, 3);
        System.out.print("Modified: ");
        ListNode.printList(result2); // Expected: [3 -> 2 -> 1 -> 4 -> 5]

        System.out.println("\n--- Test Case 3: Entire list (left = 1, right = length) ---");
        ListNode list3 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = reverseBetween(list3, 1, 5);
        System.out.print("Modified: ");
        ListNode.printList(result3); // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        System.out.println("\n--- Test Case 4: Single element sub-list (left = right) ---");
        ListNode list4 = ListNode.createList(new int[]{1, 2, 3});
        System.out.print("Original: ");
        ListNode.printList(list4);
        ListNode result4 = reverseBetween(list4, 2, 2);
        System.out.print("Modified: ");
        ListNode.printList(result4); // Expected: [1 -> 2 -> 3]
    }
}
