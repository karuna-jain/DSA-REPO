// LeetCode 328: Odd Even Linked List
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class OddEvenLinkedList {

    /**
     * Groups all odd nodes together followed by all even nodes.
     * Note: We are referring to the node's position (1-indexed), not the value.
     * @param head The head of the linked list
     * @return The rearranged linked list
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even; // Store the start of the even list to attach to the odd list at the end

        while (even != null && even.next != null) {
            odd.next = even.next;    // Connect current odd to next odd
            odd = odd.next;          // Move odd pointer forward
            
            even.next = odd.next;    // Connect current even to next even
            even = even.next;        // Move even pointer forward
        }

        odd.next = evenHead; // Connect end of odd list to head of even list
        return head;
    }

    public static void main(String[] args) {
        // Test Case 1: [1, 2, 3, 4, 5]
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode head1 = ListNode.createList(arr1);
        System.out.print("Original List 1: ");
        ListNode.printList(head1);
        
        ListNode result1 = oddEvenList(head1);
        System.out.print("Rearranged List 1 (Odd-Even): ");
        ListNode.printList(result1); // Expected: [1 -> 3 -> 5 -> 2 -> 4]

        // Test Case 2: [2, 1, 3, 5, 6, 4, 7]
        int[] arr2 = {2, 1, 3, 5, 6, 4, 7};
        ListNode head2 = ListNode.createList(arr2);
        System.out.print("\nOriginal List 2: ");
        ListNode.printList(head2);
        
        ListNode result2 = oddEvenList(head2);
        System.out.print("Rearranged List 2 (Odd-Even): ");
        ListNode.printList(result2); // Expected: [2 -> 3 -> 6 -> 7 -> 1 -> 5 -> 4]
    }
}
