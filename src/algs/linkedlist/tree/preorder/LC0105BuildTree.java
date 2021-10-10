//给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
//
//
//
// 示例 1:
//
//
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
//
//
//
//
// 提示:
//
//
// 1 <= preorder.length <= 3000
// inorder.length == preorder.length
// -3000 <= preorder[i], inorder[i] <= 3000
// preorder 和 inorder 均无重复元素
// inorder 均出现在 preorder
// preorder 保证为二叉树的前序遍历序列
// inorder 保证为二叉树的中序遍历序列
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1253 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package algs.linkedlist.tree.preorder;

public class LC0105BuildTree {
    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] pre, int[] in, int pl, int pr, int il, int ir) {
        // 注意边界条件很重要
        if (pl > pr) return null;
        int preVal = pre[pl], index = il;
        TreeNode root = new TreeNode(preVal);
        for (int i = il; i <= ir; i++) {
            if (preVal == in[i]) {
                index = i;
                break;
            }
        }
        root.left = helper(pre, in, pl + 1, pl + index - il, il, index - 1);
        root.right = helper(pre, in, pl + index - il + 1, pr, index + 1, ir);
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
