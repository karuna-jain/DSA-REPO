// GeeksforGeeks: Intersection of two Sorted Linked Lists
// Time Complexity: O(N + M) where N and M are the lengths of the two linked lists
// Space Complexity: O(1) auxiliary space (O(min(N, M)) to store the output list)

public class IntersectionSortedLists {

    /**
     * Computes the intersection of two sorted linked lists.
     * A new list is created containing only the elements common to both lists.
     * @param head1 Head of the first sorted linked list
     * @param head2 Head of the second sorted linked list
     * @return Head of the intersection linked list
     */
    public static ListNode getIntersection(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        ListNode p1 = head1;
        ListNode p2 = head2;

        while (p1 != null && p2 != null) {
            if (p1.val == p2.val) {
                // If it is the first node or different from the last inserted (to handle duplicates optionally)
                // Note: Standard GFG intersection maintains duplicates if they are present in both lists.
                // We will implement standard intersection maintaining duplicate pairs if they match.
                current.next = new ListNode(p1.val);
                current = current.next;
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.val < p2.val) {
                p1 = p1.next;
            } else {
                p2 = p2.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Test Case 1: Overlapping elements
        int[] arr1 = {1, 2, 3, 4, 6};
        int[] arr2 = {2, 4, 6, 8};
        ListNode head1 = ListNode.createList(arr1);
        ListNode head2 = ListNode.createList(arr2);

        System.out.print("List 1: ");
        ListNode.printList(head1);
        System.out.print("List 2: ");
        ListNode.printList(head2);

        ListNode intersection1 = getIntersection(head1, head2);
        System.out.print("Intersection: ");
        ListNode.printList(intersection1); // Expected: [2 -> 4 -> 6]

        // Test Case 2: No common elements
        int[] arr3 = {10, 20, 30};
        int[] arr4 = {15, 25, 35};
        ListNode head3 = ListNode.createList(arr3);
        ListNode head4 = ListNode.createList(arr4);

        System.out.print("\nList 3: ");
        ListNode.printList(head3);
        System.out.print("List 4: ");
        ListNode.printList(head4);

        ListNode intersection2 = getIntersection(head3, head4);
        System.out.print("Intersection: ");
        ListNode.printList(intersection2); // Expected: []
    }
}
