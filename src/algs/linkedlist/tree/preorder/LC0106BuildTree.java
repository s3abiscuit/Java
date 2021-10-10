//根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 596 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package algs.linkedlist.tree.preorder;

public class LC0106BuildTree {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode helper(int[] in, int[] post, int il, int ir, int pl, int pr) {
        // 注意边界条件很重要
        if (pl > pr) return null;
        int val = post[pr], index = il;
        TreeNode root = new TreeNode(val);
        for (int i = il; i <= ir; i++) {
            if (val == in[i]) {
                index = i;
                break;
            }
        }
        root.left = helper(in, post, il, index - 1, pl, pl + index - 1 - il);
        root.right = helper(in, post, index + 1, ir, pl + index - il, pr - 1);
        return root;
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
