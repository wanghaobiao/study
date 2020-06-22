package com.study.leetcode.lc_7;

public class Solution {
    public int getDecimalValue(ListNode head) {
        StringBuffer sb = new StringBuffer();
        forNode(head,sb);
        System.out.println(sb.toString());
        return Integer.parseInt(sb.toString(),2);
    };

    public void forNode(ListNode head,StringBuffer sb){
        sb.append(head.val);
        if(head.next == null){
            return;
        }else {
            forNode(head.next,sb);
        }
    };

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(0);
        listNode.next.next = new ListNode(1);
        Solution solution = new Solution();
        System.out.println(solution.getDecimalValue(listNode));
    }
}
