// Problem: Reverse a Linked List in groups of Given Size (GeeksforGeeks)
// Time Complexity: O(N) - Single pass through the list of length N.
// Space Complexity:
//   - Iterative: O(1) auxiliary space.
//   - Recursive: O(N/k) auxiliary space due to the call stack.

public class ReverseKGroupGFG {

    /**
     * Reverses the linked list in groups of size k recursively.
     * Leftover nodes at the end (fewer than k) are also reversed.
     * 
     * @param head The head of the linked list
     * @param k The group size
     * @return The head of the modified linked list
     */
    public static ListNode reverseRecursive(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        int count = 0;

        // Reverse first k nodes of the linked list
        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        // 'next' now points to the (k+1)th node
        // Recursively call for the rest of the list and link it
        if (next != null) {
            head.next = reverseRecursive(next, k);
        }

        // 'prev' is now the new head of this reversed sublist
        return prev;
    }

    /**
     * Reverses the linked list in groups of size k iteratively.
     * Leftover nodes at the end (fewer than k) are also reversed.
     * 
     * @param head The head of the linked list
     * @param k The group size
     * @return The head of the modified linked list
     */
    public static ListNode reverseIterative(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupTail = dummy;
        ListNode curr = head;

        while (curr != null) {
            ListNode groupHead = curr;
            ListNode prev = null;
            int count = 0;

            // Reverse at most k nodes
            while (curr != null && count < k) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
                count++;
            }

            // Connect the previous group's tail to the current group's reversed head
            prevGroupTail.next = prev;
            // The current group's head (which is now the tail) becomes the previous group tail for the next iteration
            prevGroupTail = groupHead;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: k = 3 (Recursive) ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = reverseRecursive(list1, 3);
        System.out.print("Modified: ");
        ListNode.printList(result1); // Expected: [3 -> 2 -> 1 -> 5 -> 4]

        System.out.println("\n--- Test Case 2: k = 3 (Iterative) ---");
        ListNode list2 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = reverseIterative(list2, 3);
        System.out.print("Modified: ");
        ListNode.printList(result2); // Expected: [3 -> 2 -> 1 -> 5 -> 4]

        System.out.println("\n--- Test Case 3: k = 5 (list size = 5) ---");
        ListNode list3 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = reverseIterative(list3, 5);
        System.out.print("Modified: ");
        ListNode.printList(result3); // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        System.out.println("\n--- Test Case 4: k = 1 ---");
        ListNode list4 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list4);
        ListNode result4 = reverseIterative(list4, 1);
        System.out.print("Modified: ");
        ListNode.printList(result4); // Expected: [1 -> 2 -> 3 -> 4 -> 5]
    }
}
