// LeetCode 160 / GeeksforGeeks: Intersection Point of two Linked Lists
// Time Complexity: O(N + M) where N and M are the lengths of the two linked lists
// Space Complexity: O(1) auxiliary space

public class IntersectionPoint {

    /**
     * Finds the node at which the intersection of two singly linked lists begins.
     * @param headA Head of the first linked list
     * @param headB Head of the second linked list
     * @return The intersection node, or null if there is no intersection
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        // Traverse both lists. When a pointer reaches the end, redirect it to the other list's head.
        // If they intersect, they will meet at the intersection point after at most N + M steps.
        // If they don't intersect, they will both reach null at the same time and loop terminates.
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA; // Contains intersection node or null
    }

    public static void main(String[] args) {
        // Construct intersecting linked lists:
        // Common suffix: 8 -> 4 -> 5
        ListNode common = ListNode.createList(new int[]{8, 4, 5});

        // List A: 4 -> 1 -> 8 -> 4 -> 5
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        // List B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        System.out.print("List A: ");
        ListNode.printList(headA);
        System.out.print("List B: ");
        ListNode.printList(headB);

        ListNode intersectionNode = getIntersectionNode(headA, headB);
        if (intersectionNode != null) {
            System.out.println("Intersection Point Node Value: " + intersectionNode.val); // Expected: 8
        } else {
            System.out.println("No Intersection Point found.");
        }

        // Test Case 2: Non-intersecting lists
        ListNode headC = ListNode.createList(new int[]{1, 2, 3});
        ListNode headD = ListNode.createList(new int[]{4, 5});
        System.out.print("\nList C: ");
        ListNode.printList(headC);
        System.out.print("List D: ");
        ListNode.printList(headD);

        ListNode noIntersection = getIntersectionNode(headC, headD);
        if (noIntersection != null) {
            System.out.println("Intersection Point Node Value: " + noIntersection.val);
        } else {
            System.out.println("No Intersection Point found. (Correct)"); // Expected
        }
    }
}
