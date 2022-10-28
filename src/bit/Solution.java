package bit;

public class Solution {
    // 136. 只出现一次的数字 https://leetcode.cn/problems/single-number/?favorite=ex0k24j
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
