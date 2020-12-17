package com.study.leetcode.lc_21;

public class Solution {
    /*给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i​​​​​​​​​​​​ 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
    客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。

    示例 1：

    输入：accounts = [[1,2,3],[3,2,1]]
    输出：6
    解释：
    第 1 位客户的资产总量 = 1 + 2 + 3 = 6
    第 2 位客户的资产总量 = 3 + 2 + 1 = 6
    两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/richest-customer-wealth
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
    public int maximumWealth(int[][] accounts) {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++) {
            int temp = 0;
            for (int i1 = 0; i1 < accounts[i].length; i1++) {
                temp += accounts[i][i1];
            }
            if (temp > sum) {
                sum = temp;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
