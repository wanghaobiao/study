package com.study.leetcode.lc_5;

public class Solution {

    public String defangIPaddr(String address) {
        StringBuffer sb = new StringBuffer();
        final String value = "[.]";
        char[] list = address.toCharArray();
        for (int i = 0; i < list.length; i++) {
            char temp = list[i];
            if(temp == '.'){
                sb.append(value);
            }else{
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    /**
     * 题目描述
     * 评论 (218)
     * 题解(197)
     * 提交记录
     * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
     *
     * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
     *
     * 示例 1：
     *
     * 输入：address = "1.1.1.1"
     * 输出："1[.]1[.]1[.]1"
     */
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.defangIPaddr("1.1.1.1"));

    }
}
