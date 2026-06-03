// Grokking Pattern: Reverse Alternating K-element Sub-list
// Time Complexity: O(N) - Single pass through the list of length N.
// Space Complexity: O(1) auxiliary space - in-place modification.

public class ReverseAlternatingKGroup {

    /**
     * Reverses alternating k-element sub-lists in-place.
     * Reverses the first k nodes, skips the next k nodes, reverses the next k nodes, and so on.
     * If a group has fewer than k nodes at the end, it reverses/skips whatever is left.
     * @param head The head of the linked list
     * @param k The group size
     * @return The head of the modified linked list
     */
    public static ListNode reverseAlternatingKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupTail = dummy;
        ListNode curr = head;
        boolean reverse = true;

        while (curr != null) {
            if (reverse) {
                // Step 1: Reverse k nodes (or whatever is left)
                ListNode groupHead = curr;
                ListNode prev = null;
                for (int i = 0; i < k && curr != null; i++) {
                    ListNode nextNode = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = nextNode;
                }

                // Connect the previous section to the reversed head
                prevGroupTail.next = prev;

                // Connect the reversed tail (original groupHead) to the next section
                groupHead.next = curr;

                // Move the tail pointer to the end of the reversed group
                prevGroupTail = groupHead;
            } else {
                // Step 2: Skip k nodes (or whatever is left)
                for (int i = 0; i < k && curr != null; i++) {
                    prevGroupTail = curr;
                    curr = curr.next;
                }
            }
            // Toggle phase
            reverse = !reverse;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: k = 2 (size = 8) ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = reverseAlternatingKGroup(list1, 2);
        System.out.print("Modified: ");
        ListNode.printList(result1); // Expected: [2 -> 1 -> 3 -> 4 -> 6 -> 5 -> 7 -> 8]

        System.out.println("\n--- Test Case 2: k = 3 (size = 8) ---");
        ListNode list2 = ListNode.createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = reverseAlternatingKGroup(list2, 3);
        System.out.print("Modified: ");
        ListNode.printList(result2); // Expected: [3 -> 2 -> 1 -> 4 -> 5 -> 6 -> 8 -> 7]
        // Explanation: Reverse [1,2,3] -> [3,2,1], Skip [4,5,6] -> [4,5,6], Reverse [7,8] -> [8,7]

        System.out.println("\n--- Test Case 3: k = 1 (No changes should happen) ---");
        ListNode list3 = ListNode.createList(new int[]{1, 2, 3, 4});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = reverseAlternatingKGroup(list3, 1);
        System.out.print("Modified: ");
        ListNode.printList(result3); // Expected: [1 -> 2 -> 3 -> 4]

        System.out.println("\n--- Test Case 4: k = 5 (size = 3) ---");
        ListNode list4 = ListNode.createList(new int[]{1, 2, 3});
        System.out.print("Original: ");
        ListNode.printList(list4);
        ListNode result4 = reverseAlternatingKGroup(list4, 5);
        System.out.print("Modified: ");
        ListNode.printList(result4); // Expected: [3 -> 2 -> 1]
    }
}
