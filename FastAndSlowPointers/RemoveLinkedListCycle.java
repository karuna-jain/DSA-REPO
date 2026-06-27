// Problem: Detect and Remove Loop in a Linked List (GeeksforGeeks)
// Time Complexity: O(N) - We traverse the list at most twice.
// Space Complexity: O(1) auxiliary space.

public class RemoveLinkedListCycle {

    /**
     * Detects if a cycle exists in the linked list and removes it.
     * 
     * @param head The head of the linked list
     */
    public static void removeCycle(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        // Step 1: Detect cycle using Floyd's Cycle-Finding Algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // If no cycle exists, there is nothing to remove
        if (!hasCycle) {
            return;
        }

        // Step 2: Find the start of the cycle and break it
        slow = head;

        // Case A: The cycle starts at the head node itself
        if (slow == fast) {
            // Find the last node (tail) of the cycle
            while (fast.next != slow) {
                fast = fast.next;
            }
            // Break the cycle
            fast.next = null;
            return;
        }

        // Case B: The cycle starts at some middle node
        // Move both pointers at the same pace until their next pointers meet
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }

        // 'fast.next' points to the start of the cycle, so 'fast' is the tail of the cycle
        fast.next = null; // Break the cycle
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Cycle in the middle ---");
        ListNode head1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        // Create a cycle: connect 5 (index 4) to 3 (index 2)
        ListNode tail1 = head1;
        while (tail1.next != null) {
            tail1 = tail1.next;
        }
        ListNode cycleStart1 = head1.next.next; // Node with value 3
        tail1.next = cycleStart1;               // 5 points to 3

        System.out.print("Before removing cycle: ");
        ListNode.printList(head1); // printList has safety check to avoid infinite loop
        
        removeCycle(head1);
        
        System.out.print("After removing cycle:  ");
        ListNode.printList(head1); // Expected: [1 -> 2 -> 3 -> 4 -> 5]

        System.out.println("\n--- Test Case 2: Cycle at the head ---");
        ListNode head2 = ListNode.createList(new int[]{1, 2, 3});
        // Create a cycle: connect 3 (index 2) to 1 (index 0)
        ListNode tail2 = head2;
        while (tail2.next != null) {
            tail2 = tail2.next;
        }
        tail2.next = head2; // 3 points to 1

        System.out.print("Before removing cycle: ");
        ListNode.printList(head2);
        
        removeCycle(head2);
        
        System.out.print("After removing cycle:  ");
        ListNode.printList(head2); // Expected: [1 -> 2 -> 3]

        System.out.println("\n--- Test Case 3: No cycle ---");
        ListNode head3 = ListNode.createList(new int[]{10, 20, 30});
        System.out.print("Before removing cycle: ");
        ListNode.printList(head3);
        
        removeCycle(head3);
        
        System.out.print("After removing cycle:  ");
        ListNode.printList(head3); // Expected: [10 -> 20 -> 30]
    }
}
