// Standard definition for a singly-linked list node.
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to print the linked list (to avoid infinite loops, stops after a limit if cycle exists)
    public static void printList(ListNode head) {
        ListNode current = head;
        int count = 0;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            current = current.next;
            if (current != null) {
                System.out.print(" -> ");
            }
            count++;
            if (count > 25) { // Safe break to prevent infinite printing if there is a cycle
                System.out.print(" -> ... (cycle detected or list too long)");
                break;
            }
        }
        System.out.println("]");
    }
}
