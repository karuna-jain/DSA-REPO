// GeeksforGeeks: Quick Sort on Singly Linked List
// Time Complexity: O(N log N) average, O(N^2) worst case
// Space Complexity: O(log N) average recursion stack space

public class QuickSortLinkedList {

    /**
     * Sorts the linked list in ascending order using Quick Sort.
     * Reorganizes nodes in-place without creating new nodes or altering values.
     * @param head Head of the linked list
     * @return Head of the sorted linked list
     */
    public static ListNode quickSort(ListNode head) {
        // Base case: empty list or list with a single element
        if (head == null || head.next == null) {
            return head;
        }

        // Use the head node's value as pivot
        ListNode pivot = head;

        // Create dummy heads for partition lists
        ListNode lessHead = new ListNode(0);
        ListNode equalHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);

        ListNode lessTail = lessHead;
        ListNode equalTail = equalHead;
        ListNode greaterTail = greaterHead;

        // Partition nodes into three sublists: less than, equal to, and greater than pivot
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < pivot.val) {
                lessTail.next = curr;
                lessTail = lessTail.next;
            } else if (curr.val > pivot.val) {
                greaterTail.next = curr;
                greaterTail = greaterTail.next;
            } else {
                equalTail.next = curr;
                equalTail = equalTail.next;
            }
            curr = curr.next;
        }

        // Disconnect the tails of the partition sublists
        lessTail.next = null;
        equalTail.next = null;
        greaterTail.next = null;

        // Recursively sort the sublists
        ListNode sortedLess = quickSort(lessHead.next);
        ListNode sortedGreater = quickSort(greaterHead.next);

        // Concatenate the sorted sublists: sortedLess -> equalList -> sortedGreater
        return concatenate(sortedLess, equalHead.next, sortedGreater);
    }

    // Helper method to concatenate three lists: less, equal, and greater
    private static ListNode concatenate(ListNode less, ListNode equal, ListNode greater) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // Link less part
        if (less != null) {
            curr.next = less;
            while (curr.next != null) {
                curr = curr.next;
            }
        }

        // Link equal part
        if (equal != null) {
            curr.next = equal;
            while (curr.next != null) {
                curr = curr.next;
            }
        }

        // Link greater part
        if (greater != null) {
            curr.next = greater;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Test Case 1: Unsorted list with duplicate elements
        int[] arr1 = {30, 3, 4, 20, 5, 30, 2};
        ListNode head1 = ListNode.createList(arr1);
        System.out.print("Original List 1: ");
        ListNode.printList(head1);

        ListNode sorted1 = quickSort(head1);
        System.out.print("Sorted List 1:   ");
        ListNode.printList(sorted1); // Expected: [2 -> 3 -> 4 -> 5 -> 20 -> 30 -> 30]

        // Test Case 2: Already sorted list
        int[] arr2 = {1, 2, 3, 4, 5};
        ListNode head2 = ListNode.createList(arr2);
        System.out.print("\nOriginal List 2: ");
        ListNode.printList(head2);

        ListNode sorted2 = quickSort(head2);
        System.out.print("Sorted List 2:   ");
        ListNode.printList(sorted2); // Expected: [1 -> 2 -> 3 -> 4 -> 5]
    }
}
