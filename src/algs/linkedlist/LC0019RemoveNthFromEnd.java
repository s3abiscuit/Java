//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 进阶：你能尝试使用一趟扫描实现吗？
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1], n = 1
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1,2], n = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中结点的数目为 sz
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
// Related Topics 链表 双指针 👍 1585 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package algs.linkedlist;

public class LC0019RemoveNthFromEnd {
    public static void main(String[] args) {

    }
    // 倒数第 n 个， 正数应该是 size-n+1 个
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // case1 []
        if (head == null) return null;

        ListNode pre = new ListNode(-1, head), walker = pre, hair = pre;

        // 循环执行 n 次
        for (int i=0; i<n; i++){
            walker = walker.next;
        }

        // 循环执行 size-n 次
        // pre 指向第 size-n 个值
        while(walker.next != null) {
            pre = pre.next;
            walker = walker.next;
        }

        pre.next = pre.next.next;
        return hair.next;

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
