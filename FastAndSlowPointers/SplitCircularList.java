// GeeksforGeeks: Split a Circular Linked List into two halves
// Time Complexity: O(N) where N is the number of nodes in the linked list
// Space Complexity: O(1) auxiliary space

public class SplitCircularList {

    /**
     * Splits a circular linked list into two circular halves.
     * If the number of nodes is odd, the first half gets the extra node.
     * @param head Head of the circular linked list
     * @return An array of two ListNode objects representing the heads of the two halves
     */
    public static ListNode[] splitList(ListNode head) {
        if (head == null) {
            return new ListNode[]{null, null};
        }
        if (head.next == head) {
            return new ListNode[]{head, null};
        }

        ListNode slow = head;
        ListNode fast = head;

        // Using fast and slow pointers to locate the midpoint
        // Loop terminates when fast.next or fast.next.next loops back to head
        while (fast.next != head && fast.next.next != head) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // If there are even elements in the list, fast.next.next will be head
        // In this case, we advance fast one more step to point to the last node
        if (fast.next.next == head) {
            fast = fast.next;
        }

        // Set the heads of both halves
        ListNode head1 = head;
        ListNode head2 = slow.next;

        // Make the second half circular: tail (fast) points to head2
        fast.next = head2;

        // Make the first half circular: tail (slow) points to head1 (head)
        slow.next = head1;

        return new ListNode[]{head1, head2};
    }

    /**
     * Helper to print circular list nicely as [val1 -> val2 -> (head)]
     */
    public static void printCircularList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        ListNode temp = head;
        System.out.print("[");
        do {
            System.out.print(temp.val);
            temp = temp.next;
            if (temp != head) {
                System.out.print(" -> ");
            }
        } while (temp != head && temp != null);
        
        if (temp == head) {
            System.out.print(" -> (head)");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Test Case 1: Odd number of elements (5 nodes)
        int[] arr1 = {1, 2, 3, 4, 5};
        ListNode head1 = ListNode.createList(arr1);
        ListNode curr1 = head1;
        while (curr1.next != null) {
            curr1 = curr1.next;
        }
        curr1.next = head1; // Make circular

        System.out.print("Original Circular List (Odd Size 5): ");
        printCircularList(head1);

        ListNode[] halves1 = splitList(head1);
        System.out.print("First Half: ");
        printCircularList(halves1[0]); // Expected: [1 -> 2 -> 3 -> (head)]
        System.out.print("Second Half: ");
        printCircularList(halves1[1]); // Expected: [4 -> 5 -> (head)]

        // Test Case 2: Even number of elements (4 nodes)
        int[] arr2 = {10, 20, 30, 40};
        ListNode head2 = ListNode.createList(arr2);
        ListNode curr2 = head2;
        while (curr2.next != null) {
            curr2 = curr2.next;
        }
        curr2.next = head2; // Make circular

        System.out.print("\nOriginal Circular List (Even Size 4): ");
        printCircularList(head2);

        ListNode[] halves2 = splitList(head2);
        System.out.print("First Half: ");
        printCircularList(halves2[0]); // Expected: [10 -> 20 -> (head)]
        System.out.print("Second Half: ");
        printCircularList(halves2[1]); // Expected: [30 -> 40 -> (head)]
    }
}
