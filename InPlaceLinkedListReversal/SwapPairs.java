// LeetCode 24: Swap Nodes in Pairs
// Time Complexity: O(N) - Single pass through the list of length N.
// Space Complexity: O(1) auxiliary space - in-place swapping.

public class SwapPairs {

    /**
     * Swaps every two adjacent nodes in-place.
     * @param head The head of the linked list
     * @return The head of the modified linked list
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Swapping pointers
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Re-position prev for the next pair
            prev = first;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Even length list ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = swapPairs(list1);
        System.out.print("Swapped:  ");
        ListNode.printList(result1); // Expected: [2 -> 1 -> 4 -> 3]

        System.out.println("\n--- Test Case 2: Odd length list ---");
        ListNode list2 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = swapPairs(list2);
        System.out.print("Swapped:  ");
        ListNode.printList(result2); // Expected: [2 -> 1 -> 4 -> 3 -> 5]

        System.out.println("\n--- Test Case 3: Empty list ---");
        ListNode list3 = ListNode.createList(new int[]{});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = swapPairs(list3);
        System.out.print("Swapped:  ");
        ListNode.printList(result3); // Expected: []
    }
}
