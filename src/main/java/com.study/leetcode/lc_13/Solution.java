package com.study.leetcode.lc_13;

public class Solution {
    /*给定两个二叉树，编写一个函数来检验它们是否相同。

    如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

    示例 1:

    输入:       1         1
            / \       / \
            2   3     2   3

            [1,2,3],   [1,2,3]

    输出: true

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/same-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left , q.left) && isSameTree(p.right , q.right);
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode p = new TreeNode();
        TreeNode q = new TreeNode();
        solution.isSameTree( p,q );
    }
}
