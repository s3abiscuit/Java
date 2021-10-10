package algs.linkedlist;

import java.util.LinkedList;

// 链表的操作
// 1. 递归 recursion
// 2. 循环 iteration
// 3. 双指针: 环, 环的入口, 中间节点
// 4. 技巧操作: 两个链表的第一个公共节点

public class Basic {

    public static void main(String[] args) {
        // 循环和递归实现遍历
        traverse(createNode());
        // 循环实现翻转
        traverse1(reverseIteration(createNode()));
        // 递归实现翻转
        traverse1(reverseRecursion(createNode()));
        // 双指针操作
        System.out.println(endOfFirstHalf(createNode()).val);
        System.out.println(hasCycle(createNode()));
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

    private static ListNode reverseIteration(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private static ListNode reverseRecursion(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode tmp = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;
    }

    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
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
