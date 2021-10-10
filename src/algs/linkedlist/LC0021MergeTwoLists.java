//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表 👍 1938 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package algs.linkedlist;
// 总结1: 对于每个链表都要设置一个 walker
// 总结2: 对于要返回第链表需要设置一个 hair
public class LC0021MergeTwoLists {
    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode hair = new ListNode(-1), cur = hair, cur1 = l1, cur2 = l2;
        while(cur1 != null || cur2 != null) {
            if (cur1 == null) {
                cur.next = cur2;
                break;
            }
            if (cur2 == null) {
                cur.next = cur1;
                break;
            }
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        return hair.next;
    }

    // 相较于方法一更简练
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    // 递归
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else  {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
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
