// LeetCode 142: Linked List Cycle II (Find Cycle Start)
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class LinkedListCycleStart {

    /**
     * Finds the node where the cycle begins in the linked list.
     * @param head The head of the linked list
     * @return The node where the cycle starts, or null if no cycle exists
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        // Step 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) {
            return null; // No cycle
        }

        // Step 2: Find the start of the cycle
        slow = head; // Reset slow to the head
        while (slow != fast) {
            slow = slow.next; // Move slow 1 step
            fast = fast.next; // Move fast 1 step
        }

        return slow; // The start of the cycle
    }

    public static void main(String[] args) {
        // Create a list with a cycle: [3, 2, 0, -4], cycle tail points to 2 (index 1)
        int[] arr = {3, 2, 0, -4};
        ListNode head = ListNode.createList(arr);
        
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode cycleStartNode = head.next; // The node with value 2
        tail.next = cycleStartNode;          // Create the cycle

        System.out.print("Linked List: ");
        ListNode.printList(head);
        
        ListNode start = detectCycle(head);
        if (start != null) {
            System.out.println("Cycle detected! Starts at node with value: " + start.val); // Expected: 2
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
