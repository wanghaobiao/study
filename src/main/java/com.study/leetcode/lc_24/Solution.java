package com.study.leetcode.lc_24;

public class Solution {

    /*输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

    示例 1:
    输入: n = 1
    输出: [1,2,3,4,5,6,7,8,9]*/
    public int[] printNumbers(int n) {
        int temp = 0;
        int[] rt = new int[(int)Math.pow(10, n) - 1];
        while (temp < (int)Math.pow(10, n) - 1){
            System.out.println(temp);
            rt[temp] = temp + 1;
            temp++;
        }
        return rt;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.printNumbers( 2 );
    }
}
