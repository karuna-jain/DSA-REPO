// LeetCode 141: Linked List Cycle
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class LinkedListCycle {

    /**
     * Determines if a linked list has a cycle.
     * @param head The head of the linked list
     * @return True if a cycle is present, false otherwise
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // Move slow pointer 1 step
            fast = fast.next.next;    // Move fast pointer 2 steps

            if (slow == fast) {       // They met, so there must be a cycle
                return true;
            }
        }

        return false; // Fast reached the end, no cycle
    }

    public static void main(String[] args) {
        // Test Case 1: Linked list with no cycle
        int[] arr = {3, 2, 0, -4};
        ListNode head = ListNode.createList(arr);
        System.out.print("List 1: ");
        ListNode.printList(head);
        System.out.println("Has Cycle? " + hasCycle(head)); // Expected: false

        // Test Case 2: Linked list with a cycle (connect last node to second node)
        ListNode headWithCycle = ListNode.createList(arr);
        // Let's create a cycle: connect -4 to 2 (index 1)
        ListNode tail = headWithCycle;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode secondNode = headWithCycle.next;
        tail.next = secondNode; // -4 points to 2

        System.out.print("List 2 (with cycle from tail to index 1): ");
        ListNode.printList(headWithCycle);
        System.out.println("Has Cycle? " + hasCycle(headWithCycle)); // Expected: true
    }
}
