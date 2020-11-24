package com.study.leetcode.lc_14;

import com.server.basis.util.PowerUtil;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /*在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。

    给你一个整数数组 arr，请你从中找出并返回一个幸运数。

    如果数组中存在多个幸运数，只需返回 最大 的那个。
    如果数组中不含幸运数，则返回 -1 。
             

    示例 1：

    输入：arr = [2,2,3,4]
    输出：2
    解释：数组中唯一的幸运数是 2 ，因为数值 2 的出现频次也是 2 。
    示例 2：

    输入：arr = [1,2,2,3,3,3]
    输出：3
    解释：1、2 以及 3 都是幸运数，只需要返回其中最大的 3

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-lucky-integer-in-an-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

    public int findLucky(int[] arr) {
        Map<Integer,Integer > temp = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            temp.put( arr[i],temp.getOrDefault( arr[i],0 ) + 1 );
        }

        int r = -1;
        for(Map.Entry<Integer, Integer> entry : temp.entrySet()){
            Integer mapKey = entry.getKey();
            Integer mapValue = entry.getValue();
            if (mapKey.equals( mapValue )) {
                r = Math.max( mapKey,r );
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findLucky( new int[]{1,2,2,3,4} );
    }
}
