package com.study.leetcode.lc_19;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String restoreString(String s, int[] indices) {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            temp.put( indices[i],i );
        }
        StringBuilder sb = new StringBuilder();
        for(int x=0;x<s.length();x++)
        {
            sb.append( s.charAt( temp.get(x) ) );
        }

        return sb.toString();
    }
    /*给你一个字符串 s 和一个 长度相同 的整数数组 indices 。

    请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。

    返回重新排列后的字符串。
    输入：s = "codeleet", indices = [4,5,6,7,0,2,1,3]
    输出："leetcode"
    解释：如图所示，"codeleet" 重新排列后变为 "leetcode" 。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shuffle-string
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

    public static void main(String[] args) {
        Solution solution = new Solution();

    }
}
