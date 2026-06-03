// LeetCode 61: Rotate List
// Time Complexity: O(N) - Two passes through the list of length N.
// Space Complexity: O(1) auxiliary space - in-place pointer manipulation.

public class RotateList {

    /**
     * Rotates the list to the right by k places in-place.
     * @param head The head of the linked list
     * @param k The number of places to rotate
     * @return The new head of the rotated list
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: Calculate the length of the list and find the tail node
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // Step 2: Adjust k based on the list length
        k = k % len;
        if (k == 0) {
            return head;
        }

        // Step 3: Link tail to head to form a circular list
        tail.next = head;

        // Step 4: Find the new tail (len - k - 1 steps from head) and new head
        ListNode newTail = head;
        for (int i = 0; i < len - k - 1; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;

        // Step 5: Break the cycle
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1: Rotate by k = 2 (size = 5) ---");
        ListNode list1 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list1);
        ListNode result1 = rotateRight(list1, 2);
        System.out.print("Rotated:  ");
        ListNode.printList(result1); // Expected: [4 -> 5 -> 1 -> 2 -> 3]

        System.out.println("\n--- Test Case 2: Rotate by k = 4 (size = 3) ---");
        ListNode list2 = ListNode.createList(new int[]{0, 1, 2});
        System.out.print("Original: ");
        ListNode.printList(list2);
        ListNode result2 = rotateRight(list2, 4); // 4 % 3 = 1 rotation
        System.out.print("Rotated:  ");
        ListNode.printList(result2); // Expected: [2 -> 0 -> 1]

        System.out.println("\n--- Test Case 3: k = 0 ---");
        ListNode list3 = ListNode.createList(new int[]{1, 2});
        System.out.print("Original: ");
        ListNode.printList(list3);
        ListNode result3 = rotateRight(list3, 0);
        System.out.print("Rotated:  ");
        ListNode.printList(result3); // Expected: [1 -> 2]

        System.out.println("\n--- Test Case 4: Rotate by list length multiple (k = 5, size = 5) ---");
        ListNode list4 = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        ListNode.printList(list4);
        ListNode result4 = rotateRight(list4, 5); // 5 % 5 = 0 rotations
        System.out.print("Rotated:  ");
        ListNode.printList(result4); // Expected: [1 -> 2 -> 3 -> 4 -> 5]
    }
}
