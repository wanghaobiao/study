package com.study.leetcode.lc_20;

public class Solution {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt( i ) == ' ') {
                sb.append( "%20" );
            }else{
                sb.append( s.charAt( i ) );
            }
        }
        return sb.toString();
    }
    /*示例 1：

    输入：s = "We are happy."
    输出："We%20are%20happy."*/
    public static void main(String[] args) {
        Solution solution = new Solution();
        "".replace( "","" );
    }
}
