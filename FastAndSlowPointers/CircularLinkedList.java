// GeeksforGeeks: Check if a linked list is a circular linked list
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class CircularLinkedList {

    /**
     * Checks if the given singly linked list is circular.
     * In a circular linked list, the last node points back to the first node (head).
     * @param head Head of the linked list
     * @return True if circular, false otherwise
     */
    public static boolean isCircular(ListNode head) {
        // An empty list is considered circular by GFG's problem specification
        if (head == null) {
            return true;
        }

        ListNode temp = head.next;

        // Traverse the list to see if we reach head again or hit null
        while (temp != null && temp != head) {
            temp = temp.next;
        }

        // If we looped back to the head, it is a circular linked list
        return temp == head;
    }

    public static void main(String[] args) {
        // Test Case 1: Circular Linked List [1 -> 2 -> 3 -> 4 -> 1]
        int[] arr1 = {1, 2, 3, 4};
        ListNode head1 = ListNode.createList(arr1);
        
        // Find tail and point it to head to make it circular
        ListNode curr1 = head1;
        while (curr1.next != null) {
            curr1 = curr1.next;
        }
        curr1.next = head1;

        System.out.print("List 1 (circular representation check): ");
        ListNode.printList(head1); // printList has safety check to avoid infinite print loops
        System.out.println("Is Circular? " + isCircular(head1)); // Expected: true

        // Test Case 2: Standard Linear Linked List [1 -> 2 -> 3 -> 4 -> null]
        int[] arr2 = {1, 2, 3, 4};
        ListNode head2 = ListNode.createList(arr2);
        System.out.print("\nList 2 (linear representation): ");
        ListNode.printList(head2);
        System.out.println("Is Circular? " + isCircular(head2)); // Expected: false

        // Test Case 3: Empty List
        System.out.println("\nEmpty List Is Circular? " + isCircular(null)); // Expected: true
    }
}
