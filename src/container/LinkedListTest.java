package container;

import java.util.Deque;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {

    }

    // LinkedList used as Deque
    // LC0234 判断链表是否回文
    public boolean isPalindrome(ListNode head) {
        if(head.next == null) return true;
        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        while(cur != null) {
            deque.addLast(cur);
            cur = cur.next;
        }
        while(!deque.isEmpty()){
            if (deque.getFirst() == deque.getLast()) {
                deque.removeFirst();
                continue;
            }
            if (deque.getFirst().val == deque.getLast().val) {
                deque.removeFirst();
                deque.removeLast();
                continue;
            }
            return false;
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

}

