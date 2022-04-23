package slidingWindow;

public class Solution {

    // 1208. 尽可能使字符串相等 https://leetcode-cn.com/problems/get-equal-substrings-within-budget/
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0, right = 0;
        int max = 0;
        int sum = 0;
        while (right < s.length()) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));
            right++;
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
