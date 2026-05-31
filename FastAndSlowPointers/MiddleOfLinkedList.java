// LeetCode 876: Middle of the Linked List
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class MiddleOfLinkedList {

    /**
     * Finds the middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
     * @param head The head of the linked list
     * @return The middle node of the linked list
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Moves 1 step
            fast = fast.next.next;  // Moves 2 steps
        }

        return slow; // When fast reaches the end, slow is at the middle
    }

    public static void main(String[] args) {
        // Test Case 1: Odd number of elements [1, 2, 3, 4, 5]
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode head1 = ListNode.createList(arr1);
        System.out.print("Original List 1: ");
        ListNode.printList(head1);
        
        ListNode mid1 = middleNode(head1);
        System.out.print("Middle Node onwards: ");
        ListNode.printList(mid1); // Expected: [3, 4, 5]

        // Test Case 2: Even number of elements [1, 2, 3, 4, 5, 6]
        int[] arr2 = {1, 2, 3, 4, 5, 6};
        ListNode head2 = ListNode.createList(arr2);
        System.out.print("\nOriginal List 2: ");
        ListNode.printList(head2);
        
        ListNode mid2 = middleNode(head2);
        System.out.print("Middle Node onwards (second middle): ");
        ListNode.printList(mid2); // Expected: [4, 5, 6]
    }
}
