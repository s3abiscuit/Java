package algs.linkedlist;

import java.util.LinkedList;

public class Basic {

    public static void main(String[] args) {
        traverse(createNode());
        remove(createNode(), 3);
    }

    private static void remove(ListNode listNode, int value) {
        traverse1(listNode);
        ListNode tmp = listNode, pre = new ListNode(-1, listNode);
        while (tmp != null) {
            if (tmp.val == value) {
                pre.next = tmp.next;
            }
            pre = tmp;
            tmp = tmp.next;
        }
        traverse1(listNode);
    }

    private static void traverse(ListNode l1) {
        // 1->2->3->4->5
        System.out.println("--正序--");
        System.out.println("--循环--");
        traverse1(l1);  // 循环
        System.out.println("--递归--");
        traverse2(l1);  // 递归

        System.out.println("--逆序--");
        System.out.println("--循环--");
        traverse3(l1);
        System.out.println("--递归--");
        traverse4(l1);
    }

    private static void traverse1(ListNode head) {
        for (ListNode tmp = head; tmp != null; tmp = tmp.next){
            System.out.println(tmp.val);
        }
    }

    private static void traverse2(ListNode head) {
        // 递归访问 head.val
        if (head == null) return;
        System.out.println(head.val);
        traverse2(head.next);
    }

    private static void traverse3(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (ListNode tmp = head; tmp != null; tmp = tmp.next){
            stack.push(tmp.val);
        }
        for(int i : stack) {
            System.out.println(i);
        }
    }

    private static void traverse4(ListNode head) {
        if (head == null) return;
        traverse4(head.next);
        System.out.println(head.val);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    private static ListNode createNode() {
        ListNode head;
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        return head;
    }
}
