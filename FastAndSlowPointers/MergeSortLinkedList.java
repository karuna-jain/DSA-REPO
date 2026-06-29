// LeetCode 148: Sort List (Merge Sort For Linked Lists)
// Time Complexity: O(N log N) where N is the number of nodes in the linked list
// Space Complexity: O(log N) recursion stack space

public class MergeSortLinkedList {

    /**
     * Sorts the linked list in ascending order using Merge Sort.
     * @param head Head of the linked list
     * @return Head of the sorted linked list
     */
    public static ListNode mergeSort(ListNode head) {
        // Base case: if head is null or there is only one element
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Get the middle node of the list
        ListNode middle = getMiddle(head);
        ListNode nextOfMiddle = middle.next;

        // Step 2: Split the list into two halves
        middle.next = null;

        // Step 3: Recursively sort both halves
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(nextOfMiddle);

        // Step 4: Merge the sorted left and right halves
        return sortedMerge(left, right);
    }

    // Helper method to find the middle of a linked list (returns the end of the first half)
    private static ListNode getMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        // Moves slow by 1 and fast by 2. When fast reaches end, slow is at the middle.
        // We use fast.next != null && fast.next.next != null to ensure slow is the tail of the first half.
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Helper method to merge two sorted linked lists
    private static ListNode sortedMerge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }

        // Append remaining nodes
        if (a != null) {
            curr.next = a;
        } else {
            curr.next = b;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Test Case 1: Unsorted list with duplicate values
        int[] arr1 = {4, 2, 1, 3, 2, 5};
        ListNode head1 = ListNode.createList(arr1);
        System.out.print("Original List 1: ");
        ListNode.printList(head1);

        ListNode sorted1 = mergeSort(head1);
        System.out.print("Sorted List 1:   ");
        ListNode.printList(sorted1); // Expected: [1 -> 2 -> 2 -> 3 -> 4 -> 5]

        // Test Case 2: Already sorted list
        int[] arr2 = {-1, 5, 10, 20};
        ListNode head2 = ListNode.createList(arr2);
        System.out.print("\nOriginal List 2: ");
        ListNode.printList(head2);

        ListNode sorted2 = mergeSort(head2);
        System.out.print("Sorted List 2:   ");
        ListNode.printList(sorted2); // Expected: [-1 -> 5 -> 10 -> 20]
    }
}
