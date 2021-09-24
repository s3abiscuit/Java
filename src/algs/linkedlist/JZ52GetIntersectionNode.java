package algs.linkedlist;

// 输入两个链表，找出它们的第一个公共节点。
public class JZ52GetIntersectionNode {
    private static ListNode node1, node2;

    public static void main(String[] args) {
        createNode();
        //   1,2,3,4,5
        // 6,7,2,3,4,5
        System.out.println(getIntersectionNode(node1, node2).val);
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    private static void createNode() {
        node1 = new ListNode(1);
        ListNode tmp = new ListNode(2);
        node1.next = tmp;
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);
        node2 = new ListNode(6);
        node2.next = new ListNode(7);
        node2.next.next = tmp;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

}
