// LeetCode 2074: Reverse Nodes in Even Length Groups
// Time Complexity: O(N) - Single pass through the list of length N.
// Space Complexity: O(1) auxiliary space - in-place reversal.

public class ReverseEvenLengthGroups {

    /**
     * Reverses the nodes in each group with an even length.
     * The groups are formed sequentially with lengths 1, 2, 3, 4, ...
     * @param head The head of the linked list
     * @return The head of the modified linked list
     */
    public static ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupTail = dummy;
        int expectedLength = 1;

        while (prevGroupTail.next != null) {
            // Step 1: Count the actual nodes in the current group
            int actualLength = 0;
            ListNode curr = prevGroupTail.next;
            while (curr != null && actualLength < expectedLength) {
                curr = curr.next;
                actualLength++;
            }

            // Step 2: Check if actual length is even or odd
            if (actualLength % 2 == 0) {
                // Reverse this group of size actualLength
                ListNode groupHead = prevGroupTail.next;
                ListNode prev = curr; // This points to the node after the current group
                ListNode node = groupHead;
                for (int i = 0; i < actualLength; i++) {
                    ListNode nextNode = node.next;
                    node.next = prev;
                    prev = node;
                    node = nextNode;
                }
                prevGroupTail.next = prev;
                prevGroupTail = groupHead;
            } else {
                // Keep group as is, just skip it by moving the tail pointer
                for (int i = 0; i < actualLength; i++) {
                    prevGroupTail = prevGroupTail.next;
                }
            }

            expectedLength++;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Standard Example ---");
        ListNode list1 = ListNode.createList(new int[]{5, 2, 6, 3, 9, 1, 7, 3, 8, 4});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = reverseEvenLengthGroups(list1);
        System.out.print("Modified: ");
        ListNode.printList(result1); // Expected: [5 -> 6 -> 2 -> 3 -> 9 -> 1 -> 4 -> 8 -> 3 -> 7]

        System.out.println("\n--- Test Case 2: Last group is even, but shorter than expected ---");
        ListNode list2 = ListNode.createList(new int[]{1, 1, 0, 6, 5});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = reverseEvenLengthGroups(list2);
        System.out.print("Modified: ");
        ListNode.printList(result2); 
        // Expected groups: [1] (len 1, odd), [1, 0] (len 2, even -> reversed to [0, 1]), [6, 5] (expected len 3, actual len 2, even -> reversed to [5, 6])
        // Expected: [1 -> 0 -> 1 -> 5 -> 6]

        System.out.println("\n--- Test Case 3: Already correct lengths ---");
        ListNode list3 = ListNode.createList(new int[]{2, 1});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = reverseEvenLengthGroups(list3);
        System.out.print("Modified: ");
        ListNode.printList(result3); // Expected: [2 -> 1]
    }
}
