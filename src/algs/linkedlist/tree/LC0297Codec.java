//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 10⁴] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 638 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package algs.linkedlist.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LC0297Codec {
    public static void main(String[] args) {

    }

    static class Codec {
        public String serialize(TreeNode root) {
            StringBuilder ans = new StringBuilder();
            // 用队列实现层次遍历
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                // 如果当前节点为空
                if (node == null) {
                    ans.append('n');
                    ans.append(',');
                } else {
                    // 当前节点不为空
                    ans.append(node.val);
                    ans.append(',');
                    q.add(node.left);
                    q.add(node.right);
                }

            }
            ans.delete(ans.length() - 1, ans.length());
            return ans.toString();
        }

        private TreeNode charToNode(String ch) {
            if (ch.equals("n")) return null;
            else {
                int val = Integer.parseInt(ch);
                return new TreeNode(val);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<TreeNode> q = new LinkedList<>();
            String[] s = data.split(",");
            int index = 0;
            TreeNode root = charToNode(s[index]);
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                if (node != null) {
                    node.left = charToNode(s[++index]);
                    node.right = charToNode(s[++index]);
                    q.add(node.left);
                    q.add(node.right);
                }
            }
            return root;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}

