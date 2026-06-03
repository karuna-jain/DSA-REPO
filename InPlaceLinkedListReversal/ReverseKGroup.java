// LeetCode 25: Reverse Nodes in k-Group
// Time Complexity: O(N) - Each node is visited at most twice.
// Space Complexity: O(1) auxiliary space - in-place reversal.

public class ReverseKGroup {

    /**
     * Reverses nodes of a linked list k at a time and returns the modified list.
     * If the number of nodes is not a multiple of k, left-over nodes at the end should remain as is.
     * @param head The head of the linked list
     * @param k The size of each group to reverse
     * @return The head of the modified linked list
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupTail = dummy;

        while (true) {
            // Check if there are at least k nodes remaining
            ListNode kthNode = prevGroupTail;
            for (int i = 0; i < k && kthNode != null; i++) {
                kthNode = kthNode.next;
            }

            // If there are fewer than k nodes, keep the rest as is
            if (kthNode == null) {
                break;
            }

            ListNode nextGroupHead = kthNode.next;
            ListNode groupHead = prevGroupTail.next;

            // Reverse the current group of size k
            ListNode prev = nextGroupHead;
            ListNode curr = groupHead;
            for (int i = 0; i < k; i++) {
                ListNode nextNode = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextNode;
            }

            // Connect the previous group to the reversed head
            prevGroupTail.next = prev;

            // Move the tail pointer to the end of the reversed group
            prevGroupTail = groupHead;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: k = 2 ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = reverseKGroup(list1, 2);
        System.out.print("Modified: ");
        ListNode.printList(result1); // Expected: [2 -> 1 -> 4 -> 3 -> 5]

        System.out.println("\n--- Test Case 2: k = 3 ---");
        ListNode list2 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = reverseKGroup(list2, 3);
        System.out.print("Modified: ");
        ListNode.printList(result2); // Expected: [3 -> 2 -> 1 -> 4 -> 5]

        System.out.println("\n--- Test Case 3: k = 5 (list size = 5) ---");
        ListNode list3 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = reverseKGroup(list3, 5);
        System.out.print("Modified: ");
        ListNode.printList(result3); // Expected: [5 -> 4 -> 3 -> 2 -> 1]

        System.out.println("\n--- Test Case 4: k = 1 ---");
        ListNode list4 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list4);
        ListNode result4 = reverseKGroup(list4, 1);
        System.out.print("Modified: ");
        ListNode.printList(result4); // Expected: [1 -> 2 -> 3 -> 4 -> 5]
    }
}
