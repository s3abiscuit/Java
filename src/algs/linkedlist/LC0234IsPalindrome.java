//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,2,1]
//输出：true
//
//
// 示例 2：
//
//
//输入：head = [1,2]
//输出：false
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围[1, 10⁵] 内
// 0 <= Node.val <= 9
//
//
//
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 栈 递归 链表 双指针 👍 1135 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package algs.linkedlist;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC0234IsPalindrome {
    public static void main(String[] args) {

    }
    // use Deque
    // 时间复杂度 O(N) 空间复杂度 O(N)
    public boolean isPalindrome1(ListNode head) {
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
    // use List
    // 时间复杂度 O(N) 空间复杂度 O(N)
    public boolean isPalindrome2(ListNode head) {
        if (head == null) return false;
        List<Integer> list = new ArrayList<>();
        ListNode walker = head;
        while (walker != null) {
            list.add(walker.val);
            walker = walker.next;
        }
        int front = 0, back = list.size()-1;
        while(front < back) {
            if (list.get(front) != list.get(back)) {
                return false;
            }
            front++;
            back--;
        }
        return true;

    }
    // 时间复杂度 O(N) 空间复杂度 O(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
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

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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
