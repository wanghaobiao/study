package com.study.leetcode.lc_3;

public class Solution {

    public int subtractProductAndSum(int n) {
        int sum = 0;
        int product = 1;
        while (n != 0){
            sum += n%10;
            product *= n%10;
            n /= 10;
        }
        return product - sum;

    }

    /**
     * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
     *
     * 示例 1：
     *
     * 输入：n = 234
     * 输出：15
     * 解释：
     * 各位数之积 = 2 * 3 * 4 = 24
     * 各位数之和 = 2 + 3 + 4 = 9
     * 结果 = 24 - 9 = 15
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.subtractProductAndSum(234));

    }
}
