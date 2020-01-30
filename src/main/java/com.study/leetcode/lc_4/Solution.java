package com.study.leetcode.lc_4;

public class Solution {

    public int findNumbers(int[] nums) {
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int j = 0;
            while (temp != 0){
                j ++;
                temp /= 10 ;
            }

            if(j%2 == 0 && j != 0){
                x++;
            }
        }
        return x;

    }

    public static void main(String[] args) {
        int[] nums = {12,345,2,6,7896};
        Solution solution = new Solution();
        solution.findNumbers(nums);
    }
}
